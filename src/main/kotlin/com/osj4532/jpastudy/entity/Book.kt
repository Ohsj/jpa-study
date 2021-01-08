package com.osj4532.jpastudy.entity

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("B")
data class Book (
        var author: String,
        var isbn: String
): Item()