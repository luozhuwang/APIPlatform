package com.xiaoniu.service;

import java.util.List;
import com.xiaoniu.model.CaseResultModel;

public interface APIResultService {

	//查询运行接口结果
	List<CaseResultModel> getAPIResult(Integer caseId);
	//通过用例集和用例ID获取接口结果
	List<CaseResultModel> getAPIResultBySetId(Integer caseId,Integer setId);
	//通过用例集和用例ID、批次号获取接口结果
	List<CaseResultModel> getAPIResultBybatch(Integer caseId,Integer setId,long batch);
	//添加运行接口结果
	Integer addAPIResult(Integer caseId,Integer APIId,Integer relationId,Integer setId,String startTime,String endTime,long costTime,String request,Integer statusCode,String response,long batch);
}
