package com.spring.forspringstudy.domain.boards;

import com.spring.forspringstudy.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter                 //getter 생성
@NoArgsConstructor      //생성자 생성
@Entity                 //JPA 어노테이션
public class Boards extends BaseTimeEntity {    //실제 DB테이블과 매칭될 클래스 (Entity 클래스)
                                                //클래스의 이름을 테이블 이름으로 CREATE
                                                //ex)SalesManager -> sales_manager

    @Id                                                         //PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)         //PK의 생성 규칙 (Identity = Auto Increament)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder        //빌더 패턴 클래스 생성
    public Boards(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
