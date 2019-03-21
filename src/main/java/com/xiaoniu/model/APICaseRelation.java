package com.xiaoniu.model;

import java.io.Serializable;

public class APICaseRelation extends APIModel implements Serializable{

	private static final long serialVersionUID = -1520719454944051879L;
	
	private Integer id;
	private Integer caseId;
	private Integer APIId;
	private String caseData;
	private String caseAssert;
	private String caseParam;
	private String createTime;
	private String updateTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCaseId() {
		return caseId;
	}
	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}
	public Integer getAPIId() {
		return APIId;
	}
	public void setAPIId(Integer aPIId) {
		APIId = aPIId;
	}
	public String getCaseData() {
		return caseData;
	}
	public void setCaseData(String caseData) {
		this.caseData = caseData;
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	@Override
	public String toString() {
		return "APICaseRelation [id=" + id + ", caseId=" + caseId + ", APIId="
				+ APIId + ", caseData=" + caseData + ", caseAssert="
				+ caseAssert + ", caseParam=" + caseParam + ", createTime="
				+ createTime + ", updateTime=" + updateTime + "]";
	}
	
	
}
