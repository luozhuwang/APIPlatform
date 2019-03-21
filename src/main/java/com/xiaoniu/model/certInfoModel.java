package com.xiaoniu.model;

import java.io.Serializable;

public class certInfoModel implements Serializable{

	private static final long serialVersionUID = 5559592120883645043L;
	
	private String certType;	  
	  private String original;	  
	  private String path;
	public String getCertType() {
		return certType;
	}
	public void setCertType(String certType) {
		this.certType = certType;
	}
	public String getOriginal() {
		return original;
	}
	public void setOriginal(String original) {
		this.original = original;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	  
	  
}
