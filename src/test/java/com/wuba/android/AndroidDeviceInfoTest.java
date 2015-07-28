package com.wuba.android;

import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import com.wuba.device.AndroidDevice;
import com.wuba.device.DeviceManager;

public class AndroidDeviceInfoTest {

	@BeforeGroups(groups = "iostest")
	public void setUp() {

	}
	
	@Test(groups = { "iostest" })
	public void testAndroidDeviceInfo() {
		AndroidDevice[] ads = DeviceManager.getAndroidDevices();
		AndroidDevice device = ads[0];
//		System.out.println(ad.length);
//		int[] xy = ad[0].getScreenSize();
//		System.out.println(xy[0] + " " + xy[1]);
//		ad[0].dispose();
		while (true){
			device.getScreenShot("~/img.png");
		}
	}

}
