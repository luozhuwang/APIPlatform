package com.xiaoniu.serviceImp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoniu.mapper.TempVarMapper;
import com.xiaoniu.model.EnvVarModel;
import com.xiaoniu.service.TempVarService;

@Service
public class TempVarServiceImp implements TempVarService{
	private Logger logger=LoggerFactory.getLogger(TempVarServiceImp.class);
	
	@Autowired
	private TempVarMapper tempVarMapper;

	@Override
	public Integer addTempVar(String varName,String varRule, String varValue) {
		EnvVarModel var=tempVarMapper.getTempVarByName(varName);
		if(var !=null){
			logger.info("临时变量<"+varName+">已存在，勿重复添加");
			return null;
		}else{
			logger.info("添加临时变量<"+varName+":"+varValue+">");
			int count=tempVarMapper.addTempVar(varName,varRule, varValue);
			return count;
		}
	}

	@Override
	public EnvVarModel getTempVarByName(String varName) {
		EnvVarModel var=tempVarMapper.getTempVarByName(varName);
		if(var!=null){			
			return var;
		}else{
			logger.info("未查询到临时变量");
			return null;
		}
	}

	@Override
	public void truncateVarTemp() {
		logger.info("清空临时变量表");
		tempVarMapper.truncateVarTemp();
		
	}
	
}
