package com.byzoro.pojo;

import java.io.*;
import java.net.URL;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.byzoro.utils.JSONUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class HttpsRequest {
//    private final static String PFX_PATH = "D:\\docker\\src\\main\\resources\\tonggang\\client(1).p12";
    private final static String PFX_PATH = "D:\\docker\\src\\main\\resources\\yjzhssl\\clientxingtongyuan.p12";
    private final static String PATH ="D:\\docker\\src\\main\\resources\\ss\\private.pem";
    private final static String PFX_PWD = "123456"; //客户端证书密码及密钥库密码

    /** * 向指定URL发送GET方法的请求,默认编码UTF-8 * * @param url * 发送请求的URL * @param param * 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。 * @return URL 所代表远程资源的响应结果 */
    public static String sendGet(String url, String param) {
        return sendGet(url, param, "utf-8");
    }

    /** * 向指定URL发送GET方法的请求 * * @param url * 发送请求的URL * @param param * 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。 * @param charset * 网页编码 * @return URL 所代表远程资源的响应结果 */
    public static String sendGet(String url, String param,String charSet) {
        String result = "";
        BufferedReader in = null;
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(new KeyManager[0],
                    new TrustManager[] { new DefaultTrustManager() },
                    new SecureRandom());
            SSLContext.setDefault(ctx);

            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            HttpsURLConnection connection = (HttpsURLConnection) realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            connection.setHostnameVerifier(new HostnameVerifier() {

                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                //System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),charSet));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }


    /** * 向指定 URL 发送POST方法的请求，默认编码UTF-8 * * @param url * 发送请求的 URL * @param param * 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。 * @return 所代表远程资源的响应结果 */
    public static String sendPost(String url, String param){
        return sendPost(url, param, "utf-8");
    }

    /** * 向指定 URL 发送POST方法的请求 * * @param url * 发送请求的 URL * @param param * 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。 * @param charSet * 网页编码 * @return 所代表远程资源的响应结果 */
    public static String sendPost(String url, String param , String charSet) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(new KeyManager[0],
                    new TrustManager[] { new DefaultTrustManager() },
                    new SecureRandom());
            SSLContext.setDefault(ctx);


            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            HttpsURLConnection conn = (HttpsURLConnection) realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);

            conn.setHostnameVerifier(new HostnameVerifier() {

                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(),charSet));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }



    private static class DefaultTrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] arg0, String arg1)
                throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] arg0, String arg1)
                throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

    }

    public static String sslRequestGet(String url,String data) throws Exception {
//        System.setProperty("javax.net.ssl.keyStore","D:\\docker\\src\\main\\resources\\success\\server.key.p12");
        System.setProperty("javax.net.ssl.keyStorePassword","123456");
        System.setProperty("https.protocols", "TLSv1");
        System.setProperty("javax.net.debug","ssl");
        KeyStore keyStore = KeyStore.getInstance("JKS");
                 InputStream instream = new FileInputStream(new File(PFX_PATH));
                 try {
                         // 这里就指的是KeyStore库的密码
                         keyStore.load(instream, PFX_PWD.toCharArray());
                     } finally {
                         instream.close();
                     }

                 SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, PFX_PWD.toCharArray()).build();
                 SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext
                                 , new String[] { "TLSv1" }  // supportedProtocols ,这里可以按需要设置
                                 , null  // supportedCipherSuites
                                 , SSLConnectionSocketFactory.getDefaultHostnameVerifier());

                 CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
                 try {
                     HttpPost httppost = new HttpPost(url);
                     List<NameValuePair> params=new ArrayList<NameValuePair>();
                    //建立一个NameValuePair数组，用于存储欲传送的参数
                     params.add(new BasicNameValuePair("pwd","2544"));
                     Data data1 = new Data();
                     data1.setName("adf");
                     httppost.setEntity(new UrlEncodedFormEntity(params));
                     //          httpost.addHeader("Connection", "keep-alive");// 设置一些heander等
                         CloseableHttpResponse response = httpclient.execute(httppost);
                         try {
                                 HttpEntity entity = response.getEntity();
                                 // 返回结果
                                 String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
                                 EntityUtils.consume(entity);
                                 System.err.println(jsonStr);
                                 return jsonStr;
                             } finally {
                                 response.close();
                             }
                     } finally {
                         httpclient.close();
                     }
             }

    public static String sslRequestPost(String url,Map<String, Object> map,String charset) throws Exception {
        //        System.setProperty("javax.net.ssl.keyStore","D:\\docker\\src\\main\\resources\\success\\server.key.p12");
        System.setProperty("javax.net.ssl.keyStorePassword","123456");
//        System.setProperty("https.protocols", "TLSv1");
        System.setProperty("javax.net.debug","ssl");
        KeyStore keyStore = KeyStore.getInstance("pkcs12");
        InputStream instream = new FileInputStream(new File(PFX_PATH));
        try {
            // 这里就指的是KeyStore库的密码
            keyStore.load(instream, PFX_PWD.toCharArray());
        } finally {
            instream.close();
        }

        SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, PFX_PWD.toCharArray()).build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext
                , new String[] { "TLSv1" }  // supportedProtocols ,这里可以按需要设置
                , null  // supportedCipherSuites
                , SSLConnectionSocketFactory.getDefaultHostnameVerifier());

        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        HttpPost httpPost = null;
        try {
            httpPost = new HttpPost(url);
            // 设置参数
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> elem = (Map.Entry<String, Object>) iterator
                        .next();
                list.add(new BasicNameValuePair(elem.getKey(), elem.getValue().toString()));
            }
            if (list.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,
                        charset);
                httpPost.setEntity(entity);
            }
            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                HttpEntity entity = response.getEntity();
                // 返回结果
                String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
                EntityUtils.consume(entity);
//                System.err.println("jsonStr"+jsonStr);
                return jsonStr;
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }

    public static String sslRequestPostString(String url,String data,String charset) throws Exception {
        //        System.setProperty("javax.net.ssl.keyStore","D:\\docker\\src\\main\\resources\\success\\server.key.p12");
        System.setProperty("javax.net.ssl.keyStorePassword","123456");
//        System.setProperty("https.protocols", "TLSv1");
        System.setProperty("javax.net.debug","ssl");
        KeyStore keyStore = KeyStore.getInstance("pkcs12");
        InputStream instream = new FileInputStream(new File(PFX_PATH));
        try {
            // 这里就指的是KeyStore库的密码
            keyStore.load(instream, PFX_PWD.toCharArray());
        } finally {
            instream.close();
        }

        SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, PFX_PWD.toCharArray()).build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext
                , new String[] { "TLSv1" }  // supportedProtocols ,这里可以按需要设置
                , null  // supportedCipherSuites
                , SSLConnectionSocketFactory.getDefaultHostnameVerifier());

        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        HttpPost httpPost = null;
        try {
            httpPost = new HttpPost(url);
            StringEntity requestEntity = new StringEntity(data, "UTF-8");
            httpPost.setEntity(requestEntity);
            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                HttpEntity entity = response.getEntity();
                // 返回结果
                String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
                EntityUtils.consume(entity);
//                System.err.println("jsonStr"+jsonStr);
                return jsonStr;
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }
}

