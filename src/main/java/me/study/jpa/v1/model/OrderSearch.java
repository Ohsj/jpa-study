package me.study.jpa.v1.model;

import lombok.Getter;
import lombok.Setter;
import me.study.jpa.v1.entity.OrderStatus;

@Getter @Setter
public class OrderSearch {
    private String memberName;
    private OrderStatus orderStatus;
}
