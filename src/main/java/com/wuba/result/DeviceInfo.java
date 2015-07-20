/**
 * 
 */
package com.wuba.result;

import java.io.IOException;

import org.kxml2.io.KXmlSerializer;

/**
 * @author hui.qian qianhui@58.com
 * @date 2015年7月20日 下午2:48:18
 */
public class DeviceInfo implements XMLParser {
	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	private static final String XML_TAG = "DeviceInfo";
	private static final String DEVICENAME_ATTR = "devicename";
	private static final String NETWORK_ATTR = "network";

	private String deviceName = "";
	private String network = "";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wuba.result.XMLParser#serialize(org.kxml2.io.KXmlSerializer)
	 */
	@Override
	public void serialize(KXmlSerializer serializer) throws IOException {
		// TODO Auto-generated method stub
		serializer.startTag(TestResult.NAMESPACE, XML_TAG);
		serializer.attribute(TestResult.NAMESPACE, DEVICENAME_ATTR,
				getDeviceName());
		serializer.attribute(TestResult.NAMESPACE, NETWORK_ATTR, getNetwork());
		serializer.endTag(TestResult.NAMESPACE, XML_TAG);
	}

}
