package com.planttech.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Page {
	
	@Schema(description = "페이지 번호", example = "0", deprecated = false)
	private int beginPage;
	@Schema(description = "페이지 당 페이지수", example = "10", deprecated = false)
	private int pageSize;
	@Schema(description = "검색 필터", example = "최신순", deprecated = false)
	private String tab;
	@Schema(description = "검색 키워드", example = "키워드", deprecated = false)
	private String searchKeyword;

}