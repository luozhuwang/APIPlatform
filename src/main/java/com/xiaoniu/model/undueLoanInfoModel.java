package com.xiaoniu.model;

import java.io.Serializable;

public class undueLoanInfoModel implements Serializable{

	private static final long serialVersionUID = -219125103337921954L;
	private String fundUse;
	  private String financialCondition;
	  private String repayAbilityChange;
	  private String overdue;
	  private String litigation;
	  private String punishment;
	  
	public String getFundUse() {
		return fundUse;
	}
	public void setFundUse(String fundUse) {
		this.fundUse = fundUse;
	}
	public String getFinancialCondition() {
		return financialCondition;
	}
	public void setFinancialCondition(String financialCondition) {
		this.financialCondition = financialCondition;
	}
	public String getRepayAbilityChange() {
		return repayAbilityChange;
	}
	public void setRepayAbilityChange(String repayAbilityChange) {
		this.repayAbilityChange = repayAbilityChange;
	}
	public String getOverdue() {
		return overdue;
	}
	public void setOverdue(String overdue) {
		this.overdue = overdue;
	}
	public String getLitigation() {
		return litigation;
	}
	public void setLitigation(String litigation) {
		this.litigation = litigation;
	}
	public String getPunishment() {
		return punishment;
	}
	public void setPunishment(String punishment) {
		this.punishment = punishment;
	}
	  
}
