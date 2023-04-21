package com.planttech.domain;

import lombok.Data;

@Data
public class Page {
	
	private int beginPage 	= 0;
	private int pageSize 	= 10;
	private String userId;
	private int userNo;
	
	// 검색 탭 필터
	private String tab;
	// 검색 키워드 필터
	private String searchKeyword;

}