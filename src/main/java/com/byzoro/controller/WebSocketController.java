package com.byzoro.controller;

import com.byzoro.pojo.SocketMessage;
import com.byzoro.pojo.SocketResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @describe 请求控制器
 * @return
 * @auther: xiaopang
 * @date: 5/27/2019 11:23 AM
 */
@RestController
public class WebSocketController {

    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //当浏览器向服务器端发送STOMP请求时，通过@MessageMapping注解来映射/getServerTime地址
    @MessageMapping(value = "/getServerTime")
    //当服务端有消息时，会对订阅了@SendTo中的路径的客户端发送消息
    @SendTo(value = "/topic/getResponse")
    public SocketResponse serverTime(SocketMessage message) throws InterruptedException {
        return new SocketResponse(message.getMessage() + sf.format(new Date()));
    }
}
