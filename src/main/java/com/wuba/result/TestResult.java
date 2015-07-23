/**
 * 
 */
package com.wuba.result;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.log4j.Logger;
import org.kxml2.io.KXmlSerializer;

import com.wuba.report.TestReport;
import com.wuba.report.XMLParser;
import com.wuba.utils.Constant;

/**
 * @author hui.qian qianhui@58.com
 * @date 2015年7月23日 下午2:37:28
 */
public class TestResult implements XMLParser {
	private static final Logger LOG = Logger.getLogger(TestResult.class);

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	private static final String TESTRESULT_TAG = "TestResult";
	private static final String DEVICE_ATTR = "device";
	private static final String NETWORK_ATTR = "network";
	private static final String SN_ATTR = "sn";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wuba.report.XMLParser#serialize(org.kxml2.io.KXmlSerializer)
	 */

	private String device = "";
	private String network = "";
	private String sn = "";

	@Override
	public void serialize(KXmlSerializer serializer) throws IOException {
		serializer.startTag(Constant.NAMESPACE, TESTRESULT_TAG);
		serializer.attribute(Constant.NAMESPACE, DEVICE_ATTR, getDevice());
		serializer.attribute(Constant.NAMESPACE, NETWORK_ATTR, getNetwork());
		serializer.attribute(Constant.NAMESPACE, SN_ATTR, getSn());

		serializer.endTag(Constant.NAMESPACE, TESTRESULT_TAG);
	}
	
	/**
	 * Creates the output stream to use for test results. Exposed for mocking.
	 */
	OutputStream createOutputResultStream(File reportFile) throws IOException {
		LOG.info(String.format("Created testResult.xml  file at file://%s",
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
			LOG.error(e);
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
}
