package com.wuba.replayer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

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
	private String resDir;
	private boolean gatherFlag = false;

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
			resDir = caseResultDir + File.separator + i;
			String cmd = "sh " + Constant.iOS_REPLAY_SHELL + " " + deviceId + " " + appId + " " + resDir + File.separator + caseName;
			// 创建结果目录
			Helper.createDir(resDir);
			// 启动日志线程
			initSysLogMonitor();
			try {
				// 拷贝测试资源
				FileUtils.copyDirectory(new File(testCase.getParent()), new File(resDir));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 执行测试，并获取输出流
			String output = exec(cmd);
			if (output.contains("Replay Test Success")) {
				System.out.println("Replay Test Success!!!!!!!");
				tempNum++;	
			} else if (output.contains("Replay Test Failed")) {
				System.out.println("Replay Test Failed!!!!!!!");
			}
			gatherFlag = false;
			// 关闭deviceconsole
			Helper.executeCommand(Constant.iOS_KILL_DEVICECONSOLE_CMD);
			// 删除instrumentscli0.trace
			Helper.deleteDirectory(resDir + File.separator + Constant.iOS_INST_TRACE);
			i++;
			if (tempNum == targetNum){
				break;
			}
		}
		int passCount = tempNum;
		int failCount = i - passCount;
		System.out.println("passCount: "+passCount+" failCount: "+failCount);
	}
	
	private String exec(String command) {
		StringBuffer output = new StringBuffer();
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			// p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println("REPL_STDOUT : " + line);
				if (line.contains("BEGIN CAPTURE TIME-LOG")){
					gatherFlag = true;
				}else if(line.contains("END CAPTURE TIME-LOG")){
					gatherFlag = false;
				}
				output.append(line + "\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return output.toString();
	}
	
	/**
	 * 日志过滤
	 */
	private void initSysLogMonitor() {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				FileWriter fw;
				String cmd = Constant.iOS_DEVICECONSOLE + " -u " + deviceId + " -p 58tongcheng";
				Process p;
				BufferedReader reader;
				Pattern pattern = Pattern.compile(Constant.iOS_DEVICELOG_REGEX);
				try {
					fw = new FileWriter(resDir + File.separator + "timelog.log", true);
					p = Runtime.getRuntime().exec(cmd);
					reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
					String line;
					String filterLine;
					while ((line = reader.readLine()) != null) {
						if (pattern.matcher(line).find()) {
//							System.out.println("DEV_LOG: " + line);
							if (gatherFlag){
								filterLine = "TIME_LOG: " + line;
								fw.write(filterLine + "\n");
								System.out.println(filterLine);
							}
						}
					}
					fw.flush();
					fw.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		thread.start();
	}

}
