package net.tuxanna.portefeuille.dataFeed;



public interface TickerI
{
	public enum TypeOfItem 
	{
	    SHARE,
	    SICAV,
	    TRACKER 
	}
	
	String getSymbol();
	TypeOfItem getTypeOfItem() ; //true -> share, false -> sicav
	boolean isValid();
}
