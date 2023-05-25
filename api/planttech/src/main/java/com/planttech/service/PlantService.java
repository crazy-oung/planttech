package com.planttech.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.planttech.domain.plant.Plant;
import com.planttech.domain.plant.PlantCategory;
import com.planttech.domain.search.Page;

public interface PlantService {
	
	// 식물 리스트 가져오기
	public List<Plant> getPlantList(Page page);
	// 식물 카테고리 조회
	public List<PlantCategory> getPlantCategoryList();


}
