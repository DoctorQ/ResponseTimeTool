/**
 * 
 */
package com.wuba.result;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.kxml2.io.KXmlSerializer;

import com.wuba.report.TestReport;
import com.wuba.utils.Constant;
import com.wuba.utils.TimeUtil;

/**
 * @author hui.qian qianhui@58.com
 * @date 2015年7月23日 下午2:37:28
 */
public class TestResult implements XMLParser {
	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	private static final Logger LOG = Logger.getLogger(TestResult.class);

	// 当前任务的根目录
	private File rootDir;

	public TestResult(File rootDir) {
		this.rootDir = rootDir;
		init();
	}

	private void init() {
		String name = rootDir.getName();
		String[] array = name.split("_");
		if (array.length < 5)
			return;

		device = array[0];
		network = array[1];
		sn = array[2];
		platform = array[3];
	}

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
	private static final String PLATFORM_ATTR = "platform";

	private Map<String, TestCaseLoop> loops = new LinkedHashMap<String, TestCaseLoop>();
	private String device = "";
	private String network = "";
	private String sn = "";
	private String platform = "";

	@Override
	public void serialize(KXmlSerializer serializer) throws IOException {
		serializer.startTag(Constant.NAMESPACE, TESTRESULT_TAG);
		serializer.attribute(Constant.NAMESPACE, DEVICE_ATTR, getDevice());
		serializer.attribute(Constant.NAMESPACE, NETWORK_ATTR, getNetwork());
		serializer.attribute(Constant.NAMESPACE, SN_ATTR, getSn());
		serializer.attribute(Constant.NAMESPACE, PLATFORM_ATTR, getPlatform());
		Collection<TestCaseLoop> collection = loops.values();
		for (TestCaseLoop loop : collection) {
			loop.serialize(serializer);
		}
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

	public void serializeResultToXml() {

		OutputStream stream = null;
		File resultFile = new File(rootDir, Constant.TESTRESULT_XML);
		try {
			stream = createOutputResultStream(resultFile);
			KXmlSerializer serializer = new KXmlSerializer();
			serializer.setOutput(stream, "UTF-8");
			serializer.startDocument("UTF-8", false);
			serializer.setFeature(
					"http://xmlpull.org/v1/doc/features.html#indent-output",
					true);

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

	public TestCaseLoop getTestCaseLoop(String caseName) {
		if (caseName == null)
			return null;

		TestCaseLoop loop = loops.get(caseName);
		if (loop == null) {
			loop = new TestCaseLoop();
			loop.setName(caseName);
			loops.put(caseName, loop);
		}

		return loop;
	}

}
