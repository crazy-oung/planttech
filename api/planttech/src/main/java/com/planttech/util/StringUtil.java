package com.planttech.util;

import java.util.Map;
import java.util.regex.Pattern;

public class StringUtil {
	public static boolean isNull	(String s) 								{ return (s == null); }
	public static boolean isNull	(Map<String,Object> input, String key)	{ if (input == null || input.get(key) == null) { return true; } String s = input.get(key).toString(); return (s == null); }
	
	public static boolean isBlank	(String s) 								{ return (s == null || s.trim().length() == 0); }
	public static boolean isBlank	(Map<String,Object> input, String key)	{ if (input == null || input.get(key) == null) { return true; } String s = input.get(key).toString(); return (s == null || s.trim().length() == 0); }
	
	public static boolean isInt	(String s)									{ return s.matches("\\d+"); }
	
	public static String  null2string(String s)								{ return isBlank(s)? ""  : s; } 
	public static String  null2string(Object o)								{ return o == null ? ""  : o.toString(); }
	
	public static String int2String(int i)									{ return i+"";}
	
	public static boolean isEmail(String s)									{ return Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", s);	}
}
