package com.planttech.controller;

import jakarta.servlet.http.HttpSession;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.planttech.domain.response.ErrorMessage;
import com.planttech.domain.response.Message;
import com.planttech.domain.response.SuccessMessage;
import com.planttech.domain.shop.Product;
import com.planttech.domain.user.User;
import com.planttech.domain.user.UserNotification;
import com.planttech.exception.LoginException;
import com.planttech.exception.PasswordException;
import com.planttech.service.UserService;
import com.planttech.util.ErrorCodeEnum;
import com.planttech.util.StatusEnum;
import com.planttech.util.StringUtil;
import com.planttech.util.UserUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperties;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User Mileage", description = "유저 마일리지")
@RestController
@RequestMapping("/user/milage")
@Validated
public class UserMileageController {
	
	@Autowired private UserService userService;
	
	// ==== 유저 마일리지 ================================================================================
	@Operation(summary = "유저 마일리지 내역 조회", description = "로그인시 사용 가능")
	@GetMapping()
	public ResponseEntity getUserMileageHistory(HttpSession session) throws LoginException {
		if(!UserUtil.isUser(session)) throw new LoginException();
		return new ResponseEntity<>(new Message(HttpStatus.OK,"내 마일리지 내역", userService.getUserMileageList(UserUtil.getUser(session))), HttpStatus.OK);
	}
	
	
	
}
