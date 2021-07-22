package com.spring.forspringstudy.web.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;   //테스트 검증 라이브러리 (jUnit은 assertj)

public class HelloResponseDtoTest {

    @Test
    public void 롬복_테스트() {      //롬복의 getter, setter가 잘 작동 하는지
        //given
        String name = "test";
        int amount = 100;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
