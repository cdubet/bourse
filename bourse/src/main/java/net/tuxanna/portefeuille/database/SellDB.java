package net.tuxanna.portefeuille.database;

import java.util.Date;
import net.tuxanna.portefeuille.util.DigitValue;

public class SellDB implements ShareOrderI
{
	

	private double qte;
	private double unitPriceRequested;
	private DigitValue unitPriceSold;
	private int idSell;
	private int idShare;
	private int idAccount;
	private STATE_ORDER state;
	private String shareName;
	
	
	public SellDB(Date dateExpiration, double qte, double unitPriceRequested, int idShare, int idAccount)
	{
		super();
	
		this.qte = qte;
		this.unitPriceRequested = unitPriceRequested;
		this.idShare = idShare;
		this.idAccount = idAccount;
		state=STATE_ORDER.ACTIVE;
		unitPriceSold=new DigitValue();
		idSell=Database.NON_ASSIGNED;
		shareName="";
	}

	public SellDB(double qte, double unitPriceRequested, int idShare, int idAccount,int idSell)
	{
		super();
	
		this.qte = qte;
		this.unitPriceRequested = unitPriceRequested;
		this.idShare = idShare;
		this.idAccount = idAccount;
		state=STATE_ORDER.ACTIVE;
		unitPriceSold=new DigitValue();
		this.idSell=idSell;
		shareName="";
	}

	
	
	public SellDB()
	{
		super();
		
		this.qte = 0.;
		this.unitPriceRequested = 0.;
		state=STATE_ORDER.NO_LONGER_VALID;
		
		unitPriceSold=new DigitValue();
		idSell=Database.NON_ASSIGNED;
		idShare = Database.NON_ASSIGNED;
		idAccount = Database.NON_ASSIGNED;
		shareName="";

	}
	@Override
	public void setAsExecuted(DigitValue soldValue)
	{
		setState(ShareOrderI.STATE_ORDER.EXECUTED);
		//we assume here that everything has been sold to the price we have requested. true normally for big capitalisation but wrong elsewehre
		setUnitPriceSold(soldValue);
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

	@Override
	public double getUnitPriceRequested()
	{
		return unitPriceRequested;
	}

	public void setUnitPriceRequested(double unitPriceRequested)
	{
		this.unitPriceRequested = unitPriceRequested;
	}
	@Override
	public DigitValue getUnitPriceExecuted()
	{
		return unitPriceSold;
	}

	public void setUnitPriceSold(DigitValue unitPriceSold)
	{
		this.unitPriceSold = unitPriceSold;
	}

	public int getIdSell()
	{
		return idSell;
	}

	public void setIdSell(int idSell)
	{
		this.idSell = idSell;
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
		// TODO Auto-generated method stub
		return getIdSell();
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
