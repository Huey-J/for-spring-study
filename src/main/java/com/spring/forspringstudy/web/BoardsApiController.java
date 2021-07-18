package com.spring.forspringstudy.web;

import com.spring.forspringstudy.web.dto.BoardsSaveRequestDto;
import com.spring.forspringstudy.web.dto.BoardsUpdateRequestDto;
import com.spring.forspringstudy.web.dto.BoardsResponseDto;
import com.spring.forspringstudy.web.service.BoardsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor    //final이 있는 필드만 생성자 생성 (AllArgsCon~는 모든 필드 생성자)
@RestController             //해당 컨트롤러는 JSON을 반환함
public class BoardsApiController {

    private final BoardsService boardsService;

    @PostMapping("/api/v1/boards")
    public Long save(@RequestBody BoardsSaveRequestDto requestDto) {
        return boardsService.save(requestDto);
    }

    @PutMapping("/api/v1/boards/{id}")  //url로써 id를 받음 (@PathVariable)
    public Long update(@PathVariable Long id, @RequestBody BoardsUpdateRequestDto requestDto) {
        return boardsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/boards/{id}")
    public BoardsResponseDto findById(@PathVariable Long id) {
        return boardsService.findById(id);
    }

}
