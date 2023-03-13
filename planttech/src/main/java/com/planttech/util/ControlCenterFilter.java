//package com.planttech.util;
//
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
// 
//public class ControlCenterFilter implements Filter{
//	
//	String currDtm;
//	
//    public ControlCenterFilter() {
//		super();
//		this.currDtm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//	}
//
//	@Override 
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException { // 모든 요청(Request)에 대해서 이 부분이 실행된다.
//        HttpServletRequest request = (HttpServletRequest)req;
//        
//        System.out.println("====[ REQUEST INFO ]===================================================================================");
//        System.out.println( currDtm + " execute Request URI => ["   + request.getRequestURI()   + "]");
//        System.out.println( currDtm + " Request URL => [" 		    + request.getRequestURL() 	+ "]");
//        System.out.println( currDtm + " Request Path => [" 	        + request.getContextPath() 	+ "]");
//        System.out.println( currDtm + " Request Protocol => ["      + request.getProtocol() 	+ "]");
//        System.out.println( currDtm + " Request Method => [" 	    + request.getMethod() 		+ "]");
//        System.out.println( currDtm + " Servlet Path => [" 	        + request.getServletPath() 	+ "]");
//        currDtm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//        System.out.println("[EXECUTE SERVICE]::: "+ currDtm );
//        chain.doFilter(req, res);
//        currDtm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//        System.out.println( "[SERVICE END]::: " + currDtm );
//     
//    }
// 
//    @Override // 빌드후 실행 
//    public void init(FilterConfig config) throws ServletException {
//    	System.out.println("======================================================================================================");
//        System.out.println(currDtm + " INIT WEBAPP ");
//        System.out.println("======================================================================================================");
//    }
//    
// 
//    @Override
//    public void destroy() {
//    	currDtm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//        System.out.println(currDtm + " ===[ !WEBAPP DESTROIED! ]=============================================================================");
//    }
//}