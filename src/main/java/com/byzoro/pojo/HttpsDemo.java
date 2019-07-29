package com.byzoro.pojo;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

public class HttpsDemo {
    private final static String PFX_PATH = "D:\\docker\\src\\main\\resources\\trust\\qq.key.p12";	//客户端证书路径
    private final static String PFX_PWD = "123456";	//客户端证书密码


    public static String sslRequestGet(String url) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("JKS");
        InputStream instream = new FileInputStream(new File(PFX_PATH));
        try {
            keyStore.load(instream, PFX_PWD.toCharArray());
        } finally {
            instream.close();
        }

        SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, PFX_PWD.toCharArray()).build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext
                , new String[] { "TLSv1" }	// supportedProtocols ,这里可以按需要设置
                , null	// supportedCipherSuites
                , SSLConnectionSocketFactory.getDefaultHostnameVerifier());

        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        try {
            SslUtil.ignoreSsl();
            HttpPost httpPost = new HttpPost(url);
//			httpost.addHeader("Connection", "keep-alive");// 设置一些heander等
            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                HttpEntity entity = response.getEntity();
                String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");//返回结果
                EntityUtils.consume(entity);
                return jsonStr;
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println(System.getProperty("java.home"));
        SSLTool.disableCertificateValidation();
        System.out.println(sslRequestGet("https://10.100.2.32:8080/test?test=aaa"));
    }
}
