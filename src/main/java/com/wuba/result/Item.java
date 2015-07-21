/**
 * 
 */
package com.wuba.result;

import java.io.IOException;

import org.kxml2.io.KXmlSerializer;

import com.wuba.model.RTResult;
import com.wuba.utils.Constant;

/**
 * @author hui.qian qianhui@58.com
 * @date 2015年7月20日 下午2:49:33
 */
public class Item implements XMLParser {
	public RTResult getRtResult() {
		return rtResult;
	}

	public void setRtResult(RTResult rtResult) {
		this.rtResult = rtResult;
	}

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

	private String index = "";
	private String logFile = "";
	private String responseTime = "";
	private RTResult rtResult;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wuba.result.XMLParser#serialize(org.kxml2.io.KXmlSerializer)
	 */
	@Override
	public void serialize(KXmlSerializer serializer) throws IOException {
		// TODO Auto-generated method stub
		serializer.startTag(Constant.NAMESPACE, XML_TAG);
		serializer.attribute(Constant.NAMESPACE, INDEX_ATTR, getIndex());
//		serializer.attribute(Constant.NAMESPACE, RESPONSETIME_ATTR,
//				getResponseTime());
		if (rtResult != null) {
			rtResult.serialize(serializer);
			
		}
		serializer.attribute(Constant.NAMESPACE, LOGFILE_ATTR, getLogFile());
		serializer.endTag(Constant.NAMESPACE, XML_TAG);
	}

}
