package com.xiaoniu.service;

import java.util.List;
import com.xiaoniu.model.CaseResultModel;



public interface CaseResultService {
	//查询运行测试用例结果
	CaseResultModel getCaseResult(Integer caseId);
	//通过批次号获取用例结果
	List<CaseResultModel> getCaseResultByBatch(long batch,String resultStatus);
	//添加运行测试用例结果
	Integer addCaseResult(Integer caseId,Integer setId,String startTime,String endTime,long costTime,String resultStatus,String runEnv,long batch);
	//修改运行测试用例结果
	Integer updateCaseResult(Integer caseId,String startTime,String endTime,long costTime,String resultStatus);
	
}
