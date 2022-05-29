package me.study.jpa.v1.controller;

import lombok.RequiredArgsConstructor;
import me.study.jpa.v1.repository.OrderRepository;
import org.springframework.web.bind.annotation.RestController;

/**
 * xToOne(ManyToOne, OneToOne) 관계 최적화
 * Order
 * Order -> Member
 * Order -> Delivery
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;
}
