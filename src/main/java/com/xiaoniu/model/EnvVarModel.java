package com.xiaoniu.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class EnvVarModel implements Serializable{
	
	
	private static final long serialVersionUID = 8447854677169302978L;
	
	private Integer id;
	@NotBlank(message="变量名不能重复且不为空")
	private String varName;
	private String varRule;
	@NotBlank(message="值不能为空")
	private String varValue;
	private Integer varType;
	private String createtime;
	private String updatetime;
	
	
	public Integer getVarType() {
		return varType;
	}
	public void setVarType(Integer varType) {
		this.varType = varType;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getVarName() {
		return varName;
	}
	public void setVarName(String varName) {
		this.varName = varName;
	}
	public String getVarRule() {
		return varRule;
	}
	public void setVarRule(String varRule) {
		this.varRule = varRule;
	}
	public String getVarValue() {
		return varValue;
	}
	public void setVarValue(String varValue) {
		this.varValue = varValue;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	
	@Override
	public String toString() {
		return "EnvVarModel [id=" + id + ", varName=" + varName + ", varRule="
				+ varRule + ", varValue=" + varValue + ", varType=" + varType
				+ ", createtime=" + createtime + ", updatetime=" + updatetime
				+ "]";
	}
	
}
