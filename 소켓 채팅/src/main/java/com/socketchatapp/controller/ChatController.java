package com.socketchatapp.controller;

import com.socketchatapp.message.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class ChatController {

	@MessageMapping("hello")		//메시지를 hello로 전송하면 해당 메소드 실행
	@SendTo("/chat/hello")			//반환값을 모든 User에 방송
	public HelloMessage hello(HelloMessage message) throws Exception {
		Thread.sleep(100);
		return message;
	}

	@MessageMapping("bye")
	@SendTo("/chat/bye")
	public HelloMessage bye(HelloMessage message) throws Exception {
		Thread.sleep(100);
		return message;
	}

	@MessageMapping("detail")
	@SendTo("/chat/detail")
	public HelloMessage detail(HelloMessage message) throws Exception {
		Thread.sleep(100);
		message.setSendDate(LocalDateTime.now());
		return message;
	}
}