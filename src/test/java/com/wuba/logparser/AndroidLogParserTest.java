package com.wuba.logparser;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wuba.model.RTResult;

public class AndroidLogParserTest {

	private LogParser androidLog;

	@BeforeClass
	public void setUp() {
		androidLog = new AndroidLogParser();
	}

	@Test(groups = { "init" })
	public void logInitTest() {

		File file = new File(
				"/Users/wuxian/Documents/sourcecode/self/ResponseTimeTool/log/android/time_points.txt");
		RTResult result = androidLog.parserLog(file);

		Assert.assertNotNull(result);
	}
}
