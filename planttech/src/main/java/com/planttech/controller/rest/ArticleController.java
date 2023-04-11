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

@Tag(name = "Article", description = "재배 식물 거래 API")
@RestController
@RequestMapping("/article")
public class ArticleController {
	
	@Autowired
	private PlantSensorService ArticleService;
	
	@GetMapping
	public int getArticleList() {
		
		System.out.println("::: GET - getArticleList :::");

		return 0;
	}
	
	
	
	
	
}
