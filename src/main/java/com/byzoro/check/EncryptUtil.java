package com.byzoro.check;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Description:
 * @author 李尚伟-网络 2019/7/5 16:33
 */
public class EncryptUtil {
    public static String offset = "qwerqwerqwerqwer";

    public static String encrypt(byte[] bytes, String sKey, String encryptMode) {
        String encryptedStr = null;
        if (encryptMode == null) {
            encryptedStr = Base64.getEncoder().encodeToString(bytes);
        } else {
            encryptedStr = AESEncrypt(bytes, sKey);
        }
        return encryptedStr;
    }

    private static String AESEncrypt(byte[] bytes, String sKey) {
        if (sKey == null) {
            return null;
        }
        // 判断Key是否为16位
        if (sKey.length() != 16) {
            return null;
        }
        String encryptedStr = null;
        try {
            byte[] raw = sKey.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            // "算法/模式/补码方式"
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            // 使用CBC模式，需要一个向量iv，可增加加密算法的强度
            IvParameterSpec iv = new IvParameterSpec(offset.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(bytes);
            encryptedStr = Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
            encryptedStr = null;
        }
        return encryptedStr;
    }

    public static byte[] decrypt(String sSrc, String sKey, String encryptMode) {
        byte[] decryptBytes;
        if (encryptMode == null) {
            decryptBytes = Base64.getDecoder().decode(sSrc);
        } else {
            decryptBytes = AESDecrypt(sSrc, sKey);
        }
        return decryptBytes;
    }

    private static byte[] AESDecrypt(String sSrc, String sKey) {
        // 判断Key是否正确
        if (sKey == null) {
            System.out.print("Key为空null");
            return null;
        }
        // 判断Key是否为16位
        if (sKey.length() != 16) {
            System.out.print("Key长度不是16位");
            return null;
        }
        byte[] decryptBytes;
        try {
            byte[] raw = sKey.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(offset.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = Base64.getDecoder().decode(sSrc);
            decryptBytes = cipher.doFinal(encrypted1);
        } catch (Exception ex) {
            ex.printStackTrace();
            decryptBytes = null;
        }
        return decryptBytes;
    }

    public static void main(String[] args) {

    }
}
