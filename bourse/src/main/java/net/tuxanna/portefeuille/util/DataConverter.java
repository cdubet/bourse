package net.tuxanna.portefeuille.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.tuxanna.portefeuille.database.ShareDB;

public class DataConverter
{

	private static final Logger logger = LogManager.getLogger(DataConverter.class);

	
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
