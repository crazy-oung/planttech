package com.planttech.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.planttech.domain.Plant;


@Mapper
public interface TestMapper {
	// Test Data 로 plant 
	public List<Plant> selectPlant();
	
}
