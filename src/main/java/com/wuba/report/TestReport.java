/**
 * 
 */
package com.wuba.report;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.log4j.Logger;
import org.kxml2.io.KXmlSerializer;

import com.wuba.utils.Constant;

/**
 * @author hui.qian qianhui@58.com
 * @date 2015年7月20日 下午2:47:45
 */
public class TestReport implements XMLParser {
	public DeviceInfo getDeviceInfo() {
		return mDeviceInfo;
	}

	public void setDeviceInfo(DeviceInfo deviceInfo) {
		this.mDeviceInfo = deviceInfo;
	}

	private static final Logger LOGGER = Logger.getLogger(TestReport.class);
	private static final String XML_TAG = "TestReport";

	private DeviceInfo mDeviceInfo = new DeviceInfo();
	private TestDevice mTestCases = new TestDevice();

	/*
	 * 
	 * @see com.wuba.result.XMLParser#serialize(org.kxml2.io.KXmlSerializer)
	 */
	@Override
	public void serialize(KXmlSerializer serializer) throws IOException {
		serializer.startTag(Constant.NAMESPACE, XML_TAG);
		mDeviceInfo.serialize(serializer);
		mTestCases.serialize(serializer);
		serializer.endTag(Constant.NAMESPACE, XML_TAG);
	}

	/**
	 * Creates the output stream to use for test results. Exposed for mocking.
	 */
	OutputStream createOutputResultStream(File reportFile) throws IOException {
		LOGGER.info(String.format("Created xml report file at file://%s",
				reportFile.getAbsolutePath()));
		return new FileOutputStream(reportFile);
	}

	public void serializeResultToXml(File reportFile) {

		OutputStream stream = null;

		try {
			stream = createOutputResultStream(reportFile);
			KXmlSerializer serializer = new KXmlSerializer();
			serializer.setOutput(stream, "UTF-8");
			serializer.startDocument("UTF-8", false);
			serializer.setFeature(
					"http://xmlpull.org/v1/doc/features.html#indent-output",
					true);
			serializer
					.processingInstruction("xml-stylesheet type=\"text/xsl\"  "
							+ "href=\"result.xsl\"");
			serialize(serializer);
			serializer.endDocument();
		} catch (Exception e) {
			LOGGER.error(e);
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	public TestViewLoop getTestCaseByName(String name) {
		if (name == null) {
			LOGGER.error("You want get a name = null TestCase?");
			return null;
		}
		return mTestCases.getTestCaseByName(name);

	}
}
