package com.planttech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.planttech.domain.ai.PlantColor;
import com.planttech.domain.ai.PlantSensorAverage;
import com.planttech.domain.response.ErrorMessage;
import com.planttech.domain.search.Page;
import com.planttech.service.AiService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;

@Tag(name = "AI", description = "인공지능 데이터 관리 API")
@RestController
@RequestMapping("/ai")
public class AIController {
	
	@Autowired 
	private AiService 	aiService;
	
	// ==== 센서 평균 ================================================================================
	@Operation(summary = "인공지능 일차별 누적 데이터 조회", description = "파라미터: {beginPage, pageSize} 현재등록된 식물의 평균 센서 값 정보/종류를 불러옵니다.")
	@GetMapping("/sensor/average")
	public ResponseEntity getPlantSensorAverageList(Page page) {
		System.out.println(page.toString());
		return new ResponseEntity<>(aiService.getPlantSensorAverageList(page), HttpStatus.OK);
		
	}
	
	@Operation(summary = "식물 정보 누적 평균값 데이터 저장", description = "평균값을 일차별/계절/주말,주중?별로 누적합니다.")
	@PostMapping("/sensor/average")
	public ResponseEntity addPlantSensor(@RequestBody PlantSensorAverage plantSensorAverage) {
		return new ResponseEntity<>(aiService.addPlantSensorAverage(plantSensorAverage), HttpStatus.OK);
	}
	
	
	// ==== 입찰 평균 ================================================================================
	@Operation(summary = "입찰 내역 평균 조회", description = "현재등록된 식물의 평균 센서 값 정보/종류를 불러옵니다.")
	@GetMapping("/bid/average")
	public ResponseEntity getPlantBidAverageList(Page page) {
		List<PlantSensorAverage> list = aiService.getPlantSensorAverageList(page);
		return new ResponseEntity<>(list, HttpStatus.OK);
		
	}
	
	@Operation(summary = "입찰 내역 데이터 추가", description = "평균값을 일차별/계절/주말,주중?별로 누적합니다.")
	@PostMapping("/bid/average")
	public ResponseEntity addPlantBidAverage(@RequestBody PlantSensorAverage plantSensorAverage) {
		return new ResponseEntity<>(aiService.addPlantSensorAverage(plantSensorAverage), HttpStatus.OK);
	}
	
	
	// ==== 색채 분석  ================================================================================
	@Operation(summary = "식물 색체 데이터 분석 결과 조회", description = "현재등록된 식물의 색체 데이터 분석 결과를 조회합니다. {plantWarehouseNo}를 인자로 받습니다.")
	@GetMapping("/plant/color-analysis")
	public ResponseEntity getPlantColorAnalysisList(HttpSession session, Page page) {
		return new ResponseEntity<>(aiService.getPlantColorAnalysisList(page), HttpStatus.OK);
	}
	
	@Operation(summary = "식물 색체 데이터 분석 결과 저장", description = "현재등록된 식물의 색체 데이터 분석 결과를 추가합니다. {plantWarehouseNo, plantColorGrade} 인자로 받습니다.")
	@PostMapping("/plant/color-analysis")
	public ResponseEntity addPlantColorAnalysis(@RequestBody PlantColor plantColor) {
		return new ResponseEntity<>(aiService.addPlantColorAnalysis(plantColor), HttpStatus.OK);
	}
	
}
