package com.xiaoniu.tools.listener;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import org.testng.IResultMap;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.alibaba.fastjson.JSON;
import com.aventstack.extentreports.Status;
import com.xiaoniu.mapper.CaseResultMapper;
import com.xiaoniu.mapper.CaseSetMapper;
import com.xiaoniu.model.APICaseModel;
import com.xiaoniu.model.CaseResultModel;
import com.xiaoniu.tools.ResponseData;


public class TestSetResultListener extends TestListenerAdapter{

	@Override
	public void onFinish(ITestContext testContext) {	
		CaseSetMapper caseSetResult=SpringContextUtils.getBean(CaseSetMapper.class);
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
//		//开始时间
		Date startDate=testContext.getStartDate();
		System.out.println("开始时间："+sdf.format(testContext.getStartDate()));
//		//结束时间
		Date endDate=testContext.getEndDate();
		System.out.println("结束时间："+sdf.format(testContext.getEndDate()));
		IResultMap Passed =testContext.getPassedTests();
		IResultMap Failed =testContext.getFailedTests();
		IResultMap Skipped =testContext.getSkippedTests();
        		
	
		SaveResult(Passed, Status.PASS);
		SaveResult(Failed, Status.FATAL);
		SaveResult(Skipped, Status.SKIP);
		
		String start=sdf.format(startDate);
		String end=sdf.format(endDate);
//		caseSetResult.addSetResult(CaseSetController.caseSetid, start.substring(0, start.length()-4), end.substring(0, end.length()-4), (endDate.getTime()-startDate.getTime()));
		
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
            		System.out.println("param:"+param.toString());
                    String resultstatus=null;
            		//解析
                    APICaseModel runcase=JSON.parseObject(JSON.toJSON(param).toString(), APICaseModel.class);
                    int caseid=runcase.getId();
                    System.out.println("用例ID："+caseid);
                    System.out.println("时间1："+FormatTime(result.getStartMillis()));
                    System.out.println("时间2："+FormatTime(result.getEndMillis()));
                    System.out.println("结果状态："+result.getStatus());
                    if(result.getStatus() ==1){
                    	resultstatus="SUCCESS";
                    }else if(result.getStatus() ==2){
                    	resultstatus="FAILURE";
                    }else if(result.getStatus() ==3){
                    	resultstatus="SKIP";
                    }
                    CaseResultModel caseResultModel=ResponseData.getmap().get(caseid);
                    long costTime=result.getEndMillis()-result.getStartMillis();
                  //这种方式不需要添加@ContextConfiguration
                    CaseResultMapper caseResultDao=SpringContextUtils.getBean(CaseResultMapper.class);
                   
//                    caseResultDao.addCaseResult(caseid,CaseSetController.caseSetid, FormatTime(result.getStartMillis()), FormatTime(result.getEndMillis()), costTime, resultstatus,caseResultModel.getRequest(), caseResultModel.getResponse(),0);
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
