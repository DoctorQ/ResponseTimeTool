/**
 * 
 */
package com.wuba.result;

import java.io.IOException;

import org.kxml2.io.KXmlSerializer;

/**
 * @author hui.qian qianhui@58.com
 * @date 2015年7月20日 下午2:47:45
 */
public class TestResult implements XMLParser {
	public static final String NAMESPACE = null;
	private static final String XML_TAG = "TestResult";

	private DeviceInfo mDeviceInfo = new DeviceInfo();
	private TestCases mTestCases = new TestCases();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wuba.result.XMLParser#serialize(org.kxml2.io.KXmlSerializer)
	 */
	@Override
	public void serialize(KXmlSerializer serializer) throws IOException {

		serializer.startTag(NAMESPACE, XML_TAG);
		mDeviceInfo.serialize(serializer);
		mTestCases.serialize(serializer);
		serializer.endTag(NAMESPACE, XML_TAG);

	}

}
