package com.wuba.device;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.wuba.utils.Constant;
import com.wuba.utils.Helper;

/**
 * IOS设备信息，控制
 * 
 * @author vigoss
 *
 */
public class IOSDevice implements Device {

	private String platform = Constant.IOS_PLATFORM;
	private String deviceId;
	private String deviceName;
	private String osVersion;
	private String appId;
	private int[] screenSize;
	private boolean serverConnected = false;

	public IOSDevice(String deviceId, String appId) {
		// TODO Auto-generated constructor stub
		this.deviceId = deviceId;
		this.appId = appId;
		// 清空temp目录
		Helper.deleteDirectory(Constant.iOS_TEMP_DIR);
		Helper.createDir(Constant.iOS_TEMP_DIR);
	}

//	public void setPlatform(String platform) {
//		this.platform = platform;
//	}

	// public void setDeviceId(String deviceId) {
	// this.deviceId = deviceId;
	// }

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	// public void setAppId(String appId) {
	// this.appId = appId;
	// }

	public void setScreenSize(int[] screenSize) {
		this.screenSize = screenSize;
	}

	@Override
	public String getPlatform() {
		// TODO Auto-generated method stub
		return platform;
	}

	@Override
	public String getDeviceId() {
		// TODO Auto-generated method stub
		return deviceId;
	}

	@Override
	public String getDeviceName() {
		// TODO Auto-generated method stub
		return deviceName;
	}

	@Override
	public String getOsVersion() {
		// TODO Auto-generated method stub
		return osVersion;
	}

	@Override
	public String getAppId() {
		// TODO Auto-generated method stub
		return appId;
	}

	@Override
	public int[] getScreenSize() {
		// TODO Auto-generated method stub
		return screenSize;
	}

	public void clickOnScreen(int x, int y) {
		// TODO Auto-generated method stub

	}

	public void dragFromToScreen(int startX, int startY, int endX, int endY) {
		// TODO Auto-generated method stub

	}

	@Override
	public void takeScreenShot() {
		// TODO Auto-generated method stub

	}

	/**
	 * 获取最新屏幕截图
	 * 
	 * @return
	 */
	public String getLastScreenShot() {
		List<File> results = new ArrayList<File>();
		File[] files = new File(Constant.iOS_RUN1_DIR).listFiles();
		if (files == null) {
			return null;
		}
		for (File file : files) {
			if (file.isFile() && file.getName().startsWith("screen")) {
				results.add(file);
			}
		}

		if (results.size() == 0) {
			return null;
		}

		for (int i = 0; i < results.size() - 2; i++) {
			results.get(i).delete();
		}
		return results.get(results.size() - 1).getName();
	}

	/**
	 * 向设备发送UIAutomation命令
	 * @param command
	 * @return 响应结果
	 */
	public String sendActionCommand(String command) {
		System.out.println("SEND_CMD:" + command);
		Helper.createFileAndWrite(command, Constant.iOS_CMD_FILE);
		String resp = Helper.readFileTimeOut(Constant.iOS_RESP_FILE);
		System.out.println("RESPONSE:" + resp);
		Helper.deleteFile(Constant.iOS_RESP_FILE);
		if (resp == null) {
			System.exit(0);
		}
		;
		return resp.trim();
	}

	/**
	 * 启动录制服务
	 * 
	 * @return
	 */
	public boolean connectRecordServer() {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				Helper.executeCommand("sh " + Constant.iOS_RECORD_SHELL + " " + deviceId + " " + appId + " " + Constant.iOS_LOCAL_DIR);
			}
		});
		thread.start();

		String resp = sendActionCommand(Constant.iOS_START_SERVER_CMD);
		if (resp.equals("0|")) {
			serverConnected = true;
			return true;
		}
		return false;
	}

	/**
	 * 断开录制服务
	 * 
	 * @return
	 */
	public boolean disConnectRecordServer() {
		if (serverConnected) {
			String resp = sendActionCommand(Constant.iOS_CLOSE_SERVER_CMD);
			if (resp.equals("1|")) {
				serverConnected = false;
				return true;
			}
		}
		return false;
	}

}
