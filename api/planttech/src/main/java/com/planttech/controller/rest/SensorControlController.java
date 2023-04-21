package com.planttech.controller.rest;

import com.planttech.domain.SensorControlTf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.planttech.service.PlantSensorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Senesor Control", description = "센서 제어")
@RestController
@RequestMapping("/sensor-control")
public class SensorControlController {
	
	@Autowired
	private PlantSensorService PlantSensorService;
	
	
	@GetMapping
	@Operation(summary = "식물 환경 제어 센서 정보 가져오기", description = "유저 고유 번호로 해당 유저의 식물 환경 제어 센서의 상태 값 정보를 가져옵니다.")
	public SensorControlTf getUserSensorControl(@Parameter(description = "센서 정보를 가져올 유저 번호", in = ParameterIn.DEFAULT) @RequestParam int userNo) {
		System.out.println("::: GET -  getUserSensorControl :::");
		
		System.out.println(userNo);
		
		return PlantSensorService.getUserSensorControl(userNo);
	}	
	
	// 센서 제어
	@PostMapping("/water-pump")
	@Operation(summary = "식물 물 펌프 센서 제어", description = "유저 고유 번호로 해당 유저 식물의 물 펌프 센서의 상태 값을 수정합니다.")
	public int addPlantSensorWataerPump(@RequestParam int userNo, @RequestParam int waterPumpTf) {
	
		System.out.println("::: POST -  addPlantSensorWataerPump :::");

		System.out.println(userNo + " " +  waterPumpTf);
		
		return PlantSensorService.addPlantSensorWataerPump(userNo, waterPumpTf);
	}
	
	@PostMapping("/led")
	@Operation(summary = "식물 LED 센서 제어", description = "유저 고유 번호로 해당 유저 식물의 LED 센서의 상태 값을 수정합니다.")
	public int addPlantSensorLED(@RequestParam int userNo, @RequestParam int ledTf) {
		
		System.out.println("::: POST -  addPlantSensor LED :::");

		System.out.println(userNo + " " +  ledTf);
		
		return PlantSensorService.addPlantSensorLED(userNo, ledTf);
	}
	
	
}
