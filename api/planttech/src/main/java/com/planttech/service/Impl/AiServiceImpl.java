package com.planttech.service.Impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.planttech.domain.ai.PlantColor;
import com.planttech.domain.ai.PlantSensorAverage;
import com.planttech.domain.search.Page;
import com.planttech.domain.shop.Product;
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
		return plantSensorAverageMapper.selectPlantSensorAverageList(page);
	}

	@Override
	public int addPlantSensorAverage(PlantSensorAverage plantSensorAverage) {
		return plantSensorAverageMapper.insertPlantSensorAverage(plantSensorAverage);
	}
	
	
	// ==== 수요 공급 분석 ================================================================================
	@Override
	public List<Product> getAllBidList(Page page) {
		return productMapper.selectAllProductList(page);
	}
	
	@Override
	public int addProduct(Product product) {
		return productMapper.insertBidProduct(product);
	}

	
	// ==== 색채 분석 ================================================================================
	@Override
	public List<PlantColor> getPlantColorAnalysisList(Page page) {
		return plantColorMapper.selectPlantColorList(page);
	}

	@Override
	public int addPlantColorAnalysis(PlantColor plantColor) {
		int len = plantColor.getPlantColorPic().length();
		// 몰라
		plantColor.setPlantColorPic1(plantColor.getPlantColorPic().substring(0,len/3));
		plantColor.setPlantColorPic2(plantColor.getPlantColorPic().substring(len/3 , len/3*2));
		plantColor.setPlantColorPic3(plantColor.getPlantColorPic().substring(len/3*2 , len));
		
		return plantColorMapper.insertPlantColor(plantColor);
	}




}
