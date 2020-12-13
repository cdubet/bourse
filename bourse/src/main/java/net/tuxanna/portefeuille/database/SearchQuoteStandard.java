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

public class SearchQuoteStandard implements SearchQuoteI
{
	private static final Logger logger = LogManager.getLogger(SearchQuoteAtOneDay.class);
	private ShareDB share;
	protected PreparedStatement selectStatement;
	
	public SearchQuoteStandard(ShareDB p_share)
	{
		share=p_share;
	}

	@Override
	public boolean buildPreparedStatement(Connection conn) 
	{
		boolean res=true;
		final String SEARCH_QUOTE = "select dateQuote,lastTradedPrice,openPrice,highPrice,lowPrice ,volume ,idQuotes from QUOTES where idShare= ? order by dateQuote";
		try
		{
			selectStatement = conn.prepareStatement(SEARCH_QUOTE);
			selectStatement.setInt(1, share.getId());
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


	protected ShareDB getShare()
	{
		return share;
	}

	protected QuoteDB loadQuoteFromDB( ResultSet results) throws SQLException
	{
		
		Quote quotation=new Quote();
		
		Date dateQuote   = results.getDate("dateQuote");
		{
			double lastTradedPrice=results.getDouble("lastTradedPrice");
			quotation.setLastTradedPrice(lastTradedPrice);
		}

		{	
			double openPrice=results.getDouble("openPrice");
			if (!results.wasNull())
			{
				quotation.setOpenPrice(openPrice);
			}
		}
		{
			double highPrice=results.getDouble("highPrice");
			if (!results.wasNull())
			{
				quotation.setHighPrice(highPrice);
			}
		}

		{
			double lowPrice=results.getDouble("lowPrice");
			if (!results.wasNull())
			{
				quotation.setLowPrice(lowPrice);
			}
		}
		{
			double volume=results.getDouble("volume");
			if (!results.wasNull())
			{
				quotation.setVolume(volume);
			}
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
		if (selectStatement==null)
		{
			logger.error("not initialised !");
			return false;
		}

		boolean res=true;
		try
		{
			if (selectStatement.isClosed())
			{
				logger.error("closed !");
				return false;
			}
			
			ResultSet results = selectStatement.executeQuery();
			while (results.next())
			{
				QuoteDB quoteDB = loadQuoteFromDB( results);
				listQuote.add(quoteDB);
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
				selectStatement.close();
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
