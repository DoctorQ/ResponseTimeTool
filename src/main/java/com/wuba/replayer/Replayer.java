package com.wuba.replayer;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.wuba.device.Device;
import com.wuba.utils.Constant;

public class Replayer {

	private String deviceId;
	private String appId;
	private String casePath;

	public Replayer(Device device) {
		// TODO Auto-generated constructor stub
		this.deviceId = device.getDeviceId();
		this.appId = device.getAppId();
	}

	public void replay(String casePath) {
		String command = "sh " + Constant.iOS_REPLAY_SHELL + " " + deviceId + " " + appId + " " + casePath;
		System.out.println(command);
		StringBuffer output = new StringBuffer();
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println("REPLAY: " + line);
				output.append(line + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
