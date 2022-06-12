package me.study.jpa.v1.controller;

import lombok.RequiredArgsConstructor;
import me.study.jpa.v1.entity.Order;
import me.study.jpa.v1.model.OrderSearch;
import me.study.jpa.v1.model.OrderSimpleQueryDto;
import me.study.jpa.v1.model.SimpleOrderDto;
import me.study.jpa.v1.repository.OrderRepository;
import me.study.jpa.v1.repository.OrderSimpleQueryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * xToOne(ManyToOne, OneToOne) 관계 최적화
 * Order
 * Order -> Member
 * Order -> Delivery
 *
 * 쿼리 방식 선택 권장 순서
 * 1. 엔티티를 DTO로 변환하는 방법을 선택
 * 2. 필요하면 페지 초인으로 성능을 최적화 -> 대부분의  성능 이슈 해결
 * 3. 그래도 안되면 DTO로 직접 조회 방법 사용
 * 4. 최후의 방법은 JPA가 제공하는 네이티브 SQL이나 스프링 JDBC Template을 사용해서 SQL을 직접 사용
 */
//@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;
    private final OrderSimpleQueryRepository orderSimpleQueryRepository;

    /**
     * V1. 엔티티 직접 노출
     * - Hiberate5Module 모듈 등록, LAZY=null 처라
     * - 양방향 관계 문제 발생 -> @JsonIgnore
     */
    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        for (Order order: all) {
            order.getMember().getName(); // Lazy 강제 초기화
            order.getDelivery().getAddress(); // Lazy 강제 초기화
        }
        return all;
    }

    /**
     * 엔티티를 DTO로 변환
     */
    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> ordersV2() {
        List<Order> orders = orderRepository.findAll(new OrderSearch());

        return orders.stream()
                .map(SimpleOrderDto::new)
                .collect(toList());
    }

    /**
     * 엔티티를 DTO로 변환 - 페치 조인 최적화
     */
    @GetMapping("/api/v3/simple-orders")
    public List<SimpleOrderDto> ordersV3() {
        List<Order> orders = orderRepository.findAllWithMemberDelivery();

        return orders.stream()
                .map(SimpleOrderDto::new)
                .collect(toList());
    }

    /**
     * JPA에서 DTO로 바로 조회
     */
    @GetMapping("/api/v4/simple-orders")
    public List<OrderSimpleQueryDto> orderV4() {
        return orderSimpleQueryRepository.findOrderDtos();
    }

}
