package com.mindtree.migrationaccelerator.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

public class TimeZoneUility {

	private static List<String> timezones = new ArrayList<String>();
	final static Logger logger = Logger.getLogger(TimeZoneUility.class);
	
	//Referred from : https://www.mkyong.com/java/java-display-list-of-timezone-with-gmt/	
	static {

		String[] ids = TimeZone.getAvailableIDs();
		for (String id : ids) {
			String timezone = displayTimeZone(TimeZone.getTimeZone(id));
			timezones.add(timezone);
		}
	}

	public static List<String> getAllTimezones(){
		return timezones;
	}
	
	private static String displayTimeZone(TimeZone tz) {

		long hours = TimeUnit.MILLISECONDS.toHours(tz.getRawOffset());
		long minutes = TimeUnit.MILLISECONDS.toMinutes(tz.getRawOffset()) 
                                  - TimeUnit.HOURS.toMinutes(hours);
		// avoid -4:-30 issue
		minutes = Math.abs(minutes);

		String result = "";
		if (hours > 0) {
			result = String.format(" %s (GMT+%d:%02d)", tz.getID(), hours, minutes);
		} else {
			result = String.format(" %s (GMT%d:%02d)", tz.getID(), hours, minutes);
		}

		return result;
	}
	
	public static Date getCurrentDateTime() {
		/*SimpleDateFormat format = new SimpleDateFormat("dd-MM-YYYY hh:mm:ss");
		String dateString = format.format(new Date());
		Date date = null;
		try {
			date = format.parse (dateString);
		} catch (ParseException e) {
			logger.error("Error while parcing date in getCurrentDateTime()  " +  e); 
			e.printStackTrace();
		}*/
		return new Date();
	}
	
	public static Date getCurrentDate() {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-YYYY");
		String dateString = format.format(new Date());
		Date date = null;
		try {
			date = format.parse (dateString);
		} catch (ParseException e) {
			logger.error("Error while parcing date in getCurrentDate()  " +  e); 
			e.printStackTrace();
		}
		return date;
	}
	
	public static Date getCurrentTime() {
		SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
		String dateString = format.format(new Date());
		Date date = null;
		try {
			date = format.parse (dateString);
		} catch (ParseException e) {
			logger.error("Error while parcing date in getCurrentTime()  " +  e); 
			e.printStackTrace();
		}
		return date;
	}
}
