package com.wuba.ios;

import org.testng.Assert;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import com.wuba.device.IOSDevice;

public class IOSRecordTest {
	
	private IOSDevice iosDevice;
	
	@BeforeGroups(groups = "iostest")
	public void setUp() {
		iosDevice = new IOSDevice("2f2fb64220ed34f645d33cd222280efcaa37dadf", "com.taofang.iphone");
	}
	
	@AfterGroups(groups = "iostest")
	public void tearDown() {
		iosDevice.disConnectRecordServer();
	}

	@Test(groups = { "iostest" })
	public void testIosDevice() {
		// test ios record step
		boolean connectedFlag = iosDevice.connectRecordServer();
		if (connectedFlag) {
			// 点击
			iosDevice.clickOnScreen(33, 268);
			// 获取model
			String model = iosDevice.sendActionCommand("target.model();");
			Assert.assertEquals(model.split("\\|")[1], "iPhone");
			// 验图
			iosDevice.instertImgCheckPoint("sub.png");
			// 插入timelog开始记录flag
			iosDevice.insertLogStartFlag();
			// 点击
			iosDevice.clickOnScreen(33, 268);
			// 滑动
			iosDevice.dragFromToScreen(196, 394, 260, 394);
			// 插入timelog结束记录flag
			iosDevice.insertLogStopFlag();
		}
		
	}
	
}
