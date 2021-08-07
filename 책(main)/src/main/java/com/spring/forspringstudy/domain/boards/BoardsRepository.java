package com.spring.forspringstudy.domain.boards;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//JpaRepository<Entity클래스, PK타입>
//자동으로 CRUD메소드 생성
public interface BoardsRepository extends JpaRepository<Boards, Long> {

    //Spring Data JPA에서 제공하는 Naming으로 해결 가능하지만 쿼리문이 더 가독성이 좋음
    //
    @Query("SELECT b FROM Boards b ORDER BY b.id DESC")
    List<Boards> findAllDesc();

}