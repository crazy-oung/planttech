package com.planttech.domain;

import java.sql.Timestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Article{
	
	@Schema(description = "거래글 고유번호", example = "0", deprecated = false)
	private int articleNo;
	@Schema(description = "유저 고유번호", example = "0", deprecated = false)
	private int userNo;
	@Schema(description = "식물 고유번호", example = "0", deprecated = false)
	private int plantNo;
	
	@Schema(description = "거래글 제목", example = "제가 키운 식물 팝니다.", deprecated = false)
	private String articleTitle;
	@Schema(description = "거래글 부제목/카테고리", example = "판매", deprecated = false)
	private String articleSubject;
	@Schema(description = "거래글 내용", example = "2023년 4월 20알 부터 키운 식물입니다. 현재 적정가에 올려놓아요. 많이 찔러 봐 주세요.", deprecated = false)
	private String articleContent;
	
	@Schema(description = "거래글 재배식물 가격", example = "1350", deprecated = false)
	private int articleProductPrice;
	
	@Schema(description = "거래글 생성 일자", deprecated = true)
	private Timestamp articleCreatetime;
	@Schema(description = "거래글 수정 일자", deprecated = true)
	private Timestamp articleModifytime;
	
}
