package com.osj4532.jpastudy.entity

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("A")
data class Album (
        var artist: String,
        var etc: String
): Item()