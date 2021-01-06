package com.osj4532.jpastudy.entity

import javax.persistence.*

@Entity
@Table(name = "DELIVERY")
data class Delivery (
        @Id
        @GeneratedValue
        @Column(name = "DELIVERY_ID")
        var id: Long,

        var city: String,
        var street: String,
        var zipcode: String,
        @Enumerated(EnumType.STRING)
        var status: DeliveryStatus,

        @OneToOne(mappedBy = "delivery")
        var order: Order
)

enum class DeliveryStatus {
    READY, COMP
}