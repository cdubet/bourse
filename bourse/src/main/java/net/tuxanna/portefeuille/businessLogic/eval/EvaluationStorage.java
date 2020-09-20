package net.tuxanna.portefeuille.businessLogic.eval;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.googlecode.jatl.Html;

import net.tuxanna.portefeuille.util.ReportI;

public class EvaluationStorage implements ReportI
{

	private static final Logger logger = LogManager.getLogger(EvaluationStorage.class);
	private static final int NUMBER_TOP_WORST = 5;
	private List<Evaluation> listEvaluation=new ArrayList<Evaluation>();
	
	public EvaluationStorage()
	{
	}

	@Override
	public String getTitle()
	{ 
		return "evaluation";
	}

	private List<Evaluation> getListSortedByGainLoss()
	{
		List<Evaluation> listEvaluationSorted=listEvaluation;
		
		//lambda here!
		listEvaluationSorted.sort((Evaluation o1, Evaluation o2)->(int) (o1.getGain()-o2.getGain()));
		return listEvaluationSorted;
	}
	
	private List<Evaluation> getListSortedByDayGainLoss()
	{
		List<Evaluation> listEvaluationSorted=listEvaluation;
		
		//lambda here!
		listEvaluationSorted.sort((Evaluation o1, Evaluation o2)->(int) (o1.getVariationInCurrency()-o2.getVariationInCurrency()));
		return listEvaluationSorted;
	}
	
	@Override
	public String toHtml()
	{
		StringWriter writer = new StringWriter();
		final double globalEvaluation=computeGlobalEval();
		final String valPortfolio = String.format("val=%.2f gain=%.2f sur 24h=%.2f",globalEvaluation ,computeGlobalGain(),computeGlobalVariation());

		//alphabetical sort
		Collections.sort(listEvaluation);

		new Html(writer) 
		{
			{
				body();
				h1().text(getTitle()).end();

				h2().text(valPortfolio).end();

				//get the header, same for all items
				if (listEvaluation.size() !=0)
				{
					table().width("100%").height("100%").border(HtmlGenerator.COLOR_HTML_NORMAL_TEXT);
					
					
					printHeader();

					tbody();

					for (int i = 0; i < listEvaluation.size(); i++) 
					{
						tr();
						final Evaluation eval = listEvaluation.get(i);
						final String color = HtmlGenerator.getHtmlColor(eval);
						List<String> lineInTable=HtmlGenerator.getContentForReport(eval,globalEvaluation);
						for (Iterator<String> iterator = lineInTable.iterator(); iterator.hasNext();)
						{
							String string = (String) iterator.next();
							td().font().color(color).text(string).end().end();		
						}
						end();
					}

					
					
					//	print 5 best/5 worst 
					if (listEvaluation.size() > 10)
					{
						final List<Evaluation> listSortedByGainToday=getListSortedByDayGainLoss();
						printTopWorst(globalEvaluation, listSortedByGainToday,"pire pertes du jour","meilleur gain du jour");
						
						final List<Evaluation> listSortedByGain=getListSortedByGainLoss();
						
						printTopWorst(globalEvaluation, listSortedByGain,"pire pertes","meilleur gain");
						
					}
					end();
				}
				endAll();
				done();
			}

			private void printHeader()
			{
				final List<String> header=HtmlGenerator.getHeaderForReport();
				thead().tr();									
				for (Iterator<String> iterator = header.iterator(); iterator.hasNext();)
				{
					String string = (String) iterator.next();
					th().bgcolor(HtmlGenerator.COLOR_HTML_HEADER_BACKGROUND).text(string).end();
				}
				end().end();
			}

			private void printTopWorst(final double globalEvaluation, List<Evaluation> listSortedByGain,String textLoss,String textWin)
			{
				//add a separator
				tr();
				td().bgcolor(HtmlGenerator.COLOR_HTML_HEADER_BACKGROUND).text(textLoss).end();
				end();
				printHeader();
				for (int i = 0; i < NUMBER_TOP_WORST; i++) 
				{
					tr();
					final Evaluation eval = listSortedByGain.get(i);
					final String color = HtmlGenerator.getHtmlColor(eval);
					List<String> lineInTable=HtmlGenerator.getContentForReport(eval,globalEvaluation);
					for (Iterator<String> iterator = lineInTable.iterator(); iterator.hasNext();)
					{
						String string = (String) iterator.next();
						td().font().color(color).text(string).end().end();		
					}
					end();
				}
				//add a separator
				tr();
				td().bgcolor(HtmlGenerator.COLOR_HTML_HEADER_BACKGROUND).text(textWin).end();
				end();
				printHeader();
				for (int i = listSortedByGain.size()-1; i >= listSortedByGain.size() -NUMBER_TOP_WORST; i--) 
				{
					tr();
					final Evaluation eval = listSortedByGain.get(i);
					final String color = HtmlGenerator.getHtmlColor(eval);
					List<String> lineInTable=HtmlGenerator.getContentForReport(eval,globalEvaluation);
					for (Iterator<String> iterator = lineInTable.iterator(); iterator.hasNext();)
					{
						String string = (String) iterator.next();
						td().font().color(color).text(string).end().end();		
					}
					end();
				}
			}
		};
		return  writer.getBuffer().toString();
	}


	
	private double computeGlobalEval()
	{
		//TODO make difference according to currency
		double value=0.;
		for (Evaluation evaluation : listEvaluation)
		{
			value += evaluation.getQte()*evaluation.getCurrentPrice();
		}
		return value;
	}
	
	private double computeGlobalGain()
	{
		logger.trace("test");
		//TODO make difference according to currency
		double value=0.;
		for (Evaluation evaluation : listEvaluation)
		{
			value += evaluation.getGain();
		}
		return value;
	}
	
	private double computeGlobalVariation()
	{
		//TODO make difference according to currency
		double value=0.;
		for (Evaluation evaluation : listEvaluation)
		{
			value += evaluation.getVariationInCurrency();
		}
		return value;
	}
	
	public void add(Evaluation evaluation)
	{
		listEvaluation.add(evaluation);
	}

	public int size()
	{
		return listEvaluation.size();
	}

	public Evaluation get(int index)
	{
		return listEvaluation.get(index);
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((listEvaluation == null) ? 0 : listEvaluation.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
//		// used only in junit tests
		if (obj instanceof EvaluationStorage) 
		{
			EvaluationStorage that = (EvaluationStorage) obj;
			if (that.size() == size())
			{
				int index=0;
				for (Evaluation evaluation : listEvaluation)
				{
					if (!evaluation.equals(that.get(index)))
					{
						return false;
					}
					index++;
				}
				return true;
			}
		}
		return false;
	}
	

}
