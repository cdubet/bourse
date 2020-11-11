package net.tuxanna.portefeuille.business_logic;

import java.util.ArrayList;
import java.util.List;

public class QuoteVariation implements Comparable<QuoteVariation>
{
	private String name;
	private double oldPrice;
	private double newPrice;
	private double variation;
	
	public String getName()
	{
		return name;
	}



	public void setName(String name)
	{
		this.name = name;
	}



	public double getOldPrice()
	{
		return oldPrice;
	}



	public void setOldPrice(double oldPrice)
	{
		this.oldPrice = oldPrice;
	}



	public double getNewPrice()
	{
		return newPrice;
	}



	public void setNewPrice(double newPrice)
	{
		this.newPrice = newPrice;
	}



	public double getVariation()
	{
		return variation;
	}



	public void setVariation(double variation)
	{
		this.variation = variation;
	}



	@Override
	public String toString()
	{
		return "QuoteVariation [name=" + name + ", oldPrice=" + oldPrice + ", newPrice=" + newPrice + ", variation="
				+ variation + "%]";
	}

	public List<String>getHeaderForReport()
	{
		ArrayList<String> list=new ArrayList<String>();
		list.add("name");
		list.add("old");
		list.add("new");
		list.add("variation");
		return list;
	}
	
	public List<String>getContentForReport()
	{
		ArrayList<String> list=new ArrayList<String>();
		list.add(getName());
		
		{
			Double d=getOldPrice();
			list.add(d.toString());
		}
		{
			Double d=getNewPrice();
			list.add(d.toString());
		}
		
		// % with 2 digit
		String result = String.format("%.2f", getVariation());
		list.add(result);
		return list;
	}
	
	
	public QuoteVariation(String name, double oldPrice, double newPrice)
	{
		super();
		this.name = name;
		this.oldPrice = oldPrice;
		this.newPrice = newPrice;
		variation=100.0*(newPrice-oldPrice)/oldPrice;
	}

	@Override
	public int compareTo(QuoteVariation o)
	{
	    return (int)(( o.variation- this.variation)*10000.);
	}



}
