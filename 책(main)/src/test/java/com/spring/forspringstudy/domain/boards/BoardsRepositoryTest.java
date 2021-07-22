package com.spring.forspringstudy.domain.boards;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardsRepositoryTest {

    @Autowired
    BoardsRepository boardsRepository;

    @After      //테스트가 끝날 때 마다 수행되는 메소드
    public void cleanup() {
        boardsRepository.deleteAll();       //데이터 전체 삭제
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "게시글 제목";
        String content = "게시글 본문";

        boardsRepository.save(Boards.builder()      //빌드 패턴을 활용하여 저장
                .title(title)
                .content(content)
                .author("test@jpa_test.com")
                .build());
        
        //when
        List<Boards> boardsList = boardsRepository.findAll();

        //then
        Boards boards = boardsList.get(0);
        assertThat(boards.getTitle()).isEqualTo(title);
        assertThat(boards.getContent()).isEqualTo(content);
    }
}
