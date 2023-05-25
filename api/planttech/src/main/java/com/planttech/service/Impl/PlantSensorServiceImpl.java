package com.planttech.service.Impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.planttech.domain.factory.PlantSensor;
import com.planttech.domain.factory.SensorControlTf;
import com.planttech.domain.search.Page;
import com.planttech.domain.user.User;
import com.planttech.mapper.PlantSensorMapper;
import com.planttech.service.PlantSensorService;


@Service
@Transactional
public class PlantSensorServiceImpl implements PlantSensorService {
	@Autowired private PlantSensorMapper plantSensorMapper;


	@Override
	public List<PlantSensor> getAllPlantSensorList() {
		return plantSensorMapper.selectAllPlantSensorList();
	}
	
	@Override
	public List<PlantSensor> getPlantSensorList(int warehousePlantNo, int searchDate, User user) {
		
		Map map = new HashMap<>() {{
			put("warehousePlantNo", warehousePlantNo);
			put("searchDate", searchDate-1);
			put("userNo", user.getUserNo());
		}};
		
		return plantSensorMapper.selectPlantSensorList(map);
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
