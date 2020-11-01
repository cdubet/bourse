package portefeuilleTest.businessLogic.shareOrders;


public class FakeSell
{
	Integer idSell=1;
	Integer idShare;
	Integer idAccount;
	Double qte;
	
	public FakeSell(Integer idSell, Integer idShare, Integer idAccount, Double qte)
	{
		super();
		this.idSell = idSell;
		this.idShare = idShare;
		this.idAccount = idAccount;
		this.qte = qte;
	}

	public Integer getIdSell()
	{
		return idSell;
	}

	public void setIdSell(Integer idSell)
	{
		this.idSell = idSell;
	}

	public Integer getIdShare()
	{
		return idShare;
	}

	public void setIdShare(Integer idShare)
	{
		this.idShare = idShare;
	}

	public Integer getIdAccount()
	{
		return idAccount;
	}

	public void setIdAccount(Integer idAccount)
	{
		this.idAccount = idAccount;
	}

	public Double getQte()
	{
		return qte;
	}

	public void setQte(Double qte)
	{
		this.qte = qte;
	}



}
