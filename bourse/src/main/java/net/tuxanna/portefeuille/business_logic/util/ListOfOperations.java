package net.tuxanna.portefeuille.business_logic.util;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.googlecode.jatl.Html;

import net.tuxanna.portefeuille.business_logic.share_orders.OrderInfo;
import net.tuxanna.portefeuille.database.DatabaseI;
import net.tuxanna.portefeuille.database.QuoteDB;
import net.tuxanna.portefeuille.database.ShareOrderI;
import net.tuxanna.portefeuille.util.DigitValue;
import net.tuxanna.portefeuille.util.ReportI;

public class ListOfOperations implements ReportI
{
	private static final Logger logger = LogManager.getLogger(ListOfOperations.class);

	private String title;

	private List<OrderInfo> listOrdersExecuted = new ArrayList<OrderInfo>();

	public ListOfOperations(String newTitle)
	{
		title = newTitle;
	}

	@Override
	public String getTitle()
	{
		return title;
	}

	@Override
	public String toHtml()
	{
		StringWriter sw = new StringWriter();
		StringWriter writer = sw;

		// alphabetical sort
		Collections.sort(listOrdersExecuted);

		new Html(writer) {
			{
				body();
				h1().text(getTitle()).end();

				// get the header, same for all items
				if (listOrdersExecuted.size() != 0)
				{
					table().width("100%").height("100%").border("#000000");
					thead().tr();
					List<String> header = getHeaderForReport();
					for (Iterator<String> iterator = header.iterator(); iterator.hasNext();)
					{
						String string = (String) iterator.next();
						th().bgcolor("#00BFFF").text(string).end();
					}
					end().end();

					tbody();

					for (int i = 0; i < listOrdersExecuted.size(); i++)
					{
						tr();

						List<String> lineInTable = getContentForReport(i);
						for (Iterator<String> iterator = lineInTable.iterator(); iterator.hasNext();)
						{
							String string = (String) iterator.next();
							td().font().text(string).end().end();
						}
						end();
					}
					end();
				}
				endAll();
				done();
			}
		};
		return writer.getBuffer().toString();

	}

	private List<String> getContentForReport(int index)
	{
		return listOrdersExecuted.get(index).toText();
	}

	private List<String> getHeaderForReport()
	{
		List<String> header = new ArrayList<String>();
		header.add("name");
		header.add("qte");
		header.add("priceSold");
		return header;
	}

	public void add(String shareName, ShareOrderI sellDB)
	{
		listOrdersExecuted.add(new OrderInfo(sellDB, shareName));
	}

	public void addToList(List<ShareOrderI> newOrders)
	{
		for (ShareOrderI shareOrderI : newOrders)
		{
			add("", shareOrderI);
		}

	}

	public ShareOrderI get(int index)
	{
		return listOrdersExecuted.get(index).getSellDB();
	}

	public int size()
	{
		return listOrdersExecuted.size();
	}


	public void checkIfSharesSold(Date now, DatabaseI database,ShareToQuotations listOfQuotationsDB,ShareNameStorage shareNameStorage)
	{
		List<ShareOrderI> listOrders=new ArrayList<ShareOrderI>();
		
		if (!database.loadValidSellOrders(listOrders, now))
		{
			logger.error("unable to get list of orders");
			return;
		}
		for (Iterator<ShareOrderI> iterator = listOrders.iterator(); iterator.hasNext();)
		{
			ShareOrderI sellDB =  iterator.next();
			
			//find today quotation
			Optional<QuoteDB> quoteDB=listOfQuotationsDB.getQuotationFromList( sellDB.getIdShare());
			
			if (quoteDB.isPresent())
			{
				if (quoteDB.get().getQuotation().getHighPrice().isValid() )
				{
					if (sellDB.getUnitPriceRequested()< quoteDB.get().getQuotation().getHighPrice().getValue() )
					{
						//sold
						//we assume here that everything has been sold to the price we have requested. true normally for big capitalisation but wrong elsewehre
						sellDB.setAsExecuted(new DigitValue(sellDB.getUnitPriceRequested()));					
						
						String shareName=shareNameStorage.getName(sellDB.getIdShare());
						add(shareName,sellDB);
					}
				}
				else
				{
					logger.error("unable to get max for id {}",sellDB.getIdShare());
				}
			}
			else
			{
				logger.error("unable to get quote for id {}",sellDB.getIdShare());
			}
		}
		
	}

}
