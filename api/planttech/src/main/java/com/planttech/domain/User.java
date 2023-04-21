package com.planttech.domain;


import java.sql.Timestamp;


import lombok.Data;

@Data
public class User {
	private int userNo;
	
	private String userId;
	private String userEmail;
	private String userName;
	private String userNickname;
	private String userPw;
	private String userBirthdate;
	
	private String userCreatetime;
	private String userModifytime;
	
	private String userType;
}
