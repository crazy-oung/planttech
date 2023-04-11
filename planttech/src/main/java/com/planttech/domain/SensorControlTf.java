package com.planttech.domain;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class SensorControlTf {

	private int sensorControlNo;
	private int userNo;
	
	private int waterPumpTf;
	private int ledTf;

	private Timestamp sensorUpdateTimestamp;

}