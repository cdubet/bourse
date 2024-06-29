package portefeuilleTest.businessLogic.eval;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import net.tuxanna.portefeuille.business_logic.eval.QuotationMapStorage;
import net.tuxanna.portefeuille.business_logic.eval.ShareIdAndDate;
import net.tuxanna.portefeuille.dataFeed.TickerI;
import net.tuxanna.portefeuille.database.ShareDB;

public class TestQuotationMapStorage
{
	private static final int ID_SHARE_1 = 0;
	private static final double NEW_PRICE_SHARE_1 = 28.;
	private static final int ID_SHARE_2 = 1;
	private static final double NEW_PRICE_SHARE_2 = 11.0;

	@Test
	public void testGet()
	{
		QuotationMapStorage mapToTest=new QuotationMapStorage();

		Calendar cal = Calendar.getInstance(); // creates calendar

		cal.set(Calendar.YEAR, 2016);
		cal.set(Calendar.MONTH, 9); //month starts at 0
		cal.set(Calendar.DAY_OF_MONTH, 15);

		cal.set(Calendar.HOUR_OF_DAY, 10);
		cal.set(Calendar.MINUTE, 01);
		cal.set(Calendar.SECOND,40 );
		cal.set(Calendar.MILLISECOND, 125);

		Date testTime = cal.getTime();
		ShareDB share=new ShareDB(ID_SHARE_1,"share1","sh1",ShareDB.Currency.EURO,TickerI.TypeOfItem.TYPE_SHARE);
		ShareIdAndDate key1=new ShareIdAndDate(share,testTime);
		mapToTest.add(key1, NEW_PRICE_SHARE_1);

		ShareDB share2=new ShareDB(ID_SHARE_2,"share2","sh2",ShareDB.Currency.EURO,TickerI.TypeOfItem.TYPE_SHARE);
		ShareIdAndDate key2=new ShareIdAndDate(share2,testTime);
		mapToTest.add(key2, NEW_PRICE_SHARE_2);


		//first case : just get what we had
		{
			Double result = mapToTest.get(key1);
			assertNotNull(result);
			assertEquals(result.doubleValue(),NEW_PRICE_SHARE_1,0.01);
		}

		//second case : same day
		{	   
			//cal.set(Calendar.HOUR_OF_DAY, 9);
			Date newTestTime = cal.getTime();
			ShareIdAndDate keyNewTime=new ShareIdAndDate(share,newTestTime);
			
			
			int x=keyNewTime.hashCode();
			int y=key1.hashCode();
			assertEquals(x,y); //if the hash are not the same, we will not find
			assertEquals(keyNewTime,key1);
			{
				Double result = mapToTest.get(keyNewTime);

				assertNotNull(result);
				assertEquals(result.doubleValue(),NEW_PRICE_SHARE_1,0.01);
			}
		}
		//same day other share
		{
			cal.set(Calendar.HOUR_OF_DAY, 19);
			Date newTestTime = cal.getTime();
			ShareIdAndDate keyNewTime=new ShareIdAndDate(share2,newTestTime);
			int x=keyNewTime.hashCode();
			int y=key2.hashCode();
			assertEquals(x,y); //if the hash are not the same, we will not find
			assertEquals(keyNewTime,key2);
			{
				Double result = mapToTest.get(keyNewTime);

				assertNotNull(result);
				assertEquals(result.doubleValue(),NEW_PRICE_SHARE_2,0.01);
			}
		}

		//other day -> should not find anything
		{
			cal.set(Calendar.DAY_OF_MONTH, 16);
			Date newTestTime = cal.getTime();
			ShareIdAndDate keyNewTime=new ShareIdAndDate(share2,newTestTime);

			{
				Double result = mapToTest.get(keyNewTime);

				assertNull(result);
			}
		}
	}

}
