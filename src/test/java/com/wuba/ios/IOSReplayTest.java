package com.wuba.ios;

import java.io.File;
import java.util.LinkedHashMap;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import com.wuba.device.IOSDevice;
import com.wuba.replayer.IOSPlayer;
import com.wuba.utils.Constant;

public class IOSReplayTest {
	
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
		IOSPlayer iosPlayer = new IOSPlayer(iosDevice,Constant.NETWORK_MODEL_2G);
		LinkedHashMap<File, Integer> hashMap = new LinkedHashMap<File, Integer>();
		hashMap.put(new File("/Users/vigoss/Project/iOS_UI_Recorder/ResponseTimeTool/repo/testcase/ios/test_case_demo1/test_case_demo1.script"), 2);
		hashMap.put(new File("/Users/vigoss/Project/iOS_UI_Recorder/ResponseTimeTool/repo/testcase/ios/test_case_demo2/test_case_demo2.script"), 3);
		iosPlayer.play(hashMap);
	}
	
}
