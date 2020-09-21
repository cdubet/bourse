package protefeuilleTest;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.junit.Test;



public class TestCvs
{

	@Test
	public void testGetCSV()
	{
		FileReader fic;
		org.jooq.tools.csv.CSVReader csv=null;
		try
		{
			fic = new FileReader("TEST_DATA/CVS/quotes.csv");
			List<String[]> all = null;
			BufferedReader br = null;
			br = new BufferedReader(fic);
			csv = new org.jooq.tools.csv.CSVReader(br);
			all = csv.readAll();
			assertEquals(all.size(),2);
			assertEquals(all.get(0).length,13);
			assertEquals(all.get(1).length,13);
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if (csv != null)
			{
				try
				{
					csv.close();
				}
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	@Test
	public void testGetCSVFromYahoo()
	{
		org.jooq.tools.csv.CSVReader csv=null;
		try
		{
			URL url=new URL("http://download.finance.yahoo.com/d/quotes.csv?s=AIR.PA,IBM&f=l1c1ohgvjkm3m4prs7&e=.csv");
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			csv = new org.jooq.tools.csv.CSVReader(br);
			List<String[]> all = csv.readAll();

//			String str;
//			do
//			{
//				 str=br.readLine();
//			} while (str!=null);
			
//			ReadableByteChannel rbc = Channels.newChannel(url.openStream());
//			BufferedInputStream in=new BufferedInputStream(rbc.read(dst))
//			FileOutputStream fos = new FileOutputStream("TEST_DATA/RESULT/information.txt");
//			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
//			fos.close();
			assertEquals(all.size(),2);
			assertEquals(all.get(0).length,13);
			assertEquals(all.get(1).length,13);
		}
		catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if (csv != null)
			{
				try
				{
					csv.close();
				}
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
