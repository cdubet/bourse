package net.tuxanna.portefeuille.database;

import java.util.Date;
import java.util.List;

public interface DatabaseI
{

	boolean loadShares(List<ShareDB> listShares);
	boolean loadSharesIdInPortfolio(List<Integer> listSharesId,boolean keepNonMeaningfullShares);
	
	boolean loadSharesInPortfolio(List<PortfolioDB> listSharesInPorfolios);
	
	PortfolioDB getShareInPortfolio(int sharedId, int accountId);
	
	boolean loadValidSellOrders(List<ShareOrderI> listOrders, Date date);
	boolean loadValidBuyOrders(List<ShareOrderI> list, Date date);
	
	QuoteDB readQuotationAtOneDay(SearchQuoteAtOneDay seachQuote);
	double getLastTradedPrice(int shareId);//return 0.0 is error
	
	boolean readQuotations(List<QuoteDB> listQuote, SearchQuoteI seachQuote);
	
	boolean storeQuotation(List<QuoteDB> listQuote);
	boolean storeShares(List<ShareDB> listShares);
	
	boolean updateSellOrder(ShareOrderI sellOrder);
	boolean updateBuyOrder(ShareOrderI buyOrder);
	boolean updatePortfolio(PortfolioDB portfolioDB);
	void deleteOutdatedQuotes(Date limitDateForDeletion);
	
	boolean updateSchema();
	List<Integer> checkQuotes();

	

}