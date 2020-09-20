package net.tuxanna.portefeuille.businessLogic.util;

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
