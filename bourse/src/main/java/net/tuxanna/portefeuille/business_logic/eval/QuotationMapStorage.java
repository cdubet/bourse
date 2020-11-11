package net.tuxanna.portefeuille.business_logic.eval;

import java.io.StringWriter;
import java.util.HashMap;

public class QuotationMapStorage
{
	private HashMap<ShareIdAndDate, Double> mapQuotation;
	
	public QuotationMapStorage()
	{
		mapQuotation =new HashMap<ShareIdAndDate, Double>(); 
	}

	public void add(ShareIdAndDate key, Double value)
	{
		mapQuotation.put(key, value);
	}
	
	public Double get(ShareIdAndDate key)
	{
		return mapQuotation.get(key);

	}

	@Override
	public String toString()
	{
		StringWriter str=new StringWriter();
		for (ShareIdAndDate shareAndDate : mapQuotation.keySet())
		{
			str.append("[");
			str.append(shareAndDate.toString());
			str.append("]->");
			str.append(get(shareAndDate).toString());
			str.append("],");
		}
		return "QuotationMapStorage [mapQuotation=" + str.toString() + "]";
	}
}
