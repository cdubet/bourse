package net.tuxanna.portefeuille.business_logic;

import net.tuxanna.portefeuille.database.ShareDB;

public class Share
{
	private  ShareDB shareDB;
	private boolean isRequiredForVariation;
	
	public Share(ShareDB shareDB, boolean isRequiredForVariation)
	{
		super();
		this.shareDB = shareDB;
		this.isRequiredForVariation = isRequiredForVariation;
	}

	public ShareDB getShareDB()
	{
		return shareDB;
	}

	public void setShareDB(ShareDB shareDB)
	{
		this.shareDB = shareDB;
	}

	public boolean isRequiredForVariation()
	{
		return isRequiredForVariation;
	}

	public void setRequiredForVariation(boolean isRequiredForVariation)
	{
		this.isRequiredForVariation = isRequiredForVariation;
	}

	@Override
	public String toString()
	{
		return "Share [shareDB=" + shareDB + ", isRequiredForVariation=" + isRequiredForVariation + "]";
	}



}
