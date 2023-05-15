package com.planttech.service.Impl;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.planttech.domain.User;
import com.planttech.domain.UserMileage;
import com.planttech.mapper.UserMapper;
import com.planttech.service.UserService;
import com.planttech.util.IntUtil;
import com.planttech.util.UserUtil;


@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired private UserMapper userMapper;
	
	// 패스워드 인코더
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	@Override public PasswordEncoder passwordEncoder() {
		return this.passwordEncoder;
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
	
}
