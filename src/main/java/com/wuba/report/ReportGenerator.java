/**
 * 
 */
package com.wuba.report;

import java.io.File;
import java.util.List;


/**
 * @author hui.qian qianhui@58.com  
 * @date 2015年7月22日 下午5:20:13
 */
public interface ReportGenerator {
	/**
	 * 根据单一任务生成报告
	 * @param rootDir
	 * @param platform TODO
	 */
	public void generateReporter(File rootDir,String platform);
	/**
	 * 根据多任务生成组合报告
	 * @param list
	 * @param platform TODO
	 */
	public void generateReporter(List<File> list, String platform);

}
