package com.planttech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planttech.domain.Message;
import com.planttech.domain.Page;
import com.planttech.service.PlantService;
import com.planttech.util.StatusEnum;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Plant", description = "식물 API")
@RestController
@RequestMapping("/plant")
public class PlantController {
	
	@Autowired 
	private PlantService 	plantService;

	private Page page;
	private Message 		message = new Message();
	private HttpHeaders 	headers = new HttpHeaders();
	
	
	// 식물 종류 출력
	@GetMapping()
	@Operation(summary = "식물 종류 조회", description = "현재등록된 식물 정보/종류를 불러옵니다.")
	public ResponseEntity<Message> getPlantList(Page page) {
		System.out.println("::: getPlantList :::");
		
		try {
			System.out.println(page.toString());
			
			message.setStatus(StatusEnum.OK);
			message.setMessage("식물 종류");
			message.setData(plantService.getPlantList(page));
			return new ResponseEntity<>(message, headers, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			message.setMessage(e.getMessage() != null? e.getMessage() : "error");
			message.setStatus(StatusEnum.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<>(message, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	
	
}