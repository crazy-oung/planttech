package com.planttech.service;

import java.util.List;

import com.planttech.domain.ai.PlantColor;
import com.planttech.domain.ai.PlantSensorAverage;
import com.planttech.domain.search.Page;
import com.planttech.domain.shop.Bookmark;
import com.planttech.domain.shop.Product;
import com.planttech.domain.user.User;

public interface BookmarkService {
	
	public List<Bookmark> 	getProductBookmarkList(User user);
	public int 				addProductBookmark(Bookmark bookmark);
	public int 				removeProductBookmark(Bookmark bookmark);

}
