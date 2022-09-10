package net.tuxanna.portefeuille.data_feed.boursorama;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import net.tuxanna.portefeuille.Quote;
import net.tuxanna.portefeuille.dataFeed.QuotationProviderI;
import net.tuxanna.portefeuille.dataFeed.TickerAndQuote;
import net.tuxanna.portefeuille.dataFeed.TickerI;
import net.tuxanna.portefeuille.dataFeed.JsoupDocumentProviderFromString;



public class BoursoramaQuotationProvider implements QuotationProviderI
{
	private static final Logger logger = LogManager.getLogger(BoursoramaQuotationProvider.class);

	private static final String BOURSORAMA_SHARE_WEB_PAGE="https://www.boursorama.com/cours/";
	private static final String BOURSORAMA_SICAV_WEB_PAGE="https://www.boursorama.com/bourse/opcvm/cours/";
	private static final String BOURSORAMA_TRACKER_PAGE="https://www.boursorama.com/bourse/trackers/cours/";
	
	private List<TickerI> listOfTickers;
	private int nbThreadsForWebDownload;
	
	public BoursoramaQuotationProvider(int nbThreads)
	{
		nbThreadsForWebDownload=nbThreads;
	}

	@Override
	public void setListTickers(List<TickerI> list)
	{
		listOfTickers=list;
		logger.entry(list.size());
	}
	private void downloadWebPages( Map<TickerI,Quote> quoteForTicker) throws InterruptedException, ExecutionException
	{
		ExecutorService executorService = Executors.newFixedThreadPool(nbThreadsForWebDownload);
		HttpClient httpClient = HttpClient.newBuilder()
				.executor(executorService)
				.version(HttpClient.Version.HTTP_2)
				.followRedirects(HttpClient.Redirect.NORMAL)
				.connectTimeout(Duration.ofSeconds(50))
				.build();

		List<CompletableFuture<TickerAndQuote>> result = listOfTickers.stream()
				.map(url -> {
					return httpClient.sendAsync(
							HttpRequest.newBuilder(buildUriFromTickers(url))
							.GET()
							//.setHeader("User-Agent", "Java 11 HttpClient Bot")
							.build(),
							HttpResponse.BodyHandlers.ofString())
							.thenApply(response -> parse(url,response.body()));
				})
				.collect(Collectors.toList());
		
		for (CompletableFuture<TickerAndQuote> future : result) 
		{
			TickerAndQuote quoteForOneTicker = future.get();
			if (quoteForOneTicker.isValid())
			{
				quoteForTicker.put(quoteForOneTicker.getTicker(), quoteForOneTicker.getQuote());
			}
			else
			{
				logger.debug("invalid quote skipped ticker={}",quoteForOneTicker.getTicker());
			}
		}
		executorService.shutdown();
	}
	
	private TickerAndQuote parse(TickerI ticker,String htmlBody)
	{
		BoursoramaParser parser=new BoursoramaParser();
		parser.setDocProvider(new JsoupDocumentProviderFromString(htmlBody));
		Quote quote=new Quote();

		if (parser.parse(ticker.getTypeOfItem(),quote))
		{
			return new TickerAndQuote(ticker,quote);
		}
		else
		{
			logger.error("problem with symbol {}",ticker);
			return new TickerAndQuote();
		}
		
	}

	private URI buildUriFromTickers(TickerI ticker ) 
	{
		logger.debug("get quotes for:{}",ticker.getSymbol());

		String url;	

		switch (ticker.getTypeOfItem())
		{
		case SHARE:
			//make something like https://www.boursorama.com/cours/2aRO/
			url=BOURSORAMA_SHARE_WEB_PAGE+ticker.getSymbol()+"/";
			break;
		case SICAV:
			//make something like  https://www.boursorama.com/bourse/opcvm/cours/0P0000WXIZ/
			url=BOURSORAMA_SICAV_WEB_PAGE+ticker.getSymbol()+"/";
			break;
		case TRACKER:
			url=BOURSORAMA_TRACKER_PAGE+ticker.getSymbol()+"/";
			break;
		default:
			return null;
		}
		try
		{
			return (new URI(url));
		}
		catch (URISyntaxException e)
		{
			logger.error(url,e);
			return null;//TODO what shall we return ?
		}
	}

	@Override
	public boolean getQuotes(Map<TickerI,Quote> quoteForTicker)
	{
		
		try
		{
			downloadWebPages( quoteForTicker);
		}
		catch (InterruptedException | ExecutionException e)
		{
			logger.error("getQuotes exception received {}",e.toString());
			return false;
		}
		logger.debug("got {} quotations for {} tickers",quoteForTicker.size(),listOfTickers.size());
		return true;
	
	}

}
