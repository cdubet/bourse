package net.tuxanna.portefeuille.business_logic;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.googlecode.jatl.Html;

import net.tuxanna.portefeuille.util.ReportI;

public class TopWorst implements ReportI
{
	private ArrayList<QuoteVariation> top;
	private ArrayList<QuoteVariation> worst;
	private String title; //used only when report is printed
	
	private int sizeTopWorst;
	
	public TopWorst()
	{
		super();
		top=new ArrayList<QuoteVariation>(0); //just to allow empty usage 
		worst=new ArrayList<QuoteVariation>(0);
		sizeTopWorst=5;
	}

	public TopWorst(int newSizeTopWorst,ArrayList<QuoteVariation> listQuotationVariation)
	{
		sizeTopWorst=newSizeTopWorst;		
		Collections.sort(listQuotationVariation);
		top=new ArrayList<QuoteVariation> (sizeTopWorst);
		worst=new ArrayList<QuoteVariation> (sizeTopWorst);
		
		for (int i=0; i<listQuotationVariation.size() && i<sizeTopWorst;i++ )
		{
			top.add(listQuotationVariation.get(i));
		}
		
		for (int i=listQuotationVariation.size()-1, addedItems=0; i>=0 && addedItems<sizeTopWorst ;i--,addedItems++ )
		{
			worst.add(listQuotationVariation.get(i));
		}
	}

	public ArrayList<QuoteVariation> getTop5()
	{
		return top;
	}

	public ArrayList<QuoteVariation> getWorst5()
	{
		return worst;
	}

	@Override
	public String toString()
	{
		return "TopWorst5 [top=" + top + "\nworst=" + worst + "]";
	}

	@Override
	public String toHtml()
	{
		StringWriter sw = new StringWriter();
		StringWriter writer = sw;
		//Html html = new Html(sw);
		
		new Html(writer) {{
			body();
			h1().text(getTitle()).end();

			
			//get the header, same for all items
			if (top.size() !=0)
			{
				table().width("100%").height("100%").border("#000000");
				thead().tr();
				List<String> header=top.get(0).getHeaderForReport();
				for (Iterator<String> iterator = header.iterator(); iterator.hasNext();)
				{
					String string = (String) iterator.next();
					th().bgcolor("#00ff00").text(string).end();
				}
				end().end();
				
				tbody();
				
				for (int i = 0; i < top.size(); i++) {
					tr();
					List<String> lineInTable=top.get(i).getContentForReport();
					for (Iterator<String> iterator = lineInTable.iterator(); iterator.hasNext();)
					{
						String string = (String) iterator.next();
						td().text(string).end();
						
					}
					end();
				}
				end();
			}


			
			//worst 5
			if (worst.size() !=0)
			{
				table().width("100%").height("100%").border("#000000");
				thead().tr();
				List<String> header=worst.get(0).getHeaderForReport();
				for (Iterator<String> iterator = header.iterator(); iterator.hasNext();)
				{
					String string = (String) iterator.next();
					th().bgcolor("#ff0000").text(string).end();
				}
				end().end();
				
				tbody();
				
				for (int i = 0; i < worst.size(); i++) {
					tr();
					List<String> lineInTable=worst.get(i).getContentForReport();
					for (Iterator<String> iterator = lineInTable.iterator(); iterator.hasNext();)
					{
						String string = (String) iterator.next();
						td().text(string).end();
						
					}
					end();
				}
				end();
			}
			
			endAll();
			done();
		}};
		return  writer.getBuffer().toString();
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void setSizeTopWorst(int sizeTopWorst)
	{
		this.sizeTopWorst = sizeTopWorst;
	}



}
