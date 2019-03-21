package com.xiaoniu.httpclient;

import java.io.Serializable;

public class HttpResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 状体码
     */
    private Integer statusCode;

    /**
     * 响应内容
     */
    private String content;   
    
    /**
     * 开始时间
     * */
	private long beginTime;
	/**
	 * 结束时间
	 * */
	private long endTime;
    

    public HttpResult(){

    }

    public HttpResult(Integer statusCode, String content) {
        this.statusCode = statusCode;
        this.content = content;
    }

    /**
     * 获取状体码
     * @return 状体码
     */
    public Integer getStatusCode() {
        return statusCode;
    }


    /**
     * 设置状体码
     * @param statusCode 状体码
     */
    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }


    /**
     * 获取响应内容
     * @return 响应内容
     */
    public String getContent() {
        return content;
    }


    /**
     * 设置响应内容
     * @param content 响应内容
     */
    public void setContent(String content) {
        this.content = content;
    }

	public long getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(long beginTime) {
		this.beginTime = beginTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	
	@Override
	public String toString() {
		return "HttpResult [statusCode=" + statusCode + ", content=" + content
				+ ", beginTime=" + beginTime + ", endTime=" + endTime + "]";
	}

    

}
