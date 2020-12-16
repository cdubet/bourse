package portefeuilleTest.businessLogic.shareOrders;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Record5;
import org.jooq.Record6;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.tools.jdbc.MockResult;

import net.tuxanna.database.jooq.public_.tables.Portfolio;
import net.tuxanna.database.jooq.public_.tables.Quotes;
import net.tuxanna.database.jooq.public_.tables.Shares;
import portefeuilleTest.businessLogic.FakeDate;



public class ShareOrderMockDataStorage
{

	public static final int ID_PORTOFOLIO_1 = 100;
	public static final int ID_ACCOUNT_1 = 0;
	public static final int ID_SHARE_1 = 10;
	public static final int ID_BUY_SHARE_1=60;
	public static final int ID_SELL_1=501;

	public static final Double CURRENT_VALUE_SHARE_1 = 10.1;
	public static final Double OLD_VALUE_SHARE_1 = 11.1;
	public static final double UNIT_PRICE_SHARE_1_ACCOUNT_1 = 200.154;
	public static final double NUMBER_SHARE_1_IN_ACCOUNT_1 = 49.;

	public static final int ID_BUY_SHARE_2=61;
	public static final int ID_PORTOFOLIO_2 = 100;
	public static final int ID_SHARE_2 = 15;
	public static final int ID_SELL_2=50;

	public static final Double CURRENT_VALUE_SHARE_2 = 15.1;
	public static final Double OLD_VALUE_SHARE_2 = 14.1;
	public static final double UNIT_PRICE_SHARE_2_ACCOUNT_1 = 2.14;
	public static final double NUMBER_SHARE_2_IN_ACCOUNT_1 = 50.;
	public static final double BOUGHT_QTE_SHARE_2 = 55.0;
	public static final double SELL_PRICE_SHARE_2=CURRENT_VALUE_SHARE_2-1.0; // current price > requested for selling -> sold

	public static final int ID_SHARE_3 = 16;
	public static final Double CURRENT_VALUE_SHARE_3 = 35.1;
	public static final Double OLD_VALUE_SHARE_3 = 34.1;
	public static final Double BOUGHT_QTE_SHARE_3 = 199.0;
	public static final Integer ID_BUY_SHARE_3 = ID_BUY_SHARE_2+1;

	//store data sent by update or insert sql request
	private Map<Integer,Double> mapPortfolioQte = new HashMap<Integer,Double>();
	private Map<Integer,Double> mapPortfolioPrice = new HashMap<Integer,Double>();

	private Map<Integer,Integer> mapBuyState = new HashMap<Integer,Integer>();
	private Map<Integer,Double> mapBuyQte = new HashMap<Integer,Double>();
	private Map<Integer,Double> mapBuyPriceExecuted = new HashMap<Integer,Double>();

	public ShareOrderMockDataStorage()
	{
		super();
	}

	protected void storeNewPortfolioValues(Integer portfolioID, Double newUnitPrice, Double newQte)
	{
		mapPortfolioQte.put(portfolioID, newQte);
		mapPortfolioPrice.put(portfolioID, newUnitPrice);		
	}

	public boolean hasNewPortfolioThisUnitPrice(Integer portfolioID, Double newUnitPrice)
	{
		Double newVal =mapPortfolioPrice.get(portfolioID);
		if (newVal == null)
		{
			return false;
		}
		if (newVal.equals(newUnitPrice))
		{
			return true;
		}
		return false;
	}

	public boolean hasNewPortfolioThisQte(Integer portfolioID, Double newQte)
	{
		Double newVal =mapPortfolioQte.get(portfolioID);
		if (newVal == null)
		{
			return false;
		}
		if (newVal.equals(newQte))
		{
			return true;
		}
		return false;
	}

	protected void storeNewBuyValues(Integer buyId, Integer state, Double qte, Double valSold)
	{
		mapBuyState.put(buyId, state);
		mapBuyQte.put(buyId, qte);
		mapBuyPriceExecuted.put(buyId, valSold);	
	}

	public boolean hasBuySellOrderThisState(Integer buyId, Integer newState)
	{
		Integer newVal =mapBuyState.get(buyId);
		if (newVal == null)
		{
			return false;
		}
		if (newVal.equals(newState))
		{
			return true;
		}
		return false;
	}
	public boolean hasBuySellThisQte(Integer buyId, Double newQte)
	{
		Double newVal =mapBuyQte.get(buyId);
		if (newVal == null)
		{
			return false;
		}
		if (newVal.equals(newQte))
		{
			return true;
		}
		return false;
	}
	public boolean hasBuySellThisExecutedValue(Integer buyId, Double newValue)
	{
		Double newVal =mapBuyPriceExecuted.get(buyId);
		if (newVal == null)
		{
			return false;
		}
		if (newVal.equals(newValue))
		{
			return true;
		}
		return false;
	}

	protected MockResult[] fillHighPriceWithOneQuote(Integer Id)
	{
		DSLContext create = DSL.using( SQLDialect.HSQLDB);

		Result<Record1<Double>> result = create.newResult(Quotes.QUOTES.HIGHPRICE);
		Record1<Double> item1=create.newRecord(Quotes.QUOTES.HIGHPRICE);
		switch (Id.intValue())
		{
		case ID_SHARE_1:
			item1.setValue(Quotes.QUOTES.HIGHPRICE, CURRENT_VALUE_SHARE_1);
			break;
		case ID_SHARE_2:
			item1.setValue(Quotes.QUOTES.HIGHPRICE, CURRENT_VALUE_SHARE_2);
			break;
		case ID_SHARE_3:
			item1.setValue(Quotes.QUOTES.HIGHPRICE, CURRENT_VALUE_SHARE_3);
			break;			
		default:
			item1.setValue(Quotes.QUOTES.HIGHPRICE, 0.0);

		}

		result.add(item1);

		return new MockResult[] {
				new MockResult(1, result)
		};
	}

	protected MockResult[] fillWithQuotes()
	{
		FakeDate fakeDate=new FakeDate();
		DSLContext create = DSL.using( SQLDialect.HSQLDB);
		Result<Record4<Integer,Integer,LocalDateTime,Double>> result = create.newResult(
				Quotes.QUOTES.IDSHARE, 
				Quotes.QUOTES.IDQUOTES,
				Quotes.QUOTES.DATEQUOTE,
				Quotes.QUOTES.LASTTRADEDPRICE);
		int idx=0;
		LocalDateTime dateOfYesterday=fakeDate.getYesterday().toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDateTime();
		LocalDateTime  dateOfNow=fakeDate.getNow().toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDateTime();

		result.add(create
				.newRecord(Quotes.QUOTES.IDSHARE, 
						Quotes.QUOTES.IDQUOTES,
						Quotes.QUOTES.DATEQUOTE,
						Quotes.QUOTES.LASTTRADEDPRICE)
				.values(ID_SHARE_1,
						idx++,
						dateOfYesterday,
						OLD_VALUE_SHARE_1));

		result.add(create
				.newRecord(Quotes.QUOTES.IDSHARE, 
						Quotes.QUOTES.IDQUOTES,
						Quotes.QUOTES.DATEQUOTE,
						Quotes.QUOTES.LASTTRADEDPRICE)
				.values(ID_SHARE_2,
						idx++,
						dateOfYesterday,
						OLD_VALUE_SHARE_2));	

		result.add(create
				.newRecord(Quotes.QUOTES.IDSHARE, 
						Quotes.QUOTES.IDQUOTES,
						Quotes.QUOTES.DATEQUOTE,
						Quotes.QUOTES.LASTTRADEDPRICE)
				.values(ID_SHARE_3,
						idx++,
						dateOfYesterday,
						OLD_VALUE_SHARE_3));
		result.add(create
				.newRecord(Quotes.QUOTES.IDSHARE, 
						Quotes.QUOTES.IDQUOTES,
						Quotes.QUOTES.DATEQUOTE,
						Quotes.QUOTES.LASTTRADEDPRICE)
				.values(ID_SHARE_1,
						idx++,
						dateOfNow,
						CURRENT_VALUE_SHARE_1));
		result.add(create
				.newRecord(Quotes.QUOTES.IDSHARE, 
						Quotes.QUOTES.IDQUOTES,
						Quotes.QUOTES.DATEQUOTE,
						Quotes.QUOTES.LASTTRADEDPRICE)
				.values(ID_SHARE_2,
						idx++,
						dateOfNow,
						CURRENT_VALUE_SHARE_2));
		result.add(create
				.newRecord(Quotes.QUOTES.IDSHARE, 
						Quotes.QUOTES.IDQUOTES,
						Quotes.QUOTES.DATEQUOTE,
						Quotes.QUOTES.LASTTRADEDPRICE)
				.values(ID_SHARE_3,
						idx++,
						dateOfNow,
						CURRENT_VALUE_SHARE_3));

		return new MockResult[] {
				new MockResult(1, result)
		};
	}

	protected MockResult[] fillWithPortfolio(Integer shareID)
	{
		DSLContext create = DSL.using( SQLDialect.HSQLDB);
		Result<Record6<Integer, Integer, Integer, Double, Double, Integer>> result = create.newResult(
				Portfolio.PORTFOLIO.IDPORTFOLIO,
				Portfolio.PORTFOLIO.IDACCOUNT, 
				Portfolio.PORTFOLIO.IDSHARE,
				Portfolio.PORTFOLIO.QTE,
				Portfolio.PORTFOLIO.UNITPRICE,
				Portfolio.PORTFOLIO.USEFORSUMMARY);

		final Integer USE_FOR_SUMMARY=1;
		switch(shareID.intValue())
		{	
		case ID_SHARE_1:
		{
			result.add(create
					.newRecord(Portfolio.PORTFOLIO.IDPORTFOLIO,
							Portfolio.PORTFOLIO.IDACCOUNT, 
							Portfolio.PORTFOLIO.IDSHARE,
							Portfolio.PORTFOLIO.QTE,
							Portfolio.PORTFOLIO.UNITPRICE,
							Portfolio.PORTFOLIO.USEFORSUMMARY)
					.values(ID_PORTOFOLIO_1,
							ID_ACCOUNT_1,
							ID_SHARE_1,
							NUMBER_SHARE_1_IN_ACCOUNT_1,
							UNIT_PRICE_SHARE_1_ACCOUNT_1,
							USE_FOR_SUMMARY));
		}
		break;
		case ID_SHARE_2:
		{
			result.add(create
					.newRecord(Portfolio.PORTFOLIO.IDPORTFOLIO,
							Portfolio.PORTFOLIO.IDACCOUNT, 
							Portfolio.PORTFOLIO.IDSHARE,
							Portfolio.PORTFOLIO.QTE,
							Portfolio.PORTFOLIO.UNITPRICE,
							Portfolio.PORTFOLIO.USEFORSUMMARY)
					.values(ID_PORTOFOLIO_2,
							ID_ACCOUNT_1,
							ID_SHARE_2,
							NUMBER_SHARE_2_IN_ACCOUNT_1,
							UNIT_PRICE_SHARE_2_ACCOUNT_1,
							USE_FOR_SUMMARY));
		}
		break;

		case ID_SHARE_3:
			//not existing in portfolio (share not bought yet
			return new MockResult[] {
					new MockResult(0, result)};

		default:
			int idx=0;
			{
				result.add(create
						.newRecord(Portfolio.PORTFOLIO.IDPORTFOLIO,
								Portfolio.PORTFOLIO.IDACCOUNT, 
								Portfolio.PORTFOLIO.IDSHARE,
								Portfolio.PORTFOLIO.QTE,
								Portfolio.PORTFOLIO.UNITPRICE,
								Portfolio.PORTFOLIO.USEFORSUMMARY)
						.values(idx++,
								ID_ACCOUNT_1,
								ID_SHARE_1,
								NUMBER_SHARE_1_IN_ACCOUNT_1,
								UNIT_PRICE_SHARE_1_ACCOUNT_1,
								USE_FOR_SUMMARY));
			}
		}
		return new MockResult[] {
				new MockResult(1, result)
		};
	}

	protected MockResult[] fillWithShares()
	{
		DSLContext create = DSL.using( SQLDialect.HSQLDB);
		Result<Record5<Integer, String, String, String, String>> result = create.newResult(
				Shares.SHARES.IDSHARE,
				Shares.SHARES.CURRENCY,
				Shares.SHARES.IS_SHARE,
				Shares.SHARES.NAME,
				Shares.SHARES.TICKER);

		result.add(create
				.newRecord(Shares.SHARES.IDSHARE,
						Shares.SHARES.CURRENCY,
						Shares.SHARES.IS_SHARE,
						Shares.SHARES.NAME,
						Shares.SHARES.TICKER)
				.values(ID_SHARE_1,
						"$",
						"Y",
						"share1",
						"not used here"));

		result.add(create
				.newRecord(Shares.SHARES.IDSHARE,
						Shares.SHARES.CURRENCY,
						Shares.SHARES.IS_SHARE,
						Shares.SHARES.NAME,
						Shares.SHARES.TICKER)
				.values(ID_SHARE_2,
						"$",
						"Y",
						"share2",
						"not used here"));
		
		result.add(create
				.newRecord(Shares.SHARES.IDSHARE,
						Shares.SHARES.CURRENCY,
						Shares.SHARES.IS_SHARE,
						Shares.SHARES.NAME,
						Shares.SHARES.TICKER)
				.values(ID_SHARE_3,
						"$",
						"Y",
						"share3",
						"not used here"));

		return new MockResult[] {
				new MockResult(1, result)
		};
	}
}