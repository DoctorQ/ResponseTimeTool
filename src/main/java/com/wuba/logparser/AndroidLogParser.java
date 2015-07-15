package com.wuba.logparser;

import java.io.File;

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
	// 建立连接时间
	private String mBegin;
	// 连接成功时间
	private String mConnected;
	// 读取数据时间
	private String mRead;
	// 解析数据时间
	private String mParser;

	private static final String BEGIN_PATTERN = "\\|([0-9]+)\\|begin\\*{6}\\|([0-9]+)$";
	private static final String CONNECTED_PATTERN = "\\|([0-9]+)\\|connect[\\s]+host[\\s]+is[\\s]+over\\|([0-9]+)$";
	private static final String READ_PATTERN = "\\|([0-9]+)\\|read[\\s]+inputstream[\\s]+is[\\s]+over\\|([0-9]+)$";
	private static final String PARSER_PATTERN = "\\|([0-9]+)\\|parser[\\s]+json[\\s]+is[\\s]+over\\|([0-9]+)$";

	public AndroidLogParser() {

	}

	@Override
	public RTResult parserLog(File logDir) {
		
		
		return null;
	}

	@Override
	public long parserStringToLongForTime(String time) {

		return 0;
	}

	@Override
	public String paserLongToStringForTime(long time) {
		return null;
	}
	
	
	
}
