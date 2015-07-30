package com.wuba.logparser;

import java.io.File;

import com.wuba.result.RTResult;
/**
 * 
 * @author hui.qian qianhui@58.com  
 * @date 2015年7月15日 下午5:49:02
 */
public interface LogParser {
	

	// 解析系统log,得到响应时间数据
	public RTResult parserLog(File logDir);

	public abstract String paserLongToStringForTime(long time);

	public abstract long parserStringToLongForTime(String time);

}
