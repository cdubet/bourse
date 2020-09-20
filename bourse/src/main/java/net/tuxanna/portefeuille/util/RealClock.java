package net.tuxanna.portefeuille.util;

import java.util.Date;

public class RealClock implements ClockI
{

	public RealClock()
	{
		
	}

	@Override
	public Date getNow()
	{
		Date now=new Date( System.currentTimeMillis());
		return now;
	}

}
