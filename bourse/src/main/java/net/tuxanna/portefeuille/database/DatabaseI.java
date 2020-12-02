package net.tuxanna.portefeuille.database;

public interface DatabaseI extends BuyAndSellI ,QuoteI, ShareI, PortfolioI
{
	boolean updateSchema();
}