package com.wuba.logparser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;

import com.wuba.result.RTResult;
import com.wuba.utils.Constant;

/**
 * 
 * @author hui.qian qianhui@58.com
 * @date 2015年7月15日 下午5:49:08
 */
public class IOSLogParser extends AbstractLogParser {
	private static final String NATIVE_BEGIN_REGEX = "(http:.*)\\|([0-9]+)\\|begin\\|([0-9]+)$";
	private static final String NATIVE_CONNECT_REGEX = "\\|%s\\|connect\\|([0-9]+)$";
	private static final String NATIVE_READ_REGEX = "\\|%s\\|read\\|([0-9]+)$";
	private static final String NATIVE_PARSER_REGEX = "\\|%s\\|parser\\|([0-9]+)$";

	public RTResult parserLog(File logFile) {
		if (!logFile.exists()) {
			return null;
		}

		InputStreamReader is = null;
		BufferedReader br = null;

		try {
			is = new InputStreamReader(new FileInputStream(logFile), "utf-8");
			br = new BufferedReader(is);
			String line = null;
			while ((line = br.readLine()) != null) {
				if (Constant.NATIVE.equals(viewType)) {
					parserNative(line);
					if (mParserJson == null)
						continue;
					return setRTResult();
				} else if (Constant.WEBVIEW.equals(viewType)) {
					parserWebView(line);
					if (mWebViewEnd == null)
						continue;
					return setRTResult();
				} else {
					Matcher nativeBeginMatcher = getMatcher(NATIVE_BEGIN_REGEX,
							line);
					Matcher webViewBeginMatcher = getMatcher(
							WEBVIEW_BEGIN_REGEX, line);

					if (nativeBeginMatcher.find()) {
						String url = nativeBeginMatcher.group(1);
						// 如果处于黑名单的话,不做处理
						if (isBlack(url))
							continue;
						parserBeginMatcher(nativeBeginMatcher);
						viewType = Constant.NATIVE;
						LOG.debug("begin time : " + mBegin);
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
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(is, br);
			tearDown();
		}

		return null;
	}

	/**
	 * 解析native的日志
	 * 
	 * @param line
	 */
	private void parserNative(String line) {

		Matcher connectMatcher = getMatcher(
				String.format(NATIVE_CONNECT_REGEX, mId), line);
		Matcher readMatcher = getMatcher(String.format(NATIVE_READ_REGEX, mId),
				line);
		Matcher parserJsonMatcher = getMatcher(
				String.format(NATIVE_PARSER_REGEX, mId), line);

		if (connectMatcher.find()) {
			mConnect = connectMatcher.group(1);
			LOG.debug("connect time : " + mConnect);
		} else if (readMatcher.find()) {
			mRead = readMatcher.group(1);
			LOG.debug("read time :" + mRead);
		} else if (parserJsonMatcher.find()) {
			mParserJson = parserJsonMatcher.group(1);
			LOG.debug("parser time : " + mParserJson);

		}

	}

}
