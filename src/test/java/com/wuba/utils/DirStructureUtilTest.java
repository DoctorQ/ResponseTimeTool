/**
 * 
 */
package com.wuba.utils;

import java.io.File;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

/**
 * @author hui.qian qianhui@58.com
 * @date 2015年7月22日 下午5:57:54
 */
public class DirStructureUtilTest {
	private static final Logger LOG = Logger
			.getLogger(DirStructureUtilTest.class);

	@Test(groups = { "unittest" })
	public void getResultAndroidTest() {
		File file = DirStructureUtil.getResultAndroid();
		LOG.info(file.getAbsolutePath());
	}

	@Test(groups = { "unittest" })
	public void getResultIOSTest() {
		File file = DirStructureUtil.getResultIOS();
		LOG.info(file.getAbsolutePath());
	}

}
