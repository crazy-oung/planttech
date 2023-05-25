package com.planttech.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.planttech.domain.ai.PlantColor;
import com.planttech.domain.search.Page;
import com.planttech.domain.shop.Bookmark;
import com.planttech.domain.user.User;


@Mapper
public interface BookmarkMapper {
	
	public List<Bookmark> 	selectPlantColorList(User user);
	public int 				insertPlantColor(Bookmark bookmark);
	public int 				deletePlantColor(Bookmark bookmark);
	
}
