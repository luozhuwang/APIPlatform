package com.xiaoniu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoniu.model.UserModel;


public interface UserMapper {
	UserModel checkUser(@Param("user")String user);
	
	UserModel findByUsername(@Param("user")String user,@Param("userPwd")String userPwd);
	
	Integer registerUser(@Param("user")String user,@Param("userPwd")String userPwd,@Param("userName")String userName,@Param("userIp")String userIp,@Param("email")String email );
	
	List<UserModel> getAllUser(UserModel userModel);
	
	UserModel getUserById(Integer id);
	
	Integer delUser(Integer id);
	
	Integer UpdateUser(@Param("id")Integer id,@Param("userPwd")String userPwd,@Param("userName")String userName,@Param("email")String email);
}
