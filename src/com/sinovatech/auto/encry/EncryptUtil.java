package com.sinovatech.auto.encry;

import com.sinovatech.auto.encry.anotation.Encrypt;
import com.sinovatech.auto.excel.PoiReadExcelBean;
import com.sinovatech.auto.excel.PoiReadExcelBeanImpl;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * hibernate解密加密工具类
 *
 * @author wujiajun mail:wujiajun@sinovatech.com QQ:352181949 2017年6月21日下午2:48:45
 */
public class EncryptUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(EncryptUtil.class);

    /**
     * 获取带有Encrypt注解的属性列表(加密)
     *
     * @param clazz
     * @return if exception happend, return empty list.
     */
    public static List<Field> getEncryptFielsFromClazz(Class<?> clazz) {
        List<Field> fieldList = new ArrayList<Field>();

        Field[] fields = clazz.getDeclaredFields();
        if (fields != null) {
            for (Field field : fields) {
                if (canNotEncrypt(field)) {
                    continue;
                }

                Encrypt encrypt = field.getAnnotation(Encrypt.class);
                if (encrypt != null) {
                    if (encrypt.isEncrypt()) {
                        fieldList.add(field);
                    }
                }
            }
        }

        return fieldList;
    }

    /**
     * @param obj
     * @return
     * @throws NullPointerException if the given obj is null
     * @see #getEncryptFielsFromClazz(Class) 加密
     */
    public static List<Field> getEncryptFileds(Object obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
        // 如果当前类属性获取不到目标字段，则从父类中再处理一次
        List<Field> fileList = getEncryptFielsFromClazz(obj.getClass());
        if (fileList == null || fileList.size() == 0) {
            fileList = getEncryptFielsFromClazz(obj.getClass().getSuperclass());
        }
        return fileList;
    }

    /**
     * 获取带有Encrypt注解的属性列表(解密)
     *
     * @param clazz
     * @return if exception happend, return empty list.
     */
    public static List<Field> getUnEncryptFielsFromClazz(Class<?> clazz) {
        List<Field> fieldList = new ArrayList<Field>();

        Field[] fields = clazz.getDeclaredFields();
        if (fields != null) {
            for (Field field : fields) {
                if (canNotEncrypt(field)) {
                    continue;
                }

                Encrypt encrypt = field.getAnnotation(Encrypt.class);
                if (encrypt != null) {
                    if (encrypt.isUnEncrypt()) {
                        fieldList.add(field);
                    }
                }
            }
        }

        return fieldList;
    }

    /**
     * @param obj
     * @return
     * @throws NullPointerException if the given obj is null
     * @see #getEncryptFielsFromClazz(Class) 解密
     */
    public static List<Field> getUnEncryptFileds(Object obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
        // 如果当前类属性获取不到目标字段，则从父类中再处理一次
        List<Field> fileList = getUnEncryptFielsFromClazz(obj.getClass());
        if (fileList == null || fileList.size() == 0) {
            fileList = getUnEncryptFielsFromClazz(obj.getClass().getSuperclass());
        }
        return fileList;
    }

    /**
     * 查找属性对应的setter方法
     *
     * @param field
     * @param obj
     * @return
     * @throws NullPointerException if the field or obj is null
     */
    public static Method getSetter(Field field, Object obj) {
        if (field == null || obj == null) {
            throw new NullPointerException();
        }

        String fieldName = field.getName();
        fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        fieldName = "set" + fieldName;

        try {
            return obj.getClass().getMethod(fieldName, field.getType());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 查找属性对应的getter方法
     *
     * @param field
     * @param obj
     * @return
     * @throws NullPointerException if the field or obj is null
     */
    public static Method getGetter(Field field, Object obj) {
        if (field == null || obj == null) {
            throw new NullPointerException();
        }

        String fieldName = field.getName();
        fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        fieldName = "get" + fieldName;

        try {
            return obj.getClass().getMethod(fieldName);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 加密，加密后的密文前后有会中括号（[]）包括
     *
     * @param field
     * @param entity
     * @return 属性不支持加密返回原值
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static Object encrypt(Field field, Object entity)
        throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        if (canNotEncrypt(field)) {
            return null;
        }

        Method getterMethod = EncryptUtil.getGetter(field, entity);
        Object value = getterMethod.invoke(entity);
        if (value != null && !EncryptUtil.hasEncryptMark(value.toString())) {

            String encryptStr = "";
            try {
                encryptStr
                    = EncryptionUtil.byte2hex(EncryptionUtil.encode(value.toString().getBytes(EncryptionUtil.CHAR_CODE),
                        EncryptionUtil.KEY.getBytes(EncryptionUtil.CHAR_CODE)));
                encryptStr = ("[" + encryptStr + "]");
            } catch (Exception e) {
                LOGGER.info("value" + value.toString() + " encrypt is error" + e.getMessage());
            }

            return encryptStr;
        }

        return value;
    }

    /**
     * 加密（提供流程中字段加密处理），加密后的密文前后有会中括号（[]）包括
     *
     * @param value
     * @return 属性不支持加密返回原值
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static String encryptForStr(String value) {
        if (value != null && !EncryptUtil.hasEncryptMark(value.toString())) {

            String encryptStr = "";
            try {
                encryptStr
                    = EncryptionUtil.byte2hex(EncryptionUtil.encode(value.toString().getBytes(EncryptionUtil.CHAR_CODE),
                        EncryptionUtil.KEY.getBytes(EncryptionUtil.CHAR_CODE)));
                encryptStr = ("[" + encryptStr + "]");
            } catch (Exception e) {
                LOGGER.info("value" + value.toString() + " encrypt is error" + e.getMessage());
            }

            return encryptStr;
        }

        return value;
    }

    /**
     * 解密
     *
     * @param field
     * @param entity
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static Object decrypt(Field field, Object entity)
        throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        if (canNotEncrypt(field)) {
            return null;
        }

        Method getterMethod = EncryptUtil.getGetter(field, entity);
        Object value = getterMethod.invoke(entity);
        if (value != null) {
            // 对于有加密标记的值才会进行解密
            if (EncryptUtil.hasEncryptMark(value.toString())) {

                String decryStr = "";
                try {
                    String tag = value.toString().replaceAll("\\[", "");
                    tag = tag.replaceAll("\\]", "");
                    decryStr = new String(EncryptionUtil.decode(EncryptionUtil.hex2byte(tag),
                        EncryptionUtil.KEY.getBytes(EncryptionUtil.CHAR_CODE)), EncryptionUtil.CHAR_CODE);
                } catch (Exception e) {
                    LOGGER.info("value" + value.toString() + " decrypt is error" + e.getMessage());
                }
                return decryStr;
            } else {
                return value;
            }
        }

        return null;
    }

    /**
     * 解密(单个字符串用于set方法)
     *
     * @param value
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static String decryptForSet(String value) {
        if (value != null) {
            // 对于有加密标记的值才会进行解密
            if (EncryptUtil.hasEncryptMark(value.toString())) {
                String decryStr = "";
                try {
                    String tag = value.toString().replaceAll("\\[", "");
                    tag = tag.replaceAll("\\]", "");
                    decryStr = new String(EncryptionUtil.decode(EncryptionUtil.hex2byte(tag),
                        EncryptionUtil.KEY.getBytes(EncryptionUtil.CHAR_CODE)), EncryptionUtil.CHAR_CODE);
                } catch (Exception e) {
                    LOGGER.info("value" + value.toString() + " decryptForSet is error" + e.getMessage());
                }
                return decryStr;
            } else {
                return value;
            }
        }
        return null;
    }

    /**
     * 判断是否支持加密
     *
     * @param field
     * @return 支持加密返回true
     */
    public static boolean canEncrypt(Field field) {
        return (String.class.equals(field.getType()));
    }

    /**
     * 判断是否支持加密
     *
     * @param field
     * @return 不支持加密返回true
     */
    public static boolean canNotEncrypt(Field field) {
        return !canEncrypt(field);
    }

    /**
     * 判断是否有需要加密的注解
     *
     * @param field
     * @return 有加密注解返回true
     */
    public static boolean hasEncryptAnnotation(Field field) {
        Encrypt encryptAnnotation = field.getAnnotation(Encrypt.class);

        return (encryptAnnotation != null);
    }

    /**
     * 判断密文中是否有加密标记（使用中括号[]将加密后的字符串包括起来）
     *
     * @param encryptStr
     * @return
     */
    public static boolean hasEncryptMark(String encryptStr) {
        if (encryptStr != null && encryptStr.length() > 2) {
            return (encryptStr.startsWith("[") && encryptStr.endsWith("]"));
        }

        return false;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(EncryptUtil.encryptForStr("安徽省芜湖市弋江区dfgdf "));
        System.out.println(EncryptUtil.decryptForSet("[37C075A755FEE42D249DB1CCFE51501D]"));

        String decryStr = new String(EncryptionUtil.decode(EncryptionUtil.hex2byte("37C075A755FEE42D249DB1CCFE51501D"),
            EncryptionUtil.KEY.getBytes(EncryptionUtil.CHAR_CODE)));
        System.out.println(decryStr);
        generateEncryData();
    }

    /**
     * 自动生成sql脚本方法
     */
    public static void generateEncryData() {
        // sql模板路径
        String updateSqlTemlatePath = "D://updateData.txt";
        // 原始数据路径
        String originDataPath = "D://originData.xls";
        // 生成结果存放路径
        String resultDataPath = "D://sqlResultData.txt";
        List<String> dataList = new ArrayList<String>();
        try {
            InputStream inputStream = new FileInputStream(updateSqlTemlatePath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            // sql模板
            String sqlTemlate = "";
            StringBuilder builder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);
            }
            sqlTemlate = builder.toString();
            System.out.println(sqlTemlate);
            PoiReadExcelBean poiReadExcelBean = new PoiReadExcelBeanImpl();
            List list = poiReadExcelBean.readExcel(originDataPath, 1);
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    Map<?, ?> map = (Map<?, ?>)list.get(i);
                    // id
                    String id = (String)map.get(0);
                    // buyerAddr
                    String buyerAddr = (String)map.get(1);
                    buyerAddr = EncryptUtil.encryptForStr(buyerAddr);
                    System.out.println(id + ":" + buyerAddr);
                    String sqlTem = sqlTemlate;
                    // sqlTemlate.replace()
                    sqlTem = sqlTem.replace("#buyer_addr#", "'" + buyerAddr + "'").replace("#id#", "'" + id + "'");
                    dataList.add(sqlTem);
                }
            }
            System.out.println(dataList);
            FileUtils.writeLines(new File(resultDataPath), dataList);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
