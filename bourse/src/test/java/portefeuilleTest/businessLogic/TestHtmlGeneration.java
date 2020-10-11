package portefeuilleTest.businessLogic;


import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.tuxanna.portefeuille.businessLogic.QuoteVariation;
import net.tuxanna.portefeuille.businessLogic.TopWorst5;
import net.tuxanna.portefeuille.businessLogic.eval.Evaluation;
import net.tuxanna.portefeuille.businessLogic.eval.EvaluationStorage;
import net.tuxanna.portefeuille.businessLogic.util.ListOfOperations;
import net.tuxanna.portefeuille.database.SellDB;
import net.tuxanna.portefeuille.database.ShareOrderI;
import net.tuxanna.portefeuille.util.DigitValue;
import net.tuxanna.portefeuille.util.MailNotifier;
import net.tuxanna.portefeuille.util.MailParameters;
import net.tuxanna.portefeuille.util.ReportI;

public class TestHtmlGeneration
{

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
	public void testTopWorst5()
	{
		ArrayList<QuoteVariation> listQuotationVariation=new ArrayList<QuoteVariation>();

		{
			QuoteVariation var1=new QuoteVariation("var1",100.00,90.99);
			listQuotationVariation.add(var1);
		}
		{
			QuoteVariation var1=new QuoteVariation("var2",10.00,9.99);		
			listQuotationVariation.add(var1);
		}
		{
			QuoteVariation var1=new QuoteVariation("var3",110.00,100.99);		
			listQuotationVariation.add(var1);
		}
		{
			QuoteVariation var1=new QuoteVariation("var4",210.00,200.99);		
			listQuotationVariation.add(var1);
		}
		{
			QuoteVariation var1=new QuoteVariation("var5",310.00,300.99);		
			listQuotationVariation.add(var1);
		}
		{
			QuoteVariation var1=new QuoteVariation("var6",400.00,410.99);		
			listQuotationVariation.add(var1);
		}
		{
			QuoteVariation var1=new QuoteVariation("var7",500.00,500.99);		
			listQuotationVariation.add(var1);
		}
		{
			QuoteVariation var1=new QuoteVariation("var8",610.00,610.99);		
			listQuotationVariation.add(var1);
		}
		{
			QuoteVariation var1=new QuoteVariation("var9",710.00,710.99);		
			listQuotationVariation.add(var1);
		}
		{
			QuoteVariation var1=new QuoteVariation("var10",810.00,800.99);		
			listQuotationVariation.add(var1);
		}

		TopWorst5 topWorst=new TopWorst5(listQuotationVariation);
		topWorst.setTitle("tst title");
		String htmlStr=topWorst.toHtml();
		// TODO check the output instead of sending mail

		MailParameters mailParam=new MailParameters();
		MailNotifier mail=new MailNotifier(mailParam);
		ArrayList<ReportI> report=new ArrayList<ReportI>();
		report.add(topWorst);

		TopWorst5 topWorst2=new TopWorst5(listQuotationVariation);
		topWorst2.setTitle("tst title2");
		report.add(topWorst2);

		//mail.notifyUser(report);
	}

	@Test
	public void testEvaluation()
	{
		EvaluationStorage evalStorage=new EvaluationStorage();
		{
			Evaluation evaluation=new Evaluation();
			evaluation.setBoughtPrice(1.0);
			evaluation.setCurrentPrice(2.0);
			evaluation.setGain(3.0);
			evaluation.setGainInPercent(4.4);
			evaluation.setIdShare(0);
			evaluation.setQte(5);
			evaluation.setShareName("share1");
			evaluation.setVariationInCurrency(6.7);
			evaluation.setVariationInPercent(7.8);
			evaluation.setYesterdayPrice(8.9);

			evalStorage.add(evaluation);
		}

		{
			Evaluation evaluation=new Evaluation();
			evaluation.setBoughtPrice(10.2);
			evaluation.setCurrentPrice(20.30);
			evaluation.setGain(30.40);
			evaluation.setGainInPercent(40.5);
			evaluation.setIdShare(1);
			evaluation.setQte(50);
			evaluation.setShareName("share2");
			evaluation.setVariationInCurrency(60.7);
			evaluation.setVariationInPercent(70.8);
			evaluation.setYesterdayPrice(80.9);

			evalStorage.add(evaluation);
		}
		{
			Evaluation evaluation=new Evaluation();
			evaluation.setBoughtPrice(10.2);
			evaluation.setCurrentPrice(20.30);
			evaluation.setGain(-40.50);
			evaluation.setGainInPercent(40.5);
			evaluation.setIdShare(1);
			evaluation.setQte(50);
			evaluation.setShareName("first share in alphabetical");
			evaluation.setVariationInCurrency(60.7);
			evaluation.setVariationInPercent(70.8);
			evaluation.setYesterdayPrice(80.9);

			evalStorage.add(evaluation);
		}


		// TODO check the output instead of sending mail
		//MailNotifier mail=new MailNotifier();
		ArrayList<ReportI> report=new ArrayList<ReportI>();
		report.add(evalStorage);


		//mail.notifyUser(report);
	}

	@Test
	public void testPorfolio()
	{
		EvaluationStorage evalStorage=new EvaluationStorage();
		{	
			Evaluation ev1=new Evaluation();
			ev1.setBoughtPrice(1.0);
			ev1.setCurrentPrice(1.25);
			ev1.setIdShare(-1);
			ev1.setQte(100.0);
			ev1.setShareName("EV1");
			ev1.setYesterdayPrice(1.15);
			ev1.computeVariation();
			evalStorage.add(ev1);
		}
		{
			Evaluation ev5=new Evaluation();
			ev5.setBoughtPrice(50.0);
			ev5.setCurrentPrice(5.25);
			ev5.setIdShare(-1);
			ev5.setQte(500.0);
			ev5.setShareName("EV5");
			ev5.setYesterdayPrice(5.55);
			ev5.computeVariation();
			evalStorage.add(ev5);
		}
		{
			Evaluation ev2=new Evaluation();
			ev2.setBoughtPrice(2.0);
			ev2.setCurrentPrice(2.25);
			ev2.setIdShare(-1);
			ev2.setQte(20.0);
			ev2.setShareName("EV2");
			ev2.setYesterdayPrice(2.15);
			ev2.computeVariation();
			evalStorage.add(ev2);
		}
		{
			Evaluation ev3=new Evaluation();
			ev3.setBoughtPrice(3.0);
			ev3.setCurrentPrice(3.25);
			ev3.setIdShare(-1);
			ev3.setQte(300.0);
			ev3.setShareName("EV3");
			ev3.setYesterdayPrice(3.15);
			ev3.computeVariation();
			evalStorage.add(ev3);
		}
		{
			Evaluation ev4=new Evaluation();
			ev4.setBoughtPrice(4.0);
			ev4.setCurrentPrice(3.45);
			ev4.setIdShare(-1);
			ev4.setQte(400.0);
			ev4.setShareName("EV4");
			ev4.setYesterdayPrice(3.15);
			ev4.computeVariation();
			evalStorage.add(ev4);
		}


		String htmlOutput=evalStorage.toHtml();
		// TODO check it
		int i=0;
	}


	@Test
	public void testSellHtmlOutput()
	{
		ListOfOperations listSoldOrdersExecuted=new ListOfOperations("sell");//for the notification by mail
		{	
			SellDB sell1=new SellDB();

			sell1.setIdSell(1);
			sell1.setQte(100.);
			sell1.setUnitPriceSold(new DigitValue(1.01));
			sell1.setState(ShareOrderI.STATE_ORDER.EXECUTED);

			listSoldOrdersExecuted.add("a sell1", sell1);
		}
		{	
			SellDB sell2=new SellDB();

			sell2.setIdSell(2);
			sell2.setQte(200.);
			sell2.setUnitPriceSold(new DigitValue(2.02));
			sell2.setState(ShareOrderI.STATE_ORDER.EXECUTED);

			listSoldOrdersExecuted.add("z sell2", sell2);
		}

		{	
			SellDB sell3=new SellDB();

			sell3.setIdSell(3);
			sell3.setQte(300.);
			sell3.setUnitPriceSold(new DigitValue(3.03));
			sell3.setState(ShareOrderI.STATE_ORDER.EXECUTED);

			listSoldOrdersExecuted.add("a sell3", sell3);
		}
		String htmlOutput=listSoldOrdersExecuted.toHtml();
		// TODO check it
		int i=0;
	}
}
