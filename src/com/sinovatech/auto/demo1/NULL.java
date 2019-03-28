package com.sinovatech.auto.demo1;

public class NULL {

    public static void haha() {
        System.out.println("hahaha");
    }

    public static void main(String[] args) {
        try {
            // ((NULL)null).haha();能输出，但是编译不通过
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}