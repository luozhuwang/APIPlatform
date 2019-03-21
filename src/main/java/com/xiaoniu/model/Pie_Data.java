package com.xiaoniu.model;

import java.io.Serializable;

public class Pie_Data implements Serializable{

	private static final long serialVersionUID = 2517584971510387244L;
	
	private Integer value;
	private String name;
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Pie_Data [value=" + value + ", name=" + name + "]";
	}
	
	
	
	
}
