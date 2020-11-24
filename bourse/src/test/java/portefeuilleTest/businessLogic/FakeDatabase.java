package portefeuilleTest.businessLogic;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import net.tuxanna.portefeuille.database.ConditionQuoteI;
import net.tuxanna.portefeuille.database.DatabaseI;
import net.tuxanna.portefeuille.database.PortfolioDB;
import net.tuxanna.portefeuille.database.QuoteDB;
import net.tuxanna.portefeuille.database.SearchQuoteAtOneDay;
import net.tuxanna.portefeuille.database.SearchQuoteI;
import net.tuxanna.portefeuille.database.ShareDB;
import net.tuxanna.portefeuille.database.ShareOrderI;

public class FakeDatabase implements DatabaseI
{

	private List<QuoteDB> storedQuotes;

	public FakeDatabase()
	{
	}

	@Override
	public boolean loadShares(List<ShareDB> listShares)
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
	public PortfolioDB getShareInPortfolio(int sharedId, int accountId)
	{
		// TODO Auto-generated method stub
		return null;
	}

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
	public QuoteDB readQuotationAtOneDay(SearchQuoteAtOneDay dummy)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Double> getHighTradedPrice(int shareId)
	{
		return Optional.ofNullable(null);
	}
	@Override
	public Optional<Double> getLowTradedPrice(int shareId)
	{
		return Optional.ofNullable(null);
	}
	@Override
	public boolean readQuotations(List<QuoteDB> listQuote, SearchQuoteI share)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean storeQuotation(List<QuoteDB> listQuote)
	{
		storedQuotes=listQuote;
		return true;
	}

	@Override
	public boolean storeShares(List<ShareDB> listShares)
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
	public boolean updatePortfolio(PortfolioDB portfolioDB)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteOutdatedQuotes(Date limitDateForDeletion)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public boolean updateSchema()
	{
		// TODO Auto-generated method stub
		return false;
	}

	public List<QuoteDB> getStoredQuotes()
	{
		return storedQuotes;
	}

	@Override
	public List<Integer> checkQuotes()
	{
		return null;
	}

	@Override
	public ShareDB loadShare(String shareName)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QuoteDB> readQuotations(ConditionQuoteI condition)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
