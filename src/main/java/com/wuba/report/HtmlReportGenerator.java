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

import com.wuba.utils.TimeUtil;


/**
 * @author hui.qian qianhui@58.com  
 * @date 2015年7月22日 下午5:22:44
 */
public class HtmlReportGenerator implements ReportGenerator {
	/*
	 * 报告格式文件xsl文件名
	 */
	private static final String RESULT_XSL = "ci_result.xsl";
	/*
	 * html报告的后缀名，因为会存放历史数据，所以以时间戳来区分报告，所以最终的报告会以时间戳+html后缀名
	 */
	private static final String RESULT_HTML = "ci_result.html";

	private static final String ROOT_DIR = System.getProperty("user.dir")
			+ File.separator;
	/*
	 * 资源文件存放目录，里面存放有报告的xsl文件(配置报告的格式)，email配置文件
	 */
	private static final String PATH_SOURCE = ROOT_DIR + "build/resources/main"
			+ File.separator;
	/*
	 * 报告结果存放目录，里面存放生成的ci_result.xml文件和ci_result.xml文件
	 */
	private static final String PATH_RESULT = ROOT_DIR + "result"
			+ File.separator;

	

	/**
	 * 指定xml文件来生成html文件
	 * 
	 * @param resultXMLPath
	 * @return
	 */
	public static String transferToHtml(String resultXMLPath) {
		String xmlPath = resultXMLPath;
		String xlsPath = PATH_SOURCE + RESULT_XSL;
		String htmlPath = PATH_RESULT + TimeUtil.getTimestampForFile() + "_"
				+ RESULT_HTML;
		if (!isExist(xlsPath) || !isExist(xmlPath)) {
			return null;
		}
		transferToHtml(xlsPath, xmlPath, htmlPath);
		return htmlPath;
	}

	private static boolean isExist(String path) {
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
	public static void transferToHtml(String xsl, String xml, String html) {
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

	/* (non-Javadoc)
	 * @see com.wuba.result.ReportGenerator#generateReporter(java.io.File)
	 */
	@Override
	public void generateReporter(File rootDir,String platform) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.wuba.result.ReportGenerator#generateReporter(com.sun.tools.javac.util.List)
	 */
	@Override
	public void generateReporter(List<File> list, String platform) {
		// TODO Auto-generated method stub
		
	}

}
