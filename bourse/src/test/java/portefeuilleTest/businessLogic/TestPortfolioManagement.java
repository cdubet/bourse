package portefeuilleTest.businessLogic;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.tuxanna.portefeuille.business_logic.PortfolioManagement;
import net.tuxanna.portefeuille.business_logic.util.ShareToQuotations;
import net.tuxanna.portefeuille.dataFeed.TickerI;
import net.tuxanna.portefeuille.database.ShareDB;


public class TestPortfolioManagement
{

	private PortfolioManagement testPortfolio; //class under test
	private static FakeQuotationProvider fakeQuotationProvider; 
	private static FakeDatabase testDb;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		fakeQuotationProvider=new FakeQuotationProvider();
		testDb=new FakeDatabase();
	}

	@Before
	public void setUp() throws Exception
	{
		testPortfolio= new PortfolioManagement();
		testPortfolio.setClock(new FakeDate());
		testPortfolio.setQuotationProvider(fakeQuotationProvider);
		testPortfolio.setDatabase(testDb);
	}

	@Test
	public void testGetLatestQuotationMissingQuotes()
	{
		List<TickerI> listPredefinedTickers = fakeQuotationProvider.getListPredefinedTickers();
		//build the share list
		List<ShareDB> listShares=new ArrayList<ShareDB>();
		int idx=0;
		for (TickerI ticker : listPredefinedTickers)
		{
			String name="testShare"+Integer.valueOf(idx++).toString();
			ShareDB share=new ShareDB(idx,name, 
					ticker.getSymbol(),
					ShareDB.Currency.DOLLAR_US,
					ticker.isShare());
			listShares.add(share);
		}

		//add the missing (no ticker)
		{
			ShareDB share=new ShareDB(++idx,"unknown share", 
				"no ticker existinng",
				ShareDB.Currency.DOLLAR_US,
				true);
			listShares.add(share);
		}
		ShareToQuotations listOfQuotationsDB=new ShareToQuotations();
		//test
		boolean res=testPortfolio.getLatestQuotationFromInternetAndStoreThem( listShares,listOfQuotationsDB);
		
		//check
		assertTrue(res);
		//same amount of data
		final int predefineSize = listPredefinedTickers.size();
		final int quoteSize=testDb.getStoredQuotes().size();
		assertEquals(predefineSize,quoteSize);
	}

}
