package com.xiaoniu.model;

import java.io.Serializable;
import java.util.List;

public class ResultData implements Serializable{

	private static final long serialVersionUID = -3257963121426526464L;
	
	private String resultStatus;
	private List<Object> totalArray;
	
	public String getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(String resultStatus) {
		this.resultStatus = resultStatus;
	}
	public List<Object> getTotalArray() {
		return totalArray;
	}
	public void setTotalArray(List<Object> totalArray) {
		this.totalArray = totalArray;
	}
	@Override
	public String toString() {
		return "ResultData [resultStatus=" + resultStatus + ", totalArray="
				+ totalArray + "]";
	}

}
