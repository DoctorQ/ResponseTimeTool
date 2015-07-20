/**
 * 
 */
package com.wuba.result;

import java.io.File;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

/**
 * @author hui.qian qianhui@58.com
 * @date 2015年7月20日 下午4:24:04
 */
public class TestResultTest {
	private static final Logger LOGGER = Logger.getLogger(TestResultTest.class);

	private TestResult testResult = null;

	@BeforeGroups(groups = { "unittest" })
	public void setUp() {
		testResult = new TestResult();
	}

	@Test(groups = { "unittest" })
	public void generateXMLTest() {
		testResult.serializeResultToXml(new File("testResult.xml"));
	}

	@Test(groups = { "unittest" })
	public void generateDeviceInfoTest() {
		
	}
}
