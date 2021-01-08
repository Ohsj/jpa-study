package com.osj4532.jpastudy.entity.practice.singletablestrategy

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.PrimaryKeyJoinColumn

@Entity
@DiscriminatorValue("B")
// 자식 테이블의 컬럼명을 변경하고 싶다면 @PrimaryKeyJoinColumn을 사용하면 된다.
@PrimaryKeyJoinColumn(name = "BOOK_ID")
data class StBook (
        var author: String?,
        var isbn: String?
): StItem()