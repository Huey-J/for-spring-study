package com.spring.forspringstudy.web.service;

import com.spring.forspringstudy.domain.boards.BoardsRepository;
import com.spring.forspringstudy.web.dto.BoardsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor    //final이 있는 필드만 생성자 생성 (AllArgsCon~는 모든 필드 생성자)
@Service                    //서비스는 트랜잭션, 도메인의 순서를 보장함
public class BoardsService {
    private final BoardsRepository boardsRepository;

    @Transactional
    public Long save(BoardsSaveRequestDto requestDto) {
        //Entity로 변환 후 Repository에서 저장, id값 반환
        return boardsRepository.save(requestDto.toEntity()).getId();
    }
}
