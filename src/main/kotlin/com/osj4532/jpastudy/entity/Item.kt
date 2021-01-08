package com.osj4532.jpastudy.entity

import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
abstract class Item : BaseEntity() {
        @Id
        @GeneratedValue
        @Column(name = "ITEM_ID")
        val id: Long = 0

        var name: String? = null
        var price: Int = 0
        var stockQuantity: Int = 0

        @ManyToMany(mappedBy = "items")
        var categories: MutableList<Category> = mutableListOf()
}