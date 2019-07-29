package com.byzoro.controller;

import com.alibaba.fastjson.JSONObject;
import com.byzoro.pojo.Data;
import com.byzoro.pojo.HttpUtil;
import org.jsoup.Connection;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

@RestController
public class Controller {
    private static String url= "http://112.17.133.189:8087";
    @Resource
    private Data data;
    @RequestMapping("/test")
    public String test(@RequestParam("postStr") String postStr){
//        System.setProperty("https.protocols", "TLSv1");
        System.out.println(postStr);
        return "8088test";
    }

    @RequestMapping("/test1")
    public String test1(@RequestParam("postStr") String postStr){
//        System.setProperty("https.protocols", "TLSv1");
        System.setProperty("javax.net.debug","ssl");
        System.out.println(postStr);
        return "8088test1";
    }

    @RequestMapping("/hello")
    public HashMap<String, Object> helloDocker(){
        String urlStr =url+ "/externalController/getDeviceList";
        HashMap<String, Object> map = null;
        HashMap<String, Object> map1 = new HashMap<>();
        JSONObject jsonObject = new JSONObject();
        map1.put("page", 1);
        map1.put("limit", 2);
        map1.put("enterpriseName","頔塘新村");
        map1.put("name", "");
        map1.put("form", "");
        String st2 = null;
        String result = getData(jsonObject);
        Connection.Response response = null;
        String s =  null;
        try {
            s = HttpUtil.get(urlStr, map1);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        String getUrl =urlStr + "?" + HttpUtil.getParamString(map1);
//        try {
//             response = HttpUtils.get(getUrl);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        response.body();
        System.out.println(s);
        if(result !=null && result !=""){
            // TODO: 5/30/2019 按照括号切割数据
            st2 = result.substring(result.indexOf("(")+1,result.indexOf(")"));
            JSONObject json = JSONObject.parseObject(st2);
            Object list = json.get("data");
            Object count = json.get("count");
            Object code = json.get("code");
            map = new HashMap<>();
            map.put("list",list);
            map.put("count",count);
            map.put("code ",code);
        }
        return map;

}

    public String getData(JSONObject jsonObject){

        String urlStr =url+ "/externalController/getDeviceList";
        DataOutputStream printWriter = null;
        BufferedReader reader = null;
        String result = "";
        String paras = jsonObject.toJSONString();
        try{
            URL url = new URL(urlStr);
            URLConnection urlConnection = url.openConnection();
            // 设置通用的请求属性
            urlConnection.setRequestProperty("accept", "*/*");
            urlConnection.setRequestProperty("connection", "Keep-Alive");
            urlConnection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            urlConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            // 发送POST请求必须设置如下两行
//            urlConnection.setDoOutput(true);
//            urlConnection.setDoInput(true);
            printWriter = new DataOutputStream(urlConnection.getOutputStream());
            //发送请求
            printWriter.write(paras.getBytes("utf-8"));
            printWriter.flush();

            //获取数据
            reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));
            String  line ;
            while ( (line =reader.readLine()) != null){
                result += line;
            }
        }catch (Exception e){
        }
        return result;
    }

    /**
     * @describe 查询周边目标（风险源、隐患、重大危险源）
     * @return com.wyait.manage.entity.enterMap.GisResult
     * @auther: xiaopang
     * @date: 6/25/2019 1:57 PM
     */
    @RequestMapping("queryNearbyObjsByGeometry")
    @ResponseBody
    public String queryNearbyObjsByGeometry(String data){
        System.out.println(data);
        return data;
    }

}
