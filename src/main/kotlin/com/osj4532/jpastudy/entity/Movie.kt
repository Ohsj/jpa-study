package com.osj4532.jpastudy.entity

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("M")
data class Movie (
        var director: String,
        var actor: String
): Item()