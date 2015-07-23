/**
 * 
 */
package com.wuba.result;

import java.io.IOException;

import org.kxml2.io.KXmlSerializer;

import com.wuba.report.XMLParser;
import com.wuba.utils.Constant;

/**
 * @author hui.qian qianhui@58.com
 * @date 2015年7月23日 下午2:37:28
 */
public class TestResult implements XMLParser {

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	private static final String TESTRESULT_TAG = "TestResult";
	private static final String DEVICE_ATTR = "device";
	private static final String NETWORK_ATTR = "network";
	private static final String SN_ATTR = "sn";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wuba.report.XMLParser#serialize(org.kxml2.io.KXmlSerializer)
	 */

	private String device = "";
	private String network = "";
	private String sn = "";

	@Override
	public void serialize(KXmlSerializer serializer) throws IOException {
		serializer.startTag(Constant.NAMESPACE, TESTRESULT_TAG);
		serializer.attribute(Constant.NAMESPACE, DEVICE_ATTR, getDevice());
		serializer.attribute(Constant.NAMESPACE, NETWORK_ATTR, getNetwork());
		serializer.attribute(Constant.NAMESPACE, SN_ATTR, getSn());

		serializer.endTag(Constant.NAMESPACE, TESTRESULT_TAG);
	}

}
