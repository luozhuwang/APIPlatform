package com.xiaoniu.model;

import java.io.Serializable;

public class CaseResultModel extends APIModel  implements Serializable{
	private static final long serialVersionUID = -4924926769070124603L;
	private Integer id;
	private Integer relationId;
	private Integer caseId;
	//增加对1个case对应多个APID支持
	private Integer APIId;
	private String caseName;
	private Integer setId;
	private String startTime;
	private String endTime;
	private String resultStatus;
	private String request;
	private Integer	statusCode;
	private String response;
	private String caseAssert;
	private String createTime;
	private String updateTime;
	private long costTime;
	private String runEnv;
	
	
	public String getRunEnv() {
		return runEnv;
	}
	public void setRunEnv(String runEnv) {
		this.runEnv = runEnv;
	}
	
	public Integer getRelationId() {
		return relationId;
	}
	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	public Integer getAPIId() {
		return APIId;
	}
	public void setAPIId(Integer aPIId) {
		APIId = aPIId;
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
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public Integer getSetId() {
		return setId;
	}
	public void setSetId(Integer setId) {
		this.setId = setId;
	}
	public long getCostTime() {
		return costTime;
	}
	public void setCostTime(long costTime) {
		this.costTime = costTime;
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
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(String resultStatus) {
		this.resultStatus = resultStatus;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	@Override
	public String toString() {
		return "CaseResultModel [id=" + id + ", caseId=" + caseId + ", APIId="
				+ APIId + ", caseName=" + caseName + ", setId=" + setId
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", resultStatus=" + resultStatus + ", request=" + request
				+ ", statusCode=" + statusCode + ", response=" + response
				+ ", caseAssert=" + caseAssert + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", costTime=" + costTime + "]";
	}

}
