package com.byzoro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DockerApplication {
    public static void main(String[] args) {
//            SpringApplication application = new SpringApplication(ApplicationTest.class);
//            application.setBannerMode(Banner.Mode.OFF);
//            application.run(args);
//        SSLServer server = new SSLServer();
//        server.init();
//        server.start();
        String webserviceAddress = "http://10.100.2.14:9999/ws";
//        Endpoint.publish(webserviceAddress, new MTProxyServiceImpl());
        System.setProperty("tomcat.util.http.parser.HttpsParser.requestTargetAllow","|{}");
        SpringApplication.run(DockerApplication.class, args);
        System.out.println("启动成功了 "+webserviceAddress);
    }
}
