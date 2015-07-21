/**
 * 
 */
package com.wuba.result;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.kxml2.io.KXmlSerializer;

import com.wuba.model.RTResult;
import com.wuba.utils.Constant;

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
	private static final String VIEWTYPE_ATTR = "viewtype";
	private static final String DATATYPE_ATTR = "datatype";

	private String name = "";
	private String startTime = "";
	private String endTime = "";
	private String averageResponseTime = "";

	private String dataType;
	private String viewType;

	private List<Item> items = new LinkedList<Item>();

	/*
	 * 
	 * @see com.wuba.result.XMLParser#serialize(org.kxml2.io.KXmlSerializer)
	 */
	@Override
	public void serialize(KXmlSerializer serializer) throws IOException {
		serializer.startTag(Constant.NAMESPACE, XML_TAG);
		serializer.attribute(Constant.NAMESPACE, NAME_ATTR, getName());

		serializeDataFromItems(serializer);
		serializer.attribute(Constant.NAMESPACE, STARTTIME_ATTR, getEndTime());
		serializer.attribute(Constant.NAMESPACE, ENDTIME_ATTR, getStartTime());
		serializer.attribute(Constant.NAMESPACE, AVERAGERESPONSETIME_ATTR,
				getAverageResponseTime());
		for (Item item : items) {
			item.serialize(serializer);
		}
		serializer.endTag(Constant.NAMESPACE, XML_TAG);
	}

	public void addItem(Item item) {
		if (item == null)
			return;
		items.add(item);
	}

	/**
	 * 从Item信息从提取case的信息，比如case名,页面类型,数据类型
	 */
	private void serializeDataFromItems(KXmlSerializer serializer)
			throws IOException {
		if (items.size() <= 0)
			return;
		Item item = items.get(0);
		RTResult result = item.getRtResult();
		dataType = result.getDataType();
		viewType = result.getViewType();
		serializer.attribute(Constant.NAMESPACE, VIEWTYPE_ATTR, viewType);
		serializer.attribute(Constant.NAMESPACE, DATATYPE_ATTR, dataType);

	}

}
