package com.planttech.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.planttech.domain.plant.UserPlant;
import com.planttech.domain.user.User;
import com.planttech.domain.user.UserMileage;
import com.planttech.domain.user.UserNotification;


@Mapper
public interface UserMapper {
	
	// 유저
	public int 	insertUser(User user);
	public int 	updateUserPassword(User user);
	public User selectUserId(User user);
	public User selectUserByUserId(String userId);
	public User selectUserByUserEmail(String userEmail);
	
	// 유저 식물
	public UserPlant selectUserPlantList(User user);
	
	// 유저 마일리지
	public int insertUserMileage(UserMileage userMileage);
	public int selectUserTotalMileage(User user); // 유저 마일리지 갱신용
	public List<UserMileage> selectUserMileageList(User user);
	
	// 유저 알림
	public int insertUserNotification(UserNotification userNotification);
	public int updateUserNotification(UserNotification userNotification); // 유저 알림 확인 처리용
	public List<UserNotification> selectUserNotificationList(User user);
	
}
