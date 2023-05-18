package com.planttech.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.planttech.domain.plant.Plant;
import com.planttech.domain.plant.PlantCategory;
import com.planttech.domain.search.Page;
import com.planttech.mapper.PlantMapper;
import com.planttech.service.PlantService;


@Service
@Transactional
public class PlantServiceImpl implements PlantService {
	
	
	@Autowired 
	private PlantMapper plantMapper;
	
	// 식물 모두 조회
	@Override
	public List<Plant> getPlantList(Page page) {
		System.out.println("::: - getPlantList :::");
		
		return plantMapper.selectPlantList(page);
	}
	
	// 식물 카테고리 조회 
	@Override
	public List<PlantCategory> getPlantCategoryList() {
		System.out.println("::: - getPlantCategoryList :::");
		
		return plantMapper.selectPlantCategoryList();
	}
	

}
