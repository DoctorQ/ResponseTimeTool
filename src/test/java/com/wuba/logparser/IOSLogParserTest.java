/**
 * 
 */
package com.wuba.logparser;

import java.io.File;

import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

/**
 * @author hui.qian qianhui@58.com
 * @date 2015年7月27日 上午11:38:59
 */
public class IOSLogParserTest {

	private LogParser logParser;
	private File file;

	@BeforeGroups(groups = "unittest")
	public void setUp() {
		logParser = new IOSLogParser();
		file = new File("");
	}

	@Test(groups = { "unittest" })
	public void parserLogTest() {
		logParser.parserLog(file);

	}

}
