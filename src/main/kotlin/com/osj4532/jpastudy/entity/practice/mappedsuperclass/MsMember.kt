package com.osj4532.jpastudy.entity.practice.mappedsuperclass

import javax.persistence.AttributeOverride
import javax.persistence.AttributeOverrides
import javax.persistence.Column
import javax.persistence.Entity

@Entity
// 하나의 재정의
//@AttributeOverride(name = "id", column = Column(name = "MEMBER_ID"))
// 둘 이상 재정의
@AttributeOverrides(
        AttributeOverride(name = "id", column = Column(name = "MEMBER_ID")),
        AttributeOverride(name = "name", column = Column(name = "MEMBER_NAME"))
)
data class MsMember (
        var email: String
): BaseEntity()