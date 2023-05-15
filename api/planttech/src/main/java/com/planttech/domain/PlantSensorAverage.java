package com.planttech.domain;

import java.sql.Timestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PlantSensorAverage {
	
	@Schema(description = "식물 환경 정보 평균 데아터 고유번호", example = "0", deprecated = false)
	private int plantSensorAverageNo;
	@Schema(description = "식물 고유번호", example = "0", deprecated = false)
	private int plantNo;
	@Schema(description = "식물 창고위치 고유번호", example = "0", deprecated = false)
	private int warehousePlantNo;
	@Schema(description = "온습도 센서 고유번호", example = "0", deprecated = false)
	private int dhtNo;
	@Schema(description = "조도 센서 고유번호", example = "0", deprecated = false)
	private int photoRegistorNo;
	@Schema(description = "물온도 센서 고유번호", example = "0", deprecated = false)
	private int waterTempNo;
	
	@Schema(description = "습도 평균", example = "30.4", deprecated = false)
	private float humiAverage;
	@Schema(description = "온도 평균", example = "26.4", deprecated = false)
	private float tempAverage;
	@Schema(description = "물온도 평균", example = "24.3", deprecated = false)
	private float waterTempAverage;
	@Schema(description = "채광 평균", example = "1", deprecated = false)
	private float lightAverage;
	
	@Schema(description = "센서 평균 일차", example = "1", deprecated = false)
	private int avergaeDay;

	@Schema(description = "센서 값 평균 저장 날짜", example = "0", deprecated = true)
	private Timestamp plantSensorAverageCreatetime;

}