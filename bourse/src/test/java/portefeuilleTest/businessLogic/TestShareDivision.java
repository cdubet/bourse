package portefeuilleTest.businessLogic;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import net.tuxanna.portefeuille.Quote;
import net.tuxanna.portefeuille.business_logic.PortfolioManagement;
import net.tuxanna.portefeuille.dataFeed.TickerI.TypeOfItem;
import net.tuxanna.portefeuille.database.PortfolioDB;
import net.tuxanna.portefeuille.database.QuoteDB;
import net.tuxanna.portefeuille.database.ShareDB;
import net.tuxanna.portefeuille.database.ShareDB.Currency;
import net.tuxanna.portefeuille.util.DigitValue;

public class TestShareDivision
{
	private static final int SHARE_ID=10;
	private static final String SHARE_NAME="name does not matter";
	private static final String SHARE_TICKER="ticker does not matter";
	private static final Currency CURRENCY=ShareDB.Currency.ENGLISH_POUND;
	private static final double FAKE_QUOTATION_1=125.9;
	private static final double FAKE_VOLUME_1=10000;
	
	private static final double FAKE_QUOTATION_2=25.9;
	private static final double FAKE_VOLUME_2=27700;
	
	private static final double FAKE_QUOTATION_3=35.9;
	private static final double FAKE_VOLUME_3=37700;
	
	private static final double QTE_PORTFOLIO_1 = 500.;
	private static final double UNIT_PRICE_1 = 45.4;
	
	private static final double QTE_PORTFOLIO_2 = 14.;
	private static final double UNIT_PRICE_2 = 4.9;
	private static final double DIVIDE_SHARE_RATIO=10.;
	
	private void fillFakeQuotationOriginal(List<QuoteDB> listQuoteOriginal)
	{
		{
			Quote quotation1=buildFakeQuote(FAKE_QUOTATION_1, FAKE_VOLUME_1);
			QuoteDB quote1=new QuoteDB(SHARE_ID,quotation1);
			listQuoteOriginal.add(quote1);
		}
		{
			Quote quotation2=buildFakeQuote(FAKE_QUOTATION_2
					, FAKE_VOLUME_2);
			QuoteDB quote2=new QuoteDB(SHARE_ID,quotation2);
			listQuoteOriginal.add(quote2);
		}
		{
			Quote quotation3=buildFakeQuote(FAKE_QUOTATION_3
					, FAKE_VOLUME_3);
			QuoteDB quote3=new QuoteDB(SHARE_ID,quotation3);
			listQuoteOriginal.add(quote3);
		}		
	}
	private void fillFakeQuotationExpected(List<QuoteDB> listQuoteExpected)
	{
		{
			Quote quotation1=buildFakeQuote(FAKE_QUOTATION_1/DIVIDE_SHARE_RATIO,FAKE_VOLUME_1*DIVIDE_SHARE_RATIO);

			QuoteDB quote1=new QuoteDB(SHARE_ID,quotation1);
			listQuoteExpected.add(quote1);
		}
		{
			Quote quotation2=buildFakeQuote(FAKE_QUOTATION_2/DIVIDE_SHARE_RATIO
					, FAKE_VOLUME_2*DIVIDE_SHARE_RATIO);
			QuoteDB quote2=new QuoteDB(SHARE_ID,quotation2);
			listQuoteExpected.add(quote2);
		}
		{
			Quote quotation3=buildFakeQuote(FAKE_QUOTATION_3/DIVIDE_SHARE_RATIO
					, FAKE_VOLUME_3*DIVIDE_SHARE_RATIO);
			QuoteDB quote3=new QuoteDB(SHARE_ID,quotation3);
			listQuoteExpected.add(quote3);
		}		
	}
	
	private Quote buildFakeQuote(double fakeQuoteValue,  double fakeVolume)
	{
		Quote newQuote=new Quote();
		newQuote.setLastTradedPrice(fakeQuoteValue);
		newQuote.setHighPrice(fakeQuoteValue*15.6);

		newQuote.setLowPrice(fakeQuoteValue*0.61);
		newQuote.setOpenPrice(fakeQuoteValue*1.078);
		newQuote.setVolume(fakeVolume);
		return newQuote;
	}	
	
	private void fillPortfolio(List<PortfolioDB> listPorfolioOriginal)
	{
		{
			PortfolioDB portfolio1=new PortfolioDB();
			portfolio1.setIdShare(SHARE_ID);
			portfolio1.setQte(new DigitValue(QTE_PORTFOLIO_1));
			portfolio1.setUnitPrice(new DigitValue(UNIT_PRICE_1));
			listPorfolioOriginal.add(portfolio1);
		}
		{
			PortfolioDB portfolio2=new PortfolioDB();
			portfolio2.setIdShare(SHARE_ID);
			portfolio2.setQte(new DigitValue(QTE_PORTFOLIO_2));
			portfolio2.setUnitPrice(new DigitValue(UNIT_PRICE_2));
			listPorfolioOriginal.add(portfolio2);
		}
	}
	private void fillPortfolioExpected(List<PortfolioDB> listPorfolio)
	{
		{
			PortfolioDB portfolio1=new PortfolioDB();
			portfolio1.setIdShare(SHARE_ID);
			portfolio1.setQte(new DigitValue(QTE_PORTFOLIO_1*DIVIDE_SHARE_RATIO));
			portfolio1.setUnitPrice(new DigitValue(UNIT_PRICE_1/DIVIDE_SHARE_RATIO));
			listPorfolio.add(portfolio1);
		}
		{
			PortfolioDB portfolio2=new PortfolioDB();
			portfolio2.setIdShare(SHARE_ID);
			portfolio2.setQte(new DigitValue(QTE_PORTFOLIO_2*DIVIDE_SHARE_RATIO));
			portfolio2.setUnitPrice(new DigitValue(UNIT_PRICE_2/DIVIDE_SHARE_RATIO));
			listPorfolio.add(portfolio2);
		}
	}
	@Test
	public void testExistingShareDivision()
	{

		//test set up
		PortfolioManagement portfolio=new PortfolioManagement();
		
		ShareDB share=new ShareDB(SHARE_ID,SHARE_NAME , SHARE_TICKER ,CURRENCY ,TypeOfItem.SHARE);
		List<PortfolioDB> listPorfolioOriginal=new ArrayList<>();
		List<QuoteDB> listQuoteOriginal=new ArrayList<>();
		fillFakeQuotationOriginal(listQuoteOriginal);
		
		fillPortfolio(listPorfolioOriginal);
		
		ShareDivisionMockDataProvider mockData=new ShareDivisionMockDataProvider(share, listQuoteOriginal, listPorfolioOriginal);
		portfolio.setDatabase(mockData);
		

		Date dateSplit=null;//does not matter here
		//act
		boolean result= portfolio.divideShare(SHARE_NAME, dateSplit, DIVIDE_SHARE_RATIO);
		
		//check
		assertTrue(result);
		List<PortfolioDB> listPortfolioExpected =new ArrayList<>();
		List<QuoteDB> listQuoteExpected=new ArrayList<>();

		fillFakeQuotationExpected(listQuoteExpected);
		fillPortfolioExpected(listPortfolioExpected);
		assertArrayEquals(listQuoteExpected.toArray(),mockData.getListQuoteAfterProcessing().toArray());
		assertArrayEquals(listPortfolioExpected.toArray(),mockData.getListPortofolioAfterProcessing().toArray());
	}

	@Test
	public void testNonExistingShareDivision()
	{

		//test set up
		PortfolioManagement portfolio=new PortfolioManagement();
		
		ShareDB share=null;
		List<PortfolioDB> listPorfolioOriginal=new ArrayList<>();
		List<QuoteDB> listQuoteOriginal=new ArrayList<>();
		
		ShareDivisionMockDataProvider mockData=new ShareDivisionMockDataProvider(share, listQuoteOriginal, listPorfolioOriginal);
		portfolio.setDatabase(mockData);
		

		Date dateSplit=null;//does not matter here
		//act
		boolean result= portfolio.divideShare(SHARE_NAME, dateSplit, DIVIDE_SHARE_RATIO);
		
		//check
		assertFalse(result);
	}
	
	@Test
	public void testExistingShareNotInPortfolioDivision()
	{

		//test set up
		PortfolioManagement portfolio=new PortfolioManagement();
		
		ShareDB share=new ShareDB(SHARE_ID,SHARE_NAME , SHARE_TICKER ,CURRENCY ,TypeOfItem.SHARE);
		List<PortfolioDB> listPorfolioOriginal=new ArrayList<>();
		List<QuoteDB> listQuoteOriginal=new ArrayList<>();
		fillFakeQuotationOriginal(listQuoteOriginal);
		
		
		ShareDivisionMockDataProvider mockData=new ShareDivisionMockDataProvider(share, listQuoteOriginal, listPorfolioOriginal);
		portfolio.setDatabase(mockData);
		

		Date dateSplit=null;//does not matter here
		//act
		boolean result= portfolio.divideShare(SHARE_NAME, dateSplit, DIVIDE_SHARE_RATIO);
		
		//check
		assertTrue(result);
		List<PortfolioDB> listPortfolioExpected =new ArrayList<>();
		List<QuoteDB> listQuoteExpected=new ArrayList<>();

		fillFakeQuotationExpected(listQuoteExpected);

		assertArrayEquals(listQuoteExpected.toArray(),mockData.getListQuoteAfterProcessing().toArray());
		assertArrayEquals(listPortfolioExpected.toArray(),mockData.getListPortofolioAfterProcessing().toArray());

	}
	@Test
	public void testExistingShareNoQuotationAvailableDivision()
	{

		//test set up
		PortfolioManagement portfolio=new PortfolioManagement();
		
		ShareDB share=new ShareDB(SHARE_ID,SHARE_NAME , SHARE_TICKER ,CURRENCY ,TypeOfItem.SHARE);
		List<PortfolioDB> listPorfolioOriginal=new ArrayList<>();
		List<QuoteDB> listQuoteOriginal=new ArrayList<>();	
		
		ShareDivisionMockDataProvider mockData=new ShareDivisionMockDataProvider(share, listQuoteOriginal, listPorfolioOriginal);
		portfolio.setDatabase(mockData);
		

		Date dateSplit=null;//does not matter here
		//act
		boolean result= portfolio.divideShare(SHARE_NAME, dateSplit, DIVIDE_SHARE_RATIO);
		
		//check
		assertTrue(result);
		List<PortfolioDB> listPortfolioExpected =new ArrayList<>();
		List<QuoteDB> listQuoteExpected=new ArrayList<>();

		assertArrayEquals(listQuoteExpected.toArray(),mockData.getListQuoteAfterProcessing().toArray());
		assertArrayEquals(listPortfolioExpected.toArray(),mockData.getListPortofolioAfterProcessing().toArray());

	}
}
