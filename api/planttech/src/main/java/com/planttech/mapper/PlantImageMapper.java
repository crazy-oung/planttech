package com.planttech.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.planttech.domain.plant.PlantImage;

@Mapper
public interface PlantImageMapper {
	
	public List<PlantImage> 	selectPlantImageList(Map<String, Object> map);
	public int		 			insertPlantImage(PlantImage plantImage);

}
