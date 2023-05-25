package com.planttech.service.Impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.planttech.domain.ai.PlantColor;
import com.planttech.domain.ai.PlantSensorAverage;
import com.planttech.domain.search.Page;
import com.planttech.domain.shop.Bookmark;
import com.planttech.domain.shop.Product;
import com.planttech.domain.user.User;
import com.planttech.mapper.PlantSensorAverageMapper;
import com.planttech.mapper.ProductMapper;
import com.planttech.mapper.PlantColorMapper;
import com.planttech.service.AiService;
import com.planttech.service.BookmarkService;


@Service
@Transactional
public class BookmarkServiceImpl implements BookmarkService {

	@Override
	public List<Bookmark> getProductBookmarkList(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addProductBookmark(Bookmark bookmark) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeProductBookmark(Bookmark bookmark) {
		// TODO Auto-generated method stub
		return 0;
	}
	


}
