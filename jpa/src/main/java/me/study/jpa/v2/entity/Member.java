package me.study.jpa.v2.entity;

import lombok.*;

import javax.persistence.*;

@Entity
// @Setter: 실무에서 가급적 Setter는 사용하지 않기
@Getter @Setter
// @NoArgsConstructor AccessLevel.PROTECTED: 기본 생성자 막고 싶은데, JPA 스팩상 PROTECTED로 열어두어야 함
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// @ToString은 가급적 내부 필드만(연관관계 없는 필드만
@ToString(of = {"id", "username", "age"})

@NamedQuery(
        name = "Member.findByUsername",
        query="select m from Member m where m.username = :username"
)
public class Member
//        extends JpaBaseEntity
extends BaseEntity
{

    @Id @GeneratedValue
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String username) {
        this(username, 0);
    }

    public Member(String username, int age) {
        this(username, age, null);
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;

        if (team != null) {
            changeTeam(team);
        }
    }

    // changeTeam() 으로 양방향 연관관계 한번에 처리(연관관계 편의 메소드)
    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
