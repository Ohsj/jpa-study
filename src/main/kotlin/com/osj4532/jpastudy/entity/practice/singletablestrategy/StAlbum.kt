package com.osj4532.jpastudy.entity.practice.singletablestrategy

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("A")
data class StAlbum (
        val artist: String?
): StItem()