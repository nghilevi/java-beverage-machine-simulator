package answer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class TimeUtils {
	/*
	 * Generate random point in time based on a specific hour
	 */
	public static String generateTimeBasedOnHour(int currentHour){
		Random random = new Random();
		int randomNumber = random.nextInt(59) + 0;
		String randomTime;
		if(randomNumber<10){
			randomTime=currentHour+":0"+randomNumber;
		}else{
			randomTime=currentHour+":"+randomNumber;
		}
		
		return randomTime;
	}
	
	/*
	 * Compare two time
	 * The result can be:
	 * 0 if first time equals second time
	 * greater than 0 if first time is after/bigger than second time
	 * less than 0 if first time is before/less than second time
	 */
	public static int compareTime(String time1,String time2){
		SimpleDateFormat format= new SimpleDateFormat("hh:mm");
	    Date t1=null,t2=null;
	    try {
			t1 = format.parse(time1);
			t2 = format.parse(time2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    return t1.compareTo(t2);
	}
	
	/*
	 * Calculate the difference between two time in minutes
	 */
	public static int differentBetweenTime(String time1,String time2){
		SimpleDateFormat format= new SimpleDateFormat("hh:mm"); //TODO
	    Date t1=null,t2=null;
	    try {
			t1 = format.parse(time1);
			t2 = format.parse(time2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    return (int) (Math.abs(t1.getTime()-t2.getTime())/60000);
	}
	
	/*
	 * Add minutes to a time and return the result time
	 */
	public static String addMinutes(String startTime,float minutes){
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		Date d=null;
		try {
			d = df.parse(startTime);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.MINUTE, (int) minutes);
		return df.format(cal.getTime());
	}

}
