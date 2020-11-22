package portefeuilleTest.database.util;

import java.sql.Connection;
import java.sql.SQLException;

import net.tuxanna.portefeuille.database.Database;


public class MockDatabase extends Database
{

	public MockDatabase(Connection connection) throws ClassNotFoundException, SQLException
	{
		super(connection);
	}

}
