package com.spring.forspringstudy.domain.boards;

import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository<Entity클래스, PK타입>
//자동으로 CRUD메소드 생성
public interface BoardsRepository extends JpaRepository<Boards, Long> {

}
