package com.wuba.ios;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.spi.IIORegistry;

import org.testng.annotations.Test;

import com.wuba.device.DeviceManager;
import com.wuba.device.IOSDevice;

public class IOSDeviceInfoTest {
	
	@Test(groups = { "iostest" })
	public void testIOSDeviceInfo() {
		// TODO Auto-generated constructor stub

		// ImageIO

		IOSDevice[] ids = DeviceManager.getIOSDevices("com.wuba.TestApp");
		IOSDevice device = ids[0];
		System.out.println(device.getAppId());
		System.out.println(device.getDeviceId());
		System.out.println(device.getDeviceName());
		System.out.println(device.getOsVersion());
		device.connectRecordServer();
		while (true) {
			BufferedImage img = device.takeScreenShot();
			if (img != null){
				System.out.println("not null");
			}
		}

	}
}
