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
	 * 执行测试suite
	 * @param taskMap 测试suite
	 */
	@Override
	public void play(LinkedHashMap<File, Integer> taskMap) {
		// TODO Auto-generated method stub
		Iterator<File> it = taskMap.keySet().iterator();
		while (it.hasNext()) {
			File testCase = (File) it.next();
			int iteration = taskMap.get(testCase);
			casePlay(testCase, iteration);
		}
	}

	/**
	 * 迭代执行指定脚本达到目标轮数（即成功次数），迭代上限 ＝ 目标轮数 * 2
	 * @param testCase 测试脚本
	 * @param iteration 目标轮数
	 */
	private void casePlay(File testCase, int iteration) {
		int i = 0;
		int tempNum = 0;
		int targetNum = iteration;
		int maxNum = iteration * 2;
		String caseResultDir = Constant.RESULT_IOS_ROOT + File.separator + taskName + File.separator + testCase.getName().split("\\.")[0];
		String caseName = testCase.getName();

		while (i < maxNum) {
			String resDir = caseResultDir + File.separator + i;
			String cmd = "sh " + Constant.iOS_REPLAY_SHELL + " " + deviceId + " " + appId + " " + resDir + File.separator + caseName;
			// 创建结果目录
			Helper.createDir(resDir);
			try {
				// 拷贝测试资源
				FileUtils.copyDirectory(new File(testCase.getParent()), new File(resDir));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 执行测试，并获取输出流
			String output = Helper.executeCommand(cmd);
			if (output.contains("Replay Test Success")) {
				System.out.println("Replay Test Success!!!!!!!");
				tempNum++;	
			} else if (output.contains("Replay Test Failed")) {
				System.out.println("Replay Test Failed!!!!!!!");
			}
			// 删除instrumentscli0.trace
			Helper.deleteDirectory(resDir + File.separator + Constant.iOS_INST_TRACE);
			i++;
			if (tempNum == targetNum){
				break;
			}
		}
		int passCount = tempNum;
		int failCount = i - passCount;
		System.out.println("passCount:"+passCount+" failCount:"+failCount);
	}

}
