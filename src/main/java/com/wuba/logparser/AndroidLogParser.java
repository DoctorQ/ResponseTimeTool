package com.wuba.logparser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;

import com.wuba.result.RTResult;
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
	private static final String NATIVE_BEGIN_REGEX = "(http:.*)\\|([0-9]+)\\|begin\\*{6}\\|([0-9]+)$";

	private static final String CONNECTED_REGEX = "\\|%s\\|connect[\\s]+host[\\s]+is[\\s]+over\\|([0-9]+)$";
	private static final String READ_REGEX = "\\|%s\\|read[\\s]+inputstream[\\s]+is[\\s]+over\\|([0-9]+)$";
	private static final String PARSER_JSON_REGEX = "\\|%s\\|parser[\\s]+json[\\s]+is[\\s]+over\\|([0-9]+)$";
	private static final String PARSER_XML_REGEX = "\\|%s\\|parser[\\s]+xml[\\s]+is[\\s]+over\\|([0-9]+)$";

	
	

//	Matcher connectMatcher = null;
//	Matcher readMatcher = null;
//	Matcher parserJsonMatcher = null;
//	Matcher parserXMLMatcher = null;
//
//	Matcher nativeBeginMatcher = null;
//	Matcher webViewBeginMatcher = null;

	public AndroidLogParser() {

	}

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
					return setRTResult();
				} else if (Constant.WEBVIEW.equals(viewType)) {
					parserWebView(line);
					if (mWebViewEnd == null)
						continue;
					return setRTResult();
				} else {
					Matcher nativeBeginMatcher = getMatcher(NATIVE_BEGIN_REGEX, line);
					Matcher webViewBeginMatcher = getMatcher(WEBVIEW_BEGIN_REGEX,
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

	
	private void parserNative(String line) {

		Matcher connectMatcher = getMatcher(String.format(CONNECTED_REGEX, mId), line);
		Matcher readMatcher = getMatcher(String.format(READ_REGEX, mId), line);
		Matcher parserJsonMatcher = getMatcher(String.format(PARSER_JSON_REGEX, mId),
				line);
		Matcher parserXMLMatcher = getMatcher(String.format(PARSER_XML_REGEX, mId),
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

}
