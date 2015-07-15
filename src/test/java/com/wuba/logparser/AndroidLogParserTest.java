package com.wuba.logparser;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;




public class AndroidLogParserTest {
	@BeforeClass
	public void setUp() {

	}

	@Test(groups = { "init" })
	public void logInitTest() {
		LogParser androidLog = new AndroidLogParser();
		File file = new File("/Users/wuxian/Documents/sourcecode/self/ResponseTimeTool/log/android/time_points.txt");
		String timeStamp = androidLog.parserLog(file);
		
		Assert.assertNotNull(timeStamp);
	}
}
