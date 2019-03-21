package com.xiaoniu.serviceImp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoniu.mapper.APIResultMapper;
import com.xiaoniu.model.CaseResultModel;
import com.xiaoniu.service.APIResultService;

@Service
public class APIResultServiceImp implements APIResultService{
	private Logger logger =LoggerFactory.getLogger(APIResultServiceImp.class);
	
	
	@Autowired
	private APIResultMapper APIresultMapper;
	
	@Override
	public List<CaseResultModel> getAPIResult(Integer caseId) {
		logger.info("通过id<"+caseId+">获取用例执行结果");
		List<CaseResultModel> resultModels=APIresultMapper.getAPIResult(caseId);
		if(resultModels!=null){			
			return resultModels;
		}else{
			logger.info("获取用例执行结果为空");
			return null;
		}
	}
	@Override
	public List<CaseResultModel> getAPIResultBySetId(Integer caseId, Integer setId) {
		logger.info("通过用例集<"+setId+">和用例<"+caseId+">获取用例执行结果");
		List<CaseResultModel> resultModels=APIresultMapper.getAPIResultBySetId(caseId, setId);
		if(resultModels!=null){			
			return resultModels;
		}else{
			logger.info("获取用例执行结果为空");
			return null;
		}
	}
	
	@Override
	public Integer addAPIResult(Integer caseId, Integer APIId,Integer relationId, Integer setId,
			String startTime, String endTime, long costTime, String request,Integer statusCode,
			String response, long batch) {
		logger.info("新增用例执行结果");
		int count=APIresultMapper.addAPIResult(caseId, APIId,relationId, setId, startTime, endTime, costTime, request,statusCode, response, batch);
		return count;
	}
	
	@Override
	public List<CaseResultModel> getAPIResultBybatch(Integer caseId,Integer setId, long batch) {
		logger.info("通过用例集<"+setId+">、用例<"+caseId+">、批次号<"+batch+">获取用例执行结果");
		List<CaseResultModel> resultModels=APIresultMapper.getAPIResultBybatch(caseId, setId, batch);
		if(resultModels!=null){			
			return resultModels;
		}else{
			logger.info("获取用例执行结果为空");
			return null;
		}
	}
}
