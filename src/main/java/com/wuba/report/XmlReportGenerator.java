/**
 * 
 */
package com.wuba.report;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;

import com.sun.net.httpserver.Authenticator.Success;
import com.wuba.logparser.AndroidLogParser;
import com.wuba.logparser.IOSLogParser;
import com.wuba.logparser.LogParser;
import com.wuba.result.TestCaseLoop;
import com.wuba.result.TestResult;
import com.wuba.utils.Constant;
import com.wuba.utils.DirStructureUtil;
import com.wuba.utils.TimeUtil;

/**
 * @author hui.qian qianhui@58.com
 * @date 2015年7月22日 下午5:19:14
 */
public class XmlReportGenerator implements ReportGenerator {
	private static final Logger LOG = Logger
			.getLogger(XmlReportGenerator.class);

	private TestReport mTestReport = null;
	private LogParser mLogParser = null;

	/*
	 * @see com.wuba.result.ReportGenerator#generateReporter(java.io.File)
	 */

	@Override
	public void generateReporter(File rootDir) {
		if (rootDir == null || !rootDir.exists()) {
			LOG.error("Null param rootDir  or file no exists");
			return;
		}
		// getPlatform(platform);
		generateReportFile();
		parserTestReportByTestResult(rootDir);

		mTestReport.serializeResultToXml();

	}

	/*
	 * 
	 * @see
	 * com.wuba.result.ReportGenerator#generateReporter(com.sun.tools.javac.
	 * util.List)
	 */
	@Override
	public void generateReporter(List<File> list) {
		if (list == null || list.size() == 0) {
			return;
		}
		generateReportFile();
		// getPlatform(platform);

		for (File file : list) {
			parserTestReportByTestResult(file);
		}

		mTestReport.serializeResultToXml();

	}

	private void generateReportFile() {
		File reportFile = new File(DirStructureUtil.getReport(),
				getFileNameFromTimeStamp());
		createNewFile(reportFile);
		mTestReport = new TestReport(reportFile);
	}

	private void parserTestReportByTestResult(File rootDir) {
		TestResult testResult = new TestResult(rootDir);
		// 解析testResult.xml文件
		testResult.parserXml();

		getPlatform(testResult.getPlatform());

		LOG.info(String.format("Read %s finished", "testResult.xml"));
		LOG.info(testResult.toString());
		// 配置testReport.xml对象
		TestDevice testDevice = mTestReport.getTestDevice(testResult.getSn());
		testDevice.setDevice(testResult.getDevice());
		testDevice.setVersion(testResult.getVersion());

		TestNetWork netWork = testDevice
				.getTestNetWork(testResult.getNetwork());

		netWork.setTestViewLoops(testResult.getLoops(), mLogParser);
	}

	/**
	 * 根据平台名得到相应的log解析器，以及设置报告的存放位置
	 * 
	 * @param platform
	 */
	private void getPlatform(String platform) {
		mTestReport.setPlatform(platform);
		if (Constant.ANDROID_PLATFORM.equals(platform)) {
			mLogParser = new AndroidLogParser();
			// mTestReport.setPlatform(Constant.ANDROID_PLATFORM);

		} else if (Constant.IOS_PLATFORM.equals(platform)) {
			mLogParser = new IOSLogParser();
			// mTestReport.setPlatform(Constant.IOS_PLATFORM);
		}
	}

	private void createNewFile(File reportFile) {
		if (reportFile.exists())
			return;
		boolean success = reportFile.mkdir();
		if (!success) {
			LOG.error(String.format("%s create failed",
					reportFile.getAbsolutePath()));
		}
	}

	private String getFileNameFromTimeStamp() {
		return TimeUtil.formatTimeForFile(System.currentTimeMillis());
	}

}
