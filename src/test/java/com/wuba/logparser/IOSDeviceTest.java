package com.wuba.logparser;

import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import com.wuba.device.IOSDevice;

public class IOSDeviceTest {
	
	private IOSDevice iosDevice;
	
	@BeforeGroups(groups = "iostest")
	public void setUp() {
		iosDevice = new IOSDevice("1d61a79a1bdc428a90b675fcdf6da9fab17e3f56", "com.wuba.TestApp");
	}

	@Test(groups = { "iostest" })
	public void testIosDevice() {
		// test ios record step
		if (iosDevice.connectRecordServer()) {
			iosDevice.sendActionCommand("target.tapWithOptions({ x: 33, y: 268 }, {tapCount: 1, touchCount: 1, duration: 0.5});");
			iosDevice.sendActionCommand("target.model();");
			iosDevice.sendActionCommand("verifyImage('sub.png');");
			iosDevice.sendActionCommand("target.tapWithOptions({ x: 33, y: 268 }, {tapCount: 1, touchCount: 1, duration: 0.5});");
		}
		iosDevice.disConnectRecordServer();
	}
	
}
