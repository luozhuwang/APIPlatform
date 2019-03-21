package com.xiaoniu.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
import com.xiaoniu.model.APICaseNew;
import com.xiaoniu.model.APICaseRelation;
import com.xiaoniu.model.APIModel;
import com.xiaoniu.model.AssertModel;
import com.xiaoniu.model.CaseResultModel;
import com.xiaoniu.service.APICaseNewService;
import com.xiaoniu.service.APICaseRelationService;
import com.xiaoniu.service.APIResultService;
import com.xiaoniu.service.APIService;
import com.xiaoniu.service.CaseResultService;
import com.xiaoniu.service.HandleService;
import com.xiaoniu.service.HttpClientService;
import com.xiaoniu.service.TempVarService;
import com.xiaoniu.tools.DateFormat;
import com.xiaoniu.tools.listener.SpringContextUtils;

@Listeners({com.xiaoniu.tools.listener.NewTestResultStorageListener.class})
@Controller
public class APICaseNewController {
	private Logger logger =LoggerFactory.getLogger(APICaseNewController.class);

	//执行用例ID
	public static int runid;
		
	//执行环境
	public static String runEnv;
	
	public static long bath;
	
	private Vector<Integer> stack = new Vector<Integer>();
	
	@Autowired
	private TempVarService tempVarService;

	@Autowired
	private APICaseNewService APIcaseNewService;
	
	@Autowired
	private APICaseRelationService APIcaseRelationService;
	
	@Autowired
	private APIService APIservice;
	
	@Autowired
	private APIResultService APIresultService;
	
	@Autowired
	private CaseResultService caseResultService;
		
	
	@RequestMapping("/CaseNew/list")
	public String Case_list(APICaseNew APIcaseNew, @RequestParam(value="pagenum",defaultValue="1") Integer pagenum,Model model ){		
		logger.info("获取用例列表");
		PageHelper.startPage(pagenum, 15);
		List<APICaseNew> runList=APIcaseNewService.getALLAPICaseNew(APIcaseNew);
		PageInfo<APICaseNew> pageinfo=new PageInfo<APICaseNew>(runList);
		List<APIModel> APIList=APIservice.getALLAPI(null);
		model.addAttribute("APIList",APIList);
		model.addAttribute("caseName",APIcaseNew.getCaseName());
		model.addAttribute("CaseList",pageinfo);
		return "/CaseNew/list";
	}
	
	
	@RequestMapping("/CaseNew/addCaseNew")
	public String Case_addNew(Model model){
		logger.info("新增用例");
		List<APICaseNew> APIcaseList=APIcaseNewService.getALLAPICaseNew(null);	
		model.addAttribute("APIcaseList",APIcaseList);
		return "/CaseNew/addCaseNew";
	}
	
	@RequestMapping("/CaseNew/addNewAction")
	public String Case_addNewAction(@RequestBody APICaseNew APIcaseNew,Model model){
		logger.info("新增用例Action");
		String  APIRelations=APIcaseNew.getCaseRelations();
		APIcaseNewService.addAPICaseNew(APIcaseNew.getDependCaseId(), APIcaseNew.getCaseName(), APIcaseNew.getRemark());
		//特殊处理前台传过来的Json数据
		String reg="\\\\";
		if(APIRelations.contains(reg)){
			 APIRelations=APIRelations.replace(reg, "\\\\\\");
		}
		
		int maxCaseId=APIcaseNewService.getMaxCaseId();
		List<APICaseRelation> assertModels = JSON.parseArray(APIRelations, APICaseRelation.class);  
		
		for(APICaseRelation APIrelation:assertModels){
			String caseAssert=APIrelation.getCaseAssert();
			APIcaseRelationService.addAPICaseRelation(maxCaseId, APIrelation.getAPIId() ,APIrelation.getCaseData(), caseAssert, APIrelation.getCaseParam());
		}
		
		return "redirect:/CaseNew/list";	
	}
	
	
	
	@RequestMapping("/CaseNew/del/{id}")
	public String CaseNew_del(Model model, @PathVariable Integer id) throws Exception{
		logger.info("删除用例<"+id+">");
		int count =APIcaseNewService.delAPICaseNew(id);
		if(count<=0){
			throw new Exception("测试用例被测试集引用，删除失败"); 
		}
		return "/CaseNew/list";
	}
	
	@RequestMapping("/CaseNew/edit/{id}")
	public String CaseNew_edit(Model model, @PathVariable Integer id){
		logger.info("编辑用例<"+id+">");
		List<APICaseNew> APIcaseList=APIcaseNewService.getALLAPICaseNew(null);	
		APICaseNew APIcaseNew=APIcaseNewService.getAPICaseNew(id);
		List<APICaseRelation> APICaseRelations= APIcaseRelationService.getAPICaseRelations(id);
		
		//去除本身用例	最好使用Iterator遍历,它内部不做锁定,效率最高,当写多线程时要考虑并发操作的问题!  
		Iterator<APICaseNew> iter = APIcaseList.iterator();
		while(iter.hasNext()){
			APICaseNew APIcase = iter.next();
			    if(APIcase.getId()==id){
			    	iter.remove();
			    }
		}
		model.addAttribute("APIcaseList",APIcaseList);
		
		model.addAttribute("APIcaseNew",APIcaseNew);
		model.addAttribute("APICaseRelations",APICaseRelations);
		return "/CaseNew/editCaseNew";
	}
	
	@RequestMapping("/CaseNew/editAction")
	public String Case_editAction(@RequestBody APICaseNew APIcaseNew,Model model){
		logger.info("编辑用例Action");
		APIcaseNewService.updateAPICaseNew(APIcaseNew.getId(), APIcaseNew.getDependCaseId(), APIcaseNew.getRemark());		
		String  APIRelations=APIcaseNew.getCaseRelations();		
		//特殊处理前台传过来的Json数据
		String reg="\\\\";
		if(APIRelations.contains(reg)){
			 APIRelations=APIRelations.replace(reg, "\\\\\\");
		}
		List<APICaseRelation> assertModels = JSON.parseArray(APIRelations, APICaseRelation.class);  
		for(APICaseRelation APIrelation:assertModels){
			APIcaseRelationService.addAPICaseRelation(APIcaseNew.getId(), APIrelation.getAPIId() ,APIrelation.getCaseData(), APIrelation.getCaseAssert(), APIrelation.getCaseParam());
		}

		return "redirect:/CaseNew/list";	
	}
	
	
	@RequestMapping("/CaseNew/result/{id}")
	 public String viewResult(Model model, @PathVariable Integer id){
		logger.info("查看用例<"+id+">结果");
		String caseName=APIcaseNewService.getAPICaseNew(id).getCaseName();
		//获取用例运行结果
		CaseResultModel caseResult=caseResultService.getCaseResult(id);	
		if(caseResult!=null){
			caseResult.setCaseName(caseName);
			model.addAttribute("caseResult", caseResult);
			
			List<CaseResultModel> resultModels=APIresultService.getAPIResult(id);
			for(CaseResultModel resultModel:resultModels){
				Integer APIId=resultModel.getAPIId();
				Integer relationId=resultModel.getRelationId();
				APIModel  APImodel=APIservice.getAPI(APIId);
				APICaseRelation APIRelation=APIcaseRelationService.getAPIRelation(relationId,id, APIId);
				resultModel.setApiName(APImodel.getApiName());
				resultModel.setApiUrl(APImodel.getApiUrl());	
				
				//将断言转换成:|状态码等于'200'|
				String assertcontent=APIRelation.getCaseAssert();
				List<AssertModel> assertModels = JSON.parseArray(assertcontent, AssertModel.class); 
				String content="";
				for(AssertModel ss:assertModels){
					String assertSpell=ss.getAssertItem()+ss.getCompare()+"'"+ss.getExpect()+"'";
					content=content+"|"+assertSpell+"|";
				}
				resultModel.setCaseAssert(content);
			}
			model.addAttribute("resultModels", resultModels);	
		}else{
			logger.info("用例未运行，无结果");
		}
		
		
		return "/CaseNew/resultCaseNew";
	}
	
//	递归查询是否存在依赖用例--递归算法
	private Vector<Integer>  dependCase(Integer caseId){
		APICaseNewService APIcase=SpringContextUtils.getBean(APICaseNewService.class);
		APICaseNew APICase=APIcase.getAPICaseNew(caseId);
		Integer isRun=APICase.getIsRun();
		Integer dependId=APICase.getDependCaseId();
		
		if(isRun==1){
			logger.info("用例<"+caseId+">已运行");
		}else{
			//堆栈进行存储,直接返回
			stack.add(caseId);
			if(dependId!=0 ){		
				//递归验证
				dependCase(dependId);
			}

		}		
		return stack;
	} 
	
	/***
	 * ------------
	 * 以下方法执行调用TestNG执行测试用例
	 * **/
	@RequestMapping(value="/CaseNew/run",method=RequestMethod.POST, produces="text/plain;charset=utf-8")
	@ResponseBody
	public String Case_run(Model model, Integer id,String envTitle){
		logger.info("执行用例<"+id+">");
		runid=id;
		runEnv=envTitle;
		bath=System.currentTimeMillis();
		TestNG testng = new TestNG();
		testng.setTestClasses(new Class[] { APICaseNewController.class });
		testng.run();
		//执行完测试，清空临时表
		tempVarService.truncateVarTemp();
		 return "用例执行完成";
	}
	
	@DataProvider(name = "db")
	 public Iterator<Object[]> parameterIntTestProvider() {		
		 List<Object[]> dataProvider = new ArrayList<Object[]>();
//		 APICaseNewMapper APIcaseNewMapper=SpringContextUtils.getBean(APICaseNewMapper.class);
		 //这种方式不需要添加@ContextConfiguration
		 APICaseRelationMapper APIcaseRelationMapper=SpringContextUtils.getBean(APICaseRelationMapper.class);		
		//需要加载dao,从数据库获取需要执行的用例
		 List<APICaseRelation> APIcaseRelations= APIcaseRelationMapper.getAPICaseRelations(runid);		 
		 dataProvider.add(new Object[] {APIcaseRelations});
		 return dataProvider.iterator();
		 
		 //检查用例是否执行以及用例的依赖
/*		 Vector<Integer> stackInt=dependCase(runid);
		 Integer aa;
		  try {
				while ((aa = stackInt.lastElement()) != null) {
					logger.info("查询依赖后执行用例---" + aa);
					stackInt.removeElement(aa);
					// break;
					List<APICaseRelation> APIcaseRelations= APIcaseRelationMapper.getAPICaseRelations(aa);		 
					 dataProvider.add(new Object[] {APIcaseRelations});
					 return dataProvider.iterator();
				}
			} catch (Exception e) {
				
			}
		return null;*/		 		 
	 }
	 
	 
	 @Test(dataProvider="db")
	 public void testCase(List<APICaseRelation> APIcaseRelations) {
		 logger.info("执行单个用例测试");
//		 int caseId=APIcaseRelations.get(0).getCaseId();
		 //加载不出来HttpClientService
		 HttpClientService httpOperate=SpringContextUtils.getBean(HttpClientService.class);		 
		 APIMapper APIdao=SpringContextUtils.getBean(APIMapper.class);
		 APIResultMapper APIresultMapper=SpringContextUtils.getBean(APIResultMapper.class);
		 HandleService handleService=SpringContextUtils.getBean(HandleService.class);
		 
		 for(APICaseRelation APIcase:APIcaseRelations){				 
//			 int caseId=APIcase.getCaseId();
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
				APIresultMapper.addAPIResult(APIcase.getCaseId(), APIcase.getAPIId(),APIcase.getId(), 0, DateFormat.LongToDate(startTime), DateFormat.LongToDate(endTime), endTime-startTime, caseData, statusCode,response,bath);
				
				//保存数据
				handleService.paramExtraction(caseParam, response);
				
				//结果验证
				handleService.resultAssert(caseAssert, statusCode, response);
				
		 }
		 
	 }
	 
}
