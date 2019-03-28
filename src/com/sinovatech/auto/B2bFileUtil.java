package com.sinovatech.auto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 项目上线打包工具类
 */
public class B2bFileUtil {
    /**
     * 打包运行
     * 
     * @param packagePath 文件打包路径
     * @param storageFolder 打包文件存储目录
     * @param compileFolder 编译路径
     * @throws Exception
     */
    public static void execute(String packagePath, String storageFolder, String compileFolder) throws Exception {
        System.out.println("开始处理文件=" + packagePath);
        File file = new File(packagePath);
        if (!file.exists()) {
            System.out.println("文件不存在=" + packagePath);
        }
        FileReader freader = new FileReader(file);
        BufferedReader reader = new BufferedReader(freader);
        // 逐行读取
        String line = "";
        int countLine = 0;
        while ((line = reader.readLine()) != null) {
            countLine++;
            if (line.startsWith("#") || "".equals(line)) {
                continue; // 跳过注释
            }
            copyFile(line.trim(), storageFolder, compileFolder);
        }
        reader.close();
        System.out.println("解析文件行数=" + countLine);
        System.out.println("处理文件结束=" + packagePath);
    }

    /**
     * 将文件复制到指定的文件夹中
     * 
     * @param filePath
     * @param storageFolder
     * @param compileFolder
     */
    private static void copyFile(String filePath, String storageFolder, String compileFolder) {
        // 处理Java文件
        if (filePath.contains("/src/main/java/")) {
            filePath = filePath.replace("/src/main/java/", "/" + compileFolder + "/WEB-INF/classes/");
            filePath = filePath.replace(".java", ".class");
        }
        // 处理配置文件
        if (filePath.contains("/src/main/resources/")) {
            filePath = filePath.replace("/src/main/resources/", "/" + compileFolder + "/WEB-INF/classes/");
        }
        // 处理静态文件，包括js,css,image,html,jsp等
        if (filePath.contains("WebRoot")) {
            filePath = filePath.replace("WebRoot", compileFolder);
        }
        String fileFolder = filePath.substring(0, filePath.lastIndexOf("/") + 1);
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length());

        File newFolder = new File(storageFolder + fileFolder);
        if (!newFolder.exists()) {
            newFolder.mkdirs();
        }
        // 处理文件夹中所有的文件
        if (fileName.equals("**")) {
            // 处理文件夹中所有的文件
            File oldFolder = new File(AutoClasses.WORKSPACE + fileFolder);
            copyFolder(oldFolder, newFolder);
        } else {
            // 处理单个文件
            // 原始文件
            File newFile = new File(AutoClasses.WORKSPACE + filePath);
            // 处理java文件
            if (fileName.endsWith(".class")) {
                copyClass2NewFolder(newFile, newFolder);
            } else {
                // 处理非java文件
                copyCommonFile(newFile, newFolder);
            }
        }
    }

    /**
     * 复制class文件
     * 
     * @param file
     * @param folder
     */
    private static void copyClass2NewFolder(File file, File folder) {
        // 去掉.class
        String name = file.getName().substring(0, file.getName().length() - 6);
        System.out.println("name\\\\\\\\\\\\\\\\\\\\\\:" + name);
        File parent = file.getParentFile();
        for (File f : parent.listFiles()) {
            if (f.isFile()) {
                if (f.getName().startsWith(name)) {
                    copyCommonFile(f, folder);
                }
            }
        }
    }

    /**
     * 复制非java文件
     * 
     * @param oldfile
     * @param newFolder
     */
    public static void copyCommonFile(File oldfile, File newFolder) {
        String fileName = oldfile.getName();
        try {
            int byteread = 0;
            if (oldfile.exists() && !oldfile.getName().contains("Test")) { // 文件存在时
                InputStream inStream = new FileInputStream(oldfile); // 读入原文件
                FileOutputStream fs = new FileOutputStream(newFolder + File.separator + fileName);
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
                    copyCommonFile(file[i], newPath);
                }
                if (file[i].isDirectory()) {// 如果是子文件夹
                    copyFolder(file[i], new File(newPath.getAbsolutePath() + "/" + file[i].getName()));
                }
            }
        } catch (Exception e) {
            System.out.println("复制整个文件夹内容操作出错 ");
            e.printStackTrace();
        }
    }

    /**
     * 过滤掉无用的注释、版本号、合并信息并且去除重复记录
     * 
     * @param pathOriginal
     */
    public static void generateTXT(String pathOriginal, String path) {
        System.out.println("开始过滤文件=" + pathOriginal);
        System.out.println("过滤后文件路径=" + path);
        FileReader freader = null;
        BufferedReader reader = null;
        PrintWriter pw = null;
        try {
            File file = new File(pathOriginal);
            if (!file.exists()) {
                System.out.println("文件不存在=" + pathOriginal);
            }
            freader = new FileReader(file);
            reader = new BufferedReader(freader);

            File file2 = new File(path);

            pw = new PrintWriter(file2);

            // 使用Set集合去重
            Set<String> set = new HashSet<String>();

            // 逐行读取
            String line = "";
            int countTotal = 0;
            int countLine = 0;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("WebRoot") || line.startsWith("resources") || line.startsWith("src/main/resources")
                    || line.startsWith("src/main/java")) {
                    countLine++;
                    line = "/b2b/" + line;
                    set.add(line);
                    System.out.println(line);
                }
                countTotal++;
            }
            System.out.println("扫描到文件总行数=" + countTotal);
            System.out.println("过滤后文件剩余行数=" + countLine);
            Iterator<String> iterator = set.iterator();
            while (iterator.hasNext()) {
                String next = iterator.next();
                pw.println(next);
                pw.flush();
            }
            System.out.println("去重后文件剩余行数=" + set.size());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (pw != null) {
                    pw.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        System.out.println("过滤文件完成");
    }

}
