/**
 * 
 */
package com.wuba.utils;

import java.io.File;

/**
 * @author hui.qian qianhui@58.com
 * @date 2015年7月22日 下午5:52:03 存放各个目录获取方式的文件结构辅助类
 */
public class DirStructureUtil {
	private static final String ROOT = System.getProperty("user.dir");

	/**
	 * 获取android的结果目录根目录
	 * 
	 * @return File
	 */
	public static File getResultAndroid() {
		return new File(ROOT, "repo/result/android/");
	}

	/**
	 * 获取iOS的结果目录根目录
	 * 
	 * @return File
	 */
	public static File getResultIOS() {
		return new File(ROOT, "repo/result/ios/");
	}
	/**
	 * 获取android的报告根目录
	 * @return File
	 */
	public static File getReportAndroid() {
		return new File(ROOT, "repo/report/android/");

	}
	/**
	 * 获取iOS的报告根目录
	 * @return File
	 */
	public static File getReportIOS() {
		return new File(ROOT, "repo/report/ios/");

	}
	/**
	 * 获取报告根目录
	 * @return File
	 */
	public static File getReport() {
		return new File(ROOT, "repo/report/");

	}

}
