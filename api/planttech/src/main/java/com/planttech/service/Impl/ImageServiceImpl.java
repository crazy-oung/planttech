package com.planttech.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.planttech.domain.plant.PlantImage;
import com.planttech.mapper.PlantImageMapper;
import com.planttech.service.ImageService;
import com.planttech.util.StringUtil;


@Service
@Transactional
public class ImageServiceImpl implements ImageService {
	
	@Autowired private PlantImageMapper plantImageMapper;
	
	// ==== 색채 분석 ================================================================================
	@Override
	public List<PlantImage> getPlantImageList(Map<String, Object> map) {
		return plantImageMapper.selectPlantImageList(map);
	}

	@Override
	public PlantImage addPlantImage (PlantImage plantImage) {
		int len = plantImage.getPlantImagePic().length();
		// 몰라
		plantImage.setPlantImagePic1(plantImage.getPlantImagePic().substring(0,			len/3));
		plantImage.setPlantImagePic2(plantImage.getPlantImagePic().substring(len/3 , 	len/3*2));
		plantImage.setPlantImagePic3(plantImage.getPlantImagePic().substring(len/3*2 , 	len));
		
		plantImageMapper.insertPlantImage(plantImage);
		return plantImage;
	}


}
