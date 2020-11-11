package net.tuxanna.portefeuille.business_logic.eval;

import java.util.ArrayList;
import java.util.List;

public class HtmlGenerator
{
	static final String COLOR_HTML_NORMAL_TEXT = "#000000"; //black
	static final String COLOR_HTML_LOSS_TEXT = "#ff0000";	//red
	static final String COLOR_HTML_HEADER_BACKGROUND = "#00BFFF"; //blue
	private static final String COLOR_HTML_GAIN_TODAY_BUT_STILL_LOOSING="#610B5E";
	private static final String COLOR_HTML_LOSS_TODAY_BUT_PROFITABLE="#0B6138";
	
	
	public static String getHtmlColor(Evaluation eval)
	{
		String color;
		if (eval.getGain() > 0.)
		{
			if (eval.getVariationInCurrency() >0)
			{
				color=COLOR_HTML_NORMAL_TEXT;
			}
			else
			{
				color=COLOR_HTML_LOSS_TODAY_BUT_PROFITABLE;// today we are loosing
			}
		}
		else
		{
			if (eval.getVariationInCurrency() >0)
			{
				color=COLOR_HTML_GAIN_TODAY_BUT_STILL_LOOSING; //today win reduce the loss
			}
			else
			{
				color=COLOR_HTML_LOSS_TEXT;//red
			}
		}
		return color;
	}
	public static List<String> getHeaderForReport()
	{
		ArrayList<String> list=new ArrayList<String>();
		list.add("name");
		list.add("qte");
		list.add("PRU");
		list.add("cours");
		list.add("valeur");
		list.add("var/hier");
		list.add("var %");
		list.add("gain");
		list.add("gain %");
		list.add("%");
		return list;
	}
	
	public static List<String> getContentForReport(Evaluation eval,double globalEvaluation)
	{
		ArrayList<String> list=new ArrayList<String>();
		list.add(eval.getShareName());
		{
			String result = String.format("%.1f", eval.getQte());
			list.add(result);
		}

		{
			String result = String.format("%.2f", eval.getBoughtPrice());
			list.add(result);
		}
		{
			String result = String.format("%.2f", eval.getCurrentPrice());
			list.add(result);
		}	
		final double value=eval.getCurrentPrice()* eval.getQte();
		{
			String result = String.format("%.2f", value);
			list.add(result);
		}
		{
			// with 2 digit
			String result = String.format("%.2f", eval.getVariationInCurrency());
			list.add(result);
		}
		{
			// with 2 digit
			String result = String.format("%.2f", eval.getVariationInPercent());
			list.add(result);
		}
		{
			// with 2 digit
			String result = String.format("%.2f", eval.getGain());
			list.add(result);
		}
		{
			String result = String.format("%.2f", eval.getGainInPercent());
			list.add(result);
		}

		//% of the portfolio for this share
		{
			String result = String.format("%.2f", value*100./globalEvaluation);
			list.add(result);
		}

		return list;
	}
}
