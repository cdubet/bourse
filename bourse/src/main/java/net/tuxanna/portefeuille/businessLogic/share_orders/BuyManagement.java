package net.tuxanna.portefeuille.businessLogic.share_orders;

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

public class BuyManagement implements ShareOrderManagementI
{
	private static final Logger logger = LogManager.getLogger(BuyManagement.class);

	private DatabaseI database;
	private ListOfOperations listBuyOrdersToPerform=new ListOfOperations("buy");//for the notification by mail


	public BuyManagement()
	{
		database=null;
	}

	private boolean findExecutedBuyOrders(Date date)
	{
		boolean result=true; //let s be optimistic
		List<ShareOrderI> newOrders=new ArrayList<>();
		if (database.loadValidBuyOrders(newOrders,date))
		{
			listBuyOrdersToPerform.addToList(newOrders);
			for (int i = 0; i < listBuyOrdersToPerform.size(); i++)
			{
				ShareOrderI buyDB = listBuyOrdersToPerform.get(i);

				final int shareId=buyDB.getIdShare();
				final int accountId=buyDB.getIdAccount();
				
				double lastQuote=database.getLastTradedPrice(shareId);
				if (lastQuote != 0.0 && lastQuote <= buyDB.getUnitPriceRequested() )
				{
					//price OK -> buying
					buyDB.setAsExecuted(new DigitValue(buyDB.getUnitPriceRequested()));//we assume here big capitalisation where we can buy at the price we offer
					buyDB.setState(ShareOrderI.STATE_ORDER.EXECUTED);

					PortfolioDB shareInPortfolio=database.getShareInPortfolio(shareId,accountId);
					if (shareInPortfolio==null)
					{
						//buying non existing share
						shareInPortfolio=createNewShareInPortfolio(buyDB);
					
						//save it in DB
						database.updatePortfolio(shareInPortfolio);
						database.updateBuyOrder(buyDB);
					}
					else
					{
						//existing
						if (shareInPortfolio.getQte().isValid() && shareInPortfolio.getUnitPrice().isValid())
						{
							//compute the new quantity and unit price
							processBuyOrderOnExisting(buyDB, shareInPortfolio);

							//save it in DB
							database.updatePortfolio(shareInPortfolio);
							database.updateBuyOrder(buyDB);
						}
						else
						{
							logger.error("empty qte or price for id="+shareId);
							result=false;
						}
					}
				}
			}
		}
		else
		{
			logger.error("unable to save sold shares");
			result=false;
		}
		return result;
	}

	private PortfolioDB createNewShareInPortfolio(ShareOrderI buyDB)
	{
		PortfolioDB newShare=new PortfolioDB();
		newShare.setIdAccount(buyDB.getIdAccount());
		newShare.setIdShare(buyDB.getIdShare());
		
		newShare.setQte(new DigitValue(buyDB.getQte()));
		newShare.setUnitPrice(new DigitValue(buyDB.getUnitPriceRequested())); //we assume here no cost for the transaction TODO add it !
		return newShare;
	}

	private void processBuyOrderOnExisting(ShareOrderI buyDb, PortfolioDB shareInPortfolio)
	{
		//we assume here that we have big capitalization. so we cannot have a partial sell because not enough buyers

		final double newQte=shareInPortfolio.getQte().getValue() + buyDb.getQte();

		//update unit price
		final double oldPrice=shareInPortfolio.getUnitPrice().getValue()*shareInPortfolio.getQte().getValue();
		final double moneySpent=buyDb.getUnitPriceExecuted().getValue()*buyDb.getQte();
		final double newUnitPrice=(oldPrice+moneySpent)/newQte;
		shareInPortfolio.setUnitPrice(new DigitValue(newUnitPrice));

		shareInPortfolio.setQte(new DigitValue(newQte));
	}



	@Override
	public boolean update(Date now)
	{
		return findExecutedBuyOrders(now);
	}

	@Override
	public void addResultToNotify(List<ReportI> listReportToPrint)
	{
		if (listBuyOrdersToPerform.size() !=0)
		{
			// something has been sold
			listReportToPrint.add(listBuyOrdersToPerform);
		}

	}

	@Override
	public void setDatabase(DatabaseI db)
	{
		database=db;
	}

	public DatabaseI getDatabase()
	{
		return database;
	}

}
