package com.osj4532.jpastudy.entity.practice.joinstrategy

import javax.persistence.*

@Entity
// 조인 전략
// 장점 : 테이블 정규화, 외래키 참조 무결성 제약조건 활용, 저장공간 효율적 사용행
// 단점 : 조회 시 조인이 많이 사용되므로 성능 저하, 조회 쿼리 복잡, 데이터 등록시 insert sql 두번 실
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE")
abstract class JItem {
    @Id
    @GeneratedValue
    @Column(name = "ITEM_ID")
    var id: Long = 0

    var name: String? = null

    var price: Int = 0
}