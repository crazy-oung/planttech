package com.planttech.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.planttech.domain.ai.PlantColor;
import com.planttech.domain.search.Page;


@Mapper
public interface PlantColorMapper {
	
	public List<PlantColor> selectPlantColorList(Page page);
	public int 				insertPlantColor(PlantColor plantColor);

}
