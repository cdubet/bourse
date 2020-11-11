package net.tuxanna.portefeuille.business_logic;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.googlecode.jatl.Html;

import net.tuxanna.portefeuille.util.ReportI;

public class TopWorst5 implements ReportI
{
	private ArrayList<QuoteVariation> top5;
	private ArrayList<QuoteVariation> worst5;
	private String title; //used only when report is printed
	
	private final int SIZE_TOP_WORST= 5;
	
	public TopWorst5()
	{
		super();
		top5=new ArrayList<QuoteVariation>(0); //just to allow empty usage 
		worst5=new ArrayList<QuoteVariation>(0);

	}

	public TopWorst5(ArrayList<QuoteVariation> listQuotationVariation)
	{
		Collections.sort(listQuotationVariation);
		top5=new ArrayList<QuoteVariation> (SIZE_TOP_WORST);
		worst5=new ArrayList<QuoteVariation> (SIZE_TOP_WORST);
		
		for (int i=0; i<listQuotationVariation.size() && i<SIZE_TOP_WORST;i++ )
		{
			top5.add(listQuotationVariation.get(i));
		}
		
		for (int i=listQuotationVariation.size()-1, addedItems=0; i>=0 && addedItems<SIZE_TOP_WORST ;i--,addedItems++ )
		{
			worst5.add(listQuotationVariation.get(i));
		}
	}

	public ArrayList<QuoteVariation> getTop5()
	{
		return top5;
	}

	public ArrayList<QuoteVariation> getWorst5()
	{
		return worst5;
	}

	@Override
	public String toString()
	{
		return "TopWorst5 [top5=" + top5 + "\nworst5=" + worst5 + "]";
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
			if (top5.size() !=0)
			{
				table().width("100%").height("100%").border("#000000");
				thead().tr();
				List<String> header=top5.get(0).getHeaderForReport();
				for (Iterator<String> iterator = header.iterator(); iterator.hasNext();)
				{
					String string = (String) iterator.next();
					th().bgcolor("#00ff00").text(string).end();
				}
				end().end();
				
				tbody();
				
				for (int i = 0; i < top5.size(); i++) {
					tr();
					List<String> lineInTable=top5.get(i).getContentForReport();
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
			if (worst5.size() !=0)
			{
				table().width("100%").height("100%").border("#000000");
				thead().tr();
				List<String> header=worst5.get(0).getHeaderForReport();
				for (Iterator<String> iterator = header.iterator(); iterator.hasNext();)
				{
					String string = (String) iterator.next();
					th().bgcolor("#ff0000").text(string).end();
				}
				end().end();
				
				tbody();
				
				for (int i = 0; i < worst5.size(); i++) {
					tr();
					List<String> lineInTable=worst5.get(i).getContentForReport();
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



}
