package com.wuba.device;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.robovm.libimobiledevice.IDevice;
import org.robovm.libimobiledevice.LockdowndClient;

import com.dd.plist.NSDictionary;
import com.dd.plist.NSObject;
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
	private String appId;
	private int[] screenSize;
	private IDevice iDevice;
	private LockdowndClient iClient;
	private NSObject iNode;
	private NSDictionary iDict;
	private boolean serverConnected = false;
	

	public IOSDevice(String deviceId, String appId) {
		// TODO Auto-generated constructor stub
		this.deviceId = deviceId;
		this.appId = appId;
		// 清空temp目录
		cleanTempDir();
		// 初始化iDict
		initIdict();
	}
	
	protected void initIdict(){
		try {
			iDevice = new IDevice(deviceId);
			iClient = new LockdowndClient(iDevice, null, true);
			iNode = iClient.getValue(null, null);
			iDict = (NSDictionary) iNode;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if (iClient != null) {
				iClient.dispose();
				iClient = null;
	        }
	        if (iDevice != null) {
	        	iDevice.dispose();
	        	iDevice = null;
	        }
		}
		
	}

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
		String key = iDict.objectForKey("ProductType").toString();
		return Constant.IPHONE_MAP.get(key);
	}

	@Override
	public String getOsVersion() {
		// TODO Auto-generated method stub
		return iDict.objectForKey("ProductVersion").toString();
	}

	@Override
	public String getAppId() {
		// TODO Auto-generated method stub
		return appId;
	}
	
	@Override
	public void insertLogStartFlag() {
		// TODO Auto-generated method stub
		sendActionCommand("UIALogger.logDebug('BEGIN CAPTURE TIME-LOG');");
	}

	@Override
	public void insertLogStopFlag() {
		// TODO Auto-generated method stub
		sendActionCommand("UIALogger.logDebug('END CAPTURE TIME-LOG');");
	}
	
	@Override
	public void instertImgCheckPoint(String image) {
		// TODO Auto-generated method stub
		sendActionCommand("verifyImage('" + image + "');");
	}

	@Override
	public int[] getScreenSize() {
		// TODO Auto-generated method stub
		String key = iDict.objectForKey("ProductType").toString();
		int[] xy = Constant.IPHONE_DPI_MAP.get(key);
		return xy;
	}

	public void clickOnScreen(int x, int y) {
		// TODO Auto-generated method stub
		sendActionCommand("target.tapWithOptions({ x: " + x + ", y: " + y + " }, {tapCount: 1, touchCount: 1, duration: 0.5});");

	}

	public void dragFromToScreen(int startX, int startY, int endX, int endY) {
		// TODO Auto-generated method stub
		sendActionCommand("target.dragFromToForDuration({ x: " + startX + ", y: " + startY + " },{ x: " + endX + ", y: " + endY + " }, 0.5);");
	}

	@Override
	public BufferedImage takeScreenShot() {
		// TODO Auto-generated method stub
		return null;
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
	 * e.g.
	 * 点击:target.tapWithOptions({ x: 33, y: 268 }, {tapCount: 1, touchCount: 1, duration: 0.5});
	 * 滑瓶:target.dragFromToForDuration({ x: 50, y: 50 },{ x: 50, y: 100 }, 0.5);
	 * 获取设备信息:target.model();
	 * @param command
	 * @return 响应结果
	 */
	public String sendActionCommand(String command) {
		System.out.println("SEND_CMD: " + command);
		Helper.createFileAndWrite(command, Constant.iOS_CMD_FILE);
		String resp = Helper.readFileTimeOut(Constant.iOS_RESP_FILE).trim();
		System.out.println("RESPONSE: " + resp);
		Helper.deleteFile(Constant.iOS_RESP_FILE);
		if (resp == null) {
			System.exit(0);
		}
		return resp;
	}

	/**
	 * 启动录制服务
	 * 
	 * @return
	 */
	public boolean connectRecordServer() {
		Helper.executeCommand(Constant.iOS_KILL_INST_CMD);
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				String cmd = "sh " + Constant.iOS_RECORD_SHELL + " " + deviceId + " " + appId + " " + Constant.iOS_LOCAL_DIR;
				Helper.executeCommand(cmd);
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
		Helper.executeCommand(Constant.iOS_KILL_INST_CMD);
		if (serverConnected) {
			String resp = sendActionCommand(Constant.iOS_CLOSE_SERVER_CMD);
			if (resp.equals("1|")) {
				serverConnected = false;
				return true;
			}
		}
		
		return false;
	}

	private void cleanTempDir() {
		// 清空temp目录
		Helper.deleteDirectory(Constant.iOS_TEMP_DIR);
		Helper.createDir(Constant.iOS_TEMP_DIR);
	}
}
