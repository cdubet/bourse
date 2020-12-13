package portefeuilleTest.businessLogic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.tuxanna.portefeuille.Quote;
import net.tuxanna.portefeuille.dataFeed.QuotationProviderI;
import net.tuxanna.portefeuille.dataFeed.TickerI;

public class PredefinedQuotationProvider implements QuotationProviderI
{
	Map<TickerI, Quote> predefinedQuotesToSend = null;

	public PredefinedQuotationProvider()
	{
	}

	@Override
	public void setListTickers(List<TickerI> list)
	{
		// ignore
	}

	@Override
	public boolean getQuotes(Map<TickerI, Quote> list)
	{
		if (predefinedQuotesToSend == null)
		{
			return false;
		}
		list.clear();
		list.putAll( predefinedQuotesToSend);
		return true;
	}

	public void setPredefinedQuotes(Map<TickerI, Quote> predefinedQuotes)
	{
		this.predefinedQuotesToSend = predefinedQuotes;
	}

	// simulate values from yahoo
	public void setupWithData_9_dec_2016(List<TickerI> listTicker)
	{
		predefinedQuotesToSend = new HashMap<TickerI, Quote>();

		int idx=0;
		{
			Quote q = new Quote();
			q.setLastTradedPrice(7.45);

			q.setOpenPrice(7.31);
			q.setHighPrice(7.5);
			q.setLowPrice(7.3);
			q.setVolume(30339.0);

			TickerI ticker = listTicker.get(idx++);
			predefinedQuotesToSend.put(ticker, q);
		}

		{
			Quote q = new Quote();
			q.setLastTradedPrice("104.0");

			q.setOpenPrice("102.95");
			q.setHighPrice("104.35");
			q.setLowPrice("102.5");
			q.setVolume("1365492.0");
			
			TickerI ticker = listTicker.get(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}

		{
			Quote q = new Quote();
			q.setLastTradedPrice("3.02");
			q.setOpenPrice("3.03");
			q.setHighPrice("3.03");
			q.setLowPrice("3.02");
			q.setVolume("102.0");
			TickerI ticker = listTicker.get(idx++);
			predefinedQuotesToSend.put(ticker, q);
		}

		{
			Quote q = new Quote();
			q.setLastTradedPrice("0.19");

			q.setOpenPrice("0.19");
			q.setHighPrice("0.2");
			q.setLowPrice("0.18");
			q.setVolume("1602845.0");
			
			TickerI ticker = listTicker.get(idx++);
			predefinedQuotesToSend.put(ticker, q);
		}

		{
			Quote q = new Quote();
			q.setLastTradedPrice("2.61");

			q.setOpenPrice("2.63");
			q.setHighPrice("2.63");
			q.setLowPrice("2.6");
			q.setVolume("5843.0");
			
			TickerI ticker = listTicker.get(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("45.57");

			q.setOpenPrice("44.86");
			q.setHighPrice("45.88");
			q.setLowPrice("44.86");
			q.setVolume("512679.0");
			
			TickerI ticker = listTicker.get(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("61.32");

			q.setOpenPrice("60.13");
			q.setHighPrice("61.74");
			q.setLowPrice("60.05");
			q.setVolume("2508521.0");
			
			TickerI ticker = listTicker.get(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("21.2");

			q.setOpenPrice("20.76");
			q.setHighPrice("21.2");
			q.setLowPrice("20.76");
			q.setVolume("11261.0");
			
			TickerI ticker = listTicker.get(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("103.7");

			q.setOpenPrice("100.9");
			q.setHighPrice("104.1");
			q.setLowPrice("100.9");
			q.setVolume("804459.0");
			
			TickerI ticker = listTicker.get(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("0.77");

			q.setOpenPrice("0.77");
			q.setHighPrice("0.78");
			q.setLowPrice("0.76");
			q.setVolume("99833.0");
			
			TickerI ticker = listTicker.get(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("72.45");
			
			q.setOpenPrice("72.73");
			q.setHighPrice("73.98");
			q.setLowPrice("72.31");
			q.setVolume("320202.0");
			
			
			TickerI ticker = listTicker.get(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("50.4");
			
			q.setOpenPrice("50.41");
			q.setHighPrice("50.5");
			q.setLowPrice("49.79");
			q.setVolume("483607.0");
			
			TickerI ticker = listTicker.get(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("167.2");

			q.setOpenPrice("162.95");
			q.setHighPrice("168.4");
			q.setLowPrice("162.7");
			q.setVolume("901391.0");
			TickerI ticker = listTicker.get(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("105.55");

			q.setOpenPrice("104.85");
			q.setHighPrice("105.85");
			q.setLowPrice("104.8");
			q.setVolume("554669.0");
			
			TickerI ticker = listTicker.get(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("0.69");
			
			q.setOpenPrice("0.7");
			q.setHighPrice("0.7");
			q.setLowPrice("0.67");
			q.setVolume("260486.0");
						
			TickerI ticker = listTicker.get(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("63.74");
			
			q.setOpenPrice("61.24");
			q.setHighPrice("63.74");
			q.setLowPrice("61.24");
			q.setVolume("983213.0");
			
			TickerI ticker = listTicker.get(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("81.43");
			
			q.setOpenPrice("82.15");
			q.setHighPrice("82.81");
			q.setLowPrice("81.13");
			q.setVolume("796082.0");
			
			TickerI ticker = listTicker.get(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("129.5");

			q.setOpenPrice("129.0");
			q.setHighPrice("129.95");
			q.setLowPrice("128.85");
			q.setVolume("80811.0");
			
			TickerI ticker = listTicker.get(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("65.06");

			q.setOpenPrice("65.08");
			q.setHighPrice("65.17");
			q.setLowPrice("64.53");
			q.setVolume("1044030.0");
			
			TickerI ticker = listTicker.get(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("13.345");

			q.setOpenPrice("13.175");
			q.setHighPrice("13.42");
			q.setLowPrice("13.15");
			q.setVolume("1358546.0");
			
			TickerI ticker = listTicker.get(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("34.72");

			q.setOpenPrice("34.17");
			q.setHighPrice("35.25");
			q.setLowPrice("34.08");
			q.setVolume("75468.0");
			
			TickerI ticker = listTicker.get(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("67.05");

			q.setOpenPrice("67.15");
			q.setHighPrice("67.94");
			q.setLowPrice("66.29");
			q.setVolume("925432.0");
			
			TickerI ticker = listTicker.get(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("93.99");

			q.setOpenPrice("92.3");
			q.setHighPrice("94.51");
			q.setLowPrice("92.23");
			q.setVolume("129864.0");
			TickerI ticker = listTicker.get(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("157.25");

			q.setOpenPrice("157.8");
			q.setHighPrice("158.1");
			q.setLowPrice("156.65");
			q.setVolume("1562348.0");
			TickerI ticker = listTicker.get(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("80.57");

			q.setOpenPrice("80.75");
			q.setHighPrice("81.34");
			q.setLowPrice("80.15");
			q.setVolume("932055.0");
			TickerI ticker = listTicker.get(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("1142.63");

			q.setVolume("0.0");

			TickerI ticker = listTicker.get(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("7.52");

			q.setOpenPrice("7.42");
			q.setHighPrice("7.52");
			q.setLowPrice("7.35");
			q.setVolume("109519.0");
			TickerI ticker = listTicker.get(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("23.945");

			q.setOpenPrice("24.36");
			q.setHighPrice("24.46");
			q.setLowPrice("23.845");
			q.setVolume("8708789.0");
			TickerI ticker = listTicker.get(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("23.28");

			q.setOpenPrice("22.89");
			q.setHighPrice("23.42");
			q.setLowPrice("22.89");
			q.setVolume("2952071.0");
			TickerI ticker = listTicker.get(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("63.72");

			q.setOpenPrice("63.0");
			q.setHighPrice("63.91");
			q.setLowPrice("62.95");
			q.setVolume("301464.0");
			TickerI ticker = listTicker.get(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("35.57");

			q.setOpenPrice("35.76");
			q.setHighPrice("35.9");
			q.setLowPrice("35.04");
			q.setVolume("83027.0");

			TickerI ticker = listTicker.get(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("109.142");

			q.setOpenPrice("107.655");
			q.setHighPrice("110.28");
			q.setLowPrice("107.655");
			q.setVolume("1747.0");
			TickerI ticker = listTicker.get(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("71.0");

			q.setOpenPrice("68.9");
			q.setHighPrice("71.15");
			q.setLowPrice("68.75");
			q.setVolume("9175965.0");
			TickerI ticker = listTicker.get(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("67.5");

			q.setOpenPrice("67.75");
			q.setHighPrice("67.9");
			q.setLowPrice("66.6");
			q.setVolume("1422431.0");
			TickerI ticker = listTicker.get(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("227.8");

			q.setOpenPrice("220.5");
			q.setHighPrice("228.7");
			q.setLowPrice("220.5");
			q.setVolume("2330197.0");
			TickerI ticker = listTicker.get(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("231.0");

			q.setOpenPrice("226.1");
			q.setHighPrice("232.4");
			q.setLowPrice("225.8");
			q.setVolume("57322.0");
			TickerI ticker = listTicker.get(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("76.33");

			q.setOpenPrice("75.32");
			q.setHighPrice("76.95");
			q.setLowPrice("75.28");
			q.setVolume("3046253.0");
			TickerI ticker = listTicker.get(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("46.36");

			q.setOpenPrice("46.7");
			q.setHighPrice("46.97");
			q.setLowPrice("46.03");
			q.setVolume("6171352.0");
			TickerI ticker = listTicker.get(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("317.6");

			q.setOpenPrice("319.0");
			q.setHighPrice("321.5");
			q.setLowPrice("315.1");
			q.setVolume("217171.0");
			TickerI ticker = listTicker.get(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("46.425");
			q.setOpenPrice("45.93");
			q.setHighPrice("46.62");
			q.setLowPrice("45.79");
			q.setVolume("6638654.0");
			TickerI ticker = listTicker.get(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("180.0");
			q.setOpenPrice("182.8");
			q.setHighPrice("184.1");
			q.setLowPrice("176.0");
			q.setVolume("51205.0");
			TickerI ticker = listTicker.get(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("59.58");
			q.setOpenPrice("59.29");
			q.setHighPrice("59.63");
			q.setLowPrice("58.73");
			q.setVolume("1926839.0");
			TickerI ticker = listTicker.get(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("766.85");
			q.setOpenPrice("770.0");
			q.setHighPrice("770.25");
			q.setLowPrice("765.34");
			q.setVolume("1969576.0");
			TickerI ticker = listTicker.get(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("400.0");
			q.setOpenPrice("396.0");
			q.setHighPrice("401.2");
			q.setLowPrice("395.25");
			q.setVolume("24448.0");
			TickerI ticker = listTicker.get(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("52.31");

			q.setOpenPrice("51.14");
			q.setHighPrice("54.0");
			q.setLowPrice("50.5");
			q.setVolume("2086696.0");
			TickerI ticker = listTicker.get(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("178.75");
			q.setOpenPrice("176.55");
			q.setHighPrice("179.75");
			q.setLowPrice("176.25");
			q.setVolume("77511.0");
			TickerI ticker = listTicker.get(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("55.76");

			q.setOpenPrice("56.21");
			q.setHighPrice("56.47");
			q.setLowPrice("55.67");
			q.setVolume("756162.0");
			TickerI ticker = listTicker.get(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}

	}
}
