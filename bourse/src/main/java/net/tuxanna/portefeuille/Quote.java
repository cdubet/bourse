package net.tuxanna.portefeuille;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.tuxanna.portefeuille.util.DigitValue;

public class Quote
{
	private DigitValue lastTradedPrice;//QuoteType.LAST_TRADED_PRICE
	private DigitValue openPrice;//OPEN_PRICE
	private DigitValue highPrice;//HIGH_PRICE
	private DigitValue lowPrice;//LOW_PRICE
	private DigitValue volume;//VOLUME
	private static final Logger logger = LogManager.getLogger(Quote.class);
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
				// if deriving: appendSuper(super.hashCode()).
				append(lastTradedPrice).
				append(openPrice).
				append(highPrice).
				append(lowPrice).
				append(volume).
				toHashCode();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (!(obj instanceof Quote))
			return false;
		if (obj == this)
			return true;

		Quote rhs = (Quote) obj;
		return new EqualsBuilder().
				// if deriving: appendSuper(super.equals(obj)).
				append(lastTradedPrice,rhs.lastTradedPrice).
				append(openPrice,rhs.openPrice).
				append(highPrice,rhs.highPrice).
				append(lowPrice,rhs.lowPrice).
				append(volume,rhs.volume).
				isEquals();
	}




	public Quote()
	{
		super();
		//init to defaut values for all
		lastTradedPrice=new DigitValue();//QuoteType.LAST_TRADED_PRICE
		openPrice=new DigitValue();//OPEN_PRICE
		highPrice=new DigitValue();//HIGH_PRICE
		lowPrice=new DigitValue();//LOW_PRICE
		volume=new DigitValue();//VOLUME
	}

//	public Quote(String[] quotes)
//	{
//		super();
//		//init to defaut values for all
//		lastTradedPrice=new DigitValue();//QuoteType.LAST_TRADED_PRICE
//		openPrice=new DigitValue();//OPEN_PRICE
//		highPrice=new DigitValue();//HIGH_PRICE
//		lowPrice=new DigitValue();//LOW_PRICE
//		volume=new DigitValue();//VOLUME
//
//		int index=0;
//		if (index < quotes.length)
//		{
//			setLastTradedPrice(quotes[index]);
//			index++;
//			if (index < quotes.length)
//			{
//				setChangeInPrice(quotes[index]);
//				index++;
//				if (index < quotes.length)
//				{
//					setOpenPrice(quotes[index]);
//					index++;
//					if (index < quotes.length)
//					{
//						setHighPrice(quotes[index]);
//						index++;
//						if (index < quotes.length)
//						{
//							setLowPrice(quotes[index]);
//							index++;
//							if (index < quotes.length)
//							{
//								setVolume(quotes[index]);
//								index++;
//								if (index < quotes.length)
//								{
//									setLow52Week(quotes[index]);
//									index++;
//									if (index < quotes.length)
//									{
//										setHigh52Week(quotes[index]);
//										index++;
//										if (index < quotes.length)
//										{
//											setMobileAverage50Days(quotes[index]);
//											index++;
//											if (index < quotes.length)
//											{
//												setMobileAverage200Days(quotes[index]);
//												index++;
//												if (index < quotes.length)
//												{
//													setPreviousClose(quotes[index]);
//													index++;
//													if (index < quotes.length)
//													{
//														setPeRatio(quotes[index]);
//														index++;
//														if (index < quotes.length)
//														{
//															setShortRatio(quotes[index]);
//														}
//													}
//												}
//											}
//										}
//									}
//								}
//							}
//						}
//					}
//				}
//			}
//		}
//	}


	public DigitValue getLastTradedPrice()
	{
		return lastTradedPrice;
	}

	public void setLastTradedPrice(String p_lastTradedPrice)
	{
		this.lastTradedPrice =new  DigitValue(p_lastTradedPrice);
		if (lastTradedPrice.isValid() && lastTradedPrice.getValue()==0.0)
		{
			logger.error("invalid p_lastTradedPrice={}",p_lastTradedPrice);
		}
	}
	public void setLastTradedPrice(double lastTradedPrice2)
	{
		this.lastTradedPrice =new  DigitValue(lastTradedPrice2);
	}

	public DigitValue getOpenPrice()
	{
		return openPrice;
	}

	public void setOpenPrice(String p_openPrice)
	{
		this.openPrice = new  DigitValue(p_openPrice);
		if (openPrice.isValid() && openPrice.getValue()==0.0)
		{
			logger.error("invalid p_openPrice={}",p_openPrice);
		}
	}
	public void setOpenPrice(double openPrice)
	{
		this.openPrice = new  DigitValue(openPrice);
	}

	public DigitValue getHighPrice()
	{
		return highPrice;
	}

	public void setHighPrice(String highPrice)
	{
		this.highPrice = new  DigitValue(highPrice);
	}
	public void setHighPrice(double highPrice)
	{
		this.highPrice = new  DigitValue(highPrice);
	}

	public DigitValue getLowPrice()
	{
		return lowPrice;
	}

	public void setLowPrice(String lowPrice)
	{
		this.lowPrice = new  DigitValue(lowPrice);
	}
	public void setLowPrice(double lowPrice)
	{
		this.lowPrice = new  DigitValue(lowPrice);
	}

	public DigitValue getVolume()
	{
		return volume;
	}

	public void setVolume(String volume)
	{
		this.volume = new  DigitValue(volume);
	}
	public void setVolume(double volume2)
	{
		volume=new DigitValue(volume2);
	}

	public boolean isLastTradedPriceValid()
	{
		if (getLastTradedPrice().isValid() 
				//do not check this or we will drop quote when market is closed
				//&& getVolume().isValid() && (getVolume().getValue()>0.)	
				)
		{
			return true;
		}
		return false;
	}
	
	@Override
	public String toString()
	{
		return "Quote [lastTradedPrice=" + lastTradedPrice + ", openPrice="
				+ openPrice + ", highPrice=" + highPrice + ", lowPrice=" + lowPrice + ", volume=" + volume
				+ "]";
	}

	public boolean isValid()
	{
		return isLastTradedPriceValid();//minimal info
	}

	public void divideShare(Double divideShareRatio)
	{
		if (lastTradedPrice.isValid())
		{
			lastTradedPrice.setValue(lastTradedPrice.getValue()/divideShareRatio);
		}
		if (openPrice.isValid())
		{
			openPrice.setValue(openPrice.getValue()/divideShareRatio);
		}
		if (highPrice.isValid())
		{
			highPrice.setValue(highPrice.getValue()/divideShareRatio);
		}
		if (lowPrice.isValid())
		{
			lowPrice.setValue(lowPrice.getValue()/divideShareRatio);
		}		
		if (volume.isValid())
		{
			volume.setValue(volume.getValue() * divideShareRatio);
		}		

		
	}




}
