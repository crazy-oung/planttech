package com.planttech.domain.shop;

import java.sql.Timestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Product{
	
	@Schema(description = "입찰 고유번호", example = "0", deprecated = false)
	private int productNo;
	@Schema(description = "유저 고유번호", example = "0", deprecated = false)
	private int userNo;
	@Schema(description = "식물 고유번호", example = "221", deprecated = false)
	private int plantNo;
	
	@Schema(description = "입찰 상품 명", example = "스킨답서스", deprecated = false)
	private String productName;
	@Schema(description = "입찰 타입", example = "입찰 유형(0: 판매, 1: 입찰, 2:즉시구매)", deprecated = false)
	private String productType;
	@Schema(description = "입찰 내용", example = "", deprecated = false)
	private String productContent;
	
	@Schema(description = "식물 입찰가", example = "1350", deprecated = false)
	private int productPrice;
	
	@Schema(description = "입찰 생성 일자", deprecated = false)
	private Timestamp productCreatetime;
	@Schema(description = "입찰 수정 일자", deprecated = false)
	private Timestamp productModifytime;
	
	@Schema(description = "입찰 활성화 상태", example = "입찰 유효 여부 (0: 삭제됨, 1: 입찰중, 2: 입찰완료)", deprecated = false)
	private int productActive;
	
}
