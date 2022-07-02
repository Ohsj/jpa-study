package me.study.jpa.v2.repository;

import lombok.RequiredArgsConstructor;
import me.study.jpa.v2.entity.Member;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * 사용자 정의 리포지토리 구현
 * 스프링 데이터 JPA 리포지토리는 인터페이스만 정의하고 구현체는 스프링이 자동 생성
 * 스프링 데이터 JPA가 제공하는 인터페이스를 직접 구현하면 구현해야 하는 기능이 너무 많음
 * 다양한 이유로 인터페이스의 메서드를 직접 구현하고 싶다면?
 * JPA 직접 사용( EntityManager )
 * 스프링 JDBC Template 사용
 * MyBatis 사용
 * 데이터베이스 커넥션 직접 사용 등등...
 * Querydsl 사용
 */

/**
 * 스프링 데이터 2.x 부터는 사용자 정의 구현 클래스에 리포지토리 인터페이스 이름 + Impl 을 적용하는
 * 대신에
 * 사용자 정의 인터페이스 명 + Impl 방식도 지원한다.
 * 예를 들어서 위 예제의 MemberRepositoryImpl 대신에 MemberRepositoryCustomImpl 같이 구현해도
 * 된다.
 *
 * 기존 방식보다 이 방식이 사용자 정의 인터페이스 이름과 구현 클래스 이름이 비슷하므로 더 직관적이다.
 * 추가로 여러 인터페이스를 분리해서 구현하는 것도 가능하기 때문에 새롭게 변경된 이 방식을 사용하는
 * 것을 더 권장한다
 */
@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {

    private final EntityManager em;

    @Override
    public List<Member> findMemberCustom() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
