package net.tuxanna.portefeuille.dataFeed;

public class Ticker implements TickerI
{
	private String symbol;
	
	public Ticker(String symbol, boolean isShare)
	{
		super();
		this.symbol = symbol;
		this.isShare = isShare;
	}

	private boolean isShare;
	
	public Ticker()
	{
		isShare=true;
		symbol="";
	}

	@Override
	public String getSymbol()
	{
		// TODO Auto-generated method stub
		return symbol;
	}

	@Override
	public boolean isShare()
	{
		return isShare;
	}

	public void setSymbol(String symbol)
	{
		this.symbol = symbol;
	}

	public void setShare(boolean isShare)
	{
		this.isShare = isShare;
	}

	@Override
	public String toString()
	{
		return "Ticker [symbol=" + symbol + ", isShare=" + isShare + "]";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticker other = (Ticker) obj;
		if (symbol == null)
		{
			if (other.symbol != null)
				return false;
		}
		else if (!symbol.equals(other.symbol))
			return false;
		return true;
	}

}
