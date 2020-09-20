package net.tuxanna.portefeuille.dataFeed;

import java.util.List;
import java.util.Map;

import net.tuxanna.portefeuille.Quote;

public interface QuotationProviderI
{
	public void setListTickers(List<TickerI> list);
	public boolean getQuotes(Map<TickerI,Quote> quoteForTicker);
}
