package answer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class TimeUtils {
	static SimpleDateFormat hourFormat= new SimpleDateFormat("HH:mm");
	/*
	 * Generate random point in time based on a specific hour
	 */
	public static String generateTimeBasedOnHour(int currentHour){
		String randomTime;
		int randomNumber = new Random().nextInt(59) + 0;
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
		time1=sanitizeTimeString(time1);
		time2=sanitizeTimeString(time2);
		int result = Integer.parseInt(time1.substring(0, 2))-Integer.parseInt(time2.substring(0, 2));
	    if(result==0){
	    	Date t1=null,t2=null;
		    try {
				t1 = hourFormat.parse(time1);
				t2 = hourFormat.parse(time2);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		    return t1.compareTo(t2);
	    }else if(result>0){
	    	return 1;
	    }else{
	    	return -1;
	    }
	}
	
	private static String sanitizeTimeString(String time){
		if (time.substring(1,2).equals(":")){
			time="0"+time;
		}
		return time;
	}
	
	/*
	 * Calculate the difference between two time in minutes
	 */
	public static int differentBetweenTime(String time1,String time2){
	    Date t1=null,t2=null;
	    try {
			t1 = hourFormat.parse(time1);
			t2 = hourFormat.parse(time2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    return (int) (Math.abs(t1.getTime()-t2.getTime())/60000);
	}
	
	/*
	 * Add minutes to a time and return the result time
	 */
	public static String addMinutes(String startTime,float minutes){
		Date d=null;
		try {
			d = hourFormat.parse(startTime);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.MINUTE, (int) minutes);
		return hourFormat.format(cal.getTime());
	}

}
