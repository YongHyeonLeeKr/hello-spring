package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;


    // memberService 메모리 리포지토리를 주입시켜준다.
    @BeforeEach
    void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach // 하나의 테스트 메서드가 끝날때마다 실행되는 콜백
    public void afterEach() {
        memberRepository.clearStore();
    }
    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member memberFound = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(memberFound.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("noah");


        Member member2 = new Member();
        member2.setName("noah");
        //when
        memberService.join(member1);
        //member2를 조인했을 때 IllegalStateExcoption 에러가 발생을 예상함
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        // 반환하여 메세지 검증도 가능
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


        /**
         * try catch 사용하여 검증도 가능
         */

//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.??");
//        }


        //then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}