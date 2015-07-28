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
		iosDevice = new IOSDevice("2f2fb64220ed34f645d33cd222280efcaa37dadf", "com.taofang.iphone");
	}
	
	@AfterGroups(groups = "iostest")
	public void tearDown() {
		iosDevice.disConnectRecordServer();
	}

	@Test(groups = { "iostest" })
	public void testReplay() {
		IOSPlayer iosPlayer = new IOSPlayer(iosDevice,Constant.NETWORK_MODEL_2G);
		LinkedHashMap<File, Integer> hashMap = new LinkedHashMap<File, Integer>();
//		hashMap.put(new File("/Users/vigoss/Project/iOS_UI_Recorder/ResponseTimeTool/repo/testcase/ios/test_native_zhengzu_list/test_native_zhengzu_list.script"), 1);
		hashMap.put(new File("/Users/vigoss/Project/iOS_UI_Recorder/ResponseTimeTool/repo/testcase/ios/test_web_xinfang_list/test_web_xinfang_list.script"), 1);
//		hashMap.put(new File("/Users/vigoss/Project/iOS_UI_Recorder/ResponseTimeTool/repo/testcase/ios/test_case_demo1/test_case_demo1.script"), 2);
//		hashMap.put(new File("/Users/vigoss/Project/iOS_UI_Recorder/ResponseTimeTool/repo/testcase/ios/test_case_demo2/test_case_demo2.script"), 3);
		iosPlayer.play(hashMap);
	}
	
}
