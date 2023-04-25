
package com.planttech.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.planttech.domain.User;

import jakarta.servlet.http.HttpServletRequest;

// import javax.servlet.http.HttpServletRequest;

// import egovframework.com.cmm.LoginVO;


public class UserUtil {
	
	public static String getUniqueId() { return (new SimpleDateFormat("yyyyMMddHHmmssSSS")).format(Calendar.getInstance().getTime()); }
	
	
	 public static boolean isUser(HttpServletRequest request, String sessionName) { return request.getSession().getAttribute(sessionName) == null ? false : true; }
	 public static User getUser(HttpServletRequest request, String sessionName) { return (User)request.getSession().getAttribute(sessionName); }
}