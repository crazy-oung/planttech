package com.planttech.controller.rest;


import com.planttech.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.planttech.service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User", description = "유저")
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	// 세션 추가
	@PostMapping("/login")
	public String addSession(User user) {
		System.out.println("::: addSession :::"); 
		System.out.println(user); 
		
		// 세션 검사
//		if(session.getAttribute("loginUser") != null) {
//			System.out.println("session exsist");
//			
//			User sessionUser = (User)session.getAttribute("loginUser");
//			return sessionUser.toString();
//		}  
		
//		// 매니저 검사
//		Manager loginManager = adminService.verifyManager(user);
//		if(loginManager != null) {
//			System.out.println("manager Login-->"); 
//			session.setAttribute("loginManager", loginManager);
//			session.setAttribute("authority", "manager");
//			return "/codeblue/admin/home";
//		}
//		
		
		// 유저 검사
		User loginUser = userService.verifyUser(user);
		if(loginUser != null) {
			System.out.println("user Login"); 
			System.out.println("계정 정보 확인");
//			session.setAttribute("loginUser", loginUser);
//			session.setAttribute("authority", "user");
			
			return "success";
		}
		
		System.out.println("계정 정보 불일치");
		return "fail";
	}
	
	// 이메일 중복 검사
	@GetMapping("/check/email")
	public String getUserEmail(String userEmail) {
		System.out.println("::: getUserEmail :::"); 
		System.out.println(userEmail); 
		
		
		return "0";
	}
	
	// 아이디 중복 검사
	@GetMapping("/check/id")
	public String getUserId(String userId) {
		System.out.println("::: getUserId :::"); 
		System.out.println(userId); 
		
		
		
		return "0";
	}
	

	// 로그아웃
	@GetMapping("/logout")
	public int removeSession(@RequestParam String userNo) {
		
		System.out.println("::: removeSession :::");
		System.out.println(userNo);

		return 0;
	}
	
	
	
	// 회원가입
	@PostMapping("/register")
	public int addUser(@RequestBody User user) {
		System.out.println("::: addUser :::"); 
		System.out.println(user.toString()); 
		
		return userService.addUser(user);
	}
	
}
