package com.planttech.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.planttech.domain.user.User;
import com.planttech.domain.user.UserMileage;
import com.planttech.domain.user.UserNotification;
import com.planttech.mapper.UserMapper;
import com.planttech.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	// ==== 패스워드 인코더  ================================================================================
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	@Override public PasswordEncoder passwordEncoder() {
		return this.passwordEncoder;
	}
	
	
	// ==== 유저 ================================================================================
	@Autowired private UserMapper userMapper;

	@Override
	public User getUserByUserId(String userId) {
		System.out.println("::: - getUserByUserId :::");
		return userMapper.selectUserByUserId(userId);
	}
	
	@Override
	public User getUserByUserEmail(String userEmail) {
		System.out.println("::: - getUserByUserEmail :::");
		return userMapper.selectUserByUserEmail(userEmail);
		
	}
	
	@Override
	public int findUserPassword(User user) {
		System.out.println("::: - findUserPassword :::");
		
		user.setUserPw(passwordEncoder.encode(user.getUserPw()));
		return userMapper.updateUserPassword(user);
	}
	
	@Override
	public int addUser(User user) {
		System.out.println("::: - addUser :::");
		
		try {
			user.setUserPw(passwordEncoder.encode(user.getUserPw()));

			if (userMapper.insertUser(user) != 0) {
				UserMileage userMilage = new UserMileage();
				userMilage.setUserMileageContent("웰컴 기프트");
				userMilage.setUserMileageValue(1000);
				userMilage.setUserNo(user.getUserNo());
				
				return user.getUserNo();
			}
			return user.getUserNo();
		} catch (Exception e) {
			e.printStackTrace();

			if (e instanceof DuplicateKeyException) {
				return -1;
			} else {
				return 0;
			}
			
		}
	}
	
	@Override
	public User verifyUser(User user) {
		System.out.println("::: - verifyUser :::");
		String userPw = user.getUserPw();
		
		user = userMapper.selectUserByUserId(user.getUserId());
		
		if (passwordEncoder.matches(userPw, user.getUserPw())) {
			user.setUserPw("PROTECTED");
			user.setUserMileage(userMapper.selectUserTotalMileage(user));
			
			return user;
		}
		
		return null;
	}
	
	
	// ==== 유저 마일리지 ================================================================================
	@Override
	public List<UserMileage> getUserMileageList(User user) {
		return userMapper.selectUserMileageList(user);
	}

	@Override
	public int getUserTotalMileage(User user) {
		return userMapper.selectUserTotalMileage(user);
	}

	@Override
	public int addUserMileage(UserMileage userMileage) {
		return  userMapper.insertUserMileage(userMileage);
	}

	
	// ==== 유저 알림 ================================================================================
	@Override
	public List<UserNotification> getUserNotificationList(User user) {
		return userMapper.selectUserNotificationList(user);
	}
	
	@Override
	public int addUserNotification(UserNotification userNotification) {
		return userMapper.insertUserNotification(userNotification);
	}

	@Override
	public int readUserNotification(UserNotification userNotification) {
		return userMapper.updateUserNotification(userNotification);
	}

	@Override
	public int removeUserNotification(UserNotification userNotification) {
		userNotification.setUserNotificationActive(2);
		return userMapper.updateUserNotification(userNotification);
	}

	
	
	
}
