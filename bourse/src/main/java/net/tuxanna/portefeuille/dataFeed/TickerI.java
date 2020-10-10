package net.tuxanna.portefeuille.dataFeed;

public interface TickerI
{
	String getSymbol();
	boolean isShare() ; //true -> share, false -> sicav
	boolean isValid();
}
