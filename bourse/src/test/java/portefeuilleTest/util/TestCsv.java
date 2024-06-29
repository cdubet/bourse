package portefeuilleTest.util;


import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import org.junit.Assert;
import net.tuxanna.portefeuille.Quote;
import net.tuxanna.portefeuille.business_logic.util.ShareToQuotations;
import net.tuxanna.portefeuille.dataFeed.TickerI;
import net.tuxanna.portefeuille.database.QuoteDB;
import net.tuxanna.portefeuille.database.ShareDB;
import net.tuxanna.portefeuille.database.ShareDB.Currency;
import portefeuilleTest.businessLogic.FakeDate;

public class TestCsv
{
	private final String ticker1="ticker1";
	private final String name1="name1";
	private final int id1=1;

	private final String ticker2="ticker2";
	private final String name2="name2";
	private final int id2=2;
	private final int id3=3;
	
	List<ShareDB> buildListOf2Share(int id_share1,int id_share2)
	{
		final Currency currency=Currency.EURO;

		List<ShareDB> listShares=new ArrayList<>();
		ShareDB share1=new ShareDB(id_share1,name1,ticker1,currency,TickerI.TypeOfItem.TYPE_SHARE /*does not matter here*/);
		listShares.add(share1);

		ShareDB share2=new ShareDB(id_share2,name2,ticker2,currency,TickerI.TypeOfItem.TYPE_SHARE/*does not matter here*/);
		listShares.add(share2);

		return listShares;
	}

	private ShareToQuotations buildTestObject2ShareList(List<ShareDB> listShares)
	{
		ShareDB share1 = listShares.get(0);

		Quote quote1=new Quote();
		double price1=789.1234;
		quote1.setLastTradedPrice(price1);
		QuoteDB quoteDb1=new QuoteDB(share1.getId(), quote1);

		ShareDB share2 = listShares.get(1);
		Quote quote2=new Quote();
		double price2=98753.0124;
		quote2.setLastTradedPrice(price2);
		QuoteDB quoteDb2=new QuoteDB(share2.getId(), quote2);


		ShareToQuotations objToTest=new ShareToQuotations();
		objToTest.add(share1, quoteDb1);
		objToTest.add(share2, quoteDb2);


		FakeDate fakeDate=new FakeDate();
		objToTest.setClock(fakeDate);

		return objToTest;
	}

	@Test
	public void exportAllSharesAsCsvInListOrder()
	{
		OutputStream result=new ByteArrayOutputStream();
		List<ShareDB> listShares=buildListOf2Share(id1,id2);

		ShareToQuotations objToTest=buildTestObject2ShareList(listShares);

		//act
		try
		{
			objToTest.exportListSharesAsCsv(listShares,result);
		}
		catch (IOException e)
		{
			Assert.fail("Exception " + e);
		}

		//expected
		//1	name1	ticker1	789.12
		//2	name2	ticker2	98753.01
		//
		//generated 17-10-2016 at 10:00:00
		String expectedResult = String.format("%d\t%s\t%s\t789.12\n%d\t%s\t%s\t98753.01\n\ngenerated 17-10-2016 at 10:00:00\n",id1,name1,ticker1,id2,name2,ticker2);
		assertEquals(expectedResult,result.toString());
	}
	@Test
	public void exportAllSharesAsCsv_whenIdMissing_holeIsDisplayed()
	{
		OutputStream result=new ByteArrayOutputStream();
		List<ShareDB> listShares=buildListOf2Share(id1,id3);

		ShareToQuotations objToTest=buildTestObject2ShareList(listShares);

		//act
		try
		{
			objToTest.exportListSharesAsCsv(listShares,result);
		}
		catch (IOException e)
		{
			Assert.fail("Exception " + e);
		}

		//expected
		//1	name1	ticker1	789.12
		//2 no more in DB
		//3	name2	ticker2	98753.01
		//
		//generated 17-10-2016 at 10:00:00
		String expectedResult = String.format("%d\t%s\t%s\t789.12\n%d\tno more in DB\n%d\t%s\t%s\t98753.01\n\ngenerated 17-10-2016 at 10:00:00\n",id1,name1,ticker1,id2,id3,name2,ticker2);
		assertEquals(expectedResult,result.toString());
	}
}
