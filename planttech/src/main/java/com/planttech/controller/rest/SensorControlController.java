package com.planttech.controller.rest;

import java.util.List;

import com.planttech.domain.PlantSensor;
import com.planttech.domain.SensorControlTf;
import com.planttech.domain.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.planttech.service.PlantSensorService;

@RestController
@RequestMapping("/SensorControl")
public class SensorControlController {
	
	@Autowired
	private PlantSensorService PlantSensorService;
	
	
	@GetMapping
	public SensorControlTf getUserSensorControl(@RequestParam int userNo) {
		System.out.println("::: GET -  getPlantSensor DefalutList :::");
		
		System.out.println(userNo);
		
		return PlantSensorService.getUserSensorControl(userNo);
	}
	
//	// 센서 데이터 기록
//	@PostMapping
//	public int addPlantSensor(@RequestBody PlantSensor plantSensor) {
//		
//		System.out.println("::: POST -  addPlantSensor :::");
//
//		System.out.println(plantSensor.toString());
//		return PlantSensorService.addPlantSensor(plantSensor);
//	}
	
	
	// 센서 제어
	@PostMapping("/WT")
	public int addPlantSensorWataerPump(@RequestParam int userNo, @RequestParam int waterPumpTf) {
	
		System.out.println("::: POST -  addPlantSensor WataerPump :::");

		System.out.println(userNo + " " +  waterPumpTf);
		
		return PlantSensorService.addPlantSensorWataerPump(userNo, waterPumpTf);
	}
	
	@PostMapping("/LED")
	public int addPlantSensorLED(@RequestParam int userNo, @RequestParam int ledTf) {
		
		System.out.println("::: POST -  addPlantSensor LED :::");

		System.out.println(userNo + " " +  ledTf);
		
		return PlantSensorService.addPlantSensorLED(userNo, ledTf);
	}
	
	
}
