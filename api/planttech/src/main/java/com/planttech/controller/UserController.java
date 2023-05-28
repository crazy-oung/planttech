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

@Tag(name = "User", description = "유저")
@RestController
@RequestMapping("/user")
@Validated
public class UserController {
	
	@Autowired private UserService userService;
	
    // ==== 유저 ================================================================================
	@Operation(summary = "회원가입", description = "신규 유저 정보를 등록합니다.")
	@PostMapping("/register")
	public ResponseEntity addUser(@RequestBody @Valid	 User user) {
		switch (userService.addUser(user)) {
		case 0: {
			return new ResponseEntity<>(new ErrorMessage("0","서버 오류"),  HttpStatus.INTERNAL_SERVER_ERROR);
		} case -1:{
			return new ResponseEntity<>(new ErrorMessage("0","중복 회원 존재"),  HttpStatus.BAD_REQUEST);
		}
		default:
	        return new ResponseEntity<>(new Message(HttpStatus.CREATED,"회원가입 완료",user.getUserNo()),  HttpStatus.CREATED);
		}
		
	}
	
	@Operation(summary = "이메일 중복 검사", description = "이메일 중복 검사를 진행합니다.")
	@GetMapping("/check/email")
	public ResponseEntity getUserEmail(@RequestParam String userEmail) {
		if (!StringUtil.isEmail(userEmail)) {
			return new ResponseEntity<>(new ErrorMessage(ErrorCodeEnum.PATTERN.getCode(), "올바르지 않은 이메일 형식입니다."), HttpStatus.BAD_REQUEST);
		}
		if (userService.getUserByUserEmail(userEmail) != null) {
			return new ResponseEntity<>(new ErrorMessage("0", "이미 등록된 이메일입니다."), HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(new Message(HttpStatus.OK,"사용 가능한 이메일입니다.",userEmail), HttpStatus.OK);
	}
	
	
	@Operation(summary = "아이디 중복 검사", description = "아이디 중복 검사를 결과를 반환합니다.")
	@GetMapping("/check/id")
	public ResponseEntity getUserId(@RequestParam String userId) {
		if(userService.getUserByUserId(userId) != null) {
			return new ResponseEntity<>(new ErrorMessage("0", "이미 존재하는 아이디입니다."), HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(new Message(HttpStatus.OK,"사용 가능한 아이디입니다.",userId), HttpStatus.OK);
	}

	@Operation(summary = "유저 로그인", description = "아이디 패스워드를 받아 로그인 처리(세션 생성)합니다.")
	@PostMapping("/login")
	public ResponseEntity addSession(HttpSession session, @RequestBody @Valid Account account) {
		if(UserUtil.isUser(session)) {
			return new ResponseEntity<>(new ErrorMessage("0", "이미 로그인한 상태입니다."), HttpStatus.BAD_REQUEST);
		}
		
		User user = new User();
		user.setUserId(account.getId());
		user.setUserPw(account.getPassword());
		User loginUser = userService.verifyUser(user);
		if(loginUser != null) {
			UserUtil.setUser(session, loginUser);
			return new ResponseEntity<>(new Message(HttpStatus.OK, "로그인 성공", loginUser), HttpStatus.OK);
		}
		
        return new ResponseEntity<>(new ErrorMessage("0","해당하는 유저 정보가 없습니다."), HttpStatus.UNAUTHORIZED);
	}
	
	@Operation(summary = "비밀번호 초기화", description = "아이디, 이름, 생일로 정보 확인후 비밀번호 초기화")
	@PostMapping("/find/password")
	public ResponseEntity findUserPassword(HttpSession session, @RequestBody @Valid User user) {
			if(userService.findUserPassword(user) != 1) {
				return new ResponseEntity<>(new ErrorMessage("0","유저를 찾을 수 없습니다."), HttpStatus.UNAUTHORIZED);
			}
			return new ResponseEntity<>(new SuccessMessage("비밀번호 변경완료"), HttpStatus.OK);
	}
	
	@Operation(summary = "내정보 조회", description = "로그인 되어 있어야 가능합니다.")
	@GetMapping("/me")
	public ResponseEntity getMyInfo(HttpSession session) throws LoginException {
		if(!UserUtil.isUser(session)) throw new LoginException();
		UserUtil.getUser(session).setUserMileage(userService.getUserTotalMileage(UserUtil.getUser(session)));
		return new ResponseEntity<>(UserUtil.getUser(session), HttpStatus.OK);
	}
	
	// ****************************** 이 지점 북마크 ************************************************************************************************************
	@Operation(summary = "내정보 수정", description = "로그인 되어 있어야 가능합니다. ")
	@PutMapping("/me")
	public ResponseEntity editMyInfo( 	@Schema(  example 		= "{\n"
																+ "  \"userEmail\": \"test@email.com\",\n"
																+ "  \"userName\": \"김아무개\",\n"
																+ "  \"userNickname\": \"뿡뿡이\",\n"
																+ "  \"userPw\": \"test\",\n"
																+ "  \"userBirthdate\": \"1999-12-01\"\n"
																+ "}" 
												, description 	= "수정할 유저 정보 (변경사항이 없는 값은 그대로 돌려주면 됩니다." ) 
										@RequestBody User user
									  , HttpSession session ) throws LoginException, PasswordException {
		if(!UserUtil.isUser(session)) throw new LoginException();
		userService.modifyUser(user, UserUtil.getUser(session));
		
		if(user.getUserNo() == -1) throw new PasswordException();
		return new ResponseEntity<>(new SuccessMessage("회원 정보 수정 완료"), HttpStatus.OK);
	}
	
	

	@Operation(summary = "로그아웃", description = "세션을 삭제하여 로그아웃합니다.")
	@GetMapping("/logout")
	public ResponseEntity removeSession(HttpSession session) throws LoginException {
		if (!UserUtil.isLoginned(session)) throw new LoginException();
		
		session.invalidate();
		return new ResponseEntity<>(new SuccessMessage("로그아웃 완료"),  HttpStatus.OK);
	}
	
	
	// ==== 유저 마일리지 ================================================================================
//	@Operation(summary = "유저 마일리지 내역 조회", description = "로그인시 사용 가능")
//	@GetMapping("/milage")
//	public ResponseEntity getUserMileageHistory(HttpSession session) throws LoginException {
//		if(!UserUtil.isUser(session)) throw new LoginException();
//		return new ResponseEntity<>(new Message(HttpStatus.OK,"내 마일리지 내역", userService.getUserMileageList(UserUtil.getUser(session))), HttpStatus.OK);
//	}
	
	
	// ==== 유저 알림 ================================================================================
	@Operation(summary = "유저 알림 조회", description = "로그인시 사용 가능")
	@GetMapping("/notification")
	public ResponseEntity getUserNotification(HttpSession session) throws LoginException {
		if(!UserUtil.isUser(session)) throw new LoginException();
		return new ResponseEntity<>(userService.getUserNotificationList(UserUtil.getUser(session)), HttpStatus.OK);
	}
	
	@Operation(summary = "유저 알림 추가", description = "식물 환경 정보/마일리지/ 구매 입찰 정보에 대한 알림을 등록합니다.")
	@PostMapping("/notification")
	public ResponseEntity addUserNotification(HttpSession session, @RequestBody @Valid UserNotification userNotification) throws LoginException {
		if(!UserUtil.isUser(session)) throw new LoginException();
		userNotification.setUserNo(UserUtil.getUser(session).getUserNo());
		userService.addUserNotification(userNotification);
		return new ResponseEntity<>(userNotification.getUserNotificationNo(),  HttpStatus.CREATED);
		
	}
	
	@Operation(summary = "유저 알림 업데이트", description = "알림 확인에 대한 정보를 갱신합니다. (삭제/확인 처리 용도)")
	@PutMapping("/notification")
	public ResponseEntity readUserNotification(HttpSession session, @RequestBody @Valid UserNotification userNotification) throws LoginException {
		if(!UserUtil.isUser(session)) throw new LoginException();
		userNotification.setUserNo(UserUtil.getUser(session).getUserNo());
		userService.readUserNotification(userNotification);
		return new ResponseEntity<>(new Message(HttpStatus.CREATED,"알림 업데이트 완료", userNotification),  HttpStatus.CREATED);
		
	}
	
	@Operation(summary = "유저 알림 삭제 처리", description = "알림 리스트에서 뜨지않도록 가림처리 합니다.")
	@DeleteMapping("/notification")
	public ResponseEntity removeUserNotification(HttpSession session, @RequestBody @Valid UserNotification userNotification) throws LoginException {
		if(!UserUtil.isUser(session)) throw new LoginException();
		userNotification.setUserNo(UserUtil.getUser(session).getUserNo());
		userService.removeUserNotification(userNotification);
		return new ResponseEntity<>(new Message(HttpStatus.CREATED,"알림 업데이트 완료", userNotification.getUserMileageNo()),  HttpStatus.CREATED);
	}
	
	// ==== 유저 입찰 내역 ================================================================================
	@Operation(summary = "유저 입찰 내역 조회", description = "로그인시 사용 가능")
	@GetMapping("/my/bid")
	public ResponseEntity getUserBidList( 	@RequestParam @Schema(description = "페이지 번호(ex. 2에 pageSize 10이면, 2페이지 조회. 즉, 11번째 정보부터 10개가 출력되는 셈)", example="0") int beginPage, 
											@RequestParam @Schema(description = "페이지 당 조회할 데이터 개수", example="10") int  pageSize, 
											HttpSession session ) throws LoginException {
		if(!UserUtil.isUser(session)) throw new LoginException();
		Map<String, Object> page = new HashMap<String, Object>(){{
			put("beginPage", beginPage);
			put("pageSize", pageSize);
			put("userNo", UserUtil.getUser(session).getUserNo());
		}};
		return new ResponseEntity<>(userService.getUserProductList(UserUtil.getUser(session), page), HttpStatus.OK);
	}
	
	// ==== 유저 화분 소유 내역 ================================================================================
	@Operation(summary = "유저 소유 화분 조회", description = "로그인시 사용 가능")
	@GetMapping("/my/plant")
	public ResponseEntity getUserPlantList (HttpSession session) throws LoginException {
		if(!UserUtil.isUser(session)) throw new LoginException();
		return new ResponseEntity<>(userService.getUserPlantList(UserUtil.getUser(session)), HttpStatus.OK);
	}
	
	
	
	// ==== 유저 북마킹 ================================================================================
//	@Operation(summary = "관심목록 등록", description = "유저만 관심목록 이용 가능")
//	@PostMapping("/bookmark")
//	public ResponseEntity getUserBidList( @RequestBody Product product,HttpSession session ) throws LoginException {
//		if(!UserUtil.isUser(session)) throw new LoginException();
//		
//		return new ResponseEntity<>( product, HttpStatus.OK);
//	}
	
	
	
}

@Data
class Account {
	
	@NotBlank(message = "아이디 입력 필수")
    @Pattern(regexp = "^[a-z_0-9]{1,12}$", message = "올바르지 않은 아이디 형식")
	@Schema(description = "아이디", example = "test", deprecated = false)
	private String id;
	
	@NotBlank(message = "비밀번호 입력 필수")
	@Schema(description = "비밀번호", example = "test", deprecated = false)
	private String password;

}
