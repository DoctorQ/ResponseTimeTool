package com.wuba.device;

/**
 * 测试设备的接口，抽象公共部分
 * @author vigoss
 *
 */
public interface Device {
	
	String getPlatform();

	String getDeviceId();

	String getDeviceName();

	String getOSVersion();

	int[] getScreenSize();

	void clickOnScreen(int x, int y);

	void dragFromToScreen(int startX, int startY, int endX, int endY);

	void takeScreenShot();

}
