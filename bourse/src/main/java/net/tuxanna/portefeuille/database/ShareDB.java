package net.tuxanna.portefeuille.database;


public class ShareDB
{
	public enum Currency
	{
	    DOLLAR_US, 
	    EURO,
	    CHF,
	    ENGLISH_POUND	     
	}
	
	private String name;
	private String ticker;
	private Currency currency;
	private boolean isShare;
	
	private int id;
		
	public ShareDB(int id,String name, String ticker,Currency currency,boolean isShare)
	{
		super();
		this.setName(name);
		this.ticker = ticker;
		this.id = id;
		this.currency=currency;
		this.isShare=isShare;
	}
	public ShareDB(String name, String ticker,Currency currency,boolean isShare)
	{
		super();
		this.setName(name);
		this.ticker = ticker;
		this.currency=currency;
		this.isShare=isShare;
		id = Database.NON_ASSIGNED;
	}
	public ShareDB()
	{
		super();
		this.name="";
		this.ticker = "";
		this.currency=ShareDB.Currency.EURO;
		this.isShare=true;
		id = Database.NON_ASSIGNED;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getTicker()
	{
		return ticker;
	}
	public void setTicker(String ticker)
	{
		this.ticker = ticker;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public Currency getCurrency()
	{
		return currency;
	}
	public void setCurrency(Currency currency)
	{
		this.currency = currency;
	}
  
	public boolean isShare()
	{
		return isShare;
	}
	public void setIsShare(boolean isShare)
	{
		this.isShare = isShare;
	}
	@Override
	public String toString()
	{
		return "ShareDB [name=" + name + ", ticker=" + ticker + ", currency=" + currency + ", isShare=" + isShare
				+ ", id=" + id + "]";
	}



	   
}
