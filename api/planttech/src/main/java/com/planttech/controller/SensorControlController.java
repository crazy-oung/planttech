package com.planttech.controller;

import com.planttech.domain.Message;
import com.planttech.domain.SensorControlTf;

import org.apache.ibatis.javassist.expr.Instanceof;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	private PlantSensorService plantSensorService;
	
	private Message		message = new Message();
	private HttpHeaders headers = new HttpHeaders();
	
	
	@GetMapping
	@Operation(summary = "식물 환경 제어 센서 정보 가져오기", description = "유저 고유 번호로 해당 유저의 식물 환경 제어 센서의 상태 값 정보를 가져옵니다.")
	public SensorControlTf getUserSensorControl(@Parameter(description = "센서 정보를 가져올 유저 번호", in = ParameterIn.DEFAULT) @RequestParam int userNo) {
		System.out.println("::: getUserSensorControl :::");
		System.out.println(userNo);
		
		return plantSensorService.getUserSensorControl(userNo);
	}	
	
	@PostMapping
	@Operation(summary = "식물 환경 제어 센서 정보 추가하기", description = "세 센서 제어 정보를 추가 합니다.")
	public ResponseEntity<Message> addUserSensorControl(@RequestBody SensorControlTf sensorControlTf) {
		try {
			plantSensorService.addUserSensorControl(sensorControlTf);
			message.setSuccessMessage();
			message.setMessage("추가 완료");
			message.setData(sensorControlTf);
			
			return new ResponseEntity<>(message, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			
			message.setFailMessage();
			message.setData(sensorControlTf);
			if (e instanceof DuplicateKeyException) {
				message.setMessage("해당 센서 데이터가 이미 존재합니다. 센서 제어 정보는 중복으로 등록 될 수 없습니다.");
			} else if (e instanceof DataIntegrityViolationException) {
				message.setMessage("등록 되지 않은 센서입니다.");
			}
			return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
	
	// 센서 제어
	@PostMapping("/water-pump")
	@Operation(summary = "식물 물 펌프 센서 제어", description = "유저 고유 번호로 해당 유저 식물의 물 펌프 센서의 상태 값을 수정합니다.")
	public int addPlantSensorWataerPump(@RequestParam int userNo, @RequestParam int waterPumpTf) {
	
		System.out.println("::: POST -  addPlantSensorWataerPump :::");

		System.out.println(userNo + " " +  waterPumpTf);
		
		return plantSensorService.addPlantSensorWataerPump(userNo, waterPumpTf);
	}
	
	@PostMapping("/humidifier")
	@Operation(summary = "식물 가습기 센서 제어", description = "유저 고유 번호로 해당 유저 식물의 가습기 센서의 상태 값을 수정합니다.")
	public int addPlantSensorHumidifier(@RequestParam int userNo, @RequestParam int humidifierTf) {
		
		System.out.println("::: POST -  addPlantSensorHumidifier :::");

		System.out.println(userNo + " " +  humidifierTf);
		
		return plantSensorService.addPlantSensorHumidifier(userNo, humidifierTf);
	}
	
	
}
