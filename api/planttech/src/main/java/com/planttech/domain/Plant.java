package com.planttech.domain;


import java.sql.Timestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Plant {
	
	@Schema(description = "식물 종 고유번호", example = "0", deprecated = false)
	private int plantNo;
	@Schema(description = "식물 한글명", example = "스킨답서스", deprecated = false)
	private String plantKoreanName;
	@Schema(description = "식물 유통명", example = "스킨다비스, 신답서스", deprecated = false)
	private String plantDistributionName;
	@Schema(description = "식물 학명", example = "Epipremnum aureum", deprecated = false)
	private String plantScientificName;
	@Schema(description = "식물 영명", example = "scindapsus, devils ivy, goden pothos, hunter’s robe", deprecated = false)
	private String plantEnglishName;
	@Schema(description = "식물 과명", example = "천남성과", deprecated = false)
	private String plantCategory;
	@Schema(description = "식물 원산지", example = "솔로몬군도, 인도네시아(자바 섬)", deprecated = false)
	private String plantOrigin;
	@Schema(description = "식물 기르는 팁", example = "행잉이용", deprecated = false)
	private String plantCultivateTip;
	
	
	
	@Schema(description = "식물 등록 날짜", deprecated = true)
	private Timestamp plantCreatetime;
	@Schema(description = "식물 수정 날짜", deprecated = true)
	private Timestamp plantModifytime;

}