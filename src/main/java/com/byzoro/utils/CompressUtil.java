package com.byzoro.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.*;

/**
 * Description:
 * @author 李尚伟 2019/7/4 21:05
 */
public class CompressUtil {

    public static byte[] zip(byte[] data, String compressMode) {
        if (compressMode != null) {
            byte[] b = null;
            try {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ZipOutputStream zip = new ZipOutputStream(bos);
                ZipEntry entry = new ZipEntry("zip");
                entry.setSize(data.length);
                zip.putNextEntry(entry);
                zip.write(data);
                zip.closeEntry();
                zip.close();
                b = bos.toByteArray();
                bos.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return b;
        } else {
            return data;
        }
    }

    public static byte[] unZip(byte[] data, String compressMode) {
        byte[] b = null;
        if (compressMode == null) {
            b = data;
        } else {
            try {
                ByteArrayInputStream bis = new ByteArrayInputStream(data);
                ZipInputStream zip = new ZipInputStream(bis);
                while (zip.getNextEntry() != null) {
                    byte[] buf = new byte[1024];
                    int num = -1;
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    while ((num = zip.read(buf, 0, buf.length)) != -1) {
                        baos.write(buf, 0, num);
                    }
                    b = baos.toByteArray();
                    baos.flush();
                    baos.close();
                }
                zip.close();
                bis.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return b;
    }

    public static byte[] zlib(String str) {
        byte[] b = null;
        try {
            byte[] bytes = str.getBytes();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            DeflaterOutputStream dout = new DeflaterOutputStream(out);
            dout.write(bytes);
            dout.close();
            b = out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    public static String unZlib(byte[] bytes) {
        byte[] b = null;

        Inflater decompresser = new Inflater();
        decompresser.reset();
        decompresser.setInput(bytes);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            byte[] buffer = new byte[1024*1024];
            while (!decompresser.finished()) {
                int i = decompresser.inflate(buffer);
                out.write(buffer, 0, i);
            }
            b = out.toByteArray();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        decompresser.end();
        return new String(b);
    }

    public static String getUnZlib(String encdat){
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            InflaterOutputStream zos = new InflaterOutputStream(bos);
            zos.write(BASE64Util.decodeByte(encdat));
            zos.close();
            return new String(bos.toByteArray());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

//    public static void main(String[] args) {
//        String str = "eJxtkM1OwzAQhF+l2nOK7DjOj2+oElIlhCoIHEA9mGahEbEdbKdSVfXdWQfKBa7fjD07cwLrOlx3oIDlTDacCcjAOHvbhwjq5QRhjxjb44igFlBwyBbQ6aif0CfAr1gigw7x5rHtzezKGW+WrFryquWlYlIV9fPFtfnXRZZGCXKdtxlEDPEh6u/4lHVJtxjTN58T+uPKxoRqVpe5kHniYdrdYxh/FCkKWclKJMVjcMMBrw/vbZLKKkH9ap03eljbN0eUsvSA/rfq/HAmd5MhUORSnLfpvs4Z3dvVHncfswS8nFcLdPMGfe/SmoLRMBCpK1UxI5E/q4iS+n4BBPRsbQ==";
//        byte[] bytes = Base64.getDecoder().decode(str);
//        String unzip = unZlib(bytes);
//        System.out.println(unzip);
//    }

}
