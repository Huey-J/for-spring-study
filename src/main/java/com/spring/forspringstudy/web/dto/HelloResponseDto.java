package com.spring.forspringstudy.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter                         //getter메소드 생성
@RequiredArgsConstructor        //final이 있는 필드만 생성자 생성 (AllArgsCon~는 모든 필드 생성자)
public class HelloResponseDto {
    private final String name;
    private final int amount;
}
