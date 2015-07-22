package com.wuba.gui;

import java.io.File;
import java.util.LinkedHashMap;

import com.wuba.device.IOSDevice;
import com.wuba.replayer.IOSPlayer;
import com.wuba.utils.Constant;



public class HomePage {

	public static void main(String[] args) {
		IOSDevice device = new IOSDevice("1d61a79a1bdc428a90b675fcdf6da9fab17e3f56","com.wuba.TestApp");
		IOSPlayer iosPlayer = new IOSPlayer(device,Constant.NETWORK_MODEL_2G);
		
		LinkedHashMap<File, Integer> hashMap = new LinkedHashMap<File, Integer>();
		hashMap.put(new File("/Users/vigoss/Project/iOS_UI_Recorder/ResponseTimeTool/testcase/ios/test_case_demo1/test_case_demo1.script"), 2);
		hashMap.put(new File("/Users/vigoss/Project/iOS_UI_Recorder/ResponseTimeTool/testcase/ios/test_case_demo2/test_case_demo2.script"), 3);
		
		iosPlayer.play(hashMap);
//		File theDir = new File("/Users/vigoss/Project/iOS_UI_Recorder/ResponseTimeTool/result/ios/task20150722122741/case/a");
//		theDir.mkdirs();
//		Helper.createDir("/Users/vigoss/Project/iOS_UI_Recorder/ResponseTimeTool/result/ios/task20150722122741/case/a/b");
		
//		SimpleDateFormat   sDateFormat   =   new   SimpleDateFormat("yyyyMMddhhmmss");   
//		String   date   =   sDateFormat.format(new   java.util.Date()); 
//		System.out.println(date);
//		File file = new File("/Users/vigoss/Project/iOS_UI_Recorder/ResponseTimeTool/testcase/ios/test_case_demo1/test_case_demo1.script");
//		System.out.println("Basename: " + file.getName());
//		System.out.println("Dirname: " + file.getParent());
//		System.out.println("Absolute: " + file.getAbsolutePath());
//		AfcClient client;
//		IDevice device;
//		LockdowndClient lockdowndClient;
//		device = new IDevice("1d61a79a1bdc428a90b675fcdf6da9fab17e3f56");
//		lockdowndClient = new LockdowndClient(device, null, true);
//        LockdowndServiceDescriptor service = lockdowndClient.startService(AfcClient.SERVICE_NAME);
//		
//		client = new AfcClient(device, service);
//		Map<String, String> devInfo = client.getDeviceInfo();
//		
////		device.
//		Iterator<String> it = devInfo.keySet().iterator();
//		while(it.hasNext()) {
//			String key = it.next();
//	    	String val = devInfo.get(key);
////	    	System.out.println("testCase:" + testCase.getName() + " iteration:" + iteration);
//	    	System.out.println(key+" : "+val);
////	    	casePlay(testCase, iteration);
//		}
		
		
//		String udid = IDevice.listUdids()[0];
//
//		IDevice device = new IDevice(udid);
//		LockdowndClient client = new LockdowndClient(device, null, true);
//
//		NSObject node;
//		try {
//			node = client.getValue(null, null);
//			NSDictionary dict = (NSDictionary) node;
//			System.out.println(dict.objectForKey("ProductVersion"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		if (client != null) {
//			System.out.println("dispose client");
//            client.dispose();
//            client = null;
//        }
//        if (device != null) {
//        	System.out.println("dispose device");
//            device.dispose();
//            device = null;
//        }
		
        
	}

}
