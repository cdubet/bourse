package portefeuilleTest.util;

import net.tuxanna.portefeuille.util.MailParameters;

public class MailParametersForTest extends MailParameters
{
	@Override
	public boolean sanityCheck()
	{
		return true;	
	}
	@Override
	public String getMailTo()
	{
		return "dummy@test.com";
	}
	@Override
	public String getVersion()
	{
		return "0.07";
	}
}
