package portefeuilleTest.database;

import java.sql.SQLException;

import net.tuxanna.portefeuille.database.Database;

public class TestRamDatabase extends Database
{

	public TestRamDatabase() throws ClassNotFoundException, SQLException
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
