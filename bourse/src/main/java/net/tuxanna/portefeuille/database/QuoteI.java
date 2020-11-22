package net.tuxanna.portefeuille.database;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface QuoteI
{
	QuoteDB readQuotationAtOneDay(SearchQuoteAtOneDay seachQuote);
	Optional<Double> getHighTradedPrice(int shareId);
	Optional<Double> getLowTradedPrice(int shareId);
	
	boolean readQuotations(List<QuoteDB> listQuote, SearchQuoteI seachQuote);
	
	boolean storeQuotation(List<QuoteDB> listQuote);
	
	void deleteOutdatedQuotes(Date limitDateForDeletion);

	//return list ID to be checked
	List<Integer> checkQuotes();	

}
