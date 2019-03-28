package com.sinovatech.auto.yinyong;

import java.util.ArrayList;
import java.util.List;

public class TestYinYong {

    public static void main(String[] args) {
        List<TestDTO> arraylist = new ArrayList<TestDTO>();
        TestDTO testDTO1 = new TestDTO();
        testDTO1.setName("zhangsan");
        testDTO1.setTel("18767890987");
        TestDTO testDTO2 = new TestDTO();
        testDTO2.setName("lisi");
        testDTO2.setTel("18967890577");
        arraylist.add(testDTO1);
        arraylist.add(testDTO2);
        for (int i = 0; i < arraylist.size(); i++) {
            TestDTO testDTO = arraylist.get(i);
            String tel = testDTO.getTel();
            tel = tel.substring(0, 3) + "****" + tel.substring(tel.length() - 4, tel.length());
            System.out.println(tel);
            testDTO.setTel(tel);

        }
        for (TestDTO testDTO : arraylist) {
            System.out.println(testDTO.toString());
        }
        String[] arr = new String[] {"aa", "ss"};
        Object[] obj = new Object[] {1, "2dd", 1234, 'e'};

    }

}
