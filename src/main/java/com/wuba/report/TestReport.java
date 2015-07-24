/**
 * 
 */
package com.wuba.report;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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

	private static final Logger LOG = Logger.getLogger(TestReport.class);
	private static final String XML_TAG = "TestReport";

	private TestDevice mTestDevice = new TestDevice();
	
	
	
	private Map<String, TestDevice> testDevices = new LinkedHashMap<String, TestDevice>();
	
	private File xmlFile;
	public TestReport(File xmlFile) {
		// TODO Auto-generated constructor stub
		this.xmlFile = xmlFile;
	}
	

	/*
	 * 
	 * @see com.wuba.result.XMLParser#serialize(org.kxml2.io.KXmlSerializer)
	 */
	@Override
	public void serialize(KXmlSerializer serializer) throws IOException {
		serializer.startTag(Constant.NAMESPACE, XML_TAG);
		mTestDevice.serialize(serializer);
		serializer.endTag(Constant.NAMESPACE, XML_TAG);
	}

	/**
	 * Creates the output stream to use for test results. Exposed for mocking.
	 */
	OutputStream createOutputResultStream(File reportFile) throws IOException {
		LOG.info(String.format("Created testReport.xml  file at file://%s",
				reportFile.getAbsolutePath()));
		return new FileOutputStream(reportFile);
	}

	public void serializeResultToXml() {

		OutputStream stream = null;

		try {
			stream = createOutputResultStream(xmlFile);
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
	/**
	 * 根据case名得到TestViewLoop对象
	 * @param name
	 * @return TestViewLoop
	 */
	public TestViewLoop getTestCaseByName(String name) {
		if (name == null) {
			LOG.error("You want get a name = null TestCase?");
			return null;
		}
		return mTestDevice.getTestCaseByName(name);
	}
	
	
}
