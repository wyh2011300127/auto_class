package com.sinovatech.auto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class TxtReplace {

	/**
	 * 遍历文件夹下的所有文件，并替换执行内容
	 * @param args
	 */
	public static void main(String[] args) {
		String filePath = "D:/money/b2b_trunk/portal"; // 给我你要读取的文件夹路径
		File outPath = new File("D:/after/b2b_trunk/portal"); // 随便给一个输出文件夹的路径(不存在也可以)
		readFolder(filePath, outPath, ".jsp");
	}

	/**
	 * 读取文件夹
	 * 
	 * @return
	 */
	public static void readFolder(String filePath, File outPath, String subFix) {
		try {
			// 读取指定文件夹下的所有文件
			File file = new File(filePath);
			if (!file.isDirectory()) {
				System.out.println("---------- 该文件" + filePath + "不是一个目录文件 ----------");
				readFile(filePath, file.getName(), outPath);// 调用readFile方法读取文件夹下所有文件
			} else if (file.isDirectory()) {
				System.out.println("---------- 很好，这是一个目录文件夹 ----------");
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					File readfile = new File(filePath + "\\" + filelist[i]);
					if (readfile.isDirectory()) {
						readFolder(filePath + "\\" + filelist[i], new File(outPath + "\\" + filelist[i]), subFix);
					} else {
						String absolutepath = readfile.getAbsolutePath();// 文件的绝对路径
						String filename = readfile.getName();// 读到的文件名
						if (filename.endsWith(subFix)) {
							readFile(absolutepath, filename, outPath);// 调用readFile方法读取文件夹下所有文件
						}
					}
				}
				System.out.println("---------- 所有文件操作完毕 ----------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读取文件夹下的文件
	 * 
	 * @return
	 */
	public static void readFile(String absolutepath, String filename, File outPath) {
		try {
			BufferedReader bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(absolutepath),"GBK"));// 数据流读取文件
			StringBuffer strBuffer = new StringBuffer();
			String empty = "jquery-1.11.3";
			String tihuan1 = "jquery-1.8.3";
			String tihuan2 = "jquery-1.8.4";
			boolean needModify = false;
			for (String temp = null; (temp = bufReader.readLine()) != null; temp = null) {
				if (temp.indexOf(tihuan1) != -1 || temp.indexOf(tihuan2) != -1) {
					System.out.println(temp);
					temp = temp.replace(tihuan1, empty);
					temp = temp.replace(tihuan2, empty);
					needModify = true;
				}
				strBuffer.append(temp);
				strBuffer.append(System.getProperty("line.separator"));// 行与行之间的分割
			}
			bufReader.close();
			if(needModify) {
				if (outPath.exists() == false) { // 检查输出文件夹是否存在，若不存在先创建
					outPath.mkdirs();
					System.out.println("已成功创建输出文件夹：" + outPath);
				}
				PrintWriter printWriter = new PrintWriter(outPath + "\\" + filename);// 替换后输出的文件位置（切记这里的E:/ttt
				// 在你的本地必须有这个文件夹）
				printWriter.write(strBuffer.toString().toCharArray());
				printWriter.flush();
				printWriter.close();
				System.out.println(absolutepath + "  已成功输出到    " + outPath + "\\" + filename);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getFileEncode(String absolutepath){
		String javaEncode = EncodingDetect.getJavaEncode(absolutepath);
		System.out.println(absolutepath + ":" + javaEncode);
		return javaEncode;
	}
}
