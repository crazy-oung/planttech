package com.planttech.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.planttech.domain.Page;
import com.planttech.domain.Plant;
import com.planttech.mapper.PlantMapper;
import com.planttech.service.PlantService;


@Service
@Transactional
public class PlantServiceImpl implements PlantService {
	
	
	@Autowired 
	private PlantMapper plantMapper;
	
	//Report 카테고리 가져오기
	@Override
	public List<Plant> getPlantList(Page page) {
		System.out.println("::: - getPlantList :::");
		
		return plantMapper.selectPlantList();
	}
	

}
