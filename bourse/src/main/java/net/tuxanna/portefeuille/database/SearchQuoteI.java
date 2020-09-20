package net.tuxanna.portefeuille.database;

import java.sql.Connection;

import java.util.List;

public interface SearchQuoteI
{
	boolean buildPreparedStatement(Connection conn) ;
	
	boolean executeAndFill(List<QuoteDB> listQuote);
}
