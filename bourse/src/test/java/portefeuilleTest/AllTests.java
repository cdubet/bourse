package portefeuilleTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import dataFeed.boursorama.AllTestsDatafeed;
import portefeuilleTest.businessLogic.AllTestsBusinessLogic;
import portefeuilleTest.businessLogic.mobileAverage.AllTestsMobileAverage;
import portefeuilleTest.businessLogic.shareOrders.AllTestsShareOrders;
import portefeuilleTest.database.AllTestsDatabase;
import portefeuilleTest.util.AllTestsUtil;

@RunWith(Suite.class)
@SuiteClasses({ AllTestsDatafeed.class,
	AllTestsBusinessLogic.class,
	AllTestsMobileAverage.class,
	AllTestsShareOrders.class,
	AllTestsDatabase.class,
	AllTestsUtil.class})
public class AllTests
{

}
