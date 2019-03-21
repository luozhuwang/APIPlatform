package com.xiaoniu.model;

import java.io.Serializable;

public class ParamModel implements Serializable{

	private static final long serialVersionUID = 8756462503442586021L;
	
	private String param;
	private String method;
	private String rule;
	
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	@Override
	public String toString() {
		return "paramEntity [param=" + param + ", method=" + method + ", rule="
				+ rule + "]";
	}
	
	
}
