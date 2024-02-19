package repository;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.List;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 하나의 테스트 메서드가 끝날때마다 실행되는 콜백
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("Noah1-0");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();
       // Assertions.assertEquals(member,result);
        org.assertj.core.api.Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member = new Member();
        member.setName("Noah2-0");

        Member member1= new Member();
        member1.setName("Noah2-1");
        Member member2 = new Member();
        member2.setName("Noah2-2");

        repository.save(member1);
        repository.save(member2);

        Member result = repository.findByName("Noah2-2").get();

        Assertions.assertThat(result).isEqualTo(member2);
    }

    @Test public void findAll(){
        Member member = new Member();
        member.setName("Noah3-0");
        repository.save(member);
        Member member1= new Member();
        member1.setName("Noah3-1");
        repository.save(member);
        Member member2 = new Member();
        member2.setName("Noah3-2");
        repository.save(member);
        List<Member> result = repository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(3);

    }

}
