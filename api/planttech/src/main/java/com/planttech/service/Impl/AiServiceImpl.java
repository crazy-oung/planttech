package com.planttech.service.Impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.planttech.domain.ai.PlantColor;
import com.planttech.domain.ai.PlantSensorAverage;
import com.planttech.domain.search.Page;
import com.planttech.mapper.PlantSensorAverageMapper;
import com.planttech.mapper.ProductMapper;
import com.planttech.mapper.PlantColorMapper;
import com.planttech.service.AiService;


@Service
@Transactional
public class AiServiceImpl implements AiService {
	
	@Autowired private PlantSensorAverageMapper plantSensorAverageMapper;
	@Autowired private PlantColorMapper 		plantColorMapper;
	@Autowired private ProductMapper 			productMapper;

	// ==== 센서 평균 ================================================================================
	@Override
	public List<PlantSensorAverage> getPlantSensorAverageList(Page page) {
		try {
			return plantSensorAverageMapper.selectPlantSensorAverageList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
//	public Map<String, Object> addPlantSensorAverage(PlantSensorAverage plantSensorAverage) {
	public int addPlantSensorAverage(PlantSensorAverage plantSensorAverage) {
		
		try {
			System.out.println(plantSensorAverage.toString());
			return plantSensorAverageMapper.insertPlantSensorAverage(plantSensorAverage);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	
	// 입찰 평균가 
	@Override
	public List<PlantSensorAverage> getPlantBidAverageList(Page page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addPlantBidAverage(PlantSensorAverage plantSensorAverage) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	// ==== 색채 분석 ================================================================================
	@Override
	public List<PlantColor> getPlantColorAnalysisList(Page page) {
		return plantColorMapper.selectPlantColorList(page);
	}

	@Override
	public int addPlantColorAnalysis(PlantColor plantColor) {
		return plantColorMapper.insertPlantColor(plantColor);
	}


}
