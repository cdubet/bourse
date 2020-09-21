package portefeuilleTest.businessLogic;


import java.util.Calendar;
import java.util.Date;


import net.tuxanna.portefeuille.util.ClockI;

public class FakeDate implements ClockI
{

	private int predefineYear;
	private int predefineMonth;
	private int predefineDayOfMonth;

	
	private int predefineHourOfDay;
	private int predefineMinute;
	private int predefineSecond;
	private int predefineMillisecond;
	
	public FakeDate()
	{
		//we are the monday 17 october 2016 at 10 am
		predefineYear=2016;
		predefineMonth= 9;//october, month starts at 0
		predefineDayOfMonth= 17;
	    
		predefineHourOfDay= 10;
		predefineMinute= 0;
		predefineSecond= 0;
		predefineMillisecond= 0;
	}

	@Override
	public Date getNow()
	{
		Calendar cal = Calendar.getInstance(); // creates calendar
		
	    cal.set(Calendar.YEAR, predefineYear);
	    cal.set(Calendar.MONTH, predefineMonth); //month starts at 0
	    cal.set(Calendar.DAY_OF_MONTH, predefineDayOfMonth);
	    
	    cal.set(Calendar.HOUR_OF_DAY, predefineHourOfDay);
	    cal.set(Calendar.MINUTE, predefineMinute);
	    cal.set(Calendar.SECOND,predefineSecond );
	    cal.set(Calendar.MILLISECOND, predefineMillisecond);
	    
	   return cal.getTime();
	}

	private Date skipWeekEnd(Calendar cal)
	{
		if ((cal.get(Calendar.DAY_OF_WEEK)== Calendar.SUNDAY) || (cal.get(Calendar.DAY_OF_WEEK)== Calendar.SATURDAY))
		{
			cal.add(Calendar.DAY_OF_YEAR, -1);
			return skipWeekEnd(cal);
		}
		//not WE
		return cal.getTime(); 
	}
	public java.util.Date getYesterday()
	{
		Date now=getNow();
		Calendar cal = Calendar.getInstance(); // creates calendar
	    cal.setTime(now); // sets it to now 

	    cal.add(Calendar.DAY_OF_YEAR, -1);

	    java.util.Date newDateBefore=skipWeekEnd(cal);
	    return newDateBefore;
	}
	

	public int getPredefineYear()
	{
		return predefineYear;
	}

	public void setPredefineYear(int predefineYear)
	{
		this.predefineYear = predefineYear;
	}

	public int getPredefineMonth()
	{
		return predefineMonth;
	}

	public void setPredefineMonth(int predefineMonth)
	{
		this.predefineMonth = predefineMonth;
	}

	public int getPredefineDayOfMonth()
	{
		return predefineDayOfMonth;
	}

	public void setPredefineDayOfMonth(int predefineDayOfMonth)
	{
		this.predefineDayOfMonth = predefineDayOfMonth;
	}

	public int getPredefineHourOfDay()
	{
		return predefineHourOfDay;
	}

	public void setPredefineHourOfDay(int predefineHourOfDay)
	{
		this.predefineHourOfDay = predefineHourOfDay;
	}

	public int getPredefineMinute()
	{
		return predefineMinute;
	}

	public void setPredefineMinute(int predefineMinute)
	{
		this.predefineMinute = predefineMinute;
	}

	public int getPredefineSecond()
	{
		return predefineSecond;
	}

	public void setPredefineSecond(int predefineSecond)
	{
		this.predefineSecond = predefineSecond;
	}

	public int getPredefineMillisecond()
	{
		return predefineMillisecond;
	}

	public void setPredefineMillisecond(int predefineMillisecond)
	{
		this.predefineMillisecond = predefineMillisecond;
	}

}
