package com.planttech.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.planttech.domain.PlantSensor;
import com.planttech.domain.Page;


@Mapper
public interface PlantSensorMapper {
	// Test Data 로 plant 
	public List<PlantSensor> selectPlantSensorList(Page page);
	
	public int insertPlantSensor(PlantSensor plantSensor);
	
}
