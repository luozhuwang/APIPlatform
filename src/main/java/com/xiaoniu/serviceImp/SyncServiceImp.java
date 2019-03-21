package com.xiaoniu.serviceImp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.xiaoniu.httpclient.HttpClientOperate;
import com.xiaoniu.mapper.APICaseNewMapper;
import com.xiaoniu.mapper.APIMapper;
import com.xiaoniu.mapper.APIResultMapper;
import com.xiaoniu.mapper.CaseResultMapper;
import com.xiaoniu.mapper.CaseSetMapper;
import com.xiaoniu.model.APICaseNew;
import com.xiaoniu.model.APIModel;
import com.xiaoniu.model.CaseResultModel;
import com.xiaoniu.model.CaseSetModel;
import com.xiaoniu.model.test_details;
import com.xiaoniu.service.SyncService;
import com.xiaoniu.tools.Constant;


@Service
public class SyncServiceImp implements SyncService{
	private Logger logger =LoggerFactory.getLogger(SyncServiceImp.class);
	
	@Autowired
	private CaseSetMapper caseSetMapper;
	
	@Autowired
	private APICaseNewMapper APIcaseNewMapper;
	
	@Autowired
	private CaseResultMapper caseResultMapper;
	
	@Autowired
	private APIResultMapper APIresultMapper;

	@Autowired
	private APIMapper APImapper;
	
	@Autowired
	private HttpClientOperate httpClientOperate;
	
	@Override
	public void SyncData(Integer setId) {	
		CaseSetModel caseset=caseSetMapper.getSet(setId);
		logger.info("关联用例："+caseset.getCaseRelation());
		String caserelastion=caseset.getCaseRelation();
		String [] relation=caserelastion.split(",");
		
		for(String relationId:relation){
			Integer caseId=Integer.valueOf(relationId);
			APICaseNew APIcase=APIcaseNewMapper.getAPICaseNew(caseId);
			CaseResultModel caseresult=caseResultMapper.getCaseResult(caseId);
			caseresult.setCaseName(APIcase.getCaseName());
			caseresult.setRemark(APIcase.getRemark());
			
			//同步数据参数
			Map<String, Object> nameValuePairs = new HashMap<String, Object>();
			nameValuePairs.put("job_id", "50aca6ee-cad6-11e8-9c93-0242ac120002");
            nameValuePairs.put("case_id", "U0"+caseresult.getCaseId());
            nameValuePairs.put("case_title", caseresult.getCaseName());
            nameValuePairs.put("case_description", caseresult.getRemark());
            nameValuePairs.put("executor", "罗菊旺");
            nameValuePairs.put("test_type", "api");
            nameValuePairs.put("token", "62485676616E56335957356E5148687059573975615855324E69356A6232303D");
            nameValuePairs.put("test_start_time", caseresult.getStartTime()+".000");                
            nameValuePairs.put("test_end_time", caseresult.getEndTime()+".000");
            //测试结果, 必填, 仅可选填: pass、fail、error
            String result=caseresult.getResultStatus();
            if(result.equals("SUCCESS")){            	
            	nameValuePairs.put("test_result", "pass");
            }else if(result.equals("FAILURE")){
            	nameValuePairs.put("test_result", "fail");
            }else{
            	nameValuePairs.put("test_result", "error");
            }
            nameValuePairs.put("actual", "请参考接口结果");
            List<test_details> ll_detail=getCaseResult(caseId, setId);
            nameValuePairs.put("test_details", JSON.toJSONString(ll_detail));
			try {
//				String response = HttpsUtils.doPost(Constant.syncUrl, nameValuePairs);
				String response = httpClientOperate.doPost(Constant.syncUrl, nameValuePairs).getContent();
				logger.info("数据同步结果："+response);
			} catch (Exception e) {				
				e.printStackTrace();
			}
			
		}
	}

	
	private List<test_details>  getCaseResult(Integer caseId, Integer setId){
		List<test_details> ll_detail=new ArrayList<test_details>();
		//通过用例和setid 找接口
		List<CaseResultModel> APIResults= APIresultMapper.getAPIResultBySetId(caseId, setId);
		for(CaseResultModel APIResult:APIResults){
			APIModel API=APImapper.getAPI(APIResult.getAPIId());
			APIResult.setApiName(API.getApiName());
			APIResult.setApiMethod(API.getApiMethod());
			APIResult.setApiUrl(API.getApiUrl());
//			System.out.println("接口"+APIResult.getAPIId()+"执行结果："+APIResult);
//			System.out.println("接口名称："+APIResult.getApiName());
//			System.out.println("接口URL："+API.getApiHost()+APIResult.getApiUrl());
//			System.out.println("接口名称："+APIResult.getApiMethod());
//			System.out.println("接口状态码："+APIResult.getStatusCode());
//			System.out.println("接口请求："+APIResult.getRequest());
//			System.out.println("接口响应："+APIResult.getResponse());
//			System.out.println("开始时间："+APIResult.getStartTime()+".000");
//			System.out.println("结束时间："+APIResult.getEndTime()+".000");
			
			
			test_details detail=new test_details();
			
			detail.setTitle(API.getApiName());
			detail.setUrl(API.getApiHost()+APIResult.getApiUrl());
			String method=APIResult.getApiMethod();
			if(method.equals("post1")){
				detail.setMethod("post");
			}else{					
				detail.setMethod(APIResult.getApiMethod());
			}
			detail.setStatus(APIResult.getStatusCode());
			detail.setRequest_headers("");
			detail.setRequest_body(APIResult.getRequest());
			detail.setResponse_headers("");
			detail.setResponse_body(APIResult.getResponse());
			detail.setDescription(API.getRemark());
			detail.setStart_time(APIResult.getStartTime()+".000");
			detail.setEnd_time(APIResult.getEndTime()+".000");
			
			ll_detail.add(detail);
		}
		
		return ll_detail;
	}
	
}
