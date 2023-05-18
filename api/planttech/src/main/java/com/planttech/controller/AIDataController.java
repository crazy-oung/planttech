package com.planttech.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.planttech.domain.ai.PlantSensorAverage;
import com.planttech.domain.plant.Plant;
import com.planttech.domain.response.ErrorMessage;
import com.planttech.domain.response.Message;
import com.planttech.domain.search.Page;
import com.planttech.service.AiDataService;
import com.planttech.service.PlantService;
import com.planttech.util.StatusEnum;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "AI Data", description = "인공지능 데이터 관리 API (인공지능 데이터 관리용)")
@RestController
@RequestMapping("/ai-data")
public class AIDataController {
	
	@Autowired 
	private AiDataService 	aiDataService;
	
	// 식물 평균 데이터 조회 
	@GetMapping("/average")
	@Operation(summary = "인공지능 일차별 누적 데이터 조회", description = "현재등록된 일차별 식물 평균 센서 값 정보/종류를 불러옵니다.")
	public ResponseEntity getPlantSensorAverageList() {
		System.out.println("::: getPlantList :::");
		
		try {
			return new ResponseEntity<>(aiDataService.getPlantSensorAverageList(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ErrorMessage("500", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	// 센서 평균 데이터 추가
	@PostMapping("/average")
	@Operation(summary = "식물 정보 누적 평균값 데이터 저장", description = "평균값을 일차별로 누적합니다.")
	public ResponseEntity addPlantSensor(@RequestBody PlantSensorAverage plantSensorAverage) {
		System.out.println("::: addPlantSensor :::");
		
		try {
			
			return new ResponseEntity<>( HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ErrorMessage("500", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	// 식물 평균 데이터 조회 
	@GetMapping("/pic")
	@Operation(summary = "인공지능 일차별 누적 데이터 조회 (TODO)", description = "현재등록된 일차별 식물 평균 센서 값 정보/종류를 불러옵니다.")
	public ResponseEntity getPlantPictureList(@RequestParam int userNo, @RequestParam int plantWarehouseNo) {
		System.out.println("::: getPlantPictureList :::");
		try {
			
			return new ResponseEntity<>( HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ErrorMessage("500", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	// 센서 평균 데이터 추가
	@PostMapping("/pic")
	@Operation(summary = "식물 사진 기록 (TODO)", description = "식물 사진 데이터를 추가합니다. ")
	public int addPlantPicture(@RequestParam int userNo) {
		System.out.println("::: POST -  addPlantPicture :::");
		
		System.out.println(userNo);
		
		return 0;
	}
	
}
