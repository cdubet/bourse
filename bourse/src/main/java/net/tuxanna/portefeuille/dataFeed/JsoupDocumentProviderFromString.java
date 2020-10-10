package net.tuxanna.portefeuille.dataFeed;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupDocumentProviderFromString implements JsoupDocProvider
{
	private String htmlText;
	
	public JsoupDocumentProviderFromString(String text)
	{
		htmlText=text;
	}

	@Override
	public Document getDocument() throws IOException
	{
		return Jsoup.parse(htmlText);
	}

}
