package com.xiaoniu.tools.listener;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.testng.IResultMap;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aventstack.extentreports.Status;
import com.xiaoniu.controller.APICaseNewController;
import com.xiaoniu.controller.CaseSetALLController;
import com.xiaoniu.controller.CaseSetController;
import com.xiaoniu.mapper.CaseResultMapper;
import com.xiaoniu.mapper.CaseSetMapper;
import com.xiaoniu.model.APICaseModel;
import com.xiaoniu.model.APICaseRelation;
import com.xiaoniu.model.CaseResultModel;
import com.xiaoniu.tools.ResponseData;


public class AllTestSetResultListener extends TestListenerAdapter{

	@Override
	public void onFinish(ITestContext testContext) {
		CaseSetMapper caseSetResult=SpringContextUtils.getBean(CaseSetMapper.class);
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		System.out.println("开始时间："+sdf.format(testContext.getStartDate()));
//		System.out.println("结束时间："+sdf.format(testContext.getEndDate()));
		
//		//开始时间
		Date startDate=testContext.getStartDate();
//		//结束时间
		Date endDate=testContext.getEndDate();
		
		IResultMap Passed =testContext.getPassedTests();
		IResultMap Failed =testContext.getFailedTests();
		IResultMap Skipped =testContext.getSkippedTests();
        		
	
		SaveResult(Passed, Status.PASS);
		SaveResult(Failed, Status.FATAL);
		SaveResult(Skipped, Status.SKIP);
		
		String start=sdf.format(startDate);
		String end=sdf.format(endDate);
		
		caseSetResult.addSetResult(-1, start, end, (endDate.getTime()-startDate.getTime()),CaseSetALLController.runEnv,CaseSetALLController.bath);
	}

	
	private void SaveResult(IResultMap results, Status status){		
		if (results.size() > 0) {
			Set<ITestResult> treeSet = new TreeSet<ITestResult>(new Comparator<ITestResult>() {
                @Override
                public int compare(ITestResult o1, ITestResult o2) {
                    return o1.getStartMillis()<o2.getStartMillis()?-1:1;
                }
            });
            treeSet.addAll(results.getAllResults());
            
            for (ITestResult result : treeSet) { 
            	Object[] parameters = result.getParameters();
            	for(Object param:parameters){
//            		System.out.println("param:"+param.toString());
            		
            		List<APICaseRelation> caseRelations = JSONObject.parseArray(JSON.toJSON(param).toString(), APICaseRelation.class);   		
            		Integer caseId=caseRelations.get(0).getCaseId();
            		
            		String resultstatus=null;
//            		 System.out.println("时间1："+FormatTime(result.getStartMillis()));
//                     System.out.println("时间2："+FormatTime(result.getEndMillis()));
//                     System.out.println("结果状态："+result.getStatus());
                     if(result.getStatus() ==1){
                     	resultstatus="SUCCESS";
                     }else if(result.getStatus() ==2){
                     	resultstatus="FAILURE";
                     }else if(result.getStatus() ==3){
                     	resultstatus="SKIP";
                     }
                     long costTime=result.getEndMillis()-result.getStartMillis();
                   //这种方式不需要添加@ContextConfiguration
                     CaseResultMapper caseResultDao=SpringContextUtils.getBean(CaseResultMapper.class);
                     //用例执行完，setId为0
                     caseResultDao.addCaseResult(caseId,-1, FormatTime(result.getStartMillis()), FormatTime(result.getEndMillis()), costTime, resultstatus,CaseSetALLController.runEnv,CaseSetALLController.bath);
            	}                           
    		}
		}

	}
	
	
	private String FormatTime(long millis) {
    	SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//前面的lSysTime是秒数，先乘1000得到毫秒数，再转为java.util.Date类型
		java.util.Date dt = new Date(millis);  
		String sDateTime = sdf.format(dt);  //得到精确到秒的表示：08/31/2006 21:08:00
        return sDateTime;
    } 
}
