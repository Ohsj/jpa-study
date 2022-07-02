package me.study.jpa.v2.repository;

import me.study.jpa.v2.entity.Member;
import me.study.jpa.v2.model.MemberDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNullApi;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;

/**
 * 조회 결과가 많거나 없으면?
 * 컬렉션
 * 결과 없음: 빈 컬렉션 반환
 * 단건 조회
 * 결과 없음: null 반환
 * 결과가 2건 이상: javax.persistence.NonUniqueResultException 예외 발생
 *
 * 단건으로 지정한 메서드를 호출하면 스프링 데이터 JPA는 내부에서 JPQL의
 * Query.getSingleResult() 메서드를 호출한다. 이 메서드를 호출했을 때 조회 결과가 없으면
 * javax.persistence.NoResultException 예외가 발생하는데 개발자 입장에서 다루기가 상당히
 * 불편하다. 스프링 데이터 JPA는 단건을 조회할 때 이 예외가 발생하면 예외를 무시하고 대신에 null 을
 * 반환한다.
 */
public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    // @Query를 생략하고 메서드 이름만으로 Named 쿼리 호출 가능
//    @Query(name = "Member.findByUsername")
    List<Member> findByUsername(@Param("username") String username);

    // 메소드에 쿼리 정의
    // 실무에서는 메소드 이름으로 쿼리 생성 기능은 파라미터가 증가가하면 메서드 이름이 매우 지저분해져서 Query 기능을 자주 사용한다
    @Query("select m from Member m where m.username= :username and m.age = :age")
    List<Member> findUser(@Param("username") String username, @Param("age") int age);

    // DTO로 직접 조회
    @Query("select new me.study.jpa.v2.model.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();

    // 파라미터 바인딩
    // 코드 가독성 및 유지 보수에 파라미터 바인딩이 좋다.
    @Query("select m from Member m where m.username = :name")
    Member findMembers(@Param("name") String username);

    // 컬렉션 파라미터 바인딩
    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") List<String> names);

    // 페이징과 정렬 사용 예제
//    Page<Member> findByUsername(String name, Pageable pageable);
//    Slice<Member> findByUsername(String name, Pageable pageable);
//    List<Member> findByUsername(String name, Pageable pageable);
//    List<Member> findByUsername(String name, Sort sort);

    Page<Member> findByAge(int age, Pageable pageable);

    // count 쿼리는 매우 무겁기 떄문에 count 쿼리를 따로 분리할수도 있다.
    // 카운트 쿼리 분리(이건 복잡한 sql에서 사용, 데이터는 left join, 카운트는 left join 안해도 됨)
    @Query(value = "select m from Member m",
            countQuery = "select count(m.username) from Member m")
    Page<Member> findMemberAllCountBy(Pageable pageable);

    /**
     * 벌크성 수정, 삭제 쿼리는 @Modifying 어노테이션을 사용
     *
     * 벌크성 쿼리를 실행하고 나서 영속성 컨텍스트 초기화: @Modifying(clearAutomatically = true) (이 옵션의 기본값은 false )
     * 이 옵션 없이 회원을 findById 로 다시 조회하면 영속성 컨텍스트에 과거 값이 남아서 문제가 될 수있다. 만약 다시 조회해야 하면 꼭 영속성 컨텍스트를 초기화 하자.
     *
     * 벌크 연산은 영속성 컨텍스트를 무시하고 실행하기 때문에, 영속성 컨텍스트에 있는 엔티티의 상태와
     * DB에 엔티티 상태가 달라질 수 있다.
     * 권장하는 방안
     * 1. 영속성 컨텍스트에 엔티티가 없는 상태에서 벌크 연산을 먼저 실행한다.
     * 2. 부득이하게 영속성 컨텍스트에 엔티티가 있으면 벌크 연산 직후 영속성 컨텍스트를 초기화 한다
     */
    @Modifying
    @Query("update Member m set m.age = m.age + 1 where m.age >= :age")
    int bulkAgePlus(@Param("age") int age);

    // 연관된 엔티티를 N+1 문제 없이 조회 하려면 페치조인이 필요하다.
    @Query("select m from Member m left join fetch m.team")
    List<Member> findMemberFetchJoin();

    // EntityGraph를 사용하면 JPQL 없이 페치 조인을 사용할 수 있다.
    // 사실상 페치 조인의 간편버전
    // 조인시 left outer join 을 사용한다.

    // 공통 메서드 오버라이드
    @Override
    @EntityGraph(attributePaths = {"team"})
    List<Member> findAll();

    // JPQL + 엔티티 그래프
    @EntityGraph(attributePaths = {"team"})
    @Query("select m from Member m")
    List<Member> findMemberEntityGraph();

    // 메서드 이름으로 쿼리에서 특히 편리하다.
//    @EntityGraph(attributePaths = {"team"})
//    List<Member> findByUsername(String username);

    // Hint & Lock
    // JPA 구현체에게 제공하는 힌트
    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true"))
    Member findReadOnlyByUsername(String username);

    // 쿼리 힌트 Page 추가
    // forCounting 반환 타입으로 Page 인터페이스를 적용하면 추가로 호출하는 페이징을 위한 count 쿼리도 쿼리 힌트 적용(기본값 true)
    @QueryHints(value = {@QueryHint(name = "org.hibernate.readOnly", value = "true")}, forCounting = true)
    Page<Member> findByUsername(String username, Pageable pageable);

//    @Lock(LockModeType.PESSIMISTIC_WRITE)
//    List<Member> findByUsername(String name);
}
