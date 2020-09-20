package net.tuxanna.portefeuille.database;

import net.tuxanna.portefeuille.util.DigitValue;



public interface ShareOrderI
{
	public enum STATE_ORDER {ACTIVE, EXECUTED, NO_LONGER_VALID};

	STATE_ORDER getState();

	void setState(STATE_ORDER state);

	double getQte();

	void setQte(double qte);

	int getIdShare();
	String getShareName();
	
	int getId();
	int getIdAccount();

	void setIdAccount(int idAccount);

	double getUnitPriceRequested();

	void setAsExecuted(DigitValue ValueWhenExecuted);

	DigitValue getUnitPriceExecuted();

}