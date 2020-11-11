package net.tuxanna.portefeuille.business_logic.eval;

import java.util.Comparator;
import net.tuxanna.portefeuille.database.PortfolioDB;


public class Evaluation implements Comparable<Evaluation>
{
	private int idShare;
	private double qte;
	private double boughtPrice;
	private double currentPrice;
	private double yesterdayPrice;
	private double variationInCurrency;
	private double variationInPercent;
	private double gain;
	private double gainInPercent;
	
	private String shareName;

	public Evaluation()
	{
		idShare=-1;
		qte= boughtPrice=currentPrice= yesterdayPrice= variationInCurrency= variationInPercent=-1.0;
	}

	public boolean fillWithPortfolioItems(PortfolioDB portfolioDB)
	{
		idShare=portfolioDB.getIdShare();
		if (portfolioDB.getQte().isValid())
		{
			qte=portfolioDB.getQte().getValue();
			if (portfolioDB.getUnitPrice().isValid())
			{
				boughtPrice=portfolioDB.getUnitPrice().getValue();
				return true;
			}
		}
		return false;
	}

	public int getIdShare()
	{
		return idShare;
	}

	public void setIdShare(int idShare)
	{
		this.idShare = idShare;
	}

	public double getQte()
	{
		return qte;
	}

	public void setQte(double qte)
	{
		this.qte = qte;
	}

	public double getBoughtPrice()
	{
		return boughtPrice;
	}

	public void setBoughtPrice(double boughtPrice)
	{
		this.boughtPrice = boughtPrice;
	}

	public double getCurrentPrice()
	{
		return currentPrice;
	}

	public void setCurrentPrice(double currentPrice)
	{
		this.currentPrice = currentPrice;
	}

	public double getYesterdayPrice()
	{
		return yesterdayPrice;
	}

	public void setYesterdayPrice(double yesterdayPrice)
	{
		this.yesterdayPrice = yesterdayPrice;
	}

	public double getVariationInCurrency()
	{
		return variationInCurrency;
	}

	public void setVariationInCurrency(double variationInCurrency)
	{
		this.variationInCurrency = variationInCurrency;
	}

	public double getVariationInPercent()
	{
		return variationInPercent;
	}

	public void setVariationInPercent(double variationInPercent)
	{
		this.variationInPercent = variationInPercent;
	}

	public String getShareName()
	{
		return shareName;
	}

	public void setShareName(String shareName)
	{
		this.shareName = shareName;
	}

	public void computeVariation()
	{
		variationInCurrency=(currentPrice-yesterdayPrice)*qte;
		variationInPercent=(((currentPrice-yesterdayPrice)*100.0)/yesterdayPrice) ;
		gain=(currentPrice-boughtPrice)*qte;
		gainInPercent=(100.0*(currentPrice-boughtPrice))/boughtPrice;
	}


	public double getGain()
	{
		return gain;
	}

	public void setGain(double gain)
	{
		this.gain = gain;
	}

	public double getGainInPercent()
	{
		return gainInPercent;
	}

	public void setGainInPercent(double gainInPercent)
	{
		this.gainInPercent = gainInPercent;
	}

	@Override
	public int compareTo(Evaluation o)
	{
		Comparator<String> nullSafeStringComparator = Comparator.nullsFirst(String::compareToIgnoreCase);
		int res=nullSafeStringComparator.compare(shareName, o.getShareName());
		//test name first to have alpha name list
		if (res==0)
		{
			res= idShare-o.getIdShare();
			if (res==0)
			{
				res= (int) (qte-o.getQte());
				if (res==0)
				{
					res= (int) (boughtPrice-o.getBoughtPrice());
					if (res==0)
					{
						res= (int) (currentPrice-o.getCurrentPrice());
						if (res==0)
						{
							res= (int) (yesterdayPrice-o.getYesterdayPrice());
							
							//
							// normally these items are now computed from other items of the class-> should be the same . but test them in case of
							if (res==0)
							{
								res= (int) (variationInCurrency-o.getVariationInCurrency());
								if (res==0)
								{
									res= (int) (variationInPercent-o.getVariationInPercent());
									if (res==0)
									{
										res= (int) (gain-o.getGain());
										if (res==0)
										{
											res= (int) (gainInPercent-o.getGainInPercent());
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return res;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(boughtPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(currentPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(gain);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(gainInPercent);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + idShare;
		temp = Double.doubleToLongBits(qte);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((shareName == null) ? 0 : shareName.hashCode());
		temp = Double.doubleToLongBits(variationInCurrency);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(variationInPercent);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(yesterdayPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	//just to have something coherent with compareTo
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evaluation other = (Evaluation) obj;
		if (Double.doubleToLongBits(boughtPrice) != Double.doubleToLongBits(other.boughtPrice))
			return false;
		if (Double.doubleToLongBits(currentPrice) != Double.doubleToLongBits(other.currentPrice))
			return false;
		if (Double.doubleToLongBits(gain) != Double.doubleToLongBits(other.gain))
			return false;
		if (Double.doubleToLongBits(gainInPercent) != Double.doubleToLongBits(other.gainInPercent))
			return false;
		if (idShare != other.idShare)
			return false;
		if (Double.doubleToLongBits(qte) != Double.doubleToLongBits(other.qte))
			return false;
		if (shareName == null)
		{
			if (other.shareName != null)
				return false;
		}
		else if (!shareName.equals(other.shareName))
			return false;
		if (Double.doubleToLongBits(variationInCurrency) != Double.doubleToLongBits(other.variationInCurrency))
			return false;
		if (Double.doubleToLongBits(variationInPercent) != Double.doubleToLongBits(other.variationInPercent))
			return false;
		if (Double.doubleToLongBits(yesterdayPrice) != Double.doubleToLongBits(other.yesterdayPrice))
			return false;
		return true;
	}

}
