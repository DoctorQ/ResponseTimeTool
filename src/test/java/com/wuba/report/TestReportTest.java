/**
 * 
 */
package com.wuba.report;

import java.io.File;

import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

/**
 * @author hui.qian qianhui@58.com
 * @date 2015年7月24日 下午12:17:55
 */
public class TestReportTest {

	private TestReport mTestReport;

	@BeforeGroups(groups = { "unittest" })
	public void setUp() {
		File file = new File(this.getClass().getResource("/result").getPath(),
				"testReport.xml");
		mTestReport = new TestReport(file);

	}

	@Test(groups = { "unittest" })
	public void testReportTest() {
		mTestReport.serializeResultToXml();

	}

}
