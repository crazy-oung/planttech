package com.planttech.domain.factory;

import java.sql.Timestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PlantSensor {
	
	@Schema(description = "식물 환경 정보 고유번호", example = "0", deprecated = false)
	private int plantSensorNo;
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
	
	@Schema(description = "습도", example = "30.4", deprecated = false)
	private float humi;
	@Schema(description = "온도", example = "26.4", deprecated = false)
	private float temp;
	@Schema(description = "물온도", example = "24.3", deprecated = false)
	private float waterTemp;
	@Schema(description = "채광", example = "1", deprecated = false)
	private float light;

	@Schema(description = "센서 값 저장 날짜", example = "0", deprecated = true)
	private Timestamp plantSensorCreatetime;

}