package com.xiaoniu.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.testng.TestNG;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.xiaoniu.httpclient.HttpResult;
import com.xiaoniu.mapper.APICaseRelationMapper;
import com.xiaoniu.mapper.APIMapper;
import com.xiaoniu.mapper.APIResultMapper;
import com.xiaoniu.mapper.CaseSetMapper;
import com.xiaoniu.model.APICaseRelation;
import com.xiaoniu.model.APIModel;
import com.xiaoniu.model.CaseResultModel;
import com.xiaoniu.model.CaseSetModel;
import com.xiaoniu.model.CaseSetResultModel;
import com.xiaoniu.model.Pie_Data;
import com.xiaoniu.model.ResultData;
import com.xiaoniu.service.CaseResultService;
import com.xiaoniu.service.CaseSetService;
import com.xiaoniu.service.HandleService;
import com.xiaoniu.service.HttpClientService;
import com.xiaoniu.service.SyncService;
import com.xiaoniu.service.TempVarService;
import com.xiaoniu.tools.DateFormat;
import com.xiaoniu.tools.listener.SpringContextUtils;

@Listeners({com.xiaoniu.tools.listener.AllTestSetResultListener.class})
@Controller
public class CaseSetALLController {
	private Logger logger=LoggerFactory.getLogger(CaseSetALLController.class);
	public static String runEnv;
	//执行批次
	public static long bath;
	@Autowired
	private TempVarService tempVarService;
	
	@Autowired
	private CaseSetMapper caseSet;
	
	@Autowired
	private SyncService syncService;
	
	@Autowired
	private CaseSetService caseSetService;
	
	@Autowired
	private CaseResultService caseResultService;
	

	
	@RequestMapping(value="/CaseSet/recentData",produces="text/plain;charset=utf-8")
	@ResponseBody
	public String viewReport(Model model){
		List<ResultData> ResultDatas=new ArrayList<ResultData>();
		List<Object>  totalsucc = new ArrayList<Object>();
		List<Object>  totalfail = new ArrayList<Object>();
		List<Object>  totalskip = new ArrayList<Object>();
		List<Object>  totalbatch = new ArrayList<Object>();
		//获取最新七天执行结果
		List<CaseSetResultModel> CaseSetResultModels=caseSetService.getSetResultRent(-1);
		if(CaseSetResultModels.size()!=0){
			for(CaseSetResultModel setResult:CaseSetResultModels){
				long batch=setResult.getBatch();
				totalbatch.add(batch);
				List<CaseResultModel> succ=caseResultService.getCaseResultByBatch(batch, "SUCCESS");
				totalsucc.add(succ.size());
				List<CaseResultModel> fail=caseResultService.getCaseResultByBatch(batch, "FAILURE");
				totalfail.add(fail.size());
				List<CaseResultModel> skip=caseResultService.getCaseResultByBatch(batch, "SKIP");
				totalskip.add(skip.size());
			}
			
		}else{
			totalbatch.add(0);
			totalsucc.add(0);
			totalfail.add(0);
			totalskip.add(0);
		}
		ResultData succData=new ResultData();
		succData.setResultStatus("成功"); 
		succData.setTotalArray(totalsucc);
		ResultDatas.add(succData);
		ResultData failData=new ResultData();
		failData.setResultStatus("失败"); 
		failData.setTotalArray(totalfail);
		ResultDatas.add(failData);
		ResultData skipData=new ResultData();
		skipData.setResultStatus("跳过"); 
		skipData.setTotalArray(totalskip);
		ResultDatas.add(skipData);
		ResultData batchData=new ResultData();
		batchData.setResultStatus("批次号"); 
		batchData.setTotalArray(totalbatch);
		ResultDatas.add(batchData);
		ResultData xAxisData=new ResultData();
		xAxisData.setResultStatus("xAxis"); 
		xAxisData.setTotalArray(getAxis());
		ResultDatas.add(xAxisData);
		String data = JSON.toJSONString(ResultDatas);				
		System.out.println(data);
		return data;
	}
	
	@RequestMapping(value="/CaseSet/batchReport/{batch}",produces="text/plain;charset=utf-8")
	@ResponseBody
	public String viewReport(Model model, @PathVariable Long batch){
		List<Pie_Data> list = new ArrayList<Pie_Data>();
		//成功用例
		List<CaseResultModel> caseResult_succ=caseResultService.getCaseResultByBatch(batch, "SUCCESS");
		Pie_Data succ=new Pie_Data();
		succ.setName("成功");
		succ.setValue(caseResult_succ.size());
		list.add(succ);
		//成功用例
		List<CaseResultModel> caseResult_fail=caseResultService.getCaseResultByBatch(batch, "FAILURE");
		Pie_Data fail=new Pie_Data();
		fail.setName("失败");
		fail.setValue(caseResult_fail.size());
		list.add(fail);
		//跳过用例
		List<CaseResultModel> caseResult_skip=caseResultService.getCaseResultByBatch(batch, "SKIP");
		Pie_Data skip=new Pie_Data();
		skip.setName("跳过");
		skip.setValue(caseResult_skip.size());
		list.add(skip);
			
		String response = JSON.toJSONString(list);		        
		return response;
	}
	
	@RequestMapping(value="/CaseSet/batch/{batch}",produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getcaseResult(Model model, @PathVariable Long batch){
		List<CaseResultModel> caseResults=caseResultService.getCaseResultByBatch(batch, null);
		String result = JSON.toJSONString(caseResults);			
		return result;
	}
	
	@RequestMapping("/CaseSet/Recent")
	public String Recent(Model model){
		return "/CaseSet/recentReport";
	}
	
	private List<Object> getAxis(){
		List<Object>  xAxis = new ArrayList<Object>();
		 xAxis.add("最近一次");
		 xAxis.add("倒数第二次");
		 xAxis.add("倒数第三次");
		 xAxis.add("倒数第四次");
		 xAxis.add("倒数第五次");
		 xAxis.add("倒数第六次");
		 xAxis.add("倒数第七次");
		return xAxis;
	}
	/***
	 * ------------
	 * 以下方法执行调用TestNG执行测试用例
	 * **/	
	@RequestMapping(value="/CaseSet/allRun",method=RequestMethod.POST, produces="text/plain;charset=utf-8")
	@ResponseBody
	public String CaseSet_run(Model model, String envTitle){
		logger.info("执行所有用例集");
		runEnv=envTitle;
		bath=System.currentTimeMillis();
		TestNG testng = new TestNG();
		testng.setTestClasses(new Class[] { CaseSetALLController.class });
		testng.run();		
		//执行完测试，清空临时表
		tempVarService.truncateVarTemp();
		//推送数据到平台
		List<CaseSetModel> caseSets=caseSet.getAllSet(null);
//		for(CaseSetModel setModel:caseSets){
//			Integer setId=setModel.getId();
//			syncService.SyncData(setId);
//		}
		 return "用例执行完成";		
	}
	
	
	@DataProvider(name = "db")
	 public Iterator<Object[]> parameterIntTestProvider() {
		 List<Object[]> dataProvider = new ArrayList<Object[]>();
		 //这种方式不需要添加@ContextConfiguration
		 CaseSetMapper caseSetMapper=SpringContextUtils.getBean(CaseSetMapper.class);

		 //需要加载dao,从数据库获取需要执行的用例
		 List<CaseSetModel> caseSets=caseSetMapper.getAllSet(null);
		 
		 APICaseRelationMapper APIcaseRelationMapper=SpringContextUtils.getBean(APICaseRelationMapper.class);
		 
		 for(CaseSetModel setModel:caseSets){
			 String caseRelation=setModel.getCaseRelation();
			 String[] sp = caseRelation.split(",");
			 for(String cases:sp){
				 Integer caseid=Integer.valueOf(cases);
				 List<APICaseRelation> APIcaseRelations= APIcaseRelationMapper.getAPICaseRelations(caseid);	
				 dataProvider.add(new Object[] {APIcaseRelations});
			 }	
		 }
		 
		  
		 return dataProvider.iterator();		 
	 }
	 
	 
	 @Test(dataProvider="db")
	 public void testCase(List<APICaseRelation> APIcaseRelations){
		 
		 HttpClientService httpOperate=SpringContextUtils.getBean(HttpClientService.class);		 
		 APIMapper APIdao=SpringContextUtils.getBean(APIMapper.class);
		 APIResultMapper APIresultMapper=SpringContextUtils.getBean(APIResultMapper.class);
		 HandleService handleService=SpringContextUtils.getBean(HandleService.class);
		 
		 for(APICaseRelation APIcase:APIcaseRelations){	
			 Integer caseId=APIcase.getCaseId();
			 APIModel APImodel=APIdao.getAPI(APIcase.getAPIId());
			 String URL=APImodel.getApiHost()+APImodel.getApiUrl();
			 String caseData=APIcase.getCaseData();
			 
			//将runEnv  处理IP 172.20.20.215
				caseData=handleService.ServerHost(caseData, runEnv);
				//从初始化变量中获取全局变量值,例如：${date}
				caseData=handleService.replaceParam(caseData);
				//获取请求数据中的变量
				//生成随机变量数据:生成20位随机数字:(%\\{.+?\\}%),例如：%{transId}%
				caseData=handleService.tempVar(caseData, "(%\\{.+?\\}%)");
				//生成随机身份证:(&\\{.+?\\}&),例如：&{IDCard}&,暂未使用
//				caseData=handleService.tempVar(caseData, "(&\\{.+?\\}&)");
				//从初始化变量中获取临时变量值,例如：#{userName}#
				caseData=handleService.tempVar(caseData, "(\\#\\{.+?\\}\\#)");

				String method=APImodel.getApiMethod();
				
				//验证数据
				String caseAssert=APIcase.getCaseAssert();
				//保存数据
				String caseParam=APIcase.getCaseParam();
				HttpResult httpresult=httpOperate.doSend(runEnv,method, URL, caseData);
				String response=httpresult.getContent();
				
				Integer statusCode=httpresult.getStatusCode();
				
				long startTime=httpresult.getBeginTime();
				long endTime=httpresult.getEndTime();
				APIresultMapper.addAPIResult(caseId, APIcase.getAPIId(),APIcase.getId(), -1, DateFormat.LongToDate(startTime), DateFormat.LongToDate(endTime), endTime-startTime, caseData,statusCode, response,bath);
				
				//保存数据
				handleService.paramExtraction(caseParam, response);
				
				//结果验证
				handleService.resultAssert(caseAssert, statusCode, response);
		 }	
	 }
}
