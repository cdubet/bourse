package net.tuxanna.portefeuille.businessLogic.eval;

import java.io.StringWriter;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;


import org.apache.commons.lang3.builder.HashCodeBuilder;

import net.tuxanna.portefeuille.database.PortfolioDB;
import net.tuxanna.portefeuille.database.ShareDB;

public class ShareIdAndDate implements Comparable<ShareIdAndDate> ,Comparator<ShareIdAndDate>
{
	//set to final as they will be used as key to a map. should not be changed when stored !
	private final int year;
	private final int dayInYear;
	private final int shareId;

	public ShareIdAndDate(ShareDB share,Date dateOfQuotation)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateOfQuotation);
		year=calendar.get(Calendar.YEAR);
		dayInYear=calendar.get(Calendar.DAY_OF_YEAR);
		shareId=share.getId();
	}

	public ShareIdAndDate(PortfolioDB portfolio,Date dateOfQuotation)
	{
		shareId=portfolio.getIdShare();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateOfQuotation);
		year=calendar.get(Calendar.YEAR);
		dayInYear=calendar.get(Calendar.DAY_OF_YEAR);
	}

	public int getShareId()
	{
		return shareId;
	}

	@Override
	public int compareTo(ShareIdAndDate o)
	{
		int diffId=shareId -o.getShareId();
		if (diffId==0)
		{
			int diffYear=year - o.getYear();
			if (diffYear==0)
			{
				return dayInYear-o.getDayInYear();
			}
			return diffYear;
		}

		return diffId;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
				// if deriving: appendSuper(super.hashCode()).
				append(shareId).
				append(year).
				append(dayInYear).
				toHashCode();
	}

	@Override
	public int compare(ShareIdAndDate o1, ShareIdAndDate o2)
	{
		return o1.compareTo(o2);
	}

	public int getYear()
	{
		return year;
	}


	public int getDayInYear()
	{
		return dayInYear;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof ShareIdAndDate) 
		{
			ShareIdAndDate that = (ShareIdAndDate) obj;
			if ((shareId== that.getShareId()) && (year==that.getYear()) && (dayInYear == that.getDayInYear()))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString()
	{
		StringWriter str=new StringWriter();
		str.append("id=");
		str.append(Integer.toString(getShareId()));
		str.append(",year=");
		str.append(Integer.toString(year));
		str.append(",day=");
		str.append(Integer.toString(dayInYear));
	   		
		return str.toString();
	}

}
