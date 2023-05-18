
package com.planttech.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.planttech.domain.user.User;

import jakarta.servlet.http.HttpSession;


public class UserUtil {
	
	public static String buildUUId() { return (new SimpleDateFormat("yyyyMMddHHmmssSSS")).format(Calendar.getInstance().getTime()); }
	
	public static boolean 	isUser(HttpSession session) 	{ return session.getAttribute("loginUser") == null ? false : true; }
	public static boolean 	isAdmin(HttpSession session) 	{ return session.getAttribute("loginAdmin") == null ? false : true; }
	public static boolean 	isLoginned(HttpSession session) { return (session.getAttribute("loginUser") == null || session.getAttribute("loginAdmin") == null) ? false : true; }
	public static User 		getUser(HttpSession session) 	{ return (User)session.getAttribute("loginUser"); }
	public static User 		getAdmin(HttpSession session) 	{ return (User)session.getAttribute("loginAdmin"); }

	public static User 		setUser(HttpSession session, User user) 	{ session.setAttribute("loginUser", user); return user;}
	public static User 		setAdmin(HttpSession session, User user) 	{ session.setAttribute("loginAdmin", user); return user;}
	
}