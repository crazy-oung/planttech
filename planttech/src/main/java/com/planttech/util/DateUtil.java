package com.planttech.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class DateUtil {
	
	public static String timestamp2date(String timeStamp){
	    long 				timestamp 	= Long.parseLong(timeStamp);
	    Date 				date 		= new java.util.Date(timestamp); 
	    
	    SimpleDateFormat 	sdf 		= new java.text.SimpleDateFormat("yyyy-MM-dd"); 
	    sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+9")); 
	    return sdf.format(date);
	}
	
	public static String timestamp2datetime(String timeStamp){
		long 				timestamp 	= Long.parseLong(timeStamp);
		Date 				date 		= new java.util.Date(timestamp); 
		SimpleDateFormat 	sdf 		= new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+9")); 
		return sdf.format(date);
	}
	
	public static String getTodaysDate() {
		SimpleDateFormat 	sdf 		= new java.text.SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}

}
