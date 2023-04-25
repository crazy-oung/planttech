package com.planttech.domain;


import java.sql.Timestamp;

import lombok.Data;

@Data
public class Plant {

	private int plantNo;
	
	private String plantName;
	private Timestamp plantCreatetime;

}