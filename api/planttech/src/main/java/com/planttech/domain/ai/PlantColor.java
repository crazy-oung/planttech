package com.planttech.domain.ai;

import java.sql.Timestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PlantColor {
	
	// 식물번호, 색채분석결과(문자열) 이용
	@Schema(description = "식물 색채 분석 결과 고유번호", example = "0", deprecated = true)
	private int plantColorNo; 
	@Schema(description = "식물 화분 고유번호", example = "0", deprecated = false)
	private int plantWarehouseNo;
	@Schema(description = "식물 색채 분석 결과 문자열", example = "Soso", deprecated = false)
	private String plantColorGrade;
	@Schema(description = "식물 색채 분석 결과 사진 BASE 64 코드", example = "", deprecated = false)
	private String plantColorPic;
	
	@Schema(deprecated = true)
	private String plantColorPic1;
	@Schema(deprecated = true)
	private String plantColorPic2;
	@Schema(deprecated = true)
	private String plantColorPic3;
	
	@Schema(description = "식물 데이터 생성일시", deprecated = true)
	private Timestamp plantColorCreatetime;
}
