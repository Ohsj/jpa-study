package com.osj4532.jpastudy.entity.practice.singletablestrategy

import javax.persistence.*

@Entity
// 단일 테이블 전략
// 장점 : 조인이 필요 없으므로 일반적으로 조회 성능이 가장 빠르다
//       조회 쿼리가 단순하다.
// 단점 : 자식 엔티티가 메핑한 컬럼은 모두 null을 허용해야 한다
//       한테이블에 모든 내용을 저장해 테이블이 커질수 있고 상황에 따라서는 조회가 느려질 수 있다.
// 특징 : 구분 컬럼을 꼭 사용해야 한다.
//       @DiscriminatorValue를 사용하지 않으면 기본으로 엔티티 이름을 사용한다.
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
abstract class StItem {
    @Id
    @GeneratedValue
    @Column(name = "ITEM_ID")
    var id: Long = 0


}