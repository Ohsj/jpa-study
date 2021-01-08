package com.osj4532.jpastudy.entity

import java.util.*
import javax.persistence.MappedSuperclass

@MappedSuperclass
open class BaseEntity {
        var createDate: Date = Date()
        var lastModifiedDate: Date? = null
}