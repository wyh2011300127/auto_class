package com.sinovatech.auto;

import java.io.IOException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import cn.mochasoft.cipher.UniDecrypt;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author zhoukun
 * @date 2018年5月14日
 */
public class CodecUtils {
    private final static String DES = "DES";
    /** 加密解决的key */
    public final static String DES_KEY = "5S8P01D0B155C3HI00N0A00M01B9IE21";

    /**
     * 将表示16进制值的字符串转换为byte数组， 和public static String byteArr2HexStr(byte[] arrB) 互为可逆的转换过程
     * 
     * @param strIn 需要转换的字符串
     * @return 转换后的byte数组
     * @throws Exception 本方法不处理任何异常，所有异常全部抛出
     */
    public static byte[] hexStr2ByteArr(String strIn) throws Exception {
        byte[] arrB = strIn.getBytes();
        int iLen = arrB.length;
        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen; i = i + 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[i / 2] = (byte)Integer.parseInt(strTmp, 16);
        }
        return arrOut;
    }

    /**
     * Description base64解密
     * 
     * @param data
     * @param key
     * 
     * @return
     * @throws Exception
     */
    public static byte[] decodecOnBase64(String s) {

        try {
            return new BASE64Decoder().decodeBuffer(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * Description base64加密
     * 
     * @param data
     * @param key
     * 
     * @return
     * @throws Exception
     */
    public static String encodeOnBase64(byte[] s) {
        return new BASE64Encoder().encode(s);
    }

    /**
     * Description 根据键值进行加密
     * 
     * @param data
     * @param key 加密键byte数组
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(DES);

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }

    /**
     * Description 根据键值进行解密
     * 
     * @param data
     * @param key 加密键byte数组
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(DES);

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }

    /**
     * 将byte数组转换为表示16进制值的字符串， 如：byte[]{8,18}转换为：0813， 和public static byte[] hexStr2ByteArr(String strIn) 互为可逆的转换过程
     * 
     * @param arrB 需要转换的byte数组
     * @return 转换后的字符串
     * @throws Exception 本方法不处理任何异常，所有异常全部抛出
     */
    public static String byteArr2HexStr(byte[] arrB) throws Exception {
        int iLen = arrB.length;
        StringBuffer sb = new StringBuffer(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];
            while (intTmp < 0) {
                intTmp = intTmp + 256;
            }
            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {

        /**
         * <pre>
         * String xml
         *     = "<agentCode>b2btest001</agentCode><agentName>京东方</agentName><agentIdNo>3412546212547542</agentIdNo><isSpdbAcnt>0</isSpdbAcnt><payAcntCode>京东方</payAcntCode><payAcntType>1</payAcntType><payAcntName>京东方</payAcntName><openBankCode>56235462</openBankCode><chargeLimit>50000.0</chargeLimit><beginDate>20171113</beginDate><endDate>20181113</endDate><linkAddr>安徽省合肥市</linkAddr><linkedTel>13865875894</linkedTel><reserved></reserved>";
         * String codeString = new String(encodeBodyInfo(xml.getBytes("utf-8")));
         * System.out.println(codeString);
         * System.out.println(new String(dencodeBodyInfo(codeString)));
         * </pre>
         */
        String dessString = "qz6iJYhklIM9ZIvYJzNaNueNAYz8RmN7LdOodawdvrZzFLq229Mk8u5s+vJmdnMQ+WnKiEhvoACM\n"
            + "Xpu3U6eLEvE9y+hvgoJV6WGEPhkl5VvTBiZ5GmhFdg==";
        String t = new String(dencodeBodyInfo(dessString), "gbk");
        String str = "mf44stor69o1a9im51qe392n5tqe19ivgpdcr2fo1fa44";
        System.out.println(t);
        System.out.print(UniDecrypt.decrypt(str));

    }

    public static byte[] encodeBodyInfo(byte[] bodyInfo) throws Exception {
        byte[] b = CodecUtils.encrypt(bodyInfo, DES_KEY.getBytes("UTF-8"));
        return CodecUtils.encodeOnBase64(b).getBytes();
    }

    public static byte[] dencodeBodyInfo(String bodyInfo) throws Exception {
        byte[] b = CodecUtils.decodecOnBase64(bodyInfo);
        return CodecUtils.decrypt(b, DES_KEY.getBytes("UTF-8"));
    }
}

/*
 * Date now = new Date(); Element root =
 * DocumentHelper.createElement("root"); Element head =
 * root.addElement("head"); head.addElement("reqflag").addText("0");//
 * 请求方 head.addElement("tr_code").addText("80001");// 交易代码
 * head.addElement("tr_acdt").addText(DateUtil.date2Str(now,
 * "yyyyMMdd"));// 请求日期YYYYMMDD
 * head.addElement("tr_time").addText(DateUtil.date2Str(now,
 * "HHmmss"));// 请求时间HHMMSS head.addElement("reserved").addText("");//
 * 保留字段 Element body = root.addElement("body"); // 拼接加密字符串放到body里面去
 * StringBuilder secretReqXml = new StringBuilder();
 * secretReqXml.append("<agentCode>b2btest01</agentCode>");
 * secretReqXml.append("<agentName>测试用户</agentName>");
 * secretReqXml.append("<agentIdNo>34242519900717475X</agentIdNo>");
 * secretReqXml.append("<isSpdbAcnt>0</isSpdbAcnt>");
 * secretReqXml.append("<payAcntCode>62220213568753269</payAcntCode>");
 * secretReqXml.append("<payAcntType>0</payAcntType>");
 * secretReqXml.append("<payAcntName>周昆</payAcntName>");
 * secretReqXml.append("<openBankCode>1120356</openBankCode>");
 * secretReqXml.append("<chargeLimit>50000</chargeLimit>");
 * secretReqXml.append("<beginDate>" + DateUtil.date2Str(now,
 * "yyyyMMdd") + "</beginDate>"); secretReqXml.append("<endDate>" +
 * DateUtil.date2Str(DateUtil.getIntervalYear(now, false, 1),
 * "yyyyMMdd") + "</endDate>");
 * secretReqXml.append("<linkAddr>安徽省合肥市高新区海亮九玺</linkAddr>");
 * secretReqXml.append("<linkedTel>18225859325</linkedTel>");
 * secretReqXml.append("<reserved></reserved>");
 * System.out.println("浦发代收付签约发送加密前body报文:" + secretReqXml.toString());
 * String encryptReqXml =
 * CodecUtils.byteArr2HexStr(CodecUtils.encrypt(secretReqXml
 * .toString().getBytes("UTF-8"),
 * CodecUtils.DES_KEY.getBytes("UTF-8")));
 * System.out.println("浦发代收付签约发送加密后body报文:" + encryptReqXml);
 * body.addText(encryptReqXml); // 加密字符串 // 3、调用接口给浦发银行推送报文 String
 * sendXml = root.asXML(); System.out.println("浦发代收付签约发送报文:" + sendXml);
 * // String receiveXml = SocketUtil.socketAsK(sendXml, socketIP,
 * socketPort); String receiveXml =
 * "<root><head><reqflag>0</reqflag><tr_code>80001</tr_code><tr_acdt>20171031</tr_acdt><tr_time>095222</tr_time>"
 * ; receiveXml += "<ans_code>0000</ans_code>"; receiveXml +=
 * "<ans_info>签约成功</ans_info>"; receiveXml += "<reserved></reserved>";
 * receiveXml += "</head><body>";
 * 
 * String secretResXml = ""; secretResXml +=
 * "<signStatus>0</signStatus>"; secretResXml +=
 * "<agentCode>b2btest01</agentCode>"; secretResXml +=
 * "<payAcntCode>62220213568753269</payAcntCode>"; secretResXml +=
 * "<payAcntName>周昆</payAcntName>"; secretResXml +=
 * "<reserved></reserved>";
 * 
 * // secretResXml ： 需要加密的body内报文字符串 String encryptResXml =
 * CodecUtils.byteArr2HexStr
 * (CodecUtils.encrypt(secretResXml.toString().getBytes("UTF-8"),
 * CodecUtils.DES_KEY.getBytes("UTF-8")));
 * 
 * receiveXml +=encryptResXml; receiveXml += "</body>"; receiveXml +=
 * "</root>"; System.out.println("浦发代收付签约接收报文:" + receiveXml); //
 * 4、解析浦发返回的报文
 * 
 * Document receiveDoc = DocumentHelper.parseText(receiveXml); //
 * 将字符串转为XML Element rootElement = receiveDoc.getRootElement(); Node
 * headNode = rootElement.selectSingleNode("head"); Node ansCodeNode =
 * headNode.selectSingleNode("ans_code"); // 应答编码 Node ansInfoNode =
 * headNode.selectSingleNode("ans_info"); // 应答信息
 * System.out.println("解析报文获得的ansCode=" + ansCodeNode.getText() +
 * "||ansInfoNode=" + ansInfoNode.getText()); Node bodyNode =
 * rootElement.selectSingleNode("body"); String bodyResEncryptXml =
 * bodyNode.getText(); System.out.println("浦发代收付签约接收报文body加密部分:" +
 * bodyResEncryptXml); // bodyResEncryptXml ： 需要解密的body内报文字符串 String
 * decryptBodyXml = new
 * String(CodecUtils.decrypt(CodecUtils.hexStr2ByteArr
 * (bodyResEncryptXml), CodecUtils.DES_KEY.getBytes("UTF-8")));
 * System.out.println("浦发代收付签约接收报文body解密内容:" + decryptBodyXml);
 */

/**
 * 
 * f647f7eea218aef5d4a0003ec7737237deb81a48409ff5201ddde1453e56fa57eda53b4da06b4641457f97dd80b8ac28d888ffb45c6a7c40e217cdea0dfb28d92ee3de4607fca8e118d000e412a99759756501efe89f0c0bc2f9b2a693871e1403215d88bd870e69770df65de85d01d8141dc3281cd6bfde2f57c78d132e5d8bed92e2d4d8f0524e6ee5206b2ebeda452c633b443488ec5f494e91bb307c923ba18d64f8bb1b4ee9c8f3a7d6a0d8c16dd24c4d43908e1c7ab2eac511e1bee150e1a2d293f6f84a40681b778c3b20c6bd2c633b443488ec5f494e91bb307c923bc79a440e8309799d934e9026e1d762296b5bea61ff306e4e872bea224e5b6ff94688cab848380df99ff69ab87e0cad3a846d67544d9520c1e68c95ef2483f2529313b12e7ef4a82e529207256f5df806912ef45c6505f3b4682f0a3c31e56b98981a58ebebffdff53204d2491d9636ac82a3e5505d0f0e16c7c73998c3c800836c37724a20101177e9f1d9366a707f7e21ed072bae6c0efa728d8aa532da7c6d9c8131e01fd740912563fc4de87a74e94e4973a8464fa8f342b18fd609b62e20cf1ead9be76d71f593d10e5d634c8ef8013e7914bc057d11d2a7bd182764c468bdd23967aef8b49b
 * 
 */
