package com.xiaoniu.model;

import java.io.Serializable;

public class CaseSetResultModel extends CaseSetModel implements Serializable{

	private static final long serialVersionUID = -5462759168393061356L;
	
	private Integer id;
	private Integer setId;
	private String  startTime;
	private String  endTime;
	private long 	costTime;
	private long 	batch;
	private String  createTime;
	private String runEnv;
	
	
	public String getRunEnv() {
		return runEnv;
	}
	public void setRunEnv(String runEnv) {
		this.runEnv = runEnv;
	}
	
	public long getBatch() {
		return batch;
	}
	public void setBatch(long batch) {
		this.batch = batch;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSetId() {
		return setId;
	}
	public void setSetId(Integer setId) {
		this.setId = setId;
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
	@Override
	public String toString() {
		return "CaseSetResultModel [id=" + id + ", setId=" + setId
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", costTime=" + costTime + ", batch=" + batch
				+ ", createTime=" + createTime + "]";
	}

}
