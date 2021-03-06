/**
 * 
 */
package com.wuba.report;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import com.wuba.report.ExcelReportGenerator;
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
	private static final Logger LOG = Logger
			.getLogger(ReportGeneratorProxyTest.class);

	@BeforeGroups(groups = { "unittest" })
	public void setUp() {
		proxy = new ReportGeneratorProxy();
		proxy.addReportGenerator(new XmlReportGenerator());

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
	 * android测试多目录合成报告
	 */
	@Test(groups = { "unittest" }, dependsOnGroups = { "androidtestresult" })
	public void generatorAndroidReportByMuiltDirTest() {
		File file1 = new File(DirStructureUtil.getResultAndroid(),
				"MI_2G_dksldfdffdf_4.4.2_201507210823923");
		File file2 = new File(DirStructureUtil.getResultAndroid(),
				"MI_3G_dksldfdffdf_4.4.2_20150721082392");
		File file3 = new File(DirStructureUtil.getResultAndroid(),
				"MI_4G_dksldfdffdf_4.4.2_20150721082392");

		File file4 = new File(DirStructureUtil.getResultAndroid(),
				"HW_4G_12323242_4.4.2_20150721082392");

		List<File> list = new ArrayList<File>();
		list.add(file1);
		list.add(file2);
		list.add(file3);
		list.add(file4);
		proxy.generateReporter(list);

	}

	/**
	 * android测试单目录报告
	 */
	@Test(groups = { "unittest" }, dependsOnGroups = { "androidtestresult" })
	public void generatorAndroidReportByDirTest() {
		File file = new File(DirStructureUtil.getResultAndroid(),
				"MI_2G_dksldfdffdf_4.4.2_201507210823923");
		proxy.generateReporter(file);
	}

	@Test
	public void filePathTest() {
		File file = new File(DirStructureUtil.getReportAndroid(),
				"testResult.xml");
		LOG.info(file.getAbsolutePath());
	}

	/**
	 * android测试多目录合成报告
	 */
	@Test(groups = { "unittest" }, dependsOnGroups = { "iostestresult" })
	public void generatorIOSReportByMuiltDirTest() {
		File file1 = new File(DirStructureUtil.getResultIOS(),
				"iPhone5_2G_2f2fb64220ed34f645d33cd222280efcaa37dadf_7.0.3_20150727042215");
		File file2 = new File(DirStructureUtil.getResultIOS(),
				"iPhone5_3G_2f2fb64220ed34f645d33cd222280efcaa37dadf_7.0.3_20150727042215");

		List<File> list = new ArrayList<File>();
		list.add(file1);
		list.add(file2);

		proxy.generateReporter(list);

	}

	/**
	 * android测试单目录报告
	 */
	@Test(groups = { "unittest" }, dependsOnGroups = { "iostestresult" })
	public void generatorIOSReportByDirTest() {
		File file = new File(DirStructureUtil.getResultIOS(),
				"iPhone5_2G_2f2fb64220ed34f645d33cd222280efcaa37dadf_7.0.3_20150727042215");
		proxy.generateReporter(file);
	}

	/**
	 * android测试单目录报告
	 */
	@Test(groups = { "unittest" }, dependsOnGroups = { "iostestresult",
			"androidtestresult" })
	public void generatorCrossPlatformReportTest() {
		File file = new File(DirStructureUtil.getResultIOS(),
				"iPhone5_2G_2f2fb64220ed34f645d33cd222280efcaa37dadf_7.0.3_20150727042215");

		File file1 = new File(DirStructureUtil.getResultIOS(),
				"iPhone5_3G_2f2fb64220ed34f645d33cd222280efcaa37dadf_7.0.3_20150727042215");
		File file2 = new File(DirStructureUtil.getResultAndroid(),
				"MI_2G_dksldfdffdf_4.4.2_201507210823923");
		File file3 = new File(DirStructureUtil.getResultAndroid(),
				"MI_4G_dksldfdffdf_4.4.2_20150721082392");

		File file4 = new File(DirStructureUtil.getResultAndroid(),
				"HW_4G_12323242_4.4.2_20150721082392");

		List<File> list = new ArrayList<File>();
		list.add(file);
		list.add(file1);
		list.add(file2);
		list.add(file3);
		list.add(file4);
		proxy.generateReporter(list);
	}
}
