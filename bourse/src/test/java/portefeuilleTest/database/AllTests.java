package portefeuilleTest.database;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestDatabaseStorage.class,
	TestSearch.class,
	TestDatabaseWithDbUnit.class,
	TestCheckDatabase.class,
	TestShareCases.class  })

public class AllTests
{

}
