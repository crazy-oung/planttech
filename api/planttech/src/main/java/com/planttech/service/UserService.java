package com.planttech.service;

import java.util.List;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.planttech.domain.plant.UserPlant;
import com.planttech.domain.shop.Product;
import com.planttech.domain.user.User;
import com.planttech.domain.user.UserMileage;
import com.planttech.domain.user.UserNotification;

public interface UserService {
	
	// 유저
	public User 	getUserByUserId(String userId);
	public User 	getUserByUserEmail(String userEmail); 	
	public int 		findUserPassword(User user); 
	public User 	verifyUser(User user); 
	public int 		addUser(User user); 
	public int 		modifyUser(User user, User userSession); 
	
	// user plant
	public List<UserPlant> getUserPlantList(User user);
	
	
	// 유저 마일리지
	public List<UserMileage>	getUserMileageList(User user);
	public int 					getUserTotalMileage(User user);
	public int					addUserMileage(UserMileage userMileage);
	
	// 유저 알림
	public int						addUserNotification(UserNotification userNotification);
	public int 						readUserNotification(UserNotification userNotification);
	public int 						removeUserNotification(UserNotification userNotification);
	public List<UserNotification>	getUserNotificationList(User user);
	
	// 유저 입찰 내역
	public List<Product> 	getUserProductList(User user, Map<String, Object> page);
	
	
	// 유저 패스워드 인코더
	public 
	PasswordEncoder passwordEncoder();
}
