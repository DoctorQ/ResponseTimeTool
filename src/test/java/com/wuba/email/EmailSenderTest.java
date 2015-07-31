/**
 * 
 */
package com.wuba.email;

import java.io.File;
import java.io.FileFilter;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;
import org.w3c.dom.html.HTMLScriptElement;

import com.android.io.FolderWrapper;
import com.android.io.IAbstractFolder;
import com.android.io.IAbstractFolder.FilenameFilter;
import com.wuba.utils.DirStructureUtil;

/**
 * @author hui.qian qianhui@58.com
 * @date 2015年7月30日 下午6:02:32
 */
public class EmailSenderTest {
	EmailSender sender;

	@BeforeGroups(groups = { "unittest" })
	public void setUp() {
		sender = new EmailSender();
	}

	@Test
	public void sendEmailTest() {

		File rootFile = DirStructureUtil.getReport();

		FileFilter filter = new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				// TODO Auto-generated method stub
				if (pathname.getName().equals("testReport.html"))
					return true;
				return false;
			}
		};
		File[] files = rootFile.listFiles();
		for (File file : files) {
			File[] reports = file.listFiles(filter);
			if (files.length > 0) {
				sender.send(reports[0]);
				return;
			}
		}

	}

}
