package com.sinovatech.auto.map;

import java.util.HashMap;
import java.util.Map;

public class HashMapDemo1 {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>(16);
        map.put("A", "a");
        // System.out.println(map.get("A"));
        // print();
        // binary();
        test1();
    }

    public static void print() {
        String str = "A";
        int hashCode = str.hashCode();
        System.out.println(hashCode);
        int i = hashCode >>> 16;
        System.out.println(i);
        System.out.println(hashCode ^ i);

    }

    public static void binary() {
        int i = -5;
        printInfo(i);
        i = i >> 2;
        printInfo(i);
        System.out.println(i);
        // int number = 10;
        // // 原始数二进制
        // printInfo(number);
        // number = number << 1;
        // // 左移一位
        // printInfo(number);
        // number = number >>> 2;
        // printInfo(number);
        // 右移一位
        // printInfo(number);
        // number = number >>> 3;
        // printInfo(number);
        // System.out.println(number);

    }

    /**
     * 输出一个int的二进制数
     */
    private static void printInfo(int num) {
        System.out.println(Integer.toBinaryString(num));
    }

    public static void test1() {
        int n = 16 - 1;
        System.out.println(Integer.toBinaryString(n));
        n |= n >>> 1;
        System.out.println(Integer.toBinaryString(n));
        n |= n >>> 2;
        System.out.println(Integer.toBinaryString(n));
        n |= n >>> 4;
        System.out.println(Integer.toBinaryString(n));
        n |= n >>> 8;
        System.out.println(Integer.toBinaryString(n));
        n |= n >>> 16;
        System.out.println(Integer.toBinaryString(n));
        n = (n < 0) ? 1 : (n >= 1 << 30) ? 1 << 30 : n + 1;
        System.out.println(n);
    }
}