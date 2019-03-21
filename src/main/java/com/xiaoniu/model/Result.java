package com.xiaoniu.model;

import java.io.Serializable;

public class Result implements Serializable{
	
	private static final long serialVersionUID = 3083928596044215515L;
	
	private String contractId;
	private String transId;
	private String status;
	private String returnMsg;
	
	
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReturnMsg() {
		return returnMsg;
	}
	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
	@Override
	public String toString() {
		return "Result [contractId=" + contractId + ", status=" + status
				+ ", returnMsg=" + returnMsg + "]";
	}
	
	
}
