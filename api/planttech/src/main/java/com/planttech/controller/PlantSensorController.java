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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.planttech.service.PlantSensorService;
import com.planttech.util.UserUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;

@Tag(name = "Plant Sensor", description = "환경 센서 API (제어 API는 따로 있습니다.)")
@RestController
@RequestMapping("/plant-sensor")
public class PlantSensorController {
	
	@Autowired
	private PlantSensorService PlantSensorService;
	
	
	@GetMapping
	@Operation(summary = "식물 환경 정보 조회", description = "로그인한 유저의 식물 환경 제어 센서의 상태 값 정보를 가져옵니다. 검색은 or 형태로 이루어 집니다.")
	public List<PlantSensor> getPlantSensorDefalutList ( @RequestParam @Parameter(description = "화분 고유번호 ", required = false) int warehousePlantNo
													   , @RequestParam @Parameter(description = "조회할 환경정보의 날짜 범위 (예. 30 입력시, 오늘 포함 30일 치의 데이터를 조회)", required = false) int searchDate
													   , HttpSession session ) throws LoginException {
		if(!UserUtil.isUser(session)) throw new LoginException();
		
		return PlantSensorService.getPlantSensorList(warehousePlantNo, searchDate, UserUtil.getUser(session));
	}
	
	
	// 랙 시스템 영역
	@PostMapping
	@Operation(summary = "식물 환경 정보 저장", description = "유저 고유 번호로 해당 유저의 식물 환경 제어 센서의 상태 값 정보를 저장니다.")
	public int addPlantSensor (@RequestBody PlantSensor plantSensor) {
		return PlantSensorService.addPlantSensor(plantSensor);
	}
	
	@GetMapping("/all")
	@Operation(summary = "식물 환경 정보 입력 현황 조회 ", description = "로그인한 유저의 식물 환경 제어 센서의 상태 값 정보를 가져옵니다. 검색은 or 형태로 이루어 집니다.")
	public List<PlantSensor> getPlantSensorDefalutList () throws LoginException {
		return PlantSensorService.getAllPlantSensorList();
	}
	
	
}
