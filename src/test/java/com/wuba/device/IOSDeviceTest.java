package com.wuba.device;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import com.wuba.device.IOSDevice;

public class IOSDeviceTest {
	
	private IOSDevice iosDevice;
	boolean stopFlag = false;
	
	@BeforeGroups(groups = "iostest")
	public void setUp() {
		iosDevice = new IOSDevice("1d61a79a1bdc428a90b675fcdf6da9fab17e3f56", "com.wuba.TestApp");
	}
	
	@AfterGroups(groups = "iostest")
	public void tearDown() {
		stopFlag = true;
		iosDevice.disConnectRecordServer();
	}

	@Test(groups = { "iostest" })
	public void testIosDevice() {
//		// test get the last screen
//		
//		Thread thread = new Thread(new Runnable() {
//			@Override
//			public void run() {
//				while (!stopFlag) {
//					System.out.println("===========================");
//					System.out.println(iosDevice.getLastScreenShot());
//				}
//			}
//		});
//		thread.start();
		
		// test ios record step
		if (iosDevice.connectRecordServer()) {
			iosDevice.sendActionCommand("target.tapWithOptions({ x: 33, y: 268 }, {tapCount: 1, touchCount: 1, duration: 0.5});");
			iosDevice.sendActionCommand("target.model();");
			iosDevice.sendActionCommand("verifyImage('sub.png');");
			iosDevice.sendActionCommand("target.tapWithOptions({ x: 33, y: 268 }, {tapCount: 1, touchCount: 1, duration: 0.5});");
		}
		
		
	}
	
}
