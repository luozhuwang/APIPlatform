package com.xiaoniu.serviceImp;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.testng.Assert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.xiaoniu.model.AssertModel;
import com.xiaoniu.model.EnvInfoModel;
import com.xiaoniu.model.EnvVarModel;
import com.xiaoniu.model.ParamModel;
import com.xiaoniu.service.EnvInfoService;
import com.xiaoniu.service.EnvVarService;
import com.xiaoniu.service.HandleService;
import com.xiaoniu.service.TempVarService;
import com.xiaoniu.tools.RegexUtils;
import com.xiaoniu.tools.Generator.ChineseIDCardNumberGenerator;
import com.xiaoniu.tools.Generator.RandomUtil;

@Controller
public class HandleServiceImp implements HandleService {
	private Logger logger = LoggerFactory.getLogger(HandleServiceImp.class);

	@Autowired
	private EnvVarService varService;

	@Autowired
	private TempVarService tempVarService;
	
	@Autowired
	private EnvInfoService envInfoService;

	@Override
	public String replaceParam(String requestData) {
		String returnData = null;
		List<String> vars = null;
		if (requestData != null && !requestData.equals("")) {
			vars = RegexUtils.resolve_Reg_Array(requestData,"(\\$\\{.+?\\})");
			if (0 != vars.size()) {
				// 从初始化变量中获取变量值
				for (String var : vars) {
					String varModify = var.replace("$", "").replace("{", "").replace("}", "");
					String value = varService.getVarByName(varModify).getVarValue();
					logger.info("替换的变量：" + var);
					logger.info("变量值：" + value);
					requestData = requestData.replace(var, value);
				}			
				returnData = requestData;
			} else {
				returnData = requestData;
				logger.info("请求参数没有需要替换的变量");
			}
			return returnData;
		}else {
			logger.info("参数为空，不需要替换");
			return null;
		}
	}	
	
	@Override
	public void resultAssert(String AssertContent, Integer statusCode,String response) {
		if (AssertContent != null && !AssertContent.equals("")) {
			ArrayList<AssertModel> datas = JSON.parseObject(AssertContent,new TypeReference<ArrayList<AssertModel>>() {});
			for (AssertModel data : datas) {
				String compare = data.getCompare();
				String Expect = data.getExpect();
				String Item = data.getAssertItem();
				if (Item.equals("状态码")) {
					if (compare.equals("等于")) {
						Assert.assertEquals(statusCode, Integer.valueOf(Expect));
					} else if (compare.equals("不等于")) {
						Assert.assertNotEquals(statusCode,
								Integer.valueOf(Expect));
					}
				} else {
					if (compare.equals("包含")) {
						Assert.assertTrue(response.contains(Expect));
					} else if (compare.equals("等于")) {
						Assert.assertEquals(response, Expect);
					} else if (compare.equals("不等于")) {
						Assert.assertNotEquals(response, Expect);
					}
				}
			}
		} else {
			logger.info("未设置需要校验的参数");
		}

	}

	@Override
	public void paramExtraction(String Params, String response) {
		if (Params != null && !Params.equals("")) {
			ArrayList<ParamModel> params = JSON.parseObject(Params,new TypeReference<ArrayList<ParamModel>>() {});
			for (ParamModel param : params) {
				String variable = param.getParam();
				String method_value = param.getMethod();
				String rule = param.getRule();
				if (method_value.equals("JSON")) {
					// 只能解析简单的json数据,数组或者多层大括号无法解析
					// {"status":false,"errorTimes":0,"maxErrorTimes":5,"resultCode":"10","tokenName":"3278f33f8727","tokenValue":"16SLTQ57L04FW"}
					try {
						JSONObject jsonObject = JSONObject
								.parseObject(response);
						String value = jsonObject.get(rule).toString();
						tempVarService.addTempVar(variable, "JSON", value);
					} catch (Exception e) {
						logger.info("response非标准JSON或json中包含数组或Json包含多个对象，序列化失败");
					}
				} else if (method_value.equals("Regular")) {
					// 正则匹配
					String value = RegexUtils.resolve_Reg(response, rule);
					tempVarService.addTempVar(variable, rule, value);
				}
			}
		} else {
			logger.info("未设置需要提取的参数");
		}
	}

	/**
	 * 替换临时变量
	 * %{abc}%		20位随机数字
	 * &{IdCard}& 	随机身份证
	 * #{Temp}#		请求后的临时结果
	 * */
	@Override
	public String tempVar(String requestData, String reg) {
		String returnData = null;
		List<String> vars = null;
		if (requestData != null && !requestData.equals("")) {
			vars = RegexUtils.resolve_Reg_Array(requestData,reg);
			if (0 != vars.size()) {
				for (String var : vars) {
					String value="";
					String varModify = "";					
					if(var.contains("%{") && var.contains("}%")){
						//生成20位随机数字
						varModify = var.replace("%{", "").replace("}%", "");
						value=RandomUtil.getRndNumByLen(20);
					}else if(var.contains("&{") && var.contains("}&")){
						varModify = var.replace("&{", "").replace("}&", "");
						value=ChineseIDCardNumberGenerator.getInstance().generate();
					}else if(var.contains("#{") && var.contains("}#")){
						varModify = var.replace("#{", "").replace("}#", "");
					}
					EnvVarModel  varModel=tempVarService.getTempVarByName(varModify);
					if(varModel !=null){
						logger.info("临时变量已存在");
						value=varModel.getVarValue();
					}else{
						logger.info("添加临时变量");						
						tempVarService.addTempVar(varModify, "Random", value);							
					}
					logger.info("临时变量：" + var);
					logger.info("临时变量值：" + value);
					requestData = requestData.replace(var, value);
				}
				returnData = requestData;
			}else{
				returnData = requestData;
				logger.info("请求参数没有需要替换的临时变量");
			}
			return returnData;
		}else{
			logger.info("参数为空，不需要替换临时变量");
			return null;
		}
	}

	
	@Override
	public String ServerHost(String requestData, String runEnv) {
		String returnData = null;
		List<String> vars = null;
		if(requestData != null && !requestData.equals("")){
			vars = RegexUtils.resolve_Reg_Array(requestData,"##\\{.+?\\}##");
			if (0 != vars.size()) {
				for (String var : vars) {
					EnvInfoModel envModel=new EnvInfoModel();
//					String varModify = var.replace("#{", "").replace("}#", "");
					envModel.setEnvTitle(runEnv);
					envModel.setEnvDomain("www.app.com");
					String value =envInfoService.getServer(envModel).getEnvIp();
					logger.info("替换的变量：" + var);
					logger.info("变量值：" + value);
					requestData = requestData.replace(var, value);
				}
				returnData = requestData;
			}else{
				returnData = requestData;
				logger.info("请求参数没有需要替换的AppServerIP");
			}	
			return returnData;
		}else{
			logger.info("参数为空，不需要替换AppServerIP");
			return null;
		}
	}

	

}
