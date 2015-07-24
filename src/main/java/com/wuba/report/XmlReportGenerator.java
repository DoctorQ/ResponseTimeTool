/**
 * 
 */
package com.wuba.report;

import java.io.File;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;

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
	private static final String TESTREPORT_XML = "testReport_%s.xml";
	

	/*
	 * @see com.wuba.result.ReportGenerator#generateReporter(java.io.File)
	 */
	
	@Override
	public void generateReporter(File rootDir, String platform) {
		if (rootDir == null || !rootDir.exists()) {
			LOG.error("Null param rootDir  or file no exists");
			return;
		}
		if(Constant.ANDROID_PLATFORM.equals(platform)){
			mLogParser = new AndroidLogParser();
			mTestReport = new TestReport(new File(DirStructureUtil.getReportAndroid(),getFileNameFromTimeStamp()));
			
		}else if(Constant.IOS_PLATFORM.equals(platform)){
			mLogParser = new IOSLogParser();
			mTestReport = new TestReport(new File(DirStructureUtil.getReportIOS(),getFileNameFromTimeStamp()));
		}
		
		TestResult testResult = new TestResult(rootDir);
		//解析testResult.xml文件
		testResult.parserXml();
		
		LOG.info(String.format("Read %s finished", "testResult.xml"));
		
		//配置testReport.xml对象
		TestDevice testDevice = mTestReport.getTestDevice(testResult.getSn());
		testDevice.setDevice(testResult.getDevice());
		testDevice.setPlatform(testResult.getPlatform());
		
		TestNetWork netWork = testDevice.getTestNetWork(testResult.getNetwork());
		
		
		netWork.setTestViewLoops(testResult.getLoops(), mLogParser);
		
		
		mTestReport.serializeResultToXml();
		
	}

	private String getFileNameFromTimeStamp() {
		return String.format(TESTREPORT_XML, TimeUtil.formatTimeForFile(System.currentTimeMillis()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wuba.result.ReportGenerator#generateReporter(com.sun.tools.javac.
	 * util.List)
	 */
	@Override
	public void generateReporter(List<File> list) {
		// TODO Auto-generated method stub

	}

}
