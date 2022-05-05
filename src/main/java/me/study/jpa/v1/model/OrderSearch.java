package me.study.jpa.v1.model;

import lombok.Getter;
import me.study.jpa.v1.entity.OrderStatus;

@Getter
public class OrderSearch {
    private String memberName;
    private OrderStatus orderStatus;
}
