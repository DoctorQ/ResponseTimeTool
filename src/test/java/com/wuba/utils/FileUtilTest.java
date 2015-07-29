/**
 * 
 */
package com.wuba.utils;

import java.io.File;

import org.testng.annotations.Test;

/**
 * @author hui.qian qianhui@58.com  
 * @date 2015年7月29日 下午4:20:13
 */
public class FileUtilTest {
	
	
	@Test(groups={"other"})
	public void mkdirsRWXTest(){
		FileUtil.mkdirsRWX(new File("repo/report"));
	}

}
