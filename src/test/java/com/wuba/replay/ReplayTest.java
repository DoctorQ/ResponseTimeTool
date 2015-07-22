package com.wuba.replay;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import com.wuba.device.IOSDevice;

public class ReplayTest {
	
	private IOSDevice iosDevice;
	
	@BeforeGroups(groups = "iostest")
	public void setUp() {
		iosDevice = new IOSDevice("1d61a79a1bdc428a90b675fcdf6da9fab17e3f56", "com.wuba.TestApp");
	}
	
	@AfterGroups(groups = "iostest")
	public void tearDown() {
		iosDevice.disConnectRecordServer();
	}

	@Test(groups = { "iostest" })
	public void testReplay() {
		
	}
	
}
