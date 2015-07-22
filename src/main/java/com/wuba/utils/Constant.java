package com.wuba.utils;

import java.io.File;

public class Constant {
	public static final String IOS_PLATFORM = "ios";
	public static final String ANDROID_PLATFORM = "android";
	public static final String DEFAULT_TASK_NAME = "task";
	
	//Android only
	//本地存放android文件root目录
	public static final String ANDROID_LOCAL_DIR = System.getProperty("user.dir") + File.separator + 
			"android_local";
	//本地存放android文件root下的temp目录
	public static final String ANDROID_TEMP_DIR = ANDROID_LOCAL_DIR + File.separator + 
			"temp";
	
	//iOS only
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
	
	//result
	public static final String RESULT_BASE_ROOT = System.getProperty("user.dir") + File.separator + 
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
	
	
}
