package net.tuxanna.portefeuille.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MailParameters
{
	private static final Logger logger = LogManager.getLogger(MailParameters.class);
	private String version;

	private String smtpServer;

	private String mailTo;

	private String user;

	private String passwd;

	public MailParameters()
	{
		version="";
		smtpServer="";
		mailTo="";
		user="";
		passwd="";
	}
	public MailParameters(String version, String smtpServer, String mailTo, String user, String passwd)
	{
		super();
		this.version = version;
		this.smtpServer = smtpServer;
		this.mailTo = mailTo;
		this.user = user;
		this.passwd = passwd;
	}

	public boolean sanityCheck()
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
	


	public String getVersion()
	{
		return version;
	}

	public void setVersion(String version)
	{
		this.version = version;
	}

	public String getSmtpServer()
	{
		return smtpServer;
	}

	public void setSmtpServer(String smtpServer)
	{
		this.smtpServer = smtpServer;
	}

	public String getMailTo()
	{
		return mailTo;
	}

	public void setMailTo(String mailTo)
	{
		this.mailTo = mailTo;
	}

	public String getUser()
	{
		return user;
	}

	public void setUser(String user)
	{
		this.user = user;
	}

	public String getPasswd()
	{
		return passwd;
	}

	public void setPasswd(String passwd)
	{
		this.passwd = passwd;
	}

	public static Logger getLogger()
	{
		return logger;
	}

}
