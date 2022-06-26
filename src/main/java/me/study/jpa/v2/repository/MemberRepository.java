package me.study.jpa.v2.repository;

import me.study.jpa.v2.entity.Member;
import me.study.jpa.v2.model.MemberDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
}
