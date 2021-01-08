package com.osj4532.jpastudy.entity.practice.mappedsuperclass

import javax.persistence.Entity

@Entity
data class MsSeller (
        var shopName: String
): BaseEntity()