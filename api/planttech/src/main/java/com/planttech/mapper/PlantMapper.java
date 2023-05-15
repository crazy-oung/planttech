package com.planttech.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.planttech.domain.Plant;


@Mapper
public interface PlantMapper {
	
	public List<Plant> selectPlantList();
	
}
