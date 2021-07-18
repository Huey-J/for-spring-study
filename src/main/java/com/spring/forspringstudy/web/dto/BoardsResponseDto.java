package com.spring.forspringstudy.web.dto;

import com.spring.forspringstudy.domain.boards.Boards;
import lombok.Getter;

@Getter
public class BoardsResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    public BoardsResponseDto(Boards entity) {   //DB에서 데이터를 불러올 때는 Entity를 통해서 받아오기!
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }

}
