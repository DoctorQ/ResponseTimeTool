/**
 * 
 */
package com.wuba.report;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.kxml2.io.KXmlSerializer;

import com.wuba.result.XMLParser;
import com.wuba.utils.Constant;

/**
 * @author hui.qian qianhui@58.com
 * @date 2015年7月20日 下午2:48:41
 */
public class TestDevice implements XMLParser {
	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	private static final String XML_TAG = "TestDevice";
	private static final String DEVICE_ATTR = "device";
	private static final String SN_ATTR = "sn";
	private static final String PLATFORM_ATTR = "platform";

	private TestNetWork netWork = new TestNetWork();

	private Map<String, TestNetWork> netTypes = new LinkedHashMap<String, TestNetWork>();

	private String device = "";
	private String sn = "";
	private String platform = "";

	/*
	 * @see com.wuba.result.XMLParser#serialize(org.kxml2.io.KXmlSerializer)
	 */
	@Override
	public void serialize(KXmlSerializer serializer) throws IOException {
		// TODO Auto-generated method stub
		serializer.startTag(Constant.NAMESPACE, XML_TAG);
		serializer.attribute(Constant.NAMESPACE, SN_ATTR, getSn());
		serializer.attribute(Constant.NAMESPACE, DEVICE_ATTR, getDevice());
		serializer.attribute(Constant.NAMESPACE, PLATFORM_ATTR, getPlatform());
		netWork.serialize(serializer);

		serializer.endTag(Constant.NAMESPACE, XML_TAG);
	}

	public TestViewLoop getTestCaseByName(String name) {
		return netWork.getTestCaseByName(name);
	}

	public TestNetWork getTestNetWork(String type) {
		TestNetWork netWork = netTypes.get(type);

		if (netWork == null) {
			netWork = new TestNetWork();
			netWork.setType(type);
			netTypes.put(type, netWork);
		}
		
		return netWork;

	}

}
