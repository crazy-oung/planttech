package com.planttech.controller;

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

import com.planttech.domain.search.Page;
import com.planttech.domain.shop.Product;
import com.planttech.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Product", description = "식물 경매/거래")
@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired ProductService productService;
	
	
	@Operation(summary = "입찰 목록 조회", description = " { beginPage, pageSize} 키워드, 카테고리, 페이지 번호, 페이지당 글 수를 이용해 입찰을 조회합니다. 목록 출력시 productNo에 해당품목의 진행중인 입찰 수가 출력됩니다.")
	@GetMapping()
	public ResponseEntity getProductList(Page page) {
		return new ResponseEntity<>(productService.getProductList(page), HttpStatus.OK);
	}
	
	@Operation(summary = "입찰 상세", description = "해당 상품에 대한 상세 입찰 정보를 조회합니다.")
	@GetMapping("/{plantNo}")
	public ResponseEntity getProductProductInfo(@PathVariable("plantNo") int plantNo) {
		return new ResponseEntity<>(productService.getProduct(plantNo), HttpStatus.OK);
	}
	
	@Operation(summary = "입찰 상제 등급별 가격 정보 출력", description = "해당 상품에 대한 등급별 가격 정보를 조회합니다.")
	@GetMapping("/grades/{plantNo}")
	public ResponseEntity getProductPriceListByGrade(@PathVariable("plantNo") int plantNo, @RequestParam int productType, @RequestParam int productInstant) {
		
		Product product = new Product();
		product.setPlantNo(plantNo);
		product.setProductType(productType);
		product.setProductInstant(productInstant);
		System.out.println(product.toString());
		return new ResponseEntity<>(productService.getProductPriceListByGrade(product), HttpStatus.OK);
	}
	
	@Operation(summary = "입찰 삭제", description = "등록한 입찰을 삭제합니다. 객체 값중 생성일자 및 수정일자는 받지 않습니다.")
	@DeleteMapping()
	public ResponseEntity removeProduct(@RequestParam int productNo) {
		Product product = new Product();
		product.setProductNo(productNo);
		return new ResponseEntity<>(productService.removeProduct(product), HttpStatus.OK);
	}
	
	@Operation(summary = "판매 입찰 추가", description = "입찰을 추가합니다. productType|입찰 타입 - 거래 유형 (0: 판매, 1: 구매), productInstant|거래 유형 - 즉시 거래 여부 (0: 입찰상품, 1: 즉시거래상품) 타입에 유의합니다. ")
	@PostMapping()
	public ResponseEntity addProduct(@RequestBody  Product product) {
		return new ResponseEntity<>(productService.addProduct(product), HttpStatus.OK);
	}

	@Operation(summary = "입찰 정보 수정", description = "입찰 정보를 수정합니다. 파라미터는 입찰 추가와 같으며 마찬가지로 객체 값중 생성일자 및 수정일자는 받지 않습니다.")
	@PutMapping()
	public ResponseEntity modifyProduct(@RequestBody Product product) {
		return new ResponseEntity<>(productService.modifyProduct(product), HttpStatus.OK);
	}
	
	
	
}
