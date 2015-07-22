package com.wuba.replayer;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.apache.commons.io.FileUtils;

import com.wuba.device.Device;
import com.wuba.utils.Constant;
import com.wuba.utils.Helper;

/**
 * 测试设备的接口，抽象公共部分
 * 
 * @author vigoss
 * 
 */
public class IOSPlayer extends BasePlayer implements Player {

	private String deviceId;
	private String appId;

	public IOSPlayer(Device device) {
		// TODO Auto-generated constructor stub
		super(device, Constant.DEFAULT_TASK_NAME);
		this.deviceId = device.getDeviceId();
		this.appId = device.getAppId();
	}
	
//	public IOSPlayer(Device device, String taskName) {
//		// TODO Auto-generated constructor stub
//		super(device, taskName);
//		this.deviceId = device.getDeviceId();
//		this.appId = device.getAppId();
//	}

	/**
	 * 回放测试脚本
	 * 
	 * @param testScriptPath
	 *            : 脚本绝对路径，/the/script/path/name.script
	 */
	@Override
	public boolean play(LinkedHashMap<File, Integer> taskMap) {
		// TODO Auto-generated method stub
		Iterator<File> it = taskMap.keySet().iterator();
		while(it.hasNext()) {
			File testCase = (File)it.next();
	    	int iteration = taskMap.get(testCase);
//	    	System.out.println("testCase:" + testCase.getName() + " iteration:" + iteration);
//	    	System.out.println(super.taskName);
	    	casePlay(testCase, iteration);
		}
		return false;
	}

	private boolean casePlay(File testCase, int iteration) {
		int i = 0;
		String caseResultDir = Constant.RESULT_IOS_ROOT + File.separator + 
				taskName + File.separator +
				testCase.getName().split("\\.")[0];
		String caseName = testCase.getName();
//		String command = "sh " + Constant.iOS_REPLAY_SHELL + " " + deviceId + " " + appId + " " + testCase.getAbsolutePath();
		
		while(i < iteration){
			String resDir = caseResultDir + File.separator + i;
			String cmd = "sh " + Constant.iOS_REPLAY_SHELL + " " + deviceId + " " + appId + " " + resDir + File.separator + caseName;
			Helper.createDir(resDir);
			try {
				FileUtils.copyDirectory(new File(testCase.getParent()), new File(resDir));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String output = Helper.executeCommand(cmd);
			if (output.contains("Replay Test Success")) {
				System.out.println("OKOKOKOKOKOKOKOK!!!!!!!");
			}
			System.out.println(resDir);
			System.out.println(cmd);
//			System.out.println("1");
			i++;
		}
//		createCaseDir();
//		String command = "sh " + Constant.iOS_REPLAY_SHELL + " " + deviceId + " " + appId + " " + testCase.getAbsolutePath();
//		String output = Helper.executeCommand(command);
//		if (output.contains("Replay Test Success")) {
//			return true;
//		}
		return false;
	}
	
	private void createCaseDir(){
		
	}

	

}
