/**
 * 
 */
package com.wuba.report;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.kxml2.io.KXmlSerializer;

import com.wuba.result.XMLParser;
import com.wuba.utils.Constant;

/**
 * @author hui.qian qianhui@58.com
 * @date 2015年7月24日 上午11:46:24
 */
public class TestNetWork implements XMLParser {

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	private static final String TESTNETWORK_TAG = "TestNetWork";

	private static final String TYPE_ATTR = "type";

	private String type;

	private Map<String, TestViewLoop> testCases = new LinkedHashMap<String, TestViewLoop>();
	
	private TestViewLoop currentCase = null;
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wuba.result.XMLParser#serialize(org.kxml2.io.KXmlSerializer)
	 */
	@Override
	public void serialize(KXmlSerializer serializer) throws IOException {
		// TODO Auto-generated method stub
		serializer.startTag(Constant.NAMESPACE, TESTNETWORK_TAG);
		serializer.attribute(Constant.NAMESPACE, TYPE_ATTR, getType());
		Collection<TestViewLoop> collection = testCases.values();
		for (TestViewLoop testCase : collection) {
			testCase.serialize(serializer);
		}
		serializer.endTag(Constant.NAMESPACE, TESTNETWORK_TAG);

	}
	public TestViewLoop getTestCaseByName(String name) {
		currentCase = testCases.get(name);
		if (currentCase == null) {
			currentCase = new TestViewLoop();
			currentCase.setName(name);
			testCases.put(name, currentCase);
		}
		return currentCase;
	}
}
