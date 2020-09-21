package portefeuilleTest.util;

import static org.junit.Assert.*;

import org.junit.Test;
import net.tuxanna.portefeuille.util.DigitValue;
public class TestDigitalValue
{

	@Test
	public void testDigitValueString()
	{
		{
			DigitValue val = new DigitValue();
			assertFalse(val.isValid());
		}
		{
			DigitValue val = new DigitValue("12.5");
			assertTrue(val.isValid());
			assertEquals(12.5, val.getValue(), 0.001 /*delta*/);
		}
		{
			DigitValue val = new DigitValue("102.54321");
			assertTrue(val.isValid());
			assertEquals(102.54321, val.getValue(), 0.001 /*delta*/);
		}
		//negative values
		{
			DigitValue val = new DigitValue("- 1.25");
			assertTrue(val.isValid());
			assertEquals(-1.25, val.getValue(), 0.001 /*delta*/);
		}
		{
			DigitValue val = new DigitValue("-1.25");
			assertTrue(val.isValid());
			assertEquals(-1.25, val.getValue(), 0.001 /*delta*/);
		}
		//with a +
		{
			DigitValue val = new DigitValue("+19.45");
			assertTrue(val.isValid());
			assertEquals(19.45, val.getValue(), 0.001 /*delta*/);
		}
	}
	@Test
	public void testDigitValueStringNonValid()
	{

		{
			DigitValue val = new DigitValue("A12.5");
			assertFalse(val.isValid());
		}
		{
			DigitValue val = new DigitValue("N/A");
			assertFalse(val.isValid());
		}
		{
			DigitValue val = new DigitValue("$1.25");
			assertFalse(val.isValid());
		}
	}
	

}
