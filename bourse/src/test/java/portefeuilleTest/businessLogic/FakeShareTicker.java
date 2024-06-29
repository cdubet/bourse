package portefeuilleTest.businessLogic;

import net.tuxanna.portefeuille.dataFeed.TickerI;

public class FakeShareTicker implements TickerI
{
	private String tickerName;
	
	public FakeShareTicker()
	{
		tickerName="fakeTicker";
	}
	public FakeShareTicker(int index)
	{
		Integer idx=index;
		tickerName="ticker"+idx.toString();
	}
	
	@Override
	public String getSymbol()
	{
		return tickerName;
	}

	@Override
	public TickerI.TypeOfItem getTypeOfItem()
	{
		return TickerI.TypeOfItem.TYPE_SHARE;
	}
	@Override
	public String toString()
	{
		return "FakeShareTicker [symbol=" + tickerName +"]";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tickerName == null) ? 0 : tickerName.hashCode());
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
		TickerI other = (TickerI) obj;
		if (tickerName == null)
		{
			if (other.getSymbol() != null)
				return false;
		}
		else if (!tickerName.equals(other.getSymbol()))
			return false;
		return true;
	}
	@Override
	public boolean isValid()
	{
		return true;
	}
}
