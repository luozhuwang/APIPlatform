package com.xiaoniu.serviceImp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoniu.mapper.APICaseRelationMapper;
import com.xiaoniu.model.APICaseRelation;
import com.xiaoniu.service.APICaseRelationService;


@Service
public class APICaseRelationServiceImp implements APICaseRelationService{
	private Logger logger =LoggerFactory.getLogger(APICaseRelationServiceImp.class);
	
	@Autowired
	private APICaseRelationMapper	APIcaseRelationMapper;
	
	@Override
	public List<APICaseRelation> getAPICaseRelations(Integer caseId) {
		logger.info("跟据caseId<"+caseId+">查询测试用例关联的接口");
		List<APICaseRelation> APIRelations=APIcaseRelationMapper.getAPICaseRelations(caseId);
		if(APIRelations!=null){			
			return APIRelations;
		}else{
			logger.info("获取测试用例关联的接口为空");
			return null;
		}
	}

	@Override
	public Integer addAPICaseRelation(Integer caseId, Integer APIId,
			String caseData, String caseAssert, String caseParam) {
		logger.info("新增测试用例关联接口");
		int count=APIcaseRelationMapper.addAPICaseRelation(caseId, APIId, caseData, caseAssert, caseParam);
		return count;
	}

	@Override
	public APICaseRelation getAPIRelation(Integer relationId,Integer caseId, Integer APIId) {
		logger.info("跟据relationId<"+relationId+">caseId<"+caseId+">APIId<"+APIId+"查询测试用例关联的接口");
		APICaseRelation APIcaseRelation=APIcaseRelationMapper.getAPIRelation(relationId,caseId, APIId);
		return APIcaseRelation;
	}

}
