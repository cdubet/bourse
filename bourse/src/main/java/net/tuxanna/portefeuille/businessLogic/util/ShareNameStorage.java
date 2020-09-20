package net.tuxanna.portefeuille.businessLogic.util;

import java.util.HashMap;
import java.util.List;

import net.tuxanna.portefeuille.database.ShareDB;



public class ShareNameStorage
{
	private HashMap<Integer, String> mapShareName;
	public ShareNameStorage()
	{
		mapShareName=new HashMap<Integer, String> ();
	}

	public void fill(List<ShareDB> listShare)
	{
		for (ShareDB shareDB : listShare)
		{
			mapShareName.put(shareDB.getId(), shareDB.getName());
		}
	}
	
	public String getName(int shareId)
	{
		return mapShareName.get(shareId);
	}
}
