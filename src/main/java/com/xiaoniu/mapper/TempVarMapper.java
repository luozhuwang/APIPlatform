package com.xiaoniu.mapper;

import org.apache.ibatis.annotations.Param;

import com.xiaoniu.model.EnvVarModel;

public interface TempVarMapper {
	//添加变量
	Integer addTempVar(@Param("varName")String varName,@Param("varRule")String varRule,@Param("varValue")String varValue);
	//通过变量名获取变量
	EnvVarModel getTempVarByName(String varName);
	//清空临时表
	void truncateVarTemp();
}
