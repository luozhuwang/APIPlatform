package com.xiaoniu.model;

import java.io.Serializable;

public class CommonDictModel implements Serializable{
	private static final long serialVersionUID = -2484378158318782508L;
	
	private Integer id;
	private String dictType;
	private String dictCode;
	private String dictValue;
	private String remark;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDictType() {
		return dictType;
	}
	public void setDictType(String dictType) {
		this.dictType = dictType;
	}
	public String getDictCode() {
		return dictCode;
	}
	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}
	public String getDictValue() {
		return dictValue;
	}
	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "CommonDictModel [id=" + id + ", dictType=" + dictType
				+ ", dictCode=" + dictCode + ", dictValue=" + dictValue
				+ ", remark=" + remark + "]";
	}

}
