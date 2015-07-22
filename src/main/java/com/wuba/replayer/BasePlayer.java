package com.wuba.replayer;

import java.io.File;

import com.wuba.device.Device;
import com.wuba.utils.Constant;
import com.wuba.utils.Helper;

public class BasePlayer {

	private String platform;
	private String taskStr;
	public String taskName;
	
	public BasePlayer(Device device, String taskName) {
		// TODO Auto-generated constructor stub
		this.platform = device.getPlatform();
		this.taskStr = taskName;
		createTaskDir();
	}
	
	private void createTaskDir(){
		String timeStr = Helper.getCurrFormatTime("yyyyMMddhhmmss");
		taskName = taskStr + timeStr;
		if (platform.equals(Constant.ANDROID_PLATFORM)){
			Helper.createDir(Constant.RESULT_ANDROID_ROOT + File.separator + taskName );
		}else if (platform.equals(Constant.IOS_PLATFORM)){
			Helper.createDir(Constant.RESULT_IOS_ROOT + File.separator + taskName );
		}
	}
}
