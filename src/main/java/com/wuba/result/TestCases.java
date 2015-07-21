/**
 * 
 */
package com.wuba.result;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.kxml2.io.KXmlSerializer;

import com.wuba.utils.Constant;

/**
 * @author hui.qian qianhui@58.com
 * @date 2015年7月20日 下午2:48:41
 */
public class TestCases implements XMLParser {
	private static final String XML_TAG = "TestCases";

	private Map<String, TestCase> testCases = new LinkedHashMap<String, TestCase>();

	private TestCase currentCase = null;

	/*
	 * @see com.wuba.result.XMLParser#serialize(org.kxml2.io.KXmlSerializer)
	 */
	@Override
	public void serialize(KXmlSerializer serializer) throws IOException {
		// TODO Auto-generated method stub
		serializer.startTag(Constant.NAMESPACE, XML_TAG);
		Collection<TestCase> collection = testCases.values();
		for (TestCase testCase : collection) {
			testCase.serialize(serializer);
		}
		serializer.endTag(Constant.NAMESPACE, XML_TAG);
	}
	
	public TestCase getTestCaseByName(String name) {
		currentCase = testCases.get(name);
		if (currentCase == null) {
			currentCase = new TestCase();
			currentCase.setName(name);
			testCases.put(name, currentCase);
		}
		return currentCase;
	}

}
