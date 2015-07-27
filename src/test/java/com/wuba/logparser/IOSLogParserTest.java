/**
 * 
 */
package com.wuba.logparser;

import java.io.File;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import com.wuba.model.RTResult;
import com.wuba.utils.DirStructureUtil;

/**
 * @author hui.qian qianhui@58.com
 * @date 2015年7月27日 上午11:38:59
 */
public class IOSLogParserTest {

	private LogParser logParser;
	private File nativeFile;
	private File webViewFile;

	@BeforeGroups(groups = "unittest")
	public void setUp() {
		logParser = new IOSLogParser();
		nativeFile = new File(
				DirStructureUtil.getResultIOS(),
				"iPhone5_2G_2f2fb64220ed34f645d33cd222280efcaa37dadf_7.0.3_20150727042215/test_native_zhengzu_list/0/time.log");
		webViewFile = new File(
				DirStructureUtil.getResultIOS(),
				"iPhone5_2G_2f2fb64220ed34f645d33cd222280efcaa37dadf_7.0.3_20150727042215/test_web_xinfang_list/0/time.log");
	}

	@Test(groups = { "unittest" })
	public void parserNativeLogTest() {
		RTResult result = logParser.parserLog(nativeFile);
		System.out.println(result.toString());
	}
	
	@Test(groups = { "unittest" })
	public void parserWebViewLogTest() {
		RTResult result = logParser.parserLog(webViewFile);
		System.out.println(result.toString());
	}

}
