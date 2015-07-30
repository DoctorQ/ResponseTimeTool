/**
 * 
 */
package com.wuba.report;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.testng.log4testng.Logger;

/**
 * @author hui.qian qianhui@58.com
 * @date 2015年7月22日 下午5:19:51
 */
public class ExcelReportGenerator {
	private static final Logger LOG = Logger
			.getLogger(ExcelReportGenerator.class);
	private static final String XLSFILE = "testReport.xls";

	private static final int COLLIMIT = 6;
	private TestReport testReport;

	private String[] tableTitle = { "Case", "ViewType", "DataType",
			"Connect(ms)", "Connect(ms)", "Paser(ms)", "Total(ms)" };

	// 行号
	private int row = 0;

	// 列号

	private int col = 0;
	private WritableCellFormat titleFormate;
	private WritableCellFormat deviceTitleFormate;
	private WritableCellFormat netWorkTitleFormate;
	private WritableCellFormat tableTitleFormate;
	private WritableCellFormat dataFormate;

	/**
	 * @param dir
	 *            excel报告存放的根目录
	 */
	public ExcelReportGenerator(TestReport testReport) {
		this.testReport = testReport;

	}

	public void generaterExcelReport() {

		if (testReport == null)
			return;

		File xlsFile = new File(testReport.getReportDir(), XLSFILE);
		if (!xlsFile.exists())
			try {
				boolean success = xlsFile.createNewFile();
				if (!success)
					return;
				generaterExcelReport(createOutputResultStream(xlsFile));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RowsExceededException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	private void generaterExcelReport(OutputStream os) throws IOException,
			RowsExceededException, WriteException {

		initWritableCellFormat();
		// 创建工作薄
		WritableWorkbook workbook = Workbook.createWorkbook(os);
		// 创建新的一页
		WritableSheet sheet = workbook.createSheet("Performance data", 0);

		// 添加合并单元格，第一个参数是起始列，第二个参数是起始行，第三个参数是终止列，第四个参数是终止行
		sheet.mergeCells(0, row, COLLIMIT, row);

		Label title = new Label(0, 0, "性能测试-响应时间测试报告", titleFormate);
		sheet.setRowView(0, 600, false);// 设置第一行的高度
		sheet.addCell(title);

		Collection<TestDevice> collection = testReport.getTestDevices()
				.values();

		for (TestDevice testDevice : collection) {
			sheet.mergeCells(0, ++row, COLLIMIT, row);
			sheet.setRowView(row, 500, false);
			Label deviceTitle = new Label(0, row, testDevice.getSn() + "-"
					+ testDevice.getDevice() + "-" + testDevice.getVersion(),
					deviceTitleFormate);
			sheet.addCell(deviceTitle);

			Collection<TestNetWork> netWorks = testDevice.getNetTypes()
					.values();

			for (TestNetWork netWork : netWorks) {
				sheet.mergeCells(0, ++row, COLLIMIT, row);
				sheet.setRowView(row, 500, false);
				Label netWorkTitle = new Label(0, row, "Test Summary For "
						+ netWork.getType(), netWorkTitleFormate);
				sheet.addCell(netWorkTitle);

				Collection<TestViewLoop> testViewLoops = netWork
						.getTestViewLoops().values();

				// 设置table的标题
				getRowAndAdd();
				for (int i = 0; i < tableTitle.length; i++) {
					Label colTitle = new Label(i, row, tableTitle[i],
							tableTitleFormate);
					sheet.addCell(colTitle);
				}

				for (TestViewLoop loop : testViewLoops) {
					String[] atts = loop.getAtts();
					getRowAndAdd();
					for (int i = 0; i < atts.length; i++) {
						Label colTitle = new Label(i, row, atts[i], dataFormate);
						sheet.addCell(colTitle);
					}
				}

			}
			getRowAndAdd();

		}

		// 把创建的内容写入到输出流中，并关闭输出流
		workbook.write();
		workbook.close();
		os.close();

	}

	OutputStream createOutputResultStream(File reportFile) throws IOException {
		LOG.info(String.format("Created file at file://%s",
				reportFile.getAbsolutePath()));
		return new FileOutputStream(reportFile);
	}

	/**
	 * 得到行号
	 * 
	 * @return
	 */
	private int getRow() {
		return row;
	}

	private int getRowAndAdd() {
		return row++;
	}

	private void initWritableCellFormat() throws WriteException {
		WritableFont bold = new WritableFont(WritableFont.ARIAL, 10,
				WritableFont.BOLD);// 设置字体种类和黑体显示,字体为Arial,字号大小为10,采用黑体显示

		titleFormate = new WritableCellFormat(bold);
		titleFormate.setAlignment(jxl.format.Alignment.CENTRE);// 单元格中的内容水平方向居中
		titleFormate.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 单元格的内容垂直方向居中

		deviceTitleFormate = new WritableCellFormat(bold);
		deviceTitleFormate.setAlignment(jxl.format.Alignment.CENTRE);
		deviceTitleFormate
				.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 单元格的内容垂直方向居中

		netWorkTitleFormate = new WritableCellFormat(bold);
		netWorkTitleFormate.setAlignment(jxl.format.Alignment.LEFT);
		netWorkTitleFormate
				.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 单元格的内容垂直方向居中
		tableTitleFormate = new WritableCellFormat(bold);
		tableTitleFormate.setAlignment(jxl.format.Alignment.LEFT);
		tableTitleFormate
				.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 单元格的内容垂直方向居中
		tableTitleFormate.setWrap(true);

		dataFormate = new WritableCellFormat(bold);
		dataFormate.setAlignment(jxl.format.Alignment.LEFT);
		dataFormate.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 单元格的内容垂直方向居中
		dataFormate.setWrap(true);

	}

}
