package com.planttech.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.planttech.domain.factory.PlantSensor;
import com.planttech.domain.factory.SensorControlTf;
import com.planttech.domain.search.Page;


@Mapper
public interface PlantSensorMapper {
	
	public List<PlantSensor> 	selectPlantSensorList(Page page);
	public int 					insertPlantSensor(PlantSensor plantSensor);
	
	public SensorControlTf 	selectSensorControl(int userNo);
	public SensorControlTf	insertPlantSensorCtrl(SensorControlTf sensorControlTf);
	public int 				updatePlantSensorWaterPump(int userNo, int waterPumpTf);
	public int 				updatePlantSensorHumidifier(int userNo, int humidifierTf);

}
