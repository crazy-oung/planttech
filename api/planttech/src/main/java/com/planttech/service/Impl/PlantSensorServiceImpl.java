package com.planttech.service.Impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.planttech.domain.PlantSensor;
import com.planttech.domain.SensorControlTf;
import com.planttech.domain.Page;
import com.planttech.mapper.PlantSensorMapper;
import com.planttech.service.PlantSensorService;


@Service
@Transactional
public class PlantSensorServiceImpl implements PlantSensorService {
	@Autowired private PlantSensorMapper plantSensorMapper;


	@Override
	public List<PlantSensor> getPlantSensorList(Page page) {
		return plantSensorMapper.selectPlantSensorList(page);
	}

	@Override
	public int addPlantSensor(PlantSensor plantSensor) {
		try {
			return plantSensorMapper.insertPlantSensor(plantSensor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	
	
	@Override
	public SensorControlTf getUserSensorControl(int userNo) {
		try {
			return plantSensorMapper.selectSensorControl(userNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public SensorControlTf addUserSensorControl(SensorControlTf sensorControlTf) {
		return plantSensorMapper.insertPlantSensorCtrl(sensorControlTf);
	}
	
	@Override
	public int addPlantSensorWataerPump(int userNo, int waterPumpTf) {
		try {
			return plantSensorMapper.updatePlantSensorWaterPump(userNo, waterPumpTf);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int addPlantSensorHumidifier(int userNo, int humidifierTf) {
		try {
			return plantSensorMapper.updatePlantSensorHumidifier(userNo, humidifierTf);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}


}