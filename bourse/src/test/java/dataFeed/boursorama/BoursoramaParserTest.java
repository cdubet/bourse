package dataFeed.boursorama;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import net.tuxanna.portefeuille.Quote;
import net.tuxanna.portefeuille.dataFeed.Ticker;
import net.tuxanna.portefeuille.dataFeed.TickerI;
import net.tuxanna.portefeuille.dataFeed.TickerI.TypeOfItem;
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
		assertTrue(parser.parse(TypeOfItem.SHARE,quote));
		assertEquals(193.04, quote.getLastTradedPrice().getValue(),0.001);
		assertEquals(1326239, quote.getVolume().getValue(),0.001);
		assertEquals(0.0, quote.getOpenPrice().getValue(),0.001);
		assertEquals(0.0, quote.getHighPrice().getValue(),0.001);
		assertEquals(192.660, quote.getLowPrice().getValue(),0.001);
		
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
		assertTrue(parser.parse(TypeOfItem.SHARE,quote));
		assertEquals(71.34, quote.getLastTradedPrice().getValue(),0.001);
		assertEquals(0, quote.getVolume().getValue(),0.001);
		assertEquals(0, quote.getOpenPrice().getValue(),0.001);
		assertEquals(0, quote.getHighPrice().getValue(),0.001);
		assertEquals(0, quote.getLowPrice().getValue(),0.001);
		
		
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
		assertTrue(parser.parse(TypeOfItem.SHARE,quote));
		assertEquals(1495.560, quote.getLastTradedPrice().getValue(),0.001);
		assertEquals(7925357, quote.getVolume().getValue(),0.001);
		assertEquals(1539.74, quote.getOpenPrice().getValue(),0.001);
		assertEquals(1549.02, quote.getHighPrice().getValue(),0.001);
		assertEquals(1495.36, quote.getLowPrice().getValue(),0.001);
		
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
		assertTrue(parser.parse(TypeOfItem.SICAV,quote));
		assertEquals(63.5900, quote.getLastTradedPrice().getValue(),0.001);
		
		//TODO check non filled values
	}
	@Test
	public void testParseTracker()
	{
		BoursoramaParser parser=new BoursoramaParser();
		
		TestFileJsoupDocProvider jsoupProvider=new TestFileJsoupDocProvider();
		jsoupProvider.setFileName("TEST_DATA/DATA_FEED/AMUNDI MSCI EM ASIA UCITS ETF - USD - USD, Cours Tracker AASU, Cotation Bourse Euronext Paris - Boursorama.html");
		parser.setDocProvider(jsoupProvider);
		
		Quote quote=new Quote();
		assertTrue(parser.parse(TypeOfItem.TRACKER,quote));
		assertEquals(42.4379, quote.getLastTradedPrice().getValue(),0.001);
		
		//TODO check non filled values
	}
	@Test
	public void testWithRealBoursoramaDataFeed_ShareCase()
	{
		BoursoramaQuotationProvider provider=new BoursoramaQuotationProvider(3 /*3 threads*/);
		ArrayList<TickerI> listTickers=new ArrayList<TickerI> ();
		listTickers.add(new Ticker("1rPILD",TypeOfItem.SHARE)); //iliad
		listTickers.add(new Ticker("AMZN",TypeOfItem.SHARE)); //amazon
		listTickers.add(new Ticker("VMW",TypeOfItem.SHARE)); //vm ware
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
			
			System.out.println(quote.toString());
		}
	}
	@Test
	public void testWithRealBoursoramaDataFeed_SicavCase()
	{
		BoursoramaQuotationProvider provider=new BoursoramaQuotationProvider(2 /*threads*/);
		ArrayList<TickerI> listTickers=new ArrayList<TickerI> ();
		listTickers.add(new Ticker("MP-806536",TypeOfItem.SICAV)); //sicav Prévoir Perspectives C - FR0007071931
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
