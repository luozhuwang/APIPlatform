package com.xiaoniu.serviceImp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoniu.mapper.EnvInfoMapper;
import com.xiaoniu.model.EnvInfoModel;
import com.xiaoniu.service.EnvInfoService;

@Service
public class EnvInfoServiceImp implements EnvInfoService{
	private Logger logger=LoggerFactory.getLogger(EnvInfoServiceImp.class);

	@Autowired
	private EnvInfoMapper envInfoMapper;
	
	@Override
	public Integer addEnv(String envTitle, String envDomain, String envIp) {
		logger.info("新增接口环境信息");
		int count=envInfoMapper.addEnv(envTitle, envDomain, envIp);
		return count;
	}

	@Override
	public Integer delEnv(Integer id) {
		logger.info("删除id<"+id+">环境信息");	
		int count=envInfoMapper.delEnv(id);
		return count;
	}

	@Override
	public EnvInfoModel getEnv(Integer id) {
		logger.info("跟据id<"+id+">查询环境信息");		
		EnvInfoModel envInfo=envInfoMapper.getEnv(id);
		return envInfo;
	}

	@Override
	public List<EnvInfoModel> getAllEnv(EnvInfoModel envModel) {
		if(envModel !=null){			
			logger.info("跟据标题<"+envModel.getEnvTitle()+">查询环境信息");
		}
		List<EnvInfoModel> envInfoList=envInfoMapper.getAllEnv(envModel);
		if(envInfoList !=null){
			return envInfoList;
		}else{	
			logger.info("获取环境信息为空");			
			return null;
		}
		
	}

	@Override
	public List<EnvInfoModel> getEnvALLTitle() {
		List<EnvInfoModel> envInfoList=envInfoMapper.getEnvALLTitle();
		if(envInfoList !=null){
			return envInfoList;
		}else{	
			logger.info("获取环境标题为空");			
			return null;
		}
	}

	@Override
	public EnvInfoModel getServer(EnvInfoModel envModel) {
		if(envModel !=null){				
			logger.info("跟据标题<"+envModel.getEnvTitle()+">查询APP/WEB host");
			EnvInfoModel envHost=envInfoMapper.getServer(envModel);
			return envHost;
		}else{
			logger.info("获取环境信息为空");
			return null;
		}
	}

}
