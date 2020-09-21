package portefeuilleTest.businessLogic.eval;

import static org.junit.Assert.*;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.tuxanna.portefeuille.businessLogic.PortfolioManagement;
import net.tuxanna.portefeuille.businessLogic.eval.Evaluation;
import net.tuxanna.portefeuille.businessLogic.eval.EvaluationStorage;
import net.tuxanna.portefeuille.businessLogic.eval.QuotationMapStorage;
import net.tuxanna.portefeuille.businessLogic.util.ShareNameStorage;
import net.tuxanna.portefeuille.database.PortfolioDB;
import net.tuxanna.portefeuille.util.DigitValue;
import portefeuilleTest.businessLogic.FakeDate;
import portefeuilleTest.businessLogic.PredefinedQuotationProvider;
import portefeuilleTest.database.TestRamDatabaseAtOneDay;

public class TestEvaluation
{

	private static final int ID_SHARE_1 = 0;
	private static final double PRICE_SHARE_1 = 25.;
	private static final double QTE_SHARE_1 = 100.;
	private static final DigitValue QTE_1 = new DigitValue(QTE_SHARE_1);
	private static final DigitValue UNIT_PRICE_1 = new DigitValue(PRICE_SHARE_1);
	
	private static final int ID_SHARE_2 = 1;
	private static final double PRICE_SHARE_2 = 11.;
	private static final double QTE_SHARE_2 = 10.;
	private static final DigitValue QTE_2 = new DigitValue(QTE_SHARE_2);
	private static final DigitValue UNIT_PRICE_2 = new DigitValue(PRICE_SHARE_2);

	private static final int ID_SHARE_3 = 2;
	private static final double PRICE_SHARE_3 = 33.3;
	private static final double QTE_SHARE_3 = 300.;
	private static final DigitValue QTE_3 = new DigitValue(QTE_SHARE_3);
	private static final DigitValue UNIT_PRICE_3 = new DigitValue(PRICE_SHARE_3);

	private static final int ID_SHARE_SAME_AS_1 = 0;
	private static final double PRICE_SHARE_SAME_AS_1 = 50.;
	private static final double QTE_SHARE_SAME_AS_1 = 200.;
	private static final DigitValue QTE_SAME_AS_1 = new DigitValue(QTE_SHARE_SAME_AS_1);
	private static final DigitValue UNIT_PRICE_SAME_AS_1 = new DigitValue(PRICE_SHARE_SAME_AS_1);
	
	private static final int ID_SHARE_SAME_AS_3 = 2;
	private static final double PRICE_SHARE_SAME_AS_3 = 45.;
	private static final double QTE_SHARE_SAME_AS_3 = 15.;
	private static final DigitValue QTE_SAME_AS_3 = new DigitValue(QTE_SHARE_SAME_AS_3);
	private static final DigitValue UNIT_PRICE_SAME_AS_3 = new DigitValue(PRICE_SHARE_SAME_AS_3);

	private static final int ID_SHARE_4 = 4;
	private static final double PRICE_SHARE_4 = 33.3;
	private static final double QTE_SHARE_4 = 300.;
	private static final DigitValue QTE_4 = new DigitValue(QTE_SHARE_4);
	private static final DigitValue UNIT_PRICE_4 = new DigitValue(PRICE_SHARE_4);
	
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
	public void testEvaluate()
	{
		PortfolioManagement portfolio=new PortfolioManagement();
		FakeDatabase fakeDB = new FakeDatabase();
		portfolio.setDatabase(fakeDB);
		
		FakeDate clock =new FakeDate();
		Date dateOfYesterday=clock.getYesterday();
		Date now=clock.getNow();
		portfolio.setClock(clock);
		
		QuotationMapStorage mapToFill=new QuotationMapStorage();
		ShareNameStorage mapShareToFill=new ShareNameStorage();
		
		fakeDB.fillQuotationMapStorage(mapToFill,now,dateOfYesterday,mapShareToFill);
		portfolio.setQuotationMapStorage(mapToFill);
		portfolio.setShareNameStorage(mapShareToFill);
		
		
		//test fct
		portfolio.evaluate();
		
		//check result;
		EvaluationStorage result = portfolio.getEvaluationStorage();

		//check contents
		assertEquals(result.size(),4);
		
		EvaluationStorage expectedEval=new EvaluationStorage();
		fakeDB.fillExpectedEvaluation( expectedEval);
		assertEquals(result,expectedEval);

	}
	
	@Test
	public void testComputeVariation()
	{
		Evaluation ev1=new Evaluation();
		ev1.setBoughtPrice(1.0);
		ev1.setCurrentPrice(1.25);
		ev1.setYesterdayPrice(1.15);
		ev1.setQte(200.0);
		ev1.computeVariation();

		//check 
		assertEquals(0.25*200., ev1.getGain(), 0.001 /*delta*/);
		assertEquals((1.25-1.15)*200., ev1.getVariationInCurrency(), 0.001 /*delta*/);
		assertEquals(25.0, ev1.getGainInPercent(), 0.001 /*delta*/);
		assertEquals((1.25-1.15)*100/1.15, ev1.getVariationInPercent(), 0.001 /*delta*/);
	}
	@Test
	public void testMergeShareOwned()
	{
		PortfolioManagement portfolioManagement=new PortfolioManagement();
		ArrayList<PortfolioDB> testList=new ArrayList<PortfolioDB>();
		
		//add test items with 2 shares being in 2 different accounts (see SAME_AS_1 and SAME_AS_3)
		//shares should be sorted by ID like the one given by database class
		{
			PortfolioDB item=new PortfolioDB();
			item.setIdShare(ID_SHARE_1);
			item.setQte(QTE_1);
			item.setUnitPrice(UNIT_PRICE_1);
			testList.add(item);
		}
		{
			PortfolioDB item=new PortfolioDB();
			item.setIdShare(ID_SHARE_SAME_AS_1);
			item.setQte(QTE_SAME_AS_1);
			item.setUnitPrice(UNIT_PRICE_SAME_AS_1);
			testList.add(item);
		}
		{
			PortfolioDB item=new PortfolioDB();
			item.setIdShare(ID_SHARE_2);
			item.setQte(QTE_2);
			item.setUnitPrice(UNIT_PRICE_2);
			testList.add(item);
		}
		{
			PortfolioDB item=new PortfolioDB();
			item.setIdShare(ID_SHARE_3);
			item.setQte(QTE_3);
			item.setUnitPrice(UNIT_PRICE_3);
			testList.add(item);
		}
		{
			PortfolioDB item=new PortfolioDB();
			item.setIdShare(ID_SHARE_SAME_AS_3);
			item.setQte(QTE_SAME_AS_3);
			item.setUnitPrice(UNIT_PRICE_SAME_AS_3);
			testList.add(item);
		}
		{
			PortfolioDB item=new PortfolioDB();
			item.setIdShare(ID_SHARE_4);
			item.setQte(QTE_4);
			item.setUnitPrice(UNIT_PRICE_4);
			testList.add(item);
		}

		portfolioManagement.mergeShareOwned(testList);
		//check the result -> 2 common shares are merged
		assertEquals(4, testList.size());
		
		int idx=0;
		assertEquals(ID_SHARE_1, testList.get(idx).getIdShare());
		assertEquals(QTE_SHARE_1+QTE_SHARE_SAME_AS_1, testList.get(idx).getQte().getValue(),0.0001);
		assertEquals((QTE_SHARE_1*PRICE_SHARE_1+QTE_SHARE_SAME_AS_1*PRICE_SHARE_SAME_AS_1)/(QTE_SHARE_1+QTE_SHARE_SAME_AS_1),
				testList.get(idx).getUnitPrice().getValue(),0.0001);
		
		//second item is unchanged
		idx++;
		assertEquals(ID_SHARE_2, testList.get(idx).getIdShare());
		assertEquals(QTE_SHARE_2, testList.get(idx).getQte().getValue(),0.0001);
		assertEquals(PRICE_SHARE_2,testList.get(idx).getUnitPrice().getValue(),0.0001);
		
		idx++;
		assertEquals(ID_SHARE_3, testList.get(idx).getIdShare());
		assertEquals(QTE_SHARE_3+QTE_SHARE_SAME_AS_3, testList.get(idx).getQte().getValue(),0.0001);
		assertEquals((QTE_SHARE_3*PRICE_SHARE_3+QTE_SHARE_SAME_AS_3*PRICE_SHARE_SAME_AS_3)/(QTE_SHARE_3+QTE_SHARE_SAME_AS_3),
				testList.get(idx).getUnitPrice().getValue(),0.0001);
		
		idx++;
		assertEquals(ID_SHARE_4, testList.get(idx).getIdShare());
		assertEquals(QTE_SHARE_4, testList.get(idx).getQte().getValue(),0.0001);
		assertEquals(PRICE_SHARE_4,testList.get(idx).getUnitPrice().getValue(),0.0001);
		
	}
	

	@Test
	public void systemTest()
	{
		try
		{
			TestRamDatabaseAtOneDay testDb=new TestRamDatabaseAtOneDay("TEST_DATA/portfolio_9_dec_2016.xml");
			assertTrue(testDb.setup());
			
			PortfolioManagement portfolio=new PortfolioManagement();			
			portfolio.setDatabase(testDb);
			
			FakeDate clock =new FakeDate();
			clock.setPredefineYear(2016);
			clock.setPredefineMonth(11);//month start at 0 -> dec=11
			clock.setPredefineDayOfMonth(9);
			clock.setPredefineHourOfDay(23);			
			portfolio.setClock(clock);
		
			PredefinedQuotationProvider quoteProvider=new PredefinedQuotationProvider();
			quoteProvider.setupWithData_9_dec_2016();
			portfolio.setQuotationProvider(quoteProvider);
			
			//now make the test
			assertTrue(portfolio.update());
			portfolio.evaluate();				

			//get the evaluation
			EvaluationStorage eval = portfolio.getEvaluationStorage();
			assertEquals(47, eval.size());
			String htmlReport=eval.toHtml();
			System.out.println(htmlReport);
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
