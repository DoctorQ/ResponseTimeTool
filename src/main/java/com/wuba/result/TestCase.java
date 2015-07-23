/**
 * 
 */
package com.wuba.result;

import java.io.IOException;

import org.kxml2.io.KXmlSerializer;

import com.wuba.report.XMLParser;
import com.wuba.utils.Constant;

/**
 * @author hui.qian qianhui@58.com
 * @date 2015年7月23日 下午2:45:24
 */
public class TestCase implements XMLParser {

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLogFile() {
		return logFile;
	}

	public void setLogFile(String logFile) {
		this.logFile = logFile;
	}

	private static final String TESTCASE_TAG = "TestCase";
	private static final String INDEX_ATTR = "index";
	private static final String STATUS_ATTR = "status";
	private static final String LOGFILE_ATTR = "logfile";

	private String index;
	private String status;
	private String logFile;

	/*
	 * @see com.wuba.report.XMLParser#serialize(org.kxml2.io.KXmlSerializer)
	 */
	@Override
	public void serialize(KXmlSerializer serializer) throws IOException {
		// TODO Auto-generated method stub
		serializer.startTag(Constant.NAMESPACE, TESTCASE_TAG);
		serializer.attribute(Constant.NAMESPACE, INDEX_ATTR, getIndex());
		serializer.attribute(Constant.NAMESPACE, STATUS_ATTR, getStatus());
		serializer.attribute(Constant.NAMESPACE, LOGFILE_ATTR, getLogFile());
		serializer.endTag(Constant.NAMESPACE, TESTCASE_TAG);

	}

}
