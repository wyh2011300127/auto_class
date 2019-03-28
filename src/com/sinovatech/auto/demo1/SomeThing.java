package com.sinovatech.auto.demo1;

public class SomeThing {
    int i;

    public void print() {
        System.out.println("i=" + i);
        // int k;
        // System.out.println("k=" + k);
    }

    public static void main(String[] args) {
        // new SomeThing().print();
        // int i = 2;
        // i += i -= i * i;
        // System.out.println(i);

        int i = 0;
        for (; i < 4; i += 2) {
            System.out.print(i + "");
        }
        System.out.println(i);
    }
}