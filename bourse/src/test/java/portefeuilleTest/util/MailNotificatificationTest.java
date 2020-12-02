package portefeuilleTest.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import net.tuxanna.portefeuille.business_logic.util.ProblemNotification;
import net.tuxanna.portefeuille.util.MailNotifier;
import net.tuxanna.portefeuille.util.MailParameters;

public class MailNotificatificationTest
{

	@Test
	public void testNotifyErrors()
	{
		List<ProblemNotification> problemsToBeNotified=new ArrayList<ProblemNotification>();
		
		problemsToBeNotified.add(new ProblemNotification("test pb1"));
		problemsToBeNotified.add(new ProblemNotification("test pb2"));
		problemsToBeNotified.add(new ProblemNotification("test pb3"));
		MailParameters mailParam=new MailParametersForTest();
		MailNotifier mail=new MailNotifier(mailParam);//TODO do not use real mail
		//TODO https://golb.hplar.ch/2019/08/catch-all-smtp.html & https://greenmail-mail-test.github.io/greenmail/
		
		//test
		//TODO boolean res=mail.notifyErrors(problemsToBeNotified);
		
		//check
		//TODO assertTrue(res);
		//we cannot check that the mail is really sent ...
		
	}

}
