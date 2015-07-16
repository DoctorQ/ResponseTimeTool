package com.wuba.utils;

import java.io.File;

public class Constant {
	public static final String IOS_PLATFORM = "iOS";
	public static final String ANDROID_PLATFORM = "android";
	
	//iOS only
	public static final String iOS_DEVICECONSOLE = System.getProperty("user.dir") + File.separator + 
			"ios_local" + File.separator + 
			"bin" + File.separator +
			"deviceconsole";
	

}
