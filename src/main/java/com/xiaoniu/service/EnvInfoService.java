package com.xiaoniu.service;

import java.util.List;

import com.xiaoniu.model.EnvInfoModel;

public interface EnvInfoService {

	Integer addEnv(String envTitle,String envDomain,String envIp);

	Integer delEnv(Integer id);
	
	EnvInfoModel	getEnv(Integer id);
	
	EnvInfoModel 	getServer(EnvInfoModel envModel);
	
	List<EnvInfoModel>	getAllEnv(EnvInfoModel envModel);
	
	List<EnvInfoModel>	getEnvALLTitle();
}
