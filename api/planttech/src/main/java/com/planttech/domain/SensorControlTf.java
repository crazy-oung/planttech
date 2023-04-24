package com.planttech.domain;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class SensorControlTf {

	private int sensorControlNo;
	private int userNo;
	
	private int waterpumpNo;
	private int humidifierNo;
	private int warehousePlantNo;
	
	private int waterPumpTf;
	private int humidifierTf;

	private Timestamp sensorCreatetime;
	private Timestamp sensorModifytime;

}