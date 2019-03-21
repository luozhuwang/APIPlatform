package com.xiaoniu.model;

public class loanDataModel {
	
	private Integer Num;//导入资产数量
	private String EnvIp;//环境IP
	private Integer periodType;//借款期限类型:0：天/1：月
	private Integer	period;//借款期限
	/**	
	 还款方式:
	0：一次性还本付息
	1：等额本息
	3：先息后本
	5：等本等息
	*/
	private Integer repayType;//还款方式
	private Integer amount;//合同金额
	private double  monthFee;//借款人年利率	例：16.8
	private String borrowerName;//借款人
	private String idCardNo;//证件号码
	private String borrowBankCardNo;//银行账户
	private String receiverMobile;//手机号码
	private Integer signStatus;	// 1 发标 2 不发标
	
	public Integer getPeriodType() {
		return periodType;
	}
	public void setPeriodType(Integer periodType) {
		this.periodType = periodType;
	}
	public Integer getPeriod() {
		return period;
	}
	public void setPeriod(Integer period) {
		this.period = period;
	}
	public Integer getRepayType() {
		return repayType;
	}
	public void setRepayType(Integer repayType) {
		this.repayType = repayType;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public double getMonthFee() {
		return monthFee;
	}
	public void setMonthFee(double monthFee) {
		this.monthFee = monthFee;
	}
	public String getBorrowerName() {
		return borrowerName;
	}
	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}
	public String getIdCardNo() {
		return idCardNo;
	}
	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}
	public String getBorrowBankCardNo() {
		return borrowBankCardNo;
	}
	public void setBorrowBankCardNo(String borrowBankCardNo) {
		this.borrowBankCardNo = borrowBankCardNo;
	}
	public String getReceiverMobile() {
		return receiverMobile;
	}
	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}
	public String getEnvIp() {
		return EnvIp;
	}
	public void setEnvIp(String envIp) {
		EnvIp = envIp;
	}
	public Integer getNum() {
		return Num;
	}
	public void setNum(Integer num) {
		Num = num;
	}
	public Integer getSignStatus() {
		return signStatus;
	}
	public void setSignStatus(Integer signStatus) {
		this.signStatus = signStatus;
	}
	
}
