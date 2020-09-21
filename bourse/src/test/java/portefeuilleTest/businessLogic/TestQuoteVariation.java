package portefeuilleTest.businessLogic;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.tuxanna.portefeuille.businessLogic.QuoteVariation;

public class TestQuoteVariation
{

	private static final String V_MINUS_102 = "v-10%";
	private static final String V_10 = "v+10%";
	private static final String V_MINUS_5_22 = "v-5.2%";
	private static final String V_5_2 = "v 5.2%";
	private static final String V_1_2 = "v 1.2%";

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
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testCompareTo()
	{
		ArrayList<QuoteVariation> listQuotationVariation=new ArrayList<>();
		{	
			QuoteVariation q1=new QuoteVariation(V_1_2, 100.0, 101.2);
			listQuotationVariation.add(q1);
		}
		{	
			QuoteVariation q1=new QuoteVariation(V_5_2, 10.0, 10.52); //5.2 %
			listQuotationVariation.add(q1);
		}
		{	
			QuoteVariation q1=new QuoteVariation(V_MINUS_5_22, 10.0, 9.48);
			listQuotationVariation.add(q1);
		}	
		{	
			QuoteVariation q1=new QuoteVariation(V_10, 1000.0, 1100.);
			listQuotationVariation.add(q1);
		}
		{	
			QuoteVariation q1=new QuoteVariation(V_MINUS_102, 1000.0, 900.);
			listQuotationVariation.add(q1);
		}
		Collections.sort(listQuotationVariation);
		
		//check it is sorted
		assertEquals(V_10,listQuotationVariation.get(0).getName()); //10 %
		assertEquals(V_5_2,listQuotationVariation.get(1).getName()); //5.2 %
		assertEquals(V_1_2,listQuotationVariation.get(2).getName()); //1.2 %
		assertEquals(V_MINUS_5_22,listQuotationVariation.get(3).getName()); //- 5.2 %
		assertEquals(V_MINUS_102,listQuotationVariation.get(4).getName()); //- 10.2 %
	}

}
