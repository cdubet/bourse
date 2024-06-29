package net.tuxanna.portefeuille.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.tuxanna.portefeuille.dataFeed.TickerI.TypeOfItem;

public class ShareDB
{
	private static final Logger logger = LogManager.getLogger(ShareDB.class);

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
	private char shareType; //y = share, n sicav, t tracker
	
	private int id;
		
	private static  char  convertTypeShareToString(TypeOfItem typeOfItem)
	{
		switch (typeOfItem)
		{
		case TYPE_SHARE:
			return 'Y';
		case TYPE_SICAV:
			return 'N';
		case TYPE_TRACKER:
			return 'T';
		default:
			logger.error("unexpected value");
			return '?';
		}
	}
	
	private static TypeOfItem convertShareStringToType(char isShareChar)
	{
		if ((isShareChar=='y') || (isShareChar=='Y'))
		{
			return TypeOfItem.TYPE_SHARE;
		}
		if ((isShareChar=='t') || (isShareChar=='T'))
		{
			return TypeOfItem.TYPE_TRACKER;
		}
		if ((isShareChar=='N') || (isShareChar=='n'))
		{
			return TypeOfItem.TYPE_SICAV;
		}
		//TODO trace, should not happen
		return TypeOfItem.TYPE_SHARE;
	}
	
	public ShareDB(int id,String name, String ticker,Currency currency,TypeOfItem typeOfItem)
	{
		super();
		this.setName(name);
		this.ticker = ticker;
		this.id = id;
		this.currency=currency;
		this.shareType=convertTypeShareToString(typeOfItem);
	}
	public ShareDB(String name, String ticker,Currency currency,TypeOfItem typeOfItem)
	{
		super();
		this.setName(name);
		this.ticker = ticker;
		this.currency=currency;
		this.shareType=convertTypeShareToString(typeOfItem);
		id = Database.NON_ASSIGNED;
	}
	public ShareDB()
	{
		super();
		this.name="";
		this.ticker = "";
		this.currency=ShareDB.Currency.EURO;
		this.shareType='y';
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
  
	public TypeOfItem getTypeOfItem()
	{
		return convertShareStringToType(shareType);
	}
	public char getRawTypeOfItem()
	{
		return shareType;
	}
	
	public void setTypeOfItem(char val)
	{
		this.shareType = val;
	}
	@Override
	public String toString()
	{
		return "ShareDB [name=" + name + ", ticker=" + ticker + ", currency=" + currency + ", type=" + shareType
				+ ", id=" + id + "]";
	}



	   
}
