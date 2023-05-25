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
	@Schema(description = "식물 고유번호", example = "0", deprecated = false)
	private int plantNo;
	@Schema(description = "식물 등급 고유번호(1: Very Bad, 2: Bad, 3: Soso, 4: Good, 5: Very Good)", example = "", deprecated = false)
	private int plantScoreNo;
	@Schema(description = "입찰하는데 쓴 마일리지", example = "0", deprecated = false)
	private int productBidMileage;
	
	@Schema(description = "화분 고유번호", example = "0", deprecated = false)
	private Object warehousePlantNo;
	@Schema(description = "구매하는 유저의 고유번호", example = "0", deprecated = false)
	private Object productBidUser;
	
	
	@Schema(description = "입찰 상품 명 (현재 사용 안함)", example = "스킨답서스", deprecated = false)
	private String productName;
	
	@Schema(description = "입찰 타입 - 거래 유형 (0: 판매, 1: 구매)", example = "0", deprecated = false)
	private int productType;
	@Schema(description = "거래 유형 - 즉시 거래 여부 (0: 입찰상품, 1: 즉시거래상품)", example = "0", deprecated = false)
	private int productInstant;
	@Schema(description = "입찰 상태 - 입찰 완료 여부 (0: 삭제됨, 1: 입찰중, 2: 입찰완료)", example = "1", deprecated = false)
	private int productActive;
	
	@Schema(description = "식물 입찰가", example = "1350", deprecated = false)
	private int productPrice;
	@Schema(description = "입찰 날짜 주증 여부 (0:주말, 1:주중)", example = "0", deprecated = false)
	private int productWeekdayTf;
	@Schema(description = "입찰 유효일시(만료날짜)", example = "2023-06-02", deprecated = false)
	private Timestamp productExpirationTime;
	
	@Schema(description = "입찰 생성 일자", deprecated = false)
	private Timestamp productCreatetime;
	@Schema(description = "입찰 수정 일자", deprecated = false)
	private Timestamp productModifytime;
}
