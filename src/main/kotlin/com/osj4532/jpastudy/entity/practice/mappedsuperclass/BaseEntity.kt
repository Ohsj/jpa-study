package com.osj4532.jpastudy.entity.practice.mappedsuperclass

import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass

// 테이블과 매핑되지 않고 자식 클래스에 엔티티의 매핑 정보를 상속하기 위해 사용
// entity가 아니므로 em.find() 나 JPQL에서 사용할 수 없다.
// 직접 사용하는 일이 없어서 추상 클래스로 만드는 것을 추천
@MappedSuperclass
abstract class BaseEntity {
    @Id
    @GeneratedValue
    var id: Long = 0
    var name: String? = null
}