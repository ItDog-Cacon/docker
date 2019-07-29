package com.byzoro.check;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Description:
 * @author 李尚伟 2019/7/4 19:55
 */
public class HashUtil {
    /**
     * 定义char数组,16进制对应的基本字符
     */
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
            'e', 'f'};

    /**
     * Description: hash加密
     * @param str       要加密的字符串
     * @param algorithm 加密算法，如"MD5", "SHA-256"
     * @return java.lang.String
     * @author 李尚伟-网络修改 2019/7/4 20:11
     */
    public static String getHashString(String str, String algorithm) {
            return getHashString(str.getBytes(), algorithm);
    }

    public static String getHashString(byte[] bytes, String algorithm) {
        if (algorithm != null) {
            MessageDigest messageDigest = null;
            try {
                messageDigest = MessageDigest.getInstance(algorithm);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return null;
            }
            messageDigest.update(bytes);
            return byteArray2HexString(messageDigest.digest());
        } else {
            return new String(bytes);
        }
    }

    /**
     * Description: 将byte数组转换成String
     * @param bytes hash加密后的数组
     * @return java.lang.String
     * @author 李尚伟-网络转 2019/7/4 20:09
     */
    private static String byteArray2HexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(HEX_DIGITS[(b & 0xf0) >> 4]).append(HEX_DIGITS[(b & 0x0f)]);
        }
        return sb.toString();
    }

    /**
     * 测试方法
     * @param args
     * @author sucb
     * @date 2017年7月26日下午5:11:50
     */
    public static void main(String[] args) {
        System.out.println(getHashString("123456", "SHA-256"));
    }

}
