/**
 * 
 */
package com.wuba.result;

import java.io.File;

import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import com.wuba.utils.Constant;
import com.wuba.utils.DirStructureUtil;
import com.wuba.utils.TimeUtil;

/**
 * @author hui.qian qianhui@58.com
 * @date 2015年7月23日 下午3:46:41
 */
public class TestResultTest {
	private TestResult testResult = null;
	private TestResult iosTestresult = null;

	@BeforeGroups(groups = { "unittest" })
	public void setUp() {
		testResult = new TestResult(new File(
				DirStructureUtil.getResultAndroid(),
				"MI_2G_dksldfdffdf_4.4.2_201507210823923"));
		iosTestresult = new TestResult(new File(
				DirStructureUtil.getResultIOS(),
				"iPhone5_2G_2f2fb64220ed34f645d33cd222280efcaa37dadf_7.0.3_20150727042215"));
	}

	@Test(groups = { "unittest","androidtestresult" })
	public void generateAndroidXmlTestResult() {
		testResult.setPlatform(Constant.ANDROID_PLATFORM);
		// webview
		TestCaseLoop webview = testResult.getTestCaseLoop("case3");
		webview.setStartTime(TimeUtil.formatTimeStamp(System
				.currentTimeMillis()));
		webview.setPath("/webview");
		webview.setLoop(1);
		TestCase webviewCase0 = webview.getTestCase(0);
		webviewCase0.setPass(true);
		webviewCase0
				.setLogFile("repo/result/android/MI_2G_dksldfdffdf_4.4.2_201507210823923/case3/0/result.txt");

		webview.setEndTime(TimeUtil.formatTimeStamp(System.currentTimeMillis()));
		// native json
		TestCaseLoop nativeJson = testResult.getTestCaseLoop("case1");
		nativeJson.setStartTime(TimeUtil.formatTimeStamp(System
				.currentTimeMillis()));

		nativeJson.setPath("/nativeJson");
		nativeJson.setLoop(2);

		TestCase nativeJsonCase0 = nativeJson.getTestCase(0);

		nativeJsonCase0.setPass(true);
		nativeJsonCase0
				.setLogFile("repo/result/android/MI_2G_dksldfdffdf_4.4.2_201507210823923/case1/0/result.txt");
		TestCase nativeJsonCase1 = nativeJson.getTestCase(1);
		nativeJsonCase1.setPass(true);
		nativeJsonCase1
				.setLogFile("repo/result/android/MI_2G_dksldfdffdf_4.4.2_201507210823923/case1/1/result.txt");
		nativeJson.setEndTime(TimeUtil.formatTimeStamp(System
				.currentTimeMillis()));

		// native xml
		TestCaseLoop nativeXml = testResult.getTestCaseLoop("case2");
		nativeXml.setStartTime(TimeUtil.formatTimeStamp(System
				.currentTimeMillis()));
		nativeXml.setPath("/nativeXml");
		nativeXml.setLoop(1);
		TestCase nativeXmlCase0 = nativeXml.getTestCase(0);
		nativeXmlCase0.setPass(true);
		nativeXmlCase0
				.setLogFile("repo/result/android/MI_2G_dksldfdffdf_4.4.2_201507210823923/case2/0/result.txt");

		nativeXml.setEndTime(TimeUtil.formatTimeStamp(System
				.currentTimeMillis()));

		testResult.serializeResultToXml();
	}

	@Test(groups = { "unittest","iostestresult" })
	public void generateIOSXmlTestResult() {
		iosTestresult.setPlatform(Constant.IOS_PLATFORM);
		
		
		// native
		TestCaseLoop nativeLoop = iosTestresult
				.getTestCaseLoop("test_native_zhengzu_list");
		nativeLoop.setStartTime(TimeUtil.formatTimeStamp(System
				.currentTimeMillis()));
		nativeLoop.setPath("/native");
		nativeLoop.setLoop(1);
		TestCase nativeCase0 = nativeLoop.getTestCase(0);
		nativeCase0.setPass(true);
		nativeCase0.setLogFile("repo/result/ios/iPhone5_2G_2f2fb64220ed34f645d33cd222280efcaa37dadf_7.0.3_20150727042215/test_native_zhengzu_list/0/time.log");
		nativeLoop.setEndTime(TimeUtil.formatTimeStamp(System
				.currentTimeMillis()));

		TestCaseLoop webViewLoop = iosTestresult
				.getTestCaseLoop("test_web_xinfang_list");
		webViewLoop.setStartTime(TimeUtil.formatTimeStamp(System
				.currentTimeMillis()));

		webViewLoop.setPath("/webView");
		webViewLoop.setLoop(2);

		TestCase webViewCase0 = webViewLoop.getTestCase(0);
		webViewCase0.setPass(true);
		webViewCase0.setLogFile("repo/result/ios/iPhone5_2G_2f2fb64220ed34f645d33cd222280efcaa37dadf_7.0.3_20150727042215/test_web_xinfang_list/0/time.log");
		TestCase webViewCase1 = webViewLoop.getTestCase(1);
		webViewCase1.setPass(true);
		webViewCase1.setLogFile("repo/result/ios/iPhone5_2G_2f2fb64220ed34f645d33cd222280efcaa37dadf_7.0.3_20150727042215/test_web_xinfang_list/1/time.log");

		webViewLoop.setEndTime(TimeUtil.formatTimeStamp(System
				.currentTimeMillis()));
		
		
		iosTestresult.serializeResultToXml();
	}

}
