package net.tuxanna.portefeuille.database;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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
	@Override
	public int hashCode() {
		return new HashCodeBuilder(7, 13). // two randomly chosen prime numbers
				// if deriving: appendSuper(super.hashCode()).
				append(idShare).
				append(idQuote).
				toHashCode()+quotation.hashCode();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (!(obj instanceof QuoteDB))
			return false;
		if (obj == this)
			return true;

		QuoteDB rhs = (QuoteDB) obj;
		return new EqualsBuilder().
				// if deriving: appendSuper(super.equals(obj)).
				append(idShare,rhs.idShare).
				append(idQuote,rhs.idQuote).
				append(quotation,rhs.quotation).
				isEquals();
	}

	@Override
	public String toString()
	{
		StringBuffer str=new StringBuffer("id quote=");
		str.append(Integer.toString(idQuote));
		str.append(", id share=");
		str.append(Integer.toString(idShare));
		str.append(",");
		str.append(quotation.toString());
		return str.toString();
	}
}
