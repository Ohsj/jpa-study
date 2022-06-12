package me.study.jpa.v2.repository;

import me.study.jpa.v2.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
