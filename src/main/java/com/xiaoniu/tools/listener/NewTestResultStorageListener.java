package com.xiaoniu.tools.listener;

import java.text.SimpleDateFormat;
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
import com.alibaba.fastjson.JSONObject;
import com.aventstack.extentreports.Status;
import com.xiaoniu.controller.APICaseNewController;
import com.xiaoniu.mapper.CaseResultMapper;
import com.xiaoniu.model.APICaseRelation;
import com.xiaoniu.model.CaseResultModel;
import com.xiaoniu.tools.ResponseData;

/**
 * 将testng测试结果存储到数据库中
 * https://blog.csdn.net/happymff/article/details/72377285
 * @Test
    public void keyword(ITestContext context){
        context.setAttribute("demoString", "Test Passing Value.");
    }
 * 
 * https://www.jianshu.com/p/74816a200221
 * **/

public class NewTestResultStorageListener extends TestListenerAdapter{

	@Override
	public void onFinish(ITestContext testContext) {		
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		//开始时间
		System.out.println("开始时间："+sdf.format(testContext.getStartDate()));
//		//结束时间
		System.out.println("结束时间："+sdf.format(testContext.getEndDate()));
//		int passSize = testContext.getPassedTests().size();
//        int failSize = testContext.getFailedTests().size();
//        int skipSize = testContext.getSkippedTests().size();
        
		IResultMap Passed =testContext.getPassedTests();
		IResultMap Failed =testContext.getFailedTests();
		IResultMap Skipped =testContext.getSkippedTests();
        		
	
		SaveResult(Passed, Status.PASS);
		SaveResult(Failed, Status.FATAL);
		SaveResult(Skipped, Status.SKIP);
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
            	String resultstatus=null;
            	for(Object param:parameters){  
            		System.out.println("param:"+param.toString());
            		List<APICaseRelation> caseRelations = JSONObject.parseArray(JSON.toJSON(param).toString(), APICaseRelation.class);   
            		Integer caseId=caseRelations.get(0).getCaseId();
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
            		long costTime=result.getEndMillis()-result.getStartMillis();
            		//这种方式不需要添加@ContextConfiguration
            		CaseResultMapper caseResultDao=SpringContextUtils.getBean(CaseResultMapper.class);            		
            		//用例执行完，setId为0
            		caseResultDao.addCaseResult(caseId,0, FormatTime(result.getStartMillis()), FormatTime(result.getEndMillis()), costTime, resultstatus,APICaseNewController.runEnv,APICaseNewController.bath);
            	}
               /* 
                Object[] parameters = result.getParameters();
            	for(Object param:parameters){
            		System.out.println("param:"+param.toString());
            		//解析
//                    APICaseRelation runcase=JSON.parseObject(JSON.toJSON(param).toString(), APICaseRelation.class);
//                    int caseid=runcase.getCaseId();
//                    System.out.println("用例ID："+caseid);
            		String resultstatus=null;
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
                    long costTime=result.getEndMillis()-result.getStartMillis();
                  //这种方式不需要添加@ContextConfiguration
                    CaseResultMapper caseResultDao=SpringContextUtils.getBean(CaseResultMapper.class);
                    //用例执行完，setId为0
                    caseResultDao.addCaseResult(APICaseNewController.runid,0, FormatTime(result.getStartMillis()), FormatTime(result.getEndMillis()), costTime, resultstatus,null,null);
                }*/	
    		}
		}

	}
	
	
	private String FormatTime(long millis) {
		//精度显示毫秒
		//SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
    	SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//前面的lSysTime是秒数，先乘1000得到毫秒数，再转为java.util.Date类型
		java.util.Date dt = new Date(millis);  
		String sDateTime = sdf.format(dt);  //得到精确到秒的表示：08/31/2006 21:08:00
        return sDateTime;
    } 
}
