package net.tuxanna.portefeuille.database;

import net.tuxanna.portefeuille.util.DigitValue;

public class BuyDB implements ShareOrderI
{
	

	private double qte;
	private double unitPriceRequested;

	private String shareName;
	private DigitValue unitPriceBought;
	
	private int idBuy;
	private int idShare;
	private int idAccount;
	private STATE_ORDER state;
	

	
	public BuyDB( double qte, double unitPriceRequested, int idShare, int idAccount)
	{
		super();
		this.qte = qte;
		this.unitPriceRequested = unitPriceRequested;
		this.idShare = idShare;
		this.idAccount = idAccount;
		state=STATE_ORDER.ACTIVE;
		unitPriceBought=new DigitValue();
		idBuy=Database.NON_ASSIGNED;
		shareName="";
	}

	public BuyDB(double qte, double unitPriceRequested, int idShare, int idAccount,int idSell)
	{
		super();
		this.qte = qte;
		this.unitPriceRequested = unitPriceRequested;
		this.idShare = idShare;
		this.idAccount = idAccount;
		state=STATE_ORDER.ACTIVE;
		unitPriceBought=new DigitValue();
		this.idBuy=idSell;
		shareName="";
	}

	
	
	public BuyDB()
	{
		super();
		qte = 0.;
		unitPriceRequested = 0.;
		state=STATE_ORDER.NO_LONGER_VALID;
		
		unitPriceBought=new DigitValue();
		idBuy=Database.NON_ASSIGNED;
		idShare = Database.NON_ASSIGNED;
		idAccount = Database.NON_ASSIGNED;
		shareName="";

	}

	public void setAsBought(DigitValue boughtValue)
	{
		setState(ShareOrderI.STATE_ORDER.EXECUTED);
		//we assume here that everything has been sold to the price we have requested. true normally for big capitalisation but wrong elsewehre
		setUnitPriceBought(boughtValue);
	}
	
	/* (non-Javadoc)
	 * @see net.tuxanna.portefeuille.database.ShareOrderI#getQte()
	 */
	@Override
	public double getQte()
	{
		return qte;
	}

	/* (non-Javadoc)
	 * @see net.tuxanna.portefeuille.database.ShareOrderI#setQte(double)
	 */
	@Override
	public void setQte(double qte)
	{
		this.qte = qte;
	}

	public double getUnitPriceRequested()
	{
		return unitPriceRequested;
	}

	public void setUnitPriceRequested(double unitPriceRequested)
	{
		this.unitPriceRequested = unitPriceRequested;
	}

	public DigitValue getUnitPriceBought()
	{
		return unitPriceBought;
	}

	public void setUnitPriceBought(DigitValue unitPrice)
	{
		this.unitPriceBought = unitPrice;
	}

	public int getIdBuy()
	{
		return idBuy;
	}

	public void setIdBuy(int idSell)
	{
		this.idBuy = idSell;
	}

	/* (non-Javadoc)
	 * @see net.tuxanna.portefeuille.database.ShareOrderI#getIdShare()
	 */
	@Override
	public int getIdShare()
	{
		return idShare;
	}

	public void setIdShare(int idShare)
	{
		this.idShare = idShare;
	}

	/* (non-Javadoc)
	 * @see net.tuxanna.portefeuille.database.ShareOrderI#getIdAccount()
	 */
	@Override
	public int getIdAccount()
	{
		return idAccount;
	}

	/* (non-Javadoc)
	 * @see net.tuxanna.portefeuille.database.ShareOrderI#setIdAccount(int)
	 */
	@Override
	public void setIdAccount(int idAccount)
	{
		this.idAccount = idAccount;
	}
	@Override
	public int getId()
	{
		return getIdBuy();
	}
	@Override
	public void setAsExecuted(DigitValue valueWhenExecuted)
	{
		setAsBought(valueWhenExecuted);
		
	}
	@Override
	public DigitValue getUnitPriceExecuted()
	{
		return getUnitPriceBought();
	}
	/* (non-Javadoc)
	 * @see net.tuxanna.portefeuille.database.ShareOrderI#getState()
	 */
	@Override
	public STATE_ORDER getState()
	{
		return state;
	}
	/* (non-Javadoc)
	 * @see net.tuxanna.portefeuille.database.ShareOrderI#setState(net.tuxanna.portefeuille.database.SellDB.STATE_ORDER)
	 */
	@Override
	public void setState(STATE_ORDER state)
	{
		this.state = state;
	}
	public String getShareName() {return shareName;}
	void setShareName(String newName) {shareName=newName;}
}
