package com.osj4532.jpastudy.entity

import com.osj4532.jpastudy.enum.OrderStatus
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "Orders")
data class Order (
        @Id
        @GeneratedValue
        @Column(name = "ORDER_ID")
        val id: Long,

        val memberId: Long,

        @Temporal(TemporalType.TIMESTAMP)
        var orderDate: Date,

        @Enumerated(EnumType.STRING)
        var status: OrderStatus
)