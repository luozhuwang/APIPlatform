package com.xiaoniu.service;

import java.util.List;

import com.xiaoniu.model.APIModel;

public interface APIService {

	//获取所有接口
	List<APIModel> getALLAPI(APIModel APImodel);
	//通过ID获取接口
	APIModel getAPI(Integer id);
	//通过ID删除接口
	Integer delAPI(Integer id);
	//添加接口
	Integer addAPI(String apiName,String apiHost,String apiUrl,String apiMethod,String apiHeaders,String apiParams,Integer apiStatus,String remark);
	//修改接口
	Integer updateAPI(Integer id,String apiName,String apiHost,String apiUrl,String apiMethod,String apiHeaders,String apiParams,Integer apiStatus,String remark);
	

}
