package com.spring.forspringstudy.web;

import com.spring.forspringstudy.web.dto.HelloResponseDto;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController     //해당 컨트롤러는 JSON을 반환함
public class HelloController {

    @ApiOperation(value="value 입니다.", notes="notes 입니다.")
    @GetMapping("/hello")   //GET요청 응답
    public String hello() {
        return "hello";
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name="name", value="여기다가 설명을..."),
            @ApiImplicitParam(name="amount", value="value_이름2", required=true, dataType="데이터타입2", paramType="쿼리타입2?")
    })
    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name,     //url로 받은 파라미터 변수 값 (~~~?name=hi&amount=100)
                                     @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }
}