package portefeuilleTest.businessLogic.shareOrders;

import static org.junit.Assert.*;


import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.tools.jdbc.MockConnection;
import org.junit.Test;

import net.tuxanna.portefeuille.businessLogic.share_orders.BuyManagement;
import net.tuxanna.portefeuille.database.DatabaseI;
import portefeuilleTest.businessLogic.FakeDate;

public class TestBuy
{
	private static final Logger logger = LogManager.getLogger(TestBuy.class);
	@Test
	public void testBuyAlreadyBoughtShare()
	{

		//test set up
		BuyManagement buyManagement=new BuyManagement();
		DatabaseI db;
		ShareOrdersBuyAlreadyBoughtMockDataProvider mockData;
		try
		{
			mockData=new ShareOrdersBuyAlreadyBoughtMockDataProvider();
			MockConnection connection = new MockConnection(mockData);
			db = new MockDatabase(connection);
			buyManagement.setDatabase(db);
		}
		catch (ClassNotFoundException | SQLException e)
		{
			logger.error(e);
			fail("exception");
			return;
		}
		FakeDate date = new FakeDate();
		
		//fct to test
		buyManagement.update(date.getNow());
		
		//check result
		Double newQte=(ShareOrderMockDataStorage.BOUGHT_QTE_SHARE_2+ShareOrderMockDataStorage.NUMBER_SHARE_2_IN_ACCOUNT_1);
		Double newUnitPrice=(ShareOrderMockDataStorage.NUMBER_SHARE_2_IN_ACCOUNT_1*ShareOrderMockDataStorage.UNIT_PRICE_SHARE_2_ACCOUNT_1+ShareOrderMockDataStorage.BOUGHT_QTE_SHARE_2*ShareOrderMockDataStorage.CURRENT_VALUE_SHARE_2)
							/newQte;
		assertTrue(mockData.hasNewPortfolioThisUnitPrice(ShareOrderMockDataStorage.ID_PORTOFOLIO_2, newUnitPrice));
		
		assertTrue(mockData.hasNewPortfolioThisQte(ShareOrderMockDataStorage.ID_PORTOFOLIO_2, newQte));
		
		//for the buy table
		assertTrue(mockData.hasBuySellOrderThisState(ShareOrderMockDataStorage.ID_BUY_SHARE_2, 1 /* executed*/));
		assertTrue(mockData.hasBuySellThisQte(ShareOrderMockDataStorage.ID_BUY_SHARE_2, ShareOrderMockDataStorage.BOUGHT_QTE_SHARE_2));
		assertTrue(mockData.hasBuySellThisExecutedValue(ShareOrderMockDataStorage.ID_BUY_SHARE_2, ShareOrderMockDataStorage.CURRENT_VALUE_SHARE_2));
	}

	@Test
	public void testBuyNewShare()
	{

		//test set up
		BuyManagement buyManagement=new BuyManagement();
		DatabaseI db;
		ShareOrdersBuyNewShareMockDataProvider mockData;
		try
		{
			mockData=new ShareOrdersBuyNewShareMockDataProvider();
			MockConnection connection = new MockConnection(mockData);
			db = new MockDatabase(connection);
			buyManagement.setDatabase(db);
		}
		catch (ClassNotFoundException | SQLException e)
		{
			logger.error(e);
			fail("exception");
			return;
		}
		FakeDate date = new FakeDate();
		
		//fct to test
		buyManagement.update(date.getNow());
		
		//check result
		Double newQte=ShareOrderMockDataStorage.BOUGHT_QTE_SHARE_3;
		Double newUnitPrice=ShareOrderMockDataStorage.CURRENT_VALUE_SHARE_3;
		assertEquals(1,mockData.getNbInsertedShare());
		assertEquals(Integer.valueOf(ShareOrderMockDataStorage.ID_ACCOUNT_1),mockData.getIdAccount());
		assertEquals(Integer.valueOf(ShareOrderMockDataStorage.ID_SHARE_3),mockData.getIdShare());
		assertEquals(newQte,mockData.getNewQte());
		assertEquals(newUnitPrice,mockData.getNewUnitPrice());
		
		//for the buy table
		assertTrue(mockData.hasBuySellOrderThisState(ShareOrderMockDataStorage.ID_BUY_SHARE_3, 1 /* executed*/));
		assertTrue(mockData.hasBuySellThisQte(ShareOrderMockDataStorage.ID_BUY_SHARE_3, ShareOrderMockDataStorage.BOUGHT_QTE_SHARE_3));
		assertTrue(mockData.hasBuySellThisExecutedValue(ShareOrderMockDataStorage.ID_BUY_SHARE_3, ShareOrderMockDataStorage.CURRENT_VALUE_SHARE_3));
	}

}
