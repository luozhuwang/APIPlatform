package com.xiaoniu.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoniu.model.EnvInfoModel;

public interface EnvInfoMapper {
	Integer addEnv(@Param("envTitle")String envTitle,@Param("envDomain")String envDomain,@Param("envIp")String envIp);

	Integer delEnv(Integer id);
	
	EnvInfoModel	getEnv(Integer id);	
	
	EnvInfoModel 	getServer(EnvInfoModel envModel);
	
	List<EnvInfoModel>	getAllEnv(EnvInfoModel envModel);
	
	List<EnvInfoModel>	getEnvALLTitle();
}
