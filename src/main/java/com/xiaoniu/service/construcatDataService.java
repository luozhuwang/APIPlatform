package com.xiaoniu.service;

import java.util.List;

import com.xiaoniu.model.ResponseDetailModel;
import com.xiaoniu.model.loanDataModel;

public interface construcatDataService {
	//导资产
	public String  importLoan(String contractid,loanDataModel loanDataModel);
	//执行风控
	public void loanRisk();
	//获取资产信息
	public String getLoan(String contractid);
	//风控审核
	public void auditRisk(List<String> serialId);
	//资产审核
	public String auditLoan(List<Long> data,String approveStatus);
	//推送资产
	public void sendLoan();
	//资产提前还款
	public ResponseDetailModel<Boolean> prePay(Long loanId) throws InterruptedException;
}
