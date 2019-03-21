package com.xiaoniu.model;

import java.io.Serializable;

public class MessageResult<T> implements Serializable{

	private static final long serialVersionUID = -8917312847015394981L;
	private String responseMessage;
	private String responseCode;
	private String status;
	private T data;

	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
