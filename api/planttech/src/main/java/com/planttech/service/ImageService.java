package com.planttech.service;

import java.util.List;
import java.util.Map;

import com.planttech.domain.plant.PlantImage;

public interface ImageService {
	
	// 식물 이미지
	public List<PlantImage> 	getPlantImageList(Map<String, Object> map);
	public PlantImage  			addPlantImage(PlantImage plantImage);
	
}
