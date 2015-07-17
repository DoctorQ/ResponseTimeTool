package com.wuba.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 公共方法
 * 
 * @author vigoss
 *
 */
public class Helper {

	public static String executeCommand(String command) {

		StringBuffer output = new StringBuffer();

		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

			String line = "";
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return output.toString();

	}
	
	/**
	 * 创建文件，并写入内容，非追加，如何已存在文件会覆写
	 * @param line
	 * @param filename
	 */
	public static void createFileAndWrite(String line, String filename){
		FileWriter fw;
		try {
			fw = new FileWriter(filename, false);
			fw.write(line);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 读取文件
	 * @param fileName
	 */
	public static String readFileByLines(String fileName) {
		File file = new File(fileName);
		StringBuffer output = new StringBuffer();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = "";
			// 一次读入一行，直到读入null为文件结束
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return output.toString();
	}
	
	/**
	 * 超时读取文件 5s
	 * @param filename
	 * @return
	 */
	public static String waitForReadFile(String filename){
		long startTime = System.currentTimeMillis();
		long timeOut = 10*1000;
		File file = new File(filename);
		boolean exist = file.exists();
		while(!exist){
			long durationTime = System.currentTimeMillis() - startTime;
			if (durationTime > timeOut) {
				return null;
			}
			exist = file.exists();
		}
		
		return readFileByLines(filename);
	}
	
	/** 
	 * 删除单个文件 
	 * @param   sPath    被删除文件的文件名 
	 * @return 单个文件删除成功返回true，否则返回false 
	 */  
	public static boolean deleteFile(String sPath) {  
	    boolean flag = false;  
	    File file = new File(sPath);  
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	        flag = true;  
	    }  
	    return flag;  
	}  

}
