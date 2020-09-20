package net.tuxanna.portefeuille.dataFeed;

import java.io.IOException;

import org.jsoup.nodes.Document;

public interface JsoupDocProvider
{
	Document getDocument() throws IOException;
}
