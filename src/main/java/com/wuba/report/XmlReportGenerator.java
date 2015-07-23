/**
 * 
 */
package com.wuba.report;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;

import com.wuba.logparser.LogParser;

/**
 * @author hui.qian qianhui@58.com
 * @date 2015年7月22日 下午5:19:14
 */
public class XmlReportGenerator implements ReportGenerator {
	private static final Logger LOG = Logger
			.getLogger(XmlReportGenerator.class);

	private static final String XML_RESULT = "testReport.xml";
	private static final String LOG_TXT_FILE = "result.txt";
	private TestReport mTestResult = new TestReport();

	/*
	 * @see com.wuba.result.ReportGenerator#generateReporter(java.io.File)
	 */
	@Override
	public void generateReporter(File rootDir, LogParser logParser) {
		if (rootDir == null || !rootDir.exists()) {
			LOG.error("Null param rootDir  or file no exists");
			return;
		}
		// 获取case名,每个目录代表一个case
		File[] cases = rootDir.listFiles();
		for (File caseFile : cases) {
			if (caseFile.isFile())
				continue;
			TestViewLoop testCase = mTestResult.getTestCaseByName(caseFile
					.getName());
			File[] itemFiles = caseFile.listFiles();
			for (File itemFile : itemFiles) {
				TestView item = new TestView();
				item.setIndex(itemFile.getName());
				File logFile = new File(itemFile, LOG_TXT_FILE);
				item.setLogFile(logFile.getAbsolutePath());
				item.setRtResult(logParser.parserLog(logFile));
				testCase.addItem(item);
			}
		}
		mTestResult.serializeResultToXml(new File(rootDir, XML_RESULT));

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
