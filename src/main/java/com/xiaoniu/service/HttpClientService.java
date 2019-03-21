package com.xiaoniu.service;

import com.xiaoniu.httpclient.HttpResult;

public interface HttpClientService {

	//发送http请求
	HttpResult doSend(String runEnv,String method,String URL,String data);
	
//	void DNSResolver(String runEnv);
}
