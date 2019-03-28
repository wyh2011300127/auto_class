package com.sinovatech.auto.encry;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 * @author nieweiren@sinovatech.com
 * @since 2012-10-15
 */
public class EncryptionUtil {
	private static String Algorithm = "DES";
	public final static String KEY ="yhxxshop";
	public final static String CHAR_CODE="GBK";
	
	public static byte[] getKey() throws Exception {
		KeyGenerator keygen = KeyGenerator.getInstance(Algorithm);
		SecretKey deskey = keygen.generateKey();
		return deskey.getEncoded();
	}

	public static byte[] encode(byte[] input, byte[] key) throws Exception {
		SecretKey deskey = new javax.crypto.spec.SecretKeySpec(key, Algorithm);
		Cipher c1 = Cipher.getInstance(Algorithm);
		c1.init(Cipher.ENCRYPT_MODE, deskey);
		byte[] cipherByte = c1.doFinal(input);
		return cipherByte;
	}

	public static byte[] decode(byte[] input, byte[] key) throws Exception {
		SecretKey deskey = new javax.crypto.spec.SecretKeySpec(key, Algorithm);
		Cipher c1 = Cipher.getInstance(Algorithm);
		c1.init(Cipher.DECRYPT_MODE, deskey);
		byte[] clearByte = c1.doFinal(input);
		return clearByte;
	}

	public static byte[] md5(byte[] input) throws Exception {
		java.security.MessageDigest alg = java.security.MessageDigest
				.getInstance("MD5");
		// or "SHA-1"
		alg.update(input);
		byte[] digest = alg.digest();
		return digest;
	}

	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
		}
		return hs.toUpperCase();
	}

	public static byte[] hex2byte(String hex) throws IllegalArgumentException {
		if (hex.length() % 2 != 0) {
			throw new IllegalArgumentException();
		}
		char[] arr = hex.toCharArray();
		byte[] b = new byte[hex.length() / 2];
		for (int i = 0, j = 0, l = hex.length(); i < l; i++, j++) {
			String swap = "" + arr[i++] + arr[i];
			int byteint = Integer.parseInt(swap, 16) & 0xFF;
			b[j] = new Integer(byteint).byteValue();
		}
		return b;
	}

	public static String decodeStr(String s) {
		try {
			return new String(decode(hex2byte(s), "yhxxshop".getBytes(EncryptionUtil.CHAR_CODE)));
		} catch (IllegalArgumentException e) {
			return s;
		} catch (Exception e) {
			return s;
		}
	}

}
