package com.wuba.device;

import com.android.chimpchat.adb.AdbChimpDevice;
import com.android.ddmlib.IDevice;


/**
 * Android设备信息，控制
 * 
 * @author vigoss
 *
 */

public class AndroidDevice implements Device {

	private AdbChimpDevice mChimpDevice;		
	private IDevice mDevice;
	private ADB mAdb;
	
	public AndroidDevice(IDevice iDevice) {
		// TODO Auto-generated constructor stub
		mAdb = new ADB();
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
	public void insertLogStartFlag() {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertLogStopFlag() {
		// TODO Auto-generated method stub

	}

	@Override
	public void instertImgCheckPoint(String image) {
		// TODO Auto-generated method stub

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
