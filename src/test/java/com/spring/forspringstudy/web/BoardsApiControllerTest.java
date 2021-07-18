package com.spring.forspringstudy.web;

import com.spring.forspringstudy.domain.boards.Boards;
import com.spring.forspringstudy.domain.boards.BoardsRepository;
import com.spring.forspringstudy.web.dto.BoardsResponseDto;
import com.spring.forspringstudy.web.dto.BoardsSaveRequestDto;
import com.spring.forspringstudy.web.dto.BoardsUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
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
        //GET요청 응답 검사
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        //DB검사
        List<Boards> all = boardsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    public void Boards_수정() throws Exception {
        //given
        Boards savedBoards = boardsRepository.save(     //저장
                Boards.builder()
                        .title("title")
                        .content("content")
                        .author("author")
                        .build());

        Long updatedId = savedBoards.getId();

        String expectedTitle = "new_title";
        String expectedContent = "new_content";
        BoardsUpdateRequestDto requestDto = BoardsUpdateRequestDto.builder()    //수정될 객체
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        String url = "http://localhost:" + port + "/api/v1/boards/" + updatedId;
        HttpEntity<BoardsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(    //수정 요청
                url, HttpMethod.PUT, requestEntity, Long.class);

        //then
        //PUT요청 응답 검사
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        
        //DB검사
        List<Boards> all = boardsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
    }

    @Test
    public void BaseTimeEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.of(2021, 07, 18, 0, 0, 0);
        boardsRepository.save(
                Boards.builder()
                        .title("title")
                        .content("content")
                        .author("author")
                        .build());

        //when
        List<Boards> boardsList = boardsRepository.findAll();

        //then
        Boards boards = boardsList.get(0);
        System.out.println(">>>>> createdDate=" + boards.getCreatedDate()
                + ", modifiedDate=" + boards.getModifiedDate());

        assertThat(boards.getCreatedDate()).isAfter(now);
        assertThat(boards.getModifiedDate()).isAfter(now);
    }
}
