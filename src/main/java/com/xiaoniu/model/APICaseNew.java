package com.xiaoniu.model;

import java.io.Serializable;

public class APICaseNew  implements Serializable {

	private static final long serialVersionUID = 3942635254143979308L;
	
	private Integer id;
	private Integer dependCaseId;
	private String caseName;
	private String caseRelations ;
	private Integer	isRun;
	private String remark;
	private String createTime;
	private String updateTime;



	public Integer getIsRun() {
		return isRun;
	}
	public void setIsRun(Integer isRun) {
		this.isRun = isRun;
	}
	public String getCaseRelations() {
		return caseRelations;
	}
	public void setCaseRelations(String caseRelations) {
		this.caseRelations = caseRelations;
	}
	public Integer getDependCaseId() {
		return dependCaseId;
	}
	public void setDependCaseId(Integer dependCaseId) {
		this.dependCaseId = dependCaseId;
	}
	public String getCaseName() {
		return caseName;
	}
	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
		return "APICaseNew [id=" + id + ", dependCaseId=" + dependCaseId
				+ ", caseName=" + caseName + ", caseRelations=" + caseRelations
				+ ", isRun=" + isRun + ", remark=" + remark + ", createTime="
				+ createTime + ", updateTime=" + updateTime + "]";
	}


}
