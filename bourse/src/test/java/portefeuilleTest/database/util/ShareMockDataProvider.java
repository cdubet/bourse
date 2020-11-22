package portefeuilleTest.database.util;

import static org.junit.Assert.fail;

import java.sql.SQLException;

import org.jooq.tools.jdbc.MockDataProvider;
import org.jooq.tools.jdbc.MockExecuteContext;
import org.jooq.tools.jdbc.MockResult;


import net.tuxanna.database.jooq.public_.tables.Shares;

import org.jooq.DSLContext;
import org.jooq.Record5;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;


public class ShareMockDataProvider implements MockDataProvider
{
	public static final String NAME_SHARE1 = "share1";
	public static final String NAME_SHARE2 = "share2";
	public static final String NAME_SHARE3 = "share3";

	
	public static final Integer ID_SHARE_1 = 1;
	public static final Integer ID_SHARE_2 = 2;
	public static final Integer ID_SHARE_3 = 3;
	public enum predefinedBehaviorE
	{
		RETURN_1_SHARE,
		RETURN_3_SHARES
	}
	private predefinedBehaviorE predefinedBehavior;
	
	public ShareMockDataProvider(predefinedBehaviorE predefinedBehaviorRequested)
	{
		predefinedBehavior=predefinedBehaviorRequested;
	}
	
	@Override
	public MockResult[] execute(MockExecuteContext ctx) throws SQLException
	{
		// The execute context contains SQL string(s), bind values, and other meta-data
		String sql = ctx.sql().toLowerCase();

		if (sql.contains("select"))
		{
			if (sql.contains("from \"public\".\"shares\"")) 
			{			
				switch(predefinedBehavior)
				{
				case RETURN_1_SHARE:
					return fillWithOneShare();
				case RETURN_3_SHARES:
					return fillWithThreeShares();
				}
				
			}
		}
		fail("not expected case");			
		
		return null;
	}
	private MockResult[] fillWithOneShare()
	{
		DSLContext create = DSL.using( SQLDialect.HSQLDB);
		Result<Record5<Integer, String, String, String, String>> result = create.newResult(
				Shares.SHARES.IDSHARE,
				Shares.SHARES.NAME,
				Shares.SHARES.CURRENCY,
				Shares.SHARES.IS_SHARE,				
				Shares.SHARES.TICKER);
		result.add(create
				.newRecord(Shares.SHARES.IDSHARE,
						Shares.SHARES.NAME,
						Shares.SHARES.CURRENCY,
						Shares.SHARES.IS_SHARE,
						Shares.SHARES.TICKER)
				.values(ID_SHARE_1,
						NAME_SHARE1,
						"$",
						"Y",
						"not used here"));

		return new MockResult[] {
				new MockResult(1, result)
		};
	}

	private MockResult[] fillWithThreeShares()
	{
		DSLContext create = DSL.using( SQLDialect.HSQLDB);
		Result<Record5<Integer, String, String, String, String>> result = create.newResult(
				Shares.SHARES.IDSHARE,
				Shares.SHARES.NAME,
				Shares.SHARES.CURRENCY,
				Shares.SHARES.IS_SHARE,				
				Shares.SHARES.TICKER);
		result.add(create
				.newRecord(Shares.SHARES.IDSHARE,
						Shares.SHARES.NAME,
						Shares.SHARES.CURRENCY,
						Shares.SHARES.IS_SHARE,
						Shares.SHARES.TICKER)
				.values(ID_SHARE_1,
						NAME_SHARE1,
						"$",
						"Y",
						"not used here"));

		result.add(create
				.newRecord(Shares.SHARES.IDSHARE,
						Shares.SHARES.NAME,
						Shares.SHARES.CURRENCY,
						Shares.SHARES.IS_SHARE,
						Shares.SHARES.TICKER)
				.values(ID_SHARE_2,
						NAME_SHARE2,
						"$",
						"Y",
						"not used here"));
		
		result.add(create
				.newRecord(Shares.SHARES.IDSHARE,
						Shares.SHARES.NAME,
						Shares.SHARES.CURRENCY,
						Shares.SHARES.IS_SHARE,
						Shares.SHARES.TICKER)
				.values(ID_SHARE_3,
						NAME_SHARE3,
						"$",
						"Y",
						"not used here"));

		return new MockResult[] {
				new MockResult(1, result)
		};
	}
}
