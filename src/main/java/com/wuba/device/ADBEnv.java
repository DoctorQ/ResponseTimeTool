package com.wuba.device;


import java.io.File;

import com.android.ddmlib.AndroidDebugBridge;
import com.android.ddmlib.IDevice;

/**
 * AndroidDebugBridge 本地环境变量获取
 * 1. initialize 使用ddmlib确认系统当前的ADB环境是否可用
 * 2. getDevices 获取所有的ADB识别的设备
 * @author a58
 *
 */

/**
 * @author a58
 *
 */
public class ADBEnv {
	
	private AndroidDebugBridge mAndroidDebugBridge;

	public boolean initialize(String[] args) {
		
		boolean success = true;
        // Init the lib  
        // [try to] Ensure ADB is running  
		String adbLocation = System
				.getProperty("com.android.screenshot.bindir");
		System.out.println("Get the adb path for system directly, result : " + adbLocation);

		if (adbLocation == null) {
			if ((args != null) && (args.length > 0)) {
				adbLocation = args[0];
			} else {
				// Get user defined envionment variables
				adbLocation = System.getenv("ANDROID_HOME");
				System.out.println("ANDROID_HOME path:"+adbLocation);
			}
			// Here, adbLocation may be android sdk directory
			if (adbLocation != null) {
				adbLocation += File.separator + "platform-tools";
			}
		}

		// For debugging (follwing line is a example) 调试用，获取的ADB路径的格式
		//		adbLocation = "C:\\ ... \\android-sdk-windows\\platform-tools"; // Windows
		//		adbLocation = "/ ... /adt-bundle-mac-x86_64/sdk/platform-tools"; // MacOS X
		//		adbLocation = "/Applications/sdk/platform-tools"; // MacOS X 
		
		if (success) {
			if ((adbLocation != null) && (adbLocation.length() != 0)) {
				adbLocation += File.separator + "adb";
			} else {
				adbLocation = "adb";
			}
			System.out.println("adb path is " + adbLocation);
			AndroidDebugBridge.init(false);
			mAndroidDebugBridge = AndroidDebugBridge.createBridge(adbLocation,true);
			if (mAndroidDebugBridge == null) {
				success = false;
			}
		}

		
        // we can't just ask for the device list right away, as the internal  
        // thread getting  
        // them from ADB may not be done getting the first list.  
        // Since we don't really want getDevices() to be blocking, we wait 10s 
        // here manually.  
		
		if (success) {
			int count = 0;
			while (mAndroidDebugBridge.hasInitialDeviceList() == false) {
				try {
					Thread.sleep(100);
					count++;
				} catch (InterruptedException e) {
				}
				if (count > 100) {
					success = false;
					break;
				}
			}
		}

		if (!success) {
			terminate();
		}

		return success;
	}

	// terminate connected device
	
	public void terminate() {
//		AndroidDebugBridge.disconnectBridge();
//		AndroidDebugBridge.terminate();
	}


	//get all connected devices as object list
	
	public IDevice[] getDevices() {
		IDevice[] devices = null;
		if (mAndroidDebugBridge != null) {
			devices = mAndroidDebugBridge.getDevices();
		}
		System.out.println("Got devices:"+ devices);
		return devices;
	}
	
	
	//for test my func
	public static void main (String [] args) {
		ADBEnv adbEnv = new ADBEnv();
		adbEnv.initialize(args);
		adbEnv.getDevices();
	}
}
