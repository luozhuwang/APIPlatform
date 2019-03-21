package com.xiaoniu.service;

public interface TelnetService {
	//telnet端口
	public boolean connect(String ip, Integer port);
	//执行发送命令
	public String execute(String command);
	//关闭连接
	public void disconnect();
}
