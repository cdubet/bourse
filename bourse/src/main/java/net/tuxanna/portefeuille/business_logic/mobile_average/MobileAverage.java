package net.tuxanna.portefeuille.business_logic.mobile_average;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.tuxanna.portefeuille.database.QuoteDB;
import net.tuxanna.portefeuille.database.QuoteI;
import net.tuxanna.portefeuille.database.SearchLimitedNumberOfQuote;
import net.tuxanna.portefeuille.database.ShareDB;

public class MobileAverage
{
	private int nbDay;
	private QuoteI db;
	private ShareDB share;
	private static final Logger logger = LogManager.getLogger(MobileAverage.class);

	public MobileAverage(ShareDB p_share,QuoteI p_db, int p_nbDay)
	{
		db=p_db;
		nbDay=p_nbDay;
		share=p_share;
	}

	public Double compute()
	{
		//we cannot extract exactly the number because some item will be discarded (quote on saturday-> no quotation -> do not add duplication)
		final int nbItemToExtractFromDb=(int) ((float)nbDay*1.2);
		SearchLimitedNumberOfQuote search=new SearchLimitedNumberOfQuote(share, nbItemToExtractFromDb);
		List<QuoteDB> listQuote=new ArrayList<QuoteDB>();
		boolean res= db.readQuotations(listQuote, search);
		if (!res)
		{
			logger.debug("problem getting "+ nbItemToExtractFromDb+"quotation for ["+share.getId()+"]");
			return null;
		}
		
		//now get only 1 quote per day
		QuoteDB alreadyUsedQuote= null;
		double sumQuote=0;
		int nbQuote=0;
		Calendar calendar = Calendar.getInstance(); 

		//start with the last one (more recent quote)
		ListIterator<QuoteDB> listIter = listQuote.listIterator(listQuote.size());
		while (listIter.hasPrevious()) 
		{
			QuoteDB quoteDB=listIter.previous();

			boolean quoteToUse;
			if (alreadyUsedQuote ==null)
			{
				quoteToUse=true;
			}
			else
			{
				final Date dateCandidate = quoteDB.getDate();
				final Date dateAlreadyUsed = alreadyUsedQuote.getDate();
				
				calendar.setTime(dateCandidate); 
				final int dayOfTheWeek = calendar.get (Calendar.DAY_OF_WEEK);
				final boolean isWeekday = ((dayOfTheWeek >= Calendar.MONDAY) && (dayOfTheWeek <= Calendar.FRIDAY));
				
				final long dateCandidateInMs = dateCandidate.getTime();
				
				final long dateAlreadyUsedInMs = dateAlreadyUsed.getTime();
				final long days = (dateAlreadyUsedInMs-dateCandidateInMs)/1000/60/60/24;
				
				if (isWeekday)
				{
					if (days >=1 )
					{
						quoteToUse=true;
					}
					else
					{
						if (days <0)
						{
							logger.warn("going backwords ?"+ days);
						}
						logger.debug("rejecting "+dateCandidate.toString()+" because already used");
						quoteToUse=false;
					}
				}
				else
				{
					calendar.setTime(dateAlreadyUsed); 
					final int dayOfTheWeekForPreviousQuote = calendar.get (Calendar.DAY_OF_WEEK);

					if  (dayOfTheWeekForPreviousQuote == Calendar.MONDAY)
					{
						if (days >=2)
						{
							//more than a week end hole -> take it
							quoteToUse=true;
						}
						else
						{
							logger.debug("rejecting "+dateCandidate.toString()+" because WE");
							quoteToUse=false;//do not use quotation done in WE if there is one day before
						}
					}
					else
					{
						//no friday quotation, use WE one
						quoteToUse=true;
					}
				}
			}

			if (quoteToUse)
			{				
				final Date dateCandidate = quoteDB.getDate();
				logger.debug("using "+dateCandidate.toString());
				if (quoteDB.getQuotation().getLastTradedPrice().isValid())
				{
					alreadyUsedQuote=quoteDB;
					sumQuote += quoteDB.getQuotation().getLastTradedPrice().getValue();
					nbQuote++;
					if (nbQuote == nbDay)
					{
						return computeAverage(sumQuote, nbQuote);
					}
				}
				else
				{
					logger.error("cannot find quotation for "+dateCandidate.toString());
				}
			}
		}
		if (nbQuote != nbDay)
		{
			logger.error("problem "+ nbDay+"expected but got "+nbQuote+"for ["+share.getId()+"]");
			return null;
		}
		return computeAverage(sumQuote, nbQuote);
	}

	private Double computeAverage(double sumQuote, int nbQuote)
	{
		Double average= sumQuote/(double)nbQuote;
		return average;
	}
}
