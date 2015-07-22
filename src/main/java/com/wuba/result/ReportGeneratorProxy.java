/**
 * 
 */
package com.wuba.result;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hui.qian qianhui@58.com
 * @date 2015年7月22日 下午5:28:10
 */
public class ReportGeneratorProxy implements ReportGenerator {

	public List<ReportGenerator> getReportGenerators() {
		return reportGenerators;
	}

	private List<ReportGenerator> reportGenerators = new ArrayList<ReportGenerator>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wuba.result.ReportGenerator#generateReporter(java.io.File)
	 */
	@Override
	public void generateReporter(File rootDir) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		for (ReportGenerator reportGenerator : reportGenerators) {
			reportGenerator.generateReporter(rootDir);
		}

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
		for (ReportGenerator reportGenerator : reportGenerators) {
			reportGenerator.generateReporter(list);
		}

	}

	public void addReportGenerator(ReportGenerator reportGenerator) {
		reportGenerators.add(reportGenerator);
	}

	public void removeReportGenerator(ReportGenerator reportGenerator) {
		int index = reportGenerators.indexOf(reportGenerator);
		if (index != -1) {
			reportGenerators.remove(reportGenerator);
		}
	}

}
