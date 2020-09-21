package portefeuilleTest.businessLogic.shareOrders;

import java.sql.SQLException;

import org.jooq.DSLContext;
import org.jooq.Record5;
import org.jooq.Record6;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.tools.jdbc.MockDataProvider;
import org.jooq.tools.jdbc.MockExecuteContext;
import org.jooq.tools.jdbc.MockResult;

import net.tuxanna.database.jooq.public_.tables.Buy;
import net.tuxanna.database.jooq.public_.tables.Sell;


public class ShareOrdersBuyAlreadyBoughtMockDataProvider extends ShareOrderMockDataStorage implements MockDataProvider
{
	public ShareOrdersBuyAlreadyBoughtMockDataProvider()
	{

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


		//default
		MockResult[] mock = new MockResult[0];
		return mock;
	}

	private MockResult[] fillWithSell()
	{
		DSLContext create = DSL.using( SQLDialect.HSQLDB);

		Result<Record5<Integer, Integer, Integer,Double, Integer>> result = create.newResult(
				Sell.SELL.IDSELL, 
				Sell.SELL.IDSHARE,
				Sell.SELL.IDACCOUNT,
				Sell.SELL.QTE,
				Sell.SELL.STATE);
		result.add(create
				.newRecord(Sell.SELL.IDSELL, 
						Sell.SELL.IDSHARE,
						Sell.SELL.IDACCOUNT,
						Sell.SELL.QTE,
						Sell.SELL.STATE)
				.values(1, ID_SHARE_2,ID_ACCOUNT_1,100.,0 /* valid*/));

		return new MockResult[] {
				new MockResult(1, result)
		};
	}

	private MockResult[] fillWithBuy()
	{
		DSLContext create = DSL.using( SQLDialect.HSQLDB);
		Result<Record6<Integer, Integer, Integer, Double, Integer, Double>> result = create.newResult(
				Buy.BUY.IDBUY, 
				Buy.BUY.IDSHARE,
				Buy.BUY.IDACCOUNT,
				Buy.BUY.QTE,
				Buy.BUY.STATE,
				Buy.BUY.UNITPRICEREQUESTED);
		result.add(create
				.newRecord(Buy.BUY.IDBUY, 
						Buy.BUY.IDSHARE,
						Buy.BUY.IDACCOUNT,
						Buy.BUY.QTE,
						Buy.BUY.STATE,
						Buy.BUY.UNITPRICEREQUESTED)
				.values(ID_BUY_SHARE_1, ID_SHARE_1,ID_ACCOUNT_1,100.,0 /* valid*/,CURRENT_VALUE_SHARE_1-1.0));// below price limit -> not done
		result.add(create
				.newRecord(Buy.BUY.IDBUY, 
						Buy.BUY.IDSHARE,
						Buy.BUY.IDACCOUNT,
						Buy.BUY.QTE,
						Buy.BUY.STATE,
						Buy.BUY.UNITPRICEREQUESTED)
				.values(ID_BUY_SHARE_2, ID_SHARE_2,ID_ACCOUNT_1,BOUGHT_QTE_SHARE_2,0 /* valid*/,CURRENT_VALUE_SHARE_2)); // == current price -> should be performed

		return new MockResult[] {
				new MockResult(1, result)
		};
	}

}
