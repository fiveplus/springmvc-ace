package com.fiveplus.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * DES自定义KEY加密
 * @author Mr.Zhang
 *
 */
public class DesUtil {
	/**
	 * 加密方式为DES
	 */
	private final static String DES = "DES";
	/**
	 * 秘钥字符串
	 */
	private final static String KEY = "CRM!@#$8859";
	/**
	 * get请求地址栏转换
	 */
	private final static List<KeyValue> TESHU_URL= new ArrayList<KeyValue>(){{
		add(new KeyValue("[+]", "%2B"));
		//add(new KeyValue("[ ]", "%20"));
		//add(new KeyValue("[/]", "%2F"));
		//add(new KeyValue("[?]", "%3F"));
		//add(new KeyValue("[%]", "%25"));
		//add(new KeyValue("[#]", "%23"));
		//add(new KeyValue("[&]", "%26"));
		//add(new KeyValue("[=]", "%3D"));
		
	}};
	
	 /**
     * Description 根据键值进行加密
     * @param data 
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    public static String encrypt(String data, String key) throws Exception {
        byte[] bt = encrypt(data.getBytes(), key.getBytes());
        String strs = new BASE64Encoder().encode(bt);
        return strs;
    }
 
    /**
     * Description 根据键值进行解密
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws IOException
     * @throws Exception
     */
    public static String decrypt(String data, String key) throws IOException,
            Exception {
        if (data == null)
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] buf = decoder.decodeBuffer(data);
        byte[] bt = decrypt(buf,key.getBytes());
        String str = new String(bt,"utf-8");
        return str;
    }
 
    /**
     * Description 根据键值进行加密
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
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
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
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
	 * 密码加密
	 * @param password
	 * @return
	 * @throws Exception 
	 */
	public static String encryptPassword(String password) throws Exception{
		return encrypt(password,KEY);
	}
	
	
	/**
	 * 路径加密
	 * @param path 真实路径
	 * @return 返回加密路径
	 * @throws Exception 
	 */
	public static String encryptPath(String path) throws Exception{
		String temp = encrypt(path,KEY).replaceAll("\r\n", "");
		for(KeyValue ky:TESHU_URL){
			temp = temp.replaceAll(ky.getKey(), ky.getValue().toString());
		}
		return temp;
	}
	
	 /**
	  * 路径解密
	  * @param path 加密路径
	  * @return 返回解密后的路径
	 * @throws Exception 
	 * @throws IOException 
	  */
	public static String decryptPath(String path) throws IOException, Exception{
		return decrypt(path,KEY).replaceAll("\r\n", "");
	}
	
	
	
	
	public static void main(String[] args) throws Exception {
		//System.out.println(encryptPath("/home/admin/web-deploy/apache-tomcat-7.0.33/webapps/ROOT/uploads/百桥通讯录.doc"));
		//System.out.println(decrypt("1LohRx9CCERxVW6wQY2M8ANKZ9Rc5l2Jv9mbWZmgtsObyU7%2BdvqdzNIQKlCCEOAum6lH%2Bt7v%2BeETC1cFcpp8TGkHOgk3tmHMaLyFgzZiKCs=",KEY));
		//String ming = DesUtil.decryptPath("QQy02SECMVilRSBA/AgAXmIqvwpfTM94");
		//System.out.println(ming);
		//String mi = encryptPassword("123456");
		//String mi = decryptPath("pKCuVZLEQncpRP4h8qGj5DXehkmV1YY5Zwd8Mazi9lSaU1F2sfFtfYnQ+rBPXDscNTLDr9I9CHTF2c3SOaVj+Q==");
		//System.out.println(mi);
		//System.out.println(mi);14yPBpir6M8fVCHK5Ia4
		//String mima = encryptPassword("123456aA_aabbbccc");
		//String mima2 = encryptPassword("sdfsdfsdf@#$@#$111");
		//String mima1 = encryptPassword("sdfsdfsdf@#$@#$111");
		//System.out.println(mima2.equals(mima1));
	}
	
	
	
}
