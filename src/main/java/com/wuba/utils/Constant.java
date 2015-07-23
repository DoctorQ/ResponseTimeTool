package com.wuba.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Constant {
	public static final String IOS_PLATFORM = "ios";
	public static final String ANDROID_PLATFORM = "android";
	public static final String DEFAULT_TASK_NAME = "task";
	public static final String NETWORK_MODEL_2G = "2G";
	public static final String NETWORK_MODEL_3G = "3G";
	
	//Android only
	//本地存放android文件root目录
	public static final String ANDROID_LOCAL_DIR = System.getProperty("user.dir") + File.separator + 
			"android_local";
	//本地存放android文件root下的temp目录
	public static final String ANDROID_TEMP_DIR = ANDROID_LOCAL_DIR + File.separator + 
			"temp";
	
	//iOS only
	public static final String iOS_INST_TRACE = "instrumentscli0.trace";
	public static final String iOS_LOCAL_DIR = System.getProperty("user.dir") + File.separator + 
			"ios_local";
	public static final String iOS_DEVICECONSOLE = iOS_LOCAL_DIR + File.separator + 
			"bin" + File.separator +
			"deviceconsole";
	public static final String iOS_TEMP_DIR = iOS_LOCAL_DIR + File.separator + 
			"temp";
	public static final String iOS_RUN1_DIR = iOS_TEMP_DIR + File.separator + "Run 1";
	public static final String iOS_RECORD_SHELL = iOS_LOCAL_DIR + File.separator + 
			"bin" + File.separator +
			"record.sh";
	public static final String iOS_REPLAY_SHELL = iOS_LOCAL_DIR + File.separator + 
			"bin" + File.separator +
			"replay.sh";
	public static final String iOS_CMD_FILE = iOS_TEMP_DIR + File.separator + "cmd.txt";
	public static final String iOS_RESP_FILE = iOS_TEMP_DIR + File.separator + "resp.txt";
	public static final String iOS_START_SERVER_CMD = "start record server;";
	public static final String iOS_CLOSE_SERVER_CMD = "close record server;";
	@SuppressWarnings("serial")
	public static Map<String, String> IPHONE_MAP = new HashMap<String,String>() {{    
	    put("iPhone1,1", "iPhone");
	    put("iPhone1,2", "iPhone3G");
	    put("iPhone2,1", "iPhone3GS");
	    put("iPhone3,1", "iPhone4GSM");
	    put("iPhone3,2", "iPhone4CDMA");
	    put("iPhone3,3", "iPhone4CDMA");
	    put("iPhone4,1", "iPhone4S");
	    put("iPhone5,1", "iPhone5");
	    put("iPhone5,2", "iPhone5");
	    put("iPhone5,3", "iPhone5C(GSM)");
	    put("iPhone5,4", "iPhone5C(CDMA)");
	    put("iPhone6,1", "iPhone5S(GSM)");
	    put("iPhone6,2", "iPhone5S(CDMA)");
	    put("iPhone7,1", "iPhone6Plus");
	    put("iPhone7,2", "iPhone6");
	    put("iPad1,1", "iPad");
	    put("iPad2,1", "iPad2(Wi-Fi)");
	    put("iPad2,2", "iPad2(GSM)");
	    put("iPad2,3", "iPad2(CDMA)");
	    put("iPad2,4", "iPad2(Wi-Fi,revised)");
	    put("iPad2,5", "iPadmini(Wi-Fi)");
	    put("iPad2,6", "iPadmini(A1454)");
	    put("iPad2,7", "iPadmini(A1455)");
	    put("iPad3,1", "iPad(3rdgen,Wi-Fi)");
	    put("iPad3,2", "iPad(3rd-gen,Wi-Fi+LTE-Verizon)");
	    put("iPad3,3", "iPad(3rd-gen,Wi-Fi+LTE-AT&T)");
	    put("iPad3,4", "iPad(4th-gen,Wi-Fi)");
	    put("iPad3,5", "iPad(4th-gen,A1459)");
	    put("iPad3,6", "iPad(4th-gen,A1460)");
	    put("iPad4,1", "iPad-Air(Wi-Fi)");
	    put("iPad4,2", "iPad-Air(Wi-Fi+LTE)");
	    put("iPad4,3", "iPad-Air(Rev)");
	    put("iPad4,4", "iPad-mini-2(Wi-Fi)");
	    put("iPad4,5", "iPad-mini-2(Wi-Fi+LTE)");
	    put("iPad4,6", "iPad-mini-2(Rev)");
	    put("iPad4,7", "iPad-mini-3(Wi-Fi)");
	    put("iPad4,8", "iPad-mini-3(A1600)");
	    put("iPad4,9", "iPad-mini-3(A1601)");
	    put("iPad5,3", "iPad-Air-2(Wi-Fi)");
	    put("iPad5,4", "iPad-Air-2(Wi-Fi+LTE)");
	    put("iPod1,1", "iPod-touch");
	    put("iPod2,1", "iPod-touch(2nd-gen)");
	    put("iPod3,1", "iPod-touch(3rd-gen)");
	    put("iPod4,1", "iPod-touch(4th-gen)");
	    put("iPod5,1", "iPod-touch(5th-gen)");
	}};
	
	//result
	public static final String RESULT_BASE_ROOT = System.getProperty("user.dir") + File.separator + 
			"repo" + File.separator +
			"result";
	public static final String RESULT_ANDROID_ROOT = RESULT_BASE_ROOT + File.separator +
			ANDROID_PLATFORM;
	public static final String RESULT_IOS_ROOT = RESULT_BASE_ROOT + File.separator +
			IOS_PLATFORM;
	
	public static final String NATIVE = "native";
	public static final String WEBVIEW = "webview";
	public static final String JSON = "json";
	public static final String XML = "xml";
	public static final String NAMESPACE = null;
	public static final String TESTRESULT_XML = "testResult.xml";
	
	
}
