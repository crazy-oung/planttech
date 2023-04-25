package com.planttech.domain;


import java.sql.Timestamp;

import lombok.Data;

@Data
public class UserPlant extends Plant{

	private int userPlantNo;
	private int userNo;
	private int warehousePlantNo;
	
	private String userPlantName;

	private Timestamp userPlantCreatetime;
	private Timestamp userPlantModifytime;
	
	
}