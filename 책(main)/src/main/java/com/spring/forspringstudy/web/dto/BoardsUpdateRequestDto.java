package com.spring.forspringstudy.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardsUpdateRequestDto {
    private String title;
    private String content;

    @Builder
    public BoardsUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
