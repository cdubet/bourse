package net.tuxanna.portefeuille.database;

import java.time.LocalDateTime;
import java.util.Date;

import org.jooq.Condition;

import net.tuxanna.database.jooq.public_.tables.Quotes;

public class ConditionQuoteDateBefore implements ConditionQuoteI
{
	private Date dateToCheck;
	private Integer shareId;
	
	public ConditionQuoteDateBefore(Date date)
	{
		dateToCheck=date;
	}
	public ConditionQuoteDateBefore(Date date,Integer shareIdToSearchFor)
	{
		dateToCheck=date;
		shareId=shareIdToSearchFor;
	}
	
	@Override
	public Condition getCondition()
	{
		final LocalDateTime dateSplit=new java.sql.Timestamp(
				dateToCheck.getTime()).toLocalDateTime();
		
		Condition condition= Quotes.QUOTES.DATEQUOTE.lessThan(dateSplit);
		if (shareId != null)
		{
			condition=condition.and(Quotes.QUOTES.IDSHARE.eq(shareId));			
		}
		return condition;

	}

}
