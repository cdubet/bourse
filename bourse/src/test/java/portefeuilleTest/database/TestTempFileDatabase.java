package portefeuilleTest.database;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.tuxanna.portefeuille.database.Database;

public class TestTempFileDatabase extends Database
{

	private static final Logger logger = LogManager.getLogger(TestTempFileDatabase.class);
	public TestTempFileDatabase() throws ClassNotFoundException, SQLException
	{
		// TODO Auto-generated constructor stub
	}
	// just overwrite this to change DB (ex for test in in memory)
	@Override
	public String getDatabaseName()
	{
		//TODO change the path here !
		logger.debug("using /home/christian/tmp/portefeuilleDb");
		return "jdbc:hsqldb:file:/home/christian/tmp/portefeuilleDb";
	}
	
}
