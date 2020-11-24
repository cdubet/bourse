package portefeuilleTest.businessLogic.eval;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import net.tuxanna.portefeuille.business_logic.eval.Evaluation;
import net.tuxanna.portefeuille.business_logic.eval.EvaluationStorage;
import net.tuxanna.portefeuille.business_logic.eval.QuotationMapStorage;
import net.tuxanna.portefeuille.business_logic.eval.ShareIdAndDate;
import net.tuxanna.portefeuille.business_logic.util.ShareNameStorage;
import net.tuxanna.portefeuille.database.ConditionQuoteI;
import net.tuxanna.portefeuille.database.DatabaseI;
import net.tuxanna.portefeuille.database.PortfolioDB;
import net.tuxanna.portefeuille.database.QuoteDB;
import net.tuxanna.portefeuille.database.SearchQuoteAtOneDay;
import net.tuxanna.portefeuille.database.SearchQuoteI;
import net.tuxanna.portefeuille.database.ShareDB;
import net.tuxanna.portefeuille.database.ShareOrderI;
import net.tuxanna.portefeuille.util.DigitValue;

public class FakeDatabase implements DatabaseI
{
	private static final String SHARE_NAME_4 = "share4";
	private static final String SHARE_NAME_3 = "share3";
	private static final String SHARE_NAME_2 = "share2";
	private static final String SHARE_NAME_1 = "share1";
	private static final int ID_SHARE_1 = 0;
	private static final double PRICE_SHARE_1 = 25.;
	private static final double NEW_PRICE_SHARE_1 = 28.;
	private static final double OLD_PRICE_SHARE_1 = 27.;
	private static final double QTE_SHARE_1 = 100.;
	private static final DigitValue QTE_1 = new DigitValue(QTE_SHARE_1);
	private static final DigitValue UNIT_PRICE_1 = new DigitValue(PRICE_SHARE_1);

	private static final int ID_SHARE_2 = 1;
	private static final double PRICE_SHARE_2 = 10.;
	private static final double OLD_PRICE_SHARE_2 = 11.5;
	private static final double NEW_PRICE_SHARE_2 = 11.0;
	private static final double QTE_SHARE_2 = 10.;
	private static final DigitValue QTE_2 = new DigitValue(QTE_SHARE_2);
	private static final DigitValue UNIT_PRICE_2 = new DigitValue(PRICE_SHARE_2);

	private static final int ID_SHARE_3 = 2;
	private static final double PRICE_SHARE_3 = 33.3;
	private static final double OLD_PRICE_SHARE_3 = 31.5;
	private static final double NEW_PRICE_SHARE_3 = 30.5;
	private static final double QTE_SHARE_3 = 300.;
	private static final DigitValue QTE_3 = new DigitValue(QTE_SHARE_3);
	private static final DigitValue UNIT_PRICE_3 = new DigitValue(PRICE_SHARE_3);

	private static final int ID_SHARE_SAME_AS_1 = 0;
	private static final double PRICE_SHARE_SAME_AS_1 = 50.;
	private static final double QTE_SHARE_SAME_AS_1 = 200.;
	private static final DigitValue QTE_SAME_AS_1 = new DigitValue(QTE_SHARE_SAME_AS_1);
	private static final DigitValue UNIT_PRICE_SAME_AS_1 = new DigitValue(PRICE_SHARE_SAME_AS_1);

	private static final int ID_SHARE_SAME_AS_3 = 2;
	private static final double PRICE_SHARE_SAME_AS_3 = 45.;
	private static final double QTE_SHARE_SAME_AS_3 = 15.;
	private static final DigitValue QTE_SAME_AS_3 = new DigitValue(QTE_SHARE_SAME_AS_3);
	private static final DigitValue UNIT_PRICE_SAME_AS_3 = new DigitValue(PRICE_SHARE_SAME_AS_3);

	private static final int ID_SHARE_4 = 3;
	private static final double PRICE_SHARE_4 = 33.3;
	private static final double OLD_PRICE_SHARE_4 = 15.3;
	private static final double NEW_PRICE_SHARE_4 = 16.5;

	private static final double QTE_SHARE_4 = 300.;
	private static final DigitValue QTE_4 = new DigitValue(QTE_SHARE_4);
	private static final DigitValue UNIT_PRICE_4 = new DigitValue(PRICE_SHARE_4);

	public FakeDatabase()
	{
		// TODO Auto-generated constructor stub
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
		//add test items with 2 shares being in 2 different accounts (see SAME_AS_1 and SAME_AS_3)
		//shares should be sorted by ID like the one given by database class
		{
			PortfolioDB item=new PortfolioDB();
			item.setIdShare(ID_SHARE_1);
			item.setQte(QTE_1);
			item.setUnitPrice(UNIT_PRICE_1);
			listSharesInPorfolios.add(item);
		}
		{
			PortfolioDB item=new PortfolioDB();
			item.setIdShare(ID_SHARE_SAME_AS_1);
			item.setQte(QTE_SAME_AS_1);
			item.setUnitPrice(UNIT_PRICE_SAME_AS_1);
			listSharesInPorfolios.add(item);
		}
		{
			PortfolioDB item=new PortfolioDB();
			item.setIdShare(ID_SHARE_2);
			item.setQte(QTE_2);
			item.setUnitPrice(UNIT_PRICE_2);
			listSharesInPorfolios.add(item);
		}
		{
			PortfolioDB item=new PortfolioDB();
			item.setIdShare(ID_SHARE_3);
			item.setQte(QTE_3);
			item.setUnitPrice(UNIT_PRICE_3);
			listSharesInPorfolios.add(item);
		}
		{
			PortfolioDB item=new PortfolioDB();
			item.setIdShare(ID_SHARE_SAME_AS_3);
			item.setQte(QTE_SAME_AS_3);
			item.setUnitPrice(UNIT_PRICE_SAME_AS_3);
			listSharesInPorfolios.add(item);
		}
		{
			PortfolioDB item=new PortfolioDB();
			item.setIdShare(ID_SHARE_4);
			item.setQte(QTE_4);
			item.setUnitPrice(UNIT_PRICE_4);
			listSharesInPorfolios.add(item);
		}

		return true;
	}

	void fillQuotationMapStorage(QuotationMapStorage mapToFill,Date now,Date yesterday, ShareNameStorage mapShareToFill)
	{
		ArrayList<ShareDB> listShares=new ArrayList<ShareDB>();

		//fill now map
		{
			ShareDB share=new ShareDB(ID_SHARE_1,SHARE_NAME_1,"sh1",ShareDB.Currency.EURO,true);
			listShares.add(share);
			{
				ShareIdAndDate key1=new ShareIdAndDate(share,now);
				mapToFill.add(key1, NEW_PRICE_SHARE_1);
			}
			ShareIdAndDate key1=new ShareIdAndDate(share,yesterday);
			mapToFill.add(key1,OLD_PRICE_SHARE_1);
		}
		{
			ShareDB share=new ShareDB(ID_SHARE_2,SHARE_NAME_2,"sh2",ShareDB.Currency.EURO,true);
			listShares.add(share);
			{
				ShareIdAndDate key1=new ShareIdAndDate(share,now);
				mapToFill.add(key1, NEW_PRICE_SHARE_2);
			}
			ShareIdAndDate key1=new ShareIdAndDate(share,yesterday);
			mapToFill.add(key1,OLD_PRICE_SHARE_2);
		}
		{
			ShareDB share=new ShareDB(ID_SHARE_3,SHARE_NAME_3,"sh3",ShareDB.Currency.EURO,true);
			listShares.add(share);
			{
				ShareIdAndDate key1=new ShareIdAndDate(share,now);
				mapToFill.add(key1, NEW_PRICE_SHARE_3);
			}
			ShareIdAndDate key1=new ShareIdAndDate(share,yesterday);
			mapToFill.add(key1,OLD_PRICE_SHARE_3);
		}
		{
			ShareDB share=new ShareDB(ID_SHARE_4,SHARE_NAME_4,"sh4",ShareDB.Currency.EURO,true);
			listShares.add(share);
			{
				ShareIdAndDate key1=new ShareIdAndDate(share,now);
				mapToFill.add(key1, NEW_PRICE_SHARE_4);
			}
			ShareIdAndDate key1=new ShareIdAndDate(share,yesterday);
			mapToFill.add(key1,OLD_PRICE_SHARE_4);
		}
		mapShareToFill.fill(listShares);
	}
	
	
	
	void fillExpectedEvaluation(EvaluationStorage expected)
	{
		{
			Evaluation ev=new Evaluation();
			double qte=QTE_SHARE_1+QTE_SHARE_SAME_AS_1;
			final double boughtPrice=(QTE_SHARE_1*PRICE_SHARE_1+QTE_SHARE_SAME_AS_1*PRICE_SHARE_SAME_AS_1)/(QTE_SHARE_1+QTE_SHARE_SAME_AS_1);
			ev.setBoughtPrice(boughtPrice);

			ev.setCurrentPrice(NEW_PRICE_SHARE_1);
			ev.setIdShare(ID_SHARE_1);
			ev.setQte(qte);
			ev.setYesterdayPrice(OLD_PRICE_SHARE_1);
			ev.setShareName(SHARE_NAME_1);

			//compute gain and variation
			ev.computeVariation();

			expected.add(ev);
		}
		{
			Evaluation ev=new Evaluation();
			double qte=QTE_SHARE_2;

			ev.setBoughtPrice(PRICE_SHARE_2);

			ev.setCurrentPrice(NEW_PRICE_SHARE_2);
			ev.setIdShare(ID_SHARE_2);
			ev.setQte(qte);
			ev.setYesterdayPrice(OLD_PRICE_SHARE_2);
			ev.setShareName(SHARE_NAME_2);

			//compute gain and variation
			ev.computeVariation();

			expected.add(ev);
		}
		{
			Evaluation ev=new Evaluation();
			double qte=QTE_SHARE_3+QTE_SHARE_SAME_AS_3;
			final double boughtPrice=(QTE_SHARE_3*PRICE_SHARE_3+QTE_SHARE_SAME_AS_3*PRICE_SHARE_SAME_AS_3)/(QTE_SHARE_3+QTE_SHARE_SAME_AS_3);
			ev.setBoughtPrice(boughtPrice);

			ev.setCurrentPrice(NEW_PRICE_SHARE_3);
			ev.setIdShare(ID_SHARE_3);
			ev.setQte(qte);
			ev.setYesterdayPrice(OLD_PRICE_SHARE_3);
			ev.setShareName(SHARE_NAME_3);

			//compute gain and variation
			ev.computeVariation();

			expected.add(ev);
		}
		{
			Evaluation ev=new Evaluation();
			double qte=QTE_SHARE_4;

			ev.setBoughtPrice(PRICE_SHARE_4);

			ev.setCurrentPrice(NEW_PRICE_SHARE_4);
			ev.setIdShare(ID_SHARE_4);
			ev.setQte(qte);
			ev.setYesterdayPrice(OLD_PRICE_SHARE_4);
			ev.setShareName(SHARE_NAME_4);

			//compute gain and variation
			ev.computeVariation();

			expected.add(ev);
		}
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
		// TODO Auto-generated method stub
		return false;
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
