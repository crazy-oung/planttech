package com.planttech.service;

import java.util.List;


import com.planttech.domain.PlantSensor;
import com.planttech.domain.PlantSensorAverage;
import com.planttech.domain.SensorControlTf;
import com.planttech.domain.Page;

public interface AiDataService {

	public List<PlantSensorAverage> getPlantSensorAverageList();
	
	public int addPlantSensorAverage(PlantSensorAverage plantSensorAverage);
	

}
