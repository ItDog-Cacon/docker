package com.byzoro.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Description:
 * @author 李尚伟 2019/7/4 20:30
 */
public class BASE64Util {

    /**
     * Description: 将字符串进行BASE64编码
     * @param str 要编码的字符串
     * @return java.lang.String
     * @author 李尚伟 2019/7/4 20:31
     */
    public static String encode(String str) {
        return encode(str.getBytes(StandardCharsets.UTF_8));
    }

    public static String encode(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * Description: 将字符串进行反编码
     * @param str 要反编码的字符串
     * @return java.lang.String
     * @author 李尚伟 2019/7/4 20:36
     */
    public static String decode(String str) {
        if (str == null) {
            return null;
        }
        return new String(Base64.getDecoder().decode(str), StandardCharsets.UTF_8);
    }

    public static byte[] decodeByte(String str) {
        if (str == null) {
            return null;
        }
         return Base64.getMimeDecoder().decode(str);
    }

    public static void main(String[] args) {
        String a = "123456";
        String b = encode(a);
        System.out.println(b);
        System.out.println(decode(b));
    }
}
