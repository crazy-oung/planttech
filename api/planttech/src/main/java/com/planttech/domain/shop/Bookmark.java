package com.planttech.domain.shop;

import java.security.Timestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Bookmark {
	
	@Schema(description = "북마크 고유번호", example = "0", deprecated = false)
	private int bookrmarkNo;
	@Schema(description = "유저 고유번호", example = "0", deprecated = false)
	private int userNo;
	@Schema(description = "식물 고유번호", example = "0", deprecated = false)
	private int plantNo;
	@Schema(description = "북마크 타입(0: 입찰 항목에 대한 북마크, 1: 항목의 등급에 대한 북마크)", example = "0", deprecated = false)
	private int bookmarkType;
	
	@Schema(description = "북마크 생성 날짜", example = "0", deprecated = false)
	private Timestamp bookmarkCreatetime;
	
	
}
