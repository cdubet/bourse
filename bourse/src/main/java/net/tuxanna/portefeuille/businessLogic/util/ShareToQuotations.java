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
import java.util.Optional;

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
		shareToQuote=new HashMap<>();
		clock=new RealClock();
	}

	public Optional<QuoteDB> getQuotationFromList( int quoteId)
	{
		Integer key=quoteId;
		QuoteDB quoteDB = shareToQuote.get(key);
		return Optional.ofNullable(quoteDB);
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
		List<QuoteDB> listOfQuotationsDB=new ArrayList<>(shareToQuote.values());
		return listOfQuotationsDB;
	}

	public boolean exportQuoteCsv(List<ShareDB> listShares,File csvFile) 
	{
		if (listShares.isEmpty())
		{
			return true;
		}
		try (FileOutputStream fic=new FileOutputStream(csvFile))
		{
			DecimalFormat df = new DecimalFormat("######.00"); //only 2 digit after .
				
			//list is sorted by ID
			int expectedId = listShares.get(0).getId();
			
			for (ShareDB shareDB : listShares)
			{
				String str;
				if (expectedId==shareDB.getId())
				{
					str=printOneShare( df, shareDB);
				}
				else
				{
					StringBuilder textWithMissingId=new StringBuilder();
					for (int i=expectedId;i<shareDB.getId();++i)
					{
						textWithMissingId.append(i+"\tno more in DB\n");
					}
					textWithMissingId.append(printOneShare( df, shareDB));
					str=textWithMissingId.toString();
				}
				fic.write(str.getBytes());
				expectedId=shareDB.getId()+1;
			}

			//add the date			
			SimpleDateFormat format1 = new SimpleDateFormat("'\ngenerated 'dd-MM-yyyy 'at' HH:mm:ss'\n'");
			String formatted =format1.format(clock.getNow().getTime());
			fic.write(formatted.getBytes());
			
			logger.debug("{} quotes written",listShares.size() );
			return true;
		}
		catch (IOException e) 
		{
			logger.error(e);
			return false;
		}


	}

	private String printOneShare(DecimalFormat df, ShareDB shareDB) throws IOException
	{
		QuoteDB quote=get(shareDB);
		if (quote==null)
		{
			logger.error("no quotation for {},{}",	shareDB.getTicker() ,shareDB.getName());
			final String str=shareDB.getId()+"\tno quotation\n";
			return str;
		}
		else if (!quote.getQuotation().getLastTradedPrice().isValid())
		{
			logger.error("no last quotation for {},{}",	shareDB.getTicker(),shareDB.getName());
			final String str=shareDB.getId()+"\tno Last quotation\n";
			return str;
		}
		else
		{
			String value=df.format(quote.getQuotation().getLastTradedPrice().getValue());
			
			final String str=shareDB.getId()+"\t"+
					shareDB.getName()+"\t"+
					shareDB.getTicker()+"\t"+
					value+"\n";
			return str;
		}
	}
}
