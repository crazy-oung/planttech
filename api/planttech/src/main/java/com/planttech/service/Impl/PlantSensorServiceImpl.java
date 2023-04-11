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
	@Autowired private PlantSensorMapper PlantSensorMapper;


	@Override
	public List<PlantSensor> getPlantSensorList(Page page) {
		System.out.println("::: PlantSensorServiceImpl - getPlantSensorList :::");
		
		return PlantSensorMapper.selectPlantSensorList(page);
	}

	@Override
	public int addPlantSensor(PlantSensor plantSensor) {
		try {
			return PlantSensorMapper.insertPlantSensor(plantSensor);
//			System.out.println(plantSensor.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	
	@Override
	public SensorControlTf getUserSensorControl(int userNo) {
		try {
			return PlantSensorMapper.selectSensorControl(userNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public int addPlantSensorWataerPump(int userNo, int waterPumpTf) {
		try {
			return PlantSensorMapper.updatePlantSensorWaterPump(userNo, waterPumpTf);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int addPlantSensorLED(int userNo, int ledTf) {
		try {
			return PlantSensorMapper.updatePlantSensorLed(userNo, ledTf);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	

	

}
