package com.spring.forspringstudy.web;

import com.spring.forspringstudy.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController     //해당 컨트롤러는 JSON을 반환함
public class HelloController {
    
    @GetMapping("/hello")   //GET요청 응답
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name,     //url로 받은 파라미터 변수 값 (~~~?name=hi&amount=100)
                                     @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }

}