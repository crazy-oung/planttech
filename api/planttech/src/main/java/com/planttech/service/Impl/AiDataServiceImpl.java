package com.planttech.service.Impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.planttech.domain.ai.PlantSensorAverage;
import com.planttech.domain.factory.PlantSensor;
import com.planttech.domain.search.Page;
import com.planttech.mapper.PlantSensorAverageMapper;
import com.planttech.mapper.PlantSensorMapper;
import com.planttech.service.AiDataService;


@Service
@Transactional
public class AiDataServiceImpl implements AiDataService {
	@Autowired private PlantSensorAverageMapper plantSensorAverageMapper;


	@Override
	public List<PlantSensorAverage> getPlantSensorAverageList() {
		System.out.println("::: PlantSensorServiceImpl - getPlantSensorList :::");
		try {
			return plantSensorAverageMapper.selectPlantSensorAverageList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public int addPlantSensorAverage(PlantSensorAverage plantSensorAverage) {
		
		try {
			System.out.println(plantSensorAverage.toString());
			return plantSensorAverageMapper.insertPlantSensorAverage(plantSensorAverage);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}

	

	

}
