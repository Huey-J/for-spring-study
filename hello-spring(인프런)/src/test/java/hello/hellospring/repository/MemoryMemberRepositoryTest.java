package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach  // 각 테스트 메소드가 끝날 때 마다 실행되는 함수
    public void afterEach() {
        repository.clearStore();    // 데이터 비우기
    }

    @Test   // 메소드 실행 시 MemoryMemberRepository의 save가 실행됨
    public void save() {
        Member member = new Member();
        member.setName("tmpName");

        repository.save(member);    // 저장

        Member result =  repository.findById(member.getId()).get(); // 불러오기

        // 위의 result와 member가 같은 것인지 확인
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        Member member2 = new Member();

        member1.setName("tmpName1");
        member2.setName("tmpName2");

        repository.save(member1);
        repository.save(member2);

        Member result = repository.findByName("tmpName1").get();

        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        Member member2 = new Member();

        member1.setName("tmpName1");
        member2.setName("tmpName2");

        repository.save(member1);
        repository.save(member2);

        List<Member> result = repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
