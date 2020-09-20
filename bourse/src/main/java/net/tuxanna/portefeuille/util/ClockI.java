package net.tuxanna.portefeuille.util;

import java.util.Date;

public interface ClockI
{
	//allow to set date in junit tests
	public Date getNow();
}
