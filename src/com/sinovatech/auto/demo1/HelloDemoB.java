package com.sinovatech.auto.demo1;

/**
 * 对象的初始化顺序：（1）类加载之后，按从上到下（从父类到子类）执行被static修饰的语句； （2）当static语句执行完之后,再执行main方法；（3）如果有语句new了自身的对象，
 * 将从上到下执行构造代码块、构造器（两者可以说绑定在一起）。
 * 
 * @author:wangyuheng
 * @date:2019/2/24 19:37
 */
public class HelloDemoB extends HelloDemoA {

    public static void main(String[] args) {
        System.out.println("--main start--");
        new HelloDemoB();
        new HelloDemoB();
        System.out.println("--main end--");
    }

    public HelloDemoB() {
        System.out.println("HelloDemoB");
    }

    {
        System.out.println("I am HelloDemoB");
    }
    static {
        System.out.println("static HelloDemoB");
    }
}

class HelloDemoA {
    public HelloDemoA() {
        System.out.println("HelloDemoA");
    }

    {
        System.out.println("I am HelloDemoA");
    }
    static {
        System.out.println("static HelloDemoA");
    }

}