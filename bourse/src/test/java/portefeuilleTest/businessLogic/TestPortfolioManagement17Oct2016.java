package portefeuilleTest.businessLogic;

import static org.junit.Assert.*;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.tuxanna.portefeuille.business_logic.PortfolioManagement;
import net.tuxanna.portefeuille.business_logic.TopWorst;
import net.tuxanna.portefeuille.database.Database;
import portefeuilleTest.database.util.RamDatabaseForTest;

public class TestPortfolioManagement17Oct2016
{

	private static PortfolioManagement testPortfolio; //class under test
	private static RamDatabaseForTest testDb;

	private static final Logger logger = LogManager.getLogger(TestPortfolioManagement17Oct2016.class);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		testDb =new RamDatabaseForTest();
		assertTrue(testDb.createSchema());
		testPortfolio= new PortfolioManagement();
		testPortfolio.setClock(new FakeDate());
		testPortfolio.setDatabase(testDb);
		testPortfolio.setQuotationProvider(new FakeQuotationProvider());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
		testDb.shutdown();
	}

	private IDataSet readDataSet() throws Exception 
	{
		return new FlatXmlDataSetBuilder().build(new File("TEST_DATA/stock_and_quotes_for_17_oct_test.xml"));
	}

	@SuppressWarnings("rawtypes")
	private void cleanlyInsert(IDataSet dataSet) throws Exception 
	{
		Class driverClass = Class.forName("org.hsqldb.jdbcDriver");
		final String JDBC_DRIVER =driverClass.getName();


		IDatabaseTester databaseTester = new JdbcDatabaseTester(JDBC_DRIVER, testDb.getDatabaseName(), Database.getUsername(), Database.getPassword());
		//tell which DB we have
		DatabaseConfig config = databaseTester.getConnection().getConfig();
		config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new HsqldbDataTypeFactory());

		databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		databaseTester.setDataSet(dataSet);
		databaseTester.onSetup();
	}

	public void importDataSet() throws Exception 
	{
		IDataSet dataSet = readDataSet();
		cleanlyInsert(dataSet);
	}

	@Before
	public void setUp() throws Exception
	{
		importDataSet();
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testUpdate()
	{
		boolean res=testPortfolio.update();
		assertTrue(res);
		TopWorst topWorstIntraDay = testPortfolio.getTodayChange();
		logger.debug(topWorstIntraDay.toString());
		/*
		 * we should have
QuoteVariation [name=name1, oldPrice=2.0, newPrice=1.53, variation=-23.5%], 
QuoteVariation [name=name4, oldPrice=22.0, newPrice=20.93, variation=-4.863636363636365%], 
QuoteVariation [name=name8, oldPrice=78.0, newPrice=75.02, variation=-3.8205128205128256%], 
QuoteVariation [name=name6, oldPrice=60.0, newPrice=58.02, variation=-3.299999999999995%], 
QuoteVariation [name=name0, oldPrice=1.08, newPrice=1.05, variation=-2.77777777777778%], 
QuoteVariation [name=name7, oldPrice=69.9, newPrice=69.02, variation=-1.2589413447782685%], 
QuoteVariation [name=name9, oldPrice=10.0, newPrice=10.02, variation=0.19999999999999574%], 
QuoteVariation [name=name5, oldPrice=6.0, newPrice=6.02, variation=0.3333333333333262%], 
QuoteVariation [name=name10, oldPrice=16.03, newPrice=16.2, variation=1.0605115408608743%], 
QuoteVariation [name=name11, oldPrice=12.0, newPrice=12.302, variation=2.5166666666666635%], 
QuoteVariation [name=name3, oldPrice=41.0, newPrice=42.53, variation=3.7317073170731736%], 
QuoteVariation [name=name2, oldPrice=3.4, newPrice=3.9, variation=14.705882352941178%]]]
		 */
		assertEquals(topWorstIntraDay.getWorst5().size(),5);
		assertEquals(topWorstIntraDay.getWorst5().get(0).getName(), "name1");
		assertEquals(topWorstIntraDay.getWorst5().get(1).getName(), "name4");
		assertEquals(topWorstIntraDay.getWorst5().get(2).getName(), "name8");
		assertEquals(topWorstIntraDay.getWorst5().get(3).getName(), "name6");
		assertEquals(topWorstIntraDay.getWorst5().get(4).getName(), "name0");
		
		assertEquals(topWorstIntraDay.getTop5().size(),5);
		assertEquals(topWorstIntraDay.getTop5().get(0).getName(), "name2");
		assertEquals(topWorstIntraDay.getTop5().get(1).getName(), "name3");
		assertEquals(topWorstIntraDay.getTop5().get(2).getName(), "name11");
		assertEquals(topWorstIntraDay.getTop5().get(3).getName(), "name10");
		assertEquals(topWorstIntraDay.getTop5().get(4).getName(), "name5");
		
		///////////////////////////////////////////////////////////
		// check top/worst last day
		TopWorst topWorstLastDay = testPortfolio.getYesterdayChange();
		logger.debug(topWorstLastDay.toString());
		assertEquals(topWorstLastDay.getWorst5().size(),5);
		assertEquals(topWorstLastDay.getWorst5().get(0).getName(), "name1");
		assertEquals(topWorstLastDay.getWorst5().get(1).getName(), "name4");
		assertEquals(topWorstLastDay.getWorst5().get(2).getName(), "name8");
		assertEquals(topWorstLastDay.getWorst5().get(3).getName(), "name6");
		assertEquals(topWorstLastDay.getWorst5().get(4).getName(), "name7");
		
		assertEquals(topWorstLastDay.getTop5().size(),5);
		assertEquals(topWorstLastDay.getTop5().get(0).getName(), "name2");
		assertEquals(topWorstLastDay.getTop5().get(1).getName(), "name5");
		assertEquals(topWorstLastDay.getTop5().get(2).getName(), "name11");
		assertEquals(topWorstLastDay.getTop5().get(3).getName(), "name9");
		assertEquals(topWorstLastDay.getTop5().get(4).getName(), "name3");
	}

}
