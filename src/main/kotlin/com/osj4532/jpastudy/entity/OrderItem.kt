package com.osj4532.jpastudy.entity

import javax.persistence.*

@Entity
@Table(name = "ORDER_ITEM")
data class OrderItem (
        @Id
        @GeneratedValue
        @Column(name = "ORDER_ITEM_ID")
        val id: Long,

        var orderPrice: Int,
        var count: Int,

        @ManyToOne
        @JoinColumn(name = "ITEM_ID")
        var item: Item,

        @ManyToOne
        @JoinColumn(name = "ORDER_ID")
        var order: Order
) : BaseEntity()