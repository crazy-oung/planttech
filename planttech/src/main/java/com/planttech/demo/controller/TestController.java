package com.planttech.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planttech.domain.Plant;
import com.planttech.service.TestService;

@RestController
public class TestController {
	@Autowired private TestService testService;
	
	// test - 식물 스트 GET
	@GetMapping("/test")
	public List<Plant> getInqueryAnswerPercentage() {
		System.out.println("::: get - getInqueryAnswerPercentage :::");
		System.out.println("::: get - getInqueryAnswerPercentage :::");
		System.out.println("::: get - getInqueryAnswerPercentage :::");
		return testService.getTestDataList();
	}
}
