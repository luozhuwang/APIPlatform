package com.xiaoniu.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class APIModel implements Serializable{
	private static final long serialVersionUID = -5440715430717463877L;
	
	private Integer id;
	@NotBlank(message="HOST不能为空")
	private String apiHost;
	@NotBlank(message="apiName不能为空")
	private String apiName;
	@NotBlank(message="apiUrl不能为空")
	private String apiUrl;
	private String apiMethod;
	private String apiHeaders;
//	@NotBlank(message="apiParams不能为空")
	private String apiParams;
	private Integer apiStatus;
	private String remark;

	public String getApiHost() {
		return apiHost;
	}
	public void setApiHost(String apiHost) {
		this.apiHost = apiHost;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getApiName() {
		return apiName;
	}
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
	public String getApiUrl() {
		return apiUrl;
	}
	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}
	public String getApiMethod() {
		return apiMethod;
	}
	public void setApiMethod(String apiMethod) {
		this.apiMethod = apiMethod;
	}
	public String getApiHeaders() {
		return apiHeaders;
	}
	public void setApiHeaders(String apiHeaders) {
		this.apiHeaders = apiHeaders;
	}
	public String getApiParams() {
		return apiParams;
	}
	public void setApiParams(String apiParams) {
		this.apiParams = apiParams;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getApiStatus() {
		return apiStatus;
	}
	public void setApiStatus(Integer apiStatus) {
		this.apiStatus = apiStatus;
	}
	
	@Override
	public String toString() {
		return "APIModel [id=" + id + ", apiName=" + apiName + ", apiUrl="
				+ apiUrl + ", apiMethod=" + apiMethod + ", apiHeaders="
				+ apiHeaders + ", apiParams=" + apiParams + ", apiStatus="
				+ apiStatus + ", remark=" + remark + "]";
	}
	


}
