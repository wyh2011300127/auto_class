package com.sinovatech.auto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AutoClasses {
    // 工作空间路径
    public final static String WORKSPACE = "D:/JetBrains/IdeaProjects/";
    // 打包文件存储目录
    private final static String TO_FOLDER
        = "D:/Package/" + new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
    // 打包原始文件路径
    private final static String PATH_ORIGINAL
        = AutoClasses.class.getClassLoader().getResource("originalPath.txt").getFile();
    // 打包列表文件路径
    private final static String PATH_QT
        = PATH_ORIGINAL.substring(0, PATH_ORIGINAL.indexOf("bin")) + "src/classPath.txt";

    /**
     * 选择打包文件路径还是class
     * 
     * @param type "path" 打包路径 "class" 打包class
     * @throws Exception
     */
    public static void choosePathOrClasses(String type) throws Exception {
        // 1.此处可变，目录到b2b下面项目编译路径
        // 2.out/artifacts/b2b_war_exploded或者WebRoot或者自己配置
        String path1 = "WebRoot";
        // String path2 = "out/artifacts/b2b_war_exploded";
        if ("path".equals(type)) {
            B2bFileUtil.generateTXT(PATH_ORIGINAL, PATH_QT);

        }
        if ("class".equals(type)) {
            B2bFileUtil.execute(PATH_QT, TO_FOLDER, path1);
        }
        if ("".equals(type)) {
            B2bFileUtil.generateTXT(PATH_ORIGINAL, PATH_QT);
            Thread.sleep(5000);
            B2bFileUtil.execute(PATH_QT, TO_FOLDER, path1);
        }
    }

    public static void main(String[] args) throws Exception {
        choosePathOrClasses("class");
    }

}
