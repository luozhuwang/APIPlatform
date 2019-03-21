package com.xiaoniu.model;

import java.io.Serializable;


public class CaseSetModel implements Serializable{

	private static final long serialVersionUID = 838785932353553420L;
	
	private Integer id;
	private String caseSetName;
	private String caseRelation;
	private Integer caseCount;
	private String remark;
	private String  createTime;
	private String updateTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCaseSetName() {
		return caseSetName;
	}
	public void setCaseSetName(String caseSetName) {
		this.caseSetName = caseSetName;
	}
	public String getCaseRelation() {
		return caseRelation;
	}
	public void setCaseRelation(String caseRelation) {
		this.caseRelation = caseRelation;
	}
	public Integer getCaseCount() {
		return caseCount;
	}
	public void setCaseCount(Integer caseCount) {
		this.caseCount = caseCount;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
		return "CaseSetModel [id=" + id + ", caseSetName=" + caseSetName
				+ ", caseRelation=" + caseRelation + ", caseCount=" + caseCount
				+ ", remark=" + remark + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}
	
	


}
