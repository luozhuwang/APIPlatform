package com.xiaoniu.service;

import java.util.List;
import com.xiaoniu.model.EnvVarModel;

public interface EnvVarService {
	//添加变量
	Integer addVar(String varName,String varValue,Integer varType);
	//修改变量
	Integer updateVar(Integer id,String varName,String varValue);
	//删除变量
	Integer delVar(Integer id);
	//获取全部变量
	List<EnvVarModel> getAllVar(EnvVarModel envVarModel);
	//通过ID获取变量
	EnvVarModel getVar(Integer id);
	//通过变量名获取变量
	EnvVarModel getVarByName(String varName);
}
