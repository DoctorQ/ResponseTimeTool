package com.wuba.logparser;

import java.io.File;

import com.wuba.model.RTResult;


public interface LogParser {
	
	// 解析系统log,得到响应时间数据
	public RTResult parserLog(File logDir);

	public abstract String paserLongToStringForTime(long time);

	public abstract long parserStringToLongForTime(String time);

}
