package com.sinovatech.auto.des;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The Class PasswordUtil. 使用DES，DESUtil解密
 * 
 * @author wangzhe
 */
public class PasswordUtil {
    private static final Log log = LogFactory.getLog(PasswordUtil.class);

    /** 解密加密 */
    private static String key1 = "YHXWWLKJYXGS";

    private static String key2 = "ZFCHHYXFL10C";

    private static String key3 = "DES";

    private static String key1S = "YHXLOPSJYXGS";

    private static String key2S = "ZFCHLCPSX10C";

    private static String key3S = "DES";

    /**
     * Auth password.
     * <p>
     * 登录密码和数据库密码比较相等
     * </p>
     * 
     * @param password the password
     * @param dbPassword the db password
     * @return true, if successful
     * @throws Exception
     */
    public static boolean authPassword(String password, String dbPassword) throws Exception {

        password = decPassword(password);
        dbPassword = decDBPassword(dbPassword);

        if (dbPassword.equals(password)) {
            return true;
        }
        return false;
    }

    public static String encDBPassword(String dbPassword) throws Exception {
        return DESUtil.byteArr2HexStr(DESUtil.encryt(key3, dbPassword));
    }

    /**
     * .
     * <p>
     * 数据库里面的密码,解密
     * </p>
     * 
     * @param dbPassword the db password
     * @return the string
     * @throws Exception
     */
    public static String decDBPassword(String dbPassword) throws Exception {
        return DESUtil.deEncryt(key3, DESUtil.hexStr2ByteArr(dbPassword));
    }

    /**
     * Gen DBPassword.
     * <p>
     * 登录密码,解密
     * </p>
     * 
     * @param dbPassword the db password
     * @return the string
     * @throws Exception
     */
    public static String decPassword(String password) {
        return DES.strDec(password, key1, key2, key3);
    }

    /**
     * 
     * @param password
     * @return
     */
    public static String decPasswordSPD(String password) {
        return DES.strDec(password, key1S, key2S, key3S);
    }
}
