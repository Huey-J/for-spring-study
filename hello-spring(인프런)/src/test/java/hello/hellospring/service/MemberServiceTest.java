package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {  // 테스트 시작할 때 마다 Dependency Injection?
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {   // 테스트 끝날 때마다 메모리 지워주기
        memberRepository.clearStore();
    }

    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("tmpName");

        // when
        Long savedId = memberService.join(member);

        // then
        Member foundMember = memberService.findOne(savedId).get(); // 현업에서는 orElseGet()을 사용
        assertThat(member.getName()).isEqualTo(foundMember.getName());
    }

    @Test
    void duplicatedName() {
        // given: 중복 이름으로
        Member member1 = new Member();
        Member member2 = new Member();
        member1.setName("tmpName");
        member2.setName("tmpName");

        // when: 가입이 시도되면
        memberService.join(member1);
        try {
            memberService.join(member2);
            fail("중복 가입");
        } catch (IllegalStateException e) {     // then: 에러가 떠야함
            // MemberService의 validateDuplicateMember에서 출력한 메시지가 나오는지 확인
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}