package com.xiaoniu.serviceImp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoniu.mapper.APICaseNewMapper;
import com.xiaoniu.mapper.APICaseRelationMapper;
import com.xiaoniu.mapper.CaseSetMapper;
import com.xiaoniu.model.APICaseNew;
import com.xiaoniu.model.CaseSetModel;
import com.xiaoniu.service.APICaseNewService;

@Service
public class APICaseNewServiceImp implements APICaseNewService{
	private Logger logger =LoggerFactory.getLogger(APICaseNewServiceImp.class);
			
	@Autowired
	private APICaseNewMapper APIcaseNewMapper;
	
	@Autowired
	private APICaseRelationMapper APIcaseRelationMapper;

	@Autowired
	private CaseSetMapper caseSetMapper;
	
	@Override
	public List<APICaseNew> getALLAPICaseNew(APICaseNew APIcaseNew) {
		logger.info("获取所有测试用例");
		List<APICaseNew>  APIcaseNewList=APIcaseNewMapper.getALLAPICaseNew(APIcaseNew);
		if(APIcaseNewList!=null){			
			return APIcaseNewList;
		}else{
			logger.info("获取测试用例为空");
			return null;
		}
	}

	@Override
	public APICaseNew getAPICaseNew(Integer id) {
		logger.info("跟据id<"+id+">查询测试用例");
		APICaseNew APIcaseNew=APIcaseNewMapper.getAPICaseNew(id);
		return APIcaseNew;
	}

	@Override
	public Integer addAPICaseNew(Integer dependCaseId, String caseName,String remark) {
		logger.info("新增测试用例");
		int count=APIcaseNewMapper.addAPICaseNew(dependCaseId, caseName, remark);
		return count;
	}

	@Override
	public Integer getMaxCaseId() {
		int MaxId=APIcaseNewMapper.getMaxCaseId();
		logger.info("获取测试用例MaxId:<"+MaxId+">");
		return MaxId;
	}

	@Override
	public Integer delAPICaseNew(Integer id) {
		logger.info("跟据id<"+id+">删除测试用例");
		int count=0;
		CaseSetModel cases=new CaseSetModel();
		cases.setCaseRelation(String.valueOf(id));
		List<CaseSetModel> caseList =caseSetMapper.getAllSet(cases);
		if(caseList.size() !=0){
			count=-1;
		}else{			
			count=APIcaseNewMapper.delAPICaseNew(id);
			if(count > 0){		
				logger.info("删除用例关联接口");
				count=APIcaseRelationMapper.delAPICaseRelation(id);
			}
		}
		return count;
	}

	@Override
	public Integer updateAPICaseNew(Integer id, Integer dependCaseId,String remark) {
		logger.info("修改测试用例<"+id+">");
		int count=APIcaseNewMapper.updateAPICaseNew(id, dependCaseId,remark);
		if(count > 0){		
			logger.info("删除用例关联接口");
			count=APIcaseRelationMapper.delAPICaseRelation(id);
		}
		return count;
	}

	
}
