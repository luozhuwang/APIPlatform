package com.xiaoniu.serviceImp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xiaoniu.model.AuditLoanModel;
import com.xiaoniu.model.LoanDetailModel;
import com.xiaoniu.model.MessageResult;
import com.xiaoniu.model.ResponseDetailModel;
import com.xiaoniu.model.accountBookModel;
import com.xiaoniu.model.certInfoModel;
import com.xiaoniu.model.loanDataModel;
import com.xiaoniu.model.mortgageModel;
import com.xiaoniu.model.undueLoanInfoModel;
import com.xiaoniu.service.TelnetService;
import com.xiaoniu.service.construcatDataService;
import com.xiaoniu.tools.Constant;

@Service
public class construcatDataServiceImp implements construcatDataService{	
	private Logger logger=LoggerFactory.getLogger(construcatDataServiceImp.class);
	
	@Autowired
	private TelnetService telnetService;
	
	@Override
	public String importLoan(String contractid,loanDataModel loanDataModel) {
		logger.info("导入资产");
		accountBookModel accountBook=new accountBookModel();
		
		int SingleFee=10;
		int lentamount=loanDataModel.getAmount()*(100-SingleFee)/100;
		
		accountBook.setAccountType("0");
		accountBook.setAllFee(0);
		accountBook.setAmount(loanDataModel.getAmount());
		accountBook.setAuditNotifyUrl("http://172.20.20.160:8281/xnzx-test-project/import/loan/audit/notity.action");
		accountBook.setAuditOpinion("风控意见风控意见风控意见");
		accountBook.setBelonCompany("a");
		accountBook.setBorrowBankCardNo(loanDataModel.getBorrowBankCardNo());
		accountBook.setBorrowBankType("ICBC");
		accountBook.setBorrowBranch("中国工商银行南山支行");
		accountBook.setBorrowCity("深圳市");
		accountBook.setBorrowProvince("广东省");
		accountBook.setBorrowerName(loanDataModel.getBorrowerName());
		//certInfoList
		List<certInfoModel> certInfoList=null;
		accountBook.setCertInfoList(certInfoList);
		
		accountBook.setCompanyType("n");
		accountBook.setContractId(contractid);
		accountBook.setDetail("借款详情");
		accountBook.setExitCharge(100.11);
		accountBook.setFuturesFee(0);
		accountBook.setIdCardNo(loanDataModel.getIdCardNo());
//		0：其他证件  1：中国大陆居民身份证  2: 台湾居民来往大陆通行证  3: 企业营业执照编号  4: 港澳居民来往内地通行证
		accountBook.setIdCardType(1);
		accountBook.setInsuranceFee(8);
		accountBook.setIntermediaryFee(0);
		accountBook.setLendAmount(lentamount);
		accountBook.setLoanCategory("a1");
		accountBook.setLoanFeeType("借款费用类别1");
		accountBook.setMerchantFee(0);
		accountBook.setMerchantFeeType(3);
		accountBook.setMonthFee(loanDataModel.getMonthFee());
		//
		mortgageModel mortgage=null;
		accountBook.setMortgage(mortgage);
		accountBook.setNotifyUrl("http://172.20.20.160:8281/xnzx-test-project/import/loan/notity.action");
		accountBook.setOrgAssetsLevel("A");
		accountBook.setPeriod(loanDataModel.getPeriod());
		accountBook.setPeriodType(loanDataModel.getPeriodType());
		accountBook.setProductType(1);
		accountBook.setPurpose("个人消费");
		accountBook.setReceiverBankCardNo(loanDataModel.getBorrowBankCardNo());
		accountBook.setReceiverBankType("ICBC");
		accountBook.setReceiverBranch("中国工商银行南山支行");
		accountBook.setReceiverCity("深圳市");
		accountBook.setReceiverIdCardNo(loanDataModel.getIdCardNo());			
//		0：其他证件  1：中国大陆居民身份证  2: 台湾居民来往大陆通行证  3: 企业营业执照编号  4: 港澳居民来往内地通行证		
		accountBook.setReceiverIdCardType(1);
		accountBook.setReceiverMobile(loanDataModel.getReceiverMobile());
		accountBook.setReceiverName(loanDataModel.getBorrowerName());
		accountBook.setReceiverProvince("广东省");
		accountBook.setRepaySafeguards("还款保障措施");
		accountBook.setRepaySource("还款来源");
		accountBook.setRepayType(loanDataModel.getRepayType());
		accountBook.setServiceCharge(100.22);
		accountBook.setSignTime("2019/03/11");
		accountBook.setSignZone(0);
		accountBook.setSingleFee(SingleFee);
		accountBook.setSourceCompany("普惠");
		accountBook.setTransId(contractid);
		accountBook.setType(0);
		undueLoanInfoModel undueLoanInfo=null;
		accountBook.setUndueLoanInfo(undueLoanInfo);;
		

		String json=JSON.toJSONString(accountBook);

		return json;
		
	}

	@Override
	public void loanRisk() {
		try {
			logger.info("执行'资产风控任务'定时任务");
			telnetService.execute("invoke "+Constant.loanRisk+"()");
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getLoan(String contractid) {
		logger.info("通过合同<"+contractid+">查询资产信息");
		String param="\""+contractid+"\""+","+"\""+Constant.merChantCode+"\"";
		//invoke com.neo.xnol.loan.facade.AccountBookQueryFacade.querySimpleAccountBookBySerialId("605873578562303488","100001")
		String result=telnetService.execute("invoke "+Constant.queryLoan+"("+param+")");
		return result;		
	}

	@Override
	public void auditRisk(List<String> serialId) {
		logger.info("风控审核中");
			//com.neo.xnol.loan.facade.LoanRiskFacade.auditRisk({"list":["WR0995aed71a8815b7200384aa3be"],"auditType":"RISK_FIRST","status":"PASS","detail":"风控审核通过","auditUser":"0"})
			String First="{\"list\":"+serialId+",\"auditType\":\"RISK_FIRST\",\"status\":\"PASS\",\"detail\":\"风控初审核通过\",\"auditUser\":\"0\"}";
			String Second="{\"list\":"+serialId+",\"auditType\":\"RISK_SECOND\",\"status\":\"PASS\",\"detail\":\"风控复审通过\",\"auditUser\":\"0\"}";
			telnetService.execute("invoke "+Constant.auditRisk+"("+First+")");
			telnetService.execute("invoke "+Constant.auditRisk+"("+Second+")");
	}

	@Override
	public String auditLoan(List<Long> data, String approveStatus) {
		logger.info("审核资产");
		//com.neo.xnol.loan.facade.AccountBookFacade.auditLoan({"operatorId":0,"approveStatus":"1","remark":"通过","assetType":4,"data":[176812],"isIgnoreException":false})
		String remark=null;
		if(approveStatus.equals("1")){
			remark="审核通过";
		}else if(approveStatus.equals("6")){
			remark="审核不通过";
		}
		String param="{\"operatorId\":0,\"approveStatus\":\""+approveStatus+"\",\"remark\":\""+remark+"\",\"assetType\":4,\"data\":"+data+",\"isIgnoreException\":false}";
		String result=telnetService.execute("invoke "+Constant.auditLoan+"("+param+")");
		return result;
	}

	@Override
	public void sendLoan() {
		logger.info("执行'借款推送至理财端'定时任务");
		for(int i=0;i<2;i++){
			try {
				Thread.sleep(1000);
				logger.info("第"+(i+1)+"次执行推送产品定时任务");
				telnetService.execute("invoke "+Constant.sendLoanAssets+"()");
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
	}

	@Override
	public ResponseDetailModel<Boolean> prePay(Long loanId) throws InterruptedException {
		logger.info("资产提前还款");
		String param=loanId+",0,0";
		String result=telnetService.execute("invoke "+Constant.setRepay+"("+param+")");
		ResponseDetailModel<Boolean> responseDetail=JSON.parseObject(result, new TypeReference<ResponseDetailModel<Boolean>>(){});

		boolean data=responseDetail.getData();
		if(data){
			telnetService.execute("invoke "+Constant.advanceRepay+"()");
			Thread.sleep(1000);
			
		}	
		return responseDetail;
	}

	
	
}
