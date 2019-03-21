package com.xiaoniu.service;

public interface HandleService {
	//参数替换
	String replaceParam(String requestData);
	//随机生成数据
	String tempVar(String requestData,String reg);
	// 替换APP/WEB  Server host
	String ServerHost(String requestData,String runEnv);
	//结果验证
	void resultAssert(String AssertContent,Integer statusCode,String response);
	//数据提取
	void paramExtraction(String Params,String response);
	
	
}
