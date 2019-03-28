package com.sinovatech.auto.util;

import com.sinovatech.common.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author zhoukun
 * @description < String工具 >
 */
public class StringUtil {
    /**
     * 判断是否为数字类型 包括带小数点
     */
    private static Pattern POINT_PATTERN = Pattern.compile("^(-|\\+)?\\d+(\\.\\d+)?$");
    /**
     * 判断是否为全数字 正整数
     */
    private static Pattern NUMBER_PATTERN = Pattern.compile("^[0-9]\\d+$");

    public static String format(String s, String... args) {
        String result = s;
        int index = 0;
        String regex = "%s";
        while (result.contains(regex)) {
            if (index == args.length) {
                break;
            }

            if (args[index] == null) {
                args[index] = " ";
            }
            result = result.replaceFirst("%s", args[index]);
            index++;
        }
        return result;
    }

    public static double round(double v, int scale) {

        String temp = "###0.";
        for (int i = 0; i < scale; i++) {
            temp += "0";
        }
        return Double.valueOf(new java.text.DecimalFormat(temp).format(v));
    }

    public static double round(double v, int scale, boolean falg) {

        String temp = "###0.0";
        String tempScale = "1";
        for (int i = 0; i < scale; i++) {
            temp += "0";
            tempScale += "0";
        }
        double count = Double.parseDouble(tempScale);
        v = v * count;
        v = java.lang.Math.round(v) / count;
        return Double.valueOf(new java.text.DecimalFormat(temp).format(v));
    }

    /**
     * 传入两个list 分别去掉重复的list
     * 
     * @param map key为one two 表示第一个和第二个list
     * @return
     */
    public static Map<String, List<String>> removeDuplicate(Map<String, List<String>> map) {
        boolean b = false;
        List<String> list1 = map.get("one");
        List<String> list2 = map.get("two");
        for (int i = 0, n = list1.size(); i < n; i++) {
            for (int j = 0, m = list2.size(); j < m; j++) {
                if (list2.get(j).equals(list1.get(i))) {
                    list2.remove(j);
                    list1.remove(i);
                    i = n;
                    b = true;
                    break;
                }
            }
        }
        map.put("one", list1);
        map.put("two", list2);
        if (b) {
            return removeDuplicate(map);
        } else {
            return map;
        }
    }

    /**
     * 传入两个list 得到list2的去掉重复后的list
     * 
     * @param list1
     * @param list2
     * @return
     */
    public static List<String> delDuplicate(List<String> list1, List<String> list2) {
        boolean b = false;
        for (int i = 0, n = list1.size(); i < n; i++) {
            for (int j = 0, m = list2.size(); j < m; j++) {
                if (list2.get(j).equals(list1.get(i))) {
                    list2.remove(j);
                    list1.remove(i);
                    i = n;
                    b = true;
                    break;
                }
            }
        }
        if (b) {
            return delDuplicate(list1, list2);
        } else {
            return list2;
        }
    }

    /**
     * 移除重复的字符串
     * 
     * @param list
     * @return
     */
    public static List<String> removeDuplicate(List<String> list) {
        HashSet<String> h = new HashSet<String>(list);
        list.clear();
        list.addAll(h);

        return list;
    }

    /**
     * 合并两个list 并除去重复
     * 
     * @param list
     * @param list2
     * @return
     */
    public static ArrayList<String> removeDuplicate(ArrayList<String> list, ArrayList<String> list2) {
        HashSet<String> hashSet = new HashSet<String>(list);
        hashSet.addAll(list2);
        list.clear();
        list.addAll(hashSet);
        return list;
    }

    /**
     * 取得excel单元格字符串值
     * 
     * @param cell
     * @return
     */
    public static String getStringValue(HSSFCell cell) {
        return null != cell ? cell.getRichStringCellValue().toString().trim().toUpperCase() : "";
    }

    /**
     * 取得excel单元格long值
     * 
     * @param cell
     * @return
     */
    public static long getLongValue(HSSFCell cell) {
        return null != cell ? (long)cell.getNumericCellValue() : 0;
    }

    /**
     * 取得excel单元格的字符串值
     * 
     * @param cell
     * @return
     */
    public static String getStringCellValue(HSSFCell cell) {
        String strCell = "";
        if (cell == null) {
            return strCell;
        }
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING:
                strCell = cell.toString();
                break;
            case HSSFCell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    /**
                     * <pre>
                     * strCell = DateUtil.formatDate(cell.getDateCellValue(), 1);
                     * </pre>
                     */
                    strCell = formatDate(cell.getDateCellValue());
                } else {
                    java.text.DecimalFormat df = new java.text.DecimalFormat("0");
                    strCell = String.valueOf(df.format(cell.getNumericCellValue()));
                }
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                strCell = String.valueOf(cell.getBooleanCellValue());
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                strCell = "";
                break;
            default:
                strCell = "";
                break;
        }
        return strCell;
    }

    /**
     * 默认格式化日期
     */
    public static String formatDate(java.util.Date date) {
        return DateUtil.format(date, DateUtil.yyyyMMddSpt);
    }

    /**
     * 取得excel单元格字符串值
     * 
     * @param strCellValue
     * @return
     */
    public static String getStringValue(String strCellValue) {
        return strCellValue;
    }

    /**
     * 取得excel单元格long值
     * 
     * @param strCellValue
     * @return
     */
    public static long getLongValue(String strCellValue) {
        return StringUtils.isEmpty(strCellValue) ? 0 : new Long(strCellValue);
    }

    /**
     * 
     * @param str
     * @return
     */
    public static Double parseDouble(String str) {
        if (StringUtil.isEmpty(str)) {
            return 0.0;
        }
        try {
            return Double.parseDouble(str);
        } catch (Exception ex) {
            return 0.0;
        }
    }

    /**
     * 判断是否为全数字 正整数
     * 
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        return NUMBER_PATTERN.matcher(str).find();
    }

    /**
     * 判断是否为数字类型 包括带小数点
     * 
     * @param str
     * @return
     */
    public static boolean isNumberAll(String str) {
        return POINT_PATTERN.matcher(str).find();
    }

    public static boolean contains(String str, char searchChar) {
        return StringUtils.contains(str, searchChar);
    }

    public static boolean contains(String str, String searchStr) {
        return StringUtils.contains(str, searchStr);
    }

    public static String getText(String str) {
        if (str == null) {
            return ("");
        }
        if ("".equals(str)) {
            return ("");
        }
        // 建立一个StringBuffer来处理输入数据
        StringBuffer buf = new StringBuffer(str.length() + 6);
        char ch = '\n';
        for (int i = 0; i < str.length(); i++) {
            ch = str.charAt(i);
            if (ch == '\r') {
                buf.append(" ");
            } else if (ch == '\n') {
                buf.append(" ");
            } else if (ch == '\t') {
                buf.append("    ");
            } else if (ch == ' ') {
                buf.append(" ");
            } else if (ch == '\'') {
                buf.append("\\'");
            } else if (ch == '\"') {
                buf.append("\\\"");
            } else if (ch == '&') {
                buf.append("&amp;");
            } else if (ch == '<') {
                buf.append("&lt;");
            } else if (ch == '>') {
                buf.append("&gt;");
            } else {
                buf.append(ch);
            }
        }
        return buf.toString();
    }

    /**
     * 判断字符串是否为空
     * 
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return (str == null || "".equals(str.trim()));
    }

    /**
     * 判断字符串是否为非空
     * 
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static int parseInt(String str, int defaultVal) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return defaultVal;
        }
    }

    /**
     * 字符串空格处理
     * 
     * @param value
     * @return
     */
    public static String trim(String value) {
        return value == null ? "" : value.trim();
    }

    public static final String encode(String str) {
        String encodedStr = str;
        try {
            encodedStr = URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            // ignored
        }
        return encodedStr;
    }
    /**
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     * ems 收货地址管理，我的收货地址，添加、编辑收货地址 ems 收货地址管理，我的收货地址，添加、编辑收货地址
     */
}
