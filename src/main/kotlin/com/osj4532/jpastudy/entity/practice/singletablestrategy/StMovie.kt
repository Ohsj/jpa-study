package com.osj4532.jpastudy.entity.practice.singletablestrategy

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("M")
data class StMovie (
        var director: String?,
        var actor: String?,
): StItem()