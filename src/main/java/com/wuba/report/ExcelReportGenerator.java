/**
 * 
 */
package com.wuba.report;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.testng.log4testng.Logger;

/**
 * @author hui.qian qianhui@58.com
 * @date 2015年7月22日 下午5:19:51
 */
public class ExcelReportGenerator {
	private static final Logger LOG = Logger
			.getLogger(ExcelReportGenerator.class);
	private static final String XLSFILE = "testReport.xls";

	private TestReport testReport;

	/**
	 * @param dir
	 *            excel报告存放的根目录
	 */
	public ExcelReportGenerator(TestReport testReport) {
		this.testReport = testReport;

	}

	public void generaterExcelReport() {
		File xlsFile = new File(testReport.getReportDir(), XLSFILE);
		if (!xlsFile.exists())
			try {
				xlsFile.createNewFile();
				createOutputResultStream(xlsFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	OutputStream createOutputResultStream(File reportFile) throws IOException {
		LOG.info(String.format("Created file at file://%s",
				reportFile.getAbsolutePath()));
		return new FileOutputStream(reportFile);
	}

}
