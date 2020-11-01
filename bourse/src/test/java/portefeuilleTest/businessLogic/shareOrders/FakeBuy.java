package portefeuilleTest.businessLogic.shareOrders;

public class FakeBuy
{

	private int idBuy; 
	private double boughtQteShare;
	private double currentValueShare;
	private double priceRequested;
	private int idPortfolio;
	private int idAccount;
	private int idShare;
	private String shareName;
	
	public FakeBuy(int idBuyShare, double boughtQteShare, double currentValueShare, double priceRequested,
			int idAccount, int idShare, String shareName, int idPortfolio)
	{
		super();
		this.idBuy = idBuyShare;
		this.boughtQteShare = boughtQteShare;
		this.currentValueShare = currentValueShare;
		this.priceRequested = priceRequested;
		this.idAccount = idAccount;
		this.idShare = idShare;
		this.shareName = shareName;
		this.idPortfolio=idPortfolio;
	}

	
	public int getIdBuy()
	{
		return idBuy;
	}
	public void setIdBuy(int idBuyShare)
	{
		this.idBuy = idBuyShare;
	}
	public double getBoughtQteShare()
	{
		return boughtQteShare;
	}
	public void setBoughtQteShare(double boughtQteShare)
	{
		this.boughtQteShare = boughtQteShare;
	}
	public double getCurrentValueShare()
	{
		return currentValueShare;
	}
	public void setCurrentValueShare(double currentValueShare)
	{
		this.currentValueShare = currentValueShare;
	}
	public int getIdAccount()
	{
		return idAccount;
	}
	public void setIdAccount(int idAccount)
	{
		this.idAccount = idAccount;
	}
	public int getIdShare()
	{
		return idShare;
	}
	public void setIdShare(int idShare)
	{
		this.idShare = idShare;
	}
	public String getShareName()
	{
		return shareName;
	}
	public void setShareName(String shareName)
	{
		this.shareName = shareName;
	}
	public double getPriceRequested()
	{
		return priceRequested;
	}
	public void setPriceRequested(double priceRequested)
	{
		this.priceRequested = priceRequested;
	}


	public int getIdPortfolio()
	{
		return idPortfolio;
	}


	public void setIdPortfolio(int idPortfolio)
	{
		this.idPortfolio = idPortfolio;
	}
	


}
