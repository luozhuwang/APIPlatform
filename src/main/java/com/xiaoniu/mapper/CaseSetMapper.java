package com.xiaoniu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoniu.model.CaseResultModel;
import com.xiaoniu.model.CaseSetModel;
import com.xiaoniu.model.CaseSetResultModel;

public interface CaseSetMapper {
	//获取测试用例集
	List<CaseSetModel> getAllSet(CaseSetModel caseSetModel);
	//通过ID获取用例集
	CaseSetModel getSet(Integer id);	
	//通过ID删除用例集
	Integer delSet(Integer id);
	//新增用例集
	Integer addSet(@Param("caseSetName")String caseSetName,@Param("caseRelation")String caseRelation,@Param("caseCount")Integer caseCount,@Param("remark")String remark);
	//修改用例集
	Integer updateSet(@Param("id")Integer id,@Param("caseRelation")String caseRelation,@Param("caseCount")Integer caseCount,@Param("remark")String remark);
	//新增用例集结果
	Integer addSetResult(@Param("setId")Integer setId,@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("costTime")long costTime,@Param("runEnv")String runEnv,@Param("batch")long batch);
	//获取最新用例集结果
	CaseSetResultModel getSetResult(Integer setId);
	//通过用例集ID获取用例结果
	List<CaseResultModel> getSetCaseResult(@Param("setId")Integer setId,@Param("resultStatus")String resultStatus);
	//查询setId为-1，最近七次执行结果
	List<CaseSetResultModel> getSetResultRent(Integer setId);
	
}
