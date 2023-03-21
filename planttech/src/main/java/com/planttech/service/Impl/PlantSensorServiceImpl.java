package com.planttech.service.Impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.planttech.domain.PlantSensor;
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
		int success= 0;
		
		
		try {
			PlantSensorMapper.insertPlantSensor(plantSensor);
			success++;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return success;
	}
	

}
