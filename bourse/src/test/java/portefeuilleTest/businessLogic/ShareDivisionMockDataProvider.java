package portefeuilleTest.businessLogic;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import net.tuxanna.portefeuille.database.ConditionPortfolioI;
import net.tuxanna.portefeuille.database.ConditionQuoteI;
import net.tuxanna.portefeuille.database.DatabaseI;
import net.tuxanna.portefeuille.database.PortfolioDB;
import net.tuxanna.portefeuille.database.QuoteDB;
import net.tuxanna.portefeuille.database.SearchQuoteAtOneDay;
import net.tuxanna.portefeuille.database.SearchQuoteI;
import net.tuxanna.portefeuille.database.ShareDB;
import net.tuxanna.portefeuille.database.ShareOrderI;

public class ShareDivisionMockDataProvider implements DatabaseI
{
	public ShareDivisionMockDataProvider(ShareDB repliedShare, List<QuoteDB> listQuoteOriginal,
			List<PortfolioDB> listPorfolioOriginal)
	{
		super();
		this.repliedShare = repliedShare;
		this.listQuoteOriginal = listQuoteOriginal;
		
		this.listPorfolioOriginal = listPorfolioOriginal;
		
	}

	final private ShareDB repliedShare;
	private List<QuoteDB> listQuoteOriginal;
	private List<QuoteDB> listQuoteAfterProcessing;
	
	private List<PortfolioDB> listPorfolioOriginal;
	private List<PortfolioDB> listPortofolioAfterProcessing;
	
	@Override
	public boolean loadValidSellOrders(List<ShareOrderI> listOrders, Date date)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean loadValidBuyOrders(List<ShareOrderI> list, Date date)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateSellOrder(ShareOrderI sellOrder)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateBuyOrder(ShareOrderI buyOrder)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public QuoteDB readQuotationAtOneDay(SearchQuoteAtOneDay seachQuote)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Double> getHighTradedPrice(int shareId)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Double> getLowTradedPrice(int shareId)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean readQuotations(List<QuoteDB> listQuote, SearchQuoteI seachQuote)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<QuoteDB> readQuotations(ConditionQuoteI condition)
	{
		return listQuoteOriginal;
	}

	@Override
	public boolean insertQuotation(List<QuoteDB> listQuote)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateQuotationInDatabase(List<QuoteDB> quoteList)
	{
		listQuoteAfterProcessing=quoteList;
		return true;
	}

	@Override
	public void deleteOutdatedQuotes(Date limitDateForDeletion)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public List<Integer> checkQuotes()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShareDB loadShare(String shareName)
	{
		return repliedShare;
	}

	@Override
	public boolean loadShares(List<ShareDB> listShares)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean storeShares(List<ShareDB> listShares)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean loadSharesIdInPortfolio(List<Integer> listSharesId, boolean keepNonMeaningfullShares)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean loadSharesInPortfolio(List<PortfolioDB> listSharesInPorfolios)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<PortfolioDB> loadSharesInPortfolio(ConditionPortfolioI condition)
	{
		return listPorfolioOriginal;
	}

	@Override
	public PortfolioDB getShareInPortfolio(int sharedId, int accountId)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updatePortfolio(PortfolioDB portfolioDB)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateListPortfolio(List<PortfolioDB> listToSave)
	{
		listPortofolioAfterProcessing=listToSave;
		return true;
	}

	@Override
	public boolean updateSchema()
	{
		// TODO Auto-generated method stub
		return false;
	}

	public List<QuoteDB> getListQuoteAfterProcessing()
	{
		return listQuoteAfterProcessing;
	}

	public List<PortfolioDB> getListPortofolioAfterProcessing()
	{
		return listPortofolioAfterProcessing;
	}

}
