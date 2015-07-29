/**
 * 
 */
package com.wuba.report;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.kxml2.io.KXmlSerializer;

import com.wuba.result.XMLParser;
import com.wuba.utils.Constant;

/**
 * @author hui.qian qianhui@58.com
 * @date 2015年7月20日 下午2:47:45
 */
public class TestReport implements XMLParser {

	public File getReportDir() {
		return reportDir;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	private static final Logger LOG = Logger.getLogger(TestReport.class);
	private static final String XML_TAG = "TestReport";
	private static final String PLATFORM_ATTR = "platform";

	private Map<String, TestDevice> testDevices = new LinkedHashMap<String, TestDevice>();

	private File reportDir;
	private String platform;

	public TestReport(File reportDir) {
		// TODO Auto-generated constructor stub
		this.reportDir = reportDir;
	}

	/*
	 * 
	 * @see com.wuba.result.XMLParser#serialize(org.kxml2.io.KXmlSerializer)
	 */
	@Override
	public void serialize(KXmlSerializer serializer) throws IOException {
		serializer.startTag(Constant.NAMESPACE, XML_TAG);
		//serializer.attribute(Constant.NAMESPACE, PLATFORM_ATTR, getPlatform());
		Collection<TestDevice> collection = testDevices.values();
		for (TestDevice mTestDevice : collection) {
			mTestDevice.serialize(serializer);
		}
		serializer.endTag(Constant.NAMESPACE, XML_TAG);
	}

	/**
	 * Creates the output stream to use for test results. Exposed for mocking.
	 */
	OutputStream createOutputResultStream(File reportFile) throws IOException {
		LOG.info(String.format("Created file at file://%s",
				reportFile.getAbsolutePath()));
		return new FileOutputStream(reportFile);
	}

	public void serializeResultToXml() {

		OutputStream stream = null;
		File xmlFile = new File(reportDir, Constant.TESTREPORT_XML);
		try {
			stream = createOutputResultStream(new File(reportDir,
					Constant.TESTREPORT_XML));
			KXmlSerializer serializer = new KXmlSerializer();
			serializer.setOutput(stream, "UTF-8");
			serializer.startDocument("UTF-8", false);
			serializer.setFeature(
					"http://xmlpull.org/v1/doc/features.html#indent-output",
					true);
			serializer
					.processingInstruction("xml-stylesheet type=\"text/xsl\"  "
							+ "href=\"testReport.xsl\"");
			serialize(serializer);
			serializer.endDocument();

			// 生成HTML文件
			new HtmlReportGenerator().transferToHtml(xmlFile.getAbsolutePath());
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

	/**
	 * 根据sn号得到TestDevice对象
	 * 
	 * @param sn
	 * @return
	 */
	public TestDevice getTestDevice(String sn) {
		TestDevice mTestDevice = testDevices.get(sn);
		if (mTestDevice == null) {
			mTestDevice = new TestDevice();
			mTestDevice.setSn(sn);
			testDevices.put(sn, mTestDevice);
		}
		return mTestDevice;

	}

}
