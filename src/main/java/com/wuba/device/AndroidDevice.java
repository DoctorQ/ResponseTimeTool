package com.wuba.device;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.android.chimpchat.adb.AdbChimpDevice;
import com.android.ddmlib.AdbCommandRejectedException;
import com.android.ddmlib.IDevice;
import com.android.ddmlib.RawImage;
import com.android.ddmlib.TimeoutException;
import com.wuba.utils.Constant;


/**
 * Android设备信息，控制
 * 
 * @author vigoss
 *
 */

public class AndroidDevice implements Device {

	private String platform = Constant.ANDROID_PLATFORM;
	private String appId;
	private AdbChimpDevice mChimpDevice;		
	private IDevice mDevice;
	
	public AndroidDevice(IDevice iDevice) {
		// TODO Auto-generated constructor stub
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
		if (mChimpDevice != null){
			mChimpDevice.dispose();
		}
	}

	@Override
	public void takeScreenShot() {
		// TODO Auto-generated method stub
//		mDevice.getScreenshot()
	}
	
	public boolean getScreenShot(String filepath) {
		RawImage rawScreen = null;
		BufferedImage image = null;
		try {
			rawScreen = mDevice.getScreenshot();
		} catch (AdbCommandRejectedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (com.android.ddmlib.TimeoutException e) {
			e.printStackTrace();
		}
		if (rawScreen != null) {
			Boolean landscape = false;
			int width2 = landscape ? rawScreen.height : rawScreen.width;
			int height2 = landscape ? rawScreen.width : rawScreen.height;
			if (image == null) {
				image = new BufferedImage(width2, height2,
						BufferedImage.TYPE_INT_RGB);
			} else {
				if (image.getHeight() != height2 || image.getWidth() != width2) {
					image = new BufferedImage(width2, height2,
							BufferedImage.TYPE_INT_RGB);
				}
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

				ImageIO.write((RenderedImage) image, "PNG", new File(filepath));
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
