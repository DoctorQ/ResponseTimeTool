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
 * 
 * @author hui.qian qianhui@58.com  
 * @date 2015年7月15日 下午5:49:43
 * * |1422421760525|begin******|1422421760525 |1422421760525|connect host is
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

	private static final String XML_DATATYPE = "xml";
	private static final String JSON_DATATYPE = "xml";

	public AndroidLogParser() {

	}

	@SuppressWarnings("resource")
	@Override
	public RTResult parserLog(File logDir) {
		if (!logDir.exists()) {
			throw new NullPointerException("LogDir is not exists");
		}
		return parserFile(logDir);
	}

	/**
	 * 解析log文件，得到各个响应时间的数据
	 * 
	 * @param logFile
	 * @return
	 */
	private RTResult parserFile(File logFile) {
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

		Matcher beginMatcher = null;
		Matcher connectMatcher = null;
		Matcher readMatcher = null;
		Matcher parserJsonMatcher = null;
		Matcher parserXMLMatcher = null;
		try {
			fr = new FileReader(logFile);
			br = new BufferedReader(fr);

			String line = null;
			while ((line = br.readLine()) != null) {
				beginMatcher = getMatcher(BEGIN_PATTERN, line);

				if (beginMatcher.find()) {
					String url = beginMatcher.group(1);
					if (isBlack(url))
						continue;

					mUrl = beginMatcher.group(1);
					mId = beginMatcher.group(2);
					mBegin = beginMatcher.group(3);
					System.out.println("begin time : " + mBegin);
					continue;
				}
				if (mId == null) {
					continue;
				}
				connectMatcher = getMatcher(
						String.format(CONNECTED_PATTERN, mId), line);
				readMatcher = getMatcher(String.format(READ_PATTERN, mId), line);
				parserJsonMatcher = getMatcher(
						String.format(PARSER_JSON_PATTERN, mId), line);
				parserXMLMatcher = getMatcher(
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
						result.setDataType(JSON_DATATYPE);
						result.setReadTime(parserStringToLongForTime(mRead));
						result.setParserTime(parserStringToLongForTime(mParserJson));
						//设置响应时间的准确值
						result.setConnectCost(result.getConnectTime()
								- result.getBeginTime());
						result.setReadCost(result.getReadTime()
								- result.getConnectTime());
						result.setParserCost(result.getParserTime()
								- result.getReadTime());
					} else {
						result.setDataType(XML_DATATYPE);
						result.setParserTime(parserStringToLongForTime(mParserXML));
						result.setConnectCost(result.getConnectTime()
								- result.getBeginTime());
						//xml因为没有读取时间，所以解析耗时应该是解析完成时间戳-连接完成时间戳
						result.setParserCost(result.getParserTime()
								- result.getConnectTime());
						
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

	/**
	 * 判断url是否为黑名单中url
	 * 
	 * @param url
	 * @return
	 */
	private boolean isBlack(String url) {
		int size = url_black.length;
		for (int i = 0; i < size; i++) {
			if (url.contains(url_black[i])) {
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
	 * @return
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
