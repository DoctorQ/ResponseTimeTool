package com.wuba.logparser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.wuba.model.RTResult;

/**
 * |1422421760525|begin******|1422421760525 |1422421760525|connect host is
 * over|1422421760800 json: |1422421760525|read inputstream is
 * over|1422421760809 |1422421760525|parser json is over|1422421760875
 * 
 * xml: |1422421772044|parser xml is over|1422421774443
 * 
 * @author hui.qian 响应时间分:1.建立连接时间(connect-begin) 2.读取数据时间(read-connect)
 *         3.解析数据时间(parser-read)
 *         其中需要注意的是android中解析xml和json不一样,xml格式的数据读取和解析是同时的,所以只有解析数据时间.
 *         其中[建立连接时间+读取数据时间]反应了服务器的性能,而[解析数据时间]反应了客户端的性能
 */
public class AndroidLogParser implements LogParser {

	private static final String BEGIN_PATTERN = "(http:.*)\\|([0-9]+)\\|begin\\*{6}\\|([0-9]+)$";
	private static final String CONNECTED_PATTERN = "\\|%s\\|connect[\\s]+host[\\s]+is[\\s]+over\\|([0-9]+)$";
	private static final String READ_PATTERN = "\\|%s\\|read[\\s]+inputstream[\\s]+is[\\s]+over\\|([0-9]+)$";
	private static final String PARSER_JSON_PATTERN = "\\|%s\\|parser[\\s]+json[\\s]+is[\\s]+over\\|([0-9]+)$";
	private static final String PARSER_XML_PATTERN = "\\|%s\\|parser[\\s]+xml[\\s]+is[\\s]+over\\|([0-9]+)$";

	public AndroidLogParser() {

	}

	@SuppressWarnings("resource")
	@Override
	public RTResult parserLog(File logDir) {
		if (!logDir.exists()) {
			throw new NullPointerException("LogDir is not exists");
		}
		FileReader fr = null;
		BufferedReader br = null;
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
		try {
			fr = new FileReader(logDir);
			br = new BufferedReader(fr);

			String line = null;
			while ((line = br.readLine()) != null) {
				Matcher beginMatcher = getMatcher(BEGIN_PATTERN, line);

				if (beginMatcher.find() && mBegin == null) {
					mUrl = beginMatcher.group(1);
					mId = beginMatcher.group(2);
					mBegin = beginMatcher.group(3);
					System.out.println("begin time : " + mBegin);
				}
				if (mId == null) {
					continue;
				}

				Matcher connectMatcher = getMatcher(
						String.format(CONNECTED_PATTERN, mId), line);
				Matcher readMatcher = getMatcher(
						String.format(READ_PATTERN, mId), line);
				Matcher parserJsonMatcher = getMatcher(
						String.format(PARSER_JSON_PATTERN, mId), line);
				Matcher parserXMLMatcher = getMatcher(
						String.format(PARSER_XML_PATTERN, mId), line);
				if (connectMatcher.find()) {
					mConnect = connectMatcher.group(1);
					System.out.println("connect time : " + mConnect);
				} else if (readMatcher.find()) {
					mRead = readMatcher.group(1);
					System.out.println("read time :" + mRead);
				} else if (parserJsonMatcher.find()) {
					mParserJson = parserJsonMatcher.group(1);
					System.out.println("parser time : " + mParserJson);
				} else if (parserXMLMatcher.find()) {
					mParserXML = parserXMLMatcher.group(1);
					System.out.println("parser time : " + mParserXML);
				}
				if (mParserJson != null || mParserXML != null) {
					RTResult result = new RTResult();
					result.setId(mId);
					result.setUrl(mUrl);
					result.setBeginTime(parserStringToLongForTime(mBegin));
					result.setConnectTime(parserStringToLongForTime(mConnect));
					if (mParserJson != null) {
						result.setReadTime(parserStringToLongForTime(mRead));
						result.setParserTime(parserStringToLongForTime(mParserJson));
					} else {
						result.setParserTime(parserStringToLongForTime(mParserXML));
					}
					System.out.println("结束解析");
					return result;
				}
				// begin的匹配器
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return null;
	}

	private Pattern getPattern(String patternStr) {

		return Pattern.compile(patternStr);
	}

	private Matcher getMatcher(String patternStr, String parserStr) {
		return getPattern(patternStr).matcher(parserStr);
	}

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
