package com.xiaoniu.model;

import java.io.Serializable;

public class EnvInfoModel implements Serializable{

	private static final long serialVersionUID = -4884518165191068178L;
	
	private Integer id;
	private String envTitle;
	private String envDomain;
	private String envIp;
	private String createTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEnvTitle() {
		return envTitle;
	}
	public void setEnvTitle(String envTitle) {
		this.envTitle = envTitle;
	}
	public String getEnvDomain() {
		return envDomain;
	}
	public void setEnvDomain(String envDomain) {
		this.envDomain = envDomain;
	}
	public String getEnvIp() {
		return envIp;
	}
	public void setEnvIp(String envIp) {
		this.envIp = envIp;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "EnvModel [id=" + id + ", envTitle=" + envTitle + ", envDomain="
				+ envDomain + ", envIp=" + envIp + ", createTime=" + createTime
				+ "]";
	}
	
	
	

}
