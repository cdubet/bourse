package portefeuilleTest.database;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.Calendar;
import java.util.Date;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.tuxanna.portefeuille.database.Database;
import portefeuilleTest.database.util.RamDatabaseForTest;


public class TestDatabaseWithDbUnit_ManyQuotes
{

	private static RamDatabaseForTest testDb;
	private IDatabaseTester databaseTester;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		testDb = new RamDatabaseForTest();
		assertTrue(testDb.createSchema());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
		testDb.shutdown();
	}

	private IDataSet readDataSet() throws Exception
	{
		FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
		return builder.build(new File("TEST_DATA/huge_quotes.xml"));
	}


	@SuppressWarnings("rawtypes")
	private void cleanlyInsert(IDataSet dataSet) throws Exception
	{
		//TODO share code between 2 junit/dbunit classes
		Class driverClass = Class.forName("org.hsqldb.jdbcDriver");
		final String JDBC_DRIVER = driverClass.getName();

		databaseTester = new JdbcDatabaseTester(JDBC_DRIVER, testDb.getDatabaseName(), Database.getUsername(),
				Database.getPassword());
		// tell which DB we have
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
	public void testDelete()
	{
		try
		{
			Calendar cal = Calendar.getInstance(); // creates calendar

			// test data are from 16/10/16 to 15/11/16
			//delete all before 09/11/16 -> check we have not deleted too much
			cal.set(Calendar.YEAR, 2016);
			cal.set(Calendar.MONTH, 10); //month starts at 0
			cal.set(Calendar.DAY_OF_MONTH, 9);

			cal.set(Calendar.HOUR_OF_DAY, 23);


			Date limitDateForDeletion = cal.getTime();
			testDb.deleteOutdatedQuotes(limitDateForDeletion);
			
			//now check the result
			ITable actualTable = databaseTester.getConnection().createDataSet().getTable("QUOTES");
			/* export the data to a partial.xml to see what is wrong*/
//			QueryDataSet partialDataSet = new QueryDataSet(databaseTester.getConnection());
//			partialDataSet.addTable("QUOTES");
//			FlatXmlDataSet.write(partialDataSet, new FileOutputStream("partial.xml"));
			
			// default path workspace/protefeuilleTest/
			FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
			builder.setMetaDataSetFromDtd(new FileInputStream("TEST_DATA/quotes.dtd"));
			IDataSet expectedDataSet = builder.build(new FileInputStream("TEST_DATA/expected_result_stripped_quotes.xml"));
			ITable expectedTable = expectedDataSet.getTable("QUOTES");

			org.dbunit.assertion.DbUnitAssert assertion=new org.dbunit.assertion.DbUnitAssert() ;
			assertion.assertEquals(expectedTable, actualTable);
			// do not use junit assert but dbunit one as it is not the same type !!
			// assertEquals(expectedTable, actualTable);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	}
