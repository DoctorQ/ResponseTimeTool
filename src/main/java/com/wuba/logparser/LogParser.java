package com.wuba.logparser;

import java.io.File;


public interface LogParser {
	// 解析系统log,得到响应时间数据
	public String parserLog(File logDir);

}
