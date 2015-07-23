/**
 * 
 */
package com.wuba.result;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.kxml2.io.KXmlSerializer;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.wuba.utils.Constant;

/**
 * @author hui.qian qianhui@58.com
 * @date 2015年7月23日 下午2:38:11
 */
public class TestCaseLoop extends AbstractXmlPullParser implements XMLParser {

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPass() {
		return pass;
	}

	public void setPass(int pass) {
		this.pass = pass;
	}

	public int getFail() {
		return fail;
	}

	public void setFail(int fail) {
		this.fail = fail;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getLoop() {
		return loop;
	}

	public void setLoop(int loop) {
		this.loop = loop;
	}

	public Map<Integer, TestCase> getTestCases() {
		return testCases;
	}

	public void setTestCases(Map<Integer, TestCase> testCases) {
		this.testCases = testCases;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public static final String TESTCASELOOP_TAG = "TestCaseLoop";
	private static final String NAME_ATTR = "name";
	private static final String TOTAL_ATTR = "total";
	private static final String PASS_ATTR = "pass";
	private static final String FAIL_ATTR = "fail";
	private static final String CASE_PATH_ATTR = "path";
	private static final String CASE_LOOP_ATTR = "loop";
	private static final String STARTTIME_ATTR = "starttime";
	private static final String ENDTIME_ATTR = "endtime";

	// case名
	private String name = "";
	// 执行的总次数
	private int total;
	// case的pass数
	private int pass;
	// case的fail数
	private int fail;
	// case的路径
	private String path = "";
	// case的设置的重复次数
	private int loop;
	// case执行的开始时间
	private String startTime = "";
	// case执行的结束时间
	private String endTime = "";

	private Map<Integer, TestCase> testCases = new LinkedHashMap<Integer, TestCase>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wuba.report.XMLParser#serialize(org.kxml2.io.KXmlSerializer)
	 */
	@Override
	public void serialize(KXmlSerializer serializer) throws IOException {
		// TODO Auto-generated method stub
		Collection<TestCase> collection = testCases.values();
		statistics(collection);
		serializer.startTag(Constant.NAMESPACE, TESTCASELOOP_TAG);
		serializer.attribute(Constant.NAMESPACE, NAME_ATTR, getName());
		serializer.attribute(Constant.NAMESPACE, TOTAL_ATTR, getTotal() + "");
		serializer.attribute(Constant.NAMESPACE, PASS_ATTR, getPass() + "");
		serializer.attribute(Constant.NAMESPACE, FAIL_ATTR, getFail() + "");
		serializer.attribute(Constant.NAMESPACE, CASE_PATH_ATTR, getPath());
		serializer
				.attribute(Constant.NAMESPACE, CASE_LOOP_ATTR, getLoop() + "");
		serializer
				.attribute(Constant.NAMESPACE, STARTTIME_ATTR, getStartTime());
		serializer.attribute(Constant.NAMESPACE, ENDTIME_ATTR, getEndTime());
		for (TestCase testCase : collection) {
			testCase.serialize(serializer);
		}
		serializer.endTag(Constant.NAMESPACE, TESTCASELOOP_TAG);

	}

	public TestCase getTestCase(int index) {

		TestCase testCase = testCases.get(index);
		if (testCase == null) {
			testCase = new TestCase();
			testCase.setIndex(index + "");
			testCases.put(index, testCase);

		}
		return testCase;

	}

	private void statistics(Collection<TestCase> collection) {
		total = collection.size();

		for (TestCase test : collection) {
			if (test.isPass())
				pass++;
		}

		fail = total - pass;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wuba.result.AbstractXmlPullParser#parse(org.xmlpull.v1.XmlPullParser)
	 */
	@Override
	public void parse(XmlPullParser parser) throws XmlPullParserException,
			IOException {
		// TODO Auto-generated method stub
		if (!parser.getName().equals(TESTCASELOOP_TAG)) {
			return;
		}
		setStartTime(getAttribute(parser, STARTTIME_ATTR));
		setTotal(Integer.parseInt(getAttribute(parser, TOTAL_ATTR)));
		
		setEndTime(getAttribute(parser, ENDTIME_ATTR));
		int eventType = parser.next();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			if (eventType == XmlPullParser.START_TAG
					&& parser.getName().equals(TestCase.TESTCASE_TAG)) {
				TestCase testCase = new TestCase();
				testCase.parse(parser);
				testCases.put(Integer.parseInt(testCase.getIndex()), testCase);
			} else if (eventType == XmlPullParser.END_TAG
					&& parser.getName().equals(TESTCASELOOP_TAG)) {
				return;
			}
			eventType = parser.next();
		}

	}

}
