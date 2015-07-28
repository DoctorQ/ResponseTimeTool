package com.wuba.device;

import org.testng.Assert;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import com.android.ddmlib.IDevice;

public class DeviceManagerTest {

	@BeforeGroups(groups = "iostest")
	public void setUp() {

	}
	
	@Test(groups = { "iostest" })
	public void testDeviceManager() {
		AndroidDevice[] androidDevices = DeviceManager.getAndroidDevices();
		System.out.println("androidDevices.length:" + androidDevices.length);
		Assert.assertTrue(androidDevices.length >= 0);
		
		IOSDevice[] iosDevices = DeviceManager.getIOSDevices("com.taofang.iphone");
		System.out.println("iosDevices.length:" + iosDevices.length);
		Assert.assertTrue(iosDevices.length >= 0);
		for (IOSDevice iosDevice : iosDevices){
			System.out.println("iosDevices.getDeviceId:" + iosDevice.getDeviceId());
			System.out.println("iosDevices.getAppId:" + iosDevice.getAppId());
			System.out.println("iosDevices.getDeviceName:" + iosDevice.getDeviceName());
			System.out.println("iosDevices.getOsVersion:" + iosDevice.getOsVersion());
		}
	}
}
