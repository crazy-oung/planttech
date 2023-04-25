package com.planttech.domain;


import java.sql.Timestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class User {
	@Schema(description = "유저 고유번호", example = "0", deprecated = false)
	private int userNo;
	
	@Schema(description = "유저 아이디", example = "test", deprecated = false)
	private String userId;
	@Schema(description = "유저 이메일", example = "test@email.com", deprecated = false)
	private String userEmail;
	@Schema(description = "유저 이름", example = "김아무개", deprecated = false)
	private String userName;
	@Schema(description = "유저 닉네임", example = "뿡뿡이", deprecated = false)
	private String userNickname;
	@Schema(description = "유저 비밀번호", example = "test", deprecated = false)
	private String userPw;
	@Schema(description = "유저 생", example = "1999-12-01", deprecated = false)
	private String userBirthdate;
	
	@Schema(description = "유저 가입일시", deprecated = true)
	private Timestamp userCreatetime;
	@Schema(description = "유저 정보 수정일시", deprecated = true)
	private Timestamp userModifytime;
	@Schema(description = "유저 타입 (유저/관리자)",deprecated = true)
	private String userType;
}
