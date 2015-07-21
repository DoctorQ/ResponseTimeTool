package com.wuba.logparser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.wuba.model.RTResult;
import com.wuba.utils.Constant;

/**
 * 
 * @author hui.qian qianhui@58.com
 * @date 2015年7月15日 下午5:49:43 * |1422421760525|begin******|1422421760525
 *       |1422421760525|connect host is over|1422421760800 json:
 *       |1422421760525|read inputstream is over|1422421760809
 *       |1422421760525|parser json is over|1422421760875
 * 
 *       xml: |1422421772044|parser xml is over|1422421774443
 * 
 *       响应时间分:1.建立连接时间(connect-begin) 2.读取数据时间(read-connect)
 *       3.解析数据时间(parser-read)
 *       其中需要注意的是android中解析xml和json不一样,xml格式的数据读取和解析是同时的,所以只有解析数据时间.
 *       其中[建立连接时间+读取数据时间]反应了服务器的性能,而[解析数据时间]反应了客户端的性能
 */
public class AndroidLogParser extends AbstractLogParser {
	private final static Logger LOG = Logger.getLogger(AndroidLogParser.class);

	private static final String NATIVE_BEGIN_PATTERN = "(http:.*)\\|([0-9]+)\\|begin\\*{6}\\|([0-9]+)$";

	private static final String WEBVIEW_BEGIN_PATTERN = "(http:.*)\\|([0-9]+)\\|webview begin\\*{6}\\|([0-9]+)$";

	private static final String CONNECTED_PATTERN = "\\|%s\\|connect[\\s]+host[\\s]+is[\\s]+over\\|([0-9]+)$";
	private static final String READ_PATTERN = "\\|%s\\|read[\\s]+inputstream[\\s]+is[\\s]+over\\|([0-9]+)$";
	private static final String PARSER_JSON_PATTERN = "\\|%s\\|parser[\\s]+json[\\s]+is[\\s]+over\\|([0-9]+)$";
	private static final String PARSER_XML_PATTERN = "\\|%s\\|parser[\\s]+xml[\\s]+is[\\s]+over\\|([0-9]+)$";

	// 建立连接时间
	String mBegin = null;
	// 连接成功时间
	String mConnect = null;
	// 读取Json数据时间
	String mRead = null;
	// 解析Json数据时间
	String mParserJson = null;
	// 解析XML数据时间
	String mParserXML = null;
	// id
	String mId = null;
	// url
	String mUrl = null;
	// 页面类型
	String viewType = null;

	Matcher connectMatcher = null;
	Matcher readMatcher = null;
	Matcher parserJsonMatcher = null;
	Matcher parserXMLMatcher = null;

	Matcher nativeBeginMatcher = null;
	Matcher webViewBeginMatcher = null;

	public AndroidLogParser() {

	}

	@SuppressWarnings("resource")
	@Override
	public RTResult parserLog(File logDir) {
		if (!logDir.exists()) {
			return null;
		}
		return parserFile(logDir);
	}

	/**
	 * 解析log文件，得到各个响应时间的数据
	 * 
	 * @param logFile
	 * @return RTResult 测试结果对象
	 */
	private RTResult parserFile(File logFile) {
		InputStreamReader is = null;
		BufferedReader br = null;

		try {
			is = new InputStreamReader(new FileInputStream(logFile), "utf-8");
			br = new BufferedReader(is);
			String line = null;
			while ((line = br.readLine()) != null) {
				if (Constant.NATIVE.equals(viewType)) {
					parserNative(line);
					if (mParserJson == null && mParserXML == null)
						continue;
					RTResult result = new RTResult();
					result.setId(mId);
					result.setUrl(mUrl);
					result.setViewType(viewType);
					result.setBeginTime(parserStringToLongForTime(mBegin));
					result.setConnectTime(parserStringToLongForTime(mConnect));
					if (mParserJson != null) {
						result.setDataType(Constant.JSON);
						result.setReadTime(parserStringToLongForTime(mRead));
						result.setParserTime(parserStringToLongForTime(mParserJson));
						// 设置响应时间的准确值
						result.setConnectCost(result.getConnectTime()
								- result.getBeginTime());
						result.setReadCost(result.getReadTime()
								- result.getConnectTime());
						result.setParserCost(result.getParserTime()
								- result.getReadTime());
					} else {
						result.setDataType(Constant.XML);
						result.setParserTime(parserStringToLongForTime(mParserXML));
						result.setConnectCost(result.getConnectTime()
								- result.getBeginTime());
						// xml因为没有读取时间，所以解析耗时应该是解析完成时间戳-连接完成时间戳
						result.setParserCost(result.getParserTime()
								- result.getConnectTime());

					}
					LOG.debug("结束解析");
					return result;
				} else if (Constant.WEBVIEW.equals(viewType)) {
					parserWebView(line);
				} else {
					nativeBeginMatcher = getMatcher(NATIVE_BEGIN_PATTERN, line);
					webViewBeginMatcher = getMatcher(WEBVIEW_BEGIN_PATTERN,
							line);
					if (nativeBeginMatcher.find()) {

						String url = nativeBeginMatcher.group(1);

						// 如果处于黑名单的话,不做处理
						if (isBlack(url))
							continue;
						parserBeginMatcher(nativeBeginMatcher);
						viewType = Constant.NATIVE;
						LOG.debug("begin time : " + mBegin);
						// 如果不处于黑名单,解析
					} else if (webViewBeginMatcher.find()) {
						String url = webViewBeginMatcher.group(1);
						// 如果处于黑名单的话,不做处理
						if (isBlack(url))
							continue;
						parserBeginMatcher(webViewBeginMatcher);
						viewType = Constant.WEBVIEW;
						LOG.debug("webview begin time : " + mBegin);
					}
				}

				// begin的匹配器
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			LOG.error(e.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOG.error(e.toString());
		} finally {
			close(is, br);
			tearDown();

		}

		return null;
	}

	private void parserBeginMatcher(Matcher beginMatcher) {
		mUrl = beginMatcher.group(1);
		mId = beginMatcher.group(2);
		mBegin = beginMatcher.group(3);
	}

	/**
	 * 
	 */
	private void parserWebView(String line) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 */
	private void parserNative(String line) {

		connectMatcher = getMatcher(String.format(CONNECTED_PATTERN, mId), line);
		readMatcher = getMatcher(String.format(READ_PATTERN, mId), line);
		parserJsonMatcher = getMatcher(String.format(PARSER_JSON_PATTERN, mId),
				line);
		parserXMLMatcher = getMatcher(String.format(PARSER_XML_PATTERN, mId),
				line);
		if (connectMatcher.find()) {
			mConnect = connectMatcher.group(1);
			LOG.debug("connect time : " + mConnect);
		} else if (readMatcher.find()) {
			mRead = readMatcher.group(1);
			LOG.debug("read time :" + mRead);
		} else if (parserJsonMatcher.find()) {
			mParserJson = parserJsonMatcher.group(1);
			LOG.debug("parser time : " + mParserJson);

		} else if (parserXMLMatcher.find()) {
			mParserXML = parserXMLMatcher.group(1);
			LOG.debug("parser time : " + mParserXML);
		}

	}

	private void close(InputStreamReader is, BufferedReader br) {
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				LOG.error(e.toString());
			}
		}

		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				LOG.error(e.toString());
			}
		}
	}

	private void tearDown() {
		// 建立连接时间
		mBegin = null;
		// 连接成功时间
		mConnect = null;
		// 读取Json数据时间
		mRead = null;
		// 解析Json数据时间
		mParserJson = null;
		// 解析XML数据时间
		mParserXML = null;
		// id
		mId = null;
		// url
		mUrl = null;
		// 页面类型
		viewType = null;
	}

	/**
	 * 判断url是否为黑名单中url
	 * 
	 * @param url
	 * @return boolean
	 */
	private boolean isBlack(String url) {
		int size = URL_BLACK.length;
		for (int i = 0; i < size; i++) {
			if (url.contains(URL_BLACK[i])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 根据正则表达式和需要进行匹配的字符串生成一个匹配器Matcher
	 * 
	 * @param patternStr
	 * @param parserStr
	 * @return Matcher
	 */
	private Matcher getMatcher(String patternStr, String parserStr) {
		return Pattern.compile(patternStr).matcher(parserStr);
	}

	/**
	 * 将字符串格式的时间转换为Long类型的
	 */
	@Override
	public long parserStringToLongForTime(String time) {
		if (time == null)
			return 0;
		return Long.parseLong(time);
	}

	@Override
	public String paserLongToStringForTime(long time) {
		return null;
	}

}
