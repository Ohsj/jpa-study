package me.study.jpa.v2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityListeners;
import java.util.Optional;
import java.util.UUID;

@EnableJpaAuditing
@Configuration
public class JpaConfig {

    // 등록자, 수정자를 처리해주는 AuditorAware 스프링 빈
    // 실무에서는 세션 정보나, 스프링 시큐리티 로그인 정보에서 ID를 받는다.
    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> Optional.of(UUID.randomUUID().toString());
    }
}
