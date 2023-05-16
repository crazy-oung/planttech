package com.planttech.controller;

import jakarta.servlet.http.HttpSession;

import jakarta.validation.Valid;
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

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.planttech.domain.User;
import com.planttech.domain.ErrorMessage;
import com.planttech.domain.Message;
import com.planttech.service.UserService;
import com.planttech.util.StatusEnum;
import com.planttech.util.StringUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User", description = "유저")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
	
	@Autowired
	private UserService userService;
	
	private Message		message = new Message();
	private HttpHeaders headers = new HttpHeaders();
    
	@Operation(summary = "내정보 조회", description = "로그인 되어 있어야 가능합니다.")
	@PostMapping("/me")
	public ResponseEntity<Message> getMyInfo(HttpSession session) {
		System.out.println("::: getMyInfo :::"); 
		message.setFailMessage();
		
		// 세션 검사
		if(session.getAttribute("loginUser") != null) {
			User sessionUser = (User)session.getAttribute("loginUser");
			
			try {
				sessionUser.setUserMileage(userService.getUserTotalMileage(sessionUser));
				
				message.setSuccessMessage();
				message.setStatus(StatusEnum.OK);
				message.setData(sessionUser);
				
				return new ResponseEntity<>(message, headers, HttpStatus.OK);
			} catch (Exception e) {
				message.setStatus(StatusEnum.INTERNAL_SERVER_ERROR);
				message.setMessage(e.getMessage());
				
				return new ResponseEntity<>(message, headers, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} 
		
		message.setStatus(StatusEnum.UNAUTHORIZED);
		message.setMessage("로그인 필요");
		
		return new ResponseEntity<>(message, headers, HttpStatus.UNAUTHORIZED);
	}
	
	
	@Operation(summary = "유저 로그인", description = "아이디 패스워드를 받아 로그인 처리(세션 생성)합니다.")
	@PostMapping("/login")
	public ResponseEntity<Message> addSession(HttpSession session, @RequestBody(required = true) @Valid Account account) {
		
		User user = new User();
		user.setUserId(account.getId());
		user.setUserPw(account.getPassword());
		
		System.out.println("::: addSession :::"); 
        
		// 세션 검사
		if(session.getAttribute("loginUser") != null) {
			User sessionUser = (User)session.getAttribute("loginUser");
			log.info("sessionUser: {}", sessionUser);
			
			message.setStatus(StatusEnum.BAD_REQUEST);
	        message.setMessage("로그인 세션 존재");
	        message.setData(sessionUser);
	        
			return new ResponseEntity<>(message, headers, HttpStatus.BAD_REQUEST);
		}  
		
		// 유저 검사
		User loginUser = userService.verifyUser(user);
		if(loginUser != null) {
			System.out.println("계정 정보 확인");
			
			session.setAttribute("loginUser", loginUser);
			session.setAttribute("authority", "user");
			
			message.setStatus(StatusEnum.OK);
	        message.setMessage("로그인 성공");
	        message.setData(loginUser);
		        
			return new ResponseEntity<>(message, headers, HttpStatus.OK);
		}
		
		message.setFailMessage();
        message.setStatus(StatusEnum.UNAUTHORIZED);
        message.setMessage("계정 정보 불일치");

        return new ResponseEntity<>(message, headers, HttpStatus.UNAUTHORIZED);
	}
	

	@Operation(summary = "이메일 중복 검사", description = "이메일 중복 검사를 진행합니다.")
	@GetMapping("/check/email")
	public ResponseEntity<Message> getUserEmail(@RequestParam String userEmail) {
		System.out.println("::: getUserEmail :::"); 
		message.setFailMessage();
		
		if (!StringUtil.isEmail(userEmail)) {
			message.setMessage("올바르지 않은 이메일 형식입니다.");
			return new ResponseEntity<>(message, headers, HttpStatus.BAD_REQUEST);
		}
		
		if (userService.getUserByUserEmail(userEmail) == null) {;
			message.setSuccessMessage();
			message.setMessage("사용 가능한 이메일입니다.");
			return new ResponseEntity<>(message, headers, HttpStatus.OK);
		}
		
		message.setStatus(StatusEnum.UNAUTHORIZED);
		message.setMessage("사용중인 이메일입니다.");
		return new ResponseEntity<>(message, headers, HttpStatus.UNAUTHORIZED);
	}
	
	
	@Operation(summary = "아이디 중복 검사", description = "아이디 중복 검사를 결과를 반환합니다.")
	@GetMapping("/check/id")
	public ResponseEntity getUserId(@RequestParam String userId) {
		System.out.println("::: getUserId :::"); 
		System.out.println(userId); 
		
		if(userService.getUserByUserId(userId) != null) {
			ErrorMessage errMsg = new ErrorMessage("IN_USED", "아이디가 존재합니다.");
			return new ResponseEntity<>(errMsg.getMap(), HttpStatus.BAD_REQUEST);
		}
		
		message.setMessage("아이디 사용가능");
		return new ResponseEntity<>(message, HttpStatus.OK);
		
	}
	

	@Operation(summary = "로그아웃", description = "세션을 삭제하여 로그아웃합니다.")
	@GetMapping("/logout")
	public ResponseEntity<Message> removeSession(HttpSession session) {
		System.out.println("::: removeSession :::");
		
		if(session.getAttribute("loginUser") != null) {
			session.invalidate();
			
			message.setStatus(StatusEnum.OK);
	        message.setMessage("로그아웃 완료");
	        message.setData(null);
	        
			return new ResponseEntity<>(message,  HttpStatus.OK);
		} 
		
		message.setFailMessage();
		message.setMessage("로그인 세션 존재하지 않음");
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}
	
	
	

	@Operation(summary = "회원가입", description = "신규 유저 정보를 등록합니다.")
	@PostMapping("/register")
	public ResponseEntity<Message> addUser(@RequestBody User user) {
		System.out.println("::: addUser :::"); 
		System.out.println(user.toString()); 
		message.setFailMessage();
		
		switch (userService.addUser(user)) {
		case 0: {
			message.setMessage("회원 등록 실패");
			return new ResponseEntity<>(message,  HttpStatus.INTERNAL_SERVER_ERROR);
		} case -1:{
			message.setMessage("중복 회원 존재");
			return new ResponseEntity<>(message,  HttpStatus.BAD_REQUEST);
		}
		default:
			message.setSuccessMessage();
	        message.setMessage("회원가입 완료");
	        message.setData(new HashMap<String, Integer>() {
	            {
	                put("userNo", user.getUserNo());
	            }
	        });
	        return new ResponseEntity<>(message,  HttpStatus.CREATED);
	        
		}
		
	}
	
	@Operation(summary = "비밀번호 초기화", description = "아이디, 이름, 생일로 정보 확인후 비밀번호 초기화")
	@PostMapping("/find/password")
	public ResponseEntity<Message> getUserPassword(HttpSession session, @RequestBody @Valid User user) {
		System.out.println("::: getUserPassword :::"); 
		
		if(StringUtil.isBlank(user.getUserBirthdate())
		|| StringUtil.isBlank(user.getUserId())
		|| StringUtil.isBlank(user.getUserEmail())
		|| StringUtil.isBlank(user.getUserName())
		|| StringUtil.isBlank(user.getUserPw())) {
			
			message.setStatus(StatusEnum.BAD_REQUEST);
			message.setMessage("아이디, 이름, 생일, 이메일, 바꿀 비밀번호 필수 입력");
			return new ResponseEntity<>(message, headers, HttpStatus.UNAUTHORIZED);
			
		} 
		
		try {
			
			if(userService.findUserPassword(user) != 1) {
				throw new Exception();
			}
			
			message.setSuccessMessage();
			message.setMessage("비밀번호 변경완료");
			return new ResponseEntity<>(message, headers, HttpStatus.OK);
			
		} catch (Exception e) {
			message.setFailMessage();
			message.setMessage(e.getMessage());
			return new ResponseEntity<>(message, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	
	@Operation(summary = "유저 마일리지 내역 조회", description = "로그인시 사용 가능")
	@PostMapping("/milage")
	public ResponseEntity<Message> getUserMileageHistory(HttpSession session) {
		System.out.println("::: getUserMileageHistory :::"); 
		message.setFailMessage();
		User user = (User) session.getAttribute("loginUser");
		if(user != null) {
			
	        try {
	        	message.setStatus(StatusEnum.OK);
	        	message.setMessage("마일리지 수입/지출 내역 조회");
	        	message.setData(userService.getUserMileageList(user));
				
				return new ResponseEntity<>(message, headers, HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				message.setMessage(e.getMessage());
				return new ResponseEntity<>(message, headers, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	        
		} 
		
		message.setMessage("로그인 후 이용가능");
		return new ResponseEntity<>(message, headers, HttpStatus.UNAUTHORIZED);
		
		
		
		
	}

	
}

@Data
class Account {
	
	@NotNull(message = "아이디 입력 필수")
    @Pattern(regexp = "^[a-z_0-9]{1,12}$", message = "올바르지 않은 아이디 형식")
	@Schema(description = "아이디", example = "test", deprecated = false)
	private String id;
	
	@NotNull(message = "비밀번호 입력 필수")
	@Schema(description = "비밀번호", example = "test", deprecated = false)
	private String password;

}
