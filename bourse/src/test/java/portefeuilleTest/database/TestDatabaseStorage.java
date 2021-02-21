package portefeuilleTest.database;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.tuxanna.portefeuille.Quote;
import net.tuxanna.portefeuille.database.QuoteDB;
import net.tuxanna.portefeuille.database.SearchQuoteI;
import net.tuxanna.portefeuille.database.SearchQuoteStandard;
import net.tuxanna.portefeuille.database.ShareDB;
import portefeuilleTest.database.util.RamDatabaseForTest;

public class TestDatabaseStorage
{

	private static final String TICKER5 = "ticker5";
	private static final String NAME5 = "name5";
	private static final String TICKER4 = "ticker4";
	private static final String NAME4 = "name4";
	private static final String TICKER3 = "ticker3";
	private static final String NAME3 = "name3";
	private static final String TICKER2 = "ticker2";
	private static final String NAME2 = "name2";
	private static final String TICKER1 = "ticker1";
	private static final String NAME1 = "name1";
	
	private static final double highPrice_0 = 1.02;
	private static final double lastTradedPrice_0 = 1.03;
	private static final double lowPrice_0 = 1.05;
	private static final double openPrice_0 = 1.08;
	private static final double volume_0 = 1.013;
	
	private static final double highPrice_1 = 2.02;
	private static final double lastTradedPrice_1 = 2.03;
	private static final double lowPrice_1 = 2.05;
	private static final double openPrice_1 = 2.08;
	private static final double volume_1 = 2.013;
	
	private static final double highPrice_2 = 3.02;
	private static final double lastTradedPrice_2 = 3.03;
	private static final double lowPrice_2 = 3.05;
	private static final double openPrice_2 = 3.08;
	private static final double volume_2 = 3.013;
	
	private static final double highPrice_3 = 40.02;
	private static final double lastTradedPrice_3 = 40.03;
	private static final double lowPrice_3 = 40.05;
	private static final double openPrice_3 = 40.08;
	private static final double volume_3 = 40.013;
	
	private static final double highPrice_4 = 25.902;
	private static final double lastTradedPrice_4 = 25.903;
	private static final double lowPrice_4 = 25.905;
	private static final double openPrice_4 = 25.908;
	private static final double volume_4 = 25.9013;
	
	private RamDatabaseForTest testDb;
	//private TestTempFileDatabase testDb;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
	}

	@Before
	public void setUp() throws Exception
	{
		//testDb =new TestTempFileDatabase();
		testDb =new RamDatabaseForTest();
		assertTrue(testDb.createSchema());
	}

	@After
	public void tearDown() throws Exception
	{
		testDb.shutdown();
	}

	@Test
	public void testStoreShares()
	{
		ArrayList<ShareDB> listSharesToWrite = fillWithTestShares();
		
		boolean res=testDb.storeShares(listSharesToWrite);
		assertTrue(res);
		
		//read it
		ArrayList<ShareDB> listSharesToRead=new ArrayList<>();
		boolean resRead=testDb.loadShares(listSharesToRead);
		assertTrue(resRead);
		assertEquals(listSharesToRead.size(),listSharesToWrite.size());
		
		for (Iterator<ShareDB> iteratorRead = listSharesToRead.iterator(), iteratorWrite=listSharesToWrite.iterator(); 
				iteratorRead.hasNext(); iteratorWrite.hasNext())
		{
			ShareDB shareRead = (ShareDB) iteratorRead.next();
			ShareDB shareWrite = (ShareDB) iteratorWrite.next();
			
			assertEquals(shareRead.getName(),shareWrite.getName());
			assertEquals(shareRead.getTicker(),shareWrite.getTicker());
		}
	}

	@Test
	public void testStoreQuotation()
	{
		//needed to avoid BD error because foreign key not existing
		ArrayList<ShareDB> listSharesToWrite = fillWithTestShares();
		
		boolean res=testDb.storeShares(listSharesToWrite);
		assertTrue(res);
		
		//read it to get the IDs
		ArrayList<ShareDB> listSharesToRead=new ArrayList<>();
		boolean resRead=testDb.loadShares(listSharesToRead);
		assertTrue(resRead);
		
		//build the quote test set
		ArrayList<QuoteDB> fullQuotationsToWrite=new ArrayList<QuoteDB>();
		
		int idx=0;
		for (Iterator<ShareDB> iteratorRead = listSharesToRead.iterator(); 
				iteratorRead.hasNext();)
		{
			ShareDB shareRead = (ShareDB) iteratorRead.next();
			Quote quote=fillQuoteFully(idx);
			++idx;
			
			QuoteDB quoteDb=fillWithTestQuotation(shareRead,quote);
			fullQuotationsToWrite.add(quoteDb);
		}
		
		res=testDb.insertQuotation(fullQuotationsToWrite);
		assertTrue(res);
		
		Calendar cal = Calendar.getInstance(); // creates calendar
	    cal.setTime(new Date()); // sets calendar time/date
	    cal.add(Calendar.HOUR_OF_DAY, 1); // adds one hour
	    Date newDate=cal.getTime(); // returns new date object, one hour in the future
	    
		//store a second row with partial NULL items
		ArrayList<QuoteDB> partiallyFilledQuotationsToWrite=new ArrayList<QuoteDB>();
		idx=0;
		for (Iterator<ShareDB> iteratorRead = listSharesToRead.iterator(); 
				iteratorRead.hasNext();)
		{
			ShareDB shareRead = (ShareDB) iteratorRead.next();
			Quote quote=fillQuotePartially(idx);
			++idx;
			
			QuoteDB quoteDb=fillWithTestQuotation(shareRead,quote);
			
			quoteDb.setDate(newDate);
			partiallyFilledQuotationsToWrite.add(quoteDb);
		}
		res=testDb.insertQuotation(partiallyFilledQuotationsToWrite);
		assertTrue(res);
		
		// now read and check;
		idx=0;
		for (Iterator<ShareDB> iteratorRead = listSharesToRead.iterator(); 
				iteratorRead.hasNext();)
		{
			ShareDB shareRead = (ShareDB) iteratorRead.next();
			ArrayList<QuoteDB> readQuoteList=new ArrayList<QuoteDB>();
			SearchQuoteI search=new SearchQuoteStandard(shareRead);
			res=testDb.readQuotations(readQuoteList, search);
			assertTrue(res);
			//now compare
			assertEquals(2, readQuoteList.size());
			{
				Quote QuoteRead=readQuoteList.get(0).getQuotation();
				Quote QuoteWritten=fullQuotationsToWrite.get(idx).getQuotation();
				assertEquals(QuoteRead,QuoteWritten);
			}
			{
				Quote QuoteRead=readQuoteList.get(1).getQuotation();
				Quote QuoteWritten=partiallyFilledQuotationsToWrite.get(idx).getQuotation();
				assertEquals(QuoteRead,QuoteWritten);
			}
			idx++;
		}
	}
	
	private Quote fillQuoteFully(int dataSet)
	{
		Quote quote=new Quote();
		switch(dataSet%5)
		{
		case 0:
			quote.setHighPrice(highPrice_0);
			quote.setLastTradedPrice(lastTradedPrice_0);
			quote.setLowPrice(lowPrice_0);
			quote.setOpenPrice(openPrice_0);
			quote.setVolume(volume_0);
			break;
		case 1:
			quote.setHighPrice(highPrice_1);
			quote.setLastTradedPrice(lastTradedPrice_1);
			quote.setLowPrice(lowPrice_1);
			quote.setOpenPrice(openPrice_1);
			quote.setVolume(volume_1);
			break;
		case 2:
			quote.setHighPrice(highPrice_2);
			quote.setLastTradedPrice(lastTradedPrice_2);
			quote.setLowPrice(lowPrice_2);
			quote.setOpenPrice(openPrice_2);
			quote.setVolume(volume_2);
			break;
		case 3:
			quote.setHighPrice(highPrice_3);
			quote.setLastTradedPrice(lastTradedPrice_3);
			quote.setLowPrice(lowPrice_3);
			quote.setOpenPrice(openPrice_3);
			quote.setVolume(volume_3);
			break;
		case 4:
			quote.setHighPrice(highPrice_4);
			quote.setLastTradedPrice(lastTradedPrice_4);
			quote.setLowPrice(lowPrice_4);
			quote.setOpenPrice(openPrice_4);
			quote.setVolume(volume_4);
			break;
			
			default:
				assertFalse(true);
		}
		return quote;
	}
	
	private Quote fillQuotePartially(int dataSet)
	{
		Quote quote=new Quote();
		switch((dataSet+2)%5)
		{
		case 0:
			quote.setLastTradedPrice(lastTradedPrice_0+100.0);
			quote.setLowPrice(lowPrice_0+100.0);
			quote.setOpenPrice(openPrice_0+100.0);
			quote.setVolume(volume_0+100.0);
			break;
		case 1:
			quote.setLastTradedPrice(lastTradedPrice_1+100.0);
			break;
		case 2:
			quote.setLastTradedPrice(lastTradedPrice_2+100.0);
			break;
		case 3:
			quote.setHighPrice(highPrice_3+100.0);
			quote.setLastTradedPrice(lastTradedPrice_3+100.0);
			quote.setLowPrice(lowPrice_3+100.0);
			quote.setOpenPrice(openPrice_3+100.0);
			quote.setVolume(volume_3+100.0);
			break;
		case 4:
			quote.setHighPrice(highPrice_4+100.0);
			quote.setLastTradedPrice(lastTradedPrice_4+100.0);
			quote.setLowPrice(lowPrice_4+100.0);			
			quote.setOpenPrice(openPrice_4+100.0);			
			quote.setVolume(volume_4+100.0);
			break;
			
			default:
				assertFalse(true);
		}
		return quote;
	}
	
	private ArrayList<ShareDB> fillWithTestShares()
	{
		ArrayList<ShareDB> listSharesToWrite=new ArrayList<>();
		listSharesToWrite.add(new ShareDB(NAME1,TICKER1,ShareDB.Currency.EURO,true));
		listSharesToWrite.add(new ShareDB(NAME2,TICKER2,ShareDB.Currency.EURO,true));
		listSharesToWrite.add(new ShareDB(NAME3,TICKER3,ShareDB.Currency.EURO,true));
		listSharesToWrite.add(new ShareDB(NAME4,TICKER4,ShareDB.Currency.EURO,true));
		listSharesToWrite.add(new ShareDB(NAME5,TICKER5,ShareDB.Currency.EURO,true));
		return listSharesToWrite;
	}
	
	private QuoteDB fillWithTestQuotation(ShareDB share,Quote quote)
	{
		return new QuoteDB(share.getId(),quote);
	}
}
