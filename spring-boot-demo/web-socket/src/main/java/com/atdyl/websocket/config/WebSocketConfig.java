package com.atdyl.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author Dong YL
 * @version V1.0 2020/1/2 16:08
 */
@Configuration
public class WebSocketConfig {

    //创建服务端点，有了这个Bean就可以通过@ServerEndpoint定义端点服务类
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
