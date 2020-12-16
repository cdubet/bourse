package portefeuilleTest.businessLogic;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestHtmlGeneration.class, 
				TestPortfolioManagement.class, 
				TestPortfolioManagement17Oct2016.class, 
				TestCsvExport.class,
				TestQuoteVariation.class ,
				TestShareDivision.class})
public class AllTestsBusinessLogic
{

}
