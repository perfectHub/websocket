package com.chen.socket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @Package com.chen.config
 * @Author chenh
 * @CreateDao 2016/12/6
 * @Description
 * @Version 1.0
 */
@Configuration
@EnableWebSocket
public class WebSocketConfigurer implements org.springframework.web.socket.config.annotation.WebSocketConfigurer {

    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        //注册处理拦截器,拦截url为socketServer的请求
        registry.addHandler(socketHandler(), "/socketServer").addInterceptors(new WebSocketInterceptor());

        //注册SockJs的处理拦截器,拦截url为/sockjs/socketServer的请求
        registry.addHandler(socketHandler(), "/sockjs/socketServer").addInterceptors(new WebSocketInterceptor()).withSockJS();

    }
    @Bean
    public WebSocketHandler socketHandler(){
        return new SocketHandler();
    }
}
