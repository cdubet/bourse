package net.tuxanna.portefeuille.database;

import java.util.List;


public interface DatabaseI extends BuyAndSellI ,QuoteI, ShareI
{

	boolean loadSharesIdInPortfolio(List<Integer> listSharesId,boolean keepNonMeaningfullShares);
	boolean loadSharesInPortfolio(List<PortfolioDB> listSharesInPorfolios);
	
	PortfolioDB getShareInPortfolio(int sharedId, int accountId);
	
	boolean updatePortfolio(PortfolioDB portfolioDB);
	boolean updateSchema();

}