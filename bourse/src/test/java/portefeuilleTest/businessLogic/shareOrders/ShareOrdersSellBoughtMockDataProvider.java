package portefeuilleTest.businessLogic.shareOrders;

import java.sql.SQLException;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.tools.jdbc.MockDataProvider;
import org.jooq.tools.jdbc.MockExecuteContext;
import org.jooq.tools.jdbc.MockResult;

import net.tuxanna.portefeuille.database.public_.tables.Buy;
import net.tuxanna.portefeuille.database.public_.tables.Sell;
import net.tuxanna.portefeuille.database.public_.tables.records.BuyRecord;
import net.tuxanna.portefeuille.database.public_.tables.records.SellRecord;



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

		Result<SellRecord> result = create.newResult(Sell.SELL);
		{
			SellRecord item1=new SellRecord();
			item1.setIdsell(ID_SELL_2);
			item1.setIdshare(ID_SHARE_2);
			item1.setQte(sellQte);
			item1.setState(0 /* valid*/);
			item1.setIdaccount(ID_ACCOUNT_1);
			item1.setUnitpricerequested(SELL_PRICE_SHARE_2); // current price > requested for selling -> sold
			result.add(item1);
		}
		{
			SellRecord item1=new SellRecord();
			item1.setIdsell(ID_SELL_1);
			item1.setIdshare(ID_SHARE_1);
			item1.setQte(sellQte);
			item1.setState(0 /* valid*/);
			item1.setIdaccount(ID_ACCOUNT_1);
			item1.setUnitpricerequested(CURRENT_VALUE_SHARE_1+1.0); //current price too low for selling
			result.add(item1);
		}
		return new MockResult[] {
				new MockResult(2, result)
		};
	}

	private MockResult[] fillWithBuy()
	{
		DSLContext create = DSL.using( SQLDialect.HSQLDB);

		Result<BuyRecord> result = create.newResult(Buy.BUY);

		return new MockResult[] {
				new MockResult(0, result)
		};
	}

}
