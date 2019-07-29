package com.byzoro.utils;

import java.io.File;
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.IOException;  
import java.io.InputStream;  
import java.security.SecureRandom;  
import java.security.cert.CertificateException;  
import java.security.cert.X509Certificate;  
import java.util.Map;  
import java.util.Map.Entry;  
import java.util.Set;  
  
import javax.net.ssl.HostnameVerifier;  
import javax.net.ssl.HttpsURLConnection;  
import javax.net.ssl.SSLContext;  
import javax.net.ssl.SSLSession;  
import javax.net.ssl.X509TrustManager;  
  
import org.jsoup.Connection;  
import org.jsoup.Connection.Response;  
import org.jsoup.Jsoup;  
  
public class HttpsUtils {  
    /** 
     * 请求超时时间 
     */  
    private static final int TIME_OUT = 120000;  
  
    /** 
     * Https请求 
     */  
    private static final String HTTPS = "https";  
  
    /** 
     * 返回成功状态码 
     */  
    private static final int OK = 200;  
  
    /** 
     * 纯字符串参数post请求 
     *  
     * @param url 请求URL地址 
     * @param paramMap 请求字符串参数集合 
     * @return 服务器返回内容 
     * @throws Exception 
     */  
    public static String post(String url, Map<String, String> paramMap) throws Exception {  
        Response response = doPostRequest(url, paramMap, null);  
        return response.body();  
    }  
  
    /** 
     * 带上传文件的post请求 
     *  
     * @param url 请求URL地址 
     * @param paramMap 请求字符串参数集合 
     * @param fileMap 请求文件参数集合 
     * @return 服务器返回内容 
     * @throws Exception 
     */  
    public static String post(String url, Map<String, String> paramMap, Map<String, File> fileMap) throws Exception {  
        Response response = doPostRequest(url, paramMap, fileMap);  
        return response.body();  
    }  
  
    /** 
     * 执行post请求 
     *  
     * @param url 请求URL地址 
     * @param paramMap 请求字符串参数集合 
     * @param fileMap 请求文件参数集合 
     * @return 服务器相应对象 
     * @throws Exception 
     */  
    public static Response doPostRequest(String url, Map<String, String> paramMap, Map<String, File> fileMap) throws Exception {  
        if (null == url || url.isEmpty()) {  
            throw new Exception("The request URL is blank.");  
        }  
  
        // 如果是Https请求  
        if (url.startsWith(HTTPS)) {  
            getTrust();  
        }  
        Connection connection = Jsoup.connect(url);  
        //Connection connection = Jsoup.connect(url).url(new URL(null, url, new sun.net.www.protocol.https.Handler()));//可用  
        connection.method(Connection.Method.POST);  
        connection.timeout(TIME_OUT);  
        connection.header("Content-Type", "multipart/form-data");  
        connection.ignoreHttpErrors(true);  
        connection.ignoreContentType(true);  
  
        // 添加字符串类参数  
        if (null != paramMap && !paramMap.isEmpty()) {  
            connection.data(paramMap);  
        }  
  
        // 添加文件参数  
        if (null != fileMap && !fileMap.isEmpty()) {  
            InputStream in = null;  
            File file = null;  
            Set<Entry<String, File>> set = fileMap.entrySet();  
            try {  
                for (Entry<String, File> e : set) {  
                    file = e.getValue();  
                    in = new FileInputStream(file);  
                    connection.data(e.getKey(), file.getName(), in);  
                }  
            } catch (FileNotFoundException e) {  
                throw new Exception(e.getMessage());  
            }  
        }  
  
        try {  
            Response response = connection.execute();  
            if (response.statusCode() != OK) {  
                throw new Exception(response.statusMessage());  
            }  
            return response;  
        } catch (IOException e) {  
            throw new Exception(e);  
        }  
    }  
  
    /** 
     * 获取服务器信任 
     */  
    private static void getTrust() {  
        try {  
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {  
  
                public boolean verify(String hostname, SSLSession session) {  
                    return true;  
                }  
            });  
            SSLContext context = SSLContext.getInstance("TLS");  
            context.init(null, new X509TrustManager[] { new X509TrustManager() {  
  
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}  
  
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}  
  
                public X509Certificate[] getAcceptedIssuers() {  
                    return new X509Certificate[0];  
                }  
            } }, new SecureRandom());  
            HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
}  