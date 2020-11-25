package portefeuilleTest.database;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;



import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;

import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.tuxanna.portefeuille.database.ConditionQuoteDateBefore;
import net.tuxanna.portefeuille.database.ConditionQuoteI;
import net.tuxanna.portefeuille.database.Database;
import net.tuxanna.portefeuille.database.PortfolioDB;
import net.tuxanna.portefeuille.database.QuoteDB;
import net.tuxanna.portefeuille.database.SearchQuoteAtOneDay;
import net.tuxanna.portefeuille.database.SellDB;
import net.tuxanna.portefeuille.database.ShareDB;
import net.tuxanna.portefeuille.database.ShareOrderI;
import net.tuxanna.portefeuille.util.DigitValue;
import portefeuilleTest.database.util.RamDatabaseForTest;

public class TestDatabaseWithDbUnit
{

	private static RamDatabaseForTest testDb;
	private IDatabaseTester databaseTester;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		testDb = new RamDatabaseForTest();
		assertTrue(testDb.createSchema());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
		testDb.shutdown();
	}

	private IDataSet readDataSet() throws Exception
	{
		FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
		//TODO why is it not working ?
		//builder.setMetaDataSetFromDtd(new FileInputStream("/home/christian/workspace/portefeuilleTest/TEST_DATA/quotes.dtd"));
		return builder.build(new File("TEST_DATA/stock_and_quotes.xml"));
	}

	@SuppressWarnings("rawtypes")
	private void cleanlyInsert(IDataSet dataSet) throws Exception
	{
		Class driverClass = Class.forName("org.hsqldb.jdbcDriver");
		final String JDBC_DRIVER = driverClass.getName();

		databaseTester = new JdbcDatabaseTester(JDBC_DRIVER, testDb.getDatabaseName(), Database.getUsername(),
				Database.getPassword());
		// tell which DB we have
		DatabaseConfig config = databaseTester.getConnection().getConfig();
		config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new HsqldbDataTypeFactory());

		databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		databaseTester.setDataSet(dataSet);
		databaseTester.onSetup();
	}

	public void importDataSet() throws Exception
	{
		IDataSet dataSet = readDataSet();
		cleanlyInsert(dataSet);
	}

	@Before
	public void setUp() throws Exception
	{
		importDataSet();
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testLoadSharesInPortfolios()
	{
		ArrayList<PortfolioDB> listShares = new ArrayList<PortfolioDB>();

		boolean res = testDb.loadSharesInPortfolio(listShares);

		assertTrue(res);
		// expected contents
		// <!-- same share in 2 portfolio -->
		// <PORTFOLIO idPortfolio="0" IDSHARE="4" idAccount="0" qte="10.0"
		// unitPrice="50.2" useForSummary="1"/>
		// <PORTFOLIO idPortfolio="1" IDSHARE="4" idAccount="1" qte="30.0"
		// unitPrice="15.2" useForSummary="1"/>
		//
		// <PORTFOLIO idPortfolio="2" IDSHARE="0" idAccount="0" qte="100.0"
		// unitPrice="5.2" useForSummary="1"/>
		// <PORTFOLIO idPortfolio="3" IDSHARE="1" idAccount="0" qte="50.0"
		// unitPrice="55.2" useForSummary="0"/>
		// <PORTFOLIO idPortfolio="4" IDSHARE="2" idAccount="0" qte="0.0"
		// unitPrice="15.12" useForSummary="1"/>
		//
		assertEquals(4, listShares.size());
		assertEquals(0, listShares.get(0).getIdAccount());
		assertEquals(2, listShares.get(0).getIdPortfolio());
		assertEquals(0, listShares.get(0).getIdShare());

		assertEquals(true, listShares.get(0).getQte().isValid());
		assertEquals(100., listShares.get(0).getQte().getValue(), 0.001);

		assertEquals(true, listShares.get(0).getUnitPrice().isValid());
		assertEquals(5.2, listShares.get(0).getUnitPrice().getValue(), 0.001);

		assertEquals(true, listShares.get(0).isUseForSummary());

		//////////////////////////////////////////////////
		// TODO check all the other items

	}

	@Test
	public void testLoadSharesIdInPortfolioKeepNonMeaningful()
	{
		ArrayList<Integer> listSharesId = new ArrayList<Integer>();
		final boolean keepNonMeaningfullShares = true;
		boolean res = testDb.loadSharesIdInPortfolio(listSharesId, keepNonMeaningfullShares);

		assertTrue(res);
		assertEquals(3, listSharesId.size());
		// id should be 4 0 1
		Collections.sort(listSharesId);
		assertEquals(Integer.valueOf(0), listSharesId.get(0));
		assertEquals(Integer.valueOf(1), listSharesId.get(1));
		assertEquals(Integer.valueOf(4), listSharesId.get(2));
	}

	@Test
	public void testLoadSharesIdInPortfolioDropNonMeaningful()
	{
		ArrayList<Integer> listSharesId = new ArrayList<Integer>();
		final boolean keepNonMeaningfullShares = false;
		boolean res = testDb.loadSharesIdInPortfolio(listSharesId, keepNonMeaningfullShares);

		assertTrue(res);
		assertEquals(2, listSharesId.size());
		// id should be 4 0
		Collections.sort(listSharesId);
		assertEquals(Integer.valueOf(0), listSharesId.get(0));
		assertEquals(Integer.valueOf(4), listSharesId.get(1));
	}

	@Test
	public void testReadQuotationAtOneDay()
	{

		Calendar cal = Calendar.getInstance(); // creates calendar
		cal.set(Calendar.YEAR, 2016);
		cal.set(Calendar.MONTH, 9 /* month start form 0!!! */);
		cal.set(Calendar.DAY_OF_MONTH, 15);

		cal.set(Calendar.HOUR_OF_DAY, 10);
		cal.set(Calendar.MINUTE, 30);
		cal.set(Calendar.SECOND, 20);
		cal.set(Calendar.MILLISECOND, 40);

		java.util.Date newDateToSearch = cal.getTime();

		{
			ShareDB share = new ShareDB(1 /* id */, 
					"not matter", 
					"not matter",
					ShareDB.Currency.EURO, //does not matter
					true //does not matter
					);
			SearchQuoteAtOneDay search=new SearchQuoteAtOneDay(share, newDateToSearch);
			QuoteDB quote = testDb.readQuotationAtOneDay(search);

			// check we have found the right one
			// should be
			// <QUOTES IDQUOTES="10" IDSHARE="1" DATEQUOTE="2016-10-15
			// 11:41:42.0"
			// LASTTRADEDPRICE="10.03" CHANGEINPRICE="1.05" OPENPRICE="1.08"
			// HIGHPRICE="1.02" LOWPRICE="1.05"
			// PREVIOUSCLOSE="1.011" PERATIO="1.09" SHORTRATIO="1.012" />
			assertEquals(50,quote.getIdQuote());
			assertEquals(10.03, quote.getQuotation().getLastTradedPrice().getValue(), 0.0001 /* delta */);
		}

		// try with an other who has 3 items
		{
			ShareDB share = new ShareDB(3 /* id */, 
					"not matter",
					"not matter",
					ShareDB.Currency.EURO, //does not matter
					true //does not matter
					);

			SearchQuoteAtOneDay search=new SearchQuoteAtOneDay(share, newDateToSearch);
			QuoteDB quote = testDb.readQuotationAtOneDay(search);

			// check we have found the right one
			// should be
			assertEquals(73,quote.getIdQuote());
			assertEquals(14.0, quote.getQuotation().getLastTradedPrice().getValue(), 0.0001 /* delta */);
		}

		// 1 with 1 item
		{
			ShareDB share = new ShareDB(0 /* id */,
					"not matter", 
					"not matter",
					ShareDB.Currency.EURO, //does not matter
					true //does not matter
					);

			SearchQuoteAtOneDay search=new SearchQuoteAtOneDay(share, newDateToSearch);
			QuoteDB quote = testDb.readQuotationAtOneDay(search);

			// check we have found the right one
			// should be
			assertEquals(40,quote.getIdQuote());
			assertEquals(1.03, quote.getQuotation().getLastTradedPrice().getValue(), 0.0001 /* delta */);
		}
	}

	@Test
	public void testReadSellAtOneDay()
	{

		Calendar cal = Calendar.getInstance(); // creates calendar
		cal.set(Calendar.YEAR, 2016);
		cal.set(Calendar.MONTH, 9 /* month start form 0!!! */);
		cal.set(Calendar.DAY_OF_MONTH, 15);

		cal.set(Calendar.HOUR_OF_DAY, 10);
		cal.set(Calendar.MINUTE, 30);
		cal.set(Calendar.SECOND, 20);
		cal.set(Calendar.MILLISECOND, 40);

		java.util.Date newDateToSearch = cal.getTime();

		{
			List<ShareOrderI> listOrders = new ArrayList<ShareOrderI>();

			boolean res = testDb.loadValidSellOrders(listOrders, newDateToSearch);

			assertEquals(true,res);
			assertEquals(1,listOrders.size());
			// check we have the right values (see xml file)

			assertEquals(4,listOrders.get(0).getId());
			assertEquals(3,listOrders.get(0).getIdShare());
			assertEquals(0,listOrders.get(0).getIdAccount());
			assertEquals(100,listOrders.get(0).getQte(),  0.1);
			assertEquals( 5.1,listOrders.get(0).getUnitPriceRequested(), 0.1);
		}
	}

	@Test
	public void testReadBuyAtOneDay()
	{

		Calendar cal = Calendar.getInstance(); // creates calendar
		cal.set(Calendar.YEAR, 2016);
		cal.set(Calendar.MONTH, 9 /* month start form 0!!! */);
		cal.set(Calendar.DAY_OF_MONTH, 15);

		cal.set(Calendar.HOUR_OF_DAY, 10);
		cal.set(Calendar.MINUTE, 30);
		cal.set(Calendar.SECOND, 20);
		cal.set(Calendar.MILLISECOND, 40);

		java.util.Date newDateToSearch = cal.getTime();

		{
			List<ShareOrderI> listOrders = new ArrayList<ShareOrderI>();

			boolean res = testDb.loadValidBuyOrders(listOrders, newDateToSearch);

			assertEquals(true,res);
			assertEquals(1,listOrders.size());
			// check we have the right values (see xml file)

			assertEquals(4,listOrders.get(0).getId());
			assertEquals(3,listOrders.get(0).getIdShare());
			assertEquals(0,listOrders.get(0).getIdAccount());
			assertEquals(99.5,listOrders.get(0).getQte(), 0.1);
			assertEquals(25.1,listOrders.get(0).getUnitPriceRequested(), 0.1);
			assertEquals("name4",listOrders.get(0).getShareName() );
		}
	}
	
	@Test
	public void testGetHighTradedPrice()
	{
		assertEquals(400.902,testDb.getHighTradedPrice(4).get(),0.001);
		assertEquals(1.02,testDb.getHighTradedPrice(2).get(),0.001);
		//not existing
		assertEquals(true,testDb.getHighTradedPrice(200).isEmpty());
	}
	
	@Test
	public void testGetLowTradedPrice()
	{
		assertEquals(395.905,testDb.getLowTradedPrice(4).get(),0.001);
		assertEquals(0.95,testDb.getLowTradedPrice(2).get(),0.001);
		//not existing
		assertEquals(true,testDb.getLowTradedPrice(200).isEmpty());
	}
	
	@Test
	public void testGetShareInPortfolio_NonExistingShareCase()
	{
		
		int ID_ACCOUNT=0;
		int ID_SHARE=9999;
		
		PortfolioDB noShare=testDb.getShareInPortfolio(ID_SHARE, ID_ACCOUNT);
			
		assertNull(noShare);
		
	}
	
	@Test
	public void testUpdatePortfolioExistingShare()
	{
		
		/* was
		<PORTFOLIO idPortfolio="3" IDSHARE="1" idAccount="0" qte="50.0"
				unitPrice="55.2" useForSummary="0" />
				*/
		int ID_ACCOUNT=0;
		int ID_SHARE=1;
		double NEW_QTE=125.0;
		double NEW_UNIT_PRICE=12345.68;
		
		PortfolioDB oldShare=testDb.getShareInPortfolio(ID_SHARE, ID_ACCOUNT);
			
		oldShare.setQte(new DigitValue(NEW_QTE));
		oldShare.setUnitPrice(new DigitValue(NEW_UNIT_PRICE));

		//tested fct
		boolean res=testDb.updatePortfolio(oldShare);
		assertTrue(res);
		
		//reload to see if qte and unit price are OK
		PortfolioDB changedShare=testDb.getShareInPortfolio(ID_SHARE, ID_ACCOUNT);
		assertEquals(oldShare,changedShare);
	}
	
	@Test
	public void testUpdatePortfolioNotExistingShare()
	{
		int ID_ACCOUNT=1;
		int ID_SHARE=2;
		double NEW_QTE=125.0;
		double NEW_UNIT_PRICE=12345.68;
		
		PortfolioDB newShare=new PortfolioDB();
			
		newShare.setQte(new DigitValue(NEW_QTE));
		newShare.setUnitPrice(new DigitValue(NEW_UNIT_PRICE));
		newShare.setIdAccount(ID_ACCOUNT);
		newShare.setIdShare(ID_SHARE);
		//tested fct
		boolean res=testDb.updatePortfolio(newShare);
		assertTrue(res);
		
		//check if in DB
		ITable actualTable;
		try
		{
			actualTable = databaseTester.getConnection().createDataSet().getTable("PORTFOLIO");
/* export the data to a partial.xml to see what is wrong
			QueryDataSet partialDataSet = new QueryDataSet(databaseTester.getConnection());
			partialDataSet.addTable("PORTFOLIO");
			FlatXmlDataSet.write(partialDataSet, new FileOutputStream("partial.xml"));
*/
			// default path workspace/protefeuilleTest/
			FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
			builder.setMetaDataSetFromDtd(new FileInputStream("TEST_DATA/quotes.dtd"));
			IDataSet expectedDataSet = builder.build(new FileInputStream("TEST_DATA/expected_result_add_share_in_portfolio.xml"));
			ITable expectedTable = expectedDataSet.getTable("PORTFOLIO");

			org.dbunit.assertion.DbUnitAssert assertion=new org.dbunit.assertion.DbUnitAssert() ;
			assertion.assertEquals(expectedTable, actualTable);
			// do not use junit assert but dbunit one as it is not the same type !!
			// assertEquals(expectedTable, actualTable);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("exception received");//fail
		}

	}
	
	@Test
	public void testUpdateSellOrdersInDatabase()
	{
		{

			SellDB sold = new SellDB();
			// set values like in database
			sold.setIdSell(4);
			sold.setIdShare(3);
			sold.setIdAccount(0);
			sold.setQte(75); //partial selling
			sold.setUnitPriceRequested(5.1);
			sold.setState(ShareOrderI.STATE_ORDER.EXECUTED);
			// now set is as sold
			sold.setAsExecuted(new DigitValue(45.5));
			

			boolean res = testDb.updateSellOrder(sold);

			assertEquals(true,res);
		}

		ITable actualTable;
		try
		{
			actualTable = databaseTester.getConnection().createDataSet().getTable("SELL");
/* export the data to a partial.xml to see what is wrong
			QueryDataSet partialDataSet = new QueryDataSet(databaseTester.getConnection());
			partialDataSet.addTable("SELL");
			FlatXmlDataSet.write(partialDataSet, new FileOutputStream("partial.xml"));
*/
			// default path workspace/protefeuilleTest/
			FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
			builder.setMetaDataSetFromDtd(new FileInputStream("TEST_DATA/quotes.dtd"));
			IDataSet expectedDataSet = builder.build(new FileInputStream("TEST_DATA/expected_result_sold.xml"));
			ITable expectedTable = expectedDataSet.getTable("SELL");

			org.dbunit.assertion.DbUnitAssert assertion=new org.dbunit.assertion.DbUnitAssert() ;
			assertion.assertEquals(expectedTable, actualTable);
			// do not use junit assert but dbunit one as it is not the same type !!
			// assertEquals(expectedTable, actualTable);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("exception received");
		}
	}
	
	@Test
	public void whenOnlyOneNameMatch_LoadShareFromName_returnsOneShare()
	{
		//<SHARES IDSHARE="0" NAME="name1" TICKER="ticker1" IS_SHARE="y" CURRENCY="E"/>

		String shareName="name1";
		ShareDB share=testDb.loadShare(shareName);
			
		assertNotNull(share);
		assertEquals(shareName,share.getName());
		assertEquals(0,share.getId());
	}
	@Test
	public void whenMoreThanOneNameMatch_LoadShareFromName_returnsNull()
	{
		String shareName="name";
		ShareDB share=testDb.loadShare(shareName);
			
		assertNull(share);
	}
	
	private Integer getShareIdToSearchFor()
	{
		return 3;
	}
	private ConditionQuoteI getSearchConditionToGetOneQuoteFromDatabase()
	{
		Calendar cal = Calendar.getInstance(); 
		cal.set(Calendar.YEAR, 2016);
		cal.set(Calendar.MONTH, 9); //month starts at 0
		cal.set(Calendar.DAY_OF_MONTH, 15);
		cal.set(Calendar.HOUR_OF_DAY, 01);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		
		Date testTime = cal.getTime();

		
		return new ConditionQuoteDateBefore(testTime,getShareIdToSearchFor());
	}
	
	@Test
	public void LoadQuotesBeforeDefinedDate()
	{

		List<QuoteDB>  listQuote=testDb.readQuotations(getSearchConditionToGetOneQuoteFromDatabase());

		assertNotNull(listQuote);
		assertEquals(1, listQuote.size());
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(listQuote.get(0).getDate());

		//expected 
		//	<QUOTES IDQUOTES="33" IDSHARE="3" DATEQUOTE="2016-10-14 09:41:42.0"
		//LASTTRADEDPRICE="560.0" CHANGEINPRICE="40.05" OPENPRICE="40.08"
		//HIGHPRICE="40.02" LOWPRICE="40.05" VOLUME="40.013" LOW52WEEK="40.04"
		//HIGH52WEEK="40.01" MOBILEAVERAGE50DAYS="40.07" MOBILEAVERAGE200DAYS="40.06"
		//PREVIOUSCLOSE="40.011" PERATIO="40.09" SHORTRATIO="40.012" />
		assertEquals(14, cal.get(Calendar.DAY_OF_MONTH));
		assertEquals(2016, cal.get(Calendar.YEAR));
		assertEquals(9, cal.get(Calendar.MONTH)); //month starts at 0

		assertEquals(40.013,listQuote.get(0).getQuotation().getVolume().getValue(),0.001);
		assertEquals(560.0,listQuote.get(0).getQuotation().getLastTradedPrice().getValue(),0.001);
		assertEquals(40.08,listQuote.get(0).getQuotation().getOpenPrice().getValue(),0.001);

		assertEquals(33,listQuote.get(0).getIdQuote());
		assertEquals(getShareIdToSearchFor().intValue(),listQuote.get(0).getIdShare());
	}
	
	@Test
	public void whenNotFoundId_is_given_then_LoadQuotesBeforeDefinedDate_returnsEmpty()
	{
		Calendar cal = Calendar.getInstance(); 
		cal.set(Calendar.YEAR, 2016);
		cal.set(Calendar.MONTH, 9); //month starts at 0
		cal.set(Calendar.DAY_OF_MONTH, 15);
		cal.set(Calendar.HOUR_OF_DAY, 01);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		
		Date testTime = cal.getTime();
		Integer shareIdToSearchFor=453;
		
		ConditionQuoteI searchCondition=new ConditionQuoteDateBefore(testTime,shareIdToSearchFor);
		List<QuoteDB>  listQuote=testDb.readQuotations(searchCondition);
			
		assertNotNull(listQuote);
		assertEquals(0, listQuote.size());
	}
	
	@Test
	public void whenNoId_isGiven_LoadQuotesBeforeDefinedDate_allItempsMatchingDate()
	{
		Calendar cal = Calendar.getInstance(); 
		cal.set(Calendar.YEAR, 2016);
		cal.set(Calendar.MONTH, 9); //month starts at 0
		cal.set(Calendar.DAY_OF_MONTH, 15);
		cal.set(Calendar.HOUR_OF_DAY, 01);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		
		Date testTime = cal.getTime();
		
		ConditionQuoteI searchCondition=new ConditionQuoteDateBefore(testTime);
		List<QuoteDB>  listQuote=testDb.readQuotations(searchCondition);
			
		assertNotNull(listQuote);
		assertEquals(4, listQuote.size());
		assertEquals(31,listQuote.get(0).getIdQuote());
		assertEquals(32,listQuote.get(1).getIdQuote());
		assertEquals(33,listQuote.get(2).getIdQuote());
		assertEquals(34,listQuote.get(3).getIdQuote());
	}
	
	@Test
	public void SaveExistingQuote()
	{
		List<QuoteDB>  listQuote=testDb.readQuotations(getSearchConditionToGetOneQuoteFromDatabase());
		assertNotNull(listQuote);
		assertEquals(1, listQuote.size());
		double lastTradedPriceExpected=9846.123;
		listQuote.get(0).getQuotation().setLastTradedPrice(lastTradedPriceExpected);
		
		//act
		boolean res=testDb.updateQuotationInDatabase(listQuote);
		
		assertTrue(res);
		List<QuoteDB>  newListQuote=testDb.readQuotations(getSearchConditionToGetOneQuoteFromDatabase());
		assertNotNull(newListQuote);
		assertEquals(1, newListQuote.size());
		assertEquals(lastTradedPriceExpected,newListQuote.get(0).getQuotation().getLastTradedPrice().getValue(),0.01);
		
	}
}
