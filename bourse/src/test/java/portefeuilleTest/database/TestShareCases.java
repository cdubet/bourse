package portefeuilleTest.database;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.tools.jdbc.MockConnection;
import org.junit.Test;

import net.tuxanna.portefeuille.database.ShareDB;
import net.tuxanna.portefeuille.database.ShareI;
import portefeuilleTest.database.util.MockDatabase;
import portefeuilleTest.database.util.ShareMockDataProvider;


public class TestShareCases
{
	private static final Logger logger = LogManager.getLogger(TestShareCases.class);
	@Test
	public void findMoreThanOneExistingShare()
	{
		//arrange
		ShareI db;
		ShareMockDataProvider mockData;
		
		try
		{
			mockData=new ShareMockDataProvider(	ShareMockDataProvider.predefinedBehaviorE.RETURN_3_SHARES);
			MockConnection connection = new MockConnection(mockData);
			db = new MockDatabase(connection);
		}
		catch (ClassNotFoundException | SQLException e)
		{
			logger.error(e);
			fail("exception");
			return;
		}
		
		//test
		String shareName=ShareMockDataProvider.NAME_SHARE1;
		ShareDB result = db.loadShare(shareName);
		
		assertNull(result);
	}
	@Test
	public void loadExistingShare()
	{
		//arrange
		ShareI db;
		ShareMockDataProvider mockData;
		
		try
		{
			mockData=new ShareMockDataProvider(	ShareMockDataProvider.predefinedBehaviorE.RETURN_1_SHARE);
			MockConnection connection = new MockConnection(mockData);
			db = new MockDatabase(connection);
		}
		catch (ClassNotFoundException | SQLException e)
		{
			logger.error(e);
			fail("exception");
			return;
		}
		
		//test
		String shareName=ShareMockDataProvider.NAME_SHARE1;
		ShareDB result = db.loadShare(shareName);
		
		assertNotNull(result);
		assertEquals(ShareMockDataProvider.ID_SHARE_1.intValue(), result.getId());
	}
}
