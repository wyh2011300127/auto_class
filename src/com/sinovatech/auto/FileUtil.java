package com.sinovatech.auto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;

public class FileUtil {

	public static void execute(String path, String rootFolder) throws Exception {
		System.out.println("开始处理文件=" + path);

		File file = new File(path);
		if (!file.exists()) {
			System.out.println("文件不存在=" + path);
		}

		// System.out.println("开始解析文件=" + path);
		BufferedReader reader = new BufferedReader(new FileReader(file));

		// 逐行读取
		String line = "";
		int countLine = 0;
		while ((line = reader.readLine()) != null) {
			countLine++;
			if (line.startsWith("#") || "".equals(line)) {
				continue; // 跳过注释
			}

			copyFile(line, rootFolder);
		}
		reader.close();

		System.out.println("解析文件行数=" + countLine);
		System.out.println("解析文件结束=" + path);
		System.out.println("处理文件结束=" + path);
	}

	// 将文件复制到指定的文件夹中
	private static void copyFile(String file, String rootFolder) {
		// D:/workspace/shop/src/main/java/com/sinovatech/shop/action/ActionConstants.java
		// D:/workspace/shop/WebRoot/WEB-INF/classes/com/sinovatech/shop/action/BaseShopController.class
		if (file.contains("/src/main/java")) { // 处理Java文件
			file = file.replace("/src/main/java", "/WebRoot/WEB-INF/classes");
			file = file.replace(".java", ".class");
		}

		String fileFolder = file.substring(0, file.lastIndexOf("/") + 1);
		String fileName = file.substring(file.lastIndexOf("/") + 1,
				file.length());

		File newFolder = new File(rootFolder + fileFolder);
		if (!newFolder.exists()) {
			newFolder.mkdirs();
		}

		if (fileName.equals("**")) { // 处理文件夹中所有的文件
			File oldFolder = new File(AutoClasses.WORKSPACE + fileFolder); // 处理文件夹中所有的文件
			copyFolder(oldFolder, newFolder);
		} else { // 处理单个文件
			File newFile = new File(AutoClasses.WORKSPACE + file); // 原始文件
			if (fileName.endsWith(".class")) { // 处理java文件
				copyClass2NewFolder(newFile, newFolder);
			} else { // 处理非java文件
				copyJavaFile(newFile, newFolder);
			}
		}
	}

	// D:\workspace\shop\WebRoot\WEB-INF\classes\com\sinovatech\shop\action
	// D:\workspace\shop\shop\WebRoot\WEB-INF\classes\com\sinovatech\shop\action\ActionConstants.class
	private static void copyClass2NewFolder(File file, File folder) {
		// 去掉.class
		String name = file.getName().substring(0, file.getName().length() - 6);

		File parent = file.getParentFile();
		for (File f : parent.listFiles()) {
			if (f.isFile()) {
				if (f.getName().startsWith(name)) {
					copyJavaFile(f, folder);
				}
			}
		}
	}

	/**
	 * 复制单个文件
	 */
	public static void copyJavaFile(File oldfile, File newFolder) {
		String fileName = oldfile.getName();

		try {
			int byteread = 0;
			if (oldfile.exists() && !oldfile.getName().contains("Test")) { // 文件存在时
				InputStream inStream = new FileInputStream(oldfile); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newFolder
						+ File.separator + fileName);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					fs.write(buffer, 0, byteread);
				}

				inStream.close();
				fs.close();
			}
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错 ");
			e.printStackTrace();
		}
	}

	/**
	 * 复制整个文件夹内容
	 */
	public static void copyFolder(File oldPath, File newPath) {
		if (!newPath.exists()) {
			newPath.mkdirs();
		}

		try {
			File[] file = oldPath.listFiles();
			for (int i = 0; i < file.length; i++) {
				if (file[i].isFile()) {
					copyJavaFile(file[i], newPath);
				}

				if (file[i].isDirectory()) {// 如果是子文件夹
					copyFolder(file[i], new File(newPath.getAbsolutePath()
							+ "/" + file[i].getName()));
				}
			}
		} catch (Exception e) {
			System.out.println("复制整个文件夹内容操作出错 ");
			e.printStackTrace();
		}
	}

}
