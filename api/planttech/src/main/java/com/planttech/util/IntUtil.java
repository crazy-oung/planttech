package com.planttech.util;

import java.util.Map;

public class IntUtil {
	public static int 	  getInt	 (Map<String,Object> input, String key) { return Integer.parseInt((input.get(key).toString())); }
	public static int 	  getInt	 (String key) { return Integer.parseInt(key); }
	
	public static int  	  null2int   (String s)	{ return StringUtil.isBlank(s)? 0 : Integer.parseInt(s); }
	public static int  	  null2int   (Map<String,Object> input, String key)	{ return StringUtil.isBlank(input,key)? 0 : Integer.parseInt(String.valueOf(input.get(key))); }
	public static int  	  null2int   (Object o)	{ return o == null? 0 : Integer.parseInt(o.toString()); }
}
