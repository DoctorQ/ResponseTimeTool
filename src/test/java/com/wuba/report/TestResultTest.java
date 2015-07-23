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
import com.wuba.report.Item;
import com.wuba.report.TestCase;
import com.wuba.report.TestCases;
import com.wuba.report.TestResult;

/**
 * @author hui.qian qianhui@58.com
 * @date 2015年7月20日 下午4:24:04
 */
public class TestResultTest {
	private static final Logger LOGGER = Logger.getLogger(TestResultTest.class);

	private TestResult testResult = null;
	private File file = null;
	private LogParser androidLog;

	@BeforeGroups(groups = { "unittest" })
	public void setUp() {
		testResult = new TestResult();
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
		TestCase testCase = testResult.getTestCaseByName(null);
		Assert.assertNull(testCase);
		LOGGER.info("generateTestCasesByNullName-Done");
	}

	@Test(groups = { "unittest" })
	public void generateTestItemName() {
		TestCase testCase1 = testResult.getTestCaseByName("testcase1");
		Item item0 = new Item();
		item0.setIndex("0");
		item0.setLogFile(this.getClass().getResource("/data/time_points.txt").getPath());
		RTResult result = androidLog.parserLog(new File(item0.getLogFile()));
		item0.setRtResult(result);
		
		testCase1.addItem(item0);
		
		
		Item item1 = new Item();
		item1.setIndex("1");
		item1.setLogFile(this.getClass().getResource("/data/time_points1.txt").getPath());
		RTResult result2 = androidLog.parserLog(new File(item1.getLogFile()));
		item1.setRtResult(result2);
		testCase1.addItem(item1);
		
		TestCase testCase2 = testResult.getTestCaseByName("testcase2");
		Item item2 = new Item();
		item2.setIndex("0");
		item2.setLogFile(this.getClass().getResource("/data/xml.txt").getPath());
		RTResult result3 = androidLog.parserLog(new File(item2.getLogFile()));
		item2.setRtResult(result3);
		testCase2.addItem(item2);
		
		
		TestCase testCase3 = testResult.getTestCaseByName("testcase3");
		Item item3 = new Item();
		item3.setIndex("0");
		item3.setLogFile(this.getClass().getResource("/data/webview_time_points.txt").getPath());
		RTResult result4 = androidLog.parserLog(new File(item3.getLogFile()));
		item3.setRtResult(result4);
		testCase3.addItem(item3);
		
		TestCase testCase4 = testResult.getTestCaseByName("testcase3");
		Item item4 = new Item();
		item4.setIndex("0");
		item4.setLogFile(this.getClass().getResource("/data/webview_time_points.txt").getPath());
		RTResult result5 = androidLog.parserLog(new File(item4.getLogFile()));
		item4.setRtResult(result5);
		testCase4.addItem(item3);
		
		testResult.serializeResultToXml(file);
		LOGGER.info("generateTestItemName-Done");
	}

}
