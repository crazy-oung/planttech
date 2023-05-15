package com.planttech.domain;


import java.sql.Timestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class User {
	@Schema(description = "유저 고유번호", example = "0", deprecated = false)
	private int userNo;
	
	@NotNull(message = "아이디 입력 필수")
    @Pattern(regexp = "^[a-z_0-9]{1,12}$", message = "올바르지 않은 아이디 형식")
	@Schema(description = "유저 아이디", example = "test", deprecated = false)
	private String userId;
	@Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "올바르지 않은 이메일 형식")
	@Schema(description = "유저 이메일", example = "test@email.com", deprecated = false)
	private String userEmail;
	@Size(max = 10, message = "이름 30자 이내")
	@Schema(description = "유저 이름", example = "김아무개", deprecated = false)
	private String userName;
	@Size(max = 10, message = "닉네임은 20자 이내")
	@Schema(description = "유저 닉네임", example = "뿡뿡이", deprecated = false)
	private String userNickname;
	@NotNull(message = "비밀번호 입력 필수")
	@Schema(description = "유저 비밀번호", example = "test", deprecated = false)
	private String userPw;
	@Schema(description = "유저 생일", example = "1999-12-01", deprecated = false)
	private String userBirthdate;
	
	@Schema(description = "유저 마일리지", example = "1000", deprecated = true)
	private int userMileage;
	
	
	
	
	@Schema(description = "유저 가입일시", deprecated = true)
	private Timestamp userCreatetime;
	@Schema(description = "유저 정보 수정일시", deprecated = true)
	private Timestamp userModifytime;
	
	@Schema(description = "유저 타입 (유저/관리자)",deprecated = true)
	private String userType;
	

    
}
