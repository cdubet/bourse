package net.tuxanna.portefeuille;

import java.io.File;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.tuxanna.portefeuille.businessLogic.PortfolioManagement;
import net.tuxanna.portefeuille.dataFeed.boursorama.BoursoramaQuotationProvider;
import net.tuxanna.portefeuille.database.Database;
import net.tuxanna.portefeuille.util.MailNotifier;
import net.tuxanna.portefeuille.util.MailParameters;
import net.tuxanna.portefeuille.util.PortfolioNotifierI;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.ParameterException;


@Command(name = "portefeuille", description = "gestion portefeuille")

public class Portefeuille   implements Runnable  {

	private static final String VERSION="1.09";
	private static final Logger logger = LogManager.getLogger(Portefeuille.class);

	@Option(names={"-create"}, description="create database", required=false)  
	private boolean createDB;  
	  
	@Option(names={"-h", "--help"}, description="Display help/usage.", help=true)  
	private boolean help; 
	   
	@Option(names={"-evaluate"}, description="evaluation portefeuille")  
	private boolean evaluate; 
	
	@Option(names={"-strip_quotes"}, description="suppression cours trop ancien")  
	private boolean stripQuotes; 
	
	@Option(names={"-csv"}, description="export CSV des cours issus boursorama")  
    private File csvFiles=null;
	
	@Option(names={"-u","update_schema"}, description="update DB schema")  
    private boolean updateSchema;
	
	@Option(names={"-check"}, description="check database (detect strong variations)")  
    private boolean checkDatabase;
	
	@Option(names = "--user", description="email user")
	String user;
	
	@Option(names = "--server", description="smtp server")
	String smtpServer;
	
	@Option(names = "--mailTo", description="destination mail")
	String mailTo;
	
	@Option(names = "--p1", arity = "0..1" /* TODO, interactive = true*/)
	String p1;
	@Option(names = "--p2", arity = "0..1" /* TODO, interactive = true*/)
	String p2;
	
	@Option(names = "--threads", description="threads for getting quotes from internet")
	Integer nbThreads;
	
	public static void main(String[] args) 
	{
        Portefeuille portefeuille = new Portefeuille();
        try 
        {
            new CommandLine(portefeuille).execute(args);
        } 
        catch (ParameterException ex) 
        { // handle invalid input
            System.err.println(ex.getMessage());
            CommandLine.usage(portefeuille, System.err);
            return;
        }
	}

	private static void createDatabase()
	{
		//create database
		Database db;
		try
		{
			db = new Database();
			System.out.println("creating DB at"+db.getDatabaseName());
			db.createSchema();
			db.shutdown();
			System.out.println("DB created");
		}
		catch (ClassNotFoundException | SQLException e)
		{
			logger.error("exception received",e);
			e.printStackTrace();
		}
	}

	private void updateSchema()
	{
		Database db;
		try
		{
			db = new Database();
			if (!db.updateSchema())
			{
				logger.error("updating schema failed");
			}
			db.shutdown();
		}
		catch (ClassNotFoundException | SQLException e)
		{
			logger.error("exception received",e);
			e.printStackTrace();
		}
		
	}

	private void generateCSV(File csvFileName)
	{
		Database db;
		try
		{
			db = new Database();
			PortfolioManagement portfolio = setupPortfolio(db);

			//do the job
			portfolio.exportQuoteCsv(csvFileName);
			db.shutdown();
		}
		catch (ClassNotFoundException | SQLException e)
		{
			logger.error("exception received",e);
			e.printStackTrace();
		}		
	}

	private static void stripQuotes()
	{
		try
		{
			Database db = new Database();
			PortfolioManagement portfolio = setupPortfolio(db);

			//do the job
			portfolio.deleteOutdatedQuotes();
			db.shutdown();
		}
		catch (SQLException e)
		{
			logger.error("exception received",e);
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			logger.error("exception received",e);
			e.printStackTrace();
		}
		
	}
	private static void checkDatabase()
	{
		try
		{
			Database db = new Database();
			PortfolioManagement portfolio = setupPortfolio(db);

			//do the job
			portfolio.checkQuotes();
			db.shutdown();
		}
		catch (SQLException e)
		{
			logger.error("exception received",e);
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			logger.error("exception received",e);
			e.printStackTrace();
		}
		
	}
	private static void updatePortfolio(boolean isEvaluateRequired, String user, String smtpServer, String password, String mailTo)
	{
		//update
		try
		{
			Database db = new Database();
			//
			// config
			PortfolioManagement portfolio = setupPortfolio(db);

			//do the job
			if (portfolio.update())
			{
				if (isEvaluateRequired)
				{
					portfolio.evaluate();
					portfolio.deleteOutdatedQuotes();
				}
			}
			
			//notify
			MailParameters mailParam=new MailParameters(VERSION,smtpServer,mailTo,user,password);
			PortfolioNotifierI notifier=new MailNotifier(mailParam);
			portfolio.notifyResults(notifier);

			db.shutdown();
		}
		catch (SQLException e)
		{
			logger.error("exception received",e);
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			logger.error("exception received",e);
			e.printStackTrace();
		}
	}

	private static PortfolioManagement setupPortfolio(Database db)
	{
		PortfolioManagement portfolio=new PortfolioManagement();
		portfolio.setDatabase(db);
		portfolio.setQuotationProvider(new BoursoramaQuotationProvider(8));//TODO change hard coded
		return portfolio;
	}

	@Override
	public void run()
	{
		if (createDB)
		{
			createDatabase();
		}
		else if (evaluate)
		{			
			final String passwd=p1+p2;
			updatePortfolio(true /* eval*/,user,smtpServer,passwd,mailTo);
		}
		else if (stripQuotes)
		{
			stripQuotes();
		}
		else if (csvFiles !=null)
		{
			generateCSV(csvFiles);
		}
		else if (updateSchema)
		{
			updateSchema();
		}
		else if (checkDatabase)
		{
			checkDatabase();
		}
		else
		{
			//no option
			updatePortfolio(false /* no eval*/,user,smtpServer,p1+p2,mailTo);
		}
	}

}
