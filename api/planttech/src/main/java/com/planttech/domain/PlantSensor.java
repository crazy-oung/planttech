package com.planttech.domain;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class PlantSensor {

	private int plantSensorNo;
	private int plantNo;
	private int warehousePlantNo;
	private int dhtNo;
	private int photoRegistorNo;
	private int waterTempNo;
	
	private float humi;
	private float temp;
	private float waterTemp;
	private float light;

	private Timestamp plantSensorCreatetime;

}