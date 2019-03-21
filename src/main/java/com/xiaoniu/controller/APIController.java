package com.xiaoniu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoniu.model.APIModel;
import com.xiaoniu.service.APIService;


@Controller
public class APIController {
	private Logger logger = LoggerFactory.getLogger(APIController.class); 
	
	
	@Autowired
	private APIService APIservice;
	
	/**
	 * 提供测试页面分页显示接口
	 * */
	@RequestMapping("/Interface/jsonlist")
	@ResponseBody
	public Map<String,Object> API_json(APIModel APImodel,int page,Model model){
		logger.info("获取所有接口JSON数据");
		int pageSize=5;
		//PageHelper 后台分页插件
		PageHelper.startPage(page, pageSize);
		Map<String,Object> modelMap = new HashMap<String,Object>();
		//查询启用的接口
		APImodel.setApiStatus(0);
		List<APIModel> jsonlist=APIservice.getALLAPI(APImodel);				
		PageInfo<APIModel> pageInfo = new PageInfo<APIModel>(jsonlist);		
		int totalPage = pageInfo.getPages();//总页数
		 
		modelMap.put("page", page);
		modelMap.put("list", jsonlist);
		modelMap.put("totalPage", totalPage);
		modelMap.put("pageSize",pageSize);
		
		return modelMap;
	}
	
	@RequestMapping("/Interface/list")
	public String API_list(APIModel APImodel,@RequestParam(value="pagenum",defaultValue="1") Integer pagenum,Model model ){
		logger.info("进入接口列表页面");
		PageHelper.startPage(pagenum, 15);
		List<APIModel> APIList  =APIservice.getALLAPI(APImodel);
		PageInfo<APIModel> pageinfo=new PageInfo<APIModel>(APIList);		
		model.addAttribute("apiName",APImodel.getApiName());
		model.addAttribute("apiMethod",APImodel.getApiMethod());
		model.addAttribute("apiStatus",APImodel.getApiStatus());
		model.addAttribute("APIList",pageinfo);
		return "/API/list";
	}
	@RequestMapping("/Interface/addAPI")
	public String API_add(){
		logger.info("进入接口新增页面");
		return "/API/addAPI";
	}
	
	@RequestMapping("/Interface/addAPIAction")
	public String API_addAction(@Valid @ModelAttribute("APImodel") APIModel APImodel, BindingResult result,Model model){
		logger.info("添加接口Action");	
		if(!result.hasErrors()){
			int count=APIservice.addAPI(APImodel.getApiName(),APImodel.getApiHost(),APImodel.getApiUrl(),APImodel.getApiMethod(),APImodel.getApiHeaders(),APImodel.getApiParams(),APImodel.getApiStatus(),APImodel.getRemark());
			if(count>0){			
				return "redirect:list";	
			}		
		}
		model.addAttribute("APImodel", APImodel);
		return "/API/addAPI";
	}
	@RequestMapping("/Interface/editAPI/{id}")
	public String API_edit(Model model, @PathVariable Integer id){
		logger.info("进入接口编辑页面");
		APIModel API  =APIservice.getAPI(id);
		model.addAttribute("APImodel",API);
		return "/API/editAPI";
	}

	@RequestMapping("/Interface/editAPIAction")
	public String API_editAction(@Valid @ModelAttribute("APImodel") APIModel APImodel, BindingResult result,Model model){
		logger.info("编辑接口Action");
		if(!result.hasErrors()){
			int count=APIservice.updateAPI(APImodel.getId(),APImodel.getApiName(),APImodel.getApiHost(),APImodel.getApiUrl(),APImodel.getApiMethod(),APImodel.getApiHeaders(),APImodel.getApiParams(),APImodel.getApiStatus(),APImodel.getRemark());
			if(count>0){			
				return "redirect:list";	
			}		
		}
		model.addAttribute("APImodel", APImodel);
		return "/API/editAPI";
	}
	
	@RequestMapping("/Interface/del/{id}")
	public String API_del(Model model, @PathVariable Integer id){
		logger.info("删除接口");
		int count =APIservice.delAPI(id);
		if(count<=0){
			logger.info("没有删除可用测试用例");			
		}
		return "/API/list";
	}
	
//	@RequestMapping("/Interface/run/{id}")	
//	public String API_run(Model model, @PathVariable Integer id){
//		logger.info("调试接口");
//		//获取待调试接口
//		APIModel API  =APIservice.getAPI(id);
//		String method=API.getApiMethod();
//		String URL=API.getApiHost()+API.getApiUrl();
//		String param=API.getApiParams();
//		//发送请求
//		HttpResult result=Httpoperate.doSend(method, URL, param);
//		String response=result.getContent();
//		model.addAttribute("response", response);
//		return "/API/result";
//	}
}
