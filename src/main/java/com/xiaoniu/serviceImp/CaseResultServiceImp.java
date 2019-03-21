package com.xiaoniu.serviceImp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoniu.mapper.APICaseNewMapper;
import com.xiaoniu.mapper.CaseResultMapper;
import com.xiaoniu.model.APICaseNew;
import com.xiaoniu.model.CaseResultModel;
import com.xiaoniu.service.CaseResultService;

@Service
public class CaseResultServiceImp implements CaseResultService{
	private Logger log = LoggerFactory.getLogger(CaseResultServiceImp.class); 
	
	@Autowired
	private CaseResultMapper CaseresultMapper;
	
	@Autowired
	private APICaseNewMapper APIcaseNewMapper;
	
	@Override
	public CaseResultModel getCaseResult(Integer caseId) {
		log.info("通过id<"+caseId+">获取用例执行结果");
		CaseResultModel caseResult  =CaseresultMapper.getCaseResult(caseId);
		return caseResult;
	}

	@Override
	public Integer addCaseResult(Integer caseId,Integer setId, String startTime,
			String endTime, long costTime, String resultStatus,String runEnv,long batch) {
		log.info("新增用例执行结果");
		int count=CaseresultMapper.addCaseResult(caseId,setId, startTime, endTime, costTime, resultStatus,runEnv,batch);
		return count;
	}

	@Override
	public Integer updateCaseResult(Integer caseId, String startTime,
			String endTime, long costTime, String resultStatus) {
		log.info("修改用例执行结果");
		int count=CaseresultMapper.updateCaseResult(caseId, startTime, endTime, costTime, resultStatus);
		return count;
	}

	@Override
	public List<CaseResultModel> getCaseResultByBatch(long batch,String resultStatus) {
		log.info("通过batch<"+batch+">获取用例执行结果");
		List<CaseResultModel> caseResults=CaseresultMapper.getCaseResultByBatch(batch, resultStatus);
		if(caseResults!=null){					
			for(CaseResultModel caseResult:caseResults){
				APICaseNew  APIcaseNew=APIcaseNewMapper.getAPICaseNew(caseResult.getCaseId());
				caseResult.setCaseName(APIcaseNew.getCaseName());
			}
			return caseResults;
		}else{
			log.info("通过batch<"+batch+">获取用例执行结果为空");
			return null;
		}
	}

}
