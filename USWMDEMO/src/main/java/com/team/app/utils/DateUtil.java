package com.team.app.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.log4j.Logger;





public class DateUtil {
		final static Logger logger = Logger.getLogger(DateUtil.class);

	
		
		public static Date convertStringToDate(String  strTime, String srcFormat, String destFormat) {
			Date date= null;
		    try {
				DateFormat srcFormatter = new SimpleDateFormat(srcFormat);
				date = srcFormatter.parse(strTime);
				DateFormat destFormatter = new SimpleDateFormat(destFormat);
				System.out.println("str date in dest format : "+destFormatter.format(date));
				date = destFormatter.parse(destFormatter.format(date));
			} catch (Exception e) {
				logger.error("error in Timestamp",e);
			}
		    System.out.println("Dateeeeeeeee"+date);
		    return date;
		  }
		
		public static Date getCurrentDateTime(String format) {
			Date date= null;
		    try {
		    	Calendar calendar = Calendar.getInstance();
		    	long currentDateTime = calendar.getTimeInMillis();
				DateFormat formatter = new SimpleDateFormat(format);
				formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
			//	String dateStr = formatter.format(currentDateTime);
			//	System.out.println("dateStr = "+dateStr);
				date = formatter.parse(formatter.format(currentDateTime));
			} catch (Exception e) {
				logger.error("error in  convertStringToTimestamp",e);
			}
		    return date;
		  }
		
		public static Date getCurrentDateTimeIST(String format) {
			Date date= null;
		    try {
		    	Calendar calendar = Calendar.getInstance();
		    	long currentDateTime = calendar.getTimeInMillis();
				DateFormat formatter = new SimpleDateFormat(format);
				formatter.setTimeZone(TimeZone.getTimeZone("IST"));
			//	String dateStr = formatter.format(currentDateTime);
			//	System.out.println("dateStr = "+dateStr);
				date = formatter.parse(formatter.format(currentDateTime));
			} catch (Exception e) {
				logger.error("error in  convertStringToTimestamp",e);
			}
		    return date;
		  }
		
		public static Date getCurrentDateTimeIST() {
			Date date= null;
		    try {
		    	TimeZone.setDefault(TimeZone.getTimeZone("IST"));
		  	  	DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  	  	formatter.setTimeZone(TimeZone.getTimeZone("IST")); // Or whatever IST is supposed to be
		    	date=formatter.parse(formatter.format(new Date(System.currentTimeMillis())));
		    	
			} catch (Exception e) {
				logger.error("error in  convertStringToTimestamp",e);
			}
		    return date;
		  }
		
		public static String changeDateFromat(Date sqlFormat) {
			// System.out.println("Got Here");
			try {
				String naturalFormat = "";
				DateFormat formatter;
				formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				naturalFormat = formatter.format(sqlFormat);

				// System.out.println("Date" + naturalFormat);
				return naturalFormat;
			} catch (Exception e) {
				// System.out.println("Error" + e);
				return sqlFormat.toString();
			}
		}	
		
		public static String changeDateFromatIST(Date sqlFormat) {
			// System.out.println("Got Here");
			try {
				String naturalFormat = "";
				DateFormat formatter;
				formatter = new SimpleDateFormat("yyyy-MM-dd");
				naturalFormat = formatter.format(sqlFormat);

				// System.out.println("Date" + naturalFormat);
				return naturalFormat;
			} catch (Exception e) {
				// System.out.println("Error" + e);
				return sqlFormat.toString();
			}
		}	
		
		public static Date convertLongToDate(long longTime, String format) {
			Date date = null;
			try {
				DateFormat formatter = new SimpleDateFormat(format);
				formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(longTime);
				System.out.println(formatter.format(calendar.getTime()));
				date = formatter.parse(formatter.format(calendar.getTime()));
			} catch (Exception e) {

				logger.error("error in Timestamp", e);
			}
			return date;
		}
		
		public static Date convertLongToDateIST(long longTime, String format) {
			Date date = null;
			try {
				DateFormat formatter = new SimpleDateFormat(format);
				//formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
				formatter.setTimeZone(TimeZone.getTimeZone("IST"));
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(longTime);
				System.out.println(formatter.format(calendar.getTime()));
				date = formatter.parse(formatter.format(calendar.getTime()));
			} catch (Exception e) {

				logger.error("error in Timestamp", e);
			}
			return date;
		}
		
		
		public static Date convertLongToDateNoZone(long longTime, String format) {
			Date date = null;
			try {
				DateFormat formatter = new SimpleDateFormat(format);
				//formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
				//formatter.setTimeZone(TimeZone.getTimeZone("IST"));
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(longTime);
				System.out.println(formatter.format(calendar.getTime()));
				date = formatter.parse(formatter.format(calendar.getTime()));
			} catch (Exception e) {

				logger.error("error in Timestamp", e);
			}
			return date;
		}

		
		
}