package portefeuilleTest;

import static org.junit.Assert.*;

import org.junit.Test;

import net.tuxanna.portefeuille.Quote;

public class TestQuote
{
	@Test
	public void testEquals()
	{
		//same values (all)
		{
			String[] quotes={"53.83", "-0.60", "54.48", "54.48", "53.30", "1974540", "48.07", 
				"68.50", "52.87", "53.88", "54.43", "14.41", "0.00"};
			Quote val1=new Quote(quotes);
			Quote val2=new Quote(quotes);
		
			assertTrue(val1.equals(val2));
		}
		//try with some non set values
		{
			String[] quotes={"53.83", "-0.60", "54.48", "54.48", "53.30", "1974540", "48.07", 
				"68.50", "52.87", "53.88", "54.43", "14.41", "xxx"};
			Quote val1=new Quote(quotes);
			Quote val2=new Quote(quotes);
		
			assertTrue(val1.equals(val2));
		}
		{
			String[] quotes={"53.83", "-0.60", "54.48", "54.48", "53.30", "1974540", "48.07", 
				"68.50", "52.87", "53.88", "54.43", "xxx", "0.00"};
			Quote val1=new Quote(quotes);
			Quote val2=new Quote(quotes);
		
			assertTrue(val1.equals(val2));
		}
		{
			String[] quotes={"53.83", "-0.60", "54.48", "54.48", "53.30", "1974540", "48.07", 
				"68.50", "52.87", "53.88", "xxx", "14.41", "0.00"};
			Quote val1=new Quote(quotes);
			Quote val2=new Quote(quotes);
		
			assertTrue(val1.equals(val2));
		}
		{
			String[] quotes={"53.83", "-0.60", "54.48", "54.48", "53.30", "1974540", "48.07", 
				"68.50", "52.87", "xxx", "54.43", "14.41", "0.00"};
			Quote val1=new Quote(quotes);
			Quote val2=new Quote(quotes);
		
			assertTrue(val1.equals(val2));
		}
		{
			String[] quotes={"53.83", "-0.60", "54.48", "54.48", "53.30", "1974540", "48.07", 
				"68.50", "xxx", "53.88", "54.43", "14.41", "0.00"};
			Quote val1=new Quote(quotes);
			Quote val2=new Quote(quotes);
		
			assertTrue(val1.equals(val2));
		}
		{
			String[] quotes={"53.83", "-0.60", "54.48", "54.48", "53.30", "1974540", "48.07", 
				"xxx", "52.87", "53.88", "54.43", "14.41", "0.00"};
			Quote val1=new Quote(quotes);
			Quote val2=new Quote(quotes);
		
			assertTrue(val1.equals(val2));
		}
		{
			String[] quotes={"53.83", "-0.60", "54.48", "54.48", "53.30", "1974540", "xxx", 
				"68.50", "52.87", "53.88", "54.43", "14.41", "0.00"};
			Quote val1=new Quote(quotes);
			Quote val2=new Quote(quotes);
		
			assertTrue(val1.equals(val2));
		}
		{
			String[] quotes={"53.83", "-0.60", "54.48", "54.48", "53.30", "xxx", "48.07", 
				"68.50", "52.87", "53.88", "54.43", "14.41", "0.00"};
			Quote val1=new Quote(quotes);
			Quote val2=new Quote(quotes);
		
			assertTrue(val1.equals(val2));
		}
		{
			String[] quotes={"53.83", "-0.60", "54.48", "54.48", "xxx", "1974540", "48.07", 
				"68.50", "52.87", "53.88", "54.43", "14.41", "0.00"};
			Quote val1=new Quote(quotes);
			Quote val2=new Quote(quotes);
		
			assertTrue(val1.equals(val2));
		}
		{
			String[] quotes={"53.83", "-0.60", "54.48", "xx", "53.30", "1974540", "48.07", 
				"68.50", "52.87", "53.88", "54.43", "14.41", "0.00"};
			Quote val1=new Quote(quotes);
			Quote val2=new Quote(quotes);
		
			assertTrue(val1.equals(val2));
		}
		{
			String[] quotes={"53.83", "-0.60", "xxx", "54.48", "53.30", "1974540", "48.07", 
				"68.50", "52.87", "53.88", "54.43", "14.41", "0.00"};
			Quote val1=new Quote(quotes);
			Quote val2=new Quote(quotes);
		
			assertTrue(val1.equals(val2));
		}
		
		// now try with different values
		{
			String[] quotes1={"53.83", "-0.60", "54.9", "54.48", "53.30", "1974540", "48.07", "68.50", "52.87", "53.88", "54.43", "14.41", "0.00"};
		    String[] quotes2={"53.903", "-0.60", "54.9", "54.48", "53.30", "1974540", "48.07", "68.50", "52.87", "53.88", "54.43", "14.41", "0.00"};
			Quote val1=new Quote(quotes1);
			Quote val2=new Quote(quotes2);
		
			assertFalse(val1.equals(val2));
		}
		
		{
			String[] quotes1={"53.83", "-0.60", "54.9", "54.48", "53.30", "1974540", "48.07", "68.50", "52.87", "53.88", "54.43", "14.41", "0.00"};
		    String[] quotes2={"53.93", "-0.960", "54.9", "54.48", "53.30", "1974540", "48.07", "68.50", "52.87", "53.88", "54.43", "14.41", "0.00"};
			Quote val1=new Quote(quotes1);
			Quote val2=new Quote(quotes2);
		
			assertFalse(val1.equals(val2));
		}
		{
			String[] quotes1={"53.83", "-0.60", "54.9", "54.48", "53.30", "1974540", "48.07", "68.50", "52.87", "53.88", "54.43", "14.41", "0.00"};
		    String[] quotes2={"53.93", "-0.60", "54.19", "54.48", "53.30", "1974540", "48.07", "68.50", "52.87", "53.88", "54.43", "14.41", "0.00"};
			Quote val1=new Quote(quotes1);
			Quote val2=new Quote(quotes2);
		
			assertFalse(val1.equals(val2));
		}
		{
			String[] quotes1={"53.83", "-0.60", "54.9", "54.48", "53.30", "1974540", "48.07", "68.50", "52.87", "53.88", "54.43", "14.41", "0.00"};
		    String[] quotes2={"53.93", "-0.60", "54.9", "54.148", "53.30", "1974540", "48.07", "68.50", "52.87", "53.88", "54.43", "14.41", "0.00"};
			Quote val1=new Quote(quotes1);
			Quote val2=new Quote(quotes2);
		
			assertFalse(val1.equals(val2));
		}
		{
			String[] quotes1={"53.83", "-0.60", "54.9", "54.48", "53.30", "1974540", "48.07", "68.50", "52.87", "53.88", "54.43", "14.41", "0.00"};
		    String[] quotes2={"53.93", "-0.60", "54.9", "54.48", "53.30", "974540", "48.07", "68.50", "52.87", "53.88", "54.43", "14.41", "0.00"};
			Quote val1=new Quote(quotes1);
			Quote val2=new Quote(quotes2);
		
			assertFalse(val1.equals(val2));
		}
		{
			String[] quotes1={"53.83", "-0.60", "54.9", "54.48", "53.30", "1974540", "48.07", "68.50", "52.87", "53.88", "54.43", "14.41", "0.00"};
		    String[] quotes2={"53.93", "-0.60", "54.9", "54.48", "53.30", "1974540", "48.107", "68.50", "52.87", "53.88", "54.43", "14.41", "0.00"};
			Quote val1=new Quote(quotes1);
			Quote val2=new Quote(quotes2);
		
			assertFalse(val1.equals(val2));
		}
		{
			String[] quotes1={"53.83", "-0.60", "54.9", "54.48", "53.30", "1974540", "48.07", "68.50", "52.87", "53.88", "54.43", "14.41", "0.00"};
		    String[] quotes2={"53.93", "-0.60", "54.9", "54.48", "53.30", "1974540", "48.07", "68.550", "52.87", "53.88", "54.43", "14.41", "0.00"};
			Quote val1=new Quote(quotes1);
			Quote val2=new Quote(quotes2);
		
			assertFalse(val1.equals(val2));
		}
		{
			String[] quotes1={"53.83", "-0.60", "54.9", "54.48", "53.30", "1974540", "48.07", "68.50", "52.87", "53.88", "54.43", "14.41", "0.00"};
		    String[] quotes2={"53.93", "-0.60", "54.9", "54.48", "53.30", "1974540", "48.07", "68.50", "52.8217", "53.88", "54.43", "14.41", "0.00"};
			Quote val1=new Quote(quotes1);
			Quote val2=new Quote(quotes2);
		
			assertFalse(val1.equals(val2));
		}
		{
			String[] quotes1={"53.83", "-0.60", "54.9", "54.48", "53.30", "1974540", "48.07", "68.50", "52.87", "53.88", "54.43", "14.41", "0.00"};
		    String[] quotes2={"53.93", "-0.60", "54.9", "54.48", "53.30", "1974540", "48.07", "68.50", "52.817", "53.88", "54.43", "14.41", "0.00"};
			Quote val1=new Quote(quotes1);
			Quote val2=new Quote(quotes2);
		
			assertFalse(val1.equals(val2));
		}
		{
			String[] quotes1={"53.83", "-0.60", "54.9", "54.48", "53.30", "1974540", "48.07", "68.50", "52.87", "53.88", "54.43", "14.41", "0.00"};
		    String[] quotes2={"53.93", "-0.60", "54.9", "54.48", "53.30", "1974540", "48.07", "68.50", "52.87", "53.788", "54.43", "14.41", "0.00"};
			Quote val1=new Quote(quotes1);
			Quote val2=new Quote(quotes2);
		
			assertFalse(val1.equals(val2));
		}
		{
			String[] quotes1={"53.83", "-0.60", "54.9", "54.48", "53.30", "1974540", "48.07", "68.50", "52.87", "53.88", "54.43", "14.41", "0.00"};
		    String[] quotes2={"53.93", "-0.60", "54.9", "54.48", "53.30", "1974540", "48.07", "68.50", "52.87", "53.88", "54.413", "14.41", "0.00"};
			Quote val1=new Quote(quotes1);
			Quote val2=new Quote(quotes2);
		
			assertFalse(val1.equals(val2));
		}
		{
			String[] quotes1={"53.83", "-0.60", "54.9", "54.48", "53.30", "1974540", "48.07", "68.50", "52.87", "53.88", "54.43", "14.41", "0.00"};
		    String[] quotes2={"53.93", "-0.60", "54.9", "54.48", "53.30", "1974540", "48.07", "68.50", "52.87", "53.88", "54.43", "14.441", "0.00"};
			Quote val1=new Quote(quotes1);
			Quote val2=new Quote(quotes2);
		
			assertFalse(val1.equals(val2));
		}
		{
			String[] quotes1={"53.83", "-0.60", "54.9", "54.48", "53.30", "1974540", "48.07", "68.50", "52.87", "53.88", "54.43", "14.41", "0.00"};
		    String[] quotes2={"53.93", "-0.60", "54.9", "54.48", "53.30", "1974540", "48.07", "68.550", "52.87", "53.88", "54.43", "14.41", "0.70"};
			Quote val1=new Quote(quotes1);
			Quote val2=new Quote(quotes2);
		
			assertFalse(val1.equals(val2));
		}
	}
	
	@Test
	public void testFullParameterString()
	{
		String[] quotes={"53.83", "-0.60", "54.8", "54.48", "53.30", "1974540", "48.07", 
				"68.50", "52.87", "53.88", "54.43", "14.41", "0.00"};
		Quote val=new Quote(quotes);

		assertTrue(val.getLastTradedPrice().isValid());
		assertEquals(53.83, val.getLastTradedPrice().getValue(), 0.0001);

		assertTrue(val.getChangeInPrice().isValid());
		assertEquals(-0.6, val.getChangeInPrice().getValue(), 0.0001);

		assertTrue(val.getOpenPrice().isValid());
		assertEquals(54.8, val.getOpenPrice().getValue(), 0.0001);

		assertTrue(val.getHighPrice().isValid());
		assertEquals(54.48, val.getHighPrice().getValue(), 0.0001);

		assertTrue(val.getLowPrice().isValid());
		assertEquals(53.30, val.getLowPrice().getValue(), 0.0001);

		assertTrue(val.getVolume().isValid());
		assertEquals(1974540., val.getVolume().getValue(), 0.0001);

		assertTrue(val.getLow52Week().isValid());
		assertEquals(48.07, val.getLow52Week().getValue(), 0.0001);

		assertTrue(val.getHigh52Week().isValid());
		assertEquals(68.50, val.getHigh52Week().getValue(), 0.0001);	

		assertTrue(val.getMobileAverage50Days().isValid());
		assertEquals(52.87, val.getMobileAverage50Days().getValue(), 0.0001);	

		assertTrue(val.getMobileAverage200Days().isValid());
		assertEquals(53.88, val.getMobileAverage200Days().getValue(), 0.0001);	

		assertTrue(val.getPreviousClose().isValid());
		assertEquals(54.43, val.getPreviousClose().getValue(), 0.0001);	

		assertTrue(val.getPeRatio().isValid());
		assertEquals(14.41, val.getPeRatio().getValue(), 0.0001);	

		assertTrue(val.getShortRatio().isValid());
		assertEquals(0.0, val.getShortRatio().getValue(), 0.0001);			


		//other possible test string [155.67, -1.21, 157.14, 157.70, 154.86, 2671852, 116.90, 165.00, 157.52, 154.03, 156.88, 12.66, 7.61]
	}


	@Test
	public void testPartialParameterString()
	{
		String[] quotes={"53.83", "-0.60", "54.48", "54.48", "53.30", "1974540", "48.07", 
				"68.50", "52.87"};
		Quote val=new Quote(quotes);

		assertTrue(val.getLastTradedPrice().isValid());
		assertEquals(53.83, val.getLastTradedPrice().getValue(), 0.0001);

		assertTrue(val.getChangeInPrice().isValid());
		assertEquals(-0.6, val.getChangeInPrice().getValue(), 0.0001);

		assertTrue(val.getOpenPrice().isValid());
		assertEquals(54.48, val.getOpenPrice().getValue(), 0.0001);

		assertTrue(val.getHighPrice().isValid());
		assertEquals(54.48, val.getHighPrice().getValue(), 0.0001);

		assertTrue(val.getLowPrice().isValid());
		assertEquals(53.30, val.getLowPrice().getValue(), 0.0001);

		assertTrue(val.getVolume().isValid());
		assertEquals(1974540., val.getVolume().getValue(), 0.0001);

		assertTrue(val.getLow52Week().isValid());
		assertEquals(48.07, val.getLow52Week().getValue(), 0.0001);

		assertTrue(val.getHigh52Week().isValid());
		assertEquals(68.50, val.getHigh52Week().getValue(), 0.0001);	

		assertTrue(val.getMobileAverage50Days().isValid());
		assertEquals(52.87, val.getMobileAverage50Days().getValue(), 0.0001);	

		assertFalse(val.getMobileAverage200Days().isValid());
		assertFalse(val.getPreviousClose().isValid());
		assertFalse(val.getPeRatio().isValid());
		assertFalse(val.getShortRatio().isValid());
	}
	
	@Test
	public void testWithInvalidValues()
	{
		String[] quotes={"53.83", "-0.60", "54.48", "54.48", "53.30", "N/A", "48.07", 
				"68.50", "52.87", "53.88", "bbbb", "14.41", ""};
		Quote val=new Quote(quotes);

		assertTrue(val.getLastTradedPrice().isValid());
		assertEquals(53.83, val.getLastTradedPrice().getValue(), 0.0001);

		assertTrue(val.getChangeInPrice().isValid());
		assertEquals(-0.6, val.getChangeInPrice().getValue(), 0.0001);

		assertTrue(val.getOpenPrice().isValid());
		assertEquals(54.48, val.getOpenPrice().getValue(), 0.0001);

		assertTrue(val.getHighPrice().isValid());
		assertEquals(54.48, val.getHighPrice().getValue(), 0.0001);

		assertTrue(val.getLowPrice().isValid());
		assertEquals(53.30, val.getLowPrice().getValue(), 0.0001);

		assertFalse(val.getVolume().isValid());
		

		assertTrue(val.getLow52Week().isValid());
		assertEquals(48.07, val.getLow52Week().getValue(), 0.0001);

		assertTrue(val.getHigh52Week().isValid());
		assertEquals(68.50, val.getHigh52Week().getValue(), 0.0001);	

		assertTrue(val.getMobileAverage50Days().isValid());
		assertEquals(52.87, val.getMobileAverage50Days().getValue(), 0.0001);	

		assertTrue(val.getMobileAverage200Days().isValid());
		assertEquals(53.88, val.getMobileAverage200Days().getValue(), 0.0001);	

		assertFalse(val.getPreviousClose().isValid());
		

		assertTrue(val.getPeRatio().isValid());
		assertEquals(14.41, val.getPeRatio().getValue(), 0.0001);	

		assertFalse(val.getShortRatio().isValid());
					


		//other possible test string [155.67, -1.21, 157.14, 157.70, 154.86, 2671852, 116.90, 165.00, 157.52, 154.03, 156.88, 12.66, 7.61]
	}
}

