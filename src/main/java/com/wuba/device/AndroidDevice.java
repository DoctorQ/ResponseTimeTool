package com.wuba.device;

import com.wuba.utils.Constant;
import com.wuba.utils.Helper;

/**
 * Android设备信息，控制
 * 
 * @author vigoss
 *
 */




public class AndroidDevice implements Device {
	
	private String platform;	//
	private String deviceId;
	private String deviceName;
	private String osVersion;
	private String appId;
	private int[] screenSize;
	private boolean adbConnected = false;
	
	/**
	 * 初始化android本地存放目录
	 */
	public AndroidDevice() {
		// TODO Auto-generated constructor stub
		this.deviceId = deviceId;
		this.appId = appId;
		// 清空，并再次创建temp目录
		Helper.deleteDirectory(Constant.ANDROID_TEMP_DIR);
		Helper.createDir(Constant.ANDROID_TEMP_DIR);
	}

	@Override
	public String getPlatform() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDeviceId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDeviceName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getOsVersion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAppId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getScreenSize() {
		// TODO Auto-generated method stub
		return null;
	}

	public void touchDown(int x, int y) {

	}

	public void touchUp(int x, int y) {

	}

	public void touchMove(int x, int y) {
		

	}

	@Override
	public void takeScreenShot() {
		// TODO Auto-generated method stub

	}
	
	
}
