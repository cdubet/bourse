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
	public void setupWithData_9_dec_2016()
	{
		predefinedQuotesToSend = new HashMap<TickerI, Quote>();

		int idx=0;
		{
			Quote q = new Quote();
			q.setLastTradedPrice(7.45);
			q.setChangeInPrice(0.03);
			q.setOpenPrice(7.31);
			q.setHighPrice(7.5);
			q.setLowPrice(7.3);
			q.setVolume(30339.0);
			q.setLow52Week(4.5);
			q.setHigh52Week(8.34);
			q.setMobileAverage50Days(7.63);
			q.setMobileAverage200Days(6.49);
			q.setPreviousClose(7.38);
			q.setPeRatio(6.86);
			q.setShortRatio(0);

			TickerI ticker = new FakeShareTicker(idx++);
			predefinedQuotesToSend.put(ticker, q);
		}

		{
			Quote q = new Quote();
			q.setLastTradedPrice("104.0");
			q.setChangeInPrice("1.25");
			q.setOpenPrice("102.95");
			q.setHighPrice("104.35");
			q.setLowPrice("102.5");
			q.setVolume("1365492.0");
			q.setLow52Week("77.93");
			q.setHigh52Week("104.35");
			q.setMobileAverage50Days("94.28");
			q.setMobileAverage200Days("88.6");
			q.setPreviousClose("102.75");
			q.setPeRatio("20.88");
			q.setShortRatio("0");
			TickerI ticker = new FakeShareTicker(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}

		{
			Quote q = new Quote();
			q.setLastTradedPrice("3.02");
			q.setChangeInPrice("-0.01");
			q.setOpenPrice("3.03");
			q.setHighPrice("3.03");
			q.setLowPrice("3.02");
			q.setVolume("102.0");
			q.setLow52Week("2.23");
			q.setHigh52Week("4.21");
			q.setMobileAverage50Days("3.04");
			q.setMobileAverage200Days("3.18");
			q.setPreviousClose("3.03");
			q.setShortRatio("0.0");
			TickerI ticker = new FakeShareTicker(idx++);
			predefinedQuotesToSend.put(ticker, q);
		}

		{
			Quote q = new Quote();
			q.setLastTradedPrice("0.19");
			q.setChangeInPrice("0.01");
			q.setOpenPrice("0.19");
			q.setHighPrice("0.2");
			q.setLowPrice("0.18");
			q.setVolume("1602845.0");
			q.setLow52Week("0.12");
			q.setHigh52Week("0.28");
			q.setMobileAverage50Days("0.19");
			q.setMobileAverage200Days("0.2");
			q.setPreviousClose("0.18");
			q.setPeRatio("95.0");
			q.setShortRatio("0.0");
			TickerI ticker = new FakeShareTicker(idx++);
			predefinedQuotesToSend.put(ticker, q);
		}

		{
			Quote q = new Quote();
			q.setLastTradedPrice("2.61");
			q.setChangeInPrice("-0.01");
			q.setOpenPrice("2.63");
			q.setHighPrice("2.63");
			q.setLowPrice("2.6");
			q.setVolume("5843.0");
			q.setLow52Week("2.52");
			q.setHigh52Week("4.69");
			q.setMobileAverage50Days("2.69");
			q.setMobileAverage200Days("2.74");
			q.setPreviousClose("2.62");
			q.setShortRatio("0.0");
			TickerI ticker = new FakeShareTicker(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("45.57");
			q.setChangeInPrice("0.56");
			q.setOpenPrice("44.86");
			q.setHighPrice("45.88");
			q.setLowPrice("44.86");
			q.setVolume("512679.0");
			q.setLow52Week("34.38");
			q.setHigh52Week("55.34");
			q.setMobileAverage50Days("44.67");
			q.setMobileAverage200Days("46.71");
			q.setPreviousClose("45.01");
			q.setPeRatio("0.74");
			q.setShortRatio("0.0");
			TickerI ticker = new FakeShareTicker(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("61.32");
			q.setChangeInPrice("1.33");
			q.setOpenPrice("60.13");
			q.setHighPrice("61.74");
			q.setLowPrice("60.05");
			q.setVolume("2508521.0");
			q.setLow52Week("57.49");
			q.setHigh52Week("70.53");
			q.setMobileAverage50Days("60.46");
			q.setMobileAverage200Days("64.09");
			q.setPreviousClose("59.99");
			q.setPeRatio("21.63");
			q.setShortRatio("0.0");
			TickerI ticker = new FakeShareTicker(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("21.2");
			q.setChangeInPrice("0.54");
			q.setOpenPrice("20.76");
			q.setHighPrice("21.2");
			q.setLowPrice("20.76");
			q.setVolume("11261.0");
			q.setLow52Week("14.3");
			q.setHigh52Week("21.51");
			q.setMobileAverage50Days("19.95");
			q.setMobileAverage200Days("18.77");
			q.setPreviousClose("20.66");
			q.setPeRatio("27.57");
			q.setShortRatio("0.0");
			TickerI ticker = new FakeShareTicker(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("103.7");
			q.setChangeInPrice("3.25");
			q.setOpenPrice("100.9");
			q.setHighPrice("104.1");
			q.setLowPrice("100.9");
			q.setVolume("804459.0");
			q.setLow52Week("93.41");
			q.setHigh52Week("124.55");
			q.setMobileAverage50Days("101.54");
			q.setMobileAverage200Days("112.25");
			q.setPreviousClose("100.45");
			q.setPeRatio("28.64");
			q.setShortRatio("0.0");
			TickerI ticker = new FakeShareTicker(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("0.77");
			q.setChangeInPrice("0.0");
			q.setOpenPrice("0.77");
			q.setHighPrice("0.78");
			q.setLowPrice("0.76");
			q.setVolume("99833.0");
			q.setLow52Week("0.71");
			q.setHigh52Week("1.89");
			q.setMobileAverage50Days("0.85");
			q.setMobileAverage200Days("1.27");
			q.setPreviousClose("0.77");
			q.setShortRatio("0.0");
			TickerI ticker = new FakeShareTicker(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("72.45");
			q.setChangeInPrice("-0.2");
			q.setOpenPrice("72.73");
			q.setHighPrice("73.98");
			q.setLowPrice("72.31");
			q.setVolume("320202.0");
			q.setLow52Week("68.28");
			q.setHigh52Week("119.5");
			q.setMobileAverage50Days("72.83");
			q.setMobileAverage200Days("88.9");
			q.setPreviousClose("72.65");
			q.setPeRatio("19.55");
			q.setShortRatio("0.0");
			TickerI ticker = new FakeShareTicker(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("50.4");
			q.setChangeInPrice("0.3");
			q.setOpenPrice("50.41");
			q.setHighPrice("50.5");
			q.setLowPrice("49.79");
			q.setVolume("483607.0");
			q.setLow52Week("30.33");
			q.setHigh52Week("54.48");
			q.setMobileAverage50Days("49.73");
			q.setMobileAverage200Days("44.86");
			q.setPreviousClose("50.1");
			q.setShortRatio("0.0");
			TickerI ticker = new FakeShareTicker(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("167.2");
			q.setChangeInPrice("4.6");
			q.setOpenPrice("162.95");
			q.setHighPrice("168.4");
			q.setLowPrice("162.7");
			q.setVolume("901391.0");
			q.setLow52Week("142.65");
			q.setHigh52Week("177.9");
			q.setMobileAverage50Days("161.98");
			q.setMobileAverage200Days("167.36");
			q.setPreviousClose("162.6");
			q.setPeRatio("32.65");
			q.setShortRatio("0.0");
			TickerI ticker = new FakeShareTicker(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("105.55");
			q.setChangeInPrice("0.7");
			q.setOpenPrice("104.85");
			q.setHighPrice("105.85");
			q.setLowPrice("104.8");
			q.setVolume("554669.0");
			q.setLow52Week("77.4");
			q.setHigh52Week("106.3");
			q.setMobileAverage50Days("98.68");
			q.setMobileAverage200Days("94.1");
			q.setPreviousClose("104.85");
			q.setPeRatio("15.96");
			q.setShortRatio("0.0");
			TickerI ticker = new FakeShareTicker(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("0.69");
			q.setChangeInPrice("0.0");
			q.setOpenPrice("0.7");
			q.setHighPrice("0.7");
			q.setLowPrice("0.67");
			q.setVolume("260486.0");
			q.setLow52Week("0.58");
			q.setHigh52Week("1.67");
			q.setMobileAverage50Days("0.69");
			q.setMobileAverage200Days("0.95");
			q.setPreviousClose("0.69");
			q.setShortRatio("0.0");
			TickerI ticker = new FakeShareTicker(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("63.74");
			q.setChangeInPrice("2.66");
			q.setOpenPrice("61.24");
			q.setHighPrice("63.74");
			q.setLowPrice("61.24");
			q.setVolume("983213.0");
			q.setLow52Week("49.94");
			q.setHigh52Week("69.54");
			q.setMobileAverage50Days("61.06");
			q.setMobileAverage200Days("63.85");
			q.setPreviousClose("61.08");
			q.setPeRatio("15.52");
			q.setShortRatio("0.0");
			TickerI ticker = new FakeShareTicker(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("81.43");
			q.setChangeInPrice("-0.88");
			q.setOpenPrice("82.15");
			q.setHighPrice("82.81");
			q.setLowPrice("81.13");
			q.setVolume("796082.0");
			q.setLow52Week("63.64");
			q.setHigh52Week("95.25");
			q.setMobileAverage50Days("75.95");
			q.setMobileAverage200Days("75.26");
			q.setPreviousClose("82.31");
			q.setPeRatio("7.59");
			q.setShortRatio("0.0");
			TickerI ticker = new FakeShareTicker(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("129.5");
			q.setChangeInPrice("0.25");
			q.setOpenPrice("129.0");
			q.setHighPrice("129.95");
			q.setLowPrice("128.85");
			q.setVolume("80811.0");
			q.setLow52Week("79.9");
			q.setHigh52Week("136.0");
			q.setMobileAverage50Days("130.38");
			q.setMobileAverage200Days("120.97");
			q.setPreviousClose("129.25");
			q.setPeRatio("30.13");
			q.setShortRatio("0.0");
			TickerI ticker = new FakeShareTicker(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("65.06");
			q.setChangeInPrice("0.13");
			q.setOpenPrice("65.08");
			q.setHighPrice("65.17");
			q.setLowPrice("64.53");
			q.setVolume("1044030.0");
			q.setLow52Week("45.31");
			q.setHigh52Week("65.17");
			q.setMobileAverage50Days("62.19");
			q.setMobileAverage200Days("59.37");
			q.setPreviousClose("64.93");
			q.setPeRatio("24.63");
			q.setShortRatio("0.0");
			TickerI ticker = new FakeShareTicker(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("13.345");
			q.setChangeInPrice("0.19");
			q.setOpenPrice("13.175");
			q.setHighPrice("13.42");
			q.setLowPrice("13.15");
			q.setVolume("1358546.0");
			q.setLow52Week("12.725");
			q.setHigh52Week("17.9");
			q.setMobileAverage50Days("13.494");
			q.setMobileAverage200Days("14.051");
			q.setPreviousClose("13.155");
			q.setPeRatio("17.606");
			q.setShortRatio("0.0");
			TickerI ticker = new FakeShareTicker(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("34.72");
			q.setChangeInPrice("0.67");
			q.setOpenPrice("34.17");
			q.setHighPrice("35.25");
			q.setLowPrice("34.08");
			q.setVolume("75468.0");
			q.setLow52Week("20.55");
			q.setHigh52Week("36.88");
			q.setMobileAverage50Days("32.19");
			q.setMobileAverage200Days("31.57");
			q.setPreviousClose("34.06");
			q.setPeRatio("22.55");
			q.setShortRatio("0.0");
			TickerI ticker = new FakeShareTicker(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("67.05");
			q.setChangeInPrice("0.13");
			q.setOpenPrice("67.15");
			q.setHighPrice("67.94");
			q.setLowPrice("66.29");
			q.setVolume("925432.0");
			q.setLow52Week("35.11");
			q.setHigh52Week("68.04");
			q.setMobileAverage50Days("63.15");
			q.setMobileAverage200Days("54.11");
			q.setPreviousClose("66.92");
			q.setPeRatio("15.5");
			q.setShortRatio("0.0");
			TickerI ticker = new FakeShareTicker(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("93.99");
			q.setChangeInPrice("1.78");
			q.setOpenPrice("92.3");
			q.setHighPrice("94.51");
			q.setLowPrice("92.23");
			q.setVolume("129864.0");
			q.setLow52Week("66.65");
			q.setHigh52Week("99.5");
			q.setMobileAverage50Days("92.48");
			q.setMobileAverage200Days("87.23");
			q.setPreviousClose("92.21");
			q.setPeRatio("27.04");
			q.setShortRatio("0.0");
			TickerI ticker = new FakeShareTicker(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("157.25");
			q.setChangeInPrice("-1.2");
			q.setOpenPrice("157.8");
			q.setHighPrice("158.1");
			q.setLowPrice("156.65");
			q.setVolume("1562348.0");
			q.setLow52Week("118.35");
			q.setHigh52Week("166.2");
			q.setMobileAverage50Days("146.85");
			q.setMobileAverage200Days("136.34");
			q.setPreviousClose("158.45");
			q.setPeRatio("11.05");
			q.setShortRatio("0.0");
			TickerI ticker = new FakeShareTicker(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("80.57");
			q.setChangeInPrice("-0.18");
			q.setOpenPrice("80.75");
			q.setHighPrice("81.34");
			q.setLowPrice("80.15");
			q.setVolume("932055.0");
			q.setLow52Week("43.25");
			q.setHigh52Week("81.95");
			q.setMobileAverage50Days("77.99");
			q.setMobileAverage200Days("70.6");
			q.setPreviousClose("80.75");
			q.setPeRatio("30.62");
			q.setShortRatio("10.82");
			TickerI ticker = new FakeShareTicker(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("1142.63");
			q.setChangeInPrice("9.43");
			q.setVolume("0.0");
			q.setPreviousClose("1133.2");
			TickerI ticker = new FakeShareTicker(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("7.52");
			q.setChangeInPrice("0.12");
			q.setOpenPrice("7.42");
			q.setHighPrice("7.52");
			q.setLowPrice("7.35");
			q.setVolume("109519.0");
			q.setLow52Week("4.61");
			q.setHigh52Week("7.75");
			q.setMobileAverage50Days("7.48");
			q.setMobileAverage200Days("6.54");
			q.setPreviousClose("7.4");
			q.setPeRatio("14.22");
			q.setShortRatio("0.0");
			TickerI ticker = new FakeShareTicker(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("23.945");
			q.setChangeInPrice("-0.42");
			q.setOpenPrice("24.36");
			q.setHighPrice("24.46");
			q.setLowPrice("23.845");
			q.setVolume("8708789.0");
			q.setLow52Week("16.11");
			q.setHigh52Week("25.79");
			q.setMobileAverage50Days("21.621");
			q.setMobileAverage200Days("19.752");
			q.setPreviousClose("24.365");
			q.setPeRatio("10.609");
			q.setShortRatio("0.0");
			TickerI ticker = new FakeShareTicker(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("23.28");
			q.setChangeInPrice("0.39");
			q.setOpenPrice("22.89");
			q.setHighPrice("23.42");
			q.setLowPrice("22.89");
			q.setVolume("2952071.0");
			q.setLow52Week("20.9");
			q.setHigh52Week("27.86");
			q.setMobileAverage50Days("23.0");
			q.setMobileAverage200Days("22.9");
			q.setPreviousClose("22.89");
			q.setPeRatio("19.02");
			q.setShortRatio("0.0");
			TickerI ticker = new FakeShareTicker(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("63.72");
			q.setChangeInPrice("0.78");
			q.setOpenPrice("63.0");
			q.setHighPrice("63.91");
			q.setLowPrice("62.95");
			q.setVolume("301464.0");
			q.setLow52Week("55.23");
			q.setHigh52Week("72.77");
			q.setMobileAverage50Days("64.09");
			q.setMobileAverage200Days("66.43");
			q.setPreviousClose("62.94");
			q.setPeRatio("16.53");
			q.setShortRatio("0.0");
			TickerI ticker = new FakeShareTicker(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("35.57");
			q.setChangeInPrice("-0.19");
			q.setOpenPrice("35.76");
			q.setHighPrice("35.9");
			q.setLowPrice("35.04");
			q.setVolume("83027.0");
			q.setLow52Week("21.66");
			q.setHigh52Week("44.35");
			q.setMobileAverage50Days("31.31");
			q.setMobileAverage200Days("28.75");
			q.setPreviousClose("35.76");
			q.setPeRatio("10.68");
			q.setShortRatio("0.0");
			TickerI ticker = new FakeShareTicker(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("109.142");
			q.setChangeInPrice("1.533");
			q.setOpenPrice("107.655");
			q.setHighPrice("110.28");
			q.setLowPrice("107.655");
			q.setVolume("1747.0");
			q.setLow52Week("87.922");
			q.setHigh52Week("122.9");
			q.setMobileAverage50Days("111.612");
			q.setMobileAverage200Days("112.161");
			q.setPreviousClose("107.609");
			q.setPeRatio("22.485");
			q.setShortRatio("0.0");
			TickerI ticker = new FakeShareTicker(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("71.0");
			q.setChangeInPrice("2.3");
			q.setOpenPrice("68.9");
			q.setHighPrice("71.15");
			q.setLowPrice("68.75");
			q.setVolume("9175965.0");
			q.setLow52Week("67.0");
			q.setHigh52Week("80.05");
			q.setMobileAverage50Days("69.82");
			q.setMobileAverage200Days("74.55");
			q.setPreviousClose("68.7");
			q.setShortRatio("0.0");
			TickerI ticker = new FakeShareTicker(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("67.5");
			q.setChangeInPrice("-0.3");
			q.setOpenPrice("67.75");
			q.setHighPrice("67.9");
			q.setLowPrice("66.6");
			q.setVolume("1422431.0");
			q.setLow52Week("53.0");
			q.setHigh52Week("73.95");
			q.setMobileAverage50Days("65.44");
			q.setMobileAverage200Days("60.45");
			q.setPreviousClose("67.8");
			q.setShortRatio("0.0");
			TickerI ticker = new FakeShareTicker(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("227.8");
			q.setChangeInPrice("7.4");
			q.setOpenPrice("220.5");
			q.setHighPrice("228.7");
			q.setLowPrice("220.5");
			q.setVolume("2330197.0");
			q.setLow52Week("218.3");
			q.setHigh52Week("279.6");
			q.setMobileAverage50Days("227.41");
			q.setMobileAverage200Days("241.74");
			q.setPreviousClose("220.4");
			q.setPeRatio("21.98");
			q.setShortRatio("0.0");
			TickerI ticker = new FakeShareTicker(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("231.0");
			q.setChangeInPrice("4.9");
			q.setOpenPrice("226.1");
			q.setHighPrice("232.4");
			q.setLowPrice("225.8");
			q.setVolume("57322.0");
			q.setLow52Week("221.8");
			q.setHigh52Week("280.0");
			q.setMobileAverage50Days("230.64");
			q.setMobileAverage200Days("243.86");
			q.setPreviousClose("226.1");
			q.setShortRatio("0.0");
			TickerI ticker = new FakeShareTicker(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("76.33");
			q.setChangeInPrice("1.25");
			q.setOpenPrice("75.32");
			q.setHighPrice("76.95");
			q.setLowPrice("75.28");
			q.setVolume("3046253.0");
			q.setLow52Week("62.5");
			q.setHigh52Week("81.45");
			q.setMobileAverage50Days("25.5");
			q.setMobileAverage200Days("25.74");
			q.setPreviousClose("75.08");
			q.setPeRatio("23.42");
			q.setShortRatio("0.0");
			TickerI ticker = new FakeShareTicker(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("46.36");
			q.setChangeInPrice("-0.5");
			q.setOpenPrice("46.7");
			q.setHighPrice("46.97");
			q.setLowPrice("46.03");
			q.setVolume("6171352.0");
			q.setLow52Week("25.0");
			q.setHigh52Week("46.98");
			q.setMobileAverage50Days("38.71");
			q.setMobileAverage200Days("33.51");
			q.setPreviousClose("46.86");
			q.setPeRatio("10.04");
			q.setShortRatio("0.0");
			TickerI ticker = new FakeShareTicker(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("317.6");
			q.setChangeInPrice("-2.7");
			q.setOpenPrice("319.0");
			q.setHighPrice("321.5");
			q.setLowPrice("315.1");
			q.setVolume("217171.0");
			q.setLow52Week("246.2");
			q.setHigh52Week("373.0");
			q.setMobileAverage50Days("299.74");
			q.setMobileAverage200Days("281.49");
			q.setPreviousClose("320.3");
			q.setShortRatio("0.0");
			TickerI ticker = new FakeShareTicker(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("46.425");
			q.setChangeInPrice("0.51");
			q.setOpenPrice("45.93");
			q.setHighPrice("46.62");
			q.setLowPrice("45.79");
			q.setVolume("6638654.0");
			q.setLow52Week("35.21");
			q.setHigh52Week("46.62");
			q.setMobileAverage50Days("43.919");
			q.setMobileAverage200Days("43.087");
			q.setPreviousClose("45.915");
			q.setPeRatio("28.746");
			q.setShortRatio("0.0");
			TickerI ticker = new FakeShareTicker(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("180.0");
			q.setChangeInPrice("-3.2");
			q.setOpenPrice("182.8");
			q.setHighPrice("184.1");
			q.setLowPrice("176.0");
			q.setVolume("51205.0");
			q.setLow52Week("162.3");
			q.setHigh52Week("249.0");
			q.setMobileAverage50Days("188.15");
			q.setMobileAverage200Days("210.48");
			q.setPreviousClose("183.2");
			q.setShortRatio("0.0");
			TickerI ticker = new FakeShareTicker(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("59.58");
			q.setChangeInPrice("0.26");
			q.setOpenPrice("59.29");
			q.setHighPrice("59.63");
			q.setLowPrice("58.73");
			q.setVolume("1926839.0");
			q.setLow52Week("48.07");
			q.setHigh52Week("67.01");
			q.setMobileAverage50Days("56.29");
			q.setMobileAverage200Days("53.56");
			q.setPreviousClose("59.32");
			q.setPeRatio("17.83");
			q.setShortRatio("0.0");
			TickerI ticker = new FakeShareTicker(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("766.85");
			q.setChangeInPrice("-0.48");
			q.setOpenPrice("770.0");
			q.setHighPrice("770.25");
			q.setLowPrice("765.34");
			q.setVolume("1969576.0");
			q.setLow52Week("474.0");
			q.setHigh52Week("847.21");
			q.setMobileAverage50Days("773.87");
			q.setMobileAverage200Days("763.56");
			q.setPreviousClose("767.33");
			q.setPeRatio("175.68");
			q.setShortRatio("1.09");
			TickerI ticker = new FakeShareTicker(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("400.0");
			q.setChangeInPrice("5.5");
			q.setOpenPrice("396.0");
			q.setHighPrice("401.2");
			q.setLowPrice("395.25");
			q.setVolume("24448.0");
			q.setLow52Week("271.25");
			q.setHigh52Week("434.95");
			q.setMobileAverage50Days("412.09");
			q.setMobileAverage200Days("375.08");
			q.setPreviousClose("394.5");
			q.setPeRatio("79.82");
			q.setShortRatio("0.0");
			TickerI ticker = new FakeShareTicker(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("52.31");
			q.setChangeInPrice("3.08");
			q.setOpenPrice("51.14");
			q.setHighPrice("54.0");
			q.setLowPrice("50.5");
			q.setVolume("2086696.0");
			q.setLow52Week("46.53");
			q.setHigh52Week("66.87");
			q.setMobileAverage50Days("49.74");
			q.setMobileAverage200Days("55.23");
			q.setPreviousClose("49.24");
			q.setShortRatio("0.0");
			TickerI ticker = new FakeShareTicker(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("178.75");
			q.setChangeInPrice("2.25");
			q.setOpenPrice("176.55");
			q.setHighPrice("179.75");
			q.setLowPrice("176.25");
			q.setVolume("77511.0");
			q.setLow52Week("166.7");
			q.setHigh52Week("238.1");
			q.setMobileAverage50Days("179.29");
			q.setMobileAverage200Days("180.68");
			q.setPreviousClose("176.5");
			q.setPeRatio("29.7");
			q.setShortRatio("0.0");
			TickerI ticker = new FakeShareTicker(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}
		{
			Quote q = new Quote();
			q.setLastTradedPrice("55.76");
			q.setChangeInPrice("-0.48");
			q.setOpenPrice("56.21");
			q.setHighPrice("56.47");
			q.setLowPrice("55.67");
			q.setVolume("756162.0");
			q.setLow52Week("33.88");
			q.setHigh52Week("56.47");
			q.setMobileAverage50Days("52.13");
			q.setMobileAverage200Days("47.64");
			q.setPreviousClose("56.24");
			q.setPeRatio("16.5");
			q.setShortRatio("0.0");
			TickerI ticker = new FakeShareTicker(idx++);
			predefinedQuotesToSend.put(ticker,q);
		}

	}
}
