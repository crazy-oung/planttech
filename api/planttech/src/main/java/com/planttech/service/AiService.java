package com.planttech.service;

import java.util.List;

import com.planttech.domain.ai.PlantColor;
import com.planttech.domain.ai.PlantSensorAverage;
import com.planttech.domain.search.Page;
import com.planttech.domain.shop.Product;

public interface AiService {
	
	// 머신러닝
	public List<PlantSensorAverage> 	getPlantSensorAverageList(Page page);
	public int 							addPlantSensorAverage(PlantSensorAverage plantSensorAverage);
	
	
	// 수요 공급 분석 
	public List<Product> 		getAllBidList(Page page);
	public int 					addProduct(Product product);
	
	// 색채분석
	public List<PlantColor> 	getPlantColorAnalysisList(Page page);
	public int 					addPlantColorAnalysis(PlantColor plantColor);
	
}
