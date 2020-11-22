package portefeuilleTest.businessLogic.shareOrders;

import static org.junit.Assert.*;


import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.tools.jdbc.MockConnection;
import org.junit.Test;

import net.tuxanna.portefeuille.business_logic.share_orders.SellManagement;
import net.tuxanna.portefeuille.database.DatabaseI;
import portefeuilleTest.businessLogic.FakeDate;
import portefeuilleTest.database.util.MockDatabase;

public class TestSell
{
	private static final Logger logger = LogManager.getLogger(TestSell.class);
	@Test
	public void testPartialSell()
	{

		//test set up
		SellManagement sellManagement=new SellManagement();
		DatabaseI db;
		ShareOrdersSellBoughtMockDataProvider mockData;
		final double REMAINING_SHARES=10.;
		double shareToSell=ShareOrderMockDataStorage.NUMBER_SHARE_2_IN_ACCOUNT_1-REMAINING_SHARES;
		try
		{
			mockData=new ShareOrdersSellBoughtMockDataProvider(shareToSell);
			MockConnection connection = new MockConnection(mockData);
			db = new MockDatabase(connection);
			sellManagement.setDatabase(db);
		}
		catch (ClassNotFoundException | SQLException e)
		{
			logger.error(e);
			fail("exception");
			return;
		}
		FakeDate date = new FakeDate();
		
		//fct to test
		sellManagement.update(date.getNow());
		
		//check result
		Double newQte=REMAINING_SHARES;
		final Double wasPaid=ShareOrderMockDataStorage.NUMBER_SHARE_2_IN_ACCOUNT_1*ShareOrderMockDataStorage.UNIT_PRICE_SHARE_2_ACCOUNT_1;
		final Double moneyReceived=shareToSell*ShareOrderMockDataStorage.SELL_PRICE_SHARE_2;
		final Double newUnitPrice=(wasPaid-moneyReceived)/newQte;
		assertTrue(mockData.hasNewPortfolioThisUnitPrice(ShareOrderMockDataStorage.ID_PORTOFOLIO_2, newUnitPrice));
		
		assertTrue(mockData.hasNewPortfolioThisQte(ShareOrderMockDataStorage.ID_PORTOFOLIO_2, newQte));
		
		//for the sell table
		assertTrue(mockData.hasBuySellOrderThisState(ShareOrderMockDataStorage.ID_SELL_2, 1 /* executed*/));
		assertTrue(mockData.hasBuySellThisQte(ShareOrderMockDataStorage.ID_SELL_2, shareToSell));
		assertTrue(mockData.hasBuySellThisExecutedValue(ShareOrderMockDataStorage.ID_SELL_2, ShareOrderMockDataStorage.SELL_PRICE_SHARE_2));
	}

	@Test
	public void testFullySold()
	{

		//test set up
		SellManagement sellManagement=new SellManagement();
		DatabaseI db;
		ShareOrdersSellBoughtMockDataProvider mockData;
		final double REMAINING_SHARES=0.;
		double shareToSell=ShareOrderMockDataStorage.NUMBER_SHARE_2_IN_ACCOUNT_1-REMAINING_SHARES;
		try
		{
			mockData=new ShareOrdersSellBoughtMockDataProvider(shareToSell);
			MockConnection connection = new MockConnection(mockData);
			db = new MockDatabase(connection);
			sellManagement.setDatabase(db);
		}
		catch (ClassNotFoundException | SQLException e)
		{
			logger.error(e);
			fail("exception");
			return;
		}
		FakeDate date = new FakeDate();
		
		//fct to test
		sellManagement.update(date.getNow());
		
		//check result
		Double newQte=0.;
		
		assertTrue(mockData.hasNewPortfolioThisQte(ShareOrderMockDataStorage.ID_PORTOFOLIO_2, newQte));
		
		//for the sell table
		assertTrue(mockData.hasBuySellOrderThisState(ShareOrderMockDataStorage.ID_SELL_2, 1 /* executed*/));
		assertTrue(mockData.hasBuySellThisQte(ShareOrderMockDataStorage.ID_SELL_2, shareToSell));
		assertTrue(mockData.hasBuySellThisExecutedValue(ShareOrderMockDataStorage.ID_SELL_2, ShareOrderMockDataStorage.SELL_PRICE_SHARE_2));
	}
}
