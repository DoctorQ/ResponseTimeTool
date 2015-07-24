/**
 * 
 */
package com.wuba.report;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.kxml2.io.KXmlSerializer;

import com.wuba.logparser.LogParser;
import com.wuba.result.TestCaseLoop;
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

	private String type = "";

	private Map<String, TestViewLoop> testViewLoops = new LinkedHashMap<String, TestViewLoop>();

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
		Collection<TestViewLoop> collection = testViewLoops.values();
		for (TestViewLoop testCase : collection) {
			testCase.serialize(serializer);
		}
		serializer.endTag(Constant.NAMESPACE, TESTNETWORK_TAG);

	}

	public TestViewLoop getTestViewLoopByName(String name) {
		TestViewLoop testViewLoop = testViewLoops.get(name);
		if (testViewLoop == null) {
			testViewLoop = new TestViewLoop();
			testViewLoop.setName(name);
			testViewLoops.put(name, testViewLoop);
		}
		return testViewLoop;
	}

	public void setTestViewLoops(Map<String, TestCaseLoop> map,LogParser logParser) {
		Iterator<Entry<String, TestCaseLoop>> iterator = map.entrySet()
				.iterator();
		TestViewLoop vieweLoop = null;
		while (iterator.hasNext()) {
			Entry<String, TestCaseLoop> entry = iterator.next();
			String key = entry.getKey();
			TestCaseLoop caseLoop = entry.getValue();
			vieweLoop = new TestViewLoop();
			vieweLoop.parserTestViewLoopByTestCaseLoop(caseLoop, logParser);
			testViewLoops.put(key, vieweLoop);
		}
	}
}
