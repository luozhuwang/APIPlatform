package com.xiaoniu.model;

import java.io.Serializable;

public class ResponseDetailModel<T>  implements Serializable{
	
	private static final long serialVersionUID = 7124211329511097218L;
	private String errorDetails;
	private Integer status;
	private T data;
	private String errorCode;
	
	public String getErrorDetails() {
		return errorDetails;
	}
	public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	
}

