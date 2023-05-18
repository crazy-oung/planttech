package com.planttech.controller;

import java.util.List;

import com.planttech.domain.factory.PlantSensor;
import com.planttech.domain.search.Page;
import com.planttech.exception.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planttech.service.PlantSensorService;
import com.planttech.util.UserUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;

@Tag(name = "Plant Sensor", description = "식물 환경 정보 센서")
@RestController
@RequestMapping("/plant-sensor")
public class PlantSensorController {
	
	@Autowired
	private PlantSensorService PlantSensorService;
	
	
	@GetMapping
	@Operation(summary = "식물 환경 정보 가져오기", description = "유저 고유 번호로 해당 유저의 식물 환경 제어 센서의 상태 값 정보를 가져옵니다.")
	public List<PlantSensor> getPlantSensorDefalutList(Page page, HttpSession session) throws LoginException {
		if(!UserUtil.isUser(session)) {
			throw new LoginException();
		}
		return PlantSensorService.getPlantSensorList(page);
	}
	
	// 센서 데이터 기록
	@PostMapping
	@Operation(summary = "식물 환경 정보 저장하기", description = "유저 고유 번호로 해당 유저의 식물 환경 제어 센서의 상태 값 정보를 저장니다.")
	public int addPlantSensor(@RequestBody PlantSensor plantSensor) {
		return PlantSensorService.addPlantSensor(plantSensor);
	}
	
	
	
	
	
}
