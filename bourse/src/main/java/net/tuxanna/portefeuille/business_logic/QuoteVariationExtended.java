package net.tuxanna.portefeuille.business_logic;

import java.util.List;

import net.tuxanna.portefeuille.database.QuoteDB;
import net.tuxanna.portefeuille.database.QuoteI;
import net.tuxanna.portefeuille.database.SearchQuoteAtOneDay;
import net.tuxanna.portefeuille.database.ShareDB;

public class QuoteVariationExtended extends QuoteVariation
{
	private ShareDB shareDB;
	java.util.Date newDateBefore;
	QuoteI database;
	
	public QuoteVariationExtended(QuoteI p_database,java.util.Date p_newDateBefore,ShareDB share, double oldPrice, double newPrice)
	{
		super(share.getName(), oldPrice, newPrice);
		shareDB=share;
		database=p_database;
		newDateBefore=p_newDateBefore;
	}

	@Override
	public List<String>getHeaderForReport()
	{
		List<String> list=super.getHeaderForReport();
		
		list.add("% 1 week");
		return list;
	}
	@Override
	public List<String>getContentForReport()
	{
		List<String> list=super.getContentForReport();
		
		SearchQuoteAtOneDay search=new SearchQuoteAtOneDay(shareDB, newDateBefore);
		QuoteDB quoteDB=database.readQuotationAtOneDay(search);
		if (quoteDB.getQuotation().getLastTradedPrice().isValid())
		{
			double priceOneWeekOld=quoteDB.getQuotation().getLastTradedPrice().getValue();
			double variation=100.0*(getNewPrice()-priceOneWeekOld)/priceOneWeekOld;
			// % with 2 digit
			String result = String.format("%.2f", variation);
			list.add(result);
		}
		else
		{
			list.add("???");
		}
		return list;
	}
}
