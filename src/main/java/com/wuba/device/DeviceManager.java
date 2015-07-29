package com.wuba.device;


public class DeviceManager {

	public DeviceManager() {
		// TODO Auto-generated constructor stub
	}
	
	public static IOSDevice[] getIOSDevices(String appID){
		String[] udids = org.robovm.libimobiledevice.IDevice.listUdids();
		int count = udids.length;
		IOSDevice[] iosDevices = new IOSDevice[count];
		for (int i = 0; i < count; i++){
			iosDevices[i] = new IOSDevice(udids[i], appID);
		}
		return iosDevices;
	}
	
	public static AndroidDevice[] getAndroidDevices(){
		ADB adb = new ADB();
		adb.init();
		com.android.ddmlib.IDevice[] devices = adb.getDevices();
		int count = devices.length;
		AndroidDevice[] androidDevices = new AndroidDevice[count];
		for (int i = 0; i < count; i++){
			androidDevices[i] = new AndroidDevice(devices[i]);
		}
		return androidDevices;
	}

}
