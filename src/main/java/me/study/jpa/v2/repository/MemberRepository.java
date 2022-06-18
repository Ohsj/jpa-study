package me.study.jpa.v2.repository;

import me.study.jpa.v2.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    // @Query를 생략하고 메서드 이름만으로 Named 쿼리 호출 가능
//    @Query(name = "Member.findByUsername")
    List<Member> findByUsername(@Param("username") String username);
}
