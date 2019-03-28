package com.sinovatech.auto.yinyong;

public class TestDTO {
    private String name;
    private String tel;

    public TestDTO() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "TestDTO [name=" + name + ", tel=" + tel + "]";
    }

}
