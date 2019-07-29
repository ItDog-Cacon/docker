package com.byzoro.pojo;

/**
 * @describe 服务器向浏览器响应数据的封装实体类
 * @return
 * @auther: xiaopang
 * @date: 5/27/2019 11:11 AM
 */
public class SocketResponse {

    private String responseMessage;

    public SocketResponse(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}
