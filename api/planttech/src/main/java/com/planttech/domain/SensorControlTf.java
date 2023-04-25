package com.planttech.domain;

import java.sql.Timestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SensorControlTf {
	
	@Schema(description = "센서 제어 정보 고유번호", example = "0", deprecated = false)
	private int sensorControlNo;
	@Schema(description = "유저 고유번호", example = "0", deprecated = false)
	private int userNo;
	
	@Schema(description = "물펌프 센서 고유번호", example = "0", deprecated = false)
	private int waterpumpNo;
	@Schema(description = "가습기 고유번호", example = "0", deprecated = false)
	private int humidifierNo;
	@Schema(description = "식물 창고위치 고유번호", example = "0", deprecated = false)
	private int warehousePlantNo;
	
	@Schema(description = "물펌프 제어 상태", example = "0", deprecated = false)
	private int waterPumpTf;
	@Schema(description = "가습기 제어 상태", example = "0", deprecated = false)
	private int humidifierTf;
	
	@Schema(description = "센서 제어 정보 생성 일시", deprecated = false)
	private Timestamp sensorCreatetime;
	@Schema(description = "센서 제어 정보 수정 일시", deprecated = false)
	private Timestamp sensorModifytime;

}