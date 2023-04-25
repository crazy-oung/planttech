package com.planttech.mapper;



import org.apache.ibatis.annotations.Mapper;

import com.planttech.domain.User;


@Mapper
public interface UserMapper {
	// 유저 로그인
	public User selectUserId(User user);
	public User selectUserByUserId(String userId);
	public User selectUserIdForCheck(User user);
	
	public int insertUser(User user);
	
}
