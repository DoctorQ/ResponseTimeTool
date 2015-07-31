/**
 * 
 */
package com.wuba.email;

import java.io.File;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wuba.utils.DirStructureUtil;

/**
 * @author hui.qian qianhui@58.com
 * @date 2015年7月30日 下午6:02:32
 */
public class EmailSenderTest {
	EmailSender sender;

	@BeforeClass
	public void setUp() {
		sender = new EmailSender();
	}

	@Test()
	public void sendEmailTest() {
		File file = new File(DirStructureUtil.getReport(),
				"/2015-07-31_10-45-44/testReport.html");
		sender.send(file);
	}

}
