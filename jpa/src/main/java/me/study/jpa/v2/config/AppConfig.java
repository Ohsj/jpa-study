package me.study.jpa.v2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 스프링 부트 사용시 생략 가능
 * 스프링 부트 사용시 @SpringBootApplication 위치를 지정(해당 패키지와 하위 패키지 인식)
 * 만약 위치가 달라지면 @EnableJpaRepositories 필요
 */
// @Configuration
//@EnableJpaRepositories(basePackages = "me.study.jpa.v2.repository")
public class AppConfig { }
