package com.osj4532.jpastudy.entity

import javax.persistence.*

@Entity
@Table(name = "ORDER_ITEM")
data class OrderItem (
        @Id
        @GeneratedValue
        @Column(name = "ORDER_ITEM_ID")
        val id: Long,

        val orderId: Long,

        val itemId: Long,

        var orderPrice: Int,
        var count: Int
)