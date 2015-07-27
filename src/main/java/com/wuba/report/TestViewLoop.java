/**
 * 
 */
package com.wuba.report;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.kxml2.io.KXmlSerializer;

import com.wuba.logparser.LogParser;
import com.wuba.model.RTResult;
import com.wuba.result.TestCase;
import com.wuba.result.TestCaseLoop;
import com.wuba.result.XMLParser;
import com.wuba.utils.Constant;

/**
 * @author hui.qian qianhui@58.com
 * @date 2015年7月20日 下午2:49:05
 */
public class TestViewLoop implements XMLParser {

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private static final String XML_TAG = "TestViewLoop";
	private static final String NAME_ATTR = "name";
	// private static final String STARTTIME_ATTR = "starttime";
	// private static final String ENDTIME_ATTR = "endtime";
	private static final String ACONNECT_ATTR = "aconnecttime";
	private static final String AREAD_ATTR = "areadtime";
	private static final String APARSER_ATTR = "aparsertime";
	private static final String VIEWTYPE_ATTR = "viewtype";
	private static final String DATATYPE_ATTR = "datatype";

	private String name = "";
	// private String startTime = "";
	// private String endTime = "";

	private String dataType;
	private String viewType;

	private List<TestView> testViews = new LinkedList<TestView>();

	/*
	 * 
	 * @see com.wuba.result.XMLParser#serialize(org.kxml2.io.KXmlSerializer)
	 */
	@Override
	public void serialize(KXmlSerializer serializer) throws IOException {
		serializer.startTag(Constant.NAMESPACE, XML_TAG);
		serializer.attribute(Constant.NAMESPACE, NAME_ATTR, getName());

		serializeDataFromItems(serializer);
		// serializer.attribute(Constant.NAMESPACE, STARTTIME_ATTR,
		// getEndTime());
		// serializer.attribute(Constant.NAMESPACE, ENDTIME_ATTR,
		// getStartTime());

		for (TestView item : testViews) {
			item.serialize(serializer);
		}
		serializer.endTag(Constant.NAMESPACE, XML_TAG);
	}

	public void addItem(TestView item) {
		if (item == null)
			return;
		testViews.add(item);
	}

	/**
	 * 从Item信息从提取case的信息，比如case名,页面类型,数据类型
	 */
	private void serializeDataFromItems(KXmlSerializer serializer)
			throws IOException {
		if (testViews.size() <= 0)
			return;
		TestView item = testViews.get(0);
		RTResult result = item.getRtResult();
		dataType = result.getDataType();
		viewType = result.getViewType();
		if (viewType != null)
			serializer.attribute(Constant.NAMESPACE, VIEWTYPE_ATTR, viewType);
		if (dataType != null)
			serializer.attribute(Constant.NAMESPACE, DATATYPE_ATTR, dataType);

		int size = testViews.size();
		long totalConnectCost = 0;
		long totalReadCost = 0;
		long totalParserCost = 0;
		for (TestView indexItem : testViews) {
			if (Constant.NATIVE.equals(viewType)) {
				totalConnectCost += indexItem.getRtResult().getConnectCost();
				if (Constant.JSON.equals(dataType)) {
					totalReadCost += indexItem.getRtResult().getReadCost();
				}
			}
			totalParserCost += indexItem.getRtResult().getParserCost();
		}
		if (Constant.NATIVE.equals(viewType)) {

			serializer.attribute(Constant.NAMESPACE, ACONNECT_ATTR,
					totalConnectCost / size + "");
			if (Constant.JSON.equals(dataType)) {
				serializer.attribute(Constant.NAMESPACE, AREAD_ATTR,
						totalReadCost / size + "");
			}
		}
		serializer.attribute(Constant.NAMESPACE, APARSER_ATTR, totalParserCost
				/ size + "");

	}

	public void parserTestViewLoopByTestCaseLoop(TestCaseLoop caseLoop,
			LogParser logParser) {
		if (caseLoop == null)
			return;
		// 期望统计的次数
		int loop = caseLoop.getLoop();
		// 成功的次数
		int pass = caseLoop.getPass();

		int size = pass >= loop ? loop : pass;

		setName(caseLoop.getName());

		Iterator<Entry<Integer, TestCase>> iterator = caseLoop.getTestCases()
				.entrySet().iterator();
		while (iterator.hasNext()) {
			if (testViews.size() > size)
				break;

			Entry<Integer, TestCase> entry = iterator.next();
			TestCase testCase = entry.getValue();

			if (!testCase.isPass())
				continue;

			TestView testView = new TestView();
			testView.parserTestViewFromTestCase(testCase, logParser);
			testViews.add(testView);
		}
	}

}
