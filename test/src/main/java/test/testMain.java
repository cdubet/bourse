package test;

import picocli.CommandLine;
import picocli.CommandLine.Option;

public class testMain  implements Runnable 
{
	@Option(names = "--user")
	String user;

	@Option(names = "--password", arity = "0..1", interactive = true)
	String password;
	//private static Connection conn;             //our connection to the db - persist for life of program

	public testMain()
	{
		// TODO Auto-generated constructor stub
	}
    public void run() {
        if (user.length() > 0) {
            System.out.println(user.length() + " user..."+user);
        }
        if (password.length() > 0) {
            System.out.println(password.length() + " password..."+password);
        }
    }

	public static void main(String[] args)
	{
   	 new CommandLine(new testMain()).execute(args);
		System.out.print("coucu");
//		DSLContext create = DSL.using(conn, SQLDialect.HSQLDB);

	}



}
