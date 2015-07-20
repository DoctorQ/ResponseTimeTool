/**
 * 
 */
package com.wuba.result;

import java.io.File;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import com.wuba.result.DeviceInfo;
import com.wuba.result.TestCases;
import com.wuba.result.TestResult;

/**
 * @author hui.qian qianhui@58.com
 * @date 2015年7月20日 下午4:24:04
 */
public class TestResultTest {
	private static final Logger LOGGER = Logger.getLogger(TestResultTest.class);

	private TestResult testResult = null;
	private File file = null;

	@BeforeGroups(groups = { "unittest" })
	public void setUp() {
		testResult = new TestResult();
		file = new File("testResult.xml");

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
	public void generateTestCasesTest(){
		TestCases testCases = testResult.getTestCases();
		
		
	}
	
	
	
}
