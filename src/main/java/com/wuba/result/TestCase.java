/**
 * 
 */
package com.wuba.result;

import java.io.IOException;

import org.kxml2.io.KXmlSerializer;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.wuba.utils.Constant;

/**
 * @author hui.qian qianhui@58.com
 * @date 2015年7月23日 下午2:45:24
 */
public class TestCase extends AbstractXmlPullParser implements XMLParser {

	public boolean isPass() {
		return pass;
	}

	public void setPass(boolean pass) {
		this.pass = pass;
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

	public static final String TESTCASE_TAG = "TestCase";
	private static final String INDEX_ATTR = "index";
	private static final String STATUS_ATTR = "status";
	private static final String LOGFILE_ATTR = "logfile";

	private String index;
	private String logFile;
	private boolean pass;

	/*
	 * @see com.wuba.report.XMLParser#serialize(org.kxml2.io.KXmlSerializer)
	 */
	@Override
	public void serialize(KXmlSerializer serializer) throws IOException {
		// TODO Auto-generated method stub
		serializer.startTag(Constant.NAMESPACE, TESTCASE_TAG);
		serializer.attribute(Constant.NAMESPACE, INDEX_ATTR, getIndex());
		serializer.attribute(Constant.NAMESPACE, STATUS_ATTR, isPass() ? "pass"
				: "fail");
		serializer.attribute(Constant.NAMESPACE, LOGFILE_ATTR, getLogFile());
		serializer.endTag(Constant.NAMESPACE, TESTCASE_TAG);

	}

	/*
	 * 
	 * @see
	 * com.wuba.result.AbstractXmlPullParser#parse(org.xmlpull.v1.XmlPullParser)
	 */
	@Override
	public void parse(XmlPullParser parser) throws XmlPullParserException,
			IOException {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		if (!parser.getName().equals(TESTCASE_TAG)) {
			return;
		}
		setIndex(getAttribute(parser, INDEX_ATTR));
		setLogFile(getAttribute(parser, LOGFILE_ATTR));
		setPass(getAttribute(parser, STATUS_ATTR).equals("pass") ? true : false);
		

	}

}
