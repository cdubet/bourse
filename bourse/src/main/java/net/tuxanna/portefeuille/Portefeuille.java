package net.tuxanna.portefeuille;

import java.io.File;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.tuxanna.portefeuille.business_logic.PortfolioManagement;
import net.tuxanna.portefeuille.data_feed.boursorama.BoursoramaQuotationProvider;
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
	private String user;
	
	@Option(names = "--server", description="smtp server")
	private String smtpServer;
	
	@Option(names = "--mailTo", description="destination mail")
	private String mailTo;
	
	@Option(names = "--p1", arity = "0..1" /* TODO, interactive = true*/)
	private String p1;
	@Option(names = "--p2", arity = "0..1" /* TODO, interactive = true*/)
	private String p2;
	
	@Option(names = "--threads", description="threads for getting quotes from internet")
	private Integer nbThreads;
	
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

	private void generateCSV(File csvFileName,int nbThreadsForDownloading)
	{
		Database db;
		try
		{
			db = new Database();
			PortfolioManagement portfolio = setupPortfolio(db,nbThreadsForDownloading);

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
			PortfolioManagement portfolio = setupPortfolio(db,1 /*does not matter here*/);

			//do the job
			portfolio.deleteOutdatedQuotes();
			db.shutdown();
		}
		catch (SQLException | ClassNotFoundException e)
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
			PortfolioManagement portfolio = setupPortfolio(db,1 /*does not matter here*/);

			//do the job
			portfolio.checkQuotes();
			db.shutdown();
		}
		catch (SQLException | ClassNotFoundException e)
		{
			logger.error("exception received",e);
			e.printStackTrace();
		}	
	}
	private static void updatePortfolio(boolean isEvaluateRequired, MailParameters mailParam, Integer nbThreadsForDownloadingQuotes)
	{
		//update
		try
		{
			Database db = new Database();
			//
			// config
			PortfolioManagement portfolio = setupPortfolio(db,nbThreadsForDownloadingQuotes);

			//do the job
			if (portfolio.update() && isEvaluateRequired)
			{
				portfolio.evaluate();
				portfolio.deleteOutdatedQuotes();
			}
			
			//notify
			PortfolioNotifierI notifier=new MailNotifier(mailParam);
			portfolio.notifyResults(notifier);

			db.shutdown();
		}
		catch (SQLException |ClassNotFoundException e)
		{
			logger.error("exception received",e);
			e.printStackTrace();
		}


	}

	private static PortfolioManagement setupPortfolio(Database db,int nbThreadsForDownloading)
	{
		PortfolioManagement portfolio=new PortfolioManagement();
		portfolio.setDatabase(db);
		portfolio.setQuotationProvider(new BoursoramaQuotationProvider(nbThreadsForDownloading));
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
			MailParameters mailParam=new MailParameters(VERSION,smtpServer,mailTo,user,passwd);
			if (nbThreads==null)
			{
				//use default
				nbThreads=3;
			}
			updatePortfolio(true /* eval*/,mailParam,nbThreads);
		}
		else if (stripQuotes)
		{
			stripQuotes();
		}
		else if (csvFiles !=null)
		{
			if (nbThreads==null)
			{
				//use default
				nbThreads=3;
			}
			generateCSV(csvFiles,nbThreads);
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
			final String passwd=p1+p2;
			MailParameters mailParam=new MailParameters(VERSION,smtpServer,mailTo,user,passwd);
			if (nbThreads==null)
			{
				//use default
				nbThreads=3;
			}
			updatePortfolio(false /* no eval*/,mailParam,nbThreads);
		}
	}

}
