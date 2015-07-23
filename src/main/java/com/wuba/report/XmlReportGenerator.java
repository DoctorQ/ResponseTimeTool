/**
 * 
 */
package com.wuba.report;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;

import com.wuba.logparser.LogParser;
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
		File xmlFile = new File(rootDir, Constant.TESTRESULT_XML);

		
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
