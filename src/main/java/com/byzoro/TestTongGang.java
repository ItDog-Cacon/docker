package com.byzoro;


import com.byzoro.check.BASE64Util;
import com.byzoro.check.HashUtil;
import com.byzoro.utils.CompressUtil;
import com.byzoro.utils.StringUtil;
import com.byzoro.utils.TestData;

public class TestTongGang {
    public static void main(String[] args) {
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("data","eJxtkM1OwzAQhF+l2nOK7DjOj2+oElIlhCoIHEA9mGahEbEdbKdSVfXdWQfKBa7fjD07cwLrOlx3oIDlTDacCcjAOHvbhwjq5QRhjxjb44igFlBwyBbQ6aif0CfAr1gigw7x5rHtzezKGW+WrFryquWlYlIV9fPFtfnXRZZGCXKdtxlEDPEh6u/4lHVJtxjTN58T+uPKxoRqVpe5kHniYdrdYxh/FCkKWclKJMVjcMMBrw/vbZLKKkH9ap03eljbN0eUsvSA/rfq/HAmd5MhUORSnLfpvs4Z3dvVHncfswS8nFcLdPMGfe/SmoLRMBCpK1UxI5E/q4iS+n4BBPRsbQ==");
//        jsonObject.put("randVal","rghwzjmnbuovp");
//        jsonObject.put("pwdHash","OGY4ZDFkNjA1YWFlMTE0MTk1M2IwNDhiMDc4ODM1ODQ3NDlhOGFlN2M3ODk3ZDhmZTYxMzIxMjQwNDdjMzk3OQ==");
//        jsonObject.toJSONString();
//        String s= "b'x\\x9c{\\xbak\\xfb\\xb3\\x19}\\xcfw-\\x02\\x00\"F\\x06\\xa6'";
//        byte[] bytes = s.getBytes();
//        byte[] zips = CompressUtil.unZip(bytes, "zip");
//        String s1 = new String(zips);
//        System.out.println(s1);
//        TestData.getXingTongYuan();
//        String encode = BASE64Util.encode("eJy1kkFLAzEQhf/LnLcwSau1exbEiwj2pHgI3bENNJllMh5k2f/uxC1YwV5kDbl8L4 PHN4AmTu676AFdM4hooMGuqDhSYNC zJAORDp9qMn6/hlfeUUYn54TxasaiC0Y mm4PqrIsx6a5K7oAcSaAdQ1nA8r4QJ/GZVwc6Jb9bGuZz03mCXQ6KJEcYGYukvuvHbjD 8eGbFX6SJc1SWeaVC xKVZP7v7oPS/Na3ePwHq1DPcnkOf/SOrw0U2 gjSeS63yXWksZENt3U1y2h2yxwvfB 665aRLvPMH4CVoPeTg==");
//        byte[] bytes = BASE64Util.decodeByte(encode);
//        String s = CompressUtil.unZlib(bytes);
//        System.out.println(s);
//        TestData.get1();
//        TestData.getSuccess();
//        String randVal="bUl2MtfLHBChKcqNx1jN";
//        String pwd="DzRkzsm7HNG5jgu6";
//        String s1 = pwd + randVal;
//        String hashString1 = HashUtil.getHashString(s1, "SHA-256");
//        String decode = BASE64Util.encode(hashString1);
//
//        byte[] bytes1 = BASE64Util.decodeByte("eJy1kkFLAzEQhf/LnLcwSau1exbEiwj2pHgI3bENNJllMh5k2f/uxC1YwV5kDbl8L4+PHN4AmTu676AFdM4hooMGuqDhSYNC+zJAORDp9qMn6/hlfeUUYn54TxasaiC0Y+mm4PqrIsx6a5K7oAcSaAdQ1nA8r4QJ/GZVwc6Jb9bGuZz03mCXQ6KJEcYGYukvuvHbjD+8eGbFX6SJc1SWeaVC+xKVZP7v7oPS/Na3ePwHq1DPcnkOf/SOrw0U2+gjSeS63yXWksZENt3U1y2h2yxwvfB+665aRLvPMH4CVoPeTg==");
//        String authKey = "v95siml3hvxBAy7T";
//        byte[] auth = authKey.getBytes();
//        // TODO: 7/18/2019 定义压缩与随机字符串拼接数组
//        byte[] concatBytes = new byte[bytes1.length + auth.length];
//        System.arraycopy(bytes1, 0, concatBytes, 0, bytes1.length);
//        System.arraycopy(auth, 0, concatBytes, bytes1.length, auth.length);
//        String hash = HashUtil.getHashString(concatBytes, "SHA-256");
//        String encode = BASE64Util.encode(hash);
//        System.out.println(encode);
        TestData.getSuccess();
//        String unZlib = CompressUtil.getUnZlib("\n" +
//                "eJztlMEKwjAMht+l5ynp5tzaN/AmuJPioawVB7YbayfI8N1ND9rcvAnCeuofkj/NR9uZhc6aQ1B2YJLlwMUKqlVeNhxkARLqI8uYDyrszdj1GnNKDGgVFNYEJk8zuykfhinaEAfgbweIDgqXmywmQCzvrepc0s6nPUnzV2NC8xiiL0cdG12+NBpN2486ebROWUMsoaJiQ0VJxZaKmgpB5kAMdzPG462BPbMfsSgWFh8W+XIxCIx8gZFg/MMzOeMovTa7+LECcBACMPoClZqF5w==");
//        System.out.println(unZlib);
    }
}
