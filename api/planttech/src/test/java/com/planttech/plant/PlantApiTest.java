package com.planttech.plant;


import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

public class PlantApiTest {
	
    public static void main(String[] args) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://openapi.nature.go.kr/openapi/service/rest/PlantService/naturalizedSearch2"); /*URL*/
        
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=서비스키"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("st","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*1-국명/2-학명/3-국명일치/4-학명일치*/
        urlBuilder.append("&" + URLEncoder.encode("sw","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*검색대상어*/
        urlBuilder.append("&" + URLEncoder.encode("dateGbn","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*빈칸-전체/1-등록일/2-수정일*/
        urlBuilder.append("&" + URLEncoder.encode("dateFrom","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*검색시작일(dateGbn 선택시 필수, YYYYMMDD)*/
        urlBuilder.append("&" + URLEncoder.encode("dateTo","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*검색종료일(dateGbn 선택시 필수, YYYYMMDD)*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
        
        
        URL url 				= new URL(urlBuilder.toString());
        HttpURLConnection conn 	= (HttpURLConnection) url.openConnection();
        
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
	        BufferedReader rd;
	        
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        StringBuilder sb = new StringBuilder();
	        String line;
	        
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	        }
	        
	        rd.close();
	        conn.disconnect();
	        
	        System.out.println(rd);
	        
//	        System.out.println(sb.toString());
	    }


}
