package portefeuilleTest.database.util;

import java.sql.SQLException;

import net.tuxanna.portefeuille.database.Database;

public class RamDatabaseForTest extends Database
{

	public RamDatabaseForTest() throws ClassNotFoundException, SQLException
	{
		super();
		// TODO Auto-generated constructor stub
	}
	   // just overwrite this to change DB (ex for test in in memory)
	@Override
	public String getDatabaseName()
	{
			return "jdbc:hsqldb:mem:portefeuilleRamDb";
	}
}
