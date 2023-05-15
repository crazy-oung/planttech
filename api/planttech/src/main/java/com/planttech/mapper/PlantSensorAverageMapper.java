package com.planttech.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.planttech.domain.PlantSensor;
import com.planttech.domain.PlantSensorAverage;
import com.planttech.domain.SensorControlTf;
import com.planttech.domain.Page;


@Mapper
public interface PlantSensorAverageMapper {
	public List<PlantSensorAverage> selectPlantSensorAverageList();
	public int insertPlantSensorAverage(PlantSensorAverage plantSensorAverage);

}
