package net.tuxanna.portefeuille.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.tuxanna.portefeuille.Quote;

public class SearchLimitedNumberOfQuote implements SearchQuoteI
{
	private int nbOfQuotes;
	private static final Logger logger = LogManager.getLogger(SearchLimitedNumberOfQuote.class);
	
	private PreparedStatement selectDateStatement;
	private PreparedStatement selectSearchStatement;
	
	private ShareDB share;
	public SearchLimitedNumberOfQuote(ShareDB p_share,int p_nbOfQuotes)
	{
		share=p_share;
		nbOfQuotes=p_nbOfQuotes;
	}

	@Override
	public boolean buildPreparedStatement(Connection conn)
	{
		boolean res=true;
		//get last X dates
		final String SEARCH_DATE_QUOTE = "select distinct  cast(dateQuote as DATE) from quotes where idshare=? order by  cast(dateQuote as DATE) desc  limit ?";
				
		final String SEARCH_QUOTE = "select dateQuote,lastTradedPrice,idQuotes from QUOTES where idshare=?  and dateQuote >= ?";
		try
		{
			selectDateStatement = conn.prepareStatement(SEARCH_DATE_QUOTE);
			selectDateStatement.setInt(1, share.getId());
			selectDateStatement.setInt(2, nbOfQuotes);
			
			selectSearchStatement=conn.prepareStatement(SEARCH_QUOTE);
			selectSearchStatement.setInt(1, share.getId()); //date limit is unknown yet
		}
		catch (SQLException e)
		{
			logger.error(e);
			res=false;
			if (selectDateStatement != null)
			{
				try
				{
					selectDateStatement.close();
				}
				catch (SQLException e2)
				{
					logger.error(e2);
				}
			}
			if (selectSearchStatement != null)
			{
				try
				{
					selectSearchStatement.close();
				}
				catch (SQLException e2)
				{
					logger.error(e2);
				}
			}
		}

		return res;
	}

	private QuoteDB loadQuoteFromDB( ResultSet results) throws SQLException
	{
		
		Quote quotation=new Quote();
		
		Date dateQuote   = results.getDate("dateQuote");
		{
			double lastTradedPrice=results.getDouble("lastTradedPrice");
			quotation.setLastTradedPrice(lastTradedPrice);
		}
		
		QuoteDB quoteDB=new QuoteDB(share.getId(),quotation);
		int id   = results.getInt("idQuotes");
		quoteDB.setIdQuote(id);
		quoteDB.setDate(dateQuote);
		return quoteDB;
	}

	@Override
	public boolean executeAndFill(List<QuoteDB> listQuote)
	{
		if ((selectDateStatement==null) || (selectSearchStatement==null))
		{
			logger.error("not initialised !");
			return false;
		}
		
		boolean res=true;
		try
		{
			if (selectDateStatement.isClosed())
			{
				logger.error("closed !");
				return false;
			}
			
			ResultSet results = selectDateStatement.executeQuery();
			//seems there is no way to get directly the last one, so iterate :-(
			Date dateLimit=null;
			while (results.next())
			{
				dateLimit   = results.getDate(1);
			}
			if (dateLimit==null)
			{
				logger.warn("no date found");
				res=false;
			}
			else
			{
				//now set the date as parameter for the second sql request
				selectSearchStatement.setDate(2, dateLimit);
				results = selectSearchStatement.executeQuery();
				while (results.next())
				{
					QuoteDB quoteDB = loadQuoteFromDB( results);
					listQuote.add(quoteDB);
				}
			}
		}
		catch (SQLException e)
		{
			logger.error(e);
			res=false;
		}
		finally
		{
			try
			{
				selectDateStatement.close();
				selectSearchStatement.close();
			}
			catch (SQLException e)
			{
				logger.error(e);
				res=false;
			}

		}
		return res;
	}


}
