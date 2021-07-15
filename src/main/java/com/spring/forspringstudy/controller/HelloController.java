package com.spring.forspringstudy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController     // 해당 컨트롤러는 JSON을 반환함
public class HelloController {
    
    @GetMapping("/hello")   // GET요청 응답
    public String hello() {
        return "Hello";
    }

}
