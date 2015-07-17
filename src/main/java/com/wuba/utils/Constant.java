package com.wuba.utils;

import java.io.File;

public class Constant {
	public static final String IOS_PLATFORM = "iOS";
	public static final String ANDROID_PLATFORM = "android";
	
	//iOS only
	public static final String iOS_LOCAL_DIR = System.getProperty("user.dir") + File.separator + 
			"ios_local";
	public static final String iOS_DEVICECONSOLE = iOS_LOCAL_DIR + File.separator + 
			"bin" + File.separator +
			"deviceconsole";
	public static final String iOS_TEMP_DIR = iOS_LOCAL_DIR + File.separator + 
			"temp";
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
	
	

}
