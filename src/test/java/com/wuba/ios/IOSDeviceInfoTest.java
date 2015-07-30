package com.wuba.ios;

import org.testng.annotations.Test;

import com.wuba.device.DeviceManager;
import com.wuba.device.IOSDevice;

public class IOSDeviceInfoTest {
	
	@Test(groups = { "iostest" })
	public void testIOSDeviceInfo() {
		// TODO Auto-generated constructor stub
		IOSDevice[] ids = DeviceManager.getIOSDevices("com.wuba.TestApp");
		IOSDevice device = ids[0];
		while(true){
			device.takeScreenShot();
		}
		
	}

}
