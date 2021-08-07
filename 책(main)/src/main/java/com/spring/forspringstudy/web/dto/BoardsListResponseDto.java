package com.spring.forspringstudy.web.dto;

import com.spring.forspringstudy.domain.boards.Boards;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardsListResponseDto {
    private Long id;
    private String title;
    private String author;
    private LocalDateTime modifeidDate;

    public BoardsListResponseDto(Boards entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.modifeidDate = entity.getModifiedDate();
    }
}
