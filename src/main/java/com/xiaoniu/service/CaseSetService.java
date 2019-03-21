package com.xiaoniu.service;

import java.util.List;

import com.xiaoniu.model.CaseResultModel;
import com.xiaoniu.model.CaseSetModel;
import com.xiaoniu.model.CaseSetResultModel;

public interface CaseSetService {
	//获取测试用例集
		List<CaseSetModel> getAllSet(CaseSetModel caseSetModel);
		//通过ID获取用例集
		CaseSetModel getSet(Integer id);	
		//通过ID删除用例集
		Integer delSet(Integer id);
		//新增用例集
		Integer addSet(String caseSetName,String caseRelation,Integer caseCount,String remark);
		//修改用例集
		Integer updateSet(Integer id,String caseRelation,Integer caseCount,String remark);
		//新增用例集结果
		Integer addSetResult(Integer setId,String startTime,String endTime,long costTime,String runEnv,long batch);
		//获取用例集结果
		CaseSetResultModel getSetResult(Integer setId);
		//通过用例集ID获取用例结果
		List<CaseResultModel> getSetCaseResult(Integer setId,String resultStatus);
		//查询setId为-1，最近七次执行结果
		List<CaseSetResultModel> getSetResultRent(Integer setId);
}
