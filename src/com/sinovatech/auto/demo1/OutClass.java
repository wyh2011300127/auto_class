package com.sinovatech.auto.demo1;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;
import javassist.runtime.Inner;

public class OutClass {

    static String str = "ABCD";

    private double d1 = 1.0;

    public OutClass() {
        InnerClass innerClass = new InnerClass();
        System.out.println("OutClass Create");
    }

    private class InnerClass {
        public InnerClass() {
            System.out.println("InnerClass Create");
        }
    }

    public static void main(String[] args) {
        OutClass outClass = new OutClass();
    }

    class InnerOne {
        //非静态内部类不可以有静态成员
        // public static double method() {
        //     return d1;
        // }
    }

    class InnerTwo {
        public double method(){
            return d1;
        }
    }

    static class InnerThree{
        //静态内部类的非静态成员可以访问外部类的静态变量，而不可访问外部类的非静态变量
        // public double method(){
        //     return d1;
        // }
        public String method2(){
            return str;
        }

    }

}