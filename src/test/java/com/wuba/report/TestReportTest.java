/**
 * 
 */
package com.wuba.report;

import java.io.File;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import com.wuba.logparser.AndroidLogParser;
import com.wuba.logparser.LogParser;
import com.wuba.model.RTResult;
import com.wuba.report.DeviceInfo;
import com.wuba.report.TestView;
import com.wuba.report.TestViewLoop;
import com.wuba.report.TestDevice;
import com.wuba.report.TestReport;

/**
 * @author hui.qian qianhui@58.com
 * @date 2015年7月20日 下午4:24:04
 */
public class TestReportTest {
	private static final Logger LOGGER = Logger.getLogger(TestReportTest.class);

	private TestReport testResult = null;
	private File file = null;
	private LogParser androidLog;

	@BeforeGroups(groups = { "unittest" })
	public void setUp() {
		testResult = new TestReport();
		file = new File(this.getClass().getResource("/result").getPath(),"testResult.xml");
		LOGGER.debug(file.getAbsolutePath());
		androidLog = new AndroidLogParser();
	}

	@Test(groups = { "unittest" })
	public void generateXMLTest() {
		testResult.serializeResultToXml(file);
		LOGGER.info("generateXMLTest-Done");
	}

	@Test(groups = { "unittest" })
	public void generateDeviceInfoTest() {
		DeviceInfo deviceInfo = testResult.getDeviceInfo();
		deviceInfo.setDeviceName("N6");
		deviceInfo.setNetwork("4G");
		testResult.serializeResultToXml(file);
		LOGGER.info("generateDeviceInfoTest-Done");
	}

	@Test(groups = { "unittest" })
	public void generateTestCasesTest() {
		testResult.getTestCaseByName("testcase1");
		testResult.getTestCaseByName("testcase2");
		testResult.serializeResultToXml(file);
		LOGGER.info("generateTestCasesTest-Done");
	}

	@Test(groups = { "unittest" })
	public void generateTestCasesByNullName() {
		TestViewLoop testCase = testResult.getTestCaseByName(null);
		Assert.assertNull(testCase);
		LOGGER.info("generateTestCasesByNullName-Done");
	}

	@Test(groups = { "unittest" })
	public void generateTestItemName() {
		TestViewLoop testCase1 = testResult.getTestCaseByName("testcase1");
		TestView item0 = new TestView();
		item0.setIndex("0");
		item0.setLogFile(this.getClass().getResource("/data/time_points.txt").getPath());
		RTResult result = androidLog.parserLog(new File(item0.getLogFile()));
		item0.setRtResult(result);
		
		testCase1.addItem(item0);
		
		
		TestView item1 = new TestView();
		item1.setIndex("1");
		item1.setLogFile(this.getClass().getResource("/data/time_points1.txt").getPath());
		RTResult result2 = androidLog.parserLog(new File(item1.getLogFile()));
		item1.setRtResult(result2);
		testCase1.addItem(item1);
		
		TestViewLoop testCase2 = testResult.getTestCaseByName("testcase2");
		TestView item2 = new TestView();
		item2.setIndex("0");
		item2.setLogFile(this.getClass().getResource("/data/xml.txt").getPath());
		RTResult result3 = androidLog.parserLog(new File(item2.getLogFile()));
		item2.setRtResult(result3);
		testCase2.addItem(item2);
		
		
		TestViewLoop testCase3 = testResult.getTestCaseByName("testcase3");
		TestView item3 = new TestView();
		item3.setIndex("0");
		item3.setLogFile(this.getClass().getResource("/data/webview_time_points.txt").getPath());
		RTResult result4 = androidLog.parserLog(new File(item3.getLogFile()));
		item3.setRtResult(result4);
		testCase3.addItem(item3);
		
		TestViewLoop testCase4 = testResult.getTestCaseByName("testcase3");
		TestView item4 = new TestView();
		item4.setIndex("0");
		item4.setLogFile(this.getClass().getResource("/data/webview_time_points.txt").getPath());
		RTResult result5 = androidLog.parserLog(new File(item4.getLogFile()));
		item4.setRtResult(result5);
		testCase4.addItem(item3);
		
		testResult.serializeResultToXml(file);
		LOGGER.info("generateTestItemName-Done");
	}

}
