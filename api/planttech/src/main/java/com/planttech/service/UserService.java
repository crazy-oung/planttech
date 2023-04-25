package com.planttech.service;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.planttech.domain.User;

public interface UserService {
	public User verifyUser(User user); 
	public User getUserByUserId(String userId);
	public String getUserIdForCheck(User user); 
	
	public int addUser(User user); 
	
	public PasswordEncoder passwordEncoder();
	
}
