package com.planttech.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.planttech.domain.user.User;
import com.planttech.domain.user.UserMileage;

public interface UserService {
	public User 	getUserByUserId(String userId);
	public User 	getUserByUserEmail(String userEmail); 	
	public int 		findUserPassword(User user); 
	
	public User 	verifyUser(User user); 
	public int 		addUser(User user); 
	
	public List<UserMileage>	getUserMileageList(User user);
	public int 					getUserTotalMileage(User user);
	public int					addUserMileage(UserMileage userMileage);
	
	
	public 
	PasswordEncoder passwordEncoder();
}
