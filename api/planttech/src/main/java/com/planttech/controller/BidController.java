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

import com.planttech.domain.response.ErrorMessage;
import com.planttech.domain.response.SuccessMessage;
import com.planttech.domain.search.Page;
import com.planttech.domain.shop.Product;
import com.planttech.domain.user.User;
import com.planttech.domain.user.UserNotification;
import com.planttech.exception.LoginException;
import com.planttech.service.ProductService;
import com.planttech.service.UserService;
import com.planttech.util.UserUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;

@Tag(name = "Bid", description = "입찰 확정 API")
@RestController
@RequestMapping("/bid")
public class BidController {
	
	@Autowired ProductService productService;
	@Autowired UserService  userService;
	
	@Operation(summary = "입찰 확정", description = "입찰을 확정 합니다.")
	@PostMapping()
	public ResponseEntity addBid(@RequestBody Product product, HttpSession session) throws LoginException {
		if(!UserUtil.isUser(session)) throw new LoginException();
		SuccessMessage succ = new SuccessMessage("status", "0");
		product.setProductActive(2);
		
		if (productService.modifyProduct(product) != 0) {
			
			UserNotification usNt = new UserNotification();
			User user = UserUtil.getUser(session);
			
			usNt.setProductNo(product.getProductNo());
			usNt.setUserNo(user.getUserNo());
			usNt.setUserNotificationContent( (product.getProductInstant() == 0? "즉시 ": "입찰 " ) + (product.getProductType() == 0? "판매 ": "구먀 " ) + "성사 알림");
			usNt.setUserPlantNo(product.getPlantNo());
			usNt.setWarehousePlantNo(product.getWarehousePlantNo());
			usNt.setUserNotificationType(1); 
			
			userService.addUserNotification(usNt);
			
			succ = new SuccessMessage("status", "1");
		} else {
			return new ResponseEntity<>(new ErrorMessage("NO_PRODUCT", "해당 거래 정보를찾을 수 없습니다."), HttpStatus.OK);
		}
		
		// 화분 고유 번호 이전
//		if()
			
		
		return new ResponseEntity<>(succ, HttpStatus.OK);
	}
	
	@Operation(summary = "체결 내역 조회", description = "해당 기간동안 체결된 내역을 조회합니다.")
	@GetMapping()
	public ResponseEntity getBidList( @RequestParam @Parameter(description = "식물 종 고유번호") int plantNo
									, @RequestParam @Parameter(description = "조회 날짜 범위 (예. 30일 내 검색시, 30입력)") int searchDate
									, @RequestParam @Parameter(description = "식물 등급 (1: Very Bad, 2: Bad, 3: Soso, 4: Good, 5: Very Good)") int plantScoreNo ) {
		return new ResponseEntity<>(productService.getProductBidList(plantNo, searchDate, plantScoreNo), HttpStatus.OK);
	}
	
	
}
