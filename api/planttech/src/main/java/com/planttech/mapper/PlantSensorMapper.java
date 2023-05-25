package com.planttech.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.planttech.domain.factory.PlantSensor;
import com.planttech.domain.factory.SensorControlTf;


@Mapper
public interface PlantSensorMapper {
	
	public List<PlantSensor> 	selectAllPlantSensorList();
	public List<PlantSensor> 	selectPlantSensorList(Map<String, Object> map);
	public int 					insertPlantSensor(PlantSensor plantSensor);
	
	public SensorControlTf 	selectSensorControl(int userNo);
	public SensorControlTf	insertPlantSensorCtrl(SensorControlTf sensorControlTf);
	public int 				updatePlantSensorWaterPump(int userNo, int waterPumpTf);
	public int 				updatePlantSensorHumidifier(int userNo, int humidifierTf);

}
