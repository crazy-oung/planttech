package com.planttech.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.planttech.domain.ai.PlantSensorAverage;
import com.planttech.domain.factory.PlantSensor;
import com.planttech.domain.factory.SensorControlTf;
import com.planttech.domain.search.Page;


@Mapper
public interface PlantSensorAverageMapper {
	
	public List<PlantSensorAverage> selectPlantSensorAverageList(Page page);
	public int 						insertPlantSensorAverage(PlantSensorAverage plantSensorAverage);

}
