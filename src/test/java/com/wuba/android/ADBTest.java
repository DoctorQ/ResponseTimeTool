package com.wuba.android;

import org.testng.Assert;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import com.android.ddmlib.IDevice;
import com.wuba.device.ADB;

public class ADBTest {
	
	ADB adb;
	
	@BeforeGroups(groups = "iostest")
	public void setUp() {
		adb = new ADB();
	}
	
	@Test(groups = { "iostest" })
	public void testADB1() {
		if (adb.init()){
			IDevice[] dList = adb.getDevices();
			System.out.println(dList.length);
			Assert.assertTrue(dList.length >= 0);
		}
	}
	
	@Test(groups = { "iostest" })
	public void testADB2() {
		System.out.println(adb.getDevices().length);
	}
}
