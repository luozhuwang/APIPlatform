package com.xiaoniu.service;


import java.util.List;
import com.xiaoniu.model.UserModel;

public interface UserService {
	
	UserModel findByUsername(String user,String userPwd);
	
	Integer registerUser(String user,String userPwd,String userName,String userIp,String email );
	
	List<UserModel> getAllUser(UserModel userModel);
	
	UserModel getUserById(Integer id);
	
	Integer delUser(Integer id);
	
	Integer UpdateUser(Integer id,String userPwd,String userName,String email);
}
