package com.planttech.service;

import java.util.List;

import com.planttech.domain.ai.PlantSensorAverage;
import com.planttech.domain.factory.PlantSensor;
import com.planttech.domain.factory.SensorControlTf;
import com.planttech.domain.search.Page;

public interface AiDataService {

	public List<PlantSensorAverage> getPlantSensorAverageList();
	
	public int addPlantSensorAverage(PlantSensorAverage plantSensorAverage);
	

}
