package com.osj4532.jpastudy.entity

import javax.persistence.*

@Entity
@Table(name = "ITEM")
data class Item (
        @Id
        @GeneratedValue
        @Column(name = "ITEM_ID")
        val id: Long,

        var name: String,
        var price: Int,
        var stockQuantity: Int,

        @ManyToMany(mappedBy = "items")
        var categories: MutableList<Category> = mutableListOf()
)