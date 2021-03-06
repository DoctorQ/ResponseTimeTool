package com.wuba.device;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.android.chimpchat.adb.AdbChimpDevice;
import com.android.ddmlib.AdbCommandRejectedException;
import com.android.ddmlib.DdmPreferences;
import com.android.ddmlib.IDevice;
import com.android.ddmlib.RawImage;
import com.wuba.utils.Constant;


/**
 * Android设备信息，控制
 * 
 * @author vigoss
 *
 */

public class AndroidDevice implements Device {

	private String platform = Constant.ANDROID_PLATFORM;
	private static final int adbTimeOut = 8000;
	private static final String monkeyProcess = "com.android.commands.monkey";
	private String appId;
	private AdbChimpDevice mChimpDevice;		
	private IDevice mDevice;
	
	public AndroidDevice(IDevice iDevice) {
		// TODO Auto-generated constructor stub
		DdmPreferences.setTimeOut(adbTimeOut);
		mDevice = iDevice;
		mChimpDevice = new AdbChimpDevice(iDevice);
	}

	@Override
	public String getPlatform() {
		// TODO Auto-generated method stub
		return this.platform;
	}

	@Override
	public String getDeviceId() {
		// TODO Auto-generated method stub
		return mDevice.getSerialNumber();
	}

	@Override
	public String getDeviceName() {
		// TODO Auto-generated method stub
		return mChimpDevice.getSystemProperty("ro.product.model");
	}

	@Override
	public String getOsVersion() {
		// TODO Auto-generated method stub
		return mChimpDevice.getSystemProperty("ro.build.version.release");
	}
	
	public void setAppId(String appId){
		this.appId = appId;
	}

	@Override
	public String getAppId() {
		// TODO Auto-generated method stub
		return this.appId;
	}

	@Override
	public void insertLogStartFlag() {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertLogStopFlag() {
		// TODO Auto-generated method stub

	}

	@Override
	public void instertImgCheckPoint(String image) {
		// TODO Auto-generated method stub

	}

	@Override
	public int[] getScreenSize() {
		// TODO Auto-generated method stub
		String output = mChimpDevice.shell("dumpsys window | grep mUnrestrictedScreen");
		String[] xy = output.trim().split(" ")[1].split("x");
		int loc[] = {Integer.parseInt(xy[0]), Integer.parseInt(xy[1])};
		return loc;
	}

	public void touchDown(int x, int y) {
		try {
			mChimpDevice.getManager().touchDown(x, y);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void touchUp(int x, int y) {
		try {
			mChimpDevice.getManager().touchUp(x, y);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void touchMove(int x, int y) {
		try {
			mChimpDevice.getManager().touchMove(x, y);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void dispose(){
		execShell("kill " + getPid(monkeyProcess));
		if (mChimpDevice != null){
			mChimpDevice.dispose();
		}
	}

	@Override
	public BufferedImage takeScreenShot() {
		RawImage rawScreen = null;
		BufferedImage image = null;
		int width;
		int height;
		int orientation = getOrientation();
		try {
			rawScreen = mDevice.getScreenshot();
		} catch (AdbCommandRejectedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (com.android.ddmlib.TimeoutException e) {
			e.printStackTrace();
		}
		boolean landscape = false;
		if (orientation == 0 || orientation == 2){
			width = rawScreen.width;
			height = rawScreen.height;
			image = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			
		} else if(orientation == 1 || orientation == 3){
			width = rawScreen.height;
			height = rawScreen.width;
			image = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			landscape = true;
		}
		 int index = 0;   
         int indexInc = rawScreen.bpp >> 3;   
         for (int y = 0; y < rawScreen.height; y++) {   
             for (int x = 0; x < rawScreen.width; x++, index += indexInc) {   
                 int value = rawScreen.getARGB(index);   
                 if (landscape)   
                     image.setRGB(y, rawScreen.width - x - 1, value);   
                 else  
                     image.setRGB(x, y, value);   
             }   
         }
		try {
			ImageIO.write(image, "png", new File(System.getProperty("user.dir")+File.separator+"console.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
	}
	
	public void execShell(String shellCommand){
		mChimpDevice.shell(shellCommand);
	}
	
	public int getPid(String processName){
		String pLine = mChimpDevice.shell("ps | grep " + processName);
		String[] items = pLine.trim().split("[\\s]+");
		return Integer.parseInt(items[1]);
	}
	
	public int getOrientation(){
		String line = mChimpDevice.shell("dumpsys input | grep SurfaceOrientation");
		String[] items = line.trim().split(":");
		return Integer.parseInt(items[1].trim());
	}

}
