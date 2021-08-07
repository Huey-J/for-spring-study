package com.socketchatapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration                      //스프링 부트를 설정하는 클래스임.
@EnableWebSocketMessageBroker       //Message Broker는 언어간 소켓통신이 가능하도록 만듬
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {    //sockJS(JS의 소켓함수)의 접속을 허용
                                                                            //handshake를 통해 http->ws로 upgrade
        registry.addEndpoint("/socket-app-door").withSockJS();
                                                                            //Simple Text Oriented Message Protocol (STOMP)
                                                                            // - 간단한 텍스트 기반 메시지 프로토콜
                                                                            // - command + header + body로 구성
                                                                            // -
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/chat", "/queue");     //1대1 통신은 queue
                                                                             //1대N 통신은 topic
        registry.setApplicationDestinationPrefixes("/");                     //소켓 메소드 URL의 접두사
    }
}