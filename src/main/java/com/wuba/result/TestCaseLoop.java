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
 * @date 2015年7月23日 下午2:38:11
 */
public class TestCaseLoop implements XMLParser {
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getFail() {
		return fail;
	}

	public void setFail(String fail) {
		this.fail = fail;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getLoop() {
		return loop;
	}

	public void setLoop(String loop) {
		this.loop = loop;
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

	private static final String TESTCASELOOP_TAG = "TestCaseLoop";
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
	private String total = "";
	// case的pass数
	private String pass = "";
	// case的fail数
	private String fail = "";
	// case的路径
	private String path = "";
	// case的重复次数
	private String loop = "";
	// case执行的开始时间
	private String startTime = "";
	// case执行的结束时间
	private String endTime = "";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wuba.report.XMLParser#serialize(org.kxml2.io.KXmlSerializer)
	 */
	@Override
	public void serialize(KXmlSerializer serializer) throws IOException {
		// TODO Auto-generated method stub
		serializer.startTag(Constant.NAMESPACE, TESTCASELOOP_TAG);
		serializer.attribute(Constant.NAMESPACE, NAME_ATTR, getName());
		serializer.attribute(Constant.NAMESPACE, TOTAL_ATTR, getTotal());
		serializer.attribute(Constant.NAMESPACE, PASS_ATTR, getPass());
		serializer.attribute(Constant.NAMESPACE, FAIL_ATTR, getFail());
		serializer.attribute(Constant.NAMESPACE, CASE_PATH_ATTR, getPath());
		serializer.attribute(Constant.NAMESPACE, CASE_LOOP_ATTR, getLoop());
		serializer
				.attribute(Constant.NAMESPACE, STARTTIME_ATTR, getStartTime());
		serializer.attribute(Constant.NAMESPACE, ENDTIME_ATTR, getEndTime());

		serializer.endTag(Constant.NAMESPACE, TESTCASELOOP_TAG);

	}

}
