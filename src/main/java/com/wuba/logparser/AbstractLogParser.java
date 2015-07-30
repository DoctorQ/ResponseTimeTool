/**
 * 
 */
package com.wuba.logparser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.wuba.result.RTResult;
import com.wuba.utils.Constant;



/**
 * @author hui.qian qianhui@58.com  
 * @date 2015年7月17日 下午12:02:26
 */
public abstract class AbstractLogParser implements LogParser {

	 static final String[] URL_BLACK = { "http://jump.zhineng.58.com",
		"http://jing.58.com", "page=2" };
	protected static final Logger LOG = Logger.getLogger(AndroidLogParser.class);
	protected static final String WEBVIEW_END_REGEX = "\\|%s\\|webview[\\s]+end\\|([0-9]+)$";
	protected static final String WEBVIEW_BEGIN_REGEX = "(http:.*)\\|([0-9]+)\\|webview begin\\|([0-9]+)$";
	protected String mConnect = null;
	protected String mId = null;
	protected String mUrl = null;
	protected String viewType = null;
	protected String mBegin = null;
	protected String mRead = null;
	protected String mParserJson = null;
	protected String mWebViewEnd = null;
	protected String mParserXML = null;

	protected void close(InputStreamReader is, BufferedReader br) {
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				
			}
		}
	
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
		}
	}

	/**
	 * 判断url是否为黑名单中url
	 * 
	 * @param url
	 * @return boolean
	 */
	protected boolean isBlack(String url) {
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
	protected Matcher getMatcher(String patternStr, String parserStr) {
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

	protected void parserBeginMatcher(Matcher beginMatcher) {
		mUrl = beginMatcher.group(1);
		mId = beginMatcher.group(2);
		mBegin = beginMatcher.group(3);
	}

	protected RTResult setRTResult() {
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
			result.setReadCost(result.getReadTime() - result.getConnectTime());
			result.setParserCost(result.getParserTime() - result.getReadTime());
		} else if (mParserXML != null) {
			result.setDataType(Constant.XML);
			result.setParserTime(parserStringToLongForTime(mParserXML));
			result.setConnectCost(result.getConnectTime()
					- result.getBeginTime());
			// xml因为没有读取时间，所以解析耗时应该是解析完成时间戳-连接完成时间戳
			result.setParserCost(result.getParserTime()
					- result.getConnectTime());
	
		} else if (mWebViewEnd != null) {
	
			result.setParserTime(parserStringToLongForTime(mWebViewEnd));
			result.setParserCost(result.getParserTime() - result.getBeginTime());
		}
		LOG.debug("结束解析");
		return result;
	}

	protected void parserWebView(String line) {
		// TODO Auto-generated method stub
		Matcher webViewEndMatcher = getMatcher(String.format(WEBVIEW_END_REGEX, mId),
				line);
		if (webViewEndMatcher.find()) {
			mWebViewEnd = webViewEndMatcher.group(1);
			LOG.debug("WebView end time : " + mWebViewEnd);
		}
	
	}

	protected void tearDown() {
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
		mWebViewEnd = null;
	}

}
