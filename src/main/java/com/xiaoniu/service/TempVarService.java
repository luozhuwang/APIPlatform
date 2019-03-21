package com.xiaoniu.service;

import com.xiaoniu.model.EnvVarModel;

public interface TempVarService {
	//添加变量
	Integer addTempVar(String varName,String varRule,String varValue);
	//通过变量名获取变量
	EnvVarModel getTempVarByName(String varName);
	//清空临时表
	void truncateVarTemp();
}
