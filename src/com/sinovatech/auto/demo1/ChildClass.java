package com.sinovatech.auto.demo1;

public class ChildClass extends FatherClass {
    public ChildClass() {
        System.out.println("ChildClass Create");
    }

    public static void main(String[] args) {
        // FatherClass fatherClass = new FatherClass();
        ChildClass childClass = new ChildClass();
    }
}