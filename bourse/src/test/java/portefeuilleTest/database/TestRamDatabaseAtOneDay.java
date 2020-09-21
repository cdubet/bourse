package portefeuilleTest.database;

import java.io.File;
import java.sql.SQLException;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;

import net.tuxanna.portefeuille.database.Database;

public class TestRamDatabaseAtOneDay extends TestRamDatabase
{
	private String xmlFileName;
	
	@SuppressWarnings("rawtypes")
	private void cleanlyInsert(IDataSet dataSet) throws Exception 
	{
		Class driverClass = Class.forName("org.hsqldb.jdbcDriver");
		final String JDBC_DRIVER =driverClass.getName();


		IDatabaseTester databaseTester = new JdbcDatabaseTester(JDBC_DRIVER, getDatabaseName(), Database.getUsername(), Database.getPassword());
		//tell which DB we have
		DatabaseConfig config = databaseTester.getConnection().getConfig();
		config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new HsqldbDataTypeFactory());

		databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		databaseTester.setDataSet(dataSet);
		databaseTester.onSetup();
	}

	public TestRamDatabaseAtOneDay(String xmlFilenameToLoad) throws ClassNotFoundException, SQLException
	{
		xmlFileName=xmlFilenameToLoad;
	}

	public boolean setup() throws Exception
	{
		if (createSchema())
		{
			importDataSet();
			return true;
		}
		return false;
	}


	private IDataSet readDataSet() throws Exception 
	{
		return new FlatXmlDataSetBuilder().build(new File(xmlFileName));
	}



	public void importDataSet() throws Exception 
	{
		IDataSet dataSet = readDataSet();
		cleanlyInsert(dataSet);
	}
}
