package net.tuxanna.portefeuille.dataFeed;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

//normal provider, fetch from URL (other one from file is used for testing)
public class UrlJsoupDocProvider implements JsoupDocProvider
{
	private String url;
	
	public UrlJsoupDocProvider()
	{
	}

	@Override
	public Document getDocument() throws IOException
	{
		return Jsoup.connect(url).get();
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

}
