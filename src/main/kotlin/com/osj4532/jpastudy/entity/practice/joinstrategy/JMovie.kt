package com.osj4532.jpastudy.entity.practice.joinstrategy

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("M")
data class JMovie (
        var director: String,
        var actor: String,
): JItem()