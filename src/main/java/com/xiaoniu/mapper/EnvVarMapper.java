package com.xiaoniu.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.xiaoniu.model.EnvVarModel;

public interface EnvVarMapper {
	//添加变量
	Integer addVar(@Param("varName")String varName,@Param("varValue")String varValue,@Param("varType")Integer varType);
	//修改变量
	Integer updateVar(@Param("id")Integer id,@Param("varName")String varName,@Param("varValue")String varValue);
	//删除变量
	Integer delVar(@Param("id")Integer id);
	//获取全部变量
	List<EnvVarModel> getAllVar(EnvVarModel envVarModel);
	//通过ID获取变量
	EnvVarModel getVar(Integer id);
	//通过变量名获取变量
	EnvVarModel getVarByName(String varName);	
}
