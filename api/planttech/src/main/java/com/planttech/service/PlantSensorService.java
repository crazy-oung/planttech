package com.planttech.service;

import java.util.List;


import com.planttech.domain.PlantSensor;
import com.planttech.domain.SensorControlTf;
import com.planttech.domain.Page;

public interface PlantSensorService {

	public List<PlantSensor> 	getPlantSensorList(Page page);
	public int 					addPlantSensor(PlantSensor PlantSensor);
	
	public SensorControlTf 	getUserSensorControl(int userNo);
	public SensorControlTf 	addUserSensorControl(SensorControlTf sensorControlTf);
	public int 				addPlantSensorWataerPump(int userNo, int waterPumpTf);
	public int 				addPlantSensorHumidifier(int userNo, int humidifierTf);

}
