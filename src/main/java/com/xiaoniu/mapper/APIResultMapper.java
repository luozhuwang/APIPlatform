package com.xiaoniu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoniu.model.CaseResultModel;

public interface APIResultMapper {
	//查询运行接口结果
	List<CaseResultModel> getAPIResult(Integer caseId);
	//通过用例集和用例ID获取接口结果
	List<CaseResultModel> getAPIResultBySetId(@Param("caseId")Integer caseId,@Param("setId")Integer setId);
	//通过用例集和用例ID、批次号获取接口结果
	List<CaseResultModel> getAPIResultBybatch(@Param("caseId")Integer caseId,@Param("setId")Integer setId,@Param("batch")long batch);
	//添加运行接口结果
	Integer addAPIResult(@Param("caseId")Integer caseId,@Param("APIId")Integer APIId,@Param("relationId")Integer relationId,@Param("setId")Integer setId,@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("costTime")long costTime,@Param("request")String request,@Param("statusCode")Integer statusCode,@Param("response")String response,@Param("batch")long batch);
}
