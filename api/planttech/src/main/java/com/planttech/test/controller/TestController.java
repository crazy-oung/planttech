package com.planttech.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planttech.domain.response.ErrorMessage;
import com.planttech.domain.response.Message;
import com.planttech.domain.search.Page;
import com.planttech.service.PlantService;
import com.planttech.util.StatusEnum;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Test", description = "테스트 API")
@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired 
	private PlantService 	plantService;
	
	
	// test - 식물 디비 잘 불러와지는지 테스트 
	@GetMapping()
	public ResponseEntity getPlantList() {
		try {
			Page page = new Page();
			return new ResponseEntity<>(plantService.getPlantList(page), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("::: !!! ERROR !!! :::");
			e.printStackTrace();
			return new ResponseEntity<>(new ErrorMessage("500", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
