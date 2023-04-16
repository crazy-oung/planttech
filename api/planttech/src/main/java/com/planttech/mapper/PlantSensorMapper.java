package com.planttech.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.planttech.domain.PlantSensor;
import com.planttech.domain.SensorControlTf;
import com.planttech.domain.Page;


@Mapper
public interface PlantSensorMapper {
	// Test Data 로 plant 
	public List<PlantSensor> selectPlantSensorList(Page page);
	
	public int insertPlantSensor(PlantSensor plantSensor);
	
	public SensorControlTf selectSensorControl(int userNo);
	public int insertPlantSensorCtrl(int userNo);
	public int updatePlantSensorWaterPump(int userNo, int waterPumpTf);
	public int updatePlantSensorLed(int userNo, int ledTf);

}
