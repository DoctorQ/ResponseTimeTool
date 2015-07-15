package com.wuba.logparser;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	@Test(groups = { "main" })
	public void parserJsonTest() {

		File file = new File(
				"/Users/wuxian/Documents/sourcecode/self/ResponseTimeTool/log/android/time_points.txt");
		RTResult result = androidLog.parserLog(file);
		Assert.assertNotNull(result);
		System.out.println(result.toString());
	}
	@Test(groups = { "main" })
	public void parserXMLTest() {

		File file = new File(
				"/Users/wuxian/Documents/sourcecode/self/ResponseTimeTool/log/android/xml.txt");
		RTResult result = androidLog.parserLog(file);
		Assert.assertNotNull(result);
		System.out.println(result.toString());
	}
	@Test
	public void patternTest(){
		String BEGIN_PATTERN = "(http:.*)\\|([0-9]+)\\|begin\\*{6}\\|([0-9]+)$";
		String str = "D/time_points(23849): http://app.58.com/api/detail/xinfang/20527557035274?v=1&format=xml&localname=bt|1422421772044|begin******|1422421772044";
		Matcher matcher = Pattern.compile(BEGIN_PATTERN).matcher(str);
		if(matcher.find()){
			System.out.println(matcher.group(1));
			
		}
	}
	@Test
	public void formatPatternTest(){
		String BEGIN_PATTERN = "\\|%s\\|begin\\*{6}\\|([0-9]+)$";
		String str = String.format(BEGIN_PATTERN, "122934334343");
		System.out.println(str);
	}
}
