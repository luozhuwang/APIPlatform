package com.xiaoniu.serviceImp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoniu.mapper.EnvVarMapper;
import com.xiaoniu.model.EnvVarModel;
import com.xiaoniu.service.EnvVarService;

@Service
public class EnvVarServiceImp implements EnvVarService{
	private Logger logger = LoggerFactory.getLogger(EnvVarServiceImp.class); 
	
	@Autowired
	private EnvVarMapper envVarMapper;
	
	@Override
	public Integer addVar(String varName,  String varValue, Integer varType) {
		logger.info("新增变量");
		int count=envVarMapper.addVar(varName, varValue, varType);
		return count;
	}

	@Override
	public Integer updateVar(Integer id, String varName, String varValue) {
		logger.info("修改变量<id:"+id+"varName:"+varName+">");
		int count=envVarMapper.updateVar(id, varName, varValue);
		return count;
	}

	@Override
	public Integer delVar(Integer id) {
		logger.info("删除变量<"+id+">");
		int count=envVarMapper.delVar(id);
		return count;
	}

	@Override
	public List<EnvVarModel> getAllVar(EnvVarModel envVarModel) {
		logger.info("查询变量");
		List<EnvVarModel> varList=envVarMapper.getAllVar(envVarModel);			
		if(varList!=null){			
			return varList;
		}else{
			logger.info("查询变量结果为空");
			return null;
		}
	}

	@Override
	public EnvVarModel getVar(Integer id) {
		logger.info("变量<"+id+">查询");
		EnvVarModel var=envVarMapper.getVar(id);
		if(var!=null){			
			return var;
		}else{
			logger.info("查询变量结果为空");
			return null;
		}
	}

	@Override
	public EnvVarModel getVarByName(String varName) {
		logger.info("变量名<"+varName+">查询");
		EnvVarModel var=envVarMapper.getVarByName(varName);
		if(var!=null){			
			return var;
		}else{
			logger.info("查询变量结果为空");
			return null;
		}
	}	
	
}
