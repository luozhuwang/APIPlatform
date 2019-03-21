package com.xiaoniu.model;

import java.io.Serializable;

public class APICaseModel implements Serializable{

	private static final long serialVersionUID = -4071134482024648111L;

	private Integer id;
	private String caseData;
	private String caseName;
	private Integer APIId;
	private String caseAssert;
	private String caseParam;
	private String remark;
	private String createtime;
	private String updatetime;
	

	public String getCaseData() {
		return caseData;
	}
	public void setCaseData(String caseData) {
		this.caseData = caseData;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCaseName() {
		return caseName;
	}
	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}
	public String getCaseAssert() {
		return caseAssert;
	}
	public void setCaseAssert(String caseAssert) {
		this.caseAssert = caseAssert;
	}
	public String getCaseParam() {
		return caseParam;
	}
	public void setCaseParam(String caseParam) {
		this.caseParam = caseParam;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAPIId() {
		return APIId;
	}
	public void setAPIId(Integer aPIId) {
		APIId = aPIId;
	}	
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	
	@Override
	public String toString() {
		return "APICaseModel [id=" + id + ", caseData=" + caseData
				+ ", caseName=" + caseName + ", APIId=" + APIId
				+ ", caseAssert=" + caseAssert + ", caseParam=" + caseParam
				+ ", remark=" + remark + ", createtime=" + createtime
				+ ", updatetime=" + updatetime + "]";
	}
	
	
}
