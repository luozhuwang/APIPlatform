package com.xiaoniu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoniu.model.APIModel;

public interface APIMapper {

	//获取测试用例
	List<APIModel> getALLAPI(APIModel APImodel);
	//通过ID获取测试用例
	APIModel getAPI(Integer id);
	//通过ID删除测试用例
	Integer delAPI(Integer id);
	//添加测试用例
	Integer addAPI(@Param("apiName")String apiName,@Param("apiHost")String apiHost,@Param("apiUrl")String apiUrl,@Param("apiMethod")String apiMethod,@Param("apiHeaders")String apiHeaders
			,@Param("apiParams")String apiParams,@Param("apiStatus")Integer apiStatus,@Param("remark")String remark);
	//修改测试用例
	Integer updateAPI(@Param("id")Integer id,@Param("apiName")String apiName,@Param("apiHost")String apiHost,@Param("apiUrl")String apiUrl,@Param("apiMethod")String apiMethod,@Param("apiHeaders")String apiHeaders
			,@Param("apiParams")String apiParams,@Param("apiStatus")Integer apiStatus,@Param("remark")String remark);

}
