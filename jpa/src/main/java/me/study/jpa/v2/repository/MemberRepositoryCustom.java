package me.study.jpa.v2.repository;

import me.study.jpa.v2.entity.Member;

import java.util.List;

public interface MemberRepositoryCustom {
    List<Member> findMemberCustom();
}
