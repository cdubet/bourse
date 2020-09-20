package net.tuxanna.portefeuille;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.tuxanna.portefeuille.util.DigitValue;

public class Quote
{
	private DigitValue lastTradedPrice;//QuoteType.LAST_TRADED_PRICE
	private DigitValue changeInPrice; // CHANGE_IN_PRICE
	private DigitValue openPrice;//OPEN_PRICE
	private DigitValue highPrice;//HIGH_PRICE
	private DigitValue lowPrice;//LOW_PRICE
	private DigitValue volume;//VOLUME
	private DigitValue low52Week;//LOW_52W
	private DigitValue high52Week;//HIGH_52W
	private DigitValue mobileAverage50Days;//MA_50_DAY
	private DigitValue mobileAverage200Days;//MA_200_DAY
	private DigitValue previousClose;//PREVIOUS_CLOSE
	private DigitValue peRatio;//PE_RATIO
	private DigitValue shortRatio;//SHORT_RATIO
	private static final Logger logger = LogManager.getLogger(Quote.class);
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
				// if deriving: appendSuper(super.hashCode()).
				append(lastTradedPrice).
				append(changeInPrice).
				append(openPrice).
				append(highPrice).
				append(lowPrice).
				append(volume).
				append(low52Week).
				append(high52Week).
				append(mobileAverage50Days).
				append(mobileAverage200Days).
				append(previousClose).
				append(peRatio).
				append(shortRatio).
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
				append(changeInPrice,rhs.changeInPrice).
				append(openPrice,rhs.openPrice).
				append(highPrice,rhs.highPrice).
				append(lowPrice,rhs.lowPrice).
				append(volume,rhs.volume).
				append(low52Week,rhs.low52Week).
				append(high52Week,rhs.high52Week).
				append(mobileAverage50Days,rhs.mobileAverage50Days).
				append(mobileAverage200Days,rhs.mobileAverage200Days).
				append(previousClose,rhs.previousClose).
				append(peRatio,rhs.peRatio).
				append(shortRatio,rhs.shortRatio).
				isEquals();
	}




	public Quote()
	{
		super();
		//init to defaut values for all
		lastTradedPrice=new DigitValue();//QuoteType.LAST_TRADED_PRICE
		changeInPrice=new DigitValue(); // CHANGE_IN_PRICE
		openPrice=new DigitValue();//OPEN_PRICE
		highPrice=new DigitValue();//HIGH_PRICE
		lowPrice=new DigitValue();//LOW_PRICE
		volume=new DigitValue();//VOLUME
		low52Week=new DigitValue();//LOW_52W
		high52Week=new DigitValue();//HIGH_52W
		mobileAverage50Days=new DigitValue();//MA_50_DAY
		mobileAverage200Days=new DigitValue();//MA_200_DAY
		previousClose=new DigitValue();//PREVIOUS_CLOSE
		peRatio=new DigitValue();//PE_RATIO
		shortRatio=new DigitValue();//SHORT_RATIO
	}

	public Quote(String[] quotes)
	{
		super();
		//init to defaut values for all
		lastTradedPrice=new DigitValue();//QuoteType.LAST_TRADED_PRICE
		changeInPrice=new DigitValue(); // CHANGE_IN_PRICE
		openPrice=new DigitValue();//OPEN_PRICE
		highPrice=new DigitValue();//HIGH_PRICE
		lowPrice=new DigitValue();//LOW_PRICE
		volume=new DigitValue();//VOLUME
		low52Week=new DigitValue();//LOW_52W
		high52Week=new DigitValue();//HIGH_52W
		mobileAverage50Days=new DigitValue();//MA_50_DAY
		mobileAverage200Days=new DigitValue();//MA_200_DAY
		previousClose=new DigitValue();//PREVIOUS_CLOSE
		peRatio=new DigitValue();//PE_RATIO
		shortRatio=new DigitValue();//SHORT_RATIO

		int index=0;
		if (index < quotes.length)
		{
			setLastTradedPrice(quotes[index]);
			index++;
			if (index < quotes.length)
			{
				setChangeInPrice(quotes[index]);
				index++;
				if (index < quotes.length)
				{
					setOpenPrice(quotes[index]);
					index++;
					if (index < quotes.length)
					{
						setHighPrice(quotes[index]);
						index++;
						if (index < quotes.length)
						{
							setLowPrice(quotes[index]);
							index++;
							if (index < quotes.length)
							{
								setVolume(quotes[index]);
								index++;
								if (index < quotes.length)
								{
									setLow52Week(quotes[index]);
									index++;
									if (index < quotes.length)
									{
										setHigh52Week(quotes[index]);
										index++;
										if (index < quotes.length)
										{
											setMobileAverage50Days(quotes[index]);
											index++;
											if (index < quotes.length)
											{
												setMobileAverage200Days(quotes[index]);
												index++;
												if (index < quotes.length)
												{
													setPreviousClose(quotes[index]);
													index++;
													if (index < quotes.length)
													{
														setPeRatio(quotes[index]);
														index++;
														if (index < quotes.length)
														{
															setShortRatio(quotes[index]);
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}


	public DigitValue getLastTradedPrice()
	{
		return lastTradedPrice;
	}

	public void setLastTradedPrice(String p_lastTradedPrice)
	{
		this.lastTradedPrice =new  DigitValue(p_lastTradedPrice);
		if (lastTradedPrice.isValid() && lastTradedPrice.getValue()==0.0)
		{
			logger.error("invalid p_lastTradedPrice=["+p_lastTradedPrice+"]");
		}
	}
	public void setLastTradedPrice(double lastTradedPrice2)
	{
		this.lastTradedPrice =new  DigitValue(lastTradedPrice2);
	}

	public DigitValue getChangeInPrice()
	{
		return changeInPrice;
	}

	public void setChangeInPrice(String p_changeInPrice)
	{
		this.changeInPrice = new  DigitValue(p_changeInPrice);
		if (changeInPrice.isValid() && changeInPrice.getValue()==0.0)
		{
			logger.error("invalid p_changeInPrice=["+p_changeInPrice+"]");
		}
	}
	public void setChangeInPrice(double changeInPrice)
	{
		this.changeInPrice = new  DigitValue(changeInPrice);
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
			logger.error("invalid p_openPrice=["+p_openPrice+"]");
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
	public DigitValue getLow52Week()
	{
		return low52Week;
	}

	public void setLow52Week(String low52Week)
	{
		this.low52Week = new  DigitValue(low52Week);
	}
	public void setLow52Week(double low52Week)
	{
		this.low52Week = new  DigitValue(low52Week);
	}

	public DigitValue getHigh52Week()
	{
		return high52Week;
	}

	public void setHigh52Week(String high52Week)
	{
		this.high52Week = new  DigitValue(high52Week);
	}
	public void setHigh52Week(double high52Week)
	{
		this.high52Week = new  DigitValue(high52Week);
	}

	public DigitValue getMobileAverage50Days()
	{
		return mobileAverage50Days;
	}

	public void setMobileAverage50Days(String mobileAverage50Days)
	{
		this.mobileAverage50Days = new  DigitValue(mobileAverage50Days);
	}
	public void setMobileAverage50Days(double mobileAverage50Days)
	{
		this.mobileAverage50Days = new  DigitValue(mobileAverage50Days);
	}

	public DigitValue getMobileAverage200Days()
	{
		return mobileAverage200Days;
	}

	public void setMobileAverage200Days(String mobileAverage200Days)
	{
		this.mobileAverage200Days = new  DigitValue(mobileAverage200Days);
	}
	public void setMobileAverage200Days(double mobileAverage200Days)
	{
		this.mobileAverage200Days = new  DigitValue(mobileAverage200Days);
	}

	public DigitValue getPreviousClose()
	{
		return previousClose;
	}

	public void setPreviousClose(String p_previousClose)
	{
		this.previousClose = new  DigitValue(p_previousClose);
		if (previousClose.isValid() && previousClose.getValue()==0.0)
		{
			logger.error("invalid p_previousClose=["+p_previousClose+"]");
		}
	}
	public void setPreviousClose(double previousClose)
	{
		this.previousClose = new  DigitValue(previousClose);
	}
	public DigitValue getPeRatio()
	{
		return peRatio;
	}

	public void setPeRatio(String peRatio)
	{
		this.peRatio = new  DigitValue(peRatio);
	}
	public void setPeRatio(double peRatio)
	{
		this.peRatio = new  DigitValue(peRatio);
	}
	public DigitValue getShortRatio()
	{
		return shortRatio;
	}

	public void setShortRatio(String shortRatio)
	{
		this.shortRatio = new  DigitValue(shortRatio);
	}


	public void setShortRatio(double shortRatio)
	{
		this.shortRatio = new  DigitValue(shortRatio);
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
		return "Quote [lastTradedPrice=" + lastTradedPrice + ", changeInPrice=" + changeInPrice + ", openPrice="
				+ openPrice + ", highPrice=" + highPrice + ", lowPrice=" + lowPrice + ", volume=" + volume
				+ ", low52Week=" + low52Week + ", high52Week=" + high52Week + ", mobileAverage50Days="
				+ mobileAverage50Days + ", mobileAverage200Days=" + mobileAverage200Days + ", previousClose="
				+ previousClose + ", peRatio=" + peRatio + ", shortRatio=" + shortRatio + "]";
	}




}
