package net.tuxanna.portefeuille.util;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class DigitValue
{
	private double value;
	private boolean isValid;
	private static final Logger logger = LogManager.getLogger(DigitValue.class);

	public DigitValue()
	{
		super();
		isValid=false;
		value=0.;
	}

	public DigitValue(final String valueToConvert)
	{
		super();
		try
		{
			String strWithoutSpace=valueToConvert.replaceAll("\\s","");
			value=Double.parseDouble(strWithoutSpace);
			if ((value == Double.MAX_VALUE) || (value == Double.MIN_VALUE))
			{
				logger.error("invalid input ["+valueToConvert+"]");
				isValid=false;
			}
			else
			{
				isValid=true;
			}
		}
		catch(java.lang.NumberFormatException e)
		{
			logger.error("invalid input ["+valueToConvert+"]");
			isValid=false;
		}
	}

	public DigitValue(final double val)
	{
		super();
		value=val;
		isValid=true;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(13, 31). // two randomly chosen prime numbers
				// if deriving: appendSuper(super.hashCode()).
				append(isValid).
				append(value).
				toHashCode();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (!(obj instanceof DigitValue))
			return false;
		if (obj == this)
			return true;

		DigitValue rhs = (DigitValue) obj;
		if (isValid == rhs.isValid)
		{
			if (isValid)
			{
				final double TOLERANCE=0.0001;
				if (Math.abs(value-rhs.value)<TOLERANCE)
				{
					return true;
				}
			}
			else
			{
				return true; //value should ne be considered
			}
		}
		return false;
	}

	public double getValue()
	{
		return value;
	}
	public void setValue(double value)
	{
		this.value = value;
	}
	public boolean isValid()
	{
		return isValid;
	}

	@Override
	public String toString()
	{
		return "DigitValue [value=" + value + ", isValid=" + isValid + "]";
	}


}
