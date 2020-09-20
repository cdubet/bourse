package net.tuxanna.portefeuille.businessLogic.shareOrders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.tuxanna.portefeuille.businessLogic.util.ListOfOperations;
import net.tuxanna.portefeuille.database.DatabaseI;
import net.tuxanna.portefeuille.database.PortfolioDB;
import net.tuxanna.portefeuille.database.ShareOrderI;
import net.tuxanna.portefeuille.util.DigitValue;
import net.tuxanna.portefeuille.util.ReportI;

public class SellManagement implements ShareOrderManagementI
{

	private static final Logger logger = LogManager.getLogger(SellManagement.class);

	private DatabaseI database;
	private ListOfOperations listSellOrdersToPerform=new ListOfOperations("sell");//for the notification by mail

	public SellManagement()
	{
		database=null;
	}

	/* (non-Javadoc)
	 * @see net.tuxanna.portefeuille.businessLogic.sell.ShareOrderManagementI#setDatabase(net.tuxanna.portefeuille.database.DatabaseI)
	 */
	@Override
	public void setDatabase(DatabaseI database)
	{
		this.database = database;
	}
	
	private boolean findExecutedSellOrders(Date date)
	{
		boolean result=true; //let s be optimistic
		List<ShareOrderI> newOrders=new ArrayList<ShareOrderI>();
		if (database.loadValidSellOrders(newOrders,date))
		{
			listSellOrdersToPerform.addToList(newOrders);
			for (int i = 0; i < listSellOrdersToPerform.size(); i++)
			{
				ShareOrderI sellDB = listSellOrdersToPerform.get(i);

				final int shareId=sellDB.getIdShare();
				final int accountId=sellDB.getIdAccount();
				
				double lastQuote=database.getLastTradedPrice(shareId);
				if (lastQuote != 0.0 && lastQuote >= sellDB.getUnitPriceRequested() )
				{
					//sold :-)
					sellDB.setAsExecuted(new DigitValue(sellDB.getUnitPriceRequested()));//we assume here big capitalisation where we can buy at the price we offer
					sellDB.setState(ShareOrderI.STATE_ORDER.EXECUTED);
					PortfolioDB shareInPortfolio=database.getShareInPortfolio(shareId,accountId);
					if (shareInPortfolio.getQte().isValid() && shareInPortfolio.getUnitPrice().isValid())
					{
						if (shareInPortfolio.getQte().getValue() >= sellDB.getQte())
						{
							//compute the new quantity and unit price
							processSellOrder(sellDB, shareInPortfolio);

							//save it in DB
							database.updatePortfolio(shareInPortfolio);
							database.updateSellOrder(sellDB);
						}
						else
						{
							logger.error("unexpected qte="+shareInPortfolio.getQte().getValue()+" for id="+shareId);
							result=false;
						}
					}
					else
					{
						logger.error("empty qte or price for id="+shareId);
						result=false;
					}
				}
			}

		}
		else
		{
			logger.error("unable to load sell orders");
			result=false;
		}
		return result;
	}

	private void processSellOrder(ShareOrderI sellDB, PortfolioDB shareInPortfolio)
	{
		//we assume here that we have big capitalization. so we cannot have a partial sell because not enough buyers
		
		double remainingQte=shareInPortfolio.getQte().getValue() - sellDB.getQte();
		if (remainingQte >= 0.0)
		{
			//partial sell: we keep some shares
			//update unit price
			double oldPrice=shareInPortfolio.getUnitPrice().getValue()*shareInPortfolio.getQte().getValue();
			double moneyReceived=sellDB.getUnitPriceExecuted().getValue()*sellDB.getQte();
			double newPrice=(oldPrice-moneyReceived)/remainingQte;
			shareInPortfolio.setUnitPrice(new DigitValue(newPrice));
		}
		else
		{
			//everything sold, just set price to 0
			shareInPortfolio.setUnitPrice(new DigitValue(0.));
		}
		shareInPortfolio.setQte(new DigitValue(remainingQte));
		
		//we do not know the price really used for selling, so leave it blank
		//sellDB.setAsSold(soldValue);
		sellDB.setState(ShareOrderI.STATE_ORDER.EXECUTED);
	}

	/* (non-Javadoc)
	 * @see net.tuxanna.portefeuille.businessLogic.sell.ShareOrderManagementI#update(java.util.Date, net.tuxanna.portefeuille.businessLogic.util.ListOfQuotations)
	 */
	@Override
	public boolean update(Date now)
	{
		return findExecutedSellOrders(now);

	}
	
	/* (non-Javadoc)
	 * @see net.tuxanna.portefeuille.businessLogic.sell.ShareOrderManagementI#addResultToNotify(java.util.List)
	 */
	@Override
	public void addResultToNotify(List<ReportI> listReportToPrint)
	{
		if (listSellOrdersToPerform.size() !=0)
		{
			// something has been sold
			listReportToPrint.add(listSellOrdersToPerform);
		}
	}
}
