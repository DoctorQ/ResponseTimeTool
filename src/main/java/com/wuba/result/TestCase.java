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
 * @date 2015年7月20日 下午2:49:05
 */
public class TestCase implements XMLParser {
	
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getAverageResponseTime() {
		return averageResponseTime;
	}

	public void setAverageResponseTime(String averageResponseTime) {
		this.averageResponseTime = averageResponseTime;
	}

	private static final String XML_TAG = "TestCase";
	private static final String NAME_ATTR = "name";
	private static final String STARTTIME_ATTR = "starttime";
	private static final String ENDTIME_ATTR = "endtime";
	private static final String AVERAGERESPONSETIME_ATTR = "averageresponsetime";

	private String name = "";
	private String startTime = "";
	private String endTime = "";
	private String averageResponseTime = "";

	private List<Item> items = new LinkedList<Item>();

	/*
	 * 
	 * @see com.wuba.result.XMLParser#serialize(org.kxml2.io.KXmlSerializer)
	 */
	@Override
	public void serialize(KXmlSerializer serializer) throws IOException {
		serializer.startTag(TestResult.NAMESPACE, XML_TAG);
		serializer.attribute(TestResult.NAMESPACE, NAME_ATTR, getName());
		serializer
				.attribute(TestResult.NAMESPACE, STARTTIME_ATTR, getEndTime());
		serializer
				.attribute(TestResult.NAMESPACE, ENDTIME_ATTR, getStartTime());
		serializer.attribute(TestResult.NAMESPACE, AVERAGERESPONSETIME_ATTR,
				getAverageResponseTime());

		for (Item item : items) {
			item.serialize(serializer);
		}
		serializer.endTag(TestResult.NAMESPACE, XML_TAG);
	}

	public void addItem(Item item) {
		if (item == null)
			return;
		items.add(item);
	}

}
