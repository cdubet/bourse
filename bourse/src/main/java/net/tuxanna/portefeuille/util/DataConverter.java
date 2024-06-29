package net.tuxanna.portefeuille.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.tuxanna.portefeuille.dataFeed.TickerI;
import net.tuxanna.portefeuille.dataFeed.TickerI.TypeOfItem;
import net.tuxanna.portefeuille.database.ShareDB;

public class DataConverter
{

	private static final Logger logger = LogManager.getLogger(DataConverter.class);
	
	static public String  convertIsShareToString(TypeOfItem typeOfItem)
	{
		switch (typeOfItem)
		{
		case TYPE_SHARE:
			return "Y";
		case TYPE_SICAV:
			return "N";
		case TYPE_TRACKER:
			return "T";
		default:
			logger.error("unknown type"+typeOfItem);
			return "Y";
		}
	}
	
	static public TypeOfItem convertIsShare(char isShareChar)
	{
		if ((isShareChar=='y') || (isShareChar=='Y'))
		{
			return TickerI.TypeOfItem.TYPE_SHARE;
		}
		if ((isShareChar=='n') || (isShareChar=='N'))
		{
			return TickerI.TypeOfItem.TYPE_SICAV;
		}
		if ((isShareChar=='t') || (isShareChar=='T'))
		{
			return TickerI.TypeOfItem.TYPE_TRACKER;
		}
		else
		{
			logger.error("unknown type "+isShareChar);
			return TickerI.TypeOfItem.TYPE_SHARE;
		}
	}
	
	static public ShareDB.Currency convertToCurrency(char currencyChar)
	{
		ShareDB.Currency currency;
		if ((currencyChar == '€') || (currencyChar=='E') || (currencyChar=='e'))
		{
			currency = ShareDB.Currency.EURO;
		}
		else if (currencyChar == '$')
		{
			currency = ShareDB.Currency.DOLLAR_US;
		}
		else if ((currencyChar == 'F')|| (currencyChar=='f'))
		{
			currency = ShareDB.Currency.CHF;
		}
		else if ((currencyChar == '£') || (currencyChar=='L') || (currencyChar=='l'))
		{
			currency = ShareDB.Currency.ENGLISH_POUND;
		}
		else
		{
			logger.error("unexpected value for currency :"+currencyChar);
			currency = ShareDB.Currency.EURO;
		}
		return currency;
	}
	
	static public String convertCurrencyToString(ShareDB.Currency currency)
	{
		if (currency == ShareDB.Currency.EURO)
		{
			return "E";
		}
		else if (currency == ShareDB.Currency.DOLLAR_US)
		{
			return "$";
		}
		else if (currency == ShareDB.Currency.CHF)
		{
			return "F";
		}
		else if (currency == ShareDB.Currency.ENGLISH_POUND)
		{
			return "£";
		}
		else
		{
			logger.error("unexpected value for currency :"+currency);
			return "E";
		}
	}

}
