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

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Auth", description = "유저 세션 API")
@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private PlantSensorService PlantSensorService;
	
	
//	@PostMapping
//	public List<PlantSensor> addSession(	 ) {
//		System.out.println("::: GET -  getPlantSensor DefalutList :::");
//		
//		
//		System.out.println(pageParameters.toString());
//		return PlantSensorService.getPlantSensorList(pageParameters);
//	}
//	
//	

	// 로그아웃
	@GetMapping("/logout")
	public int removeSession() {
		
		System.out.println("::: removeSession :::");

		return 0;
	}
	
	
	
	
	
}
