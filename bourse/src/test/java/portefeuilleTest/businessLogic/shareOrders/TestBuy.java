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
		final int idBuyShare=ShareOrderMockDataStorage.ID_BUY_SHARE_2;
		double boughtQteShare=ShareOrderMockDataStorage.BOUGHT_QTE_SHARE_2;
		double currentValueShare=0; //should be filled by test
		double priceRequested=ShareOrderMockDataStorage.CURRENT_VALUE_SHARE_2+1;//above the current price
		int idAccount=ShareOrderMockDataStorage.ID_ACCOUNT_1;
		int idShare=ShareOrderMockDataStorage.ID_SHARE_2;
		int idPortfolio=ShareOrderMockDataStorage.ID_PORTOFOLIO_2;

		String shareName="does not matter here";

		BuyManagement buyManagement=new BuyManagement();
		DatabaseI db;
		ShareOrdersBuySellSharesMockDataProvider mockData;
		try
		{
			mockData=new ShareOrdersBuySellSharesMockDataProvider();

			mockData.addFakeBuy(new FakeBuy(idBuyShare,boughtQteShare, currentValueShare,priceRequested,
					idAccount, idShare, shareName,idPortfolio));

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
		Double executedValue=priceRequested;//do not use  ShareOrderMockDataStorage.CURRENT_VALUE_SHARE_2 for share value. we assume big volume so we buy at the price we offer
		Double newQte=(boughtQteShare+ShareOrderMockDataStorage.NUMBER_SHARE_2_IN_ACCOUNT_1);
		Double newUnitPrice=(ShareOrderMockDataStorage.NUMBER_SHARE_2_IN_ACCOUNT_1*ShareOrderMockDataStorage.UNIT_PRICE_SHARE_2_ACCOUNT_1
							+boughtQteShare*executedValue) 
				/newQte;
		assertTrue(mockData.hasNewPortfolioThisUnitPrice(idPortfolio, newUnitPrice));

		assertTrue(mockData.hasNewPortfolioThisQte(idPortfolio, newQte));

		//for the buy table
		assertTrue(mockData.hasBuySellOrderThisState(idBuyShare, 1 /* executed*/));
		assertTrue(mockData.hasBuySellThisQte(idBuyShare, boughtQteShare));
		assertTrue(mockData.hasBuySellThisExecutedValue(idBuyShare,executedValue));
	}

	@Test
	public void testBuyNewShare()
	{

		//test set up
		BuyManagement buyManagement=new BuyManagement();
		DatabaseI db;
		ShareOrdersBuySellSharesMockDataProvider mockData;
		final Double newQte=ShareOrderMockDataStorage.BOUGHT_QTE_SHARE_3;
		final Double requestedPrice = ShareOrderMockDataStorage.CURRENT_VALUE_SHARE_3;
		final Double newUnitPrice=requestedPrice;
		final int idAccount = ShareOrderMockDataStorage.ID_ACCOUNT_1;
		final int idShare3 = ShareOrderMockDataStorage.ID_SHARE_3;
		final Integer idBuyShare = ShareOrderMockDataStorage.ID_BUY_SHARE_3;
		final int idPortfolio=ShareOrderMockDataStorage.ID_PORTOFOLIO_1;

		try
		{
			mockData=new ShareOrdersBuySellSharesMockDataProvider();
			mockData.addFakeBuy(new FakeBuy(
					idBuyShare,
					newQte,
					0,
					requestedPrice, 
					idAccount,
					idShare3,
					"new share bought",
					idPortfolio));
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

		assertEquals(1,mockData.getNbInsertedShare());
		assertEquals(Integer.valueOf(idAccount),mockData.getIdAccount());
		assertEquals(Integer.valueOf(idShare3),mockData.getIdShare());
		assertEquals(newQte,mockData.getNewQte());
		assertEquals(newUnitPrice,mockData.getNewUnitPrice());

		//for the buy table
		assertTrue(mockData.hasBuySellOrderThisState(idBuyShare, 1 /* executed*/));
		assertTrue(mockData.hasBuySellThisQte(idBuyShare, newQte));
		assertTrue(mockData.hasBuySellThisExecutedValue(idBuyShare, requestedPrice));
	}

}
