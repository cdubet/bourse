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

import net.tuxanna.portefeuille.businessLogic.util.ProblemNotification;


public class MailNotifier implements PortfolioNotifierI
{
	private static final Logger logger = LogManager.getLogger(MailNotifier.class);

	private String version;

	private String smtpServer;

	private String mailTo;

	private String user;

	private String passwd;

	boolean sanityCheck()
	{
		if ((smtpServer==null) || (smtpServer.length()==0))
		{
			logger.error("mail stmtpserver not set");
			return false;
		}
		if ((mailTo==null) || (mailTo.length()==0))
		{
			logger.error("mail mailTo not set");
			return false;
		}
		if ((user==null) || (user.length()==0))
		{
			logger.error("mail user not set");
			return false;
		}
		if ((passwd==null) || (passwd.length()==0))
		{
			logger.error("mail passwd not set");
			return false;
		}
		return true;
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

		email.setHostName(smtpServer);
		email.setSmtpPort(587);//465
		email.setAuthenticator(new DefaultAuthenticator(user, passwd));
		email.setStartTLSEnabled(true) ;
		try
		{
			//email.setDebug(true);
			email.setFrom("cdubet@gmx.net");
			email.setSubject("Portefeuille "+version);
			StringBuilder message=new StringBuilder();
			for (Iterator<ReportI> iterator = report.iterator(); iterator.hasNext();)
			{
				ReportI reportI = (ReportI) iterator.next();
				message.append(reportI.toHtml());
			}
			email.setHtmlMsg(message.toString());
			email.addTo(mailTo);
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
	public MailNotifier(String user2, String password, String smtpServer2,String mailTo2)
	{
		smtpServer=smtpServer2;
		mailTo=mailTo2;
		user=user2;
		passwd=password;
	}

	@Override
	public void setVersion(String vers)
	{
		version=vers;
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

		email.setHostName(smtpServer);
		email.setSmtpPort(587);//465
		email.setAuthenticator(new DefaultAuthenticator(user, passwd));
		email.setStartTLSEnabled(true) ;
		try
		{
			//email.setDebug(true);
			email.setFrom("cdubet@gmx.net");
			email.setSubject("Err Portefeuille "+version);
			StringBuilder message=new StringBuilder();
			for (ProblemNotification problemNotification : err)
			{
				message.append(problemNotification);
				message.append("\n");
			}
			email.setMsg(message.toString());
			email.addTo(mailTo);
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
