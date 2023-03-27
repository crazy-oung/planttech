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

@RestController
@RequestMapping("/PlantSensor")
public class PlantSensorController {
	
	@Autowired
	private PlantSensorService PlantSensorService;
	
	
	@GetMapping
	public List<PlantSensor> getPlantSensorDefalutList(	  @RequestParam(defaultValue = "0") int page
														, @RequestParam(defaultValue = "10") int pageSize
														, @RequestParam(defaultValue="") String userId) {
		System.out.println("::: GET -  getPlantSensor DefalutList :::");
		
		Page pageParameters 		= new Page();
		pageParameters.beginPage 	= page * pageSize;
		pageParameters.pageSize 	= pageSize;
		pageParameters.userId		= userId;
		
		System.out.println(pageParameters.toString());
		return PlantSensorService.getPlantSensorList(pageParameters);
	}
	
	// 센서 데이터 기록
	@PostMapping
	public int addPlantSensor(@RequestBody PlantSensor plantSensor) {
		
		System.out.println("::: POST -  addPlantSensor :::");

		System.out.println(plantSensor.toString());
		return PlantSensorService.addPlantSensor(plantSensor);
	}
	
	
	
	
	
}
