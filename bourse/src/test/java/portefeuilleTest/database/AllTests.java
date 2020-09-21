package portefeuilleTest.database;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestDatabase.class, TestSearch.class,TestDatabaseWithDbUnit.class,TestCheckDatabase.class  })
public class AllTests
{

}
