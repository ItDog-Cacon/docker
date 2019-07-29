package com.byzoro.pojo;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * @describe 编写WebSocket的配置类
 * @return
 * @auther: xiaopang
 * @date: 5/27/2019 11:09 AM
 */
@Configuration
//开启对WebSocket的支持
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{

    /**
     * @describe 这个方法的作用是添加一个服务端点，来接收客户端的连接。
     * 注册一个STOMP协议的节点，并映射到指定的URL
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //注册一个STOMP的endpoint,并指定使用SockJS协议
        // TODO: 5/27/2019 表示添加了一个/endpointSocket端点，客户端就可以通过这个端点来进行连接。
        registry.addEndpoint("/endpointSocket").withSockJS();
    }

    /**
     * @describe 这个方法的作用是定义消息代理，
     * 通俗一点讲就是设置消息连接请求的各种规范信息。
     * 配置消息代理
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //配置一个广播式的消息代理
        registry.enableSimpleBroker("/topic");
    }
}