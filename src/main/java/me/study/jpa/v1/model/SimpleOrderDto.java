package me.study.jpa.v1.model;

import lombok.Data;
import me.study.jpa.v1.entity.Address;
import me.study.jpa.v1.entity.Order;
import me.study.jpa.v1.entity.OrderStatus;

import java.time.LocalDateTime;

@Data
public class SimpleOrderDto {

    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    public SimpleOrderDto(Order order) {
        this.orderId = order.getId();
        this.name = order.getMember().getName();
        this.orderDate = order.getOrderDate();
        this.orderStatus = order.getStatus();
        this.address = order.getDelivery().getAddress();
    }
}
