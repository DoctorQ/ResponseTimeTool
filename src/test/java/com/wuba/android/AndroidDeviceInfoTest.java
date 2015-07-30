package com.wuba.android;

import org.testng.Assert;
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
		device.setAppId("com.wuba");
		String id = device.getDeviceId();
		String name = device.getDeviceName();
		String version = device.getOsVersion();
		String appId = device.getAppId();
		int[] size = device.getScreenSize();
		System.out.println(id);
		Assert.assertNotNull(id);
		
		System.out.println(name);
		Assert.assertNotNull(name);
		
		System.out.println(version);
		Assert.assertNotNull(version);
		
		System.out.println(appId);
		Assert.assertNotNull(appId);
		
		System.out.println(size[0] + " " + size[1]);
		Assert.assertNotNull(size);
		
//		System.out.println(ad.length);
//		int[] xy = ad[0].getScreenSize();
//		System.out.println(xy[0] + " " + xy[1]);
//		ad[0].dispose();
//		while (true){
//			device.getScreenShot("~/img.png");
//		}
	}

}
