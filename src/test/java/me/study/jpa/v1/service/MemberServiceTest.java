package me.study.jpa.v1.service;

import me.study.jpa.v1.entity.Member;
import me.study.jpa.v1.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

//    @Test
    public void 회원가입() throws Exception {
        // Given
        Member member = new Member();
        member.setName("kim");

        // When
        Long saveId = memberService.join(member);

        // Then
        assertEquals(member, memberRepository.findOne(saveId));
    }

//    @Test
    public void 중복_회원_예외() throws Exception {
        // Given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        // When
        // Then
        memberService.join(member1);
        assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2);
        });
    }

//    @Test
    public void 전체_회원_조회() throws Exception {
        // Given
        Member member1 = new Member();
        member1.setName("맴버 1");

        memberService.join(member1);

        // When
        List<Member> memberList = memberService.findMembers();
        for (Member member : memberList) {
            System.out.println(member.getName());
        }

        // Then
        assertEquals(1, memberList.size());
    }

//    @Test
    public void 단일_회원_조회() throws Exception {
        // Given
        Member member1 = new Member();
        member1.setName("맴버 1");

        memberService.join(member1);

        // When
        Member member = memberService.findOne(member1.getId());

        // Then
        assertEquals(member1, member);
    }
}
