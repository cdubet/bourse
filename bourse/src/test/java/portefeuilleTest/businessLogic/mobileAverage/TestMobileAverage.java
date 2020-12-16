package portefeuilleTest.businessLogic.mobileAverage;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import net.tuxanna.portefeuille.Quote;
import net.tuxanna.portefeuille.business_logic.mobile_average.MobileAverage;
import net.tuxanna.portefeuille.database.QuoteDB;
import net.tuxanna.portefeuille.database.ShareDB;
import portefeuilleTest.database.util.RamDatabaseForTestAtOneDay;

public class TestMobileAverage
{

	final double PRICE_1=100.0;
	final double PRICE_2=105.0;
	final double PRICE_3=109.0;
	final double PRICE_4=119.0;
	final double PRICE_NOT_USED=1;

	@Test
	public void testCompute()
	{
		
		List<QuoteDB> listQuote = buildFakeDbData();

		FakeDatabase fakeDb=new FakeDatabase(listQuote, true /* success*/);
		
		ShareDB share=new ShareDB();
		//test
		MobileAverage mobileAverage= new MobileAverage(share,fakeDb , 3);
		Double average = mobileAverage.compute();
		
		assertNotNull(average);
		
		final double expectedAverage=(PRICE_1+PRICE_2+PRICE_3)/3.0;
		assertEquals(expectedAverage, average,0.005);
	}

	@Test
	public void testComputeErrorBecauseNotEnoughtData()
	{
		
		List<QuoteDB> listQuote = buildFakeDbData();

		FakeDatabase fakeDb=new FakeDatabase(listQuote, true /* success*/);
		
		ShareDB share=new ShareDB();
		//test
		MobileAverage mobileAverage= new MobileAverage(share,fakeDb , 4 /*only 3 available */);
		Double average = mobileAverage.compute();
		
		assertNull(average);
		
	}
	
	@Test
	public void testComputeUseSaturdayDataWhenFridayMissing()
	{
		
		List<QuoteDB> listQuote = buildFakeDbDataWithSaturdayDataAndFridayMissing();

		FakeDatabase fakeDb=new FakeDatabase(listQuote, true /* success*/);
		
		ShareDB share=new ShareDB();
		//test
		MobileAverage mobileAverage= new MobileAverage(share,fakeDb , 3 );
		Double average = mobileAverage.compute();
		
		assertNotNull(average);
		
		final double expectedAverage=(PRICE_4+PRICE_2+PRICE_3)/3.0;
		assertEquals(expectedAverage, average,0.005);
		
	}
	
	private List<QuoteDB> buildFakeDbDataWithSaturdayDataAndFridayMissing()
	{
		ArrayList<QuoteDB> listQuote=new ArrayList<QuoteDB>();
		{
	
			Calendar cal = Calendar.getInstance(); // creates calendar

			// wednesday 12/11/2016
			cal.set(Calendar.YEAR, 2016);
			cal.set(Calendar.MONTH, 9); //month starts at 0
			cal.set(Calendar.DAY_OF_MONTH, 12);

			cal.set(Calendar.HOUR_OF_DAY, 10);
			cal.set(Calendar.MINUTE, 01);
			cal.set(Calendar.SECOND,40 );
			cal.set(Calendar.MILLISECOND, 125);
			{	
				Date date = cal.getTime();
				QuoteDB quote=new QuoteDB();

				quote.setDate(date);

				Quote quotation=new Quote();
				quotation.setLastTradedPrice(PRICE_1);
				quote.setQuotation(quotation);
				listQuote.add(quote);
			}

			//second item, same day 1 h later
			cal.set(Calendar.HOUR_OF_DAY, 11);
			{	
				Date date = cal.getTime();
				QuoteDB quote=new QuoteDB();

				quote.setDate(date);

				Quote quotation=new Quote();
				quotation.setLastTradedPrice(PRICE_NOT_USED);
				quote.setQuotation(quotation);
				listQuote.add(quote);
			}
			//second item, same day 6 h later
			cal.set(Calendar.HOUR_OF_DAY, 17);
			{	
				Date date = cal.getTime();
				QuoteDB quote=new QuoteDB();

				quote.setDate(date);

				Quote quotation=new Quote();
				quotation.setLastTradedPrice(PRICE_NOT_USED);
				quote.setQuotation(quotation);
				listQuote.add(quote);
			}
			
			//next day : Thursday
			cal.set(Calendar.DAY_OF_MONTH, 13);
			cal.set(Calendar.HOUR_OF_DAY, 10);
			{	
				Date date = cal.getTime();
				QuoteDB quote=new QuoteDB();

				quote.setDate(date);

				Quote quotation=new Quote();
				quotation.setLastTradedPrice(PRICE_2);
				quote.setQuotation(quotation);
				listQuote.add(quote);
			}
			
			// friday missing : saturday instead
			cal.set(Calendar.DAY_OF_MONTH, 15);
			cal.set(Calendar.HOUR_OF_DAY, 10);
			{	
				Date date = cal.getTime();
				QuoteDB quote=new QuoteDB();

				quote.setDate(date);

				Quote quotation=new Quote();
				quotation.setLastTradedPrice(PRICE_3);
				quote.setQuotation(quotation);
				listQuote.add(quote);
			}
			
			//monday
			cal.set(Calendar.DAY_OF_MONTH, 17);
			cal.set(Calendar.HOUR_OF_DAY, 10);
			{	
				Date date = cal.getTime();
				QuoteDB quote=new QuoteDB();

				quote.setDate(date);

				Quote quotation=new Quote();
				quotation.setLastTradedPrice(PRICE_4);
				quote.setQuotation(quotation);
				listQuote.add(quote);
			}
		}
		return listQuote;
	}
	
	private List<QuoteDB> buildFakeDbData()
	{
		ArrayList<QuoteDB> listQuote=new ArrayList<QuoteDB>();
		{
	
			Calendar cal = Calendar.getInstance(); // creates calendar

			cal.set(Calendar.YEAR, 2016);
			cal.set(Calendar.MONTH, 9); //month starts at 0
			cal.set(Calendar.DAY_OF_MONTH, 3);

			cal.set(Calendar.HOUR_OF_DAY, 10);
			cal.set(Calendar.MINUTE, 01);
			cal.set(Calendar.SECOND,40 );
			cal.set(Calendar.MILLISECOND, 125);
			{	
				Date date = cal.getTime();
				QuoteDB quote=new QuoteDB();

				quote.setDate(date);

				Quote quotation=new Quote();
				quotation.setLastTradedPrice(PRICE_1);
				quote.setQuotation(quotation);
				listQuote.add(quote);
			}

			//second item, same day 1 h later
			cal.set(Calendar.HOUR_OF_DAY, 11);
			{	
				Date date = cal.getTime();
				QuoteDB quote=new QuoteDB();

				quote.setDate(date);

				Quote quotation=new Quote();
				quotation.setLastTradedPrice(PRICE_NOT_USED);
				quote.setQuotation(quotation);
				listQuote.add(quote);
			}
			//second item, same day 6 h later
			cal.set(Calendar.HOUR_OF_DAY, 17);
			{	
				Date date = cal.getTime();
				QuoteDB quote=new QuoteDB();

				quote.setDate(date);

				Quote quotation=new Quote();
				quotation.setLastTradedPrice(PRICE_NOT_USED);
				quote.setQuotation(quotation);
				listQuote.add(quote);
			}
			
			//next day
			cal.set(Calendar.DAY_OF_MONTH, 4);
			cal.set(Calendar.HOUR_OF_DAY, 10);
			{	
				Date date = cal.getTime();
				QuoteDB quote=new QuoteDB();

				quote.setDate(date);

				Quote quotation=new Quote();
				quotation.setLastTradedPrice(PRICE_2);
				quote.setQuotation(quotation);
				listQuote.add(quote);
			}
			
			// one week later
			cal.set(Calendar.DAY_OF_MONTH, 11);
			cal.set(Calendar.HOUR_OF_DAY, 10);
			{	
				Date date = cal.getTime();
				QuoteDB quote=new QuoteDB();

				quote.setDate(date);

				Quote quotation=new Quote();
				quotation.setLastTradedPrice(PRICE_3);
				quote.setQuotation(quotation);
				listQuote.add(quote);
			}
		}
		return listQuote;
	}

	@Test
	public void testComputeMobileAverageWithDbInput()
	{
		try
		{
			RamDatabaseForTestAtOneDay testDb=new RamDatabaseForTestAtOneDay("TEST_DATA/portfolio_9_dec_2016.xml");
			assertTrue(testDb.setup());
			
			ShareDB share=new ShareDB();
			share.setId(5);
			//test
			MobileAverage mobileAverage= new MobileAverage(share,testDb , 20);
			Double average = mobileAverage.compute();
			assertNotNull(average);
			
			final double expectedAverage=43.653;
			assertEquals(expectedAverage, average,0.005);
			testDb.shutdown();

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
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		
	}
}
