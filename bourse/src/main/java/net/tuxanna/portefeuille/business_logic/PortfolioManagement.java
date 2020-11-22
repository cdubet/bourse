package net.tuxanna.portefeuille.business_logic;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.tuxanna.portefeuille.Quote;
import net.tuxanna.portefeuille.business_logic.eval.Evaluation;
import net.tuxanna.portefeuille.business_logic.eval.EvaluationStorage;
import net.tuxanna.portefeuille.business_logic.eval.QuotationMapStorage;
import net.tuxanna.portefeuille.business_logic.eval.ShareIdAndDate;
import net.tuxanna.portefeuille.business_logic.mobile_average.MobileAverage;
import net.tuxanna.portefeuille.business_logic.share_orders.SellManagement;
import net.tuxanna.portefeuille.business_logic.share_orders.ShareOrderManagementI;
import net.tuxanna.portefeuille.business_logic.util.ProblemNotification;
import net.tuxanna.portefeuille.business_logic.util.ShareNameStorage;
import net.tuxanna.portefeuille.business_logic.util.ShareToQuotations;
import net.tuxanna.portefeuille.dataFeed.QuotationProviderI;
import net.tuxanna.portefeuille.dataFeed.Ticker;
import net.tuxanna.portefeuille.dataFeed.TickerI;
import net.tuxanna.portefeuille.database.DatabaseI;
import net.tuxanna.portefeuille.database.PortfolioDB;
import net.tuxanna.portefeuille.database.QuoteDB;
import net.tuxanna.portefeuille.database.SearchQuoteAtOneDay;
import net.tuxanna.portefeuille.database.ShareDB;
import net.tuxanna.portefeuille.util.RealClock;
import net.tuxanna.portefeuille.util.ReportI;
import net.tuxanna.portefeuille.util.ClockI;
import net.tuxanna.portefeuille.util.DigitValue;
import net.tuxanna.portefeuille.util.PortfolioNotifierI;

public class PortfolioManagement
{
	private static final Logger logger = LogManager.getLogger(PortfolioManagement.class);

	private QuotationProviderI quotationProvider;
	private DatabaseI database;
	private ClockI clock; //get system, time (fixed data for junit)

	private TopWorst5 todayChange;
	private TopWorst5 yesterdayChange;
	private TopWorst5 weekChange;
	private TopWorst5 monthChange;

	private QuotationMapStorage quotationMapStorage=new QuotationMapStorage();

	//allow to find the name associate to the id
	private ShareNameStorage shareNameStorage=new ShareNameStorage();

	private EvaluationStorage evaluationStorage=new EvaluationStorage();
	private List<ShareOrderManagementI> listOfShareOrderManagement=new ArrayList<>(2); //for managing sell and buy orders
	private List<ProblemNotification> problemsToBeNotified=new ArrayList<>();

	public void setDatabase(DatabaseI database)
	{
		this.database = database;
	}


	public void setQuotationProvider(QuotationProviderI p)
	{
		quotationProvider=p;
	}


	public PortfolioManagement()
	{
		quotationProvider=null; //TODO make class dummy instead to allow test without the setters
		database=null;
		todayChange=new TopWorst5();
		yesterdayChange=new TopWorst5();
		weekChange=new TopWorst5();
		monthChange=new TopWorst5();

		listOfShareOrderManagement.add(new SellManagement());
		clock=new RealClock();
	}

	private void buildVariation(List<ShareDB> fullListShares,ShareToQuotations listOfQuotationsDB)
	{
		// get rid of quotes we no longer own or just not meaningful as we do not care about their value (investment without hope)
		List<Share> listSharesWithIndicationToUseOrNot=new ArrayList<>();

		buildListSharesWithFlagKeepOrNot(fullListShares,listSharesWithIndicationToUseOrNot);

		//stripShareList(fullListShares,stripListShares,stripListOfQuotationsDB);

		// get top 5 of today, we continue even if we fail
		buildTodayChange(listSharesWithIndicationToUseOrNot,listOfQuotationsDB);
		buildYesterdayChange(listSharesWithIndicationToUseOrNot,listOfQuotationsDB);
		buildWeekChange(listSharesWithIndicationToUseOrNot,listOfQuotationsDB);
		buildMonthChange(listSharesWithIndicationToUseOrNot,listOfQuotationsDB);
	}



	public boolean exportQuoteCsv(File csvFile)
	{
		ArrayList<ShareDB> listShares=new ArrayList<>();
		if (!database.loadShares(listShares))
		{
			logger.error("unable to get list of stocks");
			return false;
		}

		//same amount of quotes as shares
		ShareToQuotations listOfQuotationsDB=new ShareToQuotations();
		if (!getLatestQuotationFromInternetAndStoreThem(listShares,listOfQuotationsDB))
		{
			return false;
		}
		return listOfQuotationsDB.exportQuoteCsv(listShares,csvFile);
	}

	public boolean update()
	{
		ArrayList<ShareDB> listShares=new ArrayList<>();
		if (!database.loadShares(listShares))
		{
			logger.error("unable to get list of stocks");
			return false;
		}

		shareNameStorage.fill(listShares);

		//same amount of quotes as shares
		ShareToQuotations listOfQuotationsDB=new ShareToQuotations();
		if (!getLatestQuotationFromInternetAndStoreThem(listShares,listOfQuotationsDB))
		{
			return false;
		}

		buildVariation(listShares,listOfQuotationsDB);

		Date now=clock.getNow();

		boolean orderProcessing=true;
		for (ShareOrderManagementI order : listOfShareOrderManagement)
		{
			order.setDatabase(database);
			boolean res= order.update(now);
			if (!res)
			{
				logger.error("error while processing sell/buy orders");
				orderProcessing=false;
			}
		}

		return orderProcessing;

	}

	private void buildListSharesWithFlagKeepOrNot(List<ShareDB> fullListShares,List<Share> listSharesWithKeepOrNot)
	{
		List<Integer> listSharesId=new ArrayList<>();
		final boolean keepNonMeaningfullShares=false;
		if (database.loadSharesIdInPortfolio(listSharesId, keepNonMeaningfullShares))
		{
			//sort the list to be able to search in it
			Collections.sort(listSharesId);

			for (ShareDB shareDB : fullListShares)
			{
				Integer idToSearch=shareDB.getId();
				boolean showShareInVariation;
				int indexFound=Collections.binarySearch(listSharesId, idToSearch);
				if (indexFound >= 0)// own and care
				{
					showShareInVariation = true;
				}
				else
				{
					showShareInVariation = false;
				}
				listSharesWithKeepOrNot.add(new Share(shareDB, showShareInVariation));
			}
		}
	}

	private Date skipWeekEnd(Calendar cal)
	{
		if ((cal.get(Calendar.DAY_OF_WEEK)== Calendar.SUNDAY) || (cal.get(Calendar.DAY_OF_WEEK)== Calendar.SATURDAY))
		{
			cal.add(Calendar.DAY_OF_YEAR, -1);
			return skipWeekEnd(cal);
		}
		//not WE
		return cal.getTime(); 
	}
	private java.util.Date getDateOfYesterday()
	{
		Date now=clock.getNow();
		Calendar cal = Calendar.getInstance(); // creates calendar
		cal.setTime(now); // sets it to now 

		if (logger.isDebugEnabled())
		{
			SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.ENGLISH);
			String strDate = dateFormat.format(cal.getTime());
			logger.debug("from {}",strDate);
		}

		cal.add(Calendar.DAY_OF_YEAR, -1);

		java.util.Date newDateBefore=skipWeekEnd(cal);
		if (logger.isDebugEnabled())
		{
			SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.ENGLISH);
			String strDate = dateFormat.format(newDateBefore.getTime());
			logger.debug("to {}",strDate);
		}
		return newDateBefore;
	}
	private void buildYesterdayChange(List<Share> listShares,ShareToQuotations listOfQuotationsDB)
	{
		//first get the quotation for yesterday
		java.util.Date newDateBefore=getDateOfYesterday();

		ArrayList<QuoteVariation> listQuotationVariation=new ArrayList<>(listOfQuotationsDB.size());
		buildQuotationVariation(listShares, listOfQuotationsDB, newDateBefore, listQuotationVariation,true /* addComparisonWithLastWeek*/);
		yesterdayChange=new TopWorst5(listQuotationVariation);			
	}

	private void buildWeekChange(List<Share> listShares,ShareToQuotations listOfQuotationsDB)
	{

		java.util.Date newDateBefore = getDateOneWeekBefore();

		ArrayList<QuoteVariation> listQuotationVariation=new ArrayList<>(listOfQuotationsDB.size());
		buildQuotationVariation(listShares, listOfQuotationsDB, newDateBefore, listQuotationVariation,false/* addComparisonWithLastWeek*/);
		weekChange=new TopWorst5(listQuotationVariation);			
	}

	private java.util.Date getDateOneWeekBefore()
	{
		Date now=clock.getNow();
		Calendar cal = Calendar.getInstance(); // creates calendar
		cal.setTime(now); // sets it to now 

		cal.add(Calendar.DAY_OF_YEAR, -7);
		java.util.Date newDateBefore=skipWeekEnd(cal); //normally not needed as if we run our SW only business day, 1 week before is also a business day
		return newDateBefore;
	}

	private void buildMonthChange(List<Share> listShares,ShareToQuotations listOfQuotationsDB)
	{
		//first get the quotation for yesterday
		Date now=clock.getNow();
		Calendar cal = Calendar.getInstance(); // creates calendar
		cal.setTime(now); // sets it to now 

		cal.add(Calendar.MONTH, -1);
		java.util.Date newDateBefore=skipWeekEnd(cal);

		ArrayList<QuoteVariation> listQuotationVariation=new ArrayList<>(listOfQuotationsDB.size());
		buildQuotationVariation(listShares, listOfQuotationsDB, newDateBefore, listQuotationVariation,true /* addComparisonWithLastWeek*/);
		monthChange=new TopWorst5(listQuotationVariation);	
		
	}

	private boolean buildQuotationVariation(List<Share> listShares,
			ShareToQuotations listOfQuotationsDB,
			java.util.Date newDateBefore,
			ArrayList<QuoteVariation> listQuotationVariation,
			boolean addComparisonWithLastWeek)
	{
		java.util.Date newDateOneWeekBefore=null;
		if (addComparisonWithLastWeek)
		{
			newDateOneWeekBefore = getDateOneWeekBefore();
		}

		boolean missingQuotes=false;

		for (Iterator<Share> iterator = listShares.iterator(); iterator.hasNext();)
		{
			Share share = iterator.next();
			SearchQuoteAtOneDay search=new SearchQuoteAtOneDay(share.getShareDB(), newDateBefore);
			QuoteDB quoteDB=database.readQuotationAtOneDay(search);
			if (quoteDB.getQuotation().isLastTradedPriceValid())
			{
				QuoteDB oneQuoteDB=listOfQuotationsDB.get(share.getShareDB());
				if (oneQuoteDB==null)//TODO remove all this null tests
				{
					logger.error("buildQuotationVariation share missing in DB {}",share.getShareDB());
					missingQuotes=true;	
				}
				else
				{
					Quote storedQuote = oneQuoteDB.getQuotation();

					// will occur if some quote cannot be found
					if (storedQuote ==null)
					{
						logger.error("buildQuotationVariation share missing {}",share.getShareDB().toString());
						missingQuotes=true;
					}
					else if (storedQuote.isLastTradedPriceValid())
					{
						double currentValue=storedQuote.getLastTradedPrice().getValue();

						double firstValue=quoteDB.getQuotation().getLastTradedPrice().getValue();

						quotationMapStorage.add(new ShareIdAndDate(share.getShareDB(), newDateBefore), firstValue);
						if (share.isRequiredForVariation())
						{
							QuoteVariation q1;
							if (addComparisonWithLastWeek)
							{
								q1=new QuoteVariationExtended(database,newDateOneWeekBefore, share.getShareDB(), firstValue, currentValue);
							}
							else
							{
								q1=new QuoteVariation(share.getShareDB().getName(), firstValue, currentValue);
							}
							listQuotationVariation.add(q1);
						}
					}
					else
					{
						logger.error("buildQuotationVariation last quotation missing {}",share.getShareDB().toString());
						missingQuotes=true;
					}
				}
			}
		}
		return !missingQuotes;
	}

	private void buildTodayChange(List<Share> listShares,ShareToQuotations listOfQuotationsDB)
	{
		Date newDateOneWeekBefore = getDateOneWeekBefore();
		Date dateNow=	clock.getNow();

		ArrayList<QuoteVariation> listQuotationVariation=new ArrayList<>(listOfQuotationsDB.size());
		for (Share share : listShares)
		{
			QuoteDB quoteDB= listOfQuotationsDB.get(share.getShareDB());
			if (quoteDB == null)
			{
				logger.warn("unable to get quotes for {}",share.toString());
				problemsToBeNotified.add(new ProblemNotification(String.format("no quotation for [%s]",share.toString())));
			}
			else
			{
				DigitValue lastTraded = quoteDB.getQuotation().getLastTradedPrice();
				DigitValue firstTraded = quoteDB.getQuotation().getOpenPrice();	

				if (lastTraded.isValid()) 
				{
					double currentValue=lastTraded.getValue();
					//get the name. both array are sync -> same index
					quotationMapStorage.add(new ShareIdAndDate(share.getShareDB(), dateNow), currentValue);
					if (firstTraded.isValid())
					{
						DigitValue volume = quoteDB.getQuotation().getVolume();
						// check value is valid and volume fine
						if ((firstTraded.getValue() >0.) && 
								volume.isValid() && 
								(volume.getValue()>0.)	)
						{
							double firstValue=firstTraded.getValue();
							if (share.isRequiredForVariation())
							{
								QuoteVariation q1=new QuoteVariationExtended(database,newDateOneWeekBefore, share.getShareDB(), firstValue, currentValue);
								listQuotationVariation.add(q1);
							}
						}
					}
				}
			}
		}
		todayChange=new TopWorst5(listQuotationVariation);
	}

	public boolean getLatestQuotationFromInternetAndStoreThem(List<ShareDB> listShares,ShareToQuotations listOfQuotationsDB)
	{
		//build list of tickers from listShares and sent it to data feed
		ArrayList<TickerI> listTicker=new ArrayList<>(listShares.size());
		for (ShareDB shareDB : listShares)
		{
			listTicker.add(new Ticker(shareDB.getTicker(),shareDB.isShare()) );
		}

		quotationProvider.setListTickers(listTicker);
		HashMap<TickerI,Quote> quotationsForTicker=new HashMap<>(listTicker.size());
		if (!quotationProvider.getQuotes(quotationsForTicker))
		{
			logger.error("unable to get list of quotes");
			problemsToBeNotified.add(new ProblemNotification("unable to get list of quotes"));
			return false;
		}


		if (quotationsForTicker.size() != listTicker.size())
		{
			//some are missing, cannot assign quotation to shares !
			logger.warn("received {} quotations for {} shares",quotationsForTicker.size(),listTicker.size());
		}
		logger.debug("received {} quotations",quotationsForTicker.size());

		fillListOfQuotation(listShares, quotationsForTicker,listOfQuotationsDB);

		//save them in DB
		if (!database.storeQuotation(listOfQuotationsDB.getList()))
		{
			logger.error("enable to save-> aborting ");
			return false;
		}
		return true;
	}


	private void fillListOfQuotation(List<ShareDB> listShares,
			HashMap<TickerI, Quote> quotationsForTicker,
			ShareToQuotations listOfQuotationsDB)
	{
		for (ShareDB share : listShares)
		{
			TickerI ticker=new Ticker(share.getTicker(),share.isShare());
			Quote quotation = quotationsForTicker.get(ticker);
			if (quotation != null)
			{
				if (quotation.isLastTradedPriceValid())
				{
					QuoteDB qDB=new QuoteDB(share.getId(),quotation);
					if (!quotation.getMobileAverage50Days().isValid())
					{
						//compute it
						MobileAverage average50=new MobileAverage(share, database, 50);
						Double computedAverage50=average50.compute();
						if (computedAverage50 == null)
						{
							logger.debug("no average 50 for [ {} ]",ticker.getSymbol());
						}
						else
						{
							quotation.setMobileAverage50Days(computedAverage50);
						}
					}
					if (!quotation.getMobileAverage200Days().isValid())
					{
						//compute it
						MobileAverage average200=new MobileAverage(share, database, 200);
						Double computedAverage200=average200.compute();
						if (computedAverage200 == null)
						{
							logger.debug("no average 200 for [ {} ]",ticker.getSymbol());
						}
						else
						{
							quotation.setMobileAverage200Days(computedAverage200);
						}
					}
					listOfQuotationsDB.add(share,qDB);
				}
				else
				{
					logger.debug("no quotation for {}",ticker.getSymbol());
					problemsToBeNotified.add(new ProblemNotification("no quotation for ["+ticker.getSymbol()+"]"));
				}
			}
			else
			{
				logger.debug("unable to get quote for {}",ticker.getSymbol());
				problemsToBeNotified.add(new ProblemNotification("unable to get quote for ["+ticker.getSymbol()+"]"));
			}
		}
	}


	public TopWorst5 getTodayChange()
	{
		return todayChange;
	}


	public void setTodayChange(TopWorst5 todayChange)
	{
		this.todayChange = todayChange;
	}


	public TopWorst5 getYesterdayChange()
	{
		return yesterdayChange;
	}


	public void setYesterdayChange(TopWorst5 yesterdayChange)
	{
		this.yesterdayChange = yesterdayChange;
	}


	public TopWorst5 getWeekChange()
	{
		return weekChange;
	}


	public void setWeekChange(TopWorst5 weekChange)
	{
		this.weekChange = weekChange;
	}


	public TopWorst5 getMonthChange()
	{
		return monthChange;
	}


	public void setMonthChange(TopWorst5 monthChange)
	{
		this.monthChange = monthChange;
	}


	public void setClock(ClockI clock)
	{
		this.clock = clock;
	}


	public void notifyResults(PortfolioNotifierI notifier)
	{
		System.out.println("top 5");
		System.out.println("today "+todayChange.getTop5().toString());
		System.out.println("yesterday "+yesterdayChange.getTop5().toString());

		System.out.println("worst 5");
		System.out.println("today "+todayChange.getWorst5().toString());
		System.out.println("yesterday "+yesterdayChange.getWorst5().toString());

		ArrayList<ReportI> listReportToPrint=new ArrayList<>();

		todayChange.setTitle("today");
		yesterdayChange.setTitle("yesterday");
		weekChange.setTitle("week");
		monthChange.setTitle("month");

		listReportToPrint.add(todayChange);
		listReportToPrint.add(yesterdayChange);
		listReportToPrint.add(weekChange);
		listReportToPrint.add(monthChange);

		for (ShareOrderManagementI order : listOfShareOrderManagement)
		{
			order.addResultToNotify(listReportToPrint);
		}

		if (evaluationStorage.size() !=0)
		{
			listReportToPrint.add(evaluationStorage);
		}

		notifier.notifyUser(listReportToPrint);
		notifier.notifyErrors(problemsToBeNotified);
	}

	private boolean getUniqueListOfShares(ArrayList<PortfolioDB> listSharesInPortfolio)
	{
		if (database.loadSharesInPortfolio(listSharesInPortfolio))
		{
			if (mergeShareOwned(listSharesInPortfolio))
			{			
				return true;
			}
		}
		return false;
	}

	//public only because of testing
	//we assume listSharesInPortfolio to be sorted by idShare (see database loadShareInPortfolio()
	public boolean mergeShareOwned(ArrayList<PortfolioDB> listSharesInPortfolio)
	{
		for (int idx=0;idx<listSharesInPortfolio.size()-1;idx++)
		{
			if (listSharesInPortfolio.get(idx).getIdShare() == listSharesInPortfolio.get(idx+1).getIdShare())
			{
				//same share
				if (!listSharesInPortfolio.get(idx).getQte().isValid() ||
						!listSharesInPortfolio.get(idx+1).getQte().isValid() ||
						!listSharesInPortfolio.get(idx).getUnitPrice().isValid() ||
						!listSharesInPortfolio.get(idx+1).getUnitPrice().isValid()	)
				{
					logger.error("values not filled ! aborting ");
					return false;
				}
				//merge
				double newQte=listSharesInPortfolio.get(idx).getQte().getValue()+listSharesInPortfolio.get(idx+1).getQte().getValue();
				double newUnitPrice =(listSharesInPortfolio.get(idx).getQte().getValue() * listSharesInPortfolio.get(idx).getUnitPrice().getValue() +
						listSharesInPortfolio.get(idx+1).getQte().getValue() * listSharesInPortfolio.get(idx+1).getUnitPrice().getValue())
						/ newQte;
				//set in 1 and remove the other one
				listSharesInPortfolio.get(idx).setQte(new DigitValue(newQte));
				listSharesInPortfolio.get(idx).setUnitPrice(new DigitValue(newUnitPrice));

				listSharesInPortfolio.remove(idx+1);
			}
		}
		logger.debug("after merge {} items",listSharesInPortfolio.size());
		return true;
	}


	public boolean evaluate()
	{
		ArrayList<PortfolioDB> listSharesInPortfolio=new ArrayList<>();
		Date dateOfYesterday=getDateOfYesterday();
		Date now=clock.getNow();

		if (getUniqueListOfShares( listSharesInPortfolio))
		{
			boolean result=true;
			for (PortfolioDB portfolioDB : listSharesInPortfolio)
			{
				ShareIdAndDate keyYesterday=new ShareIdAndDate(portfolioDB,dateOfYesterday);
				ShareIdAndDate keyNow      =new ShareIdAndDate(portfolioDB,now);

				//get quotes for each one
				final Double quotationYesterday=quotationMapStorage.get(keyYesterday);

				if (quotationYesterday== null)
				{
					//not found/ should not happen !
					logger.debug("cannot find {}",keyYesterday.toString());
					logger.debug("quotationMapStorage {}",quotationMapStorage.toString());
					result= false;
				}
				else
				{
					final Double quotationNow=quotationMapStorage.get(keyNow);
					if (quotationNow== null)
					{
						//not found/ should not happen !
						logger.debug("cannot find {}",keyNow.toString());
						logger.debug("quotationMapStorage {}",quotationMapStorage.toString());
						result= false;

					}
					else
					{
						Evaluation evaluation=new Evaluation();
						if (!evaluation.fillWithPortfolioItems(portfolioDB))
						{
							result= false;
						}
						else
						{
							evaluation.setCurrentPrice(quotationNow);
							evaluation.setYesterdayPrice(quotationYesterday);
							evaluation.computeVariation();
							evaluation.setShareName(shareNameStorage.getName(evaluation.getIdShare()));
							evaluationStorage.add(evaluation);
						}
					}		
				}

			}

			return result;
		}
		return false;
	}


	public void setQuotationMapStorage(QuotationMapStorage quotationMapStorage)
	{
		this.quotationMapStorage = quotationMapStorage;
	}


	public EvaluationStorage getEvaluationStorage()
	{
		return evaluationStorage;
	}


	public void setShareNameStorage(ShareNameStorage shareNameStorage)
	{
		this.shareNameStorage = shareNameStorage;
	}


	public void deleteOutdatedQuotes()
	{
		Date now=clock.getNow();
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.setTime(now);
		cal.add(Calendar.MONTH, -1);// one month less
		Date limitDateForDeletion=cal.getTime();
		logger.debug("deleting outdate quotes from {}",limitDateForDeletion.toString());

		database.deleteOutdatedQuotes(limitDateForDeletion);
	}


	public void checkQuotes()
	{
		database.checkQuotes();
	}


	public void divideShare(String shareName, Date dateSplit, Double divideShareRatio)
	{
		
		ShareDB ShareDB=database.loadShare(shareName);
		
	}


}
