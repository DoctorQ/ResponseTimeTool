/**
 * 
 */
package com.wuba.result;

import java.io.IOException;

import org.kxml2.io.KXmlSerializer;

/**
 * @author hui.qian qianhui@58.com
 * @date 2015年7月20日 下午2:49:33
 */
public class Item implements XMLParser {
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getLogFile() {
		return logFile;
	}
	public void setLogFile(String logFile) {
		this.logFile = logFile;
	}
	public String getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
	}
	private static final String XML_TAG = "Item";
	private static final String INDEX_ATTR = "index";
	private static final String LOGFILE_ATTR = "logfile";
	private static final String RESPONSETIME_ATTR = "responsetime";
	
	
	private String index;
	private String logFile;
	private String responseTime;
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wuba.result.XMLParser#serialize(org.kxml2.io.KXmlSerializer)
	 */
	@Override
	public void serialize(KXmlSerializer serializer) throws IOException {
		// TODO Auto-generated method stub
		serializer.startTag(TestResult.NAMESPACE, XML_TAG);
		serializer.attribute(TestResult.NAMESPACE, INDEX_ATTR, getIndex());
		serializer.attribute(TestResult.NAMESPACE, LOGFILE_ATTR, getLogFile());
		serializer.attribute(TestResult.NAMESPACE, RESPONSETIME_ATTR, getResponseTime());
		serializer.endTag(TestResult.NAMESPACE, XML_TAG);
	}

}
