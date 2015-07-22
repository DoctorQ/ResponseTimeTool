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

	public IOSPlayer(Device device, String network) {
		// TODO Auto-generated constructor stub
		super(device, network);
		this.deviceId = device.getDeviceId();
		this.appId = device.getAppId();
	}

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
		while (it.hasNext()) {
			File testCase = (File) it.next();
			int iteration = taskMap.get(testCase);
			casePlay(testCase, iteration);
		}
		return false;
	}

	private boolean casePlay(File testCase, int iteration) {
		int i = 0;
		String caseResultDir = Constant.RESULT_IOS_ROOT + File.separator + taskName + File.separator + testCase.getName().split("\\.")[0];
		String caseName = testCase.getName();

		while (i < iteration) {
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
			} else if (output.contains("Replay Test Failed")) {
				System.out.println("FAFAFAFAFAFAFAFA!!!!!!!");
			}
			i++;
		}
		return false;
	}

	private void createCaseDir() {

	}

}
