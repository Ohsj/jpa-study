package com.osj4532.jpastudy.entity.practice.joinstrategy

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("A")
data class JAlbum (
        val artist: String
): JItem()