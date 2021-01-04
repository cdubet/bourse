package net.tuxanna.portefeuille.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Record6;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.SelectQuery;
import org.jooq.TableField;
import org.jooq.UpdateConditionStep;
import org.jooq.UpdateSetFirstStep;
import org.jooq.UpdateSetMoreStep;
import org.jooq.impl.DSL;

import net.tuxanna.database.jooq.public_.tables.Shares;
import net.tuxanna.database.jooq.public_.tables.Buy;
import net.tuxanna.database.jooq.public_.tables.Portfolio;
import net.tuxanna.database.jooq.public_.tables.Quotes;
import net.tuxanna.database.jooq.public_.tables.Sell;
import net.tuxanna.portefeuille.Quote;
import net.tuxanna.portefeuille.util.DataConverter;
import net.tuxanna.portefeuille.util.DigitValue;



public class Database implements DatabaseI
{
	static final  int NON_ASSIGNED=-1;
	private static final Logger logger = LogManager.getLogger(Database.class);

	private Connection conn;             //our connection to the db - persist for life of program

	public Database() throws ClassNotFoundException, SQLException
	{
		super();
		// Load the HSQL Database Engine JDBC driver
		// hsqldb.jar should be in the class path or made part of the current jar
		Class.forName("org.hsqldb.jdbcDriver");
		connect();
	}

	//used normally only for testing
	public Database(Connection connection) throws ClassNotFoundException
	{
		super();
		// Load the HSQL Database Engine JDBC driver
		// hsqldb.jar should be in the class path or made part of the current jar
		Class.forName("org.hsqldb.jdbcDriver");
		conn=connection;
	}

	public static final String getUsername()
	{
		return "portefeuille";
	}

	public static final String getPassword()
	{
		return "Dubettier!";
	}

	//allow to be overloaded for testing purpose
	private void connect() throws SQLException 
	{
		logger.entry("connect()"+getDatabaseName());
		conn = DriverManager.getConnection(getDatabaseName(), getUsername(), getPassword());
	}


	public void shutdown() throws SQLException 
	{
		logger.entry("shutdown");
		Statement st = conn.createStatement();

		// db writes out to files and performs clean shuts down
		// otherwise there will be an unclean shutdown
		// when program ends
		st.execute("SHUTDOWN");
		conn.close();    // if there are no other open connection
	}

	// just overwrite this to change DB (ex for test in in memory)
	public String getDatabaseName()
	{
		//TODO change the path here !
		logger.debug("using /home/christian/PORTEFEUILLE/portefeuilleDb");
		return "jdbc:hsqldb:file:/home/christian/PORTEFEUILLE/portefeuilleDb";
	}

	public boolean createSchema()
	{
		//TODO make a loop with a list of modifiers. each one for 1 table
		if (update(createTableShares()))
		{
			if (update(createTableQuotes()))
			{
				if (update(createTableAccount()))
				{
					if (update(createTablePortfolio()))
					{
						if (update(createTableSellOrders()))
						{
							logger.debug("createSchema OK");
							if (update(createTableBuyOrders()))
							{
								return true;
							}
							else
							{
								logger.error("createSchema createTableBuyOrders failed");
							}
						}
						else
						{
							logger.error("createSchema createTableSellOrders failed");
						}
					}
					else
					{
						logger.error("createSchema portfolio failed");
					}
				}
				else
				{
					logger.error("createSchema account failed");
				}
			}
			else
			{
				logger.error("createSchema table quotes failed");
			}
		}
		else
		{
			logger.error("createSchema table shares failed");
		}
		return false;
	}
	@Override
	public boolean loadSharesInPortfolio(List<PortfolioDB> listSharesInPorfolios)
	{
		try
		{
			DSLContext create = DSL.using(conn, SQLDialect.HSQLDB);

			Result<Record>result=	create.selectFrom(net.tuxanna.database.jooq.public_.tables.Portfolio.PORTFOLIO).
					where(net.tuxanna.database.jooq.public_.tables.Portfolio.PORTFOLIO.QTE.gt(0.)).
					orderBy(net.tuxanna.database.jooq.public_.tables.Portfolio.PORTFOLIO.IDSHARE).
					fetch();

			//TODO use JPA annotation and fetch().into(PortfolioDB.class);  instead of looping
			for (Record portfolioRecord : result)
			{
				listSharesInPorfolios.add(fillPortfolioFromRecord(portfolioRecord));
			}
			return true;
		}
		catch (org.jooq.exception.DataAccessException e)
		{
			logger.error(e);
			return false;
		}
	}

	private PortfolioDB fillPortfolioFromRecord(Record portfolioRecord)
	{
		PortfolioDB portfolioDB=new PortfolioDB();
		portfolioDB.setIdPortfolio( (int) portfolioRecord.getValue(Portfolio.PORTFOLIO.IDPORTFOLIO));
		portfolioDB.setIdShare((int) portfolioRecord.getValue(Portfolio.PORTFOLIO.IDSHARE));
		portfolioDB.setIdAccount((int) portfolioRecord.getValue(Portfolio.PORTFOLIO.IDACCOUNT));
		portfolioDB.setQte(new DigitValue( portfolioRecord.getValue(Portfolio.PORTFOLIO.QTE)));
		portfolioDB.setUnitPrice(new DigitValue(portfolioRecord.getValue(Portfolio.PORTFOLIO.UNITPRICE)));

		portfolioDB.setUseForSummary((int)portfolioRecord.getValue(Portfolio.PORTFOLIO.USEFORSUMMARY)!=0);
		return portfolioDB;
	}


	@Override
	public PortfolioDB getShareInPortfolio(int sharedId, int accountId)
	{
		try
		{
			DSLContext create = DSL.using(conn, SQLDialect.HSQLDB);

			Record result=	create.selectFrom(Portfolio.PORTFOLIO).
					where(Portfolio.PORTFOLIO.QTE.gt(0.).
							and(Portfolio.PORTFOLIO.IDSHARE.eq(sharedId)).
							and(Portfolio.PORTFOLIO.IDACCOUNT.eq(accountId))).
					fetchOne();
			if (result==null)
			{
				//not existing
				return null;
			}
			int idPortfolio= result.get(Portfolio.PORTFOLIO.IDPORTFOLIO);
			int idShare    = result.get(Portfolio.PORTFOLIO.IDSHARE);
			int idAccount  = result.get(Portfolio.PORTFOLIO.IDACCOUNT);
			double qte     = result.get(Portfolio.PORTFOLIO.QTE);
			double unitPrice=result.get(Portfolio.PORTFOLIO.UNITPRICE);

			PortfolioDB portfolioDB=new PortfolioDB();
			portfolioDB.setIdPortfolio(idPortfolio);
			portfolioDB.setIdShare(idShare);
			portfolioDB.setIdAccount(idAccount);
			portfolioDB.setQte(new DigitValue(qte));
			portfolioDB.setUnitPrice(new DigitValue(unitPrice));
			return portfolioDB;
		}
		catch (org.jooq.exception.DataAccessException e)
		{
			logger.error(e);
			return null;
		}
	}

	@Override
	public boolean loadSharesIdInPortfolio(List<Integer> listSharesId,boolean keepNonMeaningfullShares)
	{
		listSharesId.clear();
		//	table PORTFOLIO(idPortfolio primary key,idShare integer, idAccount integer, qte DOUBLE,unitPrice DOUBLE,useForSummary integer NOT NULL ,FOREIGN KEY (idShare) REFERENCES SHARES(idShare),FOREIGN KEY (idAccount) REFERENCES ACCOUNT(idAccount))";
		StringBuilder selectShareIdSqlRequest =  new StringBuilder("select distinct idShare from PORTFOLIO where qte >0.0");
		if (!keepNonMeaningfullShares)
		{
			selectShareIdSqlRequest.append(" and useForSummary<> 0");
		}

		PreparedStatement selectStatement;
		try
		{
			selectStatement = conn.prepareStatement(selectShareIdSqlRequest.toString());
		}
		catch (SQLException e)
		{
			logger.error(e);
			return false;
		}
		boolean res=true;
		try
		{
			ResultSet results = selectStatement.executeQuery();
			while (results.next())
			{
				int idShare   = results.getInt(1);
				listSharesId.add(idShare);
			}
		}
		catch (SQLException e)
		{
			logger.error(e);
			res=false;
		}
		finally
		{
			try
			{
				selectStatement.close();
			}
			catch (SQLException e)
			{
				logger.error(e);
				res=false;
			}
		}
		return res;
	}
	/* (non-Javadoc)
	 * @see net.tuxanna.database.jooq.DatabaseI#loadShares(java.util.List)
	 */
	@Override
	public boolean loadShares(List<ShareDB> listShares)
	{
		try
		{
			DSLContext create = DSL.using(conn, SQLDialect.HSQLDB);

			Result<?>result=create.select(Shares.SHARES.IDSHARE,
					Shares.SHARES.NAME,
					Shares.SHARES.CURRENCY,
					Shares.SHARES.IS_SHARE,
					Shares.SHARES.TICKER).from(Shares.SHARES).fetch();
			for (Record shareRecord : result)
			{
				ShareDB shareDb=new ShareDB();
				shareDb.setId(shareRecord.get(Shares.SHARES.IDSHARE));
				shareDb.setName(shareRecord.get(Shares.SHARES.NAME));
				shareDb.setCurrency(DataConverter.convertToCurrency(shareRecord.get(Shares.SHARES.CURRENCY).charAt(0)));
				shareDb.setIsShare(DataConverter.convertIsShare(shareRecord.get(Shares.SHARES.IS_SHARE).charAt(0)));
				shareDb.setTicker(shareRecord.get(Shares.SHARES.TICKER));

				listShares.add(shareDb);
			}
			return true;
		}
		catch (org.jooq.exception.DataAccessException e)
		{
			logger.error(e);
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see net.tuxanna.database.jooq.DatabaseI#readQuotationAtOneDay(net.tuxanna.database.jooq.ShareDB, java.util.Date)
	 */
	@Override
	public QuoteDB readQuotationAtOneDay(SearchQuoteAtOneDay seachQuote)
	{
		QuoteDB quoteDB=new QuoteDB();
		if (seachQuote.buildPreparedStatement(conn))
		{
			List<QuoteDB> listQuote=new ArrayList<>();
			if (seachQuote.executeAndFill(listQuote))
			{
				final int size=listQuote.size();
				if (size==0)
				{
					//nothing found
					logger.debug("no quote found for id={} date={}",seachQuote.getShare().getId(),seachQuote.getDate());
				}
				else if (size==1)
				{
					quoteDB=listQuote.get(0);
				}
				else
				{
					// if there is more than one get the oldest one
					quoteDB=listQuote.get(size-1);
				}
				return quoteDB;
			}
		}
		return quoteDB;	    

	}

	/* (non-Javadoc)
	 * @see net.tuxanna.database.jooq.DatabaseI#readQuotations(java.util.List, net.tuxanna.database.jooq.ShareDB)
	 */
	@Override
	public boolean readQuotations(List<QuoteDB> listQuote,SearchQuoteI seachQuote)
	{
		if (seachQuote.buildPreparedStatement(conn))
		{
			if (seachQuote.executeAndFill(listQuote))
			{
				return true;
			}
		}
		return false;

	}



	/* (non-Javadoc)
	 * @see net.tuxanna.database.jooq.DatabaseI#storeQuotation(java.util.List)
	 */
	@Override
	public boolean insertQuotation(List<QuoteDB> listQuote)
	{
		final String INSERT_QUOTE = "insert into QUOTES(idShare,dateQuote,lastTradedPrice,changeInPrice,openPrice,highPrice,lowPrice,volume,low52Week,high52Week,mobileAverage50Days,mobileAverage200Days,previousClose,peRatio,shortRatio) values (?,?,?,?,?,?,? ,? ,?,?,?,?,?,?,?)";
		PreparedStatement insertStatement;
		try
		{
			insertStatement = conn.prepareStatement(INSERT_QUOTE);
		}
		catch (SQLException e)
		{
			logger.error(e);
			return false;
		}
		for (QuoteDB quotation : listQuote)
		{
			if (quotation.hasRequiredDataForPersistance())
			{
				try
				{
					fillInsertStatementQuotation(insertStatement, quotation);
					final int iUpdateCount = insertStatement.executeUpdate();
					if (iUpdateCount !=1)
					{
						logger.error("failed to insert {} {}",iUpdateCount,quotation.toString());
					}				
				}
				catch (SQLException e)
				{
					logger.error(e);
					try
					{
						insertStatement.close();
					}
					catch (SQLException e1)
					{
						logger.error(e);
					}
					return false;
				}
			}
			else
			{
				logger.error("invalid quotation {}",quotation.toString());
			}

		}
		try
		{
			insertStatement.close();
		}
		catch (SQLException e)
		{
			logger.error(e);
			return false;
		}
		logger.traceExit("added ",listQuote.size());
		return true;
	}

	private void fillInsertStatementQuotation(PreparedStatement insertStatement, QuoteDB quotation) throws SQLException
	{
		insertStatement.setInt(1, quotation.getIdShare());
		insertStatement.setDate(2, new java.sql.Date(quotation.getDate().getTime()));
		insertStatement.setDouble(3,quotation.getQuotation().getLastTradedPrice().getValue());
		{	
			DigitValue valToSave = quotation.getQuotation().getChangeInPrice();
			if (valToSave.isValid())
			{
				insertStatement.setDouble(4,valToSave.getValue());
			}
			else
			{
				insertStatement.setNull(4,Types.DOUBLE);
			}
		}
		{	
			DigitValue valToSave = quotation.getQuotation().getOpenPrice();
			if (valToSave.isValid())
			{
				insertStatement.setDouble(5,valToSave.getValue());
			}
			else
			{
				insertStatement.setNull(5,Types.DOUBLE);
			}
		}
		{	
			DigitValue valToSave = quotation.getQuotation().getHighPrice();
			if (valToSave.isValid())
			{
				insertStatement.setDouble(6,valToSave.getValue());
			}
			else
			{
				insertStatement.setNull(6,Types.DOUBLE);
			}
		}
		{	
			DigitValue valToSave = quotation.getQuotation().getLowPrice();
			if (valToSave.isValid())
			{
				insertStatement.setDouble(7,valToSave.getValue());
			}
			else
			{
				insertStatement.setNull(7,Types.DOUBLE);
			}
		}
		{	
			DigitValue valToSave = quotation.getQuotation().getVolume();
			if (valToSave.isValid())
			{
				insertStatement.setDouble(8,valToSave.getValue());
			}
			else
			{
				insertStatement.setNull(8,Types.DOUBLE);
			}
		}
		{	
			DigitValue valToSave = quotation.getQuotation().getLow52Week();
			if (valToSave.isValid())
			{
				insertStatement.setDouble(9,valToSave.getValue());
			}
			else
			{
				insertStatement.setNull(9,Types.DOUBLE);
			}
		}
		{	
			DigitValue valToSave = quotation.getQuotation().getHigh52Week();
			if (valToSave.isValid())
			{
				insertStatement.setDouble(10,valToSave.getValue());
			}
			else
			{
				insertStatement.setNull(10,Types.DOUBLE);
			}
		}
		{	
			DigitValue valToSave = quotation.getQuotation().getMobileAverage50Days();
			if (valToSave.isValid())
			{
				insertStatement.setDouble(11,valToSave.getValue());
			}
			else
			{
				insertStatement.setNull(11,Types.DOUBLE);
			}
		}
		{	
			DigitValue valToSave = quotation.getQuotation().getMobileAverage200Days();
			if (valToSave.isValid())
			{
				insertStatement.setDouble(12,valToSave.getValue());
			}
			else
			{
				insertStatement.setNull(12,Types.DOUBLE);
			}
		}
		{	
			DigitValue valToSave = quotation.getQuotation().getPreviousClose();
			if (valToSave.isValid())
			{
				insertStatement.setDouble(13,valToSave.getValue());
			}
			else
			{
				insertStatement.setNull(13,Types.DOUBLE);
			}
		}
		{	
			DigitValue valToSave = quotation.getQuotation().getPeRatio();
			if (valToSave.isValid())
			{
				insertStatement.setDouble(14,valToSave.getValue());
			}
			else
			{
				insertStatement.setNull(14,Types.DOUBLE);
			}
		}
		{	
			DigitValue valToSave = quotation.getQuotation().getShortRatio();
			if (valToSave.isValid())
			{
				insertStatement.setDouble(15,valToSave.getValue());
			}
			else
			{
				insertStatement.setNull(15,Types.DOUBLE);
			}
		}
	}

	/* (non-Javadoc)
	 * @see net.tuxanna.database.jooq.DatabaseI#storeShares(java.util.List)
	 */
	@Override
	public boolean storeShares(List<ShareDB> listShares)
	{
		try
		{
			DSLContext create = DSL.using(conn, SQLDialect.HSQLDB);

			for (ShareDB share : listShares)
			{

				create.insertInto(Shares.SHARES).
				set(Shares.SHARES.NAME,share.getName() ).
				set(Shares.SHARES.TICKER,share.getTicker() ).
				set(Shares.SHARES.IS_SHARE,DataConverter.convertIsShareToString(share.isShare()) ).
				set(Shares.SHARES.CURRENCY,DataConverter.convertCurrencyToString(share.getCurrency()) ).
				execute();

			}
			return true;
		}
		catch (org.jooq.exception.DataAccessException e)
		{
			logger.error(e);
			return false;
		}
	}

	private static  String createTableShares()
	{
		return "create table SHARES(idShare integer generated by default as IDENTITY(start with 0 increment by 1) primary key ,"
				+ "name varchar(30) not null, "
				+ "ticker varchar(20) not null,"
				+ "currency char not null,"
				+ "is_share char not null"
				+ ")";
	}
	private static  String createTableQuotes()
	{
		return "create table QUOTES(idQuotes integer generated by default as IDENTITY(start with 0 increment by 1) primary key,"
				+ "idShare integer,dateQuote TIMESTAMP WITHOUT TIME ZONE not NULL,lastTradedPrice DOUBLE not NULL,changeInPrice DOUBLE,	openPrice DOUBLE,highPrice DOUBLE,lowPrice DOUBLE,volume DOUBLE,low52Week DOUBLE,high52Week DOUBLE,	mobileAverage50Days DOUBLE,	mobileAverage200Days DOUBLE,previousClose DOUBLE,peRatio DOUBLE,shortRatio DOUBLE,FOREIGN KEY (idShare) REFERENCES SHARES(idShare))";
	}

	private static  String createTablePortfolio()
	{
		//quantity for funds could be not an int 
		return "create table PORTFOLIO(idPortfolio integer generated by default as IDENTITY(start with 0 increment by 1) primary key,idShare integer, idAccount integer, qte DOUBLE,unitPrice DOUBLE,useForSummary integer NOT NULL ,FOREIGN KEY (idShare) REFERENCES SHARES(idShare),FOREIGN KEY (idAccount) REFERENCES ACCOUNT(idAccount))";
	}

	private static  String createTableAccount()
	{
		return "create table ACCOUNT(idAccount integer generated by default as IDENTITY(start with 0 increment by 1) primary key,name varchar(30) not null)";
	}

	private static  String createTableSellOrders()
	{ 
		return "create table SELL(idSell integer generated by default as IDENTITY(start with 0 increment by 1) primary key,"+
				"idShare integer,idAccount integer ," + //foreign keys
				"qte DOUBLE not NULL,unitPriceRequested DOUBLE not NULL,unitPriceSold DOUBLE,"+
				"state integer NOT NULL ,"+ //0 activated, 1 executed,2 no longer valid
				"dateExpiration TIMESTAMP WITHOUT TIME ZONE not NULL,"+
				"FOREIGN KEY (idShare) REFERENCES SHARES(idShare),FOREIGN KEY (idAccount) REFERENCES ACCOUNT(idAccount)"+ 
				");";
	}

	private static  String createTableBuyOrders()
	{ 
		return "create table BUY(idbuy integer generated by default as IDENTITY(start with 0 increment by 1) primary key,"+
				"idShare integer,idAccount integer ," + //foreign keys
				"qte DOUBLE not NULL,unitPriceRequested DOUBLE not NULL,unitPriceBought DOUBLE,"+
				"state integer NOT NULL ,"+ //0 activated, 1 executed,2 no longer valid
				"dateExpiration TIMESTAMP WITHOUT TIME ZONE not NULL,"+
				"FOREIGN KEY (idShare) REFERENCES SHARES(idShare),FOREIGN KEY (idAccount) REFERENCES ACCOUNT(idAccount)"+ 
				");";
	}

	public boolean checkIfTableExist(String tableName) throws SQLException
	{
		//SELECT * FROM information_schema.tables where table_name='SELL'
		StringBuilder selectRequest =  new StringBuilder("SELECT * FROM information_schema.tables where table_name='");
		selectRequest.append(tableName);
		selectRequest.append("' LIMIT 1");

		PreparedStatement selectStatement;
		selectStatement = conn.prepareStatement(selectRequest.toString());

		boolean exist;
		ResultSet results = selectStatement.executeQuery();
		if (results.next())
		{
			exist=true;
		}
		else
		{
			exist=false;
		}
		selectStatement.close(); //TODO manage correctly exception to avoid not closing 
		return exist;
	}

	@Override
	public boolean updateSchema()
	{
		try
		{
			if (!checkIfTableExist("SELL"))
			{
				if (update(createTableSellOrders()))
				{
					logger.debug("sell table created");
				}
				else
				{
					return false;
				}
			}
			if (!checkIfTableExist("BUY"))
			{
				if (update(createTableBuyOrders()))
				{
					logger.debug("buy table created");
				}
				else
				{
					return false;
				}
			}
		}
		catch (SQLException e)
		{
			logger.error(e);
			return false;
		}
		return true;
	}
	//use for SQL commands CREATE, DROP, INSERT and UPDATE
	public synchronized boolean update(String expression) {

		Statement st = null;

		try
		{
			st = conn.createStatement();
		}
		catch (SQLException e)
		{
			logger.error("update failed",e);
			return false;
		}    // statements

		int i=-1;
		try
		{
			i = st.executeUpdate(expression);
		}
		catch (SQLException e)
		{
			logger.error("update failed",e);
		}    // run the query
		finally
		{
			try
			{
				st.close();
			}
			catch (SQLException e)
			{
				logger.error("update failed",e);
				return false;
			}
		}
		if (i == -1) 
		{
			logger.error("db error {} ",expression);
			return false;
		}
		return true;

	}

	@Override
	public boolean loadValidSellOrders(List<ShareOrderI> listOrders,java.util.Date date)
	{
		listOrders.clear();
		LocalDateTime newLimitTime = makeLocalDateTimeAtMidnight(date);

		try
		{
			DSLContext create = DSL.using(conn, SQLDialect.HSQLDB);

			Result<?>result=create.select(Sell.SELL.IDSELL,
					Sell.SELL.IDSHARE,
					Sell.SELL.IDACCOUNT,
					Sell.SELL.QTE,
					Sell.SELL.UNITPRICEREQUESTED,
					Shares.SHARES.NAME).
					from(Sell.SELL, Shares.SHARES).
					where(Sell.SELL.DATEEXPIRATION.ge(newLimitTime).and(Sell.SELL.STATE.eq(0).and(Shares.SHARES.IDSHARE.eq(Sell.SELL.IDSHARE)))).//0 = not expired
					orderBy(Sell.SELL.IDSELL).
					fetch();
			for (Record sellrecord : result)
			{
				SellDB buyDb=new SellDB();
				buyDb.setIdSell(sellrecord.get(Sell.SELL.IDSELL));
				buyDb.setIdShare(sellrecord.get(Sell.SELL.IDSHARE));
				buyDb.setIdAccount(sellrecord.get(Sell.SELL.IDACCOUNT));
				buyDb.setQte(sellrecord.get(Sell.SELL.QTE));
				buyDb.setUnitPriceRequested(sellrecord.get(Sell.SELL.UNITPRICEREQUESTED));

				buyDb.setShareName(sellrecord.get(Shares.SHARES.NAME));

				listOrders.add(buyDb);
			}
			return true;
		}
		catch (org.jooq.exception.DataAccessException e)
		{
			logger.error(e);
			return false;
		}
	}
	@Override
	public boolean updateSellOrder(ShareOrderI sellDB)
	{
		try
		{
			DSLContext create = DSL.using(conn, SQLDialect.HSQLDB);

			Double valSold=null;
			if (sellDB.getUnitPriceExecuted().isValid())
			{
				valSold=sellDB.getUnitPriceExecuted().getValue();
			}

			create.update(net.tuxanna.database.jooq.public_.tables.Sell.SELL).
			set(net.tuxanna.database.jooq.public_.tables.Sell.SELL.UNITPRICESOLD,valSold ).
			set(net.tuxanna.database.jooq.public_.tables.Sell.SELL.QTE,sellDB.getQte() ).
			set(net.tuxanna.database.jooq.public_.tables.Sell.SELL.STATE,sellDB.getState().ordinal()).
			where(net.tuxanna.database.jooq.public_.tables.Sell.SELL.IDSELL.eq(sellDB.getId())).
			execute();

			return true;
		}
		catch (org.jooq.exception.DataAccessException e)
		{
			logger.error(e);
			return false;
		}
	}

	@Override
	public boolean updateBuyOrder(ShareOrderI sellDB)
	{
		try
		{
			DSLContext create = DSL.using(conn, SQLDialect.HSQLDB);

			Double valSold=null;
			if (sellDB.getUnitPriceExecuted().isValid())
			{
				valSold=sellDB.getUnitPriceExecuted().getValue();
			}

			create.update(Buy.BUY).
			set(Buy.BUY.UNITPRICEBOUGHT,valSold ).
			set(Buy.BUY.QTE,sellDB.getQte() ).
			set(Buy.BUY.STATE,sellDB.getState().ordinal()).
			where(Buy.BUY.IDBUY.eq(sellDB.getId())).
			execute();

			return true;
		}
		catch (org.jooq.exception.DataAccessException e)
		{
			logger.error(e);
			return false;
		}
	}

	@Override
	public boolean updatePortfolio(PortfolioDB portfolioDB)
	{
		double newQte;
		double newUnitPrice;
		if (portfolioDB.getQte().isValid())
		{
			newQte=portfolioDB.getQte().getValue();
			if (portfolioDB.getUnitPrice().isValid())
			{
				newUnitPrice=portfolioDB.getUnitPrice().getValue() ;
			}
			else
			{
				logger.error("failed to update, invalid price {}",portfolioDB.toString());
				return false;
			}
		}
		else
		{
			logger.error("failed to update, invalid qte {}",portfolioDB.toString());
			return false;
		}

		try
		{
			DSLContext create = DSL.using(conn, SQLDialect.HSQLDB);
			if (portfolioDB.getIdPortfolio()==Database.NON_ASSIGNED)
			{
				// new item to add
				int useForSummary;
				if (portfolioDB.isUseForSummary())
				{
					useForSummary=1;
				}
				else
				{
					useForSummary=0;
				}
				create.insertInto(Portfolio.PORTFOLIO).
				set(Portfolio.PORTFOLIO.IDACCOUNT,portfolioDB.getIdAccount() ).
				set(Portfolio.PORTFOLIO.IDSHARE,portfolioDB.getIdShare() ).
				set(Portfolio.PORTFOLIO.UNITPRICE,newUnitPrice ).
				set(Portfolio.PORTFOLIO.QTE,newQte ).
				set(Portfolio.PORTFOLIO.USEFORSUMMARY,useForSummary ).
				execute();
			}
			else
			{
				//existing -> just update
				create.update(Portfolio.PORTFOLIO).
				set(Portfolio.PORTFOLIO.UNITPRICE,newUnitPrice ).
				set(Portfolio.PORTFOLIO.QTE,newQte ).
				where(Portfolio.PORTFOLIO.IDPORTFOLIO.eq(portfolioDB.getIdPortfolio())).
				execute();
			}
			return true;
		}
		catch (org.jooq.exception.DataAccessException e)
		{
			logger.error(e);
			return false;
		}

	}

	@Override
	public boolean loadValidBuyOrders(List<ShareOrderI> listBuyOrders, java.util.Date date)
	{
		LocalDateTime newLimitTime = makeLocalDateTimeAtMidnight(date);

		try
		{
			DSLContext create = DSL.using(conn, SQLDialect.HSQLDB);

			/*	Result<BuyRecord> result=	create.selectFrom(net.tuxanna.database.jooq.public_.tables.Buy.BUY).
					where(net.tuxanna.database.jooq.public_.tables.Buy.BUY.DATEEXPIRATION.ge(newLimitTime).
							and(net.tuxanna.database.jooq.public_.tables.Buy.BUY.STATE.eq(0))).//0 = not expired
					orderBy(net.tuxanna.database.jooq.public_.tables.Buy.BUY.IDBUY).
					fetch();
			 */
			Result<?>result=create.select(Buy.BUY.IDBUY,Buy.BUY.IDSHARE,Buy.BUY.IDACCOUNT,Buy.BUY.QTE,Buy.BUY.UNITPRICEREQUESTED,Shares.SHARES.NAME).
					from(Buy.BUY, Shares.SHARES).
					where(Buy.BUY.DATEEXPIRATION.ge(newLimitTime).and(Buy.BUY.STATE.eq(0).and(Shares.SHARES.IDSHARE.eq(Buy.BUY.IDSHARE)))).//0 = not expired
					orderBy(Buy.BUY.IDBUY).
					fetch();
			for (Record buyRecord : result)
			{
				BuyDB buyDb=new BuyDB();
				buyDb.setIdBuy(buyRecord.get(Buy.BUY.IDBUY));
				buyDb.setIdShare(buyRecord.get(Buy.BUY.IDSHARE));
				buyDb.setIdAccount(buyRecord.get(Buy.BUY.IDACCOUNT));
				buyDb.setQte(buyRecord.get(Buy.BUY.QTE));
				buyDb.setUnitPriceRequested(buyRecord.get(Buy.BUY.UNITPRICEREQUESTED));

				buyDb.setShareName(buyRecord.get(Shares.SHARES.NAME));

				listBuyOrders.add(buyDb);
			}
			return true;
		}
		catch (org.jooq.exception.DataAccessException e)
		{
			logger.error(e);
			return false;
		}
	}

	private LocalDateTime makeLocalDateTimeAtMidnight(java.util.Date date)
	{
		Calendar cal = Calendar.getInstance(); // creates calendar
		cal.setTime(date); // sets calendar time/date

		//take all the day as valid    	    
		final int year=cal.get(Calendar.YEAR);
		final int month=cal.get(Calendar.MONTH)+1;//january start with 0
		final int dayOfMonth=cal.get(Calendar.DAY_OF_MONTH);
		final int hour=23;
		final int minute=59; //midnight

		LocalDateTime newLimitTime=LocalDateTime.of( year, month, dayOfMonth,  hour, minute);
		return newLimitTime;
	}

	@Override
	public Optional<Double> getHighTradedPrice(int shareId)
	{
		Optional<Double> highPrice ;
		try
		{
			DSLContext create = DSL.using(conn, SQLDialect.HSQLDB);
			Record1<Double>result=create.select(Quotes.QUOTES.HIGHPRICE).
					from(Quotes.QUOTES).
					where(Quotes.QUOTES.IDSHARE.eq(shareId)).
					orderBy(Quotes.QUOTES.DATEQUOTE.desc()).
					limit(1).
					fetchOne();
			if (result == null)
			{
				highPrice= Optional.ofNullable(null);
			}
			else
			{
				highPrice= Optional.of(result.value1());			
			}
		}
		catch (org.jooq.exception.DataAccessException e)
		{
			logger.error(e);
			highPrice= Optional.ofNullable(null);
		}
		return highPrice; 
	}  

	@Override
	public Optional<Double> getLowTradedPrice(int shareId)
	{
		Optional<Double> lowPrice ;
		try
		{
			DSLContext create = DSL.using(conn, SQLDialect.HSQLDB);
			Record1<Double>result=create.select(Quotes.QUOTES.LOWPRICE).
					from(Quotes.QUOTES).
					where(Quotes.QUOTES.IDSHARE.eq(shareId)).
					orderBy(Quotes.QUOTES.DATEQUOTE.desc()).
					limit(1).
					fetchOne();
			if (result == null)
			{
				lowPrice= Optional.ofNullable(null);
			}
			else
			{
				lowPrice= Optional.of(result.value1());			
			}
		}
		catch (org.jooq.exception.DataAccessException e)
		{
			logger.error(e);
			lowPrice= Optional.ofNullable(null);
		}
		return lowPrice; 
	}  

	private void deleteQuotesWithId(List<Integer> listIdToBeDeleted)
	{
		final int MAX_DELETE_ID=100;// do not give a 1000 list to sql
		if (listIdToBeDeleted.size()>MAX_DELETE_ID)
		{
			int fromIndex=0;
			int toIndex=MAX_DELETE_ID;
			List<Integer> newListShorter=listIdToBeDeleted.subList(fromIndex, toIndex);
			deleteQuotesWithId(newListShorter);

			fromIndex=toIndex+1;
			List<Integer> remaining=listIdToBeDeleted.subList(fromIndex, listIdToBeDeleted.size()-1);
			deleteQuotesWithId(remaining);
		}
		else
		{
			try
			{
				DSLContext create = DSL.using(conn, SQLDialect.HSQLDB);
				create.delete(Quotes.QUOTES).where(Quotes.QUOTES.IDQUOTES.in(listIdToBeDeleted)).execute();
			}
			catch (org.jooq.exception.DataAccessException e)
			{
				logger.error(e);
			}
		}
	}
	@Override
	public void deleteOutdatedQuotes(java.util.Date limitDateForDeletion)
	{
		try
		{
			Calendar cal = Calendar.getInstance(); // creates calendar

			cal.setTime(limitDateForDeletion); // sets calendar time/date
			//take all the day as valid    	    
			final int year=cal.get(Calendar.YEAR);
			final int month=cal.get(Calendar.MONTH);
			final int dayOfMonth=cal.get(Calendar.DAY_OF_MONTH);
			final int hour=cal.get(Calendar.HOUR);
			final int minute=cal.get(Calendar.MINUTE);

			LocalDateTime dateLimit=LocalDateTime.of( year, month, dayOfMonth,  hour, minute);

			DSLContext create = DSL.using(conn, SQLDialect.HSQLDB);
			//first get all share ID
			Result<Record1<Integer>>listShareId=create.select(Shares.SHARES.IDSHARE).
					from(Shares.SHARES).
					fetch();
			List<Integer> listIdToBeDeleted=new ArrayList<>();
			for (Record1<Integer> sharedId : listShareId)
			{
				final Integer idShare=sharedId.get(Shares.SHARES.IDSHARE);
				Result<Record2<Integer, LocalDateTime>>listQuotes=create.select(Quotes.QUOTES.IDQUOTES, Quotes.QUOTES.DATEQUOTE).
						from(Quotes.QUOTES).
						where((Quotes.QUOTES.IDSHARE.eq(idShare).and(Quotes.QUOTES.DATEQUOTE.le(dateLimit)))).
						orderBy(Quotes.QUOTES.DATEQUOTE). //so we are sure that next is more recent
						fetch();
				int lastDay=-1; //impossible value
				Integer Id=null;
				for (Record2<Integer, LocalDateTime> recordDate : listQuotes)
				{
					LocalDateTime localTime=recordDate.get(Quotes.QUOTES.DATEQUOTE);

					final int day=localTime.getDayOfYear();
					if (day== lastDay)
					{
						// same day -> this one is older as we are sorted -> delete the previous one
						listIdToBeDeleted.add(Id);
					}
					else
					{
						//different day -> DO NOT DELETE THE PREVIOUS ONE
					}
					lastDay=day;
					Id=recordDate.get(Quotes.QUOTES.IDQUOTES);
				}
			}
			deleteQuotesWithId(listIdToBeDeleted);
		}
		catch (org.jooq.exception.DataAccessException e)
		{
			logger.error(e);
		}
	}
	//use for SQL command SELECT
	//	public synchronized void query(String expression) throws SQLException {
	//
	//		Statement st = null;
	//		ResultSet rs = null;
	//
	//		st = conn.createStatement();         // statement objects can be reused with
	//
	//		// repeated calls to execute but we
	//		// choose to make a new one each time
	//		rs = st.executeQuery(expression);    // run the query
	//
	//
	//		st.close();    // NOTE!! if you close a statement the associated ResultSet is
	//
	//		// closed too
	//		// so you should copy the contents to some other object.
	//		// the result set is invalidated also  if you recycle an Statement
	//		// and try to execute some other query before the result set has been
	//		// completely examined.
	//	}

	@Override
	public List<Integer> checkQuotes()
	{
		List<Integer> listIdToBeChecked=new ArrayList<>();
		try
		{
			DSLContext create = DSL.using(conn, SQLDialect.HSQLDB);
			//first get all share ID
			Result<Record1<Integer>>listShareId=create.select(Shares.SHARES.IDSHARE).
					from(Shares.SHARES).
					fetch();

			for (Record1<Integer> sharedId : listShareId)
			{
				final Integer idShare=sharedId.get(Shares.SHARES.IDSHARE);
				Result<Record6<Integer, LocalDateTime, Double, Double, Double, Double>>listQuotes=create.select(Quotes.QUOTES.IDQUOTES,
						Quotes.QUOTES.DATEQUOTE,
						Quotes.QUOTES.LASTTRADEDPRICE,
						Quotes.QUOTES.OPENPRICE,
						Quotes.QUOTES.HIGHPRICE,
						Quotes.QUOTES.LOWPRICE).
						from(Quotes.QUOTES).
						where((Quotes.QUOTES.IDSHARE.eq(idShare))).
						orderBy(Quotes.QUOTES.DATEQUOTE). //so we are sure that next is more recent
						fetch();
				int lastDay=-1; //impossible value
				final double MAX_CHANGE_INTRADAY=15.; // 15 %
				final double MAX_CHANGE_NEXT_DAY=20.; //20 %
				Double lastTradedPrice=null;
				Double lastOpenPrice=null;
				Double lastHighPrice=null;
				Double lastLowPrice=null;


				for (Record6<Integer, LocalDateTime, Double, Double, Double, Double> recordDate : listQuotes)
				{
					LocalDateTime localTime=recordDate.get(Quotes.QUOTES.DATEQUOTE);

					final int day=localTime.getDayOfYear();
					if (day== lastDay)
					{
						// same day 
						lastTradedPrice=checkVariationForOneQuotation(listIdToBeChecked, 
								Quotes.QUOTES.LASTTRADEDPRICE,
								MAX_CHANGE_INTRADAY, 
								lastTradedPrice,
								recordDate);
						lastOpenPrice=checkVariationForOneQuotation(listIdToBeChecked, 
								Quotes.QUOTES.OPENPRICE,
								MAX_CHANGE_INTRADAY, 
								lastOpenPrice,
								recordDate);
						lastHighPrice=checkVariationForOneQuotation(listIdToBeChecked, 
								Quotes.QUOTES.HIGHPRICE,
								MAX_CHANGE_INTRADAY, 
								lastHighPrice,
								recordDate);
						lastLowPrice=checkVariationForOneQuotation(listIdToBeChecked, 
								Quotes.QUOTES.LOWPRICE,
								MAX_CHANGE_INTRADAY, 
								lastLowPrice,
								recordDate);
					}
					else
					{
						lastTradedPrice=checkVariationForOneQuotation(listIdToBeChecked,
								Quotes.QUOTES.LASTTRADEDPRICE,
								MAX_CHANGE_NEXT_DAY,
								lastTradedPrice,
								recordDate);
						lastOpenPrice=checkVariationForOneQuotation(listIdToBeChecked, 
								Quotes.QUOTES.OPENPRICE,
								MAX_CHANGE_NEXT_DAY, 
								lastOpenPrice,
								recordDate);
						lastHighPrice=checkVariationForOneQuotation(listIdToBeChecked, 
								Quotes.QUOTES.HIGHPRICE,
								MAX_CHANGE_NEXT_DAY, 
								lastHighPrice,
								recordDate);
						lastLowPrice=checkVariationForOneQuotation(listIdToBeChecked, 
								Quotes.QUOTES.LOWPRICE,
								MAX_CHANGE_NEXT_DAY, 
								lastLowPrice,
								recordDate);
					}
					lastDay=day;

				}
			}
		}
		catch (org.jooq.exception.DataAccessException e)
		{
			logger.error(e);
			listIdToBeChecked.clear();
		}
		return listIdToBeChecked;
	}

	private Double checkVariationForOneQuotation(List<Integer> listIdToBeChecked, 
			final TableField<Record, Double> fieldToCheck,
			final double MAX_CHANGE_INTRADAY,
			Double lastPrice, 
			Record6<Integer, LocalDateTime, Double, Double, Double, Double> recordDate)
	{
		Double priceToCheck=recordDate.get(fieldToCheck );
		if (checkVariationOutOfTolerance(priceToCheck,lastPrice,MAX_CHANGE_INTRADAY))
		{
			Integer Id=recordDate.get(Quotes.QUOTES.IDQUOTES);
			listIdToBeChecked.add(Id);
			logger.warn("quotation invalid found for IDQUOTES={}",Id.toString());
		}
		else
		{
			if (priceToCheck != null)
			{
				lastPrice=priceToCheck;//valid value, remember it
			}							
		}
		return lastPrice;
	}

	private boolean checkVariationOutOfTolerance(Double valueToCheck, 
			Double referencedValue,
			double maxAllowedChangeInPercent)
	{
		if (valueToCheck == null)
		{
			return false;
		}
		if (referencedValue == null)
		{
			return false;
		}
		// both have a value
		final double percentVariation=Math.abs(100.*valueToCheck/referencedValue -100.);
		final boolean isBelowAllowedChange= (percentVariation< maxAllowedChangeInPercent);
		return isBelowAllowedChange;
	}

	@Override
	public ShareDB loadShare(String shareName)
	{
		try
		{
			DSLContext create = DSL.using(conn, SQLDialect.HSQLDB);
			List<ShareDB> listShares=new ArrayList<>();

			Result<?>result=create.select(Shares.SHARES.IDSHARE,
					Shares.SHARES.NAME,
					Shares.SHARES.CURRENCY,
					Shares.SHARES.IS_SHARE,
					Shares.SHARES.TICKER).from(Shares.SHARES).
					where(Shares.SHARES.NAME.startsWithIgnoreCase(shareName)).fetch();
			for (Record shareRecord : result)
			{
				ShareDB shareDb=new ShareDB();
				shareDb.setId(shareRecord.get(Shares.SHARES.IDSHARE));
				shareDb.setName(shareRecord.get(Shares.SHARES.NAME));
				shareDb.setCurrency(DataConverter.convertToCurrency(shareRecord.get(Shares.SHARES.CURRENCY).charAt(0)));
				shareDb.setIsShare(DataConverter.convertIsShare(shareRecord.get(Shares.SHARES.IS_SHARE).charAt(0)));
				shareDb.setTicker(shareRecord.get(Shares.SHARES.TICKER));

				listShares.add(shareDb);
			}
			if (listShares.size()==1)
			{
				return listShares.get(0);
			}
			else
			{
				logger.error("too much matches for {}",shareName);
				for (ShareDB share:listShares)
				{
					logger.error(share.getName());
				}
			}
			return null;
		}
		catch (org.jooq.exception.DataAccessException e)
		{
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<QuoteDB> readQuotations(ConditionQuoteI condition)
	{
		DSLContext create = DSL.using(conn, SQLDialect.HSQLDB);
		SelectQuery<Record> query = create.selectQuery();
		query.addFrom(Quotes.QUOTES);
		query.addConditions(condition.getCondition());

		Result<?> result = query.fetch();
		List<QuoteDB> listQuotes=new ArrayList<>();
		for (Record shareRecord : result)
		{
			QuoteDB quoteDb=new QuoteDB();
			quoteDb.setIdQuote(shareRecord.get(Quotes.QUOTES.IDQUOTES));
			quoteDb.setIdShare(shareRecord.get(Quotes.QUOTES.IDSHARE));
			quoteDb.setDate(java.sql.Timestamp.valueOf(shareRecord.get(Quotes.QUOTES.DATEQUOTE)));

			fillQuotationFromDatabase(shareRecord, quoteDb);
			listQuotes.add(quoteDb);
		}
		return listQuotes;
	}

	private void fillQuotationFromDatabase(Record shareRecord, QuoteDB quoteDb)
	{
		Quote quote=new Quote();
		Double val = shareRecord.get(Quotes.QUOTES.CHANGEINPRICE);
		if (val != null)
		{
			quote.setChangeInPrice(val);
		}
		val=shareRecord.get(Quotes.QUOTES.HIGH52WEEK);
		if (val != null)
		{
			quote.setHigh52Week(val);
		}
		val=shareRecord.get(Quotes.QUOTES.HIGHPRICE);
		if (val != null)
		{
			quote.setHighPrice(val);
		}
		val=shareRecord.get(Quotes.QUOTES.LOW52WEEK);
		if (val != null)
		{
			quote.setLow52Week(val);
		}
		val=shareRecord.get(Quotes.QUOTES.LOWPRICE);
		if (val != null)
		{
			quote.setLowPrice(val);
		}
		val=shareRecord.get(Quotes.QUOTES.LASTTRADEDPRICE);
		if (val != null)
		{
			quote.setLastTradedPrice(val);
		}
		
		val=shareRecord.get(Quotes.QUOTES.MOBILEAVERAGE200DAYS);
		if (val != null)
		{
			quote.setMobileAverage200Days(val);
		}
		val=shareRecord.get(Quotes.QUOTES.MOBILEAVERAGE50DAYS);
		if (val != null)
		{
			quote.setMobileAverage50Days(val);
		}
		
		val=shareRecord.get(Quotes.QUOTES.OPENPRICE);
		if (val != null)
		{
			quote.setOpenPrice(val);
		}
		val=shareRecord.get(Quotes.QUOTES.PERATIO);
		if (val != null)
		{
			quote.setPeRatio(val);
		}
		val=shareRecord.get(Quotes.QUOTES.PREVIOUSCLOSE);
		if (val != null)
		{
			quote.setPreviousClose(val);
		}
		val=shareRecord.get(Quotes.QUOTES.SHORTRATIO);
		if (val != null)
		{
			quote.setShortRatio(val);
		}
		val=shareRecord.get(Quotes.QUOTES.VOLUME);
		if (val != null)
		{
			quote.setVolume(val);
		}
		quoteDb.setQuotation(quote);
	}
	@Override
	public boolean updateQuotationInDatabase(List<QuoteDB> quoteList)
	{
		List<UpdateConditionStep<Record>> updates = new ArrayList<>();
		DSLContext dslContext = DSL.using(conn, SQLDialect.HSQLDB);

		for (QuoteDB quoteDB : quoteList)
		{
			UpdateConditionStep<Record> updateCondition=updateQuotationInDatabase(dslContext,quoteDB);
			if (updateCondition!= null)
			{
				updates.add(updateCondition);
			}
		}

		int[] result = dslContext.batch(updates).execute();
		//TODO check result
		return true;
	}
	
	private UpdateConditionStep<Record> updateQuotationInDatabase(DSLContext dslContext, QuoteDB quoteDB)
	{
		UpdateSetFirstStep<Record> updateRequest = dslContext.update(Quotes.QUOTES);
		
		Quote quote=quoteDB.getQuotation();
		UpdateSetMoreStep<Record> req=null;
		
		if (quote.getLastTradedPrice().isValid())
		{
			req = updateRequest.set(Quotes.QUOTES.LASTTRADEDPRICE, quote.getLastTradedPrice().getValue());
		}
		if (req == null)
		{
			logger.error("no update : last traded price missing");
			return null;
		}
		if (quote.getChangeInPrice().isValid())
		{
			updateRequest.set(Quotes.QUOTES.CHANGEINPRICE, quote.getChangeInPrice().getValue());
		}
		if (quote.getHigh52Week().isValid())
		{
			updateRequest.set(Quotes.QUOTES.HIGH52WEEK, quote.getHigh52Week().getValue());
		}
		if (quote.getHighPrice().isValid())
		{
			updateRequest.set(Quotes.QUOTES.HIGHPRICE, quote.getHighPrice().getValue());
		}
		if (quote.getLow52Week().isValid())
		{
			updateRequest.set(Quotes.QUOTES.LOW52WEEK, quote.getLow52Week().getValue());
		}
		if (quote.getLowPrice().isValid())
		{
			updateRequest.set(Quotes.QUOTES.LOWPRICE, quote.getLowPrice().getValue());
		}

		if (quote.getMobileAverage200Days().isValid())
		{
			updateRequest.set(Quotes.QUOTES.MOBILEAVERAGE200DAYS, quote.getMobileAverage200Days().getValue());
		}		
		if (quote.getMobileAverage50Days().isValid())
		{
			updateRequest.set(Quotes.QUOTES.MOBILEAVERAGE50DAYS, quote.getMobileAverage50Days().getValue());
		}		
		if (quote.getOpenPrice().isValid())
		{
			updateRequest.set(Quotes.QUOTES.OPENPRICE, quote.getOpenPrice().getValue());
		}
		if (quote.getPeRatio().isValid())
		{
			updateRequest.set(Quotes.QUOTES.PERATIO, quote.getPeRatio().getValue());
		}
		if (quote.getPreviousClose().isValid())
		{
			updateRequest.set(Quotes.QUOTES.PREVIOUSCLOSE, quote.getPreviousClose().getValue());
		}
		if (quote.getShortRatio().isValid())
		{
			updateRequest.set(Quotes.QUOTES.SHORTRATIO, quote.getShortRatio().getValue());
		}
		if (quote.getVolume().isValid())
		{
			updateRequest.set(Quotes.QUOTES.VOLUME, quote.getVolume().getValue());
		}

		return req.where(Quotes.QUOTES.IDQUOTES.eq(quoteDB.getIdQuote()));
		
//	      .set(Quotes.QUOTES.HIGH52WEEK, 12.0)
	//      .set(AUTHOR.LAST_NAME, "Hesse")
	  //    .where(Quotes.QUOTES.IDQUOTES.eq(3))
	     // .execute();
	}


	@Override
	public List<PortfolioDB> loadSharesInPortfolio(ConditionPortfolioI condition)
	{
		DSLContext create = DSL.using(conn, SQLDialect.HSQLDB);
		SelectQuery<Record> query = create.selectQuery();
		query.addFrom(Portfolio.PORTFOLIO);
		query.addConditions(condition.getCondition());

		Result<?> result = query.fetch();
		List<PortfolioDB> listSharesInPorfolios=new ArrayList<>();
		for (Record portfolioRecord : result)
		{
			listSharesInPorfolios.add(fillPortfolioFromRecord(portfolioRecord));
		}
		return listSharesInPorfolios;
	}

	@Override
	public boolean updateListPortfolio(List<PortfolioDB> listToSave)
	{
		boolean result=true;
		for (PortfolioDB portfolioDB : listToSave)
		{
			result= (result && updatePortfolio(portfolioDB));
		}
		return result;
	}
}

