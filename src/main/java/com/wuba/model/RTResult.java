package com.wuba.model;

//响应时间结果对象，包括case名，开始时间，连接时间，读取数据时间，解析时间
public class RTResult{
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCaseName() {
		return caseName;
	}
	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}
	public long getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(long beginTime) {
		this.beginTime = beginTime;
	}
	public long getConnectTime() {
		return connectTime;
	}
	public void setConnectTime(long connectTime) {
		this.connectTime = connectTime;
	}
	public long getReadTime() {
		return readTime;
	}
	public void setReadTime(long readTime) {
		this.readTime = readTime;
	}
	public long getParserTime() {
		return parserTime;
	}
	public void setParserTime(long parserTime) {
		this.parserTime = parserTime;
	}
	private String caseName;
	private String url;
	private long beginTime;
	private long connectTime;
	private long readTime;
	private long parserTime;
	
	
	
	
	
	
	

}
