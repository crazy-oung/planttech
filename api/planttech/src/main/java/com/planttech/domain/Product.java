package com.planttech.domain;

import java.sql.Timestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Product{
	
	@Schema(description = "거래 고유번호", example = "0", deprecated = false)
	private int productNo;
	@Schema(description = "유저 고유번호", example = "0", deprecated = false)
	private int userNo;
	@Schema(description = "식물 고유번호", example = "0", deprecated = false)
	private int plantNo;
	
	@Schema(description = "거래 상품 명", example = "당근 A등급", deprecated = false)
	private String productName;
	@Schema(description = "거래 카테고리", example = "판매", deprecated = false)
	private String productCategory;
	@Schema(description = "거래 내용", example = "2023년 4월 20알 부터 키운 식물입니다. 현재 적정가에 올려놓아요. 설정가 보다 더 낮은 가격으로 입찰가 제시 주셔도 판매 해드릴 의향 있습니다.", deprecated = false)
	private String productContent;
	
	@Schema(description = "거래 식물 가격", example = "1350", deprecated = false)
	private int productPrice;
	
	@Schema(description = "거래 생성 일자", deprecated = false)
	private Timestamp productCreatetime;
	@Schema(description = "거래 수정 일자", deprecated = false)
	private Timestamp productModifytime;
	
	@Schema(description = "거래 상태", deprecated = false)
	private int productActive;
	
}
