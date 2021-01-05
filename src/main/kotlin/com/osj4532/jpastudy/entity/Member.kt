package com.osj4532.jpastudy.entity

import javax.persistence.*

@Entity
@Table(name = "Member")
data class Member (
        @Id
        @GeneratedValue
        @Column(name = "MEMBER_ID")
        var id: Long,

        var name: String,

        var city: String,
        var street: String,
        var zipcode: String
)