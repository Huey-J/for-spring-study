package com.spring.forspringstudy.web;

import com.spring.forspringstudy.domain.boards.Boards;
import com.spring.forspringstudy.domain.boards.BoardsRepository;
import com.spring.forspringstudy.web.dto.BoardsSaveRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;   //테스트 검증 라이브러리 (jUnit은 assertj)

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //JPA를 테스트할 때 사용하는 라이브러리 + 랜덤 포트로 테스트
public class BoardsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;      //JPA를 테스트할 때 사용하는 라이브러리
    @Autowired
    private BoardsRepository boardsRepository;

    @After
    public void tearDown() throws Exception {
        boardsRepository.deleteAll();
    }

    @Test
    public void Boards_등록() throws Exception {
        //given
        String url = "http://localhost:" + port + "/api/v1/boards";
        String title = "title";
        String content = "content";
        BoardsSaveRequestDto requestDto = BoardsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Boards> all = boardsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }
}
