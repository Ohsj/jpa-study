package me.study.jpa.v2.controller;

import lombok.RequiredArgsConstructor;
import me.study.jpa.v2.entity.Member;
import me.study.jpa.v2.model.MemberDto;
import me.study.jpa.v2.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

    // 스프링 부트 기본 설정
    // spring.data.web.pageable.default-page-size=20
    // spring.data.web.pageable.max-page-size=2000
    @GetMapping("/members")
    public Page<Member> list(Pageable pageable) {
        Page<Member> page = memberRepository.findAll(pageable);
        return page;
    }

    // 개별설정
    // @PageableDefault
    @GetMapping("/members_page")
    public Page<Member> eachSettingList(@PageableDefault(size= 12, sort = "username", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Member> page = memberRepository.findAll(pageable);
        return page;
    }

    // 페이징 정보가 둘 이상이면 접두사로 구분
    @GetMapping("/multi_page")
    public Page<Member> multiPage(
            @Qualifier("member") Pageable memberPageable,
            @Qualifier("order") Pageable OrderPageable
    ) {
        // 파라미터는 member_page, member_size, member_sort
        // order_page, order_size, order_sort 로 구분
        Page<Member> page = memberRepository.findAll(memberPageable);
        return page;
    }

    // 결과값 DTO로 변환
    @GetMapping("/members_dto")
    public Page<MemberDto> dtoList(Pageable pageable) {
        Page<MemberDto> page = memberRepository.findAll(pageable)
                .map(MemberDto::new);
        return page;
    }

    // 스프링 데이터는 page를 0부터 시작한다.
    // 만약 1부터 시작하고 싶다면
    // 1. Pageable, Page를 파리미터와 응답 값으로 사용히지 않고, 직접 클래스를 만들어서 처리한다. 그리고
    //직접 PageRequest(Pageable 구현체)를 생성해서 리포지토리에 넘긴다. 물론 응답값도 Page 대신에
    //직접 만들어서 제공해야 한다.

    // 2. spring.data.web.pageable.one-indexed-parameters 를 true 로 설정한다. 그런데 이 방법은
    //web에서 page 파라미터를 -1 처리 할 뿐이다. 따라서 응답값인 Page 에 모두 0 페이지 인덱스를
    //사용하는 한계가 있다
}
