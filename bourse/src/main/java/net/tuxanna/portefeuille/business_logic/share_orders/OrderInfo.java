package net.tuxanna.portefeuille.business_logic.share_orders;

import java.util.ArrayList;
import java.util.List;

import net.tuxanna.portefeuille.database.SellDB;
import net.tuxanna.portefeuille.database.ShareOrderI;

public class OrderInfo implements Comparable<OrderInfo>
{
	private ShareOrderI shareOrderDTO;
	
	public OrderInfo(ShareOrderI sellDB, String shareName)
	{
		super();
		this.shareOrderDTO = sellDB;
	}
	
	public ShareOrderI getSellDB()
	{
		return shareOrderDTO;
	}
	public void setSellDB(SellDB sellDB)
	{
		this.shareOrderDTO = sellDB;
	}
	public String getShareName()
	{
		return shareOrderDTO.getShareName();
	}


	
	public List<String> toText()
	{
		List<String> listText=new ArrayList<String>(); 
		listText.add(getShareName());
		// with 2 digit
		listText.add( String.format("%.2f", shareOrderDTO.getQte()));
		if (shareOrderDTO.getUnitPriceExecuted().isValid())
		{
			listText.add( String.format("%.2f", shareOrderDTO.getUnitPriceExecuted().getValue()));
		}
		else
		{
			listText.add( "???");
		}
		return listText;
	}

	@Override
	public int compareTo(OrderInfo o)
	{
		return (getShareName().compareTo(o.getShareName()));
	}
	


}
