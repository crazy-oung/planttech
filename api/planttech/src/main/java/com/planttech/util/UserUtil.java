
package com.planttech.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

// import javax.servlet.http.HttpServletRequest;

// import egovframework.com.cmm.LoginVO;


public class TUserUtil {
	
	public static String getUniqueId() { return (new SimpleDateFormat("yyyyMMddHHmmssSSS")).format(Calendar.getInstance().getTime()); }
	
	
	// public static boolean isUser(HttpServletRequest request, String sessionName) { return request.getSession().getAttribute(sessionName) == null ? false : true; }
	// public static LoginVO getUser(HttpServletRequest request, String sessionName) { return (LoginVO)request.getSession().getAttribute(sessionName); }
}