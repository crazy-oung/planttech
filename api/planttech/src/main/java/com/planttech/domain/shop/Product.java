package com.planttech.domain.shop;

import java.sql.Timestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Product{
	
//	plant_num, status_str, price, trade_time, day_type, season
//	plant_num(식물번호 : 정수), status_str(식물 상태 : 문자열), price(가격 : 정수), trade_time(거래일자 : 파이썬 datetime), day_type(주말/주중 : 문자열), season(계절 : 문자열)
	
	@Schema(description = "입찰 고유번호", example = "0", deprecated = false)
	private int productNo;
	@Schema(description = "유저 고유번호", example = "0", deprecated = false)
	private int userNo;
	@Schema(description = "식물 고유번호", example = "221", deprecated = false)
	private int plantNo;
	
	@Schema(description = "입찰 상품 명", example = "스킨답서스", deprecated = false)
	private String productName;
	@Schema(description = "입찰 타입", example = "입찰 유형(0: 판매 입찰, 1: 구매 입찰, 2:즉시구매)", deprecated = true)
	private int productType;
	
	@Schema(description = "식물 입찰가", example = "1350", deprecated = false)
	private int productPrice;
	@Schema(description = "입찰 날자 주증/주말여부(0:주말, 1:주중)", example = "0", deprecated = false)
	private int productWeekdayTF;
	@Schema(description = "입찰 활성화 상태", example = "입찰 완료 여부 (0: 삭제됨, 1: 입찰중, 2: 입찰완료)", deprecated = false)
	private int productActive;
	
	@Schema(description = "입찰 유효일시(만료날짜)", example = "2023-06-02", deprecated = false)
	private Timestamp productexpirationTime;
	@Schema(description = "입찰 생성 일자", deprecated = false)
	private Timestamp productCreatetime;
	@Schema(description = "입찰 수정 일자", deprecated = false)
	private Timestamp productModifytime;
}
