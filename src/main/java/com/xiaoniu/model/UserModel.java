package com.xiaoniu.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class UserModel implements Serializable{

	private static final long serialVersionUID = -3422907056694697856L;
	
	private Integer id;
	private String user;
	@NotBlank(message="密码不能为空")
	private String userPwd;
	@NotBlank(message="用户名不为空")
	private String userName;
	private String userIp;
	@NotBlank(message="邮箱不能为空")
	private String email;
	private String creatTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserIp() {
		return userIp;
	}
	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}
	@Override
	public String toString() {
		return "UserModel [id=" + id + ", user=" + user + ", userPwd="
				+ userPwd + ", userName=" + userName + ", userIp=" + userIp
				+ ", email=" + email + ", creatTime=" + creatTime + "]";
	}
	
	
}
