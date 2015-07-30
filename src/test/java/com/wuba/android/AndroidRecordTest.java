package com.wuba.android;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import com.android.ddmlib.DdmPreferences;
import com.wuba.device.AndroidDevice;
import com.wuba.device.DeviceManager;

public class AndroidRecordTest {

	@Test(groups = { "iostest" })
	public void testAndroidRecord() throws InterruptedException {
		AndroidDevice[] ads = DeviceManager.getAndroidDevices();
		AndroidDevice device = ads[0];
		
		// touch action
		device.touchDown(556, 1672);
		TimeUnit.MILLISECONDS.sleep(97);
		device.touchUp(556, 1672);
		// action between default delay 2000 ms
		TimeUnit.MILLISECONDS.sleep(2000);
		
		// move action
		device.touchDown(948, 1048);
		TimeUnit.MILLISECONDS.sleep(89);
		device.touchMove(944, 1048);
		TimeUnit.MILLISECONDS.sleep(58);
		device.touchMove(892, 1048);
		TimeUnit.MILLISECONDS.sleep(144);
		device.touchMove(460, 1060);
		TimeUnit.MILLISECONDS.sleep(11);
		device.touchMove(416, 1060);
		TimeUnit.MILLISECONDS.sleep(60);
		device.touchMove(316, 1072);
		TimeUnit.MILLISECONDS.sleep(119);
		device.touchMove(164, 1076);
		TimeUnit.MILLISECONDS.sleep(141);
		device.touchUp(92, 1080);
		// action between default delay 2000 ms
		TimeUnit.MILLISECONDS.sleep(2000);
		
		// touch action
		device.touchDown(556, 1876);
		TimeUnit.MILLISECONDS.sleep(97);
		device.touchUp(556, 1876);
		
		// quit
		device.dispose();
	}

}
