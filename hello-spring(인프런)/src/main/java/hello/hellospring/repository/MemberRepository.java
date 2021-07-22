package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);                 // 회원 저장
    Optional<Member> findById(Long id);         // 회원 찾기 by pk
    Optional<Member> findByName(String name);   // 회원 찾기 by name
    List<Member> findAll();                     // 전체 회원 리스트
}
