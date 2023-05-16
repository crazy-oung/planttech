package com.planttech.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.planttech.domain.Page;
import com.planttech.domain.Plant;

public interface PlantService {
	
	// 식물 리스트 가져오기
	public List<Plant> getPlantList(Page page);
	// 식물 카테고리 조회
	public List<Plant> getPlantCategoryList();


}
