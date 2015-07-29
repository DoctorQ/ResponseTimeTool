/**
 * 
 */
package com.wuba.report;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;

/**
 * @author hui.qian qianhui@58.com
 * @date 2015年7月22日 下午5:22:44
 */
public class HtmlReportGenerator{
	private static final Logger LOG = Logger
			.getLogger(HtmlReportGenerator.class);
	/*
	 * 报告格式文件xsl文件名
	 */
	private static final String TESTREPORT_XSL = "testReport.xsl";
	/*
	 * html报告的后缀名，因为会存放历史数据，所以以时间戳来区分报告，所以最终的报告会以时间戳+html后缀名
	 */
	private static final String TESTREPORT_HTML = "testReport.html";

	/*
	 * 资源文件存放目录，里面存放有报告的xsl文件(配置报告的格式)，email配置文件
	 */

	private String xslPath;

	/*
	 * 报告结果存放目录，里面存放生成的ci_result.xml文件和ci_result.xml文件
	 */

	public HtmlReportGenerator() {
		xslPath = this.getClass().getResource("/testReport.xsl").getPath();
	}

	/**
	 * 用指定xml文件来生成html文件
	 * 
	 * @param resultXMLPath
	 * @return
	 */
	public void transferToHtml(String xmlPath) {
		if (!isExist(xslPath)) {
			LOG.error(String.format("%s no exists", xslPath));
			return;
		}

		if (!isExist(xmlPath)) {
			LOG.error(String.format("%s no exists", xmlPath));
			return;
		}
		String htmlPath = new File(xmlPath).getParent() + File.separator
				+ TESTREPORT_HTML;

		transferToHtml(xslPath, xmlPath, htmlPath);

	}

	private boolean isExist(String path) {
		return new File(path).exists();
	}

	/**
	 * 使用xsl和xml文件生成html文件
	 * 
	 * @param xsl
	 *            xsl文件路径
	 * @param xml
	 *            xml文件路径
	 * @param html
	 *            生成的html文件
	 */
	private void transferToHtml(String xsl, String xml, String html) {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(html);
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer(new StreamSource(
					xsl));
			transformer.transform(new StreamSource(xml), new StreamResult(out));
		} catch (FileNotFoundException e) {
		} catch (TransformerConfigurationException e) {
		} catch (TransformerException e) {
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}

	}


}
