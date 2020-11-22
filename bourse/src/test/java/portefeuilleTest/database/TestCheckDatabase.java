package portefeuilleTest.database;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import portefeuilleTest.database.util.RamDatabaseForTestAtOneDay;


public class TestCheckDatabase
{
	private RamDatabaseForTestAtOneDay testDb;

	@After
	public void tearDown() throws Exception
	{
		testDb.shutdown();
	}
	
	@Test
	public void testCheckQuotesLastQuotationIntradayInvalid()
	{
		try
		{
			testDb=new RamDatabaseForTestAtOneDay("TEST_DATA/portfolio_withLastQuotationIntradayInvalid.xml");
			assertTrue(testDb.setup());
			
			
			//now make the test
			List<Integer> listPotentialInvalidId=testDb.checkQuotes();
		
			assertEquals(1, listPotentialInvalidId.size());
			assertEquals(32,listPotentialInvalidId.get(0).intValue()); //id quote 32 is wrong
		
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			fail();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			fail();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail();
		}
	}
	@Test
	public void testCheckQuotesLastQuotationNextDayInvalid()
	{
		try
		{
			testDb=new RamDatabaseForTestAtOneDay("TEST_DATA/portfolio_withLastQuotationNextDayInvalid.xml");
			assertTrue(testDb.setup());
			
			
			//now make the test
			List<Integer> listPotentialInvalidId=testDb.checkQuotes();
		
			assertEquals(2, listPotentialInvalidId.size());
			assertEquals(32,listPotentialInvalidId.get(0).intValue()); //id quote 32 is wrong
			assertEquals(42,listPotentialInvalidId.get(1).intValue()); //id quote 32 is wrong
		
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			fail();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			fail();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail();
		}
	}
}
