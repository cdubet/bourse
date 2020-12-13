package net.tuxanna.portefeuille.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SearchQuoteAtOneDay extends SearchQuoteStandard implements SearchQuoteI
{
	private static final Logger logger = LogManager.getLogger(SearchQuoteAtOneDay.class);
	private java.util.Date date;

	public SearchQuoteAtOneDay(ShareDB p_share,java.util.Date p_date)
	{
		super(p_share);
		date=p_date;
	}

	@Override
	public boolean buildPreparedStatement(Connection conn) 
	{
		if (logger.isDebugEnabled())
		{
			SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.ENGLISH);
			String strDate = dateFormat.format(date);
			logger.debug("from "+strDate);
		}
		Calendar cal = Calendar.getInstance(); // creates calendar
		cal.setTime(date); // sets calendar time/date
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);


		java.util.Date newDateBefore=cal.getTime(); 
		cal.add(Calendar.DAY_OF_YEAR, 1);

		java.util.Date newDateAfter=cal.getTime();

		if (logger.isDebugEnabled())
		{
			SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.ENGLISH);
			String strBefore = dateFormat.format(newDateBefore);
			String strAfter = dateFormat.format(newDateAfter);
			logger.debug("from "+strBefore+" to "+strAfter);
		}

		final String SEARCH_QUOTE = "select dateQuote,lastTradedPrice,openPrice,highPrice,lowPrice,volume,idQuotes from QUOTES where idShare= ? and dateQuote >= ? and dateQuote <= ? order by dateQuote";

		boolean res=true;
		try
		{
			selectStatement = conn.prepareStatement(SEARCH_QUOTE);

			selectStatement.setInt(1, getShare().getId());
			selectStatement.setTimestamp(2, new Timestamp(newDateBefore.getTime()));
			selectStatement.setTimestamp(3, new Timestamp(newDateAfter.getTime()));
		}
		catch (SQLException e)
		{
			logger.error(e);
			res=false;
			if (selectStatement != null)
			{
				try
				{
					selectStatement.close();
				}
				catch (SQLException e2)
				{
					logger.error(e2);
				}
			}
		}

		return res;
	}

	public java.util.Date getDate()
	{
		return date;
	}
}
