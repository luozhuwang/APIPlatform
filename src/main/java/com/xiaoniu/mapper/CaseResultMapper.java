package com.xiaoniu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoniu.model.CaseResultModel;

public interface CaseResultMapper {	
	//查询最新运行测试用例结果
	CaseResultModel getCaseResult(Integer caseId);
	//通过批次号获取用例结果
	List<CaseResultModel> getCaseResultByBatch(@Param("batch")long batch,@Param("resultStatus")String resultStatus);
	//添加运行测试用例结果
	Integer addCaseResult(@Param("caseId")Integer caseId,@Param("setId")Integer setId,@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("costTime")long costTime,@Param("resultStatus")String resultStatus,@Param("runEnv")String runEnv,@Param("batch")long batch);
	//修改运行测试用例结果
	Integer updateCaseResult(@Param("caseId")Integer caseId,@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("costTime")long costTime,@Param("resultStatus")String resultStatus);
}
