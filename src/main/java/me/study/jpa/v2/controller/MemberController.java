package me.study.jpa.v2.controller;

import lombok.RequiredArgsConstructor;
import me.study.jpa.v2.entity.Member;
import me.study.jpa.v2.repository.MemberRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    // 도메인 클래스 컨버터
    // id값에 맞는 데이터가 없을 경우 MissingPathVariableException 발생
    @GetMapping("/member/{id}")
    public String findMember(@PathVariable("id") Member member) {
        return member.getUsername();
    }
}
