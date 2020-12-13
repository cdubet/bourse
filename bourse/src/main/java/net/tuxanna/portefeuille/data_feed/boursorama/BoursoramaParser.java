package net.tuxanna.portefeuille.data_feed.boursorama;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import net.tuxanna.portefeuille.Quote;
import net.tuxanna.portefeuille.dataFeed.JsoupDocProvider;


public class BoursoramaParser
{
	private static final Logger logger = LogManager.getLogger(BoursoramaParser.class);
	private JsoupDocProvider docProvider=null;

	
	public BoursoramaParser()
	{
	}


	public static String stripNonDigits(String input)
	{
		final StringBuilder sb = new StringBuilder(input.length());
		for (int i = 0; i < input.length(); i++)
		{
			final char c = input.charAt(i);
			if ((c >= '0' && c <= '9') ||
					(c == '.') ||
					((c == '-') && (i==0))) //start with minus
			{
				sb.append(c);
			}
		}
		return sb.toString();
	}
		
	
	private boolean parseOpening(Elements doc,Quote quote)
	{
		//look for something like
		//<p class="c-list-info__value c-list-info__value--color-light"><span class="c-instrument c-instrument--open" data-ist-open="">13.15</span>
		Elements ouvertureItem = doc.select("span[class=c-instrument c-instrument--open]");
		if (ouvertureItem==null)
		{
			logger.error("no data found for opening");
			return false;
		}
		if (ouvertureItem.isEmpty())
		{
			logger.error("empty opening found for share");
			return false;
		}
		
		quote.setOpenPrice(stripNonDigits(ouvertureItem.text()));
		return true;
	}
	
	private boolean parseHigh(Elements doc,Quote quote)
	{
		Elements highQuoteItem = doc.select("span[class=c-instrument c-instrument--high]");
		if (highQuoteItem==null)
		{
			logger.error("no high found for share");
			return false;
		}
		if (highQuoteItem.isEmpty())
		{
			logger.error("empty high found for share");
			return false;
		}
		
		quote.setHighPrice(stripNonDigits(highQuoteItem.text()));
		return true;
	}
	private boolean parseLow(Elements doc,Quote quote)
	{
		Elements lowQuoteItem = doc.select("span[class=c-instrument c-instrument--low]");
		if (lowQuoteItem==null)
		{
			logger.error("no low quote found for share");
			return false;
		}
		if (lowQuoteItem.isEmpty())
		{
			logger.error("empty low quote found for share");
			return false;
		}
		
		quote.setLowPrice(stripNonDigits(lowQuoteItem.text()));
		return true;
	}
	private boolean parseVolume(Elements doc,Quote quote)
	{
		Elements volumeItem = doc.select("span[class=c-instrument c-instrument--totalvolume]");
		if (volumeItem==null)
		{
			logger.error("no volume found for share");
			return false;
		}
		if (volumeItem.isEmpty())
		{
			logger.error("empty volume found for share");
			return false;
		}
		
		quote.setVolume(stripNonDigits(volumeItem.text()));
		return true;
	}
	private boolean parseLastTradedPrice(Elements doc,Quote quote)
	{
		Elements lastTradedItem = doc.select("span[class=c-instrument c-instrument--last]");
		if (lastTradedItem==null)
		{
			logger.error("no last traded found for share");
			return false;
		}
		if (lastTradedItem.isEmpty())
		{
			logger.error("empty last traded found for share");
			return false;
		}
		
		quote.setLastTradedPrice(stripNonDigits(lastTradedItem.text()));
		return true;
	}
	
	public boolean parseShare(Document doc ,Quote quote)
	{
		Elements headerItem= doc.select("header[class=l-quotepage__header]");
		if (headerItem==null)
		{
			logger.error("no header found for share\n"+doc.toString()+"\n");
			return false;
		}

		if (headerItem.isEmpty())
		{
			logger.error("empty header found for share\n"+doc.toString()+"\n");
			return false;
		}
		if (!parseOpening(headerItem,quote)) 
		{
			logger.error("problem reading opening\n"+doc.toString()+"\n");
			return false;
		}

		if (!parseHigh(headerItem,quote)) 
		{
			logger.error("problem reading high\n"+doc.toString()+"\n");
			return false;
		}
		if (!parseLow(headerItem,quote)) 
		{
			logger.error("problem reading low\n"+doc.toString()+"\n");
			return false;
		}
		if (!parseVolume(headerItem,quote)) 
		{
			logger.error("problem reading volume\n"+doc.toString()+"\n");
			return false;
		}
		if (!parseLastTradedPrice(headerItem,quote)) 
		{
			logger.error("problem reading last traded\n"+doc.toString()+"\n");
			return false;
		}
		return true;
	}
	public boolean parse(boolean isShare, Quote quote)
	{
		if (docProvider==null)
		{
			logger.error("jsoup not set");
			return false;
		}
		try
		{
			Document doc = docProvider.getDocument();
			if (isShare)
			{
				return parseShare(doc,quote);
			}
			else
			{
				return parseSicav(doc ,quote);
			}

		}
		catch (IOException e)
		{
			logger.error(e);
			return false;
		}

	}



	private boolean parseSicav(Document doc, Quote quote)
	{
		Elements headerItem= doc.select("header[class=l-quotepage__header]");
		if (headerItem==null)
		{
			logger.error("no header found for sicav\n"+doc.toString()+"\n");
			return false;
		}
		if (headerItem.isEmpty())
		{
			logger.error("empty header found for sicav\n"+doc.toString()+"\n");
			return false;
		}
		
		Elements lastQuotationItem= headerItem.select("span[class=c-instrument c-instrument--last]");
		if (lastQuotationItem==null)
		{
			logger.error("no current quote found for sicav\n"+doc.toString()+"\n");
			return false;
		}
		if (lastQuotationItem.isEmpty())
		{
			logger.error("empty quote found for sicav\n"+doc.toString()+"\n");
			return false;
		}
		
		quote.setLastTradedPrice(stripNonDigits(lastQuotationItem.text()));
		
		Elements variationItem= headerItem.select("span[class=c-instrument c-instrument--variation]");
		if (variationItem==null)
		{
			logger.error("no variation found for sicav\n"+doc.toString()+"\n");
			return false;
		}
		if (variationItem.isEmpty())
		{
			logger.error("empty variation found for sicav\n"+doc.toString()+"\n");
			return false;
		}
		
		return true;
	}


	public void setDocProvider(JsoupDocProvider docProvider)
	{
		this.docProvider = docProvider;
	}
}
