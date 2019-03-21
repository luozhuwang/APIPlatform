package com.xiaoniu.service;

import java.util.List;
import com.xiaoniu.model.APICaseRelation;

public interface APICaseRelationService {
	//通过用例ID获取用例下面所有接口
	List<APICaseRelation> getAPICaseRelations(Integer caseId);
	//通过用例ID和接口ID获取接口
	APICaseRelation getAPIRelation(Integer relationId,Integer caseId,Integer APIId);
	//添加测试用例关联的接口
	Integer addAPICaseRelation(Integer caseId,Integer APIId,String caseData,String caseAssert,String caseParam);
}
