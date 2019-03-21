package com.xiaoniu.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.testng.TestNG;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoniu.httpclient.HttpResult;
import com.xiaoniu.mapper.APICaseRelationMapper;
import com.xiaoniu.mapper.APIMapper;
import com.xiaoniu.mapper.APIResultMapper;
import com.xiaoniu.mapper.CaseSetMapper;
import com.xiaoniu.model.APICaseNew;
import com.xiaoniu.model.APICaseRelation;
import com.xiaoniu.model.APIModel;
import com.xiaoniu.model.AssertModel;
import com.xiaoniu.model.CaseResultModel;
import com.xiaoniu.model.CaseSetModel;
import com.xiaoniu.model.CaseSetResultModel;
import com.xiaoniu.model.Pie_Data;
import com.xiaoniu.service.APICaseNewService;
import com.xiaoniu.service.APICaseRelationService;
import com.xiaoniu.service.APIResultService;
import com.xiaoniu.service.APIService;
import com.xiaoniu.service.CaseSetService;
import com.xiaoniu.service.HandleService;
import com.xiaoniu.service.HttpClientService;
import com.xiaoniu.service.SyncService;
import com.xiaoniu.service.TempVarService;
import com.xiaoniu.tools.DateFormat;
import com.xiaoniu.tools.listener.SpringContextUtils;

@Listeners({com.xiaoniu.tools.listener.NewTestSetResultListener.class})
@Controller
public class CaseSetController {
	private Logger logger=LoggerFactory.getLogger(CaseSetController.class);
	
	//执行用例集ID
	public static int caseSetid;
	//执行环境
	public static String runEnv;
	//执行批次
	public static long bath;
	
	@Autowired
	private CaseSetService caseSetService;
	
	@Autowired
	private APIService APIservice;
	
	@Autowired
	private APICaseRelationService APIcaseRelationService;
	
	@Autowired
	private APICaseNewService APIcaseNewService;
	
	@Autowired
	private TempVarService tempVarService;
	
	@Autowired
	private APIResultService APIresultService;
	
	@Autowired
	private SyncService syncService;
	
	@RequestMapping("/CaseSet/list")
	public String CaseSet_list(CaseSetModel caseSetModel,@RequestParam(value="pagenum",defaultValue="1") Integer pagenum,Model model ){
		logger.info("进入测试用例集列表页面");
		PageHelper.startPage(pagenum, 15);
		List<CaseSetModel> caseSetList=caseSetService.getAllSet(caseSetModel);
		PageInfo<CaseSetModel> pageinfo=new PageInfo<CaseSetModel>(caseSetList);	
		model.addAttribute("caseSetName",caseSetModel.getCaseSetName());
		model.addAttribute("caseSetList",pageinfo);		
		return "/CaseSet/list";
	}
	
	
	
	@RequestMapping("/CaseSet/addSet")
	public String CaseSet_addSet(Model model){		
		logger.info("新增用例集");
		List<APICaseNew>	APIcaseList=APIcaseNewService.getALLAPICaseNew(null);		
		model.addAttribute("APIcaseList",APIcaseList);		
		return "/CaseSet/addSet";
	}
	
	@RequestMapping("/CaseSet/addSetAction")
	public String CaseSet_addSetAction(CaseSetModel caseSetModel,Model model){
		logger.info("新增用例集Action");
		String cases=caseSetModel.getCaseRelation();
		System.err.println("cases:"+cases);
		if(StringUtils.isNotBlank(cases)){
				int caseCount=cases.split(",").length;				
				int count=caseSetService.addSet(caseSetModel.getCaseSetName(), cases, caseCount, caseSetModel.getRemark());
				if(count>0){			
					return "redirect:list";	
				}	
		}
		return "redirect:/CaseSet/addSet";
	}
	
	@RequestMapping("/CaseSet/editSet/{id}")
	public String CaseSet_editSet(Model model, @PathVariable Integer id){
		logger.info("编辑用例集");
		CaseSetModel caseSet= caseSetService.getSet(id);
		List<APICaseNew>	APIcaseList=APIcaseNewService.getALLAPICaseNew(null);
		model.addAttribute("APIcaseList",APIcaseList);
		model.addAttribute("caseSet",caseSet);		
		return "/CaseSet/editSet";
	}
	
	@RequestMapping("/CaseSet/editSetAction")
	public String CaseSet_editSetAction(CaseSetModel caseSetModel,Model model){
		logger.info("编辑用例集Action");
		String cases=caseSetModel.getCaseRelation();
		int caseCount=cases.split(",").length;
		int count=caseSetService.updateSet(caseSetModel.getId(), cases, caseCount, caseSetModel.getRemark());
		if(count>0){			
			return "redirect:/CaseSet/list";	
		}else{
			model.addAttribute("message", "编辑用例集失败");
			return "err";
		}
	}
	
	@RequestMapping("/CaseSet/del/{id}")
	public String CaseSet_del(Model model, @PathVariable Integer id){
		logger.info("删除用例集<"+id+">");
		int count =caseSetService.delSet(id);
		if(count<=0){
			logger.info("没有删除可用测试集");
		}
		return "/CaseSet/list";
	}
	

	@RequestMapping(value="/CaseSet/Report/{id}",produces="text/plain;charset=utf-8")
	@ResponseBody
	public String viewReport(Model model, @PathVariable Integer id){
		List<Pie_Data> list = new ArrayList<Pie_Data>();
		
		//成功用例
		List<CaseResultModel> caseResult_succ=caseSetService.getSetCaseResult(id,"SUCCESS");		
		Pie_Data succ=new Pie_Data();
		succ.setName("成功");
		succ.setValue(caseResult_succ.size());
		list.add(succ);
		//失败用例
		List<CaseResultModel> caseResult_fail=caseSetService.getSetCaseResult(id,"FAILURE");	
		Pie_Data fail=new Pie_Data();
		fail.setName("失败");
		fail.setValue(caseResult_fail.size());
		list.add(fail);
		//跳过用例
		List<CaseResultModel> caseResult_skip=caseSetService.getSetCaseResult(id,"SKIP");	
		Pie_Data skip=new Pie_Data();
		skip.setName("跳过");
		skip.setValue(caseResult_skip.size());
		list.add(skip);
		
		String data = JSON.toJSONString(list);		        
		return data;
	}
	
	@RequestMapping("/CaseSet/result/{id}")
	public String viewResult(Model model, @PathVariable Integer id){
		logger.info("查看用例集<"+id+">结果");
		CaseSetModel caseSet=caseSetService.getSet(id);
		caseSet.getCaseRelation().split(",");
		//所有用例
		List<CaseResultModel> allResult=caseSetService.getSetCaseResult(id,null);
		model.addAttribute("allResult", allResult);
		
		//成功用例
		List<CaseResultModel> caseResult_succ=caseSetService.getSetCaseResult(id,"SUCCESS");		
		model.addAttribute("count_succ", caseResult_succ.size());
		
		//失败用例
		List<CaseResultModel> caseResult_fail=caseSetService.getSetCaseResult(id,"FAILURE");	
		model.addAttribute("count_fail", caseResult_fail.size());
		
		//失败用例
		List<CaseResultModel> caseResult_skip=caseSetService.getSetCaseResult(id,"SKIP");	
		model.addAttribute("count_skip", caseResult_skip.size());
				
		//获取用例集结果
		CaseSetResultModel caseSetResult=caseSetService.getSetResult(id);
		model.addAttribute("caseSetResult", caseSetResult);
		model.addAttribute("caseSetId", id);		
		return "/CaseSet/resultSet";
	}
	/**
	 * 跟据用例集，用例ID弹出模态框
	 * */
	@RequestMapping("/CaseSet/APIResults")
	@ResponseBody
	public List<CaseResultModel> APIResult_json(CaseResultModel CaseresultModel,Model model){
		logger.info("查看用例集<"+CaseresultModel.getSetId()+">和用例<"+CaseresultModel.getCaseId()+">对应接口的运行结果");
		Integer caseId=CaseresultModel.getCaseId();
		Integer SetId=CaseresultModel.getSetId();
		List<CaseResultModel>  APIResults=APIresultService.getAPIResultBySetId(caseId, SetId);
		for(CaseResultModel APIResult:APIResults){
			Integer APIId=APIResult.getAPIId();
			Integer relationId=APIResult.getRelationId();
			//获取接口信息
			APIModel APImodel=APIservice.getAPI(APIId);			
			APIResult.setApiName(APImodel.getApiName());	
			APIResult.setApiUrl(APImodel.getApiUrl());
			//获取校验内容
			APICaseRelation APIrelation=APIcaseRelationService.getAPIRelation(relationId,caseId, APIId);
			//将断言转换成:|状态码等于'200'|
			String assertcontent=APIrelation.getCaseAssert();
			List<AssertModel> assertModels = JSON.parseArray(assertcontent, AssertModel.class); 
			String content="";
			for(AssertModel ss:assertModels){
				String assertSpell=ss.getAssertItem()+ss.getCompare()+"'"+ss.getExpect()+"'";
				content=content+"|"+assertSpell+"|";
			}			
			APIResult.setCaseAssert(content);
		}
		
		return APIResults;
	}
	
	/***
	 * ------------
	 * 以下方法执行调用TestNG执行测试用例
	 * **/
	
	@RequestMapping(value="/CaseSet/run",method=RequestMethod.POST, produces="text/plain;charset=utf-8")
	@ResponseBody
	public String CaseSet_run(Model model, Integer id,String envTitle){
		logger.info("执行用例集<"+id+">");
		//执行完测试，清空临时表
		tempVarService.truncateVarTemp();
		caseSetid=id;
		runEnv=envTitle;
		bath=System.currentTimeMillis();
		TestNG testng = new TestNG();
		testng.setTestClasses(new Class[] { CaseSetController.class });
		testng.run();		
		//执行完测试，清空临时表
		tempVarService.truncateVarTemp();
		//执行结果同步
//		syncService.SyncData(caseSetid);
		 return "用例执行完成";		
	}
	
	
	@DataProvider(name = "db")
	 public Iterator<Object[]> parameterIntTestProvider() {
		 List<Object[]> dataProvider = new ArrayList<Object[]>();
		 //这种方式不需要添加@ContextConfiguration
		 CaseSetMapper caseSetMapper=SpringContextUtils.getBean(CaseSetMapper.class);

		 //需要加载dao,从数据库获取需要执行的用例
		 CaseSetModel caseSet=caseSetMapper.getSet(caseSetid);
		 
		 APICaseRelationMapper APIcaseRelationMapper=SpringContextUtils.getBean(APICaseRelationMapper.class);
		 
		 String caseRelation=caseSet.getCaseRelation();
		 String[] sp = caseRelation.split(",");
		 for(String cases:sp){
			 Integer caseid=Integer.valueOf(cases);
			 List<APICaseRelation> APIcaseRelations= APIcaseRelationMapper.getAPICaseRelations(caseid);	
			 dataProvider.add(new Object[] {APIcaseRelations});
		 }	 
		 return dataProvider.iterator();		 
	 }
	 
	 
	 @Test(dataProvider="db")
	 public void testCase(List<APICaseRelation> APIcaseRelations){
		 logger.info("执行集合<"+caseSetid+">用例");
		 
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
				APIresultMapper.addAPIResult(caseId, APIcase.getAPIId(),APIcase.getId(), caseSetid, DateFormat.LongToDate(startTime), DateFormat.LongToDate(endTime), endTime-startTime, caseData,statusCode, response,bath);
				
				//保存数据
				handleService.paramExtraction(caseParam, response);
				
				//结果验证
				handleService.resultAssert(caseAssert, statusCode, response);

		 }	
	 }
	
}
