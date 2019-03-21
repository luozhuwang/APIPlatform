package com.xiaoniu.model;

import java.io.Serializable;

public class LoanDetailModel implements Serializable{

	private static final long serialVersionUID = 3668203498868770123L;
	
	private long id;
	private String serialId;
	private int amount;

	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSerialId() {
		return serialId;
	}
	public void setSerialId(String serialId) {
		this.serialId = serialId;
	}
}
