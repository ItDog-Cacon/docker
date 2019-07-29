package com.byzoro.pojo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @describe 配置WebMvc视图映射
 * @return
 * @auther: xiaopang
 * @date: 5/27/2019 11:28 AM
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter{
    /**
     * MVC视图控制器配置
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //添加一个请求映射地址为/index，返回对应视图页面为webSocket
        registry.addViewController("/index").setViewName("/webSocket");
    }
}
