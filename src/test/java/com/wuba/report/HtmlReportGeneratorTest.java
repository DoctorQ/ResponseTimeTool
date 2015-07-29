/**
 * 
 */
package com.wuba.report;

import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

/**
 * @author hui.qian qianhui@58.com
 * @date 2015年7月29日 上午11:45:40
 */
public class HtmlReportGeneratorTest {
	private HtmlReportGenerator htmlReport;

	@BeforeGroups(groups = { "unittest" })
	public void setUp() {
		htmlReport = new HtmlReportGenerator();
	}

	@Test(groups = { "unittest" })
	public void transferToHtmlTest() {
		htmlReport.transferToHtml(System.getProperty("user.dir")+"/repo/report/android/2015-07-28_19-10-33/testReport.xml");

	}

}
