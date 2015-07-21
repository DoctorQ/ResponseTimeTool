package com.wuba.logparser;

import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;

import com.wuba.model.RTResult;

public class AndroidLogParserTest {
	private final static Logger LOGGER = Logger
			.getLogger(AndroidLogParserTest.class);
	private LogParser androidLog;

	@BeforeGroups(groups = "unittest")
	public void setUp() {
		androidLog = new AndroidLogParser();
	}

	@Test(groups ={"unittest"})
	public void parserJsonTest() {
		File file = new File(this.getClass().getResource("/data/time_points.txt").getPath());
		RTResult result = androidLog.parserLog(file);
		AssertJUnit.assertNotNull(result);
		LOGGER.debug(result.toString());
	}
	@Test(groups = {"unittest"})
	public void parserXMLTest() {
		File file = new File(
				this.getClass().getResource("/data/xml.txt").getPath());
		RTResult result = androidLog.parserLog(file);
		Assert.assertNotNull(result);
		LOGGER.debug(result.toString());
	}
	//log文件不存在测试
	@Test(groups = {"unittest"})
	public void nullLogFileTest(){
		File file = new File(
				"test.txt");
		RTResult result = androidLog.parserLog(file);
		Assert.assertNull(result);
	}
	//log文件不匹配测试
	@Test(groups = {"unittest"})
	public void noMatcherFileTest(){
		File file = new File(
				this.getClass().getResource("/data/nomatch.txt").getPath());
		RTResult result = androidLog.parserLog(file);
		Assert.assertNull(result);
		
	}
	//测试时间为null时
	@Test(groups = {"unittest"})
	public void parserStringToLongForTimeByNull(){
		long time = androidLog.parserStringToLongForTime(null);
		Assert.assertEquals(time, 0);
	}
	@Test
	public void patternTest(){
		String BEGIN_PATTERN = "(http:.*)\\|([0-9]+)\\|begin\\*{6}\\|([0-9]+)$";
		String str = "D/time_points(23849): http://app.58.com/api/detail/xinfang/20527557035274?v=1&format=xml&localname=bt|1422421772044|begin******|1422421772044";
		Matcher matcher = Pattern.compile(BEGIN_PATTERN).matcher(str);
		if(matcher.find()){
			LOGGER.debug(matcher.group(1));
			
		}
	}
	@Test
	public void formatPatternTest(){
		String BEGIN_PATTERN = "\\|%s\\|begin\\*{6}\\|([0-9]+)$";
		String str = String.format(BEGIN_PATTERN, "122934334343");
		LOGGER.debug(str);
	}
}
