package com.planttech.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.planttech.domain.User;
import com.planttech.mapper.UserMapper;
import com.planttech.service.UserService;
import com.planttech.util.IntUtil;
import com.planttech.util.UserUtil;


@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired private UserMapper userMapper;
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	
	@Override
	public User verifyUser(User user) {
		System.out.println("::: - verifyUser :::");
		return userMapper.selectUserId(user);
	}
	
	@Override
	public User getUserByUserId(String userId) {
		System.out.println("::: - getUserByUserId :::");
		return userMapper.selectUserByUserId(userId);
	}
	
	@Override
	public String getUserIdForCheck(User user) {
		System.out.println("::: - getUserIdForCheck :::");
		
//		if(userMapper.selectUserIdForCheck(user) == null) {
//			return null;
//		}
		
//		String randNum = sendCodeToMail(user);
		String randNum = "99999";
		return randNum;
	}

	@Override
	public int addUser(User user) {
		System.out.println("::: - addUser :::");
		user.setUserPw(passwordEncoder.encode(user.getUserPw()));
		
		return userMapper.insertUser(user);
	}

	
	// 패스워드 인코더
	@Override
	public PasswordEncoder passwordEncoder() {
		return this.passwordEncoder;
	}

}
