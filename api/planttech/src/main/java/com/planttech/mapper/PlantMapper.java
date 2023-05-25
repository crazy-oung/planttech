package com.planttech.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.planttech.domain.plant.Plant;
import com.planttech.domain.plant.PlantCategory;
import com.planttech.domain.search.Page;


@Mapper
public interface PlantMapper {
	
	public List<Plant> selectPlantList(Page page);
	public List<PlantCategory> selectPlantCategoryList();
	
}
