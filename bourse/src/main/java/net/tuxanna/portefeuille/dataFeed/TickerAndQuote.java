package net.tuxanna.portefeuille.dataFeed;

import net.tuxanna.portefeuille.Quote;

public class TickerAndQuote
{
	private TickerI ticker;
	private Quote quote;
	
	public TickerAndQuote()
	{
		ticker=new Ticker();
		quote = new Quote();
	}
	
	public TickerAndQuote(TickerI ticker, Quote quote)
	{
		super();
		this.ticker = ticker;
		this.quote = quote;
	}
	
	public boolean isValid()
	{
		return quote.isValid() && ticker.isValid();
	}
	
	public TickerI getTicker()
	{
		return ticker;
	}
	public void setTicker(TickerI ticker)
	{
		this.ticker = ticker;
	}
	public Quote getQuote()
	{
		return quote;
	}
	public void setQuote(Quote quote)
	{
		this.quote = quote;
	}
	


}
