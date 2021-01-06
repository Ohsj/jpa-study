package com.osj4532.jpastudy.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "Orders")
class Order {
        @Id
        @GeneratedValue
        @Column(name = "ORDER_ID")
        val id: Long = 0

        @Temporal(TemporalType.TIMESTAMP)
        var orderDate: Date = Date()

        @Enumerated(EnumType.STRING)
        var status: OrderStatus = OrderStatus.ORDER

        @ManyToOne
        @JoinColumn(name = "MEMBER_ID")
        var member: Member? = null
                set(member) {
                        if (this.member != null) {
                                this.member!!.orders.remove(this)
                        }
                        field = member
                        member!!.orders.add(this)
                }

        @OneToMany(mappedBy = "order")
        var orderItems: List<OrderItem> = listOf()

        @OneToOne
        @JoinColumn(name = "DELIVERY_ID")
        var delivery: Delivery? = null
                set(delivery) {
                        field = delivery
                        delivery?.order = this
                }
}

enum class OrderStatus {
        ORDER, CANCLE
}