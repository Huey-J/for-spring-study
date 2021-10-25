package com.spring.forspringstudy.web.service;

import com.spring.forspringstudy.domain.boards.Boards;
import com.spring.forspringstudy.domain.boards.BoardsRepository;
import com.spring.forspringstudy.web.dto.BoardsListResponseDto;
import com.spring.forspringstudy.web.dto.BoardsResponseDto;
import com.spring.forspringstudy.web.dto.BoardsSaveRequestDto;
import com.spring.forspringstudy.web.dto.BoardsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor    //final이 있는 필드만 생성자 생성 (AllArgsCon~는 모든 필드 생성자)
@Service                    //서비스는 트랜잭션, 도메인의 순서를 보장함
public class BoardsService {
    private final BoardsRepository boardsRepository;

    @Transactional
    public Long save(BoardsSaveRequestDto requestDto) {
        //Entity로 변환 후 Repository에서 저장, id값 반환
        return boardsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, BoardsUpdateRequestDto requestDto) {
        //id로 찾고 없으면 Exception
        Boards boards = boardsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        //DB수정 요청 (Entity 활용)
        boards.update(requestDto.getTitle(),requestDto.getContent());

        /* DB(repository)에 save하는 부분이 없는 이유 (더티체킹 때문) */
        //= JPA의 영속성 컨텍스트 때문
        //Transaction이 끝나는 시점에서의 엔티티 값이 테이블에 저장 됨

        return id;
    }

    @Transactional
    public BoardsResponseDto findById(Long id) {
        //DB로 불러온 데이터는 entity로 받기
        Boards entity = boardsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        //entity 데이터는 DTO로 감싸서 반환
        return new BoardsResponseDto(entity);
    }

                                        //readOnly = true 옵션 : 트랜잭션 범위는 유지하되 조회 기능만 남김
    @Transactional(readOnly = true)     //                  => 조회 속도 개선 (등록, 수정, 삭제 기능이 없는 서비스 메소드에서 사용하면 좋다)
    public List<BoardsListResponseDto> findAllDesc() {
        return boardsRepository.findAllDesc().stream()
                .map(BoardsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete (Long id) {
        //존재하는 boards인지 확인 후 삭제
        Boards boards = boardsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        boardsRepository.delete(boards);
     }


}
