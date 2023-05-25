package com.planttech.domain.search;

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
	@Schema(description = "화분 고유번호", example = "0", deprecated = false)
	private Object plantWarehouseNo;
	@Schema(description = "식물 고유번호", example = "0", deprecated = false)
	private Object plantNo;
	
	@Schema(description = "검색 키워드", example = "키워드", deprecated = false)
	private String searchKeyword;

	
	@Schema(description = "유저 세션(기입 받지 앖습니다.)", example = "", deprecated = true)
	private Object userNo;
}