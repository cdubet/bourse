package portefeuilleTest.businessLogic;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import net.tuxanna.portefeuille.Quote;
import net.tuxanna.portefeuille.business_logic.util.ShareToQuotations;
import net.tuxanna.portefeuille.dataFeed.TickerI;
import net.tuxanna.portefeuille.database.QuoteDB;
import net.tuxanna.portefeuille.database.ShareDB;
import net.tuxanna.portefeuille.database.ShareDB.Currency;

public class TestCsvExport
{

	private boolean compareTwoFiles(File file1, File file2) throws IOException
	{
		BufferedReader reader1 = new BufferedReader(new FileReader(file1));
		BufferedReader reader2 = new BufferedReader(new FileReader(file2));

		while (true)
		{
			final String line1 = reader1.readLine();
			final String line2 = reader2.readLine();

			if (line1 == null) // End of file 1
			{
				boolean retVal;
				if  (line2 == null)
				{
					retVal= true;
				}
				else
				{
					retVal=false;
				}
				reader1.close();
				reader2.close();
				return retVal;
			}
			else if (line2 == null)
			{
				reader1.close();
				reader2.close();
				return false; // File 2 ended before file 1, so not equal 
			}
			else if (!line1.equals(line2)) // Non-null and different lines
			{
				reader1.close();
				reader2.close();
				return false;
			}
		}
	}

	@Test
	public void testExportQuoteCsv()
	{
		//prepare
		ShareToQuotations testData=new ShareToQuotations();
		testData.setClock(new FakeDate());
		
		List<ShareDB> listShares=new ArrayList<ShareDB>();

		final int SHARE_ID_1=1;
		ShareDB share1=new ShareDB(SHARE_ID_1,"name1", "ticker1",Currency.EURO,TickerI.TypeOfItem.TYPE_SHARE);
		listShares.add(share1);

		final int SHARE_ID_2=2;
		ShareDB share2=new ShareDB(SHARE_ID_2,"name2", "ticker2",Currency.EURO,TickerI.TypeOfItem.TYPE_SHARE);
		listShares.add(share2);

		{
			Quote pricing=new Quote();
			pricing.setLastTradedPrice(12.5);
			QuoteDB quoteDB=new QuoteDB(SHARE_ID_1,pricing);
			testData.add(share1, quoteDB);
		}
		{
			Quote pricing=new Quote();
			pricing.setLastTradedPrice(1);
			QuoteDB quoteDB=new QuoteDB(SHARE_ID_2,pricing);
			testData.add(share2, quoteDB);
		}

		File csvFile=new File("TEST_DATA/RESULT/export_csv.txt");
		
		//act
		testData.exportQuoteCsv(listShares, csvFile);

		//check
		try
		{
			assertTrue(compareTwoFiles(csvFile, new File("TEST_DATA/EXPECTED_RESULT/export_csv.txt")));
		}
		catch (IOException e)
		{
			e.printStackTrace();
			assertTrue(false);
		}
	}

}
