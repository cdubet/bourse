package net.tuxanna.portefeuille.database;

import java.util.List;

public interface PortfolioI
{
	boolean loadSharesIdInPortfolio(List<Integer> listSharesId,boolean keepNonMeaningfullShares);
	boolean loadSharesInPortfolio(List<PortfolioDB> listSharesInPorfolios);
	
	List<PortfolioDB> loadSharesInPortfolio(ConditionPortfolioI condition);

	
	PortfolioDB getShareInPortfolio(int sharedId, int accountId);

	boolean updatePortfolio(PortfolioDB portfolioDB);
	boolean updateListPortfolio(List<PortfolioDB> listToSave);
}
