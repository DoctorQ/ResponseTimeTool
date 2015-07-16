package com.wuba.logparser;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wuba.model.RTResult;

public class AndroidLogParserTest {
	private final static Logger logger = Logger
			.getLogger(AndroidLogParserTest.class);
	private LogParser androidLog;

	@BeforeMethod
	@BeforeClass
	public void setUp() {
		androidLog = new AndroidLogParser();
	}

	@Test(groups = { "main" })
	public void parserJsonTest() {
		logger.debug(new File(".").getAbsolutePath());
		File file = new File(
				"log/android/time_points.txt");
		RTResult result = androidLog.parserLog(file);
		AssertJUnit.assertNotNull(result);
		logger.debug(result.toString());
	}
	@Test(groups = { "main" })
	public void parserXMLTest() {

		File file = new File(
				"log/android/xml.txt");
		RTResult result = androidLog.parserLog(file);
		AssertJUnit.assertNotNull(result);
		logger.debug(result.toString());
	}
	@Test
	public void patternTest(){
		String BEGIN_PATTERN = "(http:.*)\\|([0-9]+)\\|begin\\*{6}\\|([0-9]+)$";
		String str = "D/time_points(23849): http://app.58.com/api/detail/xinfang/20527557035274?v=1&format=xml&localname=bt|1422421772044|begin******|1422421772044";
		Matcher matcher = Pattern.compile(BEGIN_PATTERN).matcher(str);
		if(matcher.find()){
			logger.debug(matcher.group(1));
			
		}
	}
	@Test
	public void formatPatternTest(){
		String BEGIN_PATTERN = "\\|%s\\|begin\\*{6}\\|([0-9]+)$";
		String str = String.format(BEGIN_PATTERN, "122934334343");
		logger.debug(str);
	}
}
