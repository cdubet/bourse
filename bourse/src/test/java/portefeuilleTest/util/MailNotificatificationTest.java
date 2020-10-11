package portefeuilleTest.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import net.tuxanna.portefeuille.businessLogic.util.ProblemNotification;
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
		MailParameters mailParam=new MailParameters();
		MailNotifier mail=new MailNotifier(mailParam);//TODO do not use real mail
		//TODO https://golb.hplar.ch/2019/08/catch-all-smtp.html & https://greenmail-mail-test.github.io/greenmail/
		
		
		//test
		boolean res=mail.notifyErrors(problemsToBeNotified);
		
		//check
		assertTrue(res);
		//we cannot check that the mail is really sent ...
		
	}

}
