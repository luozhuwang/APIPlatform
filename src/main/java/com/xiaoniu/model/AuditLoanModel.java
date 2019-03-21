package com.xiaoniu.model;

import java.io.Serializable;

public class AuditLoanModel implements Serializable{
	
	private static final long serialVersionUID = -4373112943523175213L;

	private int total;
	private int fail;
	private int overlimit;
	private int success;
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getFail() {
		return fail;
	}
	public void setFail(int fail) {
		this.fail = fail;
	}
	public int getOverlimit() {
		return overlimit;
	}
	public void setOverlimit(int overlimit) {
		this.overlimit = overlimit;
	}
	public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}
	
	

}
