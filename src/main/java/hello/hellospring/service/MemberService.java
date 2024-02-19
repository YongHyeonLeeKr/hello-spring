package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /*
    회원가입
    - 같은 이름이 있는 회원은 안되요
     */
    public Long join(Member member) {

        // 같은 이름의 중복 회원 방지
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    /*
    전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }
}
