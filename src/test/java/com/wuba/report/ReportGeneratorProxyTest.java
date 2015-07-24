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
import com.wuba.report.CsvReportGenerator;
import com.wuba.report.HtmlReportGenerator;
import com.wuba.report.ReportGenerator;
import com.wuba.report.ReportGeneratorProxy;
import com.wuba.report.XmlReportGenerator;
import com.wuba.utils.DirStructureUtil;

/**
 * @author hui.qian qianhui@58.com
 * @date 2015年7月22日 下午5:35:16
 */
public class ReportGeneratorProxyTest {
	private ReportGeneratorProxy proxy;
	private ReportGenerator csv;
	private File logFile;
	private static final Logger LOG = Logger.getLogger(ReportGeneratorProxyTest.class);

	@BeforeGroups(groups = { "unittest" })
	public void setUp() {
		proxy = new ReportGeneratorProxy();
		proxy.addReportGenerator(new HtmlReportGenerator());
		csv = new CsvReportGenerator();
		proxy.addReportGenerator(csv);
		proxy.addReportGenerator(new XmlReportGenerator());

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
	public void generatorAndroidReportByMuiltDirTest() {

	}

	/**
	 * 测试单目录报告
	 */
	@Test(groups = { "unittest" })
	public void generatorAndroidReportByDirTest() {
		File file = new File(DirStructureUtil.getResultAndroid(),
				"MI_2G_dksldfdffdf_4.4.2_201507210823923");
		proxy.generateReporter(file,new AndroidLogParser());
	}
	@Test
	public void filePathTest(){
		File file = new File(DirStructureUtil.getReportAndroid(),"testResult.xml");
		LOG.info(file.getAbsolutePath());
	}

}
