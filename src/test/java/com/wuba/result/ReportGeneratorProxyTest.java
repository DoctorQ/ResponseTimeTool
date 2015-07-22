/**
 * 
 */
package com.wuba.result;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

/**
 * @author hui.qian qianhui@58.com
 * @date 2015年7月22日 下午5:35:16
 */
public class ReportGeneratorProxyTest {
	private ReportGeneratorProxy proxy;
	private ReportGenerator csv;
	private File logFile;

	@BeforeGroups(groups = { "unittest" })
	public void setUp() {
		proxy = new ReportGeneratorProxy();
		proxy.addReportGenerator(new HtmlReportGenerator());
		csv = new CsvReportGenerator();
		proxy.addReportGenerator(csv);
		
		logFile = new File("");

	}

	/**
	 * 测试添加报告器的方法
	 */
	@Test(groups = { "unittest" })
	public void addReportGeneratorTest() {
		int before = proxy.getReportGenerators().size();
		proxy.addReportGenerator(new XmlReportGenerator());
		int after = proxy.getReportGenerators().size();
		Assert.assertEquals(1, after - before);
	}

	/**
	 * 测试剔除报告器的方法
	 */
	@Test(groups = { "unittest" })
	public void removeReportGeneratorTest() {
		int before = proxy.getReportGenerators().size();
		proxy.removeReportGenerator(csv);
		int after = proxy.getReportGenerators().size();
		Assert.assertEquals(1, before - after);

	}

	/**
	 * 测试多目录合成报告
	 */
	@Test(groups = { "unittest" })
	public void generatorReportByMuiltDirTest() {
		File file = new File("");
		proxy.generateReporter(file);

	}

	/**
	 * 测试单目录报告
	 */
	@Test(groups = { "unittest" })
	public void generatorReportByDirTest() {

	}

}
