package com.wuba.logparser;

import java.io.File;

import com.wuba.model.RTResult;
/**
 * 
 * @author hui.qian qianhui@58.com  
 * @date 2015年7月15日 下午5:49:02
 */
public interface LogParser {
	//url的黑名单
	public static final String[] url_black = { "http://jump.zhineng.58.com",
			"http://jing.58.com", "page=2" };

	// 解析系统log,得到响应时间数据
	public RTResult parserLog(File logDir);

	public abstract String paserLongToStringForTime(long time);

	public abstract long parserStringToLongForTime(String time);

}
