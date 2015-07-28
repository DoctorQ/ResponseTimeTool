package com.wuba.device;


/**
 * Android设备信息，控制
 * 
 * @author vigoss
 *
 */

public class AndroidDevice implements Device {

	/**
	 * 初始化android本地存放目录
	 */
	public AndroidDevice() {
		// TODO Auto-generated constructor stub

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
