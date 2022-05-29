package me.study.jpa.v1.service;

import lombok.RequiredArgsConstructor;
import me.study.jpa.v1.entity.Member;
import me.study.jpa.v1.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    // 빈 주입 시 필드 주입 대신 생성자 주입 방식 권장
    // 변경 불가능한 객체 생성 가능
    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    @Transactional // 변경
    public Long join(Member member) {
        validateDuplicateMember(member);    // 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id);

        // drity check 를 사용한 데이터 수정
        member.setName(name);
    }
}
