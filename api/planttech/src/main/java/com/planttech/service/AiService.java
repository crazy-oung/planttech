package com.planttech.service;

import java.util.List;

import com.planttech.domain.ai.PlantColor;
import com.planttech.domain.ai.PlantSensorAverage;
import com.planttech.domain.search.Page;

public interface AiService {
	
	// 머신러닝
	public List<PlantSensorAverage> 	getPlantSensorAverageList(Page page);
	public int 							addPlantSensorAverage(PlantSensorAverage plantSensorAverage);
	
	
	public List<PlantSensorAverage> 	getPlantBidAverageList(Page page);
	public int 							addPlantBidAverage(PlantSensorAverage plantSensorAverage);
	
	
	// 색채분석
	public List<PlantColor> 	getPlantColorAnalysisList(Page page);
	public int 					addPlantColorAnalysis(PlantColor plantColor);
	
}
