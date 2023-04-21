package com.planttech.controller.rest;

import java.util.List;

import com.planttech.domain.PlantSensor;
import com.planttech.domain.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.planttech.service.PlantSensorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Plant Sensor", description = "식물 환경 정보 센서")
@RestController
@RequestMapping("/plant-sensor")
public class PlantSensorController {
	
	@Autowired
	private PlantSensorService PlantSensorService;
	
	
	@GetMapping
	@Operation(summary = "식물 환경 정보 가져오기", description = "유저 고유 번호로 해당 유저의 식물 환경 제어 센서의 상태 값 정보를 가져옵니다.")
	public List<PlantSensor> getPlantSensorDefalutList(	  @RequestParam(defaultValue = "0") int page
														, @RequestParam(defaultValue = "10") int pageSize
														, @RequestParam(defaultValue="") int userNo) {
		
		System.out.println("::: GET -  getPlantSensor DefalutList :::");
		
		Page pageParameters 		= new Page();
		pageParameters.setBeginPage(page * pageSize);
		pageParameters.setPageSize(pageSize);
		pageParameters.setUserNo(userNo);
		
		System.out.println(pageParameters.toString());
		return PlantSensorService.getPlantSensorList(pageParameters);
	}
	
	// 센서 데이터 기록
	@PostMapping
	@Operation(summary = "식물 환경 정보 저장하기", description = "유저 고유 번호로 해당 유저의 식물 환경 제어 센서의 상태 값 정보를 저장니다.")
	public int addPlantSensor(@RequestBody PlantSensor plantSensor) {
		
		System.out.println("::: POST -  addPlantSensor :::");

		System.out.println(plantSensor.toString());
		return PlantSensorService.addPlantSensor(plantSensor);
	}
	
	
	
	
	
}
