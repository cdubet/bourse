package net.tuxanna.portefeuille.business_logic.util;

public class ProblemNotification
{
	String problemDescription;
	public ProblemNotification()
	{
		problemDescription=new String();
	}
	public ProblemNotification(String str)
	{
		problemDescription=str;
	}
	@Override
	public String toString()
	{
		return problemDescription;
	}
	
}
