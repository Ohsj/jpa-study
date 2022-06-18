package me.study.jpa.v2.repository;

import me.study.jpa.v2.entity.Member;
import me.study.jpa.v2.model.MemberDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

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
}
