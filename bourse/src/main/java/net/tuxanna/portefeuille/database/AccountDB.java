package net.tuxanna.portefeuille.database;

public class AccountDB
{

	private int idAccount;
	private String name;
	
	public AccountDB()
	{
		idAccount=Database.NON_ASSIGNED;
		name=new String();
	}

	public int getIdAccount()
	{
		return idAccount;
	}

	public void setIdAccount(int idAccount)
	{
		this.idAccount = idAccount;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

}
