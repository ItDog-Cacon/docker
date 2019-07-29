package com.byzoro.check;

import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.util.*;

public class StringUtil {

    public static String getIdStr() {
        String id = String.valueOf(new Date().getTime()) + getRandomStr(7);
        return id;
    }

    public static String getData() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(date);
        return time;
    }


    public static String getDataToString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(date);
        return time;
    }

    public static Boolean isEmpty(String str) {
        boolean flag = true;
        if (null == str || "".equals(str)) {
            flag = false;
        }
        return flag;
    }
    //设置时间格式，将该时间格式的时间转换为时间戳
    public static long dateToStamp(String s) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long time = date.getTime();
        return time;
    }

    public static Boolean checkIp(String ip) {

        String regex = "^(1\\d{2}|2[0-4 ]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
        // 判断ip地址是否与正则表达式匹配
        if (ip.matches(regex)) {
            // 返回判断信息
            return true;
        } else {
            // 返回判断信息
            return false;
        }
    }


    public static String getRandomStr(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        int randomNum;
        char randomChar;
        Random random = new Random();
        // StringBuffer类型的可以append增加字符
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < length; i++) {
            // 可生成[0,n)之间的整数，获得随机位置
            randomNum = random.nextInt(base.length());
            // 获得随机位置对应的字符
            randomChar = base.charAt(randomNum);
            // 组成一个随机字符串
            str.append(randomChar);
        }
        return str.toString();
    }


    /**
     * 用逗号分隔 拼接字符串
     *
     * @param list
     * @return
     * @author jjg
     */
    public static String ListToString(List list) {
        String pathName = "";
        if (list != null && list.size() > 0) {
            String str = "";
            for (int i = 0; i < list.size(); i++) {
                str += list.get(i) + ",";
            }
            pathName = str.substring(0, str.length() - 1);
        }
        return pathName;
    }

    public static void main(String args[]) {
//        isEmpty("1");
//////        String uuid = getRandomStr(7) + String.valueOf(new Date().getTime());
////        System.out.println(isEmpty("1"));
////
////        String[] strs = {"东人织造（苏州）有限公司","东人织造（苏州）有限公司1","东人织造（苏州）有限公司2","东人织造（苏州）有限公司3","c"};
////
////        boolean b = compareEqual(strs);
////        System.outintln("args = [" + b + "]");

      List<String> aa = new ArrayList<>();
        aa.add("1");
        aa.add("2");
     aa.add("2_1");
        aa.add("2_1");
        aa.add(null);
        TreeSet<String> hset = new TreeSet<String>(aa);

    }

    public static boolean compareEqual(String[] strs) {
        boolean flag = false;    //字符串数组中是否有两个字符串相等
        String temp = "";
        for (int i = 0; i < strs.length; i++) {
            if (temp.contains(strs[i] + ",")) {
                flag = true;
                break;    //若果有重复就直接跳出循环
            }
            temp += strs[i] + ",";    //将已经比较过的拼接成字符串
        }
        return flag;
    }


    public static byte[] toBytes(String str) {
        if (str == null || str.trim().equals("")) {
            return new byte[0];
        }

        byte[] bytes = new byte[str.length() / 2];
        for (int i = 0; i < str.length() / 2; i++) {
            String subStr = str.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }

        return bytes;
    }


    public static String assemblyData(String data) {

        int length = data.length();
        if (length == 3) {
            data = "0" + data;
        } else if (length == 2) {
            data = "00" + data;
        } else if (length == 1) {
            data = "000" + data;
        }
        return data;

    }


    public static Boolean checkNumber(String number) {


        String pattern = "([1-9])|([1-9]\\d{1})|([1-9]\\d{2})|([1-9]\\d{3})";

        if (number.matches(pattern)) {
            // 返回判断信息
            return true;
        } else {
            // 返回判断信息
            return false;
        }
    }

    public static boolean canParseInt(String str) {
        if (str == null) { //验证是否为空
            return false;
        }
        try {
            Integer.valueOf(str);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public static JSONObject stringToJson(String json){
        JSONObject jsonObject = JSONObject.parseObject(json);
        return jsonObject;
    }

    /**
     *@Description: 对数据进行解码校验
     *@Param:[json]
     *@return:java.lang.Boolean
     *@Author:xiaopang
     *@Date:2019/7/15
     */
    public static Boolean checkData(String json) {
        Boolean flag = false;
        JSONObject jsonObject = JSONObject.parseObject(json);
        String data = null;
        String pwdHash = null;
        String authString = null;
        if (jsonObject.get("data") != null) {
            data = jsonObject.get("data").toString();
        }
        if (jsonObject.get("pwdHash") != null) {
            pwdHash = jsonObject.get("pwdHash").toString();
        }
        String decode = BASE64Util.decode(data);
        byte[] auth = authString.getBytes();
        byte[] bytes = decode.getBytes();
        byte[] zip = CompressUtil.zip(bytes, "11");
        byte[] concatBytes = new byte[zip.length + auth.length];
        String hash = HashUtil.getHashString(concatBytes, "ss");
        String encode = BASE64Util.encode(hash);
        if(encode.equals(pwdHash)){
            flag = true;
        }
        return flag;
    }
}
