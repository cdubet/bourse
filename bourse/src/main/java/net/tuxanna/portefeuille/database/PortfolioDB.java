package net.tuxanna.portefeuille.database;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import net.tuxanna.portefeuille.util.DigitValue;

public class PortfolioDB
{
	private int idPortfolio;
	private int idShare;
	private int idAccount;
	private DigitValue qte;
	private DigitValue unitPrice;
	private boolean useForSummary;
	
	public PortfolioDB()
	{
		idPortfolio=Database.NON_ASSIGNED;
		idShare=Database.NON_ASSIGNED;
		idAccount=Database.NON_ASSIGNED;
		qte=new DigitValue();
		unitPrice=new DigitValue();
		useForSummary=true;
	}

	public int getIdPortfolio()
	{
		return idPortfolio;
	}

	public void setIdPortfolio(int idPortfolio)
	{
		this.idPortfolio = idPortfolio;
	}

	public int getIdShare()
	{
		return idShare;
	}

	public void setIdShare(int idShare)
	{
		this.idShare = idShare;
	}

	public int getIdAccount()
	{
		return idAccount;
	}

	public void setIdAccount(int idAccount)
	{
		this.idAccount = idAccount;
	}

	public DigitValue getQte()
	{
		return qte;
	}

	public void setQte(DigitValue qte)
	{
		this.qte = qte;
	}

	public DigitValue getUnitPrice()
	{
		return unitPrice;
	}

	public void setUnitPrice(DigitValue unitPrice)
	{
		this.unitPrice = unitPrice;
	}

	public boolean isUseForSummary()
	{
		return useForSummary;
	}

	public void setUseForSummary(boolean useForSummary)
	{
		this.useForSummary = useForSummary;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(7, 13). // two randomly chosen prime numbers
				// if deriving: appendSuper(super.hashCode()).
				append(useForSummary).
				append(unitPrice).
				append(qte).
				append(idAccount).
				append(idPortfolio).
				append(idShare).
				toHashCode();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (!(obj instanceof PortfolioDB))
			return false;
		if (obj == this)
			return true;

		PortfolioDB rhs = (PortfolioDB) obj;
		return new EqualsBuilder().
				// if deriving: appendSuper(super.equals(obj)).
				append(useForSummary,rhs.useForSummary).
				append(unitPrice,rhs.unitPrice).
				append(qte,rhs.qte).
				append(idAccount,rhs.idAccount).
				append(idPortfolio,rhs.idPortfolio).
				append(idShare,rhs.idShare).
				isEquals();
	}

}
