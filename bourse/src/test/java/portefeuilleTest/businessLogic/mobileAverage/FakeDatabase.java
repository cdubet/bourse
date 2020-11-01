package portefeuilleTest.businessLogic.mobileAverage;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import net.tuxanna.portefeuille.database.DatabaseI;
import net.tuxanna.portefeuille.database.PortfolioDB;
import net.tuxanna.portefeuille.database.QuoteDB;
import net.tuxanna.portefeuille.database.SearchQuoteAtOneDay;
import net.tuxanna.portefeuille.database.SearchQuoteI;
import net.tuxanna.portefeuille.database.ShareDB;
import net.tuxanna.portefeuille.database.ShareOrderI;


public class FakeDatabase implements DatabaseI
{
	List<QuoteDB> listQuoteToSend;
	boolean resultToSend;

	public FakeDatabase(List<QuoteDB> p_listQuoteToSend,boolean p_resultToSend)
	{
		listQuoteToSend=p_listQuoteToSend;
		resultToSend=p_resultToSend;
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
		return false;
	}
	@Override
	public QuoteDB readQuotationAtOneDay(SearchQuoteAtOneDay dummy)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean readQuotations(List<QuoteDB> listQuote, SearchQuoteI share)
	{
		listQuote.clear();
		listQuote.addAll(listQuoteToSend);
		return resultToSend;
	}

	@Override
	public boolean storeQuotation(List<QuoteDB> listQuote)
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
	public boolean loadValidSellOrders(List<ShareOrderI> listOrders, Date date)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateSellOrder(ShareOrderI Order)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public  PortfolioDB getShareInPortfolio(int sharedId, int accountId)
	{
		return null;
	}

	@Override
	public boolean updatePortfolio(PortfolioDB portfolioDB)
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
	public boolean updateBuyOrder(ShareOrderI buyOrder)
	{
		// TODO Auto-generated method stub
		return false;
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
	public void deleteOutdatedQuotes(Date limitDateForDeletion)
	{
	}

	@Override
	public boolean updateSchema()
	{
		return false;
	}

	@Override
	public List<Integer> checkQuotes()
	{
		return null;
	}

}
