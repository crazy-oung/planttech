package com.planttech.controller;

import java.util.HashMap;
import java.util.Map;

import org.springdoc.api.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.planttech.domain.ai.PlantColor;
import com.planttech.domain.plant.PlantImage;
import com.planttech.domain.response.SuccessMessage;
import com.planttech.domain.search.Page;
import com.planttech.domain.shop.Product;
import com.planttech.domain.user.User;
import com.planttech.service.ImageService;
import com.planttech.service.ProductService;
import com.planttech.service.UserService;
import com.planttech.util.StringUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;

@Tag(name = "Image", description = "이미지 API")
@RestController
@RequestMapping("/img")
public class ImageController {
	
	@Autowired private ImageService imageService;
	
	@Operation(summary = "식물 이미지 조회", description = "식물 이미지를 불러옵니다. 없으면 준비중 이미지가 출력됩니다.")
	@GetMapping("/plant")
	public ResponseEntity getPlantColorAnalysisList ( 	@RequestParam @Parameter(example = "-1", description = "식물 고유 번호(null: -1, 전체조회: -2 )", required = false) int plantNo,
														@RequestParam @Parameter(example = "0") int beginPage,
														@RequestParam @Parameter(example = "10") int pageSize	) {
		Map<String,Object> map = new HashMap<>() {{
			put("plantNo", 		plantNo);
			put("beginPage", 	beginPage);
			put("pageSize", 	pageSize);
		}};
		return new ResponseEntity<>(imageService.getPlantImageList(map), HttpStatus.OK);
	}
	
	
	@Operation(summary = "식물 이미지 저장", description = "현재등록된 식물의 색체 데이터 분석 결과를 추가합니다. {plantWarehouseNo, plantColorGrade} 인자로 받습니다.")
	@PostMapping("/plant")
	public ResponseEntity addPlantColorAnalysis ( 
			@Schema(  example 		= "{\n"
					+ "  \"plantNo\": \"\",\n"
					+ "  \"plantImagePic\": \"base64Code\""
					+ "}" 
	, description 	= "식물이미지" ) 
			@RequestBody PlantImage plantImage) {
		return new ResponseEntity<>( new SuccessMessage(imageService.addPlantImage(plantImage)), HttpStatus.OK);
	}
	
	
}
