package com.sinovatech.auto;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;

import com.sinovatech.auto.des.PasswordUtil;

public class PasssWord {
    /**
     * 单独的盐
     **/
    public static final String MD5_ENCRYPT_SALT = "X0w3@v";

    public static void main(String[] args) {
        try {
            System.out.println(PasswordUtil.encDBPassword("Abc!1234"));
            System.out.println(DigestUtils.md5Hex("111111" + MD5_ENCRYPT_SALT + "jmstest01"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // new Random().nextInt();
        // System.out.println(new Random().nextInt(100000000));
        /*String buyAddr = "安徽省合肥市蜀山区合作化南路998号安徽移动设计院三楼";
            buyAddr = buyAddr.substring(buyAddr.indexOf("省") + 1, buyAddr.indexOf("市") + 1);
            System.out.println(buyAddr);*/
        Map<String, String> map = new HashMap<String, String>(16);
        Object obj = new Object();
        //==对于基本数据类型变量，直接比较值，对于引用类型变量，比较的是变量所指向的对象的地址是否相同
        //equals，基本数据类型不能使用，引用类型变量指的是比较的是引用类型的变量所指向的对象的地址，
        //重写了equals方法的对象，比如，String,Integer,Double,比较的都是对象的内容是否相同



    }

}
