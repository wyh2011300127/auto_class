package com.sinovatech.auto.excel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PoiUtil {
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<Integer>();
        list.add(0);
        list.add(1);
        // poi操作工具类
        PoiReadExcelBean poiReadBean = new PoiReadExcelBeanImpl();
        try {
            List dataList = poiReadBean.readExcel("E:\\档口商双串码导入模板.xls", 1, list);
            if (dataList != null && dataList.size() > 0) {
                for (int i = 0; i < dataList.size(); i++) {
                    Object o = dataList.get(i);
                    System.out.println(o);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Integer> integers = Arrays.asList(1, 2);
        for (Integer i:integers){
            System.out.println(i);
        }



    }


}