package net.tuxanna.portefeuille.database;

import java.util.Date;

import net.tuxanna.portefeuille.Quote;

public class QuoteDB
{
	private Date date;
	private Quote quotation;
	private int idQuote;
	private int idShare;
	

	public QuoteDB()
	{
		setIdQuote(Database.NON_ASSIGNED);
		idShare=Database.NON_ASSIGNED;
		setQuotation(new Quote());
		setDate(new Date());
	}
	
	public QuoteDB(int p_idShare,Quote pricing)
	{
		idShare=p_idShare;
		setIdQuote(Database.NON_ASSIGNED);
		setQuotation(pricing);
		setDate(new Date());
	}
	
	public boolean hasRequiredDataForPersistance()
	{
		if (idShare==Database.NON_ASSIGNED )
		{
			return false;
		}
		if (!quotation.getLastTradedPrice().isValid())
		{
			return false;
		}
		return true;
	}
	
	public Date getDate()
	{
		return date;
	}
	public void setDate(Date date)
	{
		this.date = date;
	}
	public Quote getQuotation()
	{
		return quotation;
	}
	public void setQuotation(Quote quotation)
	{
		this.quotation = quotation;
	}
	public int getIdQuote()
	{
		return idQuote;
	}
	public void setIdQuote(int id)
	{
		this.idQuote = id;
	}
	public int getIdShare()
	{
		return idShare;
	}
	public void setIdShare(int idShare)
	{
		this.idShare = idShare;
	}
}
