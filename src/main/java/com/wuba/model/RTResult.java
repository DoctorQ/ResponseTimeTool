package com.wuba.model;

import java.io.IOException;

import org.kxml2.io.KXmlSerializer;

import com.wuba.utils.Constant;
import com.wuba.utils.XMLParser;

/**
 * 
 * @author hui.qian qianhui@58.com
 * @date 2015年7月15日 下午5:48:51
 */
public class RTResult implements XMLParser {
	public String getViewType() {
		return viewType;
	}

	public void setViewType(String viewType) {
		this.viewType = viewType;
	}

	@Override
	public String toString() {
		return "RTResult [caseName=" + caseName + ", url=" + url + ", id=" + id
				+ ", dataType=" + dataType + ", beginTime=" + beginTime
				+ ", connectTime=" + connectTime + ", readTime=" + readTime
				+ ", parserTime=" + parserTime + ", connectCost=" + connectCost
				+ ", readCost=" + readCost + ", parserCost=" + parserCost
				+ ", viewType=" + viewType + "]";
	}

	private static final String CONNECT_ATTR = "connect";
	private static final String READ_ATTR = "read";
	private static final String PARSER_ATTR = "parser";

	public long getConnectCost() {
		return connectCost;
	}

	public void setConnectCost(long connectCost) {
		this.connectCost = connectCost;
	}

	public long getReadCost() {
		return readCost;
	}

	public void setReadCost(long readCost) {
		this.readCost = readCost;
	}

	public long getParserCost() {
		return parserCost;
	}

	public void setParserCost(long parserCost) {
		this.parserCost = parserCost;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	// 场景名称
	private String caseName;
	// http请求的url地址
	private String url;
	// 请求的id号
	private String id;
	// 解析的数据类型:json和xml
	private String dataType;
	// 连接开始时间点
	private long beginTime;
	// 连接成功时间点
	private long connectTime;
	// 读取完成时间点
	private long readTime;
	// 解析完成时间点
	private long parserTime;
	// 连接耗时
	private long connectCost;
	// 读取耗时
	private long readCost;
	// 解析耗时
	private long parserCost;
	// 页面类型,native和webview
	private String viewType;

	@Override
	public void serialize(KXmlSerializer serializer) throws IOException {
		
		if (Constant.NATIVE.equals(viewType)) {
			serializer.attribute(Constant.NAMESPACE, CONNECT_ATTR,
					getConnectCost() + "");
			if (Constant.JSON.equals(dataType)) {
				serializer.attribute(Constant.NAMESPACE, READ_ATTR,
						getReadCost() + "");
			}
			serializer.attribute(Constant.NAMESPACE, PARSER_ATTR,
					getParserCost() + "");
		} else if (Constant.WEBVIEW.equals(viewType)) {
			serializer.attribute(Constant.NAMESPACE, PARSER_ATTR,
					getParserCost() + "");
		}

	}
}
