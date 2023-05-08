package com.planttech.controller.rest;


import com.planttech.domain.Message;
import com.planttech.domain.StatusEnum;
import com.planttech.domain.User;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planttech.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User", description = "유저")
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	Message message 		= new Message();
    HttpHeaders headers		= new HttpHeaders();
    

	@Operation(summary = "유저 로그인", description = "아이디 패스워드를 받아 로그인 처리(세션 생성)합니다.")
	@PostMapping("/login")
	public ResponseEntity addSession(HttpSession session, @Parameter(in = ParameterIn.DEFAULT, description = "유저 아이디, 비밀번호", required = true ) @RequestBody User user) {
		
		System.out.println(user.toString());
		
		System.out.println("::: addSession :::" + "\n" + user.toString()); 
      
        
		// 세션 검사
		if(session.getAttribute("loginUser") != null) {
			System.out.println("session exsist");
			
			User sessionUser = (User)session.getAttribute("loginUser");
			
			message.setStatus(StatusEnum.BAD_REQUEST);
	        message.setMessage("로그인 세션 존재");
	        message.setData(sessionUser);
	        
			return new ResponseEntity<>(message, headers, HttpStatus.BAD_REQUEST);
		}  
		
		// 매니저 검사
//		Manager loginManager = adminService.verifyManager(user);
//		if(loginManager != null) {
//			System.out.println("manager Login-->"); 
//			session.setAttribute("loginManager", loginManager);
//			session.setAttribute("authority", "manager");
//			return "/codeblue/admin/home";
//		}
		
		
		// 유저 검사
		User loginUser = userService.verifyUser(user);
		if(loginUser != null) {
			System.out.println("계정 정보 확인");
			
			session.setAttribute("loginUser", loginUser);
			session.setAttribute("authority", "user");
			
			message.setStatus(StatusEnum.OK);
	        message.setMessage("로그인 성공");
	        message.setData(user);
		        
			return new ResponseEntity<>(message, headers, HttpStatus.OK);
		}
		
		System.out.println("계정 정보 불일치");


        message.setStatus(StatusEnum.UNAUTHORIZED);
        message.setMessage("계정 정보 불일치");

        return new ResponseEntity<>(message, headers, HttpStatus.UNAUTHORIZED);
	}
	

	@Operation(summary = "미완", description = "이메일 중복 검사")
	@GetMapping("/check/email")
	public String getUserEmail(String userEmail) {
		System.out.println("::: getUserEmail :::"); 
		System.out.println(userEmail); 
		
		
		return "0";
	}
	
	
	@Operation(summary = "미완", description = "아이디 중복 검사")
	@GetMapping("/check/id")
	public String getUserId(String userId) {
		System.out.println("::: getUserId :::"); 
		System.out.println(userId); 
		
		
		
		return "0";
	}
	

	@Operation(summary = "로그아웃", description = "세션을 삭제하여 로그아웃합니다.")
	@GetMapping("/logout")
	public ResponseEntity removeSession(HttpSession session) {
		
		
		System.out.println("::: removeSession :::");
		if(session.getAttribute("loginUser") != null) {
			session.invalidate();
			
			message.setStatus(StatusEnum.OK);
	        message.setMessage("로그아웃 완료");
	        message.setData(null);
	        
			return new ResponseEntity<>(message,  HttpStatus.OK);
		} 
		
		setFailMessage();
		message.setMessage("로그인 세션 존재하지 않음");
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}
	
	
	

	@Operation(summary = "회원가입", description = "신규 유저 정보를 등록합니다.")
	@PostMapping("/register")
	public ResponseEntity addUser(@RequestBody User user) {
		System.out.println("::: addUser :::"); 
		System.out.println(user.toString()); 
		
		if(userService.addUser(user) == 1) {
			message.setStatus(StatusEnum.OK);
	        message.setMessage("회원가입 완료");
	        message.setData(1);
	        
	        return new ResponseEntity<>(message,  HttpStatus.OK);
	        
		}
		
		setFailMessage();
		message.setMessage("회원 등록 실패");
		return new ResponseEntity<>(message,  HttpStatus.BAD_REQUEST);
	}
	
	void setFailMessage() {
		message.setStatus(StatusEnum.BAD_REQUEST);
        message.setData(null);
	}
}
