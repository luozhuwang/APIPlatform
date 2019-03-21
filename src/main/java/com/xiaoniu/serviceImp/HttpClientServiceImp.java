package com.xiaoniu.serviceImp;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.alibaba.dcm.DnsCacheManipulator;
import com.alibaba.fastjson.JSON;
import com.xiaoniu.httpclient.HttpClientOperate;
import com.xiaoniu.httpclient.HttpResult;
import com.xiaoniu.mapper.EnvInfoMapper;
import com.xiaoniu.model.EnvInfoModel;
import com.xiaoniu.service.HttpClientService;

@Controller
public class HttpClientServiceImp implements HttpClientService{
	
	private Logger logger =LoggerFactory.getLogger(HttpClientServiceImp.class);
	
	@Autowired
	private HttpClientOperate httpClientOperate;
	
	@Autowired
	private EnvInfoMapper envInfoMapper;
	
	@Override
	public HttpResult doSend(String runEnv, String method, String URL,String param) {
		logger.info("发送Http请求");
		long beginTime=System.currentTimeMillis();
		HttpResult result=null;
		Map<String,Object> map=null;
		 try {
			 DNSResolver(runEnv);			 
			 	if(method.equals("get")){
					if(param ==null || param.equals("")){
						 result=httpClientOperate.doGet(URL);						
					}else{
						//将json格式 的String转成Map:{"online_date":"2018-09-20"}
						map= (Map<String,Object>)JSON.parse(param); 										 
						result=httpClientOperate.doGet(URL, map);				 
					} 
			 	}else if(method.equals("post")){
			 		//将json格式 的String转成Map:{"online_date":"2018-09-20"}
			 		map = (Map<String,Object>)JSON.parse(param); 
			 		result=httpClientOperate.doPost(URL, map);
				}else if(method.equals("post1")){
					//发送post加密请求
					 result=httpClientOperate.doPostEncrypt(URL, param);
				}else if(method.equals("postfile")){
					//上传文件
					map= (Map<String,Object>)JSON.parse(param); 
					 result=httpClientOperate.httpFile(URL, map);
				}				
			}catch (ClientProtocolException e) {
				logger.error("ClientProtocol异常:"+e.getMessage());
				e.printStackTrace();
			}catch (IOException e) {
				logger.error("IO异常"+e.getMessage());
				e.printStackTrace();
			}catch (URISyntaxException e) {
				logger.error("URISyntax异常"+e.getMessage());
				e.printStackTrace();
			}
		 long endTime=System.currentTimeMillis();
		 result.setBeginTime(beginTime);
		 result.setEndTime(endTime);
		return result;	
	}

	public void DNSResolver(String runEnv) {
		logger.info("发送请求前，先获取DNS");	
		DnsCacheManipulator.clearDnsCache();
		EnvInfoModel envInfo=new EnvInfoModel();
	    envInfo.setEnvTitle(runEnv);    	
	    List<EnvInfoModel>	envList=	envInfoMapper.getAllEnv(envInfo);   	
	    for(EnvInfoModel env:envList){  
	           DnsCacheManipulator.setDnsCache(env.getEnvDomain(), env.getEnvIp());			
	    }

	}

}
