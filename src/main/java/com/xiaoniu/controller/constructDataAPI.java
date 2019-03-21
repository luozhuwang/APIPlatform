package com.xiaoniu.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xiaoniu.httpclient.HttpClientOperate;
import com.xiaoniu.httpclient.HttpResult;
import com.xiaoniu.model.AuditLoanModel;
import com.xiaoniu.model.LoanDetailModel;
import com.xiaoniu.model.MessageResult;
import com.xiaoniu.model.ResponseDetailModel;
import com.xiaoniu.model.Result;
import com.xiaoniu.model.loanDataModel;
import com.xiaoniu.model.loanResult;
import com.xiaoniu.service.TelnetService;
import com.xiaoniu.service.construcatDataService;
import com.xiaoniu.tools.Generator.RandomUtil;

/**
 * 构造数据提供API接口
 * */
@Controller
public class constructDataAPI {
	private Logger logger=LoggerFactory.getLogger(constructDataAPI.class);

	@Autowired
	private HttpClientOperate httpClientOperate;

	@Autowired
	private construcatDataService construcatDataservice;
	
	@Autowired
	private TelnetService telnetService;
	
	@RequestMapping(value="/API/loan",method=RequestMethod.POST)
	@ResponseBody
	public loanResult<Result> importLoan(loanDataModel loanDataModel,Model model) throws Exception{
		List<String> serialIds=new ArrayList<String>();
		List<Long> loanIds=new ArrayList<Long>();
		String RequestIp=loanDataModel.getEnvIp();
		String telnetIp=RequestIp.replace(RequestIp.subSequence(RequestIp.length()-1, RequestIp.length()), "5");
		int status=loanDataModel.getSignStatus();
		telnetService.connect(telnetIp, 6038);
		int sucessRows=0;
		loanResult<Result> loanresult=new loanResult<Result>();
		List<Result> resultList=new ArrayList<Result>();
		String url="http://"+RequestIp+":9038/loan/api/import/loan";				
		int number=loanDataModel.getNum();
		loanresult.setRows(number);
		if(number>=1){	
			for(int i=0;i<number;i++){
				Result result=new Result();
				String contractid=RandomUtil.getRndNumByLen(20);
				result.setContractId(contractid);
				result.setTransId(contractid);
				String json=construcatDataservice.importLoan(contractid,loanDataModel);
				HttpResult Httpresult=httpClientOperate.doPostEncrypt(url, json);
				String responseResult=Httpresult.getContent();
				if(responseResult.equals("{\"returnMsg\":\"OK\"}")){
					result.setStatus("succ");
					result.setReturnMsg("ok");
					sucessRows++;
					//通过合同号查询serialId和id
					Map<String,Object> detail=loanDetail(contractid);
					loanIds.add((Long) detail.get("loanId"));
					serialIds.add("\""+detail.get("SerialId").toString()+"\"");
				}else{
					result.setStatus("fail");
					result.setReturnMsg(responseResult);
				}
				resultList.add(result);
			}
			loanresult.setResults(resultList);
			//执行风控定时任务
			construcatDataservice.loanRisk();
//			//风控初审和复审
			construcatDataservice.auditRisk(serialIds);
			if(status ==1){				
				//审核资产
				String auditResponse=construcatDataservice.auditLoan(loanIds, "1");
				AuditLoanModel auditLoanResult=JSON.parseObject(auditResponse, AuditLoanModel.class);
				int fail=auditLoanResult.getFail();
				if(fail ==0){				
					construcatDataservice.sendLoan();
					logger.info("资产导入成功，审核完成");
				}else{
					logger.info("资产导入成功,审核失败，请确认以下条件：1.是否授权\n2.请核实借款状态！（如需合同签章，请上传完合同在审核！）");
				}
			}
		}else{
			loanresult.setResults(null);
			throw new Exception("导入资产数量必须大于等于1");
		}
		loanresult.setSucessRows(sucessRows);
		telnetService.disconnect();
		return loanresult;
		
	}
	
	@RequestMapping(value="/API/prePayment",method=RequestMethod.POST)
	@ResponseBody
	public ResponseDetailModel<Boolean> prePay(String EnvIp,String transId,Model model) throws InterruptedException{
		String telnetIp=EnvIp.replace(EnvIp.subSequence(EnvIp.length()-1, EnvIp.length()), "5");
		telnetService.connect(telnetIp, 6038);
		//通过合同号查询serialId和id
		Map<String,Object> detail=loanDetail(transId);
		long loanId=(long) detail.get("loanId");
		ResponseDetailModel<Boolean> message=construcatDataservice.prePay(loanId);
		telnetService.disconnect();
		return message;
	}
	
	
	private Map<String,Object>  loanDetail(String transId){
		Map<String,Object> detailObject=new HashMap<String, Object>();
		String detail=construcatDataservice.getLoan(transId);
		
		MessageResult<LoanDetailModel> messageResult=JSON.parseObject(detail, new TypeReference<MessageResult<LoanDetailModel>>(){});
		detailObject.put("loanId", messageResult.getData().getId());
		detailObject.put("SerialId",messageResult.getData().getSerialId());
		
		return detailObject;
	}
	
}

