package com.osj4532.jpastudy.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "MEMBER")
data class Member (
        @Id
        @Column(name = "ID")
        var id: String,
        @Column(name = "NAME")
        var username: String,
        var age: Int
)