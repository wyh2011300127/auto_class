package com.sinovatech.auto.des;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * 解密工具类
 * 
 * @author wangyuheng
 * 
 */
public class DESUtil {

	private static DESKeySpec dks = null;
	private static SecretKeyFactory keyFactory = null;
	private static SecretKey deskey = null;

	static {
		try {
            byte[] rawKeyData = "11111111".getBytes();
			dks = new DESKeySpec(rawKeyData);
			keyFactory = SecretKeyFactory.getInstance("DES");
			deskey = keyFactory.generateSecret(dks);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DESUtil() {

	}

	public static String byteArr2HexStr(byte[] arrB) throws Exception {
		int iLen = arrB.length;
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp;
			for (intTmp = arrB[i]; intTmp < 0; intTmp += 256) {
                ;
            }
			if (intTmp < 16) {
                sb.append("0");
            }
			sb.append(Integer.toString(intTmp, 16));
		}

		return sb.toString();
	}

	public static byte[] hexStr2ByteArr(String strIn) {
        byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;
        byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i += 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}

		return arrOut;
	}

	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0xff);
			if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
		}

		return hs.toUpperCase();
	}

	public static void generateKey(String algorithm) {
		try {
			KeyGenerator keygen = KeyGenerator.getInstance(algorithm);
			SecretKey deskey = keygen.generateKey();
			// System.out.println(deskey.getFormat());
		} catch (Exception exception) {
		}
	}

	public static byte[] encryt(String algorithm, String myinfo) {
        byte[] cipherByte = (byte[]) null;
		try {
			Cipher c1 = Cipher.getInstance(algorithm);
			c1.init(1, deskey);
			cipherByte = c1.doFinal(myinfo.getBytes());
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (Exception e3) {
			e3.printStackTrace();
		} finally {
		}
		return cipherByte;
	}

	public static String deEncryt(String algorithm, byte[] cipherByte) {
        byte[] clearByte = (byte[]) null;
		try {
			Cipher c1 = Cipher.getInstance(algorithm);
			c1.init(2, deskey);
			clearByte = c1.doFinal(cipherByte);
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (Exception e3) {
			e3.printStackTrace();
		} finally {
		}
		return new String(clearByte);
	}
/**
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 *ems 收货地址管理，我的收货地址，添加、编辑收货地址
 */
}
