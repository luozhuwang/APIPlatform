package com.xiaoniu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoniu.model.APICaseRelation;

public interface APICaseRelationMapper {
	//通过用例ID获取用例下面所有接口
	List<APICaseRelation> getAPICaseRelations(Integer caseId);
	//通过用例ID和接口ID获取接口
	APICaseRelation getAPIRelation(@Param("relationId")Integer relationId,@Param("caseId")Integer caseId,@Param("APIId")Integer APIId);
	//添加测试用例关联的接口
	Integer addAPICaseRelation(@Param("caseId")Integer caseId,@Param("APIId")Integer APIId,@Param("caseData")String caseData,@Param("caseAssert")String caseAssert,@Param("caseParam")String caseParam);
	
	//通过用例ID删除关联接口
	Integer delAPICaseRelation(Integer caseId);
}
