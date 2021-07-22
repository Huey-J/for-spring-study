package com.spring.forspringstudy.web.dto;

import com.spring.forspringstudy.domain.boards.Boards;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public BoardsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Boards toEntity() {      //DB 등록 전 Entity로 변환하기
        return Boards.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
