package com.planttech.domain;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class Dht11 {

	private int seq;
	private Timestamp datetime;
	private int temperature;
	private int humidity;
	private double hi;
	
	
}