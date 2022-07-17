package me.study.jpa.v1.repository;

import me.study.jpa.v1.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;


//@SpringBootTest(classes = JpaStudyApplication.class)
public class MemberRepositoryTest {

    @Autowired private MemberRepository memberRepository;

//    @Test
//    @Transactional
    public void testMember() {
        Member member = new Member();
        member.setName("memberA");
        Long savedId = memberRepository.save(member);

        Member findMember = memberRepository.findOne(savedId);

        assertEquals(findMember.getId(), member.getId());
        assertEquals(findMember.getName(), member.getName());
        assertEquals(findMember, member);
    }
}
