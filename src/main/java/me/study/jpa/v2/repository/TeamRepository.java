package me.study.jpa.v2.repository;

import me.study.jpa.v2.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
