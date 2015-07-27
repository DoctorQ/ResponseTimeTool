/**
 * 
 */
package com.wuba.logparser;

import org.testng.annotations.BeforeGroups;

/**
 * @author hui.qian qianhui@58.com  
 * @date 2015年7月27日 上午11:38:59
 */
public class IOSLogParserTest {
	
	private LogParser logParser;
	
	@BeforeGroups(groups = "unittest")
	public void setUp() {
		logParser = new IOSLogParser();
	}
	
	

}
