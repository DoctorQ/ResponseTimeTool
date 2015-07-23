/**
 * 
 */
package com.wuba.result;

import java.io.File;

import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import com.wuba.utils.TimeUtil;

/**
 * @author hui.qian qianhui@58.com
 * @date 2015年7月23日 下午3:46:41
 */
public class TestResultTest {
	private TestResult testResult = null;

	@BeforeGroups(groups = { "unittest" })
	public void setUp() {
		testResult = new TestResult();
	}

	@Test(groups = { "unittest" })
	public void generateXmlTest() {

		// webview
		TestCaseLoop webview = testResult.getTestCaseLoop("case3");
		webview.setStartTime(TimeUtil.formatTimeStamp(System.currentTimeMillis()));
		webview.setPath("/webview");
		webview.setLoop("1");
		TestCase webviewCase0 = webview.getTestCase(0);
		webviewCase0.setStatus("pass");
		webviewCase0.setLogFile("repo/result/android/MI_2G_dksldfdffdf_4.4.2_201507210823923/case3/0/result.txt");
		webview.setTotal("1");
		webview.setPass("1");
		webview.setFail("0");
		
		webview.setEndTime(TimeUtil.formatTimeStamp(System.currentTimeMillis()));
		// native json
		TestCaseLoop nativeJson = testResult.getTestCaseLoop("case1");
		nativeJson.setStartTime(TimeUtil.formatTimeStamp(System.currentTimeMillis()));
		
		nativeJson.setEndTime(TimeUtil.formatTimeStamp(System.currentTimeMillis()));
		// native xml
		TestCaseLoop nativeXml = testResult.getTestCaseLoop("case2");
		nativeXml.setStartTime(TimeUtil.formatTimeStamp(System.currentTimeMillis()));
		
		nativeXml.setEndTime(TimeUtil.formatTimeStamp(System.currentTimeMillis()));
		
		
		testResult.serializeResultToXml(new File(String.format("repo/report/testReport_%s.xml",TimeUtil.formatTimeForFile(System.currentTimeMillis()))));
	}

}
