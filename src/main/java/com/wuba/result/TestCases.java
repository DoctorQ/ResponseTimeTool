/**
 * 
 */
package com.wuba.result;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.kxml2.io.KXmlSerializer;


/**
 * @author hui.qian qianhui@58.com
 * @date 2015年7月20日 下午2:48:41
 */
public class TestCases implements XMLParser {
	private static final String XML_TAG = "TestCases";
	
	private List<TestCase> testCases = new LinkedList<TestCase>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wuba.result.XMLParser#serialize(org.kxml2.io.KXmlSerializer)
	 */
	@Override
	public void serialize(KXmlSerializer serializer) throws IOException {
		// TODO Auto-generated method stub
		serializer.startTag(TestResult.NAMESPACE, XML_TAG);
		for(TestCase testCase : testCases) {
			testCase.serialize(serializer);
			
		}
		
		serializer.endTag(TestResult.NAMESPACE, XML_TAG);
	}

}
