/**
 * 
 */
package com.wuba.report;

import java.io.File;
import java.io.IOException;

import org.kxml2.io.KXmlSerializer;

import com.wuba.logparser.LogParser;
import com.wuba.result.RTResult;
import com.wuba.result.TestCase;
import com.wuba.result.XMLParser;
import com.wuba.utils.Constant;

/**
 * @author hui.qian qianhui@58.com
 * @date 2015年7月20日 下午2:49:33
 */
public class TestView implements XMLParser {
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

	private static final String XML_TAG = "TestView";
	private static final String INDEX_ATTR = "index";
	private static final String LOGFILE_ATTR = "logfile";

	private String index = "";
	private String logFile = "";
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
		// serializer.attribute(Constant.NAMESPACE, RESPONSETIME_ATTR,
		// getResponseTime());
		if (rtResult != null) {
			rtResult.serialize(serializer);
		}
		serializer.attribute(Constant.NAMESPACE, LOGFILE_ATTR, getLogFile());
		serializer.endTag(Constant.NAMESPACE, XML_TAG);
	}

	/**
	 * @param testCase
	 * @param logParser
	 */
	public void parserTestViewFromTestCase(TestCase testCase,
			LogParser logParser) {
		// TODO Auto-generated method stub
		setIndex(testCase.getIndex());
		setLogFile(testCase.getLogFile());

		setRtResult(logParser.parserLog(new File(testCase.getLogFile())));

	}

}
