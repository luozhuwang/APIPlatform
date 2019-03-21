package com.xiaoniu.model;

import java.io.Serializable;
import java.util.List;

public class loanResult<T> implements Serializable{

	private static final long serialVersionUID = 4506695947663842297L;
	
	private Integer rows;
	private Integer sucessRows;
	private List<T> results;
//	private Integer signStatus;// 1 发标 2 不发标
	
	public List<T> getResults() {
		return results;
	}
	public void setResults(List<T> results) {
		this.results = results;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getSucessRows() {
		return sucessRows;
	}
	public void setSucessRows(Integer sucessRows) {
		this.sucessRows = sucessRows;
	}
	
	
}
