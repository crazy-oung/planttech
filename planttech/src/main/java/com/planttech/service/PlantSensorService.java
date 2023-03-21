package com.planttech.service;

import java.util.List;

import com.planttech.domain.PlantSensor;
import com.planttech.domain.Page;

public interface PlantSensorService {

	public List<PlantSensor> getPlantSensorList(Page page);

	public int addPlantSensor(PlantSensor PlantSensor);
//
//	// Tset 상세정보 가져오기
//	public TsetBoard getTsetOne(int TsetId);
//
//	// Tset 정보 삭제하기
//	public int removeTset(int TsetId);
//
//	// Tset 리스트 삭제
//	public void removeTsetBoardList(List<String> TsetBoardIdList);
//
//	// Tset 수정하기
//	public int modifyTset(TsetBoard TsetBoard);
	
	
}
