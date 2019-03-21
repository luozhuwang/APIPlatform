package com.xiaoniu.serviceImp;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoniu.mapper.UserMapper;
import com.xiaoniu.model.UserModel;
import com.xiaoniu.service.UserService;

@Service
public class UserServiceImp implements UserService{
	private Logger logger=LoggerFactory.getLogger(UserServiceImp.class);
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public UserModel findByUsername(String user, String userPwd) {
		UserModel userModel=userMapper.findByUsername(user, userPwd);
		if(userModel!=null){			
			return userModel;
		}else{
			logger.info("未查询到此用户<"+user+">和密码");
			return null;
		}
	}

	@Override
	public Integer registerUser(String user, String userPwd, String userName,String userIp, String email) {
		int count=0;
		UserModel userModel=userMapper.checkUser(user);
		if(userModel!=null){			
			logger.info("用户已存在");
			count=-1;
		}else{
			count=userMapper.registerUser(user, userPwd, userName, userIp, email);		
		}
		return count;
	}

	@Override
	public List<UserModel> getAllUser(UserModel userModel) {
		logger.info("获取所有用户");
		List<UserModel> UserList=userMapper.getAllUser(userModel);
		if(UserList!=null){			
			return UserList;
		}else{
			logger.info("获取用户为空");
			return null;
		}		
	}

	@Override
	public UserModel getUserById(Integer id) {
		logger.info("跟据id<"+id+">查询用户");	
		UserModel User=userMapper.getUserById(id);
		if(User!=null){			
			return User;
		}else{
			logger.info("未查询到此用户");
			return null;
		}
	}

	@Override
	public Integer delUser(Integer id) {
		logger.info("删除用户id<"+id+">");	
		int count=userMapper.delUser(id);
		return count;
	}

	@Override
	public Integer UpdateUser(Integer id, String userPwd, String userName, String email) {
		logger.info("修改用户id<"+id+">");	
		int count=userMapper.UpdateUser(id, userPwd, userName, email);
		return count;

	}

}
