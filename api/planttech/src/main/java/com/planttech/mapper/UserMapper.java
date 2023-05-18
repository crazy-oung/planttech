package com.planttech.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.planttech.domain.plant.UserPlant;
import com.planttech.domain.user.User;
import com.planttech.domain.user.UserMileage;


@Mapper
public interface UserMapper {

	public User selectUserId(User user);
	public User selectUserByUserId(String userId);
	public User selectUserByUserEmail(String userEmail);
	
	public int insertUser(User user);
	public int updateUserPassword(User user);
	public UserPlant selectUserPlantList(User user);
	
	public List<UserMileage> selectUserMileageList(User user);
	public int selectUserTotalMileage(User user);
	public int insertUserMileage(UserMileage userMileage);
	
}
