package net.tuxanna.portefeuille.dataFeed;

public interface TickerI
{
	public enum TypeOfItem 
	{
	    TYPE_SHARE,
	    TYPE_SICAV,
	    TYPE_TRACKER 
	}
	
	String getSymbol();
	TypeOfItem getTypeOfItem() ; //true -> share, false -> sicav
	boolean isValid();
}
