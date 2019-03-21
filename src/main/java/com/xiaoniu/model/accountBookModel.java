package com.xiaoniu.model;

import java.io.Serializable;
import java.util.List;

public class accountBookModel implements Serializable{
	  /**
	 * 
	 */
	private static final long serialVersionUID = 8210263161574317909L;
	
	private Long id;	  
	  private String transId;
	  private String merchantCode;
	  private String contractId;
	  private String sourceCompany;
	  private Integer periodType;
	  private Integer period;
	  private Integer repayType;
	  private double amount;
	  private double lendAmount;
	  private double singleFee;
	  private double futuresFee;
	  private double intermediaryFee;
	  private double monthFee;
	  private double insuranceFee;
	  private double compositeFee;
	  private Integer productType;
	  private String loanCategory;
	  private String loanFeeType;
	  private Integer type;	  
	  private String accountType;	  
	  private Integer idCardType;
	  private String idCardNo;	  
	  private String borrowerName;
	  private String borrowBankCardNo;
	  private String borrowBankType;
	  private String borrowBankName;
	  private String borrowProvince;
	  private String borrowCity;
	  private String borrowBranch;
	  private String receiverName;
	  private Integer receiverIdCardType;
	  private String receiverIdCardNo;
	  private String receiverMobile;
	  private String receiverBankCardNo;
	  private String receiverBankType;
	  private String receiverBankName;
	  private String receiverProvince;
	  private String receiverCity;
	  private String receiverBranch;
	  private String belonCompany;
	  private String companyType;
	  private String belonId;
	  private Integer signZone;
	  private String signTime;
	  private String auditOpinion;
	  private String purpose;
	  private String notifyUrl;
	  private mortgageModel mortgage;
	  private List<certInfoModel> certInfoList;
	  private String auditNotifyUrl;
	  private String detail;
	  private Long borrowUserId;
	  private String borrowAccName;
	  private Long receiverUserId;
	  private String receiverAccName;
	  private String compensateCorpName;
	  private String compensateCorpAccName;
	  private double exitCharge;
	  private double serviceCharge;
	  private double merchantFee;
	  private Integer merchantFeeType;
	  private double allFee;
	  private String repaySource;
	  private String repaySafeguards;
	  private undueLoanInfoModel undueLoanInfo;
	  private String orgAssetsLevel;
	  private Long productId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	public String getMerchantCode() {
		return merchantCode;
	}
	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getSourceCompany() {
		return sourceCompany;
	}
	public void setSourceCompany(String sourceCompany) {
		this.sourceCompany = sourceCompany;
	}
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
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getLendAmount() {
		return lendAmount;
	}
	public void setLendAmount(double lendAmount) {
		this.lendAmount = lendAmount;
	}
	public double getSingleFee() {
		return singleFee;
	}
	public void setSingleFee(double singleFee) {
		this.singleFee = singleFee;
	}
	public double getFuturesFee() {
		return futuresFee;
	}
	public void setFuturesFee(double futuresFee) {
		this.futuresFee = futuresFee;
	}
	public double getIntermediaryFee() {
		return intermediaryFee;
	}
	public void setIntermediaryFee(double intermediaryFee) {
		this.intermediaryFee = intermediaryFee;
	}
	public double getMonthFee() {
		return monthFee;
	}
	public void setMonthFee(double monthFee) {
		this.monthFee = monthFee;
	}
	public double getInsuranceFee() {
		return insuranceFee;
	}
	public void setInsuranceFee(double insuranceFee) {
		this.insuranceFee = insuranceFee;
	}
	public double getCompositeFee() {
		return compositeFee;
	}
	public void setCompositeFee(double compositeFee) {
		this.compositeFee = compositeFee;
	}
	public Integer getProductType() {
		return productType;
	}
	public void setProductType(Integer productType) {
		this.productType = productType;
	}
	public String getLoanCategory() {
		return loanCategory;
	}
	public void setLoanCategory(String loanCategory) {
		this.loanCategory = loanCategory;
	}
	public String getLoanFeeType() {
		return loanFeeType;
	}
	public void setLoanFeeType(String loanFeeType) {
		this.loanFeeType = loanFeeType;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public Integer getIdCardType() {
		return idCardType;
	}
	public void setIdCardType(Integer idCardType) {
		this.idCardType = idCardType;
	}
	public String getIdCardNo() {
		return idCardNo;
	}
	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}
	public String getBorrowerName() {
		return borrowerName;
	}
	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}
	public String getBorrowBankCardNo() {
		return borrowBankCardNo;
	}
	public void setBorrowBankCardNo(String borrowBankCardNo) {
		this.borrowBankCardNo = borrowBankCardNo;
	}
	public String getBorrowBankType() {
		return borrowBankType;
	}
	public void setBorrowBankType(String borrowBankType) {
		this.borrowBankType = borrowBankType;
	}
	public String getBorrowBankName() {
		return borrowBankName;
	}
	public void setBorrowBankName(String borrowBankName) {
		this.borrowBankName = borrowBankName;
	}
	public String getBorrowProvince() {
		return borrowProvince;
	}
	public void setBorrowProvince(String borrowProvince) {
		this.borrowProvince = borrowProvince;
	}
	public String getBorrowCity() {
		return borrowCity;
	}
	public void setBorrowCity(String borrowCity) {
		this.borrowCity = borrowCity;
	}
	public String getBorrowBranch() {
		return borrowBranch;
	}
	public void setBorrowBranch(String borrowBranch) {
		this.borrowBranch = borrowBranch;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public Integer getReceiverIdCardType() {
		return receiverIdCardType;
	}
	public void setReceiverIdCardType(Integer receiverIdCardType) {
		this.receiverIdCardType = receiverIdCardType;
	}
	public String getReceiverIdCardNo() {
		return receiverIdCardNo;
	}
	public void setReceiverIdCardNo(String receiverIdCardNo) {
		this.receiverIdCardNo = receiverIdCardNo;
	}
	public String getReceiverMobile() {
		return receiverMobile;
	}
	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}
	public String getReceiverBankCardNo() {
		return receiverBankCardNo;
	}
	public void setReceiverBankCardNo(String receiverBankCardNo) {
		this.receiverBankCardNo = receiverBankCardNo;
	}
	public String getReceiverBankType() {
		return receiverBankType;
	}
	public void setReceiverBankType(String receiverBankType) {
		this.receiverBankType = receiverBankType;
	}
	public String getReceiverBankName() {
		return receiverBankName;
	}
	public void setReceiverBankName(String receiverBankName) {
		this.receiverBankName = receiverBankName;
	}
	public String getReceiverProvince() {
		return receiverProvince;
	}
	public void setReceiverProvince(String receiverProvince) {
		this.receiverProvince = receiverProvince;
	}
	public String getReceiverCity() {
		return receiverCity;
	}
	public void setReceiverCity(String receiverCity) {
		this.receiverCity = receiverCity;
	}
	public String getReceiverBranch() {
		return receiverBranch;
	}
	public void setReceiverBranch(String receiverBranch) {
		this.receiverBranch = receiverBranch;
	}
	public String getBelonCompany() {
		return belonCompany;
	}
	public void setBelonCompany(String belonCompany) {
		this.belonCompany = belonCompany;
	}
	public String getCompanyType() {
		return companyType;
	}
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	public String getBelonId() {
		return belonId;
	}
	public void setBelonId(String belonId) {
		this.belonId = belonId;
	}
	public Integer getSignZone() {
		return signZone;
	}
	public void setSignZone(Integer signZone) {
		this.signZone = signZone;
	}
	public String getSignTime() {
		return signTime;
	}
	public void setSignTime(String signTime) {
		this.signTime = signTime;
	}
	public String getAuditOpinion() {
		return auditOpinion;
	}
	public void setAuditOpinion(String auditOpinion) {
		this.auditOpinion = auditOpinion;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public mortgageModel getMortgage() {
		return mortgage;
	}
	public void setMortgage(mortgageModel mortgage) {
		this.mortgage = mortgage;
	}
	public List<certInfoModel> getCertInfoList() {
		return certInfoList;
	}
	public void setCertInfoList(List<certInfoModel> certInfoList) {
		this.certInfoList = certInfoList;
	}
	public String getAuditNotifyUrl() {
		return auditNotifyUrl;
	}
	public void setAuditNotifyUrl(String auditNotifyUrl) {
		this.auditNotifyUrl = auditNotifyUrl;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Long getBorrowUserId() {
		return borrowUserId;
	}
	public void setBorrowUserId(Long borrowUserId) {
		this.borrowUserId = borrowUserId;
	}
	public String getBorrowAccName() {
		return borrowAccName;
	}
	public void setBorrowAccName(String borrowAccName) {
		this.borrowAccName = borrowAccName;
	}
	public Long getReceiverUserId() {
		return receiverUserId;
	}
	public void setReceiverUserId(Long receiverUserId) {
		this.receiverUserId = receiverUserId;
	}
	public String getReceiverAccName() {
		return receiverAccName;
	}
	public void setReceiverAccName(String receiverAccName) {
		this.receiverAccName = receiverAccName;
	}
	public String getCompensateCorpName() {
		return compensateCorpName;
	}
	public void setCompensateCorpName(String compensateCorpName) {
		this.compensateCorpName = compensateCorpName;
	}
	public String getCompensateCorpAccName() {
		return compensateCorpAccName;
	}
	public void setCompensateCorpAccName(String compensateCorpAccName) {
		this.compensateCorpAccName = compensateCorpAccName;
	}
	public double getExitCharge() {
		return exitCharge;
	}
	public void setExitCharge(double exitCharge) {
		this.exitCharge = exitCharge;
	}
	public double getServiceCharge() {
		return serviceCharge;
	}
	public void setServiceCharge(double serviceCharge) {
		this.serviceCharge = serviceCharge;
	}
	public double getMerchantFee() {
		return merchantFee;
	}
	public void setMerchantFee(double merchantFee) {
		this.merchantFee = merchantFee;
	}
	public double getAllFee() {
		return allFee;
	}
	public void setAllFee(double allFee) {
		this.allFee = allFee;
	}
	public String getRepaySource() {
		return repaySource;
	}
	public void setRepaySource(String repaySource) {
		this.repaySource = repaySource;
	}
	public String getRepaySafeguards() {
		return repaySafeguards;
	}
	public void setRepaySafeguards(String repaySafeguards) {
		this.repaySafeguards = repaySafeguards;
	}
	public undueLoanInfoModel getUndueLoanInfo() {
		return undueLoanInfo;
	}
	public void setUndueLoanInfo(undueLoanInfoModel undueLoanInfo) {
		this.undueLoanInfo = undueLoanInfo;
	}
	public String getOrgAssetsLevel() {
		return orgAssetsLevel;
	}
	public void setOrgAssetsLevel(String orgAssetsLevel) {
		this.orgAssetsLevel = orgAssetsLevel;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Integer getMerchantFeeType() {
		return merchantFeeType;
	}
	public void setMerchantFeeType(Integer merchantFeeType) {
		this.merchantFeeType = merchantFeeType;
	}
	  
	  
}
