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
		final String SEARCH_QUOTE = "select dateQuote,lastTradedPrice,changeInPrice,openPrice,highPrice,lowPrice ,volume ,	low52Week,	high52Week ,mobileAverage50Days ,mobileAverage200Days,previousClose,peRatio, shortRatio,idQuotes from QUOTES where idShare= ? order by dateQuote";
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
			double changeInPrice=results.getDouble("changeInPrice");
			if (!results.wasNull())
			{
				quotation.setChangeInPrice(changeInPrice);
			}
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
		{
			double low52Week=results.getDouble("low52Week");
			if (!results.wasNull())
			{
				quotation.setLow52Week(low52Week);
			}
		}
		{
			double high52Week=results.getDouble("high52Week");
			if (!results.wasNull())
			{
				quotation.setHigh52Week(high52Week);
			}
		}
		
		{
			double mobileAverage50Days=results.getDouble("mobileAverage50Days");
			if (!results.wasNull())
			{
				quotation.setMobileAverage50Days(mobileAverage50Days);
			}
		}
		{
			double mobileAverage200Days=results.getDouble("mobileAverage200Days");
			if (!results.wasNull())
			{
				quotation.setMobileAverage200Days(mobileAverage200Days);
			}
		}
		{
			double previousClose=results.getDouble("previousClose");
			if (!results.wasNull())
			{
				quotation.setPreviousClose(previousClose);
			}
		}
		{
			double peRatio=results.getDouble("peRatio");
			if (!results.wasNull())
			{
				quotation.setPeRatio(peRatio);
			}
		}
		{
			double shortRatio=results.getDouble("shortRatio");
			if (!results.wasNull())
			{
				quotation.setShortRatio(shortRatio);
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
