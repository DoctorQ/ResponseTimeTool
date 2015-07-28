package com.wuba.device;

import java.io.File;

import com.android.ddmlib.AndroidDebugBridge;
import com.android.ddmlib.IDevice;


public class ADB {
	
	private AndroidDebugBridge mAndroidDebugBridge;
	private String adbPath = null;
//	private boolean adbStatus = false;
	private String adbPlatformTools = "platform-tools";
	
	//获取系统ADB路径
	public String getADBPath(){
//		adbPath = System.getProperty("com.android.screenshot.bindir");
		if (adbPath==null){
			adbPath = System.getenv("ANDROID_HOME");
//			System.out.println("[ANDROID_HOME]:"+adbPath);
			if(adbPath!=null){
//				adbPath += File.pathSeparator + adbPlatformTools;
				adbPath += File.separator + adbPlatformTools;
//				System.out.println("[ANDROID_ADBPATH]:"+adbPath);
			}else {
				return null;
			}
		}
		adbPath += File.separator + "adb";
		System.out.println("[ADB_ABS_PATH]:"+adbPath);
		return adbPath;
	}

	//建立ADB连接桥
	public boolean initADB(String adbpathString) {
		boolean adbStatus = false; //ADB connect status flag
		
		System.out.println("got : "+ adbpathString);
		
		if(adbpathString==null){
			adbStatus = false;
			System.out.println("ADB path is disabled.");
		}else{
			AndroidDebugBridge.init(false);
			mAndroidDebugBridge = AndroidDebugBridge.createBridge(adbpathString,true);
			if (mAndroidDebugBridge == null) {
				System.out.println("AndroidDebugBridge connection failed.");
				adbStatus = false;
			}else {
				System.out.println("AndroidDebugBridge connection success.");
				adbStatus = true;
			}
			//延时处理adb获取设备信息
			if(adbStatus){
				int loopCount = 0;
				while (mAndroidDebugBridge.hasInitialDeviceList() == false) {
					try {
						Thread.sleep(100);
						loopCount++;
					} catch (InterruptedException e) {
					}
					if (loopCount > 100) {
						System.out.println("AndroidDebugBridge connection timeout.");
						adbStatus = false;
						break;
					}
					adbStatus = true;
				}
				System.out.println("AndroidDebugBridge device checked success.");
			}
			if (!adbStatus) {
				System.out.println("AndroidDebugBridge init is " + adbStatus);
				
			}
		
		}
		
		return adbStatus;
	}
	
	
	//获取连接的设备列表
	public  IDevice[] getDevices() {
		IDevice[] devicelist = null;
		if(mAndroidDebugBridge !=null){
			devicelist = mAndroidDebugBridge.getDevices();
			
			if (devicelist==null){
				System.out.println("There is no connected device");
			}else {
				for (IDevice device:devicelist){
					System.out.println( device + "\t" + device.getState());
				}
			}
			
			return devicelist;
			
		}else {
			
			System.out.println("AndroidDebugBridge is unavailable");
			return null;
		}
	}
		
}

