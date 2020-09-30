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


public class ShareOrdersSellBoughtMockDataProvider extends ShareOrderMockDataStorage implements MockDataProvider
{
	private double sellQte;
	public ShareOrdersSellBoughtMockDataProvider(double p_sellQte)
	{
		sellQte=p_sellQte;
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
		if (sql.contains("update \"public\".\"sell")) 
		{
			Double  valSold = (Double)  ctx.bindings()[0];
			Double qte=	(Double)  ctx.bindings()[1];
			Integer state=(Integer)  ctx.bindings()[2];
			Integer sellId= (Integer)  ctx.bindings()[3];

			storeNewBuyValues(sellId,state,qte,valSold);
		}


		//default
		MockResult[] mock = new MockResult[0];
		return mock;
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
				.values(ID_SELL_2,
						ID_SHARE_2,
						ID_ACCOUNT_1,
						LocalDateTime.now(),//not used
						sellQte,
						stateValid,
						dummyPrice,
						SELL_PRICE_SHARE_2)); // current price > requested for selling -> sold

		result.add(create
				.newRecord(Sell.SELL.IDSELL,
						Sell.SELL.IDSHARE, 
						Sell.SELL.IDACCOUNT,
						Sell.SELL.DATEEXPIRATION,
						Sell.SELL.QTE,
						Sell.SELL.STATE,
						Sell.SELL.UNITPRICESOLD,
						Sell.SELL.UNITPRICEREQUESTED)
				.values(ID_SELL_1,
						ID_SHARE_1,
						ID_ACCOUNT_1,
						LocalDateTime.now(),//not used
						sellQte,
						stateValid,
						dummyPrice,
						CURRENT_VALUE_SHARE_1+1.0)); //current price too low for selling
		
		return new MockResult[] {
				new MockResult(2, result)
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

		return new MockResult[] {
				new MockResult(0, result)
		};
	}

}
