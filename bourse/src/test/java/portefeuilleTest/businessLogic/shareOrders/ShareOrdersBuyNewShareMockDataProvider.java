package portefeuilleTest.businessLogic.shareOrders;

import java.sql.SQLException;
import java.time.LocalDateTime;

import org.jooq.DSLContext;
import org.jooq.Record8;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.tools.jdbc.MockDataProvider;
import org.jooq.tools.jdbc.MockExecuteContext;
import org.jooq.tools.jdbc.MockResult;

import net.tuxanna.database.jooq.public_.tables.Buy;
import net.tuxanna.database.jooq.public_.tables.Sell;

public class ShareOrdersBuyNewShareMockDataProvider extends ShareOrderMockDataStorage implements MockDataProvider
{
	private int nbInsertedShare;
	private Integer idAccount;
	private Integer idShare;
	private Double newUnitPrice;
	private Double newQte;
	private Integer useForSummary;

	public ShareOrdersBuyNewShareMockDataProvider()
	{
		nbInsertedShare=0;
		idAccount=null;
		idShare=null;
		newUnitPrice=null;
		newQte=null;
		useForSummary=null;
	}

	@Override
	public MockResult[] execute(MockExecuteContext ctx) throws SQLException
	{
		// The execute context contains SQL string(s), bind values, and other meta-data
		String sql = ctx.sql().toLowerCase();

		if (sql.contains("select"))
		{
			if (sql.contains("from \"public\".\"sell")) 
			{
				return fillWithSell();
			}
			if (sql.contains("from \"public\".\"buy\"")) 
			{			
				return fillWithBuy();
			}
			if (sql.contains("from \"public\".\"shares\"")) 
			{			
				return fillWithShares();
			}
			if (sql.contains("from \"public\".\"portfolio\"")) 
			{			
				Integer shareID = (Integer) ctx.bindings()[1];

				return fillWithPortfolio(shareID);
			}
			if (sql.contains("select \"public\".\"quotes\".\"lasttradedprice\" from \"public\".\"quotes\""))
			{
				Integer param = (Integer) ctx.bindings()[0];

				return fillWithOneQuote(param);
			}
			if (sql.contains("from \"public\".\"quotes\"")) 
			{			
				return fillWithQuotes();
			}
		}
		if (sql.contains("update \"public\".\"portfolio")) //update "public"."portfolio" set "public"."portfolio"."unitprice" = cast(? as double), "public"."portfolio"."qte" = cast(? as double) where "public"."portfolio"."idportfolio" = cast(? as int)
		{
			//extract the param of the sql request
			Double newUnitPrice = (Double)  ctx.bindings()[0];
			Double newQte = (Double)  ctx.bindings()[1];
			Integer portfolioID = (Integer) ctx.bindings()[2];

			storeNewPortfolioValues(portfolioID,newUnitPrice,newQte);
		}
		if (sql.contains("update \"public\".\"buy")) 
		{
			Double  valSold = (Double)  ctx.bindings()[0];
			Double qte=	(Double)  ctx.bindings()[1];
			Integer state=(Integer)  ctx.bindings()[2];
			Integer buyId= (Integer)  ctx.bindings()[3];


			//update "public"."buy" set "public"."buy"."unitpricebought" = cast(? as double), "public"."buy"."qte" = cast(? as double), "public"."buy"."state" = cast(? as int) where "public"."buy"."idbuy" = cast(? as int)
			storeNewBuyValues(buyId,state,qte,valSold);
		}
		else if (sql.contains("insert into"))
		{
			int idx=0;
			Integer idAccount=(Integer)  ctx.bindings()[idx++];
			Integer idShare=(Integer)  ctx.bindings()[idx++];
			Double newUnitPrice=(Double)  ctx.bindings()[idx++];
			Double newQte=(Double)  ctx.bindings()[idx++];
			Integer useForSummary=(Integer)  ctx.bindings()[idx++];

			insert(idAccount,idShare,newUnitPrice,newQte,useForSummary);
		}

		//default
		MockResult[] mock = new MockResult[0];
		return mock;
	}

	private void insert(Integer p_idAccount, Integer p_idShare, Double p_newUnitPrice, Double p_newQte, Integer p_useForSummary)
	{
		idAccount=p_idAccount;
		idShare=p_idShare;
		newUnitPrice=p_newUnitPrice;
		newQte=p_newQte;
		useForSummary=p_useForSummary;
		nbInsertedShare++;
	}

	private MockResult[] fillWithSell()
	{
		DSLContext create = DSL.using( SQLDialect.HSQLDB);
		Result<Record8<Integer,Integer,Integer,LocalDateTime,Double,Integer,Double,Double>> result = create.newResult(
				Sell.SELL.IDSELL,
				Sell.SELL.IDSHARE, 
				Sell.SELL.IDACCOUNT,
				Sell.SELL.DATEEXPIRATION,
				Sell.SELL.QTE,
				Sell.SELL.STATE,
				Sell.SELL.UNITPRICESOLD,
				Sell.SELL.UNITPRICEREQUESTED);

		final Integer idSell=1;
		final Double qte=100.;
		final Integer stateValid=0;
		final Double dummyPrice=0.1;

		result.add(create
				.newRecord(Sell.SELL.IDSELL,
						Sell.SELL.IDSHARE, 
						Sell.SELL.IDACCOUNT,
						Sell.SELL.DATEEXPIRATION,
						Sell.SELL.QTE,
						Sell.SELL.STATE,
						Sell.SELL.UNITPRICESOLD,
						Sell.SELL.UNITPRICEREQUESTED)
				.values(idSell,
						ID_SHARE_2,
						ID_ACCOUNT_1,
						LocalDateTime.now(),//not used
						qte,
						stateValid,
						dummyPrice,dummyPrice));

		return new MockResult[] {
				new MockResult(1, result)
		};
	}

	private MockResult[] fillWithBuy()
	{
		DSLContext create = DSL.using( SQLDialect.HSQLDB);
		Result<Record8<Integer,Integer,Integer,LocalDateTime,Double,Integer,Double,Double>> result = create.newResult(
				Buy.BUY.IDBUY,
				Buy.BUY.IDSHARE, 
				Buy.BUY.IDACCOUNT,
				Buy.BUY.DATEEXPIRATION,
				Buy.BUY.QTE,
				Buy.BUY.STATE,
				Buy.BUY.UNITPRICEBOUGHT,
				Buy.BUY.UNITPRICEREQUESTED);

		final Double qte=100.;
		final Integer stateValid=0;
		final Double dummyPrice=0.1;
		
		result.add(create
				.newRecord(Buy.BUY.IDBUY,
						Buy.BUY.IDSHARE, 
						Buy.BUY.IDACCOUNT,
						Buy.BUY.DATEEXPIRATION,
						Buy.BUY.QTE,
						Buy.BUY.STATE,
						Buy.BUY.UNITPRICEBOUGHT,
						Buy.BUY.UNITPRICEREQUESTED)
				.values(ID_BUY_SHARE_1,
						ID_SHARE_1,
						ID_ACCOUNT_1,
						LocalDateTime.now(),//not used
						qte,
						stateValid,
						dummyPrice,
						CURRENT_VALUE_SHARE_1-1.0));//below price limit -> not done
		result.add(create
				.newRecord(Buy.BUY.IDBUY,
						Buy.BUY.IDSHARE, 
						Buy.BUY.IDACCOUNT,
						Buy.BUY.DATEEXPIRATION,
						Buy.BUY.QTE,
						Buy.BUY.STATE,
						Buy.BUY.UNITPRICEBOUGHT,
						Buy.BUY.UNITPRICEREQUESTED)
				.values(ID_BUY_SHARE_3,
						ID_SHARE_3,
						ID_ACCOUNT_1,
						LocalDateTime.now(),//not used
						BOUGHT_QTE_SHARE_3,
						stateValid,
						dummyPrice,
						CURRENT_VALUE_SHARE_3)); // == current price -> should be performed

		return new MockResult[] {
				new MockResult(1, result)
		};
	}

	public int getNbInsertedShare()
	{
		return nbInsertedShare;
	}

	public Integer getIdAccount()
	{
		return idAccount;
	}

	public Integer getIdShare()
	{
		return idShare;
	}

	public Double getNewUnitPrice()
	{
		return newUnitPrice;
	}

	public Double getNewQte()
	{
		return newQte;
	}

	public Integer getUseForSummary()
	{
		return useForSummary;
	}

}
