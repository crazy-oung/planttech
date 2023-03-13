package com.planttech.domain;

import lombok.Data;

@Data
public class Page {
	public int beginPage =0;
	public int pageSize =10;
	public String userId;
	
	// 검색 탭 필터
	public String tab;
	// 검색 키워드 필터
	public String searchKeyword;

}