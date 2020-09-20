package net.tuxanna.portefeuille.dataFeed.boursorama;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.tuxanna.portefeuille.Quote;
import net.tuxanna.portefeuille.dataFeed.QuotationProviderI;
import net.tuxanna.portefeuille.dataFeed.TickerI;
import net.tuxanna.portefeuille.dataFeed.UrlJsoupDocProvider;

public class BoursoramaQuotationProvider implements QuotationProviderI
{
	private static final Logger logger = LogManager.getLogger(BoursoramaQuotationProvider.class);
	
	private static final String boursoramaShareWebPage="https://www.boursorama.com/cours/";
	private static final String boursoramaSicavWebPage="https://www.boursorama.com/bourse/opcvm/cours/";
	
	private List<TickerI> listOfTickers;
	
	public BoursoramaQuotationProvider()
	{
	}

	@Override
	public void setListTickers(List<TickerI> list)
	{
		listOfTickers=list;
		logger.entry(list.size());
	}

	@Override
	public boolean getQuotes(Map<TickerI,Quote> quoteForTicker)
	{
		BoursoramaParser parser=new BoursoramaParser();
		boolean res=true; //be optimist
		
		UrlJsoupDocProvider jsoupProvider=new UrlJsoupDocProvider();
		for (TickerI ticker : listOfTickers)
		{
			logger.debug("get quotes for:["+ticker.getSymbol()+"]");

			String url;
			

			if (ticker.isShare())
			{
				//make something like https://www.boursorama.com/cours/2aRO/
				url=boursoramaShareWebPage+ticker.getSymbol()+"/";
			}
			else
			{
				//make something like  https://www.boursorama.com/bourse/opcvm/cours/0P0000WXIZ/
				url=boursoramaSicavWebPage+ticker.getSymbol()+"/";	
			}
			
			logger.debug("url=["+url.toString()+"]");
			
			jsoupProvider.setUrl(url);
			parser.setDocProvider(jsoupProvider);
			Quote quote=new Quote();
			
			if (parser.parse(ticker.isShare(),quote))
			{
				quoteForTicker.put(ticker, quote);
			}
			else
			{
				logger.error("problem with symbol"+ticker);
				res= false;
			}
		}
		
		logger.debug("got "+quoteForTicker.size()+"quotations for "+listOfTickers.size()+" tickers");
		return res;
	}

}
