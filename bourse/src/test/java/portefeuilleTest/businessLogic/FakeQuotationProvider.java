package portefeuilleTest.businessLogic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.tuxanna.portefeuille.Quote;
import net.tuxanna.portefeuille.dataFeed.QuotationProviderI;
import net.tuxanna.portefeuille.dataFeed.TickerI;


public class FakeQuotationProvider implements QuotationProviderI
{
	private static final Logger logger = LogManager.getLogger(FakeQuotationProvider.class);
	private List<TickerI> listTicker;
	
	public FakeQuotationProvider()
	{
		listTicker=new ArrayList<TickerI> ();
	}

	@Override
	public void setListTickers(List<TickerI> list)
	{
		listTicker=list;
	}

	public List<TickerI> getListPredefinedTickers()
	{
		//list of tickers we will provide quotes
		ArrayList<TickerI> listTickers=new ArrayList<TickerI>();

		for (int idx=0;idx<12;idx++)
		{
			listTickers.add(new FakeShareTicker(idx));
		}
		return listTickers;
	}
	@Override
	public boolean getQuotes(Map<TickerI,Quote> list)
	{
		for (Iterator<TickerI> tickerItr = listTicker.iterator(); tickerItr.hasNext();)
		{
			TickerI ticker=tickerItr.next();
			String shareTicker = ticker.getSymbol();
			Quote q=new Quote();
			
			int idx=0;
			boolean isQuoteValid=true;
			if (shareTicker.compareTo((new FakeShareTicker(idx++)).getSymbol())==0)
			{
				q.setLastTradedPrice(1.05); //was 1.03 in DB
				q.setOpenPrice(1.08);
				q.setVolume(1000);
			}
			else if (shareTicker.compareTo((new FakeShareTicker(idx++)).getSymbol())==0)
			{
				q.setLastTradedPrice(1.53); //was 2.03 in DB
				q.setOpenPrice(2.00);
				q.setVolume(1000);
			}
			else if (shareTicker.compareTo((new FakeShareTicker(idx++)).getSymbol())==0)
			{
				q.setLastTradedPrice(3.9); //was 3.03 in DB
				q.setOpenPrice(3.40);
				q.setVolume(1000);
			}
			else if (shareTicker.compareTo((new FakeShareTicker(idx++)).getSymbol())==0)
			{
				q.setLastTradedPrice(42.53); //was 40.03 in DB
				q.setOpenPrice(41.00);
				q.setVolume(1000);
			}
			else if (shareTicker.compareTo((new FakeShareTicker(idx++)).getSymbol())==0)
			{
				q.setLastTradedPrice(20.93); //was 25.93 in DB
				q.setOpenPrice(22.00);
				q.setVolume(1000);
			}
			else if (shareTicker.compareTo((new FakeShareTicker(idx++)).getSymbol())==0)
			{
				q.setLastTradedPrice(6.02); //was 5.903 in DB
				q.setOpenPrice(6.00);
				q.setVolume(1000);
			}
			else if (shareTicker.compareTo((new FakeShareTicker(idx++)).getSymbol())==0)
			{
				q.setLastTradedPrice(58.02); //was 60 in DB
				q.setOpenPrice(60.00);
				q.setVolume(1000);
			}
			else if (shareTicker.compareTo((new FakeShareTicker(idx++)).getSymbol())==0)
			{
				q.setLastTradedPrice(69.02); //was 5.903 in DB
				q.setOpenPrice(69.900);
				q.setVolume(1000);
			}
			else if (shareTicker.compareTo((new FakeShareTicker(idx++)).getSymbol())==0)
			{
				q.setLastTradedPrice(75.02); //was 80.903 in DB
				q.setOpenPrice(78.00);
				q.setVolume(1000);
			}
			else if (shareTicker.compareTo((new FakeShareTicker(idx++)).getSymbol())==0)
			{
				q.setLastTradedPrice(10.02); 
				q.setOpenPrice(10.00);
				q.setVolume(1000);
			}
			else if (shareTicker.compareTo((new FakeShareTicker(idx++)).getSymbol())==0)
			{
				q.setLastTradedPrice(16.2); //was 15.903 in DB
				q.setOpenPrice(16.03);
				q.setVolume(1000);
			}
			else if (shareTicker.compareTo((new FakeShareTicker(idx++)).getSymbol())==0)
			{
				q.setLastTradedPrice(12.302); //was 11.903 in DB
				q.setOpenPrice(12.00);
				q.setVolume(1000);
			}
			else
			{
				logger.error("unexpected value ["+shareTicker+"]");
				isQuoteValid=false;
			}
			if (isQuoteValid)
			{
				list.put(ticker,q);
			}
			
		}
		return true;
	}

}
