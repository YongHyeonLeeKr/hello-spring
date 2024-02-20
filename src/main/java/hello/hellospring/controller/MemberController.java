package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    private  final MemberService memberService;


    //
    @Autowired // 생성자에 스프링에 등록해놓고 멤버서비스를 공동 사용
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

}
