/**
 * 
 */
package com.wuba.utils;

import java.io.File;

import org.testng.log4testng.Logger;

/**
 * @author hui.qian qianhui@58.com
 * @date 2015年7月29日 下午4:12:55
 */
public class FileUtil {
	private static final Logger LOG = Logger.getLogger(FileUtil.class);

	public static boolean mkdirsRWX(File file) {
		File parent = file.getParentFile();

		if (parent != null && !parent.isDirectory()) {
			// parent doesn't exist. recurse upward, which should both mkdir and
			// chmod
			if (!mkdirsRWX(parent)) {
				// Couldn't mkdir parent, fail
				LOG.info(String
						.format("Failed to mkdir parent dir %s.", parent));
				return false;
			}
		}

		// by this point the parent exists. Try to mkdir file
		if (file.isDirectory() || file.mkdir()) {
			// file should exist. Try chmod and complain if that fails, but keep
			// going

		}

		return file.isDirectory();
	}

	public static boolean chmodRWXRecursively(File file) {
		boolean success = true;
		if (!file.setExecutable(true, false)) {
			LOG.warn(String.format("Failed to set %s executable.",
					file.getAbsolutePath()));
			success = false;
		}
		if (!file.setWritable(true, false)) {
			LOG.warn(String.format("Failed to set %s writable.",
					file.getAbsolutePath()));
			success = false;
		}
		if (!file.setReadable(true, false)) {
			LOG.warn(String.format("Failed to set %s readable",
					file.getAbsolutePath()));
			success = false;
		}

		if (file.isDirectory()) {
			File[] childs = file.listFiles();
			for (File child : childs) {
				if (!chmodRWXRecursively(child)) {
					success = false;
				}
			}

		}
		return success;
	}

}
