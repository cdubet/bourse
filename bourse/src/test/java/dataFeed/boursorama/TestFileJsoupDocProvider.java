package dataFeed.boursorama;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import net.tuxanna.portefeuille.dataFeed.JsoupDocProvider;

public class TestFileJsoupDocProvider implements JsoupDocProvider
{
	private String fileName;
	public TestFileJsoupDocProvider()
	{
	}
	public String getFileName()
	{
		return fileName;
	}

	//for junit test
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}
	
	@Override
	public Document getDocument() throws IOException
	{
		File input = new File(fileName);
		Document doc = Jsoup.parse(input, "UTF-8", "http://boursorama.com/");
		return doc;
	}
}
