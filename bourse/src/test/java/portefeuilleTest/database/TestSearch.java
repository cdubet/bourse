package portefeuilleTest.database;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import net.tuxanna.portefeuille.database.QuoteDB;
import net.tuxanna.portefeuille.database.SearchLimitedNumberOfQuote;
import net.tuxanna.portefeuille.database.ShareDB;
import portefeuilleTest.database.util.RamDatabaseForTestAtOneDay;

public class TestSearch
{
	private RamDatabaseForTestAtOneDay testDb;
	@After
	public void tearDown() throws Exception
	{
		testDb.shutdown();
	}
	
	@Test
	public void testSearchLimitedNumberOfQuotes()
	{
		try
		{
			testDb=new RamDatabaseForTestAtOneDay("TEST_DATA/quotes_for_2_shares.xml");
			assertTrue(testDb.setup());
			
			
			//now make the test

			ShareDB share = new ShareDB(1 /* id */, 
					"not matter", 
					"not matter",
					ShareDB.Currency.EURO, //does not matter
					true //does not matter
					);
			SearchLimitedNumberOfQuote search=new SearchLimitedNumberOfQuote(share, 5);
			List<QuoteDB> listQuote=new ArrayList<QuoteDB>();
			boolean res= testDb.readQuotations(listQuote, search);
			assertTrue(res);
			
			//check expected data are there
			assertEquals(6,listQuote.size());
			
			int idx=0;
			assertEquals(41,listQuote.get(idx).getIdQuote());
			assertEquals(3.03, listQuote.get(idx).getQuotation().getLastTradedPrice().getValue(), 0.05);
			//<QUOTES IDQUOTES="41" IDSHARE="1" DATEQUOTE="2016-10-15 09:41:42.0" LASTTRADEDPRICE="3.03" OPENPRICE="2.08"  VOLUME="100"/>

			idx++;
			assertEquals(51,listQuote.get(idx).getIdQuote());
			assertEquals(205.0, listQuote.get(idx).getQuotation().getLastTradedPrice().getValue(), 0.05);
			// <QUOTES IDQUOTES="51" IDSHARE="1" DATEQUOTE="2016-10-15 10:41:42.0"					LASTTRADEDPRICE="205.0" OPENPRICE="2.08"  VOLUME="100"/>
				
			// <QUOTES IDQUOTES="61" IDSHARE="1" DATEQUOTE="2016-10-16 09:41:42.0" LASTTRADEDPRICE="301.03" OPENPRICE="2.08"  VOLUME="100"/>
			idx++;
			assertEquals(61,listQuote.get(idx).getIdQuote());
				
			//	<QUOTES IDQUOTES="71" IDSHARE="1" DATEQUOTE="2016-10-18 09:41:42.0"				LASTTRADEDPRICE="89.2" OPENPRICE="2.08"  VOLUME="100"/>	
			idx++;
			assertEquals(71,listQuote.get(idx).getIdQuote());
			
			//	<QUOTES IDQUOTES="81" IDSHARE="1" DATEQUOTE="2016-10-19 09:41:42.0"					LASTTRADEDPRICE="37.03" OPENPRICE="2.08"  VOLUME="100"/>
			idx++;
			assertEquals(81,listQuote.get(idx).getIdQuote());

			//	<QUOTES IDQUOTES="91" IDSHARE="1" DATEQUOTE="2016-10-20 09:41:42.0"		LASTTRADEDPRICE="50.2" OPENPRICE="2.08"  VOLUME="100"/>
			idx++;
			assertEquals(91,listQuote.get(idx).getIdQuote());
		}
		catch ( Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

	}
}
