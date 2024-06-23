package dataFeed.boursorama;

import static org.junit.Assert.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import net.tuxanna.portefeuille.Quote;
import net.tuxanna.portefeuille.dataFeed.Ticker;
import net.tuxanna.portefeuille.dataFeed.TickerI;
import net.tuxanna.portefeuille.data_feed.boursorama.BoursoramaParser;
import net.tuxanna.portefeuille.data_feed.boursorama.BoursoramaQuotationProvider;

public class BoursoramaParserTest
{
	@Test
	public void testParseShare()
	{
		BoursoramaParser parser=new BoursoramaParser();
		
		TestFileJsoupDocProvider jsoupProvider=new TestFileJsoupDocProvider();
		jsoupProvider.setFileName("TEST_DATA/DATA_FEED/bourso_v3_ALLIANZ Cours Action ALV, Cotation Bourse XETRA - Boursorama.html"
				+ "");
		parser.setDocProvider(jsoupProvider);
		
		Quote quote=new Quote();
		assertTrue(parser.parse(true,quote));
		assertEquals(193.04, quote.getLastTradedPrice().getValue(),0.001);
		assertEquals(1326239, quote.getVolume().getValue(),0.001);
		assertEquals(0.0, quote.getOpenPrice().getValue(),0.001);
		assertEquals(0.0, quote.getHighPrice().getValue(),0.001);
		assertEquals(192.660, quote.getLowPrice().getValue(),0.001);
		
		//TODO check non filled values
		
	}
	@Test
	public void testParseShare4()
	{
		BoursoramaParser parser=new BoursoramaParser();
		
		TestFileJsoupDocProvider jsoupProvider=new TestFileJsoupDocProvider();
		jsoupProvider.setFileName("TEST_DATA/DATA_FEED/bourso_v4_AIRBUS Cours Action AIR, Cotation Bourse Euronext Paris - Boursorama.html"
				+ "");
		parser.setDocProvider(jsoupProvider);
		
		Quote quote=new Quote();
		assertTrue(parser.parse(true,quote));
		assertEquals(148.58, quote.getLastTradedPrice().getValue(),0.001);
		assertEquals(1930361, quote.getVolume().getValue(),0.001);
		assertEquals(148.88, quote.getOpenPrice().getValue(),0.001);
		assertEquals(149.10, quote.getHighPrice().getValue(),0.001);
		assertEquals(147.54, quote.getLowPrice().getValue(),0.001);
		assertEquals(148.760, quote.getPreviousClose().getValue(),0.001);
		//TODO check non filled values
		
	}
	
	@Test
	public void testParseShare2()
	{
		BoursoramaParser parser=new BoursoramaParser();
		
		TestFileJsoupDocProvider jsoupProvider=new TestFileJsoupDocProvider();
		jsoupProvider.setFileName("TEST_DATA/DATA_FEED/bourso_v2_SCHNEIDER EL Cours Action SU, Cotation Bourse Euronext Paris - Boursorama.html");
		parser.setDocProvider(jsoupProvider);
		
		Quote quote=new Quote();
		assertTrue(parser.parse(true,quote));
		assertEquals(71.34, quote.getLastTradedPrice().getValue(),0.001);
		assertEquals(0, quote.getVolume().getValue(),0.001);
		assertEquals(0, quote.getOpenPrice().getValue(),0.001);
		assertEquals(0, quote.getHighPrice().getValue(),0.001);
		assertEquals(0, quote.getLowPrice().getValue(),0.001);
		assertEquals(70.34, quote.getPreviousClose().getValue(),0.001);
		
		//TODO check non filled values
	}
	
	@Test
	public void testParseUsShare()
	{
		BoursoramaParser parser=new BoursoramaParser();
		
		TestFileJsoupDocProvider jsoupProvider=new TestFileJsoupDocProvider();
		jsoupProvider.setFileName("TEST_DATA/DATA_FEED/bourso_v2_AMAZON.COM Cours Action AMZN, Cotation Bourse NASDAQ - Boursorama.html");
		parser.setDocProvider(jsoupProvider);
		
		Quote quote=new Quote();
		assertTrue(parser.parse(true,quote));
		assertEquals(1495.560, quote.getLastTradedPrice().getValue(),0.001);
		assertEquals(7925357, quote.getVolume().getValue(),0.001);
		assertEquals(1539.74, quote.getOpenPrice().getValue(),0.001);
		assertEquals(1549.02, quote.getHighPrice().getValue(),0.001);
		assertEquals(1495.36, quote.getLowPrice().getValue(),0.001);
		assertEquals(1544.92, quote.getPreviousClose().getValue(),0.001);
		
		//TODO check non filled values
	}
	@Test
	public void testParseSicav()
	{
		BoursoramaParser parser=new BoursoramaParser();
		
		TestFileJsoupDocProvider jsoupProvider=new TestFileJsoupDocProvider();
		jsoupProvider.setFileName("TEST_DATA/DATA_FEED/bourso_v2_sicav_Comgest Growth Asia USD Acc - IE00BQ3D6V05 - Cours OPCVM - Boursorama.html");
		parser.setDocProvider(jsoupProvider);
		
		Quote quote=new Quote();
		assertTrue(parser.parse(false /* sicav */,quote));
		assertEquals(63.5900, quote.getLastTradedPrice().getValue(),0.001);
		assertEquals(-0.19, quote.getChangeInPrice().getValue(),0.001);
		
		//TODO check non filled values
	}
	
	@Test
	public void testWithRealBoursoramaDataFeed_ShareCase()
	{
		Duration timeOutHttpRequest=Duration.ofSeconds(60);
		BoursoramaQuotationProvider provider=new BoursoramaQuotationProvider(3 /*3 threads*/, timeOutHttpRequest);
		ArrayList<TickerI> listTickers=new ArrayList<TickerI> ();
		listTickers.add(new Ticker("1rPAIR",true)); //airbus
		listTickers.add(new Ticker("AMZN",true)); //amazon
		listTickers.add(new Ticker("1rPENGI",true)); //engie
		provider.setListTickers(listTickers);
		HashMap<TickerI,Quote> quoteFromDataFeed=new HashMap<TickerI,Quote>  ();
		final boolean res=provider.getQuotes(quoteFromDataFeed);
		
		assertTrue(res);
		assertEquals(3, quoteFromDataFeed.size());
		for (TickerI ticker:listTickers)
		{
			Quote quote = quoteFromDataFeed.get(ticker);
			assertNotNull(quote);
			assertTrue(quote.getLastTradedPrice().isValid());
			assertTrue(quote.getVolume().isValid());
			assertTrue(quote.getOpenPrice().isValid());
			assertTrue(quote.getHighPrice().isValid());
			assertTrue(quote.getLowPrice().isValid());
			assertTrue(quote.getPreviousClose().isValid());
			
			System.out.println(quote.toString());
		}
	}
	@Test
	public void testWithRealBoursoramaDataFeed_SicavCase()
	{
		Duration timeOutHttpRequest=Duration.ofSeconds(60);
		BoursoramaQuotationProvider provider=new BoursoramaQuotationProvider(2 /*threads*/,timeOutHttpRequest);
		ArrayList<TickerI> listTickers=new ArrayList<TickerI> ();
		listTickers.add(new Ticker("MP-806536",false)); //sicav Prévoir Perspectives C - FR0007071931
		provider.setListTickers(listTickers);
		HashMap<TickerI,Quote> quoteFromDataFeed=new HashMap<TickerI,Quote>  ();
		final boolean res=provider.getQuotes(quoteFromDataFeed);
		
		assertTrue(res);
		assertEquals(1, quoteFromDataFeed.size());
		for (TickerI ticker:listTickers)
		{
			Quote quote = quoteFromDataFeed.get(ticker);
			assertNotNull(quote);
			assertTrue(quote.getLastTradedPrice().isValid());			
			System.out.println(quote.toString());
		}
	}
}
