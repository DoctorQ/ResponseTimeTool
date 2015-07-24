package com.wuba.ios;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import com.wuba.device.IOSDevice;

public class IOSRecordTest {
	
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
	public void testIosDevice() {
		// test ios record step
		if (iosDevice.connectRecordServer()) {
			//点击
			iosDevice.clickOnScreen(33, 268);
			//获取model
			String model = iosDevice.sendActionCommand("target.model();");
			//验图
			iosDevice.sendActionCommand("verifyImage('sub.png');");
			//点击
			iosDevice.clickOnScreen(33, 268);
			//滑动
			iosDevice.dragFromToScreen(196, 394, 260, 394);
//			iosDevice.sendActionCommand("target.tapWithOptions({ x: 33, y: 268 }, {tapCount: 1, touchCount: 1, duration: 0.5});");
//			iosDevice.sendActionCommand("target.model();");
//			iosDevice.sendActionCommand("verifyImage('sub.png');");
//			iosDevice.sendActionCommand("target.tapWithOptions({ x: 33, y: 268 }, {tapCount: 1, touchCount: 1, duration: 0.5});");
		}
		
	}
	
}
