package com.byzoro.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.byzoro.check.BASE64Util;
import com.byzoro.check.CompressUtil;
import com.byzoro.check.HashUtil;
import com.byzoro.pojo.HttpsRequest;
import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class TestData {

    public static String get51Data(){
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        HashMap<String, Object> map1 = new HashMap<>();
        jsonObject.put("nodeId","03201101");
        jsonObject.put("cityId","1100");
        jsonObject.put("serverNum","10");
        jsonObject.put("nodeStatus","0");
        jsonObject.put("statusMsg","51");
        jsonObject.put("payloadRate","51.1");
        jsonObject.put("nodeQps","10111111");
        jsonObject.put("statPeriod","300");
        jsonObject.put("timeStamp","2019-07-01T10:30:00Z");
        String s = JSON.toJSONString(jsonObject, SerializerFeature.DisableCircularReferenceDetect);
        // TODO: 7/16/2019 压缩数据zip
        byte[] zip = CompressUtil.zlib(s);
        String randVal = "aaa";
        // TODO: 7/16/2019 生成随机密钥
        byte[] radom = randVal.getBytes();
        // TODO: 7/16/2019 拼接随机密钥
        byte[] concatBytes = new byte[zip.length + radom.length];
        System.arraycopy(zip,0,concatBytes,0,zip.length);
        System.arraycopy(radom,0,concatBytes,zip.length,radom.length);
        String hashString = HashUtil.getHashString(concatBytes, "SHA-256");
        String dataHash = BASE64Util.encode(hashString);
        String data = BASE64Util.encode(zip);
        jsonObject1.put("data",data);
        jsonObject1.put("hashMode","3");
        jsonObject1.put("dataHash",dataHash);
        jsonObject1.put("randVal",randVal);
        jsonObject1.put("timeStamp","2019-07-01T10:30:00Z");
        jsonObject1.put("intfId","51");
        jsonObject1.put("uuid","51");
        jsonObject1.put("subsysId","51");
        jsonObject1.put("intfVer","51");
        jsonObject1.put("encryptMode","0");
        jsonObject1.put("compressMode","1");
        jsonObject1.put("dataTag","0");
        jsonObject1.put("orgId","51");
        // TODO: 7/17/2019 模拟kafka数据请求
        map1.put("postStr",jsonObject1.toJSONString());
        // TODO: 7/17/2019 代码测试模拟发送现网环境
//        map1.put("postStr",s);
        String post = null;
        try {
            // TODO: 7/17/2019 模拟测试现网环境
//            HttpsRequest.sslRequestPost("https://192.168.124.100/test?postStr=aaa", map1, "UTF-8");
            // TODO: 7/17/2019 测试本地环境
            post = HttpsRequest.sslRequestPost("https://yjzh:443/51/1", map1, "UTF-8");
//            post = HttpUtil.post("https://192.168.124.100/test", map1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(post);
        return post;
    }

    public static String get52Data(){
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        HashMap<String, Object> map1 = new HashMap<>();
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        jsonObject.put("nodeId","03201101");
        jsonObject.put("statPeriod","300");
        jsonObject.put("timeStamp","2019-07-01T10:30:00Z");
        map1.put("domain","baidu.com");
        map1.put("domainType","2");
        map1.put("resolveType","1");
        map1.put("queryCnt","12345");
        list.add(map1);
        map1.put("domain","taobao.com");
        map1.put("domainType","2");
        map1.put("resolveType","1");
        map1.put("queryCnt","12345");
        list.add(map1);
        jsonObject.put("statList",list);
        String s = JSON.toJSONString(jsonObject, SerializerFeature.DisableCircularReferenceDetect);
        // TODO: 7/16/2019 压缩数据zip
        byte[] zip = CompressUtil.zlib(s);
        String randVal = "aaa";
        // TODO: 7/16/2019 生成随机密钥
        byte[] radom = randVal.getBytes();
        // TODO: 7/16/2019 拼接随机密钥
        byte[] concatBytes = new byte[zip.length + radom.length];
        System.arraycopy(zip,0,concatBytes,0,zip.length);
        System.arraycopy(radom,0,concatBytes,zip.length,radom.length);
        String hashString = HashUtil.getHashString(concatBytes, "SHA-256");
        String dataHash = BASE64Util.encode(hashString);
        String data = BASE64Util.encode(zip);
        jsonObject1.put("data",data);
        jsonObject1.put("hashMode","3");
        jsonObject1.put("dataHash",dataHash);
        jsonObject1.put("randVal",randVal);
        jsonObject1.put("timeStamp","2019-07-01T10:30:00Z");
        jsonObject1.put("intfId","52");
        jsonObject1.put("uuid","52");
        jsonObject1.put("subsysId","52");
        jsonObject1.put("intfVer","52");
        jsonObject1.put("encryptMode","0");
        jsonObject1.put("compressMode","1");
        jsonObject1.put("dataTag","0");
        jsonObject1.put("orgId","52");
        // TODO: 7/17/2019 模拟kafka数据请求
        map1.put("postStr",jsonObject1.toJSONString());
        // TODO: 7/17/2019 代码测试模拟发送现网环境
//        map1.put("postStr",s);
        String post = null;
        try {
            // TODO: 7/17/2019 模拟测试现网环境
//            HttpsRequest.sslRequestPost("https://192.168.124.100/test?postStr=aaa", map1, "UTF-8");
            // TODO: 7/17/2019 测试本地环境
            post = HttpsRequest.sslRequestPost("https://yjzh:443/52/1", map1, "UTF-8");
//            post = HttpUtil.post("https://192.168.124.100/test", map1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(post);
        return post;
    }

    public static String get53Data(){
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        HashMap<String, Object> map1 = new HashMap<>();
        HashMap<String, Object> map2 = new HashMap<>();
        HashMap<String, Object> map3 = new HashMap<>();
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        jsonObject.put("nodeId","03201101");
        jsonObject.put("statPeriod","300");
        jsonObject.put("timeStamp","2019-07-01T10:30:00Z");
        jsonObject.put("queryCnt","1234567890");
        jsonObject.put("sucRespCnt","1234567890");
        jsonObject.put("aQueryCnt","10000");
        jsonObject.put("aaaaQueryCnt","2345");
        map1.put("tldName","com");
        map1.put("queryCnt","12345");
        map1.put("sucRespCnt","12300");
        map1.put("resolveAvgT","10");
        list.add(map1);
        map1.put("tldName","net");
        map1.put("queryCnt","12345");
        map1.put("sucRespCnt","12300");
        map1.put("queryCnt","12345");
        map1.put("resolveAvgT","10");
        list.add(map1);
        jsonObject.put("tldList",list);
        String s = JSON.toJSONString(jsonObject, SerializerFeature.DisableCircularReferenceDetect);
        // TODO: 7/16/2019 压缩数据zip
        byte[] zip = CompressUtil.zlib(s);
        String randVal = "aaa";
        // TODO: 7/16/2019 生成随机密钥
        byte[] radom = randVal.getBytes();
        // TODO: 7/16/2019 拼接随机密钥
        byte[] concatBytes = new byte[zip.length + radom.length];
        System.arraycopy(zip,0,concatBytes,0,zip.length);
        System.arraycopy(radom,0,concatBytes,zip.length,radom.length);
        String hashString = HashUtil.getHashString(concatBytes, "SHA-256");
        String dataHash = BASE64Util.encode(hashString);
        String data = BASE64Util.encode(zip);
        jsonObject1.put("data",data);
        jsonObject1.put("hashMode","3");
        jsonObject1.put("dataHash",dataHash);
        jsonObject1.put("randVal",randVal);
        jsonObject1.put("timeStamp","2019-07-01T10:30:00Z");
        jsonObject1.put("intfId","53");
        jsonObject1.put("uuid","53");
        jsonObject1.put("subsysId","53");
        jsonObject1.put("intfVer","53");
        jsonObject1.put("encryptMode","0");
        jsonObject1.put("compressMode","1");
        jsonObject1.put("dataTag","0");
        jsonObject1.put("orgId","53");
        // TODO: 7/17/2019 模拟kafka数据请求
        map1.put("postStr",jsonObject1.toJSONString());
        // TODO: 7/17/2019 代码测试模拟发送现网环境
//        map1.put("postStr",s);
        String post = null;
        try {
            // TODO: 7/17/2019 模拟测试现网环境
//            HttpsRequest.sslRequestPost("https://192.168.124.100/test?postStr=aaa", map1, "UTF-8");
            // TODO: 7/17/2019 测试本地环境
            post = HttpsRequest.sslRequestPost("https://yjzh:443/54/1", map1, "UTF-8");
//            post = HttpUtil.post("https://192.168.124.100/test", map1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(post);
        return post;
    }

    public static String get54Data(){
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        HashMap<String, Object> map1 = new HashMap<>();
        HashMap<String, Object> map2 = new HashMap<>();
        HashMap<String, Object> map3 = new HashMap<>();
        map2.put("tldName","com");
        map2.put("queryCnt","12345");
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        ArrayList<HashMap<String, Object>> list1 = new ArrayList<>();
        list.add(map2);
        map3.put("tldName","net");
        map3.put("queryCnt","12345");
        list.add(map3);
        list1.add(map2);
        list1.add(map3);
        jsonObject.put("nodeId","03201101");
        jsonObject.put("statPeriod","300");
        jsonObject.put("timeStamp","2019-07-01T10:30:00Z");
        jsonObject.put("rcopyRCnt","1234567890");
        jsonObject.put("rcopyRAvgT","10");
        jsonObject.put("tldList",list);
        jsonObject.put("rootList",list1);
        String s = JSON.toJSONString(jsonObject, SerializerFeature.DisableCircularReferenceDetect);
        // TODO: 7/16/2019 压缩数据zip
        byte[] zip = CompressUtil.zlib(s);
        String randVal = "aaa";
        // TODO: 7/16/2019 生成随机密钥
        byte[] radom = randVal.getBytes();
        // TODO: 7/16/2019 拼接随机密钥
        byte[] concatBytes = new byte[zip.length + radom.length];
        System.arraycopy(zip,0,concatBytes,0,zip.length);
        System.arraycopy(radom,0,concatBytes,zip.length,radom.length);
        String hashString = HashUtil.getHashString(concatBytes, "SHA-256");
        String dataHash = BASE64Util.encode(hashString);
        String data = BASE64Util.encode(zip);
        jsonObject1.put("data",data);
        jsonObject1.put("hashMode","3");
        jsonObject1.put("dataHash",dataHash);
        jsonObject1.put("randVal",randVal);
        jsonObject1.put("timeStamp","2019-07-01T10:30:00Z");
        jsonObject1.put("intfId","54");
        jsonObject1.put("uuid","54");
        jsonObject1.put("subsysId","54");
        jsonObject1.put("intfVer","54");
        jsonObject1.put("encryptMode","0");
        jsonObject1.put("compressMode","1");
        jsonObject1.put("dataTag","0");
        jsonObject1.put("orgId","54");
        // TODO: 7/17/2019 模拟kafka数据请求
        map1.put("postStr",jsonObject1.toJSONString());
        // TODO: 7/17/2019 代码测试模拟发送现网环境
//        map1.put("postStr",s);
        String post = null;
        try {
            // TODO: 7/17/2019 模拟测试现网环境
//            HttpsRequest.sslRequestPost("https://192.168.124.100/test?postStr=aaa", map1, "UTF-8");
            // TODO: 7/17/2019 测试本地环境
            post = HttpsRequest.sslRequestPost("https://yjzh:443/54/1", map1, "UTF-8");
//            post = HttpUtil.post("https://192.168.124.100/test", map1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(post);
        return post;
    }

    public static String get55Data(){
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        HashMap<String, Object> map1 = new HashMap<>();
        HashMap<String, Object> map2 = new HashMap<>();
        HashMap<String, Object> map3 = new HashMap<>();
        map2.put("tldName","com");
        map2.put("queryCnt","12345");
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        ArrayList<HashMap<String, Object>> list1 = new ArrayList<>();
        list.add(map2);
        map3.put("tldName","net");
        map3.put("queryCnt","12345");
        list.add(map3);
        list1.add(map2);
        list1.add(map3);
        jsonObject.put("nodeId","03201101");
        jsonObject.put("statPeriod","300");
        jsonObject.put("timeStamp","2019-07-01T10:30:00Z");
        jsonObject.put("domainCheckNum","1234");
        jsonObject.put("monList",list);
        jsonObject.put("testStat",list1);
        String s = JSON.toJSONString(jsonObject, SerializerFeature.DisableCircularReferenceDetect);
        // TODO: 7/16/2019 压缩数据zip
        byte[] zip = CompressUtil.zlib(s);
        String randVal = "aaa";
        // TODO: 7/16/2019 生成随机密钥
        byte[] radom = randVal.getBytes();
        // TODO: 7/16/2019 拼接随机密钥
        byte[] concatBytes = new byte[zip.length + radom.length];
        System.arraycopy(zip,0,concatBytes,0,zip.length);
        System.arraycopy(radom,0,concatBytes,zip.length,radom.length);
        String hashString = HashUtil.getHashString(concatBytes, "SHA-256");
        String dataHash = BASE64Util.encode(hashString);
        String data = BASE64Util.encode(zip);
        jsonObject1.put("data",data);
        jsonObject1.put("hashMode","3");
        jsonObject1.put("dataHash",dataHash);
        jsonObject1.put("randVal",randVal);
        jsonObject1.put("timeStamp","2019-07-01T10:30:00Z");
        jsonObject1.put("intfId","55");
        jsonObject1.put("uuid","55");
        jsonObject1.put("subsysId","55");
        jsonObject1.put("intfVer","55");
        jsonObject1.put("encryptMode","0");
        jsonObject1.put("compressMode","1");
        jsonObject1.put("dataTag","0");
        jsonObject1.put("orgId","55");
        // TODO: 7/17/2019 模拟kafka数据请求
        map1.put("postStr",jsonObject1.toJSONString());
        // TODO: 7/17/2019 代码测试模拟发送现网环境
//        map1.put("postStr",s);
        String post = null;
        try {
            // TODO: 7/17/2019 模拟测试现网环境
//            HttpsRequest.sslRequestPost("https://192.168.124.100/test?postStr=aaa", map1, "UTF-8");
            // TODO: 7/17/2019 测试本地环境
            post = HttpsRequest.sslRequestPost("https://yjzh:443/55/1", map1, "UTF-8");
//            post = HttpUtil.post("https://192.168.124.100/test", map1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(post);
        return post;
    }

    public static String get56Data(){
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        HashMap<String, Object> map1 = new HashMap<>();
        HashMap<String, Object> map2 = new HashMap<>();
        HashMap<String, Object> map3 = new HashMap<>();
        map2.put("tldName","com");
        map2.put("queryCnt","12345");
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        ArrayList<HashMap<String, Object>> list1 = new ArrayList<>();
        list.add(map2);
        map3.put("tldName","net");
        map3.put("queryCnt","12345");
        list.add(map3);
        list1.add(map2);
        list1.add(map3);
        jsonObject.put("nodeId","03201101");
        jsonObject.put("statPeriod","300");
        jsonObject.put("timeStamp","2019-07-01T10:30:00Z");
        jsonObject.put("resolveAvgT","12");
        jsonObject.put("monList",list);
        jsonObject.put("dataStat",list1);
        String s = JSON.toJSONString(jsonObject, SerializerFeature.DisableCircularReferenceDetect);
        // TODO: 7/16/2019 压缩数据zip
        byte[] zip = CompressUtil.zlib(s);
        String randVal = "aaa";
        // TODO: 7/16/2019 生成随机密钥
        byte[] radom = randVal.getBytes();
        // TODO: 7/16/2019 拼接随机密钥
        byte[] concatBytes = new byte[zip.length + radom.length];
        System.arraycopy(zip,0,concatBytes,0,zip.length);
        System.arraycopy(radom,0,concatBytes,zip.length,radom.length);
        String hashString = HashUtil.getHashString(concatBytes, "SHA-256");
        String dataHash = BASE64Util.encode(hashString);
        String data = BASE64Util.encode(zip);
        jsonObject1.put("data",data);
        jsonObject1.put("hashMode","3");
        jsonObject1.put("dataHash",dataHash);
        jsonObject1.put("randVal",randVal);
        jsonObject1.put("timeStamp","2019-07-01T10:30:00Z");
        jsonObject1.put("intfId","56");
        jsonObject1.put("uuid","56");
        jsonObject1.put("subsysId","56");
        jsonObject1.put("intfVer","56");
        jsonObject1.put("encryptMode","0");
        jsonObject1.put("compressMode","1");
        jsonObject1.put("dataTag","0");
        jsonObject1.put("orgId","56");
        // TODO: 7/17/2019 模拟kafka数据请求
        map1.put("postStr",jsonObject1.toJSONString());
        // TODO: 7/17/2019 代码测试模拟发送现网环境
//        map1.put("postStr",s);
        String post = null;
        try {
            // TODO: 7/17/2019 模拟测试现网环境
//            HttpsRequest.sslRequestPost("https://192.168.124.100/test?postStr=aaa", map1, "UTF-8");
            // TODO: 7/17/2019 测试本地环境
            post = HttpsRequest.sslRequestPost("https://yjzh:443/56/1", map1, "UTF-8");
//            post = HttpUtil.post("https://192.168.124.100/test", map1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(post);
        return post;
    }

    public static String get57Data(){
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        HashMap<String, Object> map1 = new HashMap<>();
        HashMap<String, Object> map2 = new HashMap<>();
        HashMap<String, Object> map3 = new HashMap<>();
        map2.put("tldName","com");
        map2.put("queryCnt","12345");
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        ArrayList<HashMap<String, Object>> list1 = new ArrayList<>();
        list.add(map2);
        map3.put("tldName","net");
        map3.put("queryCnt","12345");
        list.add(map3);
        list1.add(map2);
        list1.add(map3);
        jsonObject.put("nodeId","03201101");
        jsonObject.put("statPeriod","300");
        jsonObject.put("timeStamp","2019-07-01T10:30:00Z");
        jsonObject.put("resolveAvgT","12");
        jsonObject.put("dataStat",list1);
        String s = JSON.toJSONString(jsonObject, SerializerFeature.DisableCircularReferenceDetect);
        // TODO: 7/16/2019 压缩数据zip
        byte[] zip = CompressUtil.zlib(s);
        String randVal = "aaa";
        // TODO: 7/16/2019 生成随机密钥
        byte[] radom = randVal.getBytes();
        // TODO: 7/16/2019 拼接随机密钥
        byte[] concatBytes = new byte[zip.length + radom.length];
        System.arraycopy(zip,0,concatBytes,0,zip.length);
        System.arraycopy(radom,0,concatBytes,zip.length,radom.length);
        String hashString = HashUtil.getHashString(concatBytes, "SHA-256");
        String dataHash = BASE64Util.encode(hashString);
        String data = BASE64Util.encode(zip);
        jsonObject1.put("data",data);
        jsonObject1.put("hashMode","3");
        jsonObject1.put("dataHash",dataHash);
        jsonObject1.put("randVal",randVal);
        jsonObject1.put("timeStamp","2019-07-01T10:30:00Z");
        jsonObject1.put("intfId","57");
        jsonObject1.put("uuid","57");
        jsonObject1.put("subsysId","57");
        jsonObject1.put("intfVer","57");
        jsonObject1.put("encryptMode","0");
        jsonObject1.put("compressMode","1");
        jsonObject1.put("dataTag","0");
        jsonObject1.put("orgId","57");
        // TODO: 7/17/2019 模拟kafka数据请求
        map1.put("postStr",jsonObject1.toJSONString());
        // TODO: 7/17/2019 代码测试模拟发送现网环境
//        map1.put("postStr",s);
        String post = null;
        try {
            // TODO: 7/17/2019 模拟测试现网环境
//            HttpsRequest.sslRequestPost("https://192.168.124.100/test?postStr=aaa", map1, "UTF-8");
            // TODO: 7/17/2019 测试本地环境
            post = HttpsRequest.sslRequestPost("https://yjzh:443/57/1", map1, "UTF-8");
//            post = HttpUtil.post("https://192.168.124.100/test", map1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(post);
        return post;
    }

    public static String get58Data(){
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        HashMap<String, Object> map1 = new HashMap<>();
        HashMap<String, Object> map2 = new HashMap<>();
        HashMap<String, Object> map3 = new HashMap<>();
        map2.put("tldName","com");
        map2.put("queryCnt","12345");
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        ArrayList<HashMap<String, Object>> list1 = new ArrayList<>();
        list.add(map2);
        map3.put("tldName","net");
        map3.put("queryCnt","12345");
        list.add(map3);
        list1.add(map2);
        list1.add(map3);
        jsonObject.put("nodeId","03201101");
        jsonObject.put("statPeriod","300");
        jsonObject.put("timeStamp","2019-07-01T10:30:00Z");
        jsonObject.put("resolveAvgT","12");
        jsonObject.put("dataStat",list1);
        String s = JSON.toJSONString(jsonObject, SerializerFeature.DisableCircularReferenceDetect);
        // TODO: 7/16/2019 压缩数据zip
        byte[] zip = CompressUtil.zlib(s);
        String randVal = "aaa";
        // TODO: 7/16/2019 生成随机密钥
        byte[] radom = randVal.getBytes();
        // TODO: 7/16/2019 拼接随机密钥
        byte[] concatBytes = new byte[zip.length + radom.length];
        System.arraycopy(zip,0,concatBytes,0,zip.length);
        System.arraycopy(radom,0,concatBytes,zip.length,radom.length);
        String hashString = HashUtil.getHashString(concatBytes, "SHA-256");
        String dataHash = BASE64Util.encode(hashString);
        String data = BASE64Util.encode(zip);
        jsonObject1.put("data",data);
        jsonObject1.put("hashMode","3");
        jsonObject1.put("dataHash",dataHash);
        jsonObject1.put("randVal",randVal);
        jsonObject1.put("timeStamp","2019-07-01T10:30:00Z");
        jsonObject1.put("intfId","58");
        jsonObject1.put("uuid","58");
        jsonObject1.put("subsysId","58");
        jsonObject1.put("intfVer","58");
        jsonObject1.put("encryptMode","0");
        jsonObject1.put("compressMode","1");
        jsonObject1.put("dataTag","0");
        jsonObject1.put("orgId","58");
        // TODO: 7/17/2019 模拟kafka数据请求
        map1.put("postStr",jsonObject1.toJSONString());
        // TODO: 7/17/2019 代码测试模拟发送现网环境
//        map1.put("postStr",s);
        String post = null;
        try {
            // TODO: 7/17/2019 模拟测试现网环境
//            HttpsRequest.sslRequestPost("https://192.168.124.100/test?postStr=aaa", map1, "UTF-8");
            // TODO: 7/17/2019 测试本地环境
            post = HttpsRequest.sslRequestPost("https://yjzh:443/58/1", map1, "UTF-8");
//            post = HttpUtil.post("https://192.168.124.100/test", map1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(post);
        return post;
    }

    public static String get59Data(){
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        HashMap<String, Object> map1 = new HashMap<>();
        HashMap<String, Object> map2 = new HashMap<>();
        HashMap<String, Object> map3 = new HashMap<>();
        map2.put("tldName","com");
        map2.put("queryCnt","12345");
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        ArrayList<HashMap<String, Object>> list1 = new ArrayList<>();
        list.add(map2);
        map3.put("tldName","net");
        map3.put("queryCnt","12345");
        list.add(map3);
        list1.add(map2);
        list1.add(map3);
        jsonObject.put("nodeId","03201101");
        jsonObject.put("statPeriod","300");
        jsonObject.put("timeStamp","2019-07-01T10:30:00Z");
        jsonObject.put("resolveAvgT","12");
        jsonObject.put("statList",list1);
        String s = JSON.toJSONString(jsonObject, SerializerFeature.DisableCircularReferenceDetect);
        // TODO: 7/16/2019 压缩数据zip
        byte[] zip = CompressUtil.zlib(s);
        String randVal = "aaa";
        // TODO: 7/16/2019 生成随机密钥
        byte[] radom = randVal.getBytes();
        // TODO: 7/16/2019 拼接随机密钥
        byte[] concatBytes = new byte[zip.length + radom.length];
        System.arraycopy(zip,0,concatBytes,0,zip.length);
        System.arraycopy(radom,0,concatBytes,zip.length,radom.length);
        String hashString = HashUtil.getHashString(concatBytes, "SHA-256");
        String dataHash = BASE64Util.encode(hashString);
        String data = BASE64Util.encode(zip);
        jsonObject1.put("data",data);
        jsonObject1.put("hashMode","3");
        jsonObject1.put("dataHash",dataHash);
        jsonObject1.put("randVal",randVal);
        jsonObject1.put("timeStamp","2019-07-01T10:30:00Z");
        jsonObject1.put("intfId","59");
        jsonObject1.put("uuid","59");
        jsonObject1.put("subsysId","59");
        jsonObject1.put("intfVer","59");
        jsonObject1.put("encryptMode","0");
        jsonObject1.put("compressMode","1");
        jsonObject1.put("dataTag","0");
        jsonObject1.put("orgId","59");
        // TODO: 7/17/2019 模拟kafka数据请求
        map1.put("postStr",jsonObject1.toJSONString());
        // TODO: 7/17/2019 代码测试模拟发送现网环境
//        map1.put("postStr",s);
        String post = null;
        try {
            // TODO: 7/17/2019 模拟测试现网环境
//            HttpsRequest.sslRequestPost("https://192.168.124.100/test?postStr=aaa", map1, "UTF-8");
            // TODO: 7/17/2019 测试本地环境
            post = HttpsRequest.sslRequestPost("https://yjzh:443/59/1", map1, "UTF-8");
//            post = HttpUtil.post("https://192.168.124.100/test", map1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(post);
        return post;
    }

    public static String get60Data(){
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        HashMap<String, Object> map1 = new HashMap<>();
        HashMap<String, Object> map2 = new HashMap<>();
        HashMap<String, Object> map3 = new HashMap<>();
        map2.put("tldName","com");
        map2.put("queryCnt","12345");
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        ArrayList<HashMap<String, Object>> list1 = new ArrayList<>();
        list.add(map2);
        map3.put("tldName","net");
        map3.put("queryCnt","12345");
        list.add(map3);
        list1.add(map2);
        list1.add(map3);
        jsonObject.put("nodeId","03201101");
        jsonObject.put("statPeriod","300");
        jsonObject.put("timeStamp","2019-07-01T10:30:00Z");
        jsonObject.put("resolveAvgT","12");
        jsonObject.put("queryCnt","60");
        jsonObject.put("sucRespCnt","60");
        jsonObject.put("resolveAvgT","60");
        jsonObject.put("resStat",list1);
        String s = JSON.toJSONString(jsonObject, SerializerFeature.DisableCircularReferenceDetect);
        // TODO: 7/16/2019 压缩数据zip
        byte[] zip = CompressUtil.zlib(s);
        String randVal = "aaa";
        // TODO: 7/16/2019 生成随机密钥
        byte[] radom = randVal.getBytes();
        // TODO: 7/16/2019 拼接随机密钥
        byte[] concatBytes = new byte[zip.length + radom.length];
        System.arraycopy(zip,0,concatBytes,0,zip.length);
        System.arraycopy(radom,0,concatBytes,zip.length,radom.length);
        String hashString = HashUtil.getHashString(concatBytes, "SHA-256");
        String dataHash = BASE64Util.encode(hashString);
        String data = BASE64Util.encode(zip);
        jsonObject1.put("data",data);
        jsonObject1.put("hashMode","3");
        jsonObject1.put("dataHash",dataHash);
        jsonObject1.put("randVal",randVal);
        jsonObject1.put("timeStamp","2019-07-01T10:30:00Z");
        jsonObject1.put("intfId","60");
        jsonObject1.put("uuid","60");
        jsonObject1.put("subsysId","60");
        jsonObject1.put("intfVer","60");
        jsonObject1.put("encryptMode","0");
        jsonObject1.put("compressMode","1");
        jsonObject1.put("dataTag","0");
        jsonObject1.put("orgId","60");
        // TODO: 7/17/2019 模拟kafka数据请求
        map1.put("postStr",jsonObject1.toJSONString());
        // TODO: 7/17/2019 代码测试模拟发送现网环境
//        map1.put("postStr",s);
        String post = null;
        try {
            // TODO: 7/17/2019 模拟测试现网环境
//            HttpsRequest.sslRequestPost("https://192.168.124.100/test?postStr=aaa", map1, "UTF-8");
            // TODO: 7/17/2019 测试本地环境
            post = HttpsRequest.sslRequestPost("https://yjzh:443/60/1", map1, "UTF-8");
//            post = HttpUtil.post("https://192.168.124.100/test", map1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(post);
        return post;
    }

    public static String get61Data(){
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        HashMap<String, Object> map1 = new HashMap<>();
        HashMap<String, Object> map2 = new HashMap<>();
        HashMap<String, Object> map3 = new HashMap<>();
        map2.put("tldName","com");
        map2.put("queryCnt","12345");
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        ArrayList<HashMap<String, Object>> list1 = new ArrayList<>();
        list.add(map2);
        map3.put("tldName","net");
        map3.put("queryCnt","12345");
        list.add(map3);
        list1.add(map2);
        list1.add(map3);
        jsonObject.put("nodeId","03201101");
        jsonObject.put("statPeriod","300");
        jsonObject.put("timeStamp","2019-07-01T10:30:00Z");
        jsonObject.put("resolveAvgT","12");
        jsonObject.put("queryCnt","61");
        jsonObject.put("sucRespCnt","61");
        jsonObject.put("resolveAvgT","61");
        jsonObject.put("statList",list1);
        jsonObject.put("monList",list);
        String s = JSON.toJSONString(jsonObject, SerializerFeature.DisableCircularReferenceDetect);
        // TODO: 7/16/2019 压缩数据zip
        byte[] zip = CompressUtil.zlib(s);
        String randVal = "aaa";
        // TODO: 7/16/2019 生成随机密钥
        byte[] radom = randVal.getBytes();
        // TODO: 7/16/2019 拼接随机密钥
        byte[] concatBytes = new byte[zip.length + radom.length];
        System.arraycopy(zip,0,concatBytes,0,zip.length);
        System.arraycopy(radom,0,concatBytes,zip.length,radom.length);
        String hashString = HashUtil.getHashString(concatBytes, "SHA-256");
        String dataHash = BASE64Util.encode(hashString);
        String data = BASE64Util.encode(zip);
        jsonObject1.put("data",data);
        jsonObject1.put("hashMode","3");
        jsonObject1.put("dataHash",dataHash);
        jsonObject1.put("randVal",randVal);
        jsonObject1.put("timeStamp","2019-07-01T10:30:00Z");
        jsonObject1.put("intfId","61");
        jsonObject1.put("uuid","61");
        jsonObject1.put("subsysId","61");
        jsonObject1.put("intfVer","61");
        jsonObject1.put("encryptMode","0");
        jsonObject1.put("compressMode","1");
        jsonObject1.put("dataTag","0");
        jsonObject1.put("orgId","61");
        // TODO: 7/17/2019 模拟kafka数据请求
        map1.put("postStr",jsonObject1.toJSONString());
        // TODO: 7/17/2019 代码测试模拟发送现网环境
//        map1.put("postStr",s);
        String post = null;
        try {
            // TODO: 7/17/2019 模拟测试现网环境
//            HttpsRequest.sslRequestPost("https://192.168.124.100/test?postStr=aaa", map1, "UTF-8");
            // TODO: 7/17/2019 测试本地环境
            post = HttpsRequest.sslRequestPost("https://yjzh:443/61/1", map1, "UTF-8");
//            post = HttpUtil.post("https://192.168.124.100/test", map1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(post);
        return post;
    }

    public static String get62Data(){
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        HashMap<String, Object> map1 = new HashMap<>();
        HashMap<String, Object> map2 = new HashMap<>();
        HashMap<String, Object> map3 = new HashMap<>();
        map2.put("tldName","com");
        map2.put("queryCnt","12345");
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        ArrayList<HashMap<String, Object>> list1 = new ArrayList<>();
        list.add(map2);
        map3.put("tldName","net");
        map3.put("queryCnt","12345");
        list.add(map3);
        list1.add(map2);
        list1.add(map3);
        jsonObject.put("nodeId","03201101");
        jsonObject.put("statPeriod","300");
        jsonObject.put("timeStamp","2019-07-01T10:30:00Z");
        jsonObject.put("resolveAvgT","12");
        jsonObject.put("queryCnt","62");
        jsonObject.put("sucRespCnt","62");
        jsonObject.put("resolveAvgT","62");
        jsonObject.put("statList",list1);
        jsonObject.put("domainNum","62");
        String s = JSON.toJSONString(jsonObject, SerializerFeature.DisableCircularReferenceDetect);
        // TODO: 7/16/2019 压缩数据zip
        byte[] zip = CompressUtil.zlib(s);
        String randVal = "aaa";
        // TODO: 7/16/2019 生成随机密钥
        byte[] radom = randVal.getBytes();
        // TODO: 7/16/2019 拼接随机密钥
        byte[] concatBytes = new byte[zip.length + radom.length];
        System.arraycopy(zip,0,concatBytes,0,zip.length);
        System.arraycopy(radom,0,concatBytes,zip.length,radom.length);
        String hashString = HashUtil.getHashString(concatBytes, "SHA-256");
        String dataHash = BASE64Util.encode(hashString);
        String data = BASE64Util.encode(zip);
        jsonObject1.put("data",data);
        jsonObject1.put("hashMode","3");
        jsonObject1.put("dataHash",dataHash);
        jsonObject1.put("randVal",randVal);
        jsonObject1.put("timeStamp","2019-07-01T10:30:00Z");
        jsonObject1.put("intfId","62");
        jsonObject1.put("uuid","62");
        jsonObject1.put("subsysId","62");
        jsonObject1.put("intfVer","62");
        jsonObject1.put("encryptMode","0");
        jsonObject1.put("compressMode","1");
        jsonObject1.put("dataTag","0");
        jsonObject1.put("orgId","62");
        // TODO: 7/17/2019 模拟kafka数据请求
        map1.put("postStr",jsonObject1.toJSONString());
        // TODO: 7/17/2019 代码测试模拟发送现网环境
//        map1.put("postStr",s);
        String post = null;
        try {
            // TODO: 7/17/2019 模拟测试现网环境
//            HttpsRequest.sslRequestPost("https://192.168.124.100/test?postStr=aaa", map1, "UTF-8");
            // TODO: 7/17/2019 测试本地环境
            post = HttpsRequest.sslRequestPost("https://yjzh:443/62/1", map1, "UTF-8");
//            post = HttpUtil.post("https://192.168.124.100/test", map1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(post);
        return post;
    }

    public static String get63Data(){
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        HashMap<String, Object> map1 = new HashMap<>();
        HashMap<String, Object> map2 = new HashMap<>();
        HashMap<String, Object> map3 = new HashMap<>();
        map2.put("tldName","com");
        map2.put("queryCnt","12345");
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        ArrayList<HashMap<String, Object>> list1 = new ArrayList<>();
        list.add(map2);
        map3.put("tldName","net");
        map3.put("queryCnt","12345");
        list.add(map3);
        list1.add(map2);
        list1.add(map3);
        jsonObject.put("nodeId","03201101");
        jsonObject.put("statPeriod","300");
        jsonObject.put("timeStamp","2019-07-01T10:30:00Z");
        jsonObject.put("resolveAvgT","12");
        jsonObject.put("queryCnt","63");
        jsonObject.put("sucRespCnt","63");
        jsonObject.put("resolveAvgT","63");
        jsonObject.put("statList",list1);
        jsonObject.put("tldNum","63");
        String s = JSON.toJSONString(jsonObject, SerializerFeature.DisableCircularReferenceDetect);
        // TODO: 7/16/2019 压缩数据zip
        byte[] zip = CompressUtil.zlib(s);
        String randVal = "aaa";
        // TODO: 7/16/2019 生成随机密钥
        byte[] radom = randVal.getBytes();
        // TODO: 7/16/2019 拼接随机密钥
        byte[] concatBytes = new byte[zip.length + radom.length];
        System.arraycopy(zip,0,concatBytes,0,zip.length);
        System.arraycopy(radom,0,concatBytes,zip.length,radom.length);
        String hashString = HashUtil.getHashString(concatBytes, "SHA-256");
        String dataHash = BASE64Util.encode(hashString);
        String data = BASE64Util.encode(zip);
        jsonObject1.put("data",data);
        jsonObject1.put("hashMode","3");
        jsonObject1.put("dataHash",dataHash);
        jsonObject1.put("randVal",randVal);
        jsonObject1.put("timeStamp","2019-07-01T10:30:00Z");
        jsonObject1.put("intfId","63");
        jsonObject1.put("uuid","63");
        jsonObject1.put("subsysId","63");
        jsonObject1.put("intfVer","63");
        jsonObject1.put("encryptMode","0");
        jsonObject1.put("compressMode","1");
        jsonObject1.put("dataTag","0");
        jsonObject1.put("orgId","63");
        // TODO: 7/17/2019 模拟kafka数据请求
        map1.put("postStr",jsonObject1.toJSONString());
        // TODO: 7/17/2019 代码测试模拟发送现网环境
//        map1.put("postStr",s);
        String post = null;
        try {
            // TODO: 7/17/2019 模拟测试现网环境
//            HttpsRequest.sslRequestPost("https://192.168.124.100/test?postStr=aaa", map1, "UTF-8");
            // TODO: 7/17/2019 测试本地环境
            post = HttpsRequest.sslRequestPost("https://yjzh:443/63/1", map1, "UTF-8");
//            post = HttpUtil.post("https://192.168.124.100/test", map1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(post);
        return post;
    }

    public static String get64Data(){
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        HashMap<String, Object> map1 = new HashMap<>();
        HashMap<String, Object> map2 = new HashMap<>();
        HashMap<String, Object> map3 = new HashMap<>();
        map2.put("tldName","com");
        map2.put("queryCnt","12345");
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        ArrayList<HashMap<String, Object>> list1 = new ArrayList<>();
        list.add(map2);
        map3.put("tldName","net");
        map3.put("queryCnt","12345");
        list.add(map3);
        list1.add(map2);
        list1.add(map3);
        jsonObject.put("nodeId","03201101");
        jsonObject.put("statPeriod","300");
        jsonObject.put("timeStamp","2019-07-01T10:30:00Z");
        jsonObject.put("resolveAvgT","12");
        jsonObject.put("queryCnt","64");
        jsonObject.put("sucRespCnt","64");
        jsonObject.put("resolveAvgT","64");
        jsonObject.put("statList",list1);
        jsonObject.put("tldNum","64");
        String s = JSON.toJSONString(jsonObject, SerializerFeature.DisableCircularReferenceDetect);
        // TODO: 7/16/2019 压缩数据zip
        byte[] zip = CompressUtil.zlib(s);
        String randVal = "aaa";
        // TODO: 7/16/2019 生成随机密钥
        byte[] radom = randVal.getBytes();
        // TODO: 7/16/2019 拼接随机密钥
        byte[] concatBytes = new byte[zip.length + radom.length];
        System.arraycopy(zip,0,concatBytes,0,zip.length);
        System.arraycopy(radom,0,concatBytes,zip.length,radom.length);
        String hashString = HashUtil.getHashString(concatBytes, "SHA-256");
        String dataHash = BASE64Util.encode(hashString);
        String data = BASE64Util.encode(zip);
        jsonObject1.put("data",data);
        jsonObject1.put("hashMode","3");
        jsonObject1.put("dataHash",dataHash);
        jsonObject1.put("randVal",randVal);
        jsonObject1.put("timeStamp","2019-07-01T10:30:00Z");
        jsonObject1.put("intfId","64");
        jsonObject1.put("uuid","64");
        jsonObject1.put("subsysId","64");
        jsonObject1.put("intfVer","64");
        jsonObject1.put("encryptMode","0");
        jsonObject1.put("compressMode","1");
        jsonObject1.put("dataTag","0");
        jsonObject1.put("orgId","64");
        // TODO: 7/17/2019 模拟kafka数据请求
        map1.put("postStr",jsonObject1.toJSONString());
        // TODO: 7/17/2019 代码测试模拟发送现网环境
//        map1.put("postStr",s);
        String post = null;
        try {
            // TODO: 7/17/2019 模拟测试现网环境
//            HttpsRequest.sslRequestPost("https://192.168.124.100/test?postStr=aaa", map1, "UTF-8");
            // TODO: 7/17/2019 测试本地环境
            post = HttpsRequest.sslRequestPost("https://yjzh:443/64/1", map1, "UTF-8");
//            post = HttpUtil.post("https://192.168.124.100/test", map1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(post);
        return post;
    }

    public static String getXingTongYuan(){
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        HashMap<String, Object> map1 = new HashMap<>();
        jsonObject.put("name","777");
        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("id","123");
        map2.put("id1","1231");
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        list.add(map2);
        jsonObject.put("age",list);
        String s = jsonObject.toJSONString();
        // TODO: 7/16/2019 压缩数据zip
        byte[] zip = CompressUtil.zlib(s);
        String randVal = "aaa";
        // TODO: 7/16/2019 生成随机密钥
        byte[] radom = randVal.getBytes();
        // TODO: 7/16/2019 拼接随机密钥
        byte[] concatBytes = new byte[zip.length + radom.length];
        System.arraycopy(zip,0,concatBytes,0,zip.length);
        System.arraycopy(radom,0,concatBytes,zip.length,radom.length);
        String hashString = HashUtil.getHashString(concatBytes, "SHA-256");
        String dataHash = BASE64Util.encode(hashString);
        String data = BASE64Util.encode(zip);
        jsonObject1.put("data",data);
        jsonObject1.put("hashMode","3");
        jsonObject1.put("dataHash",dataHash);
        jsonObject1.put("randVal",randVal);
        // TODO: 7/17/2019 模拟kafka数据请求
        map1.put("postStr",jsonObject1.toJSONString());
        // TODO: 7/17/2019 代码测试模拟发送现网环境
        map1.put("postStr",s);
        String post = null;
        try {
            // TODO: 7/17/2019 模拟测试现网环境
//            HttpsRequest.sslRequestPost("https://192.168.124.100/test?postStr=aaa", map1, "UTF-8");
            // TODO: 7/17/2019 测试本地环境
            post = HttpsRequest.sslRequestPost("https://huang:9443/52/1", map1, "UTF-8");
//            post = HttpUtil.post("https://192.168.124.100/test", map1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(post);
        return post;
    }

    public static String sslRequestPostString(){
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        HashMap<String, Object> map1 = new HashMap<>();
        jsonObject.put("name","777");
        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("id","123");
        map2.put("id1","1231");
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        list.add(map2);
        jsonObject.put("age",list);
        String s = jsonObject.toJSONString();
        // TODO: 7/16/2019 压缩数据zip
        byte[] zip = CompressUtil.zlib(s);
        String randVal = "aaa";
        // TODO: 7/16/2019 生成随机密钥
        byte[] radom = randVal.getBytes();
        // TODO: 7/16/2019 拼接随机密钥
        byte[] concatBytes = new byte[zip.length + radom.length];
        System.arraycopy(zip,0,concatBytes,0,zip.length);
        System.arraycopy(radom,0,concatBytes,zip.length,radom.length);
        String hashString = HashUtil.getHashString(concatBytes, "SHA-256");
        String dataHash = BASE64Util.encode(hashString);
        String data = BASE64Util.encode(zip);
        jsonObject1.put("data",data);
        jsonObject1.put("hashMode","3");
        jsonObject1.put("dataHash",dataHash);
        jsonObject1.put("randVal",randVal);
        // TODO: 7/17/2019 模拟kafka数据请求
        map1.put("postStr",jsonObject1.toJSONString());
        // TODO: 7/17/2019 代码测试模拟发送现网环境
//        map1.put("postStr",s);
        String post = null;
        try {
            // TODO: 7/17/2019 模拟测试现网环境
//            HttpsRequest.sslRequestPost("https://192.168.124.100/test?postStr=aaa", map1, "UTF-8");
            // TODO: 7/17/2019 测试本地环境
            post = HttpsRequest.sslRequestPostString("https://huang:9443/52/1", jsonObject1.toJSONString(), "UTF-8");
//            post = HttpUtil.post("https://192.168.124.100/test", map1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(post);
        return post;
    }

    public static int getKafkaData(int line){
        // TODO Auto-generated method stub
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile("K:\\0722-json\\to_sq.py");

        // 第一个参数为期望获得的函数（变量）的名字，第二个参数为期望返回的对象类型
        PyFunction pyFunction = interpreter.get("readFile", PyFunction.class);
        String a = "K:\\0722-json\\msg_dst\\51_01.json";
        int b = 0;
//		调用函数，如果函数需要参数，在Java中必须先将参数转化为对应的“Python类型”
        while(true) {
            PyObject pyobj = pyFunction.__call__(new PyString(a), new PyInteger(b));
//		System.out.println(pyobj);
            String s = pyobj.toString();
            String[] kmhs = s.split("kmh");
            if(line <0){
                return 0;
            }else{
                String index = kmhs[1];
                String message = kmhs[0];
                int i = Integer.parseInt(index);
                getKafkaData(i);
            }

//            System.out.println(s1);
        }
//		System.out.println(s);
    }

    public static String get1(){
        try {
            FileReader fr = new FileReader("K:\\0722-json\\dst\\51-NodeWorkStatus.log");
            BufferedReader bf = new BufferedReader(fr);
            String str =  bf.readLine();
            //ArrayList<Object> arrayList = new ArrayList<>();
            // 按行读取字符串
            while(true) {
                while (str == null){
                    // TODO: 7/22/2019 若为null 等待输入数据
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    str = bf.readLine();
                }
                while (str != null) {
                    System.out.println(str);
                    //arrayList.add(str);
                    // TODO: 7/22/2019 不为null 发送kafka数据
                    str = bf.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }

    public static String getSuccess(){
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        HashMap<String, Object> map1 = new HashMap<>();
        HashMap<String, Object> map2 = new HashMap<>();
        HashMap<String, Object> map3 = new HashMap<>();
        map2.put("tldName","com");
        map2.put("queryCnt","12345");
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        ArrayList<HashMap<String, Object>> list1 = new ArrayList<>();
        list.add(map2);
        map3.put("tldName","net");
        map3.put("queryCnt","12345");
        list.add(map3);
        list1.add(map2);
        list1.add(map3);
        jsonObject.put("nodeId","03201101");
        jsonObject.put("statPeriod","300");
        jsonObject.put("timeStamp","2019-07-01T10:30:00Z");
        jsonObject.put("resolveAvgT","12");
        jsonObject.put("queryCnt","64");
        jsonObject.put("sucRespCnt","64");
        jsonObject.put("resolveAvgT","64");
        jsonObject.put("statList",list1);
        jsonObject.put("tldNum","64");
        String s = JSON.toJSONString(jsonObject, SerializerFeature.DisableCircularReferenceDetect);
        // TODO: 7/16/2019 压缩数据zip
        byte[] zip = CompressUtil.zlib(s);
        String randVal = "aaa";
        String authKey="v95siml3hvxBAy7T";
        // TODO: 7/16/2019 生成随机密钥
        byte[] radom = authKey.getBytes();
        // TODO: 7/16/2019 拼接随机密钥
        byte[] concatBytes = new byte[zip.length + radom.length];
        System.arraycopy(zip,0,concatBytes,0,zip.length);
        System.arraycopy(radom,0,concatBytes,zip.length,radom.length);
        String hashString = HashUtil.getHashString(concatBytes, "SHA-256");
        String dataHash = BASE64Util.encode(hashString);
        String data = BASE64Util.encode(zip);
        String pwd="DzRkzsm7HNG5jgu6";
        String s1 = pwd + randVal;
        String hashString1 = HashUtil.getHashString(s1, "SHA-256");
        String decode = BASE64Util.encode(hashString1);
        jsonObject1.put("pwdHash",decode);
        jsonObject1.put("data",data);
        jsonObject1.put("hashMode","3");
        jsonObject1.put("dataHash",dataHash);
        jsonObject1.put("randVal",randVal);
        jsonObject1.put("timeStamp","2019-07-01T10:30:00Z");
        jsonObject1.put("intfId","64");
        jsonObject1.put("uuid","64");
        jsonObject1.put("subsysId","64");
        jsonObject1.put("intfVer","64");
        jsonObject1.put("encryptMode","0");
        jsonObject1.put("compressMode","1");
        jsonObject1.put("dataTag","0");
        jsonObject1.put("orgId","64");
        // TODO: 7/17/2019 模拟kafka数据请求
        map1.put("postStr",jsonObject1.toJSONString());
        // TODO: 7/17/2019 代码测试模拟发送现网环境
//        map1.put("postStr",s);
        String post = null;
        try {
            // TODO: 7/17/2019 模拟测试现网环境
//            HttpsRequest.sslRequestPost("https://192.168.124.100/test?postStr=aaa", map1, "UTF-8");
            // TODO: 7/17/2019 测试本地环境
            post = HttpsRequest.sslRequestPostString("https://huang:9443/64/1", jsonObject1.toJSONString(), "UTF-8");
//            post = HttpUtil.post("https://192.168.124.100/test", map1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(post);
        return post;
    }
}
