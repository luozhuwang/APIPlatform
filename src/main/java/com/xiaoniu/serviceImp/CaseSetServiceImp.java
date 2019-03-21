package com.xiaoniu.serviceImp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoniu.mapper.APICaseNewMapper;
import com.xiaoniu.mapper.CaseSetMapper;
import com.xiaoniu.model.APICaseNew;
import com.xiaoniu.model.CaseResultModel;
import com.xiaoniu.model.CaseSetModel;
import com.xiaoniu.model.CaseSetResultModel;
import com.xiaoniu.service.CaseSetService;

@Service
public class CaseSetServiceImp implements CaseSetService{
	private Logger logger=LoggerFactory.getLogger(CaseSetServiceImp.class);
	
	@Autowired
	private CaseSetMapper caseSetMapper;
	
	@Autowired
	private APICaseNewMapper APIcaseNewMapper;
	
	@Override
	public List<CaseSetModel> getAllSet(CaseSetModel caseSetModel) {
		logger.info("获取所有用例集");
		List<CaseSetModel> caseSetList=caseSetMapper.getAllSet(caseSetModel);
		if(caseSetList!=null){			
			return caseSetList;
		}else{
			logger.info("获取用例集为空");
			return null;
		}
	}

	@Override
	public CaseSetModel getSet(Integer id) {
		logger.info("跟据id<"+id+">查询用例集");
		CaseSetModel caseSet=caseSetMapper.getSet(id);
		if(caseSet!=null){			
			return caseSet;
		}else{
			logger.info("查询用例集为空");
			return null;
		}
	}

	@Override
	public Integer delSet(Integer id) {
		logger.info("删除用例集id<"+id+">");
		int count=caseSetMapper.delSet(id);
		return count;
	}

	@Override
	public Integer addSet(String caseSetName, String caseRelation,Integer caseCount, String remark) {
		logger.info("新增用例集");
		int count=caseSetMapper.addSet(caseSetName, caseRelation, caseCount, remark);
		return count;
	}

	@Override
	public Integer updateSet(Integer id, String caseRelation, Integer caseCount,String remark) {
		logger.info("修改id<"+id+">用例集");
		int count=caseSetMapper.updateSet(id, caseRelation, caseCount, remark);
		return count;
	}

	@Override
	public Integer addSetResult(Integer setId, String startTime,String endTime, long costTime,String runEnv,long batch) {
		logger.info("新增用例集结果");
		int count=caseSetMapper.addSetResult(setId, startTime, endTime, costTime,runEnv,batch);
		return count;
	}

	@Override
	public List<CaseResultModel> getSetCaseResult(Integer setId,String resultStatus) {		
		logger.info("通过用例集<"+setId+">获取最新用例结果");
		List<CaseResultModel> caseResultList=caseSetMapper.getSetCaseResult(setId,resultStatus);	
		if(caseResultList!=null){
			for(CaseResultModel caseResult:caseResultList){
				APICaseNew  APIcaseNew=APIcaseNewMapper.getAPICaseNew(caseResult.getCaseId());
				caseResult.setCaseName(APIcaseNew.getCaseName());
			}
			return caseResultList;
		}else{
			logger.info("通过用例集,查询最新用例结果为空");
			return null;
		}
	/*
		List<CaseResultModel> caseResultList=caseSetMapper.getSetCaseResult(setId,resultStatus);		
		if(caseResultList!=null){	
			for(CaseResultModel caseResult:caseResultList){
				int caseid=caseResult.getCaseId();
				//通过用例ID获取用例名称和断言
				APICaseModel APIcase=APIcaseMapper.getAPICase(caseid);
				caseResult.setCaseName(APIcase.getCaseName());
				//将断言转换成:|状态码等于'200'|
				String assertcontent=APIcase.getCaseAssert();
				List<AssertModel> assertModels = JSON.parseArray(assertcontent, AssertModel.class); 
				String content="";
				for(AssertModel ss:assertModels){
					String assertSpell=ss.getAssertItem()+ss.getCompare()+"'"+ss.getExpect()+"'";
					content=content+"|"+assertSpell+"|";
				}
				caseResult.setCaseAssert(content);				
			}
			return caseResultList;
		}else{
			logger.info("通过用例集,查询最新用例结果为空");
			return null;
		}*/
	}

	@Override
	public CaseSetResultModel getSetResult(Integer setId) {
		logger.info("获取用例集<"+setId+">结果");
		CaseSetResultModel caseSetResultModel=caseSetMapper.getSetResult(setId);
		if(caseSetResultModel!=null){	
			CaseSetModel caseSet=caseSetMapper.getSet(setId);
			caseSetResultModel.setCaseSetName(caseSet.getCaseSetName());
			return caseSetResultModel;
		}else{
			logger.info("获取用例集为空");
			return null;
		}
	}

	@Override
	public List<CaseSetResultModel> getSetResultRent(Integer setId) {
		logger.info("获取用例集<"+setId+">最近七次运行结果");
		List<CaseSetResultModel> caseSetResults=caseSetMapper.getSetResultRent(setId);
		if(caseSetResults!=null){	
			return caseSetResults;
		}else{
			logger.info("获取用例集最近七次运行结果为空");
			return null;
		}
	}

	
}
