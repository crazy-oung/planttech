package com.planttech.service;

import java.util.List;

import com.planttech.domain.factory.PlantSensor;
import com.planttech.domain.factory.SensorControlTf;
import com.planttech.domain.search.Page;
import com.planttech.domain.user.User;

public interface PlantSensorService {

	public List<PlantSensor> 	getAllPlantSensorList();
	public List<PlantSensor> 	getPlantSensorList(int warehousePlantNo, int plantNo, User user);
	public int 					addPlantSensor(PlantSensor PlantSensor);
	
	public SensorControlTf 	getUserSensorControl(int userNo);
	public SensorControlTf 	addUserSensorControl(SensorControlTf sensorControlTf);
	public int 				addPlantSensorWataerPump(int userNo, int waterPumpTf);
	public int 				addPlantSensorHumidifier(int userNo, int humidifierTf);

}
