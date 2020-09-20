package net.tuxanna.portefeuille.businessLogic.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.tuxanna.portefeuille.database.QuoteDB;
import net.tuxanna.portefeuille.database.ShareDB;
import net.tuxanna.portefeuille.util.ClockI;
import net.tuxanna.portefeuille.util.RealClock;


public class ShareToQuotations
{
	private Map<Integer /* the quote ID*/, QuoteDB> shareToQuote;
	private ClockI clock; //get system, time (fixed data for junit)
	
	private static final Logger logger = LogManager.getLogger(ShareToQuotations.class);

	public ShareToQuotations()
	{
		shareToQuote=new HashMap<Integer, QuoteDB>();
		clock=new RealClock();
	}

	public boolean getQuotationFromList(QuoteDB quoteDB, int quoteId)
	{
		Integer key=quoteId;
		quoteDB=shareToQuote.get(key);
		if (quoteDB == null)
		{
			return false;
		}
		else
		{
			//found 
			return true;
		}
	}
	public void setClock(ClockI clock)
	{
		this.clock = clock;
	}
	
	public int size()
	{
		return shareToQuote.size();
	}

	public QuoteDB get(ShareDB shareDB)
	{	
		Integer key=shareDB.getId();
		QuoteDB quoteDB=shareToQuote.get(key);
		return quoteDB;
	}

	public void add(ShareDB shareDB,QuoteDB quoteDB)
	{
		shareToQuote.put(shareDB.getId(), quoteDB);
	}

	public List<QuoteDB> getList()
	{
		List<QuoteDB> listOfQuotationsDB=new ArrayList<QuoteDB>(shareToQuote.values());
		return listOfQuotationsDB;
	}

	public boolean exportQuoteCsv(List<ShareDB> listShares,File csvFile) 
	{
		try (FileOutputStream fic=new FileOutputStream(csvFile))
		{
			DecimalFormat df = new DecimalFormat("######.00"); //only 2 digit after .
					
			for (ShareDB shareDB : listShares)
			{
				QuoteDB quote=get(shareDB);
				if (quote==null)
				{
					logger.error("no quotation for "+
							shareDB.getTicker() +","+
							shareDB.getName());
				}
				else if (!quote.getQuotation().getLastTradedPrice().isValid())
				{
					logger.error("no last quotation for "+
							shareDB.getTicker() +","+
							shareDB.getName());
							;					
				}
				else
				{
					String value=df.format(quote.getQuotation().getLastTradedPrice().getValue());
					String valueWithComma=value.replace('.',',');
					String str=shareDB.getName()+"\t"+
						   shareDB.getTicker()+"\t"+
						   valueWithComma+"\n";
					fic.write(str.getBytes());
				}
			}

			//add the date			
			SimpleDateFormat format1 = new SimpleDateFormat("'\ngenerated 'dd-MM-yyyy 'at' HH:mm:ss'\n'");
			String formatted =format1.format(clock.getNow().getTime());
			fic.write(formatted.getBytes());
			
			fic.close();
			logger.debug(listShares.size()+" quotes written");
			return true;
		}
		catch (IOException e) 
		{
			logger.error(e);
			return false;
		}


	}
}
