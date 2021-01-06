package com.osj4532.jpastudy.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(
        name = "EX_MEMBER",
// 제약 조건
//        uniqueConstraints = [
//                UniqueConstraint(
//                        name = "NAME_AGE_UNIQUE",
//                        columnNames = arrayOf("NAME", "AGE"))]
)
// 필드가 많거나 저장되는 내용이 너무 크면 수정된 데이터만 사용해서 동적으로 update sql을 생성한다.(컬럼이 30개 이상일 경우)
// @DynamicUpdate
// 데이터가 존재하는 (null이 아닌) 필드만으로 insert sql을 동적으로 생성한다.
// @DynamicInsert
data class ExMember (
        @Id
        @Column(name = "ID")
        var id: String,
        @Column(name = "NAME", nullable = false, length = 10)
        var username: String,
        var age: Int,
        @Enumerated(EnumType.STRING)
        var roleType: RoleType? = null,
        @Temporal(TemporalType.TIMESTAMP)
        var createdDate: Date? = null,
        @Temporal(TemporalType.TIMESTAMP)
        var lastModifiedDate: Date? = null,
        @Lob
        var description: String? = null
)

enum class RoleType {
        ADMIN, USER
}