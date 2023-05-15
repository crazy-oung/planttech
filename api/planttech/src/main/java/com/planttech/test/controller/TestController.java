package com.planttech.test.controller;

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

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Test", description = "테스트 API")
@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired 
	private PlantService 	plantService;

	private Page page;
	private Message 		message = new Message();
	private HttpHeaders 	headers = new HttpHeaders();
	
	
	// test - 식물 디비 잘 불러와지는지 테스트 
	@GetMapping()
	public ResponseEntity<Message> getPlantList() {
		System.out.println("::: TEST :::");
		
		try {
			
			message.setStatus(StatusEnum.OK);
			message.setMessage("식물 디비 출력 완료");
			message.setData(plantService.getPlantList(page));
			return new ResponseEntity<>(message, headers, HttpStatus.BAD_REQUEST);
			
		} catch (Exception e) {
			System.out.println("::: !!! ERROR !!! :::");
			e.printStackTrace();
			
			message.setMessage(e.getMessage() != null? e.getMessage() : "error");
			message.setStatus(StatusEnum.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<>(message, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
