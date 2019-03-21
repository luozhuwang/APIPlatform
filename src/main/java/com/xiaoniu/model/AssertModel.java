package com.xiaoniu.model;

import java.io.Serializable;

public class AssertModel implements Serializable{
	
	private static final long serialVersionUID = -5952260322365822767L;
	
	private String assertItem;
//	private String actual;
	private String compare;
	private String expect;
	
	public String getAssertItem() {
		return assertItem;
	}
	public void setAssertItem(String assertItem) {
		this.assertItem = assertItem;
	}
	public String getCompare() {
		return compare;
	}
	public void setCompare(String compare) {
		this.compare = compare;
	}
	public String getExpect() {
		return expect;
	}
	public void setExpect(String expect) {
		this.expect = expect;
	}
	@Override
	public String toString() {
		return "AssertModel [assertItem=" + assertItem +  ", compare=" + compare + ", expect=" + expect + "]";
	}
	
	
}
