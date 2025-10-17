package net.tuxanna.portefeuille.util;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.tuxanna.portefeuille.business_logic.util.ProblemNotification;


public class MailNotifier implements PortfolioNotifierI
{
	private static final Logger logger = LogManager.getLogger(MailNotifier.class);

	private MailParameters mailParameters;

	private boolean sanityCheck()
	{
		return mailParameters.sanityCheck();
	}
	/* (non-Javadoc)
	 * @see net.tuxanna.portefeuille.util.PortfolioNotifier#notifyUser(java.lang.String)
	 */
	@Override
	public boolean notifyUser(List<ReportI> report)
	{
		if (!sanityCheck())
		{
			return false;
		}
		//see https://commons.apache.org/proper/commons-email/userguide.html

		//Email email = new SimpleEmail();
		HtmlEmail email = new HtmlEmail();

		email.setHostName(mailParameters.getSmtpServer());
		email.setSmtpPort(587);//465
		email.setAuthenticator(new DefaultAuthenticator(mailParameters.getUser(), mailParameters.getPasswd()));
		email.setStartTLSEnabled(true) ;
		try
		{
			//email.setDebug(true);
			email.setFrom("cdubet@gmx.net");
			email.setSubject("Portefeuille "+mailParameters.getVersion());
			StringBuilder message=new StringBuilder();
			for (Iterator<ReportI> iterator = report.iterator(); iterator.hasNext();)
			{
				ReportI reportI = (ReportI) iterator.next();
				message.append(reportI.toHtml());
			}
			email.setHtmlMsg(message.toString());
			String[] arrayAddresses = mailParameters.getMailTo().split(",");
			email.addTo(arrayAddresses);
			email.send();
		}
		catch (EmailException e)
		{
			logger.error("exception received",e);
			return false;
		}
		return true;
	}

	//ctor
	public MailNotifier(MailParameters param)
	{
		mailParameters=param;
	}

	@Override
	public boolean notifyErrors(List<ProblemNotification> err)
	{
		if (!sanityCheck())
		{
			return false;
		}
		if (err.size() ==0)
		{
			return true;
		}

		Email email = new SimpleEmail();

		email.setHostName(mailParameters.getSmtpServer());
		email.setSmtpPort(587);//465
		email.setAuthenticator(new DefaultAuthenticator(mailParameters.getUser(), mailParameters.getPasswd()));
		email.setStartTLSEnabled(true) ;
		try
		{
			//email.setDebug(true);
			email.setFrom("cdubet@gmx.net");
			email.setSubject("Err Portefeuille "+mailParameters.getVersion());
			StringBuilder message=new StringBuilder();
			for (ProblemNotification problemNotification : err)
			{
				message.append(problemNotification);
				message.append("\n");
			}
			email.setMsg(message.toString());
			email.addTo(mailParameters.getMailTo());
			email.send();
		}
		catch (EmailException e)
		{
			logger.error("exception received",e);
			return false;
		}
		return true;

	}

}
