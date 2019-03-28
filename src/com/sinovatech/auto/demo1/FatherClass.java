package com.sinovatech.auto.demo1;

public class FatherClass {
    public FatherClass() {
        System.out.println("FatherClass Create");
    }

    public static void main(String[] args) {
        String s1 = "123";
        String s2 = "123";
        String s3 = "1" + new String("23");

        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s1.equals(s2));

        String ss1 = new String("456");
        String ss2 = new String("456");

        System.out.println(ss1==ss2);

        int i = Integer.MAX_VALUE;
        System.out.println(i+1<i);

        int a = 1;
        int b = ++a;
        System.out.println("a:"+a+",b:"+b);

        // Thread t = new Thread() {
        //     @Override
        //     public void run() {
        //         pong();
        //     }
        // };
        // t.run();
        // System.out.println("ping");

        // FatherClass fatherClass1 = new FatherClass();
        // FatherClass fatherClass2 = new FatherClass();
        // System.out.println(fatherClass1==fatherClass2);



    }

    public void FatherClass() {

    }

    public int FatherClass(String string) {
        return 0;
    }

    static void pong() {
        System.out.println("pong");
    }

}